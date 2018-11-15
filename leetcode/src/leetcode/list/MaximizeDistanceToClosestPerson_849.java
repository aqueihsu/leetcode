package leetcode.list;

public class MaximizeDistanceToClosestPerson_849 {
    public int maxDistToClosest(int[] seats) {
        int i = -1, j = 0;
        int maxDistance = -1;
        while (j < seats.length) {
            if (seats[j] == 0) {
                j++;
            } else {
                int distance = i == -1 ? j : ((j - i) / 2);
                maxDistance = Math.max(maxDistance, distance);
                i = j++;
            }
        }
        // Take care of the last segment
        return Math.max(j - i - 1, maxDistance);
    }
}
