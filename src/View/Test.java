package View;

import Controller.DictionaryCommandline;
import Controller.DictionaryManagement;
import Model.Word;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {

    public static void main(String[] args) throws IOException {
        DictionaryCommandline commandline = new DictionaryCommandline();

        int choice = 0;
        String fileName = "./data/dictionaries.txt";
        DictionaryManagement manager = new DictionaryManagement();
        ArrayList<Word> dict = new ArrayList<>();

        manager.insertFromCommandline(fileName);

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("____MENU____");
            System.out.println("1. Thêm từ ");
            System.out.println("2. Tìm từ ");
            System.out.println("3. Xoá từ ");
            System.out.println("4. Sửa từ ");
            System.out.println("5. Show dữ liệu tử điển");
            System.out.println("0. Thoát ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    break;
                case 1:
                    manager.addWord(fileName);
                    break;
                case 2:
                    manager.dictionaryLookup();
                    break;
                case 3:
                    manager.removeWord(fileName);
                    break;
                case 4:
                    manager.editWord(fileName);
                    break;
                case 5:
                    showDictionary(dict);
                    break;
            }
        } while (choice != 0);
    }

    /**
     * Display dictionary.
     */
    public static void showDictionary(ArrayList<Word> dict) {
        System.out.println("_____ TU DIEN ____");
        for (Word word : dict){
            System.out.println(word);
        }
    }
}

