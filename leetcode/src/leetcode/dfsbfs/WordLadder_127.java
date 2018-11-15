package leetcode.dfsbfs;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class WordLadder_127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }
        Set<String> begins = new HashSet<>();
        Set<String> ends = new HashSet<>();
        begins.add(beginWord);
        ends.add(endWord);
        
        int len = 0;
        while (!begins.isEmpty() && !ends.isEmpty()) {
            len++;
            Iterator<String> beginItor = begins.iterator();
            Set<String> nextWords = new HashSet<>();
            while (beginItor.hasNext()) {
                String currWord = beginItor.next();
                char[] nextChars = currWord.toCharArray();
                for (int i = 0; i < currWord.length(); i++) {
                    char originalChar = nextChars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        nextChars[i] = c;
                        String nextWord = String.valueOf(nextChars);
                        if (ends.contains(nextWord)) {
                            return len + 1;
                        }
                        if (dict.contains(nextWord)) {
                            dict.remove(nextWord);
                            nextWords.add(nextWord);
                        }
                    }
                    nextChars[i] = originalChar;
                }
                beginItor.remove();
            }
            begins.addAll(nextWords);
            
            // Swap the two sets
            Set<String> set = begins;
            begins = ends;
            ends = set;
        }
        return 0;
    }
}
