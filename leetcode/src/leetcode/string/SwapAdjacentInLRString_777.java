package leetcode.string;

public class SwapAdjacentInLRString_777 {
    public boolean canTransform(String start, String end) {
        int i = 0, j = 0;
        while (i < start.length() && j < end.length()) {
            while (i < start.length() && start.charAt(i) == 'X') {
                i++;
            }
            while (j < end.length() && end.charAt(j) == 'X') {
                j++;
            }
            if (i == start.length() || j == start.length()) {
                return i == j;
            }
            if (start.charAt(i) != end.charAt(j)) {
                return false;
            }
            if (end.charAt(j) == 'L' && j > i) {
                return false;
            }
            if (end.charAt(j) == 'R' && j < i) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }
}
