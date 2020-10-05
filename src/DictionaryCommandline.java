import java.io.IOException;

public class DictionaryCommandline {
    protected DictionaryManagement manage = new DictionaryManagement();

    /**
     * Display all words and theirs translation.
     */
    public void showAllWords() {
        System.out.println("|English     |Vietnamese");
        for (int i = 0; i < manage.dict.getDict().size(); i++) {
            System.out.format("|%s  |%s \n", manage.dict.getDict().get(i).getWord_target(), manage.dict.getDict().get(i).getWord_explain());
        }
    }

    public void dictionaryBasic() throws IOException {
//        manage.insertFromCommandline();
//        showAllWords();
    }
}
