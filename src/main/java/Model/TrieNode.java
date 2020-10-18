package Model;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    protected Map<Character, TrieNode> child;
    protected boolean is_complete_word;
    protected String value;                     //Meaning of the word.

    public TrieNode() {
        child = new HashMap<Character, TrieNode>();
        is_complete_word = false;
    }
}
