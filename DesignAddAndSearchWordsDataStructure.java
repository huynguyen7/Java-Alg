import java.util.Arrays;

// leetcode 211.
public class DesignAddAndSearchWordsDataStructure {
    public static void main(String[] args) {
        WordDictionary test1 = new WordDictionary();
        test1.addWord("bad");
        test1.addWord("dad");
        test1.addWord("mad");
        assert !test1.search("pad"); // return False
        assert test1.search("bad"); // return True
        assert test1.search(".ad"); // return True
        assert test1.search("b.."); // return True
        assert !test1.search("ba"); // return False
        assert !test1.search(""); // return False

        WordDictionary test2 = new WordDictionary();
        test2.addWord("a");
        test2.addWord("a");
        assert test2.search("."); // expect true
        assert test2.search("a"); // expect true
        assert !test2.search("aa"); // expect false
        assert test2.search("a"); // expect true
        assert !test2.search(".a"); // expect false
        assert !test2.search("a."); // expect false
    }

    /**
     * Apply Trie data structure.
     */
    private static class WordDictionary {
        private static final int MAX_SIZE = 26;
        private TrieNode root;

        public WordDictionary() {
            root = new TrieNode();
        }
        
        // Time: O(n), space: O(n)
        public void addWord(String word) {
            if(word == null || word.length() == 0) return;

            TrieNode curr = root;
            for(int i = 0; i < word.length(); ++i) {
                int c = (int) word.charAt(i)-0x61;
                if(curr.children[c] == null)
                    curr.children[c] = new TrieNode();
                curr = curr.children[c];
            }

            curr.isEndOfWord = true;
        }
        
        // Time: O(n), space: O(n)
        public boolean search(String word) {
            if(word == null || word.length() == 0) return false;
            return dfs(word, 0, root);
        }

        private boolean dfs(String word, int startIndex, TrieNode curr) {
            if(!curr.isEndOfWord && startIndex >= word.length()) return false;
            else if(curr.isEndOfWord && startIndex == word.length()) return true;

            boolean flag = false;

            if(word.charAt(startIndex) != '.') {
                int c = (int) word.charAt(startIndex)-0x61;
                if(curr.children[c] == null) return false;
                flag = flag || dfs(word, startIndex+1, curr.children[c]);
            } else {
                for(int i = 0; i < MAX_SIZE; ++i) {
                    if(curr.children[i] != null)
                        flag = flag || dfs(word, startIndex+1, curr.children[i]);
                    if(flag) return true;
                }
            }

            return flag;
        }

        public static class TrieNode {
            public TrieNode[] children;
            public boolean isEndOfWord;

            public TrieNode() {
                children = new TrieNode[MAX_SIZE];
                isEndOfWord = false;
            }

            @Override
            public String toString() {
                return String.format("%b", isEndOfWord);
            }
        }
    }
}
