package leetcode;

import java.util.Arrays;

public class BestTimeToBuyAndSellStock {
    public int maxProfitOne_121_sol1(int[] prices) {
        int maxProfit = 0;
        int minPrice = Integer.MAX_VALUE;
        for (int price : prices) {
            minPrice = Math.min(minPrice, price);
            maxProfit = Math.max(maxProfit, price - minPrice);
        }
        return maxProfit;
    }
    
    public int maxProfitOne_121_sol2(int[] prices) {
        int maxProfit = 0;
        int maxSubarraySum = 0;
        for (int i = 1; i < prices.length; i++) {
            maxSubarraySum += prices[i] - prices[i - 1];
            if (maxSubarraySum < 0) {
                maxSubarraySum = 0;
            }
            maxProfit = Math.max(maxProfit, maxSubarraySum);
        }
        return maxProfit;
    }
    
    public int maxProfitTwo_122(int[] prices) {
        int maxProfit = 0;
        for (int i = 1; i < prices.length; i++) {
            int delta = prices[i] - prices[i - 1];
            if (delta > 0) {
                maxProfit += delta;
            }
        }
        return maxProfit;
    }
    
    public int maxProfitThree_123(int[] prices) {
        int buy1 = Integer.MIN_VALUE, buy2 = Integer.MIN_VALUE;
        int sell1 = 0, sell2 = 0;
        
        for (int price : prices) {
            buy1 = Math.max(buy1, -price);           // Money remaining after buying first stock
            sell1 = Math.max(sell1, price + buy1);   // Money remaining after selling first stock
            buy2 = Math.max(buy2, sell1 - price);    // Money remaining after buying second stock
            sell2 = Math.max(sell2, price + buy2);   // Money remaining after selling second stock
        }
        return sell2;
    }
    
    public int maxProfitFour_188(int k, int[] prices) {
        if (k <= 0) {
            return 0;
        }
        
        // NOTE: k can be much larger than prices.len!!
        if (k > prices.length) {
            int maxProfit = 0;
            for (int i = 1; i < prices.length; i++) {
                int delta = prices[i] - prices[i - 1];
                if (delta > 0) {
                    maxProfit += delta;
                }
            }
            return maxProfit;
        }
        
        int[] buy = new int[k];
        int[] sell = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        
        for (int price : prices) {
            buy[0] = Math.max(buy[0], -price);
            sell[0] = Math.max(sell[0], buy[0] + price);
            for (int kk = 1; kk < k; kk++) {
                buy[kk] = Math.max(buy[kk], sell[kk - 1] - price);
                sell[kk] = Math.max(sell[kk], buy[kk] + price);
            }
        }
        return sell[k - 1];
    }
}
