package Modules;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class TextModule implements IModule {

    public boolean checkFileExtension(String fileExtension) {
        return fileExtension.equals("txt");
    }

    public void showOptions() {
        System.out.println("1 - Get character occurrence frequency");
        System.out.println("2 - Get count of lines");
        System.out.println("3 - Get count of punctuation");
    }

    public void executeOption(File file, int optionIndex) throws Exception {
        TextModule.class.getMethod("executeOption" + optionIndex, File.class).invoke(this, file);
    }


    public void executeOption1(File file) throws FileNotFoundException {
        int totalCharacters = 0;
        Scanner scanner = new Scanner(file);
        Map<Character, ArrayList<Character>> dictionary = new HashMap<>();

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
            System.out.println("Count of each symbol - '" + list.get(0) + "': " + count);
        }
    }

    public void executeOption2(File file) throws FileNotFoundException {
        int linesCount = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            scanner.nextLine();
            linesCount++;
        }
        System.out.println("Lines count: " + linesCount);
    }

    public void executeOption3(File file) throws FileNotFoundException {
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
