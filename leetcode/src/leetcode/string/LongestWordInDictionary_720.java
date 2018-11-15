package leetcode.string;

public class LongestWordInDictionary_720 {
    public String longestWord(String[] words) {
        if (words == null || words.length == 0) {
            return "";
        }
        
        // Build TrieTree
        TrieNode root = new TrieNode("-");
        for (String word : words) {
            root.insert(word);
        }
        
        String currResult = "";
        for (TrieNode child : root.children) {
            if (child != null) {
                currResult = dfs(child, currResult);
            }
        }
        return currResult;
    }
    
    private String dfs(TrieNode node, String currResult) {
        if (node.word == null || node.word.isEmpty()) {
            // The word doesn't have all the substrings in the dict
            return currResult;
        }
        
        String currWord = node.word;
        if (currResult.length() < currWord.length()
                || currResult.length() == currWord.length() && currWord.compareTo(currResult) < 0) {
            currResult = currWord;
        }
        
        for (TrieNode child : node.children) {
            if (child != null) {
                currResult = dfs(child, currResult);
            }
        }
        return currResult;
    }
    
    class TrieNode {
        String word;
        TrieNode[] children = new TrieNode[26];
        
        TrieNode() {}
        
        TrieNode(String word) {
            this.word = word;
        }
        
        void insert(String newWord) {
            TrieNode curNode = this;
            for (char c : newWord.toCharArray()) {
                int idx = c - 'a';
                if (curNode.children[idx] == null) {
                    curNode.children[idx] = new TrieNode();
                }
                curNode = curNode.children[idx];
            }
            curNode.word = newWord;
        }
    }
}
