package Controller;

public class DictionaryCommandline {
    protected DictionaryManagement manager = new DictionaryManagement();

    /**
     * Display all words and theirs translation (cmd version)
     */
    public void showAllWords() {
        System.out.println("|English     |Vietnamese");
        for (int i = 0; i < manager.dict.getDict().size(); i++) {
            System.out.format("|%s  |%s \n", manager.dict.getDict().get(i).getWord_target(), manager.dict.getDict().get(i).getWord_explain());
        }
    }
}

