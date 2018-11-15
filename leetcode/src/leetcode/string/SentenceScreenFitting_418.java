package leetcode.string;

public class SentenceScreenFitting_418 {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        String s = String.join(" ", sentence) + " ";
        int len = 0;
        for (int i = 0; i < rows; i++) {
            len += cols;
            while (len >= 0 && s.charAt(len % s.length()) != ' ') {
                len--;
            }
            // Pointing to the next start
            len++;
        }
        return len / s.length();
    }
    public int wordsTyping1(String[] sentence, int rows, int cols) {
        if (sentence == null || sentence.length == 0) {
            return 0;
        }
        int numWords = sentence.length;
        int iWord = 0;
        
        int nSentences = 0;
        for (int i = 0; i < rows; i++) {
            int len = sentence[iWord % numWords].length();
            while (len <= cols) {
                iWord++;
                len += 1 + sentence[iWord % numWords].length();
            }
            if (iWord == 0) {
                return 0;
            } else if (iWord % numWords == 0) {
                // Next line starts with the first word again
                nSentences = (iWord / numWords) * (rows / (i + 1));
                i = rows - rows % (i + 1) - 1;
                iWord = 0;
            }
        }
        return nSentences + iWord / numWords;
    }
}
