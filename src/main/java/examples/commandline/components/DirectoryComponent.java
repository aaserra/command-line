package examples.commandline.components;
import java.util.ArrayList;
import java.util.List;

public class DirectoryComponent {
    
    private String name;
    private DirectoryComponent parent;
    private List<String> files = new ArrayList<>();
    private List<DirectoryComponent> childDirectories = new ArrayList<>();

    public DirectoryComponent(String name) {
        this.parent = null;
        this.name = name;
        clear();
    }

    public DirectoryComponent(DirectoryComponent parentDirectory, String name) {
        this.parent = parentDirectory;
        this.name = name;
        clear();
    }

    public void clear() {
        files = new ArrayList<>();
        childDirectories = new ArrayList<>();
    }

    public List<DirectoryComponent> getChilds() {
        return childDirectories;
    }

    public List<String> getFiles() {
        return files;
    }

    public void addChild(String name) {
        if (!validateDirectoryName(name)) {
            System.out.println("Invalid directory name");
        } else if (containsChildDirectory(name)) {
            System.out.println("Directory already exists");
        } else {
            DirectoryComponent childDirectory = new DirectoryComponent(this, name);
            childDirectories.add(childDirectory);
        }
    }

    public void addFile(String name) {
        if (!validateFileName(name)) {
            System.out.println("Invalid file name");
        } else if (files.contains(name)) {
            System.out.println("File already exists");
        } else {
            files.add(name);
        }
    }

    public String getName() {
        return name;
    }

    public String getFullPath() {
        if (parent == null) return name;

        return parent.getFullPath() + "\\" + name;
    }

    private boolean containsChildDirectory(String name) {
        for(DirectoryComponent directory : childDirectories) {
            if (directory.name.toLowerCase().equals(name.toLowerCase())) return true;
        }

        return false;
    }

    private static boolean validateDirectoryName(String name) {
        return name.matches("[0-9a-z\\_\\-]{1,50}");
    }

    private static boolean validateFileName(String name) {
        return name.matches("[0-9a-z\\_\\-]+\\.[0-9a-z]{1,3}");
    }

}
