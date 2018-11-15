package leetcode.string;

public class BullsAndCows_299 {
    public String getHint(String secret, String guess) {
        int bull = 0, cow = 0;
        int[] counters = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            if (s == g) {
                bull++;
            } else {
                if (counters[s] > 0) {
                    cow++;
                }
                if (counters[g] < 0) {
                    cow++;
                }
                
                counters[s]--;
                counters[g]++;
            }
        }
        return String.format("%dA%dB", bull, cow);
    }
}
