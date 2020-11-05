package Modules;

import java.io.File;

public interface IModule {
    boolean checkFileExtension(String fileExtension);
    void showOptions();
    void executeOption(File file, int numFunc) throws Exception;
}
