package leetcode.dfsbfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class WordLadderII_126 {
    // Another sol: use bfs to determine the shortest length and then use dfs to find the combination
    //              that matches the length.
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> results = new ArrayList<>(0);
        
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return results;
        }
        
        Map<String, List<List<String>>> begins = new HashMap<>();
        Map<String, List<List<String>>> ends = new HashMap<>();
        List<List<String>> beginLists = new LinkedList<>();
        List<List<String>> endLists = new LinkedList<>();
        beginLists.add(new LinkedList<>(Arrays.asList(beginWord)));
        endLists.add(new LinkedList<>(Arrays.asList(endWord)));
        begins.put(beginWord, beginLists);
        ends.put(endWord, endLists);
        
        boolean isBegin = true;
        while (!begins.isEmpty() && !ends.isEmpty() && results.isEmpty()) {
            Map<String, List<List<String>>> nextBegins = new HashMap<>();
            
            for (Map.Entry<String, List<List<String>>> entry : begins.entrySet()) {
                String currWord = entry.getKey();
                List<List<String>> paths = entry.getValue();
                
                char[] nextChars = currWord.toCharArray();
                for (int i = 0; i < nextChars.length; i++) {
                    char originalChar = nextChars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        nextChars[i] = c;
                        String nextWord = String.valueOf(nextChars);
                        if (ends.containsKey(nextWord)) {
                            results.addAll(isBegin ? concatenatePaths(paths, ends.get(nextWord))
                                    : concatenatePaths(ends.get(nextWord), paths));
                        }
                        if (dict.contains(nextWord)) {
                            if (nextBegins.containsKey(nextWord)) {
                                nextBegins.get(nextWord).addAll(genNextPaths(isBegin, nextWord, paths));
                            } else {
                                nextBegins.put(nextWord, genNextPaths(isBegin, nextWord, paths));
                            }
                        }
                    }
                    nextChars[i] = originalChar;
                }
            }
            
            // Swap
            dict.removeAll(nextBegins.keySet());
            begins = ends;
            ends = nextBegins;
            isBegin = !isBegin;
        }
        return results;
    }
    
    private List<List<String>> genNextPaths(boolean isBegin, String nextWord, List<List<String>> curPaths) {
        List<List<String>> nextPaths = new ArrayList<>();
        for (List<String> curPath : curPaths) {
            List<String> nextPath = new ArrayList<>(curPath.size() + 1);
            if (isBegin) {
                nextPath.addAll(curPath);
                nextPath.add(nextWord);
            } else {
                nextPath.add(nextWord);
                nextPath.addAll(curPath);
            }
            nextPaths.add(nextPath);
        }
        return nextPaths;
    }
    
    private List<List<String>> concatenatePaths(List<List<String>> paths1, List<List<String>> paths2) {
        List<List<String>> results = new ArrayList<>();
        for (List<String> path1 : paths1) {
            for (List<String> path2 : paths2) {
                List<String> result = new ArrayList<>(path1.size() + path2.size());
                result.addAll(path1);
                result.addAll(path2);
                results.add(result);
            }
        }
        return results;
    }
}
