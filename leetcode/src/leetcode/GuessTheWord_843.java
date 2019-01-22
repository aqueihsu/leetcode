package leetcode;

import java.util.HashSet;
import java.util.Set;

public class GuessTheWord_843 {
    interface Master {
        public int guess(String word);
    }
    
    // 1. Has to match the match count with all the guessed words
    // 2. Pick the next possible solutions with the most diverse connection count to the next next guesses
    public void findSecretWord(String[] wordlist, Master master) {
        int numWords = wordlist.length;
        int[][] matchCounts = new int[numWords][numWords];
        
        int wordLength = wordlist[0].length();
        for (int i = 0; i < numWords; i++) {
            matchCounts[i][i] = wordLength;
            
            for (int j = i + 1; j < numWords; j++) {
                matchCounts[i][j] = matchCounts[j][i] = countMatches(wordlist[i], wordlist[j]);
            }
        }
        
        int[] guessedMatchCounts = new int[numWords];
        boolean[] visisted = new boolean[numWords];
        int iWord = 0;
        while ((guessedMatchCounts[iWord] = master.guess(wordlist[iWord])) < wordLength) {
            visisted[iWord] = true;
            
            Set<Integer> possibleSols = new HashSet<>();
            for (int i = 0; i < numWords; i++) {
                if (!visisted[i] && isPossibleSol(matchCounts, guessedMatchCounts, visisted, i)) {
                    possibleSols.add(i);
                }
            }
            iWord = bestNextSolution(possibleSols, matchCounts);
        }
    }
    
    private int countMatches(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            count += str1.charAt(i) == str2.charAt(i) ? 1 : 0;
        }
        return count;
    }
    
    private boolean isPossibleSol(int[][] matchCounts, int[] guessedMatchCounts, boolean[] visisted, int iWord) {
        int numWords = visisted.length;
        for (int j = 0; j < numWords; j++) {
            if (visisted[j] && matchCounts[iWord][j] != guessedMatchCounts[j]) {
                return false;
            }
        }
        return true;
    }

    private int bestNextSolution(Set<Integer> possibleSols, int[][] matchCounts) {
        int iNextSol = -1;
        int minMaxMatches = Integer.MAX_VALUE;
        for (int sol1 : possibleSols) {
            int maxMatches = Integer.MIN_VALUE;
            for (int sol2 : possibleSols) {
                maxMatches = Math.max(maxMatches, matchCounts[sol1][sol2]);
            }
            if (minMaxMatches > maxMatches) {
                minMaxMatches = maxMatches;
                iNextSol = sol1;
            }
        }
        return iNextSol;
    }
}
