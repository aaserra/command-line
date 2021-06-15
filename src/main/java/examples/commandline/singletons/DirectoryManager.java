package examples.commandline.singletons;

import examples.commandline.components.DirectoryComponent;

public class DirectoryManager {

    private static DirectoryManager INSTANCE;

    private final DirectoryComponent ROOT;

    private DirectoryComponent currentDirectory;

    public static void createNewInstance() {
        synchronized(DirectoryManager.class) {
            INSTANCE = new DirectoryManager();
        }
    }

    public static DirectoryManager getInstance() {
        if (INSTANCE == null) {
            createNewInstance();
        }

        return INSTANCE;
    }

    private DirectoryManager() {
        ROOT = new DirectoryComponent("ROOT");
        currentDirectory = ROOT;
    }

    public DirectoryComponent getCurrentDirectory() {
        return currentDirectory;
    }

    public void setCurrentDirectory(DirectoryComponent dir) {
        currentDirectory = dir;
    }

    public void setCurrentDirectory(String dir) {
        if (dir.trim().equals("\\")) {
            currentDirectory = ROOT;
        } else {
            DirectoryComponent selectedDir;

            if (dir.startsWith("\\")) {
                selectedDir = findDirectoryByPath(ROOT, dir.substring(1));
            } else {
                selectedDir = findDirectoryByPath(getCurrentDirectory(), getCurrentDirectory().getFullPath() + "\\" + dir);
            }

            if (selectedDir == null) System.out.println("Directory not found");

            else currentDirectory = selectedDir;
        }
    }

    private static DirectoryComponent findDirectoryByPath(DirectoryComponent currentDirectory, String dir) {
        if (currentDirectory.getFullPath().toLowerCase().equals(dir.toLowerCase())) return currentDirectory;

        for (DirectoryComponent childDirectory : currentDirectory.getChilds()) {
            DirectoryComponent result = findDirectoryByPath(childDirectory, dir);

            if (result != null) return result;
        }

        return null;
    }

}