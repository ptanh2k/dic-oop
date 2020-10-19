package Model;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
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
     * Insert dictionary to trie.
     */
//    public void insertDataToTrie() {
//        for (Word word : Dictionary.dict) {
//            insertWordToTrie(word);
//        }
//    }

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
