public class DictionaryCommandline {
    protected DictionaryManagement manage = new DictionaryManagement();

    /**
     * Display all words and theirs translation.
     */
    public void showAllWords() {
        System.out.println("|English     |Vietnamese");
        for (int i = 0; i < manage.dict.getDict().size(); i++) {
            System.out.println("|" + manage.dict.getDict().get(i).getWord_target() + "      |" + manage.dict.getDict().get(i).getWord_explain());
        }
    }

    public void dictionaryBasic() {
        manage.insertFromCommandline();
        showAllWords();
    }
}
