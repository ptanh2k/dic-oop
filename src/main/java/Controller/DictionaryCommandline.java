package Controller;

import Model.Dictionary;

public class DictionaryCommandline {

    /**
     * Display all words and theirs translation (cmd version)
     */
    public void showAllWords() {
        System.out.println("|English     |Vietnamese");
        for (int i = 0; i < Dictionary.dict.size(); i++) {
            System.out.format("|%s  |%s \n", Dictionary.dict.get(i).getWord_target(), Dictionary.dict.get(i).getWord_explain());
        }
    }
}

