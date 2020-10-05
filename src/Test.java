import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        DictionaryCommandline dict_cmd = new DictionaryCommandline();
//        dict_cmd.dictionaryBasic();
//        dict_cmd.manage.insertFromFile();
//        dict_cmd.showAllWords();
        dict_cmd.manage.insertFromCommandline();
        dict_cmd.manage.dictionaryLookup();
    }
}
