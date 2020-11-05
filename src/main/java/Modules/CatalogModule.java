package Modules;


import java.io.File;
import java.util.*;

public class CatalogModule implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("directory");
    }

    public void showOptions() {
        System.out.println("1 - get list of files in dir");
        System.out.println("2 - Get files' total size");
        System.out.println("3 - Get count of files in dir");
    }

    public void executeOption(File file, int optionIndex) throws Exception {
        CatalogModule.class.getMethod("executeOption" + optionIndex, File.class).invoke(this, file);
    }

    public void executeOption1(File path) {
        StringBuilder fileNames = new StringBuilder();
        File[] files = path.listFiles();
        System.out.println("Files in " + path + ": \n");
        for (File file : files) {
            fileNames.append(file.getName()).append("\n");
        }
        System.out.println(fileNames);
    }

    public void executeOption2(File path) {
        double totalSize = 0;
        File[] files = path.listFiles();
        for (File file : files) {
            totalSize += file.length() / 1024;
        }
        System.out.println("Files' total size: " + String.format("%.2f", totalSize) + " KB");
    }

    public void executeOption3(File path) {
        File[] files = path.listFiles();
        System.out.println("Count of files in " + path + ": " + files.length);
    }
}

