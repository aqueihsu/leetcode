package leetcode.string;

public class CountAndSay_38 {
    public String countAndSay(int n) {
        return helper("1", n - 1);
    }
    
    private String helper(String s, int n) {
        if (n == 0) {
            return s;
        }
        StringBuilder builder = new StringBuilder();
        int i = 0;
        while (i < s.length()) {
            int count = 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                i++; count++;
            }
            builder.append(count).append(s.charAt(i));
            i++;
        }
        return helper(builder.toString(), n - 1);
    }
}
