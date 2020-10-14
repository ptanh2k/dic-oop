package Controller;

import Model.Dictionary;
import Model.Word;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    private static Scanner sc = new Scanner(System.in);

    public DictionaryManagement() {
        //TODO
    }

    protected static Dictionary dict = new Dictionary();

    private static final String fileName = "./data/dictionaries.txt";

    /**
     * Insert number of word to translate.
     * Insert words and theirs translation.
     */
    public static void insertFromCommandline(String fileName) throws IOException {
        insertFromFile();
        System.out.print("Nhập số từ muốn thêm: ");
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            System.out.print("Từ cần dịch:  ");
            String word_target = sc.nextLine();
            System.out.print("Nghĩa của từ: ");
            String word_explain = sc.nextLine();
            Word word = new Word(word_target, word_explain);
            dict.getDict().add(word);
        }
    }

    /**
     * Read data from file.
     *
     * @return data from file
     */
    public static void insertFromFile() throws IOException {
        File file = new File("./data/dictionaries.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String cur_line = sc.nextLine();
            String[] split = cur_line.split("\\t");
            if (split.length == 2) {
                Word word = new Word(split[0], split[1]);
                dict.getDict().add(word);
            }
        }
    }

    /**
     * Load dictionary from file.
     */
//    public static void insertFromFile() throws IOException {
//        ArrayList<Word> new_words = readFile();
//        for (Word new_word : new_words) {
//            dict.getDict().add(new_word);
//        }
//    }

    /**
     * Return index of word.
     *
     * @param word_target string
     * @return index
     */
    public static int getIndexByWord(String word_target) {
        int index = -1;
        int length = dict.getDict().size();
        for (int i = 0; i < length; i++) {
            if (word_target.equals(dict.getDict().get(i).getWord_target())) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Look up using linear search.
     */
    private static Word linearLookup(String word_target) {
        int length = dict.getDict().size();
        for (int i = 0; i < length; i++) {
            if (word_target.equals(dict.getDict().get(i).getWord_target())) {
                return dict.getDict().get(i);
            }
        }
        return null;
    }

    /**
     * Look up word.
     */
    public static String lookup(String word_target) {
        Word word = linearLookup(word_target);
        return word.getWord_explain();
    }

    /**
     * Look for word.
     */
    public static void dictionaryLookup() {
        System.out.print("Nhap tu can tim: ");
        String word_target = sc.nextLine();
        String result = lookup(word_target);
        System.out.println(result);
    }

    /**
     * Add word to dictionary.
     */
    public static void addWord(String word_target, String word_explain) throws IOException {
        Word word = new Word(word_target, word_explain);
        dict.getDict().add(word);
        dictionaryExportToFile();
    }

    /**
     * Remove word from dictionary.
     */
    public static void removeWord() throws IOException {
        System.out.println("Nhap tu can xoa: ");
        String removed_target = sc.nextLine();
        int index = getIndexByWord(removed_target);
        if (index != -1) {
            dict.getDict().remove(index);
        } else {
            System.out.print("Word not found");
        }
        dictionaryExportToFile();
    }

    /**
     * Edit word in dictionary.
     */
    public static void editWord() throws IOException {
        Scanner sc = new Scanner(System.in);
        String edited_word = sc.nextLine();
        int index = getIndexByWord(edited_word);
        if (index != -1) {
            String new_target = sc.nextLine();
            String new_explain = sc.nextLine();
            dict.getDict().get(index).setWord_target(new_target);
            dict.getDict().get(index).setWord_explain(new_explain);
        } else {
            System.out.print("Word not found");
        }
        dictionaryExportToFile();
    }

//    public static ArrayList<String> dictionarySearcher(ArrayList<Word> list, String key){
//        ArrayList<String> result = new ArrayList<>();
//        String pattern = ".*" + key.toLowerCase() + ".*";
//        for (int i = 0; i < list.size(); i++) {
//            Word word = list.get(i);
//            if (word.getWord_target().toLowerCase().matches(pattern)) {
//                result.add(word.getWord_target());
//            }
//        }
//        return result;
//    }

    /**
     * Show suggestion.
     */
    public static ArrayList<String> dictionarySearcher(String key) throws IOException {
        ArrayList<String> result = new ArrayList<String>();

        for (int i = 0; i < dict.getDict().size(); i++) {
            if (dict.getDict().get(i).getWord_target().indexOf(key) == 0) {
                result.add(dict.getDict().get(i).getWord_target());
            }
        }
        return result;
    }

    /**
     * Export data to file.
     */
    public static void dictionaryExportToFile() throws IOException {
        FileWriter writer = new FileWriter("data/dictionaries.txt");
        for (Word word : dict.getDict()) {
            writer.write(String.format("%s\t%s\n", word.getWord_target(), word.getWord_explain()));
        }
        writer.close();
        System.out.println(dict.getDict());
    }

    /**
     * Display dictionary.
     */
    public static void showDictionary() {
        System.out.println("_____ TU DIEN ____");
        for (Word word : dict.getDict()) {
            System.out.format("%s\t%s \n", word.getWord_target(), word.getWord_explain());
        }
    }
}

