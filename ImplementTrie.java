/**
 * Trie(Prefix Tree) is a sorted tree-based data-structure that stores the set of strings.
 * Used in many string search applications such as auto-complete, text search, and prefix matching.
 * The word's length determines the depth of the trie.
 * This implementation is only applied to lowercase English letters.
 * GOOD EXPLANATION: https://brilliant.org/wiki/tries/
 */

// leetcode 208.
public class ImplementTrie {
    public static void main(String[] args) {
        // Test
        Trie trie = new Trie();
        trie.insert("apple");
        assert trie.search("apple");
        assert !trie.search("app");
        assert trie.startsWith("app");
        trie.insert("app");
        assert trie.search("app");
        System.out.println("ALL TESTS PASSED.");
    }

    static class Trie {
        private static final int MAX_SIZE = 26; // Number of alphabet characters.
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        /**
         * m = longest word's length
         * n = number of words in the trie
         * a = input word's length
         */

        // Time: O(n), space: O(n)
        public void insert(String word) {
            if(word == null || word.length() == 0) return;

            TrieNode curr = root;
            for(int i = 0; i < word.length(); ++i) {
                int val = (int) word.charAt(i)-97;
                if(curr.children[val] == null)
                    curr.children[val] = new TrieNode();
                curr = curr.children[val];
            }

            curr.isEndOfWord = true;
        }
        
        // Time: O(n), space: O(1)
        public boolean search(String word) {
            TrieNode curr = root;
            for(int i = 0; i < word.length(); ++i) {
                int val = (int) word.charAt(i)-97;
                if(curr.children[val] == null)
                    return false;
                curr = curr.children[val];
            }
            
            return curr.isEndOfWord;
        }

        // Time: O(n), space: O(1)
        public boolean startsWith(String word) {
            TrieNode curr = root;
            for(int i = 0; i < word.length(); ++i) {
                int val = (int) word.charAt(i)-97;
                if(curr.children[val] == null)
                    return false;
                curr = curr.children[val];
            }

            return true;
        }

        private class TrieNode {
            TrieNode[] children;
            boolean isEndOfWord; // Mark if the current node is the end of stored words.

            public TrieNode() {
                children = new TrieNode[MAX_SIZE];
                isEndOfWord = false;
            }
        }
    }
}
