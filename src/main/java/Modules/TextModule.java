package Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class TextModule implements IModule {

    public boolean checkingFileExtension(String fileExtension) {
        return fileExtension.equals("txt");
    }

    public void functionDefinition() {
        System.out.println("1 - Get character occurrence rate");
        System.out.println("2 - Get count of lines");
        System.out.println("3 - Get count of punctuation");
    }

    public void func(File file, int numFunc) throws Exception {
        TextModule.class.getMethod("func" + numFunc, File.class).invoke(this, file);
    }


    public void func1(File file) {
        int totalCharacters = 0;
        Scanner scanner = new Scanner(file.getPath());
        Hashtable<Character, ArrayList<Character>> dictionary = new Hashtable<>();

        while (scanner.hasNext()) {
            char[] str = scanner.next().toLowerCase().toCharArray();
            for (char symbol : str) {
                if (!dictionary.containsKey(symbol))
                    dictionary.put(symbol, new ArrayList<>());
                ArrayList<Character> list = dictionary.get(symbol);
                list.add(symbol);
                totalCharacters++;
            }
        }

        System.out.println("Total symbols count - " + totalCharacters);

        for (ArrayList<Character> list : dictionary.values()) {
            int count = list.size();
            System.out.println("Count of each symbol - '" + list.get(0) + "' = " + count);
        }
    }

    public void func2(File file) throws FileNotFoundException {
        int linesCount = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            linesCount++;
        }
        System.out.println("Lines count: " + linesCount);
    }

    public void func3(File file) throws FileNotFoundException {
        ArrayList<Character> punctuationList = new ArrayList<>(Arrays.asList(',', '.', '!', '\'', '\"', '-', '?', ':', ';'));
        int totalPunctuation = 0;
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()) {
            char[] str = scanner.next().toLowerCase().toCharArray();
            for (char symbol : str) {
                if (punctuationList.contains(symbol))
                    totalPunctuation++;
            }
        }
        System.out.println("Punctuation count: " + totalPunctuation);
    }
}
