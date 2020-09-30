import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class DictionaryManagement {
    public DictionaryManagement() {
        //TODO
    }

    protected Dictionary dict = new Dictionary();

    /**
     * Insert number of word to translate.
     * Insert words and theirs translation.
     */
    public void insertFromCommandline() {
        Scanner sc = new Scanner(System.in);

        //Number of word
        System.out.println("Number of word to translate: ");
        int num_word = sc.nextInt();
        sc.nextLine();
        //Insert word and its translation
        for (int i = 1; i  < num_word + 1; i++) {
            System.out.print("Word to trans: ");
            String word_target = sc.nextLine();

            System.out.print("Translation: ");
            String word_explain = sc.nextLine();
            Word word = new Word(word_target, word_explain);
            dict.getDict().add(word);
        }
    }

}
