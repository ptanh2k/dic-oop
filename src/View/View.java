package View;

import Controller.DictionaryController;
import Model.Dictionary;

import java.util.ArrayList;
import java.util.Scanner;

public class View {
    public static void main(String[] args) {
        int choice = 0;
        String fileName = "Dictionary.txt";
        DictionaryController controller = new DictionaryController();
        ArrayList<Dictionary> dicts = new ArrayList<Dictionary>();

        Scanner scanner = new Scanner(System.in);
        do{
            System.out.println("____MENU____");
            System.out.println("1. Thêm mới từ: ");
            System.out.println("2. Hiển thị danh sách từ điển");
            System.out.println("0. Thoat");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 0:
                    break;
                case 1:
                    String word, translate;
                    System.out.println("Nhập từ mới: ");
                    word = scanner.nextLine();
                    System.out.println("Nhập nghĩa của từ");
                    translate = scanner.nextLine();

                    Dictionary dict = new Dictionary(0,word,translate);
                    controller.writeWordToFile(dict, fileName);
                    break;
                case 2:
                    dicts = controller.readWordFromFile(fileName);
                    showDictionary(dicts);
                    break;
            }
        }while (choice !=0);

    }

    public static void showDictionary(ArrayList<Dictionary> dicts){
        System.out.println("_____ TU DIEN ____");
        for(Dictionary d : dicts){
            System.out.println(d);
        }
    }


}
