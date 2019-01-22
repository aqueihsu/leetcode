package leetcode;

public class GuessTheWord_843 {
    interface Master {
        public int guess(String word);
    }
    
    public void findSecretWord(String[] wordlist, Master master) {
        int numWords = wordlist.length;
        int[][] matcheCounts = new int[numWords][numWords];
        
        int strLength = wordlist[0].length();
        for (int i = 0; i < numWords; i++) {
            matcheCounts[i][i] = strLength;
            
            for (int j = i + 1; j < numWords; j++) {
                matcheCounts[i][j] = matcheCounts[j][i] = countMatches(wordlist[i], wordlist[j]);
            }
        }
        
        String word = wordlist[0];
        int nMatches = 0;
        while ((nMatches = master.guess(word)) < numWords) {
            
        }
    }
    
    private int countMatches(String str1, String str2) {
        int count = 0;
        for (int i = 0; i < str1.length(); i++) {
            count += str1.charAt(i) == str2.charAt(i) ? 1 : 0;
        }
        return count;
    }
}
