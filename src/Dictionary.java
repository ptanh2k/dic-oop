import java.util.ArrayList;

public class Dictionary {
    private ArrayList<Word> dict = new ArrayList<Word>();

    public Dictionary() {
        //TODO
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
