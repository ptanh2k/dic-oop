package Model;

import java.util.ArrayList;

public class Dictionary {
    public static ArrayList<Word> dictt = new ArrayList<Word>();

    public Dictionary() {
        //TODO
    }

    public Dictionary(ArrayList<Word> dict) {
        this.dictt = dict;
    }

    public void setDict(ArrayList<Word> dict) {
        this.dictt = dict;
    }

    public ArrayList<Word> getDict() {
        return dictt;
    }

}
