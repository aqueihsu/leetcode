package leetcode.string;

import java.util.ArrayList;
import java.util.List;

public class TextJustification_68 {
    private static final char SPACE = ' ';
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> output = new ArrayList<>();
        int firstWord = 0;
        
        while (firstWord < words.length) {
            int lastWord = firstWord + 1;
            int length = words[firstWord].length();
            while (lastWord < words.length && length + lastWord - firstWord + words[lastWord].length() <= maxWidth) {
                length += words[lastWord++].length();
            }
            
            StringBuilder builder = new StringBuilder();
            builder.append(words[firstWord]);
            if (lastWord == words.length || firstWord + 1 == lastWord) {
                // Last line
                for (int i = firstWord + 1; i < lastWord; i++) {
                    builder.append(SPACE);
                    builder.append(words[i]);
                }
                int remainingSpaces = maxWidth - builder.length();
                addSpaces(builder, remainingSpaces);
            } else {
                int remainingSpaces = maxWidth - length;
                int numDelimiters = lastWord - firstWord - 1;
                for (int i = firstWord + 1; i < lastWord; i++, numDelimiters--) {
                    int numSpaces = (int) Math.ceil((double) remainingSpaces / numDelimiters);
                    addSpaces(builder, numSpaces);
                    builder.append(words[i]);
                    remainingSpaces -= numSpaces; 
                }
            }
            output.add(builder.toString());
            firstWord = lastWord;
        }
        return output;
    }
    
    private void addSpaces(StringBuilder builder, int numSpaces) {
        while (--numSpaces >= 0) {
            builder.append(SPACE);
        }
    }
}
