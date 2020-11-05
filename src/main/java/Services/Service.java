package Services;

import Modules.IModule;

import java.io.File;
import java.util.Scanner;

public class Service {

    private static int option;

    public void showOperationChoice(IModule module, File file) throws Exception {

        while (chooseCommand(module) != 0) {
            try {
                executeOption(option, module, file);
            } catch (NoSuchMethodException e) {
                System.out.println("Unknown command");
            }
        }
    }

    private static int chooseCommand(IModule module) {
        System.out.println();
        System.out.println("0 - Exit");
        System.out.println();
        module.showOptions();
        Scanner scan = new Scanner(System.in);
        return option = scan.nextInt();
    }

    private static void executeOption(int command, IModule module, File file) throws Exception {
        module.executeOption(file, command);
    }
}

