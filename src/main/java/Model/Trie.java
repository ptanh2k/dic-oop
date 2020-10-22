package Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Trie {
    private TrieNode root;
    ArrayList<String> words;
    TrieNode prefix_root;
    String cur_prefix;

    public Trie() {
        root = new TrieNode();
        words = new ArrayList<String>();
    }

    /**
     * Insert a word to trie.
     */
    public void insertWordToTrie(Word word) {
        TrieNode current = root;
        for (int i = 0; i < word.getWord_target().length(); i++) {
            char ch = word.getWord_target().charAt(i);
            TrieNode node = current.child.get(ch);
            if (node == null) {
                node = new TrieNode();
                current.child.put(ch, node);
            }
            current = node;
        }
        current.is_complete_word = true;
        current.value = word.getWord_explain();
    }

    /**
     * See if target word is present in trie.
     */
    public boolean searchInTrie(String search_target) {
        TrieNode current = root;
        for (int i = 0; i < search_target.length(); i++) {
            char ch = search_target.charAt(i);
            TrieNode node = current.child.get(ch);

            if (node == null) {
                return false;
            }
            current = node;
        }
        return !current.child.isEmpty() || current.is_complete_word;
    }

//    public TrieNode searchNode(String key) {
//        Map<Character, TrieNode> child = root.child;
//        TrieNode current = root;
//        for (int i = 0; i < key.length(); i++) {
//            char ch = key.charAt(i);
//            TrieNode node = child.get(ch);
//
//            if (node == null) {
//                return null;
//            }
//
//            current = node;
//            child = current.child;
//        }
//        prefix_root = current;
//        cur_prefix = key;
//        words.clear();
//        return current;
//    }



    /**
     * Look for explanation in trie.
     */
    public String lookup(String search_target) {
        String result = "";
        TrieNode current = root;
        for (int i = 0; i < search_target.length(); i++) {
            char ch = search_target.charAt(i);
            current = current.child.get(ch);
            if (current == null) {
                return  "";
            }
        }
        if (current.is_complete_word) {
            result = current.value;
        }
        return result;
    }
}
