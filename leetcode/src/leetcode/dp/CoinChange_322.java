package leetcode.dp;

public class CoinChange_322 {
    public int coinChange(int[] coins, int amount) {
        // int[] counts = new int[amount];
        // return topDown(coins, amount, counts);
        return bottomUp(coins, amount);
    }
    private int bottomUp(int[] coins, int total) {
        if (total == 0) {
            return 0;
        }
        int[] counts = new int[total];
        for (int amount = 1; amount <= total; amount++) {
            int minCount = Integer.MAX_VALUE;
            for (int coin : coins) {
                int count = -1;
                int remaining = amount - coin;
                if (remaining == 0) {
                    count = 0;
                } else if (remaining > 0) {
                    count = counts[remaining - 1];
                }
                if (count != -1) {
                    minCount = Math.min(minCount, count + 1);
                }
            }
            counts[amount - 1] = minCount == Integer.MAX_VALUE ? -1 : minCount;
        }
        return counts[total - 1];
    }
    
    private int topDown(int[] coins, int amount, int[] counts) {
        if (amount == 0) {
            return 0;
        }
        if (amount < 0) {
            return -1;
        }
        if (counts[amount - 1] != 0) {
            return counts[amount - 1];
        }
        
        int minCount = Integer.MAX_VALUE;
        for (int coin : coins) {
            int remaining = amount - coin;
            int count = topDown(coins, remaining, counts);
            if (count != -1) {
                minCount = Math.min(minCount, count + 1);
            }
        }
        counts[amount - 1] = minCount == Integer.MAX_VALUE ? -1 : minCount;
        return counts[amount - 1];
    }
}
