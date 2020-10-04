import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
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

    /**
     * Read data from file.
     * @return data from file
     */
    public ArrayList<Word> readFile() throws IOException {
        File file = new File("./data/dictionaries.txt");
        String[] split;
        ArrayList<Word> words = new ArrayList<>();
        try (Scanner sc = new Scanner(file, StandardCharsets.UTF_8)) {
            while (sc.hasNextLine()) {
                String cur_line = sc.nextLine();
                split = cur_line.split("\t");
                if (split.length >= 2) {
                    Word word = new Word(split[0], split[1]);
                    words.add(word);
                    System.out.println(split[0]);
                    System.out.println(split[1]);
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
     * Look for word.
     */
    public void dictionaryLookup() {
        Scanner sc = new Scanner(System.in);
        String word_target = sc.nextLine();
    }
}

