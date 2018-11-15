package leetcode.string;

public class StrobogrammaticNumberIII_248 {
    private static final char[] SINGLES = new char[] {'0', '1', '8'};
    private static final char[][] PAIRS = new char[][] {{'0', '0'}, {'1', '1'}, {'8', '8'}, {'6', '9'}, {'9', '6'}};
    
    public int strobogrammaticInRange(String low, String high) {
        int[] count = new int[] {0};
        for (int len = low.length(); len <= high.length(); len++) {
            helper(count, new char[len], 0, len - 1, low, high);
        }
        return count[0];
    }
    
    private void helper(int[] count, char[] result, int i, int j, String low, String high) {
        if (i > j) {
            String strResult = String.valueOf(result);
            if (result.length == low.length() && low.compareTo(strResult) > 0
                    || result.length == high.length() && high.compareTo(strResult) < 0) {
                return;
            }
            count[0]++;
        } else if (i == j) {
            for (char c : SINGLES) {
                result[i] = c;
                helper(count, result, i + 1, j - 1, low, high);
            }
        } else {
            // i < j
            for (char[] pair : PAIRS) {
                if (i == 0 && pair[0] == '0') {
                    continue;
                }
                result[i] = pair[0];
                result[j] = pair[1];
                helper(count, result, i + 1, j - 1, low, high);
            }
        }
    }
}
