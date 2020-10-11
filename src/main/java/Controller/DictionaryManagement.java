package Controller;

import Model.Dictionary;
import Model.Word;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DictionaryManagement {
    public DictionaryManagement() {
        //TODO
    }

    protected Dictionary dict = new Dictionary();

    /**
     * Insert number of word to translate.
     * Insert words and theirs translation.
     */
    public void insertFromCommandline(String filename) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập số từ muốn thêm: ");
        int num = sc.nextInt();
        for (int i = 0; i < num; i++) {
            sc.nextLine();
            System.out.print("Từ cần dịch:  ");
            String word_target = sc.nextLine();
            sc.nextLine();
            System.out.print("Nghĩa của từ: ");
            String word_explain = sc.nextLine();
            Word word = new Word(word_target, word_explain);
            dict.getDict().add(word);
        }
        this.dictionaryExportToFile(filename);
    }

    /**
     * Read data from file.
     * @return data from file
     */
    public ArrayList<Word> readFile() throws IOException {
        File file = new File("./data/dictionaries.txt", "UTF8");
        String[] split;
        ArrayList<Word> words = new ArrayList<>();
        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNextLine()) {
                String cur_line = sc.nextLine();
                split = cur_line.split("\\t");
                if (split.length == 2) {
                    Word word = new Word(split[0], split[1]);
                    dict.getDict().add(word);
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }

    /**
     * Load dictionary from file.
     */
    public void insertFromFile() throws IOException {
        ArrayList<Word> new_words = readFile();
        for (Word new_word : new_words) {
            dict.getDict().add(new_word);
        }
    }

    /**
     * Return index of word.
     * @param word_target string
     * @return index
     */
    public int getIndexByWord(String word_target) {
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
     * Look for word.
     */
    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap tu can tim: ");
        String word_target = sc.nextLine();
        String result = dict.lookup(word_target);
        System.out.println(result);
    }

    /**
     * Add word to dictionary.
     */
    public void addWord(String filename) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.print("Them tu can giai nghia: ");
        String word_target = sc.nextLine();
        System.out.print("Them nghia cua tu: ");
        String word_explain = sc.nextLine();
        Word word = new Word(word_target, word_explain);
        if (!dict.getDict().contains(word)) {
            dict.getDict().add(word);
        } else {
            System.out.print("Duplicate!");
        }
        this.dictionaryExportToFile(filename);
    }

    /**
     * Remove word from dictionary.
     */
    public void removeWord(String filename) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu can xoa: ");
        String removed_target = sc.nextLine();
        int index = getIndexByWord(removed_target);
        if (index != -1) {
            dict.getDict().remove(index);
        } else {
            System.out.print("Word not found");
        }
        this.dictionaryExportToFile(filename);
    }

    /**
     * Edit word in dictionary.
     */
    public void editWord(String filename) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap tu can sua: ");
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
        this.dictionaryExportToFile(filename);
    }

    /**
     * Export data to file.
     */
    public void dictionaryExportToFile(String filename) throws IOException {
        FileWriter writer = new FileWriter(filename);
        for (Word word : dict.getDict()) {
            writer.write(String.format("%s\t%s \n", word.getWord_target(), word.getWord_explain()));
        }
        writer.close();
    }

    /**
     * Display dictionary.
     */
    public void showDictionary() {
        System.out.println("_____ TU DIEN ____");
        for (Word word : dict.getDict()) {
            System.out.format("%s\t%s \n", word.getWord_target(), word.getWord_explain());
        }
    }
}

