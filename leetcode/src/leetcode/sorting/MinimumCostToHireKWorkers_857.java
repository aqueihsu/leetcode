package leetcode.sorting;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers_857 {
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        Worker[] workers = new Worker[quality.length];
        for (int i = 0; i < quality.length; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        Arrays.sort(workers, (w1, w2) -> (w1.wage * w2.quality - w2.wage * w1.quality));
        
        double minCost = Integer.MAX_VALUE;
        double sumQuality = 0;
        
        // Max heap
        PriorityQueue<Double> qualities = new PriorityQueue<>((d1, d2) -> Double.compare(d2, d1));
        for (Worker worker : workers) {
            qualities.offer((double) worker.quality);
            sumQuality += worker.quality;
            if (qualities.size() > K) {
                // Remove the largest
                sumQuality -= qualities.poll();
            }
            if (qualities.size() == K) {
                minCost = Math.min(minCost, sumQuality * worker.getRatio());
            }
        }
        return minCost;
    }
    
    private class Worker {
        private int quality;
        private int wage;
        
        private Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
        }
        
        private double getRatio() {
            return (double) wage / quality;
        }
    }
}
