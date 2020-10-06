package Model;

public class Dictionary {
    private static int id = 0;
    private int wordID;
    private String word_target;
    private String word_explain;

    public Dictionary() {

    }

    public Dictionary(int wordID) {
        this.wordID = wordID;
    }

    public Dictionary(int wordID, String word_target, String word_explain) {

        if(wordID == 0){
            this.wordID = id++;
        } else{
            this.wordID = wordID;
        }
        this.word_target = word_target;
        this.word_explain = word_explain;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Dictionary.id = id;
    }

    public int getWordID() {
        return wordID;
    }

    public void setWordID(int wordID) {
        this.wordID = wordID;
    }

    public String getWord_target() {
        return word_target;
    }

    public void setWord_target(String word_target) {
        this.word_target = word_target;
    }

    public String getWord_explain() {
        return word_explain;
    }

    public void setWord_explain(String word_explain) {
        this.word_explain = word_explain;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "wordID=" + wordID +
                ", word_target='" + word_target + '\'' +
                ", word_explain='" + word_explain + '\'' +
                '}';
    }
}
