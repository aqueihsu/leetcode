package leetcode;

// Boundary condiitions!
public class LicenseKeyFormatting_482 {
    public String licenseKeyFormatting(String S, int K) {
        if (S == null || S.length() == 0) {
            return null;
        }
        
        char[] result = new char[2 * S.length()];
        int read = S.length() - 1, write = 2 * S.length() - 1;
        
        while (read >= 0) {
            int nChars = 0;
            while (nChars < K && read >= 0) {
                if (S.charAt(read) == '-') {
                    read--;
                } else {
                    result[write--] = toUpperCase(S.charAt(read--));
                    nChars++;
                }
            }
            if (nChars > 0) {
                result[write--] = '-';
            }
        }
        return write < 2 * S.length() - 1 ? new String(result, write + 2, 2 * S.length() - write - 2) : new String(new char[] {});
    }
    
    private char toUpperCase(char c) {
        return c <= 'z' && c >= 'a' ? (char)(c - 'a' + 'A') : c;
    }
}
