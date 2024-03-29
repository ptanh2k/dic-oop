package Controller;

import Model.Dictionary;
import Model.Trie;
import Model.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DictionaryManagement {
    private static Scanner sc = new Scanner(System.in);

    public static Trie trie = new Trie();

    public DictionaryManagement() {
        //TODO
    }

    /**
     * Insert number of word to translate.
     * Insert words and theirs translation.
     */
    public static void insertFromCommandline() throws IOException {
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
            Dictionary.dict.add(word);
            trie.insertWordToTrie(word);
        }
    }

    /**
     * Load data from file.
     */
    public static void insertFromFile() throws IOException {
        File file = new File("./data/dictionaries.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            String cur_line = sc.nextLine();
            String[] split = cur_line.split("\\t");
            if (split.length == 2) {
                Word word = new Word(split[0], split[1]);
                Dictionary.dict.add(word);
                trie.insertWordToTrie(word);
            }
        }
    }

    /**
     * Return index of word.
     *
     * @param word_target string
     * @return index
     */
    public static int getIndexByWord(String word_target) {
        int index = -1;
        int length = Dictionary.dict.size();
        for (int i = 0; i < length; i++) {
            if (word_target.equals(Dictionary.dict.get(i).getWord_target())) {
                index = i;
            }
        }
        return index;
    }

    /**
     * Look up using linear search.
     */
    private static Word linearLookup(String word_target) {
        int length = Dictionary.dict.size();
        for (int i = 0; i < length; i++) {
            if (word_target.equals(Dictionary.dict.get(i).getWord_target())) {
                return Dictionary.dict.get(i);
            }
        }
        return null;
    }

    /**
     * Look up word. (Linear search version)
     */
//    public static String lookup(String word_target) {
//        Word word = linearLookup(word_target);
//        return word.getWord_explain();
//    }

    /**
     * Look for word.
     */
    public static void dictionaryLookup() {
        System.out.print("Nhap tu can tim: ");
        String word_target = sc.nextLine();
//        String result = lookup(word_target);
//        System.out.println(result);
        String result = trie.lookup(word_target);
        System.out.println(result);
        System.out.println(trie.searchInTrie(word_target));
    }

    /**
     * Add word to dictionary.
     */
    public static void addWord(String word_target, String word_explain) throws IOException {
        Word word = new Word(word_target, word_explain);
        Dictionary.dict.add(word);
        trie.insertWordToTrie(word);
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
            Dictionary.dict.remove(index);
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
            Dictionary.dict.get(index).setWord_target(new_target);
            Dictionary.dict.get(index).setWord_explain(new_explain);
        } else {
            System.out.print("Word not found");
        }
        dictionaryExportToFile();
    }

    /**
     * Show words begin with string key. (linear search version).
     */
    public static ArrayList<String> dictionarySearcher(String key) throws IOException {
        ArrayList<String> result = new ArrayList<String>();
        for (int i = 0; i < Dictionary.dict.size(); i++) {
            if (Dictionary.dict.get(i).getWord_target().indexOf(key) == 0) {
                result.add(Dictionary.dict.get(i).getWord_target());
            }
        }
        return result;
    }

    /**
     * Export data to file.
     */
    public static void dictionaryExportToFile() throws IOException {
        FileWriter writer = new FileWriter("data/dictionaries.txt");
        for (Word word : Dictionary.dict) {
            writer.write(String.format("%s\t%s\n", word.getWord_target(), word.getWord_explain()));
        }
        writer.close();
        System.out.println(Dictionary.dict);
    }

    /**
     * Display dictionary.
     */
    public static void showDictionary() {
        System.out.println("_____ TU DIEN ____");
        for (Word word : Dictionary.dict) {
            System.out.format("%s\t%s \n", word.getWord_target(), word.getWord_explain());
        }
    }

    /**
     * Show words with similar keyword.
     */
//    public static ArrayList<String> similarWords(String key) {
//        ArrayList<String> result = new ArrayList<String>();
//        if (!Dictionary.dict.contains(key)) {
//            for (Word word : Dictionary.dict) {
//                if (word.getWord_target().compareTo(key) >= 3) {
//                    result.add(word.getWord_target());
//                }
//            }
//        }
//        return result;
//    }

// Show words contain string key.
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
     * Searcher (Binary search version).
     */
//    public static ArrayList<String> dictionarySearcher(String key) throws IOException {
//        ArrayList<String> result = new ArrayList<String>();
//        String word = "^" + key;
//        Pattern pattern = Pattern.compile(word, Pattern.CASE_INSENSITIVE);
//
//        int left = 0;
//        int right = Dictionary.dict.size();
//        int mid;
//        do {
//            mid = (left + right) / 2;
//            Matcher matcher = pattern.matcher(Dictionary.dict.get(mid).getWord_target());
//
//            if (matcher.lookingAt()) {
//                result.add(Dictionary.dict.get(mid).getWord_target());
//                return result;
//            } else if (Dictionary.dict.get(mid).getWord_target().compareTo(key) > 0) {
//                left = mid + 1;
//
//            } else {
//                right = mid - 1;
//
//            }
//        }
//        while (left <= right && !key.equals(""));
//
//        result.add("");
//        return result;
//    }
}
