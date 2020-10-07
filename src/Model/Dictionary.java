package Model;

import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> dict = new ArrayList<Word>();

    public Dictionary() {
        //TODO
    }

    /**
     * Look up using linear search.
     */
    private Word linearLookup(String word_target) {
        int length = dict.size();
        for (int i = 0; i < length; i++) {
            if (word_target.equals(dict.get(i).getWord_target())) {
                return dict.get(i);
            }
        }
        return null;
    }

    /**
     * Look up word.
     */
    public String lookup(String word_target) {
        Word word = linearLookup(word_target);
        assert word != null;
        return word.getWord_explain();
    }

    public Dictionary(ArrayList<Word> dict) {
        this.dict = dict;
    }

    public void setDict(ArrayList<Word> dict) {
        this.dict = dict;
    }

    public ArrayList<Word> getDict() {
        return dict;
    }

}
