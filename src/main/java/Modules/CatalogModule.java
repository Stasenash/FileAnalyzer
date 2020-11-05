package Modules;


import java.io.File;
import java.util.*;

public class CatalogModule implements IModule {

    public boolean checkingFileExtension(String fileExtension) {
        return fileExtension.equals("directory");
    }

    public void functionDefinition() {
        System.out.println("1 - Get list files");
        System.out.println("2 - Get files' size");
        System.out.println("3 - Get count of files");
    }

    public void func(File file, int numFunc) throws Exception {
        CatalogModule.class.getMethod("func" + numFunc, File.class).invoke(this, file);
    }

    public void func1(File path) {
        StringBuilder attrFiles = new StringBuilder();
        File[] files = path.listFiles();

        for (File file : files) {
            attrFiles.append(file.getName())
                    .append("\n");
        }
        System.out.println(attrFiles);
    }

    public void func2(File path) {
        double sum = 0;
        File[] files = path.listFiles();
        for (File file : files) {
            double KB = (double) file.length() / 1024;
            sum += KB;
        }
        System.out.println("Summary = " + String.format("%.2f", sum) + " KB");
    }

    public void func3(File path) {
        File[] files = path.listFiles();
        System.out.println("Count of files in " + path + " = " + files.length);
    }
}

