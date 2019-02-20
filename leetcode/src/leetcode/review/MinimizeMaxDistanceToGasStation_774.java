package leetcode.review;

public class MinimizeMaxDistanceToGasStation_774 {
    public double minmaxGasDist(int[] stations, int K) {
        // f(max_dist) = count, max_dist: max dist, count: number of gas station to achieve that distance
        // [1,2,3,4,5,6,7,8,9,10]
        int n = stations.length;
        double l = 0;
        double r = stations[n - 1] - stations[0];
        
        while (r - l > 1e-6) {
            double maxDist = (l + r) / 2;
            double count = 0;
            for (int i = 1; i < n && count <= K; i++) {
                // Because of the delta (1e-6) here, you don't have to check the equal condition
                count += (int)((stations[i] - stations[i - 1]) / maxDist);
            }
            if (count <= K) {
                // Find the left most
                r = maxDist;
            } else {
                l = maxDist;
            }
            System.out.println("dist: " + l);
            System.out.println("count: " + count);
        }
        return l;
    }
    
    public double minmaxGasDist1(int[] stations, int K) {
        // f(max_dist) = count, max_dist: max dist, count: number of gas station to achieve that distance
        // [1,2,3,4,5,6,7,8,9,10]
        double l = 0;
        double r = 1e8;
        
        while (r - l > 1e-6) {
            double maxDist = (l + r) / 2;
            if (isPossible(maxDist, stations, K)) {
                // Find the left most
                r = maxDist;
            } else {
                l = maxDist;
            }
        }
        return l;
    }
    
    private boolean isPossible(double maxDist, int[] stations, int K) {
        int n = stations.length;
        int count = 0;
        for (int i = 1; i < n; i++) {
            double pairDist = stations[i] - stations[i - 1];
            if (Double.compare(maxDist, pairDist) < 0) {
                count += (int)(pairDist / maxDist);
            }
        }
        return count <= K;
    }
}
