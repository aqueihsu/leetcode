package leetcode;

public class DailyTemperature_739 {
    // O(n) filling backwards
    // if (t[i-1] < t[i]) ans[i-1] = 1;
    // else compare (t[i-1] + ans[i]) until find one that is warmer
    
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return null;
        }
        
        int size = temperatures.length;
        int[] daysToWarmerTemp = new int[size];
        for (int i = size - 2; i >= 0; i--) {
            int j = i + 1;
            /*
             * The key to prove O(n) runtime is that every index in the array will appear as
             * j such that temperatures[j] <= temperatures[i] at most once in the inner loop,
             * because for any k < i, res[i] + i will lead you to some index larger than j.
             */
            while (j < size && temperatures[j] <= temperatures[i]) {
                if (daysToWarmerTemp[j] > 0) {
                    j += daysToWarmerTemp[j];
                } else {
                    j = size;
                }
            }
            
            if (j < size) {
                daysToWarmerTemp[i] = j - i;
            }
        }
        return daysToWarmerTemp;
    }
}
