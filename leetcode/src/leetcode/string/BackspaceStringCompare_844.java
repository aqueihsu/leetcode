package leetcode.string;

public class BackspaceStringCompare_844 {
    // Space O(n) solution: use stack, pop when seeing a backspace
    // String problem: sometimes you have to do it backward! :)
    public boolean backspaceCompare(String S, String T) {
        if (S == null) {
            return T == null;
        }
        int si = S.length() - 1, ti = T.length() - 1;
        while (si >= 0 && ti >= 0) {
            si = handleBackspaces(S, si);
            ti = handleBackspaces(T, ti);
            if (si >= 0 && ti >= 0 && S.charAt(si--) != T.charAt(ti--)) {
                return false;
            }
        }
        si = handleBackspaces(S, si);
        ti = handleBackspaces(T, ti);
        return si == ti;
    }
    
    private int handleBackspaces(String string, int i) {
        int nBackspaces = 0;
        while (i >= 0 && string.charAt(i) == '#') {
            while (i >= 0 && string.charAt(i) == '#') {
                i--;
                nBackspaces++;
            }
            while (i >= 0 && nBackspaces > 0) {
                nBackspaces += string.charAt(i--) == '#' ? 1 : -1;
            }
        }
        return i;
    }
}
