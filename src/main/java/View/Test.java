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
        ArrayList<Word> dict = new ArrayList<>();
        String key = null;

        DictionaryManagement.insertFromCommandline(fileName);

        Scanner scanner = new Scanner(System.in);

        do {
            DictionaryManagement.insertFromFile();
            System.out.println("____MENU____");
            System.out.println("1. Thêm từ ");
            System.out.println("2. Tìm từ ");
            System.out.println("3. Xoá từ ");
            System.out.println("4. Sửa từ ");
            System.out.println("5. Show dữ liệu tử điển");
            System.out.println("6. Các gợi ý từ: ");
            System.out.println("0. Thoát ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    break;
                case 1:
                    System.out.print("Tu can giai nghia: ");
                    String word_target = scanner.nextLine();
                    System.out.print("Nghia cua tu: ");
                    String word_explain = scanner.nextLine();
                    DictionaryManagement.addWord(word_target, word_explain);
                    break;
                case 2:
                    DictionaryManagement.dictionaryLookup();
                    break;
                case 3:
                    DictionaryManagement.removeWord();
                    break;
                case 4:
                    DictionaryManagement.editWord();
                    break;
                case 5:
                    DictionaryManagement.showDictionary();
                    break;
                case 6:
                    DictionaryManagement.dictionarySearcher(dict, key);
                    break;
            }
        } while (choice != 0);
    }

}

