package leetcode.list;

public class Candy_135 {
    public int candy(int[] ratings) {
        int nCandies = 1;
        int prevNCandies = 1;
        
        int i = 1, j = 1;
        while (i < ratings.length) {
            if (i < j && (j == ratings.length || ratings[j - 1] <= ratings[j])) {
                int adjustment = Math.max(prevNCandies, j - i + 1) - prevNCandies;
                nCandies += adjustment;
                while (i < j) {
                    nCandies += (j - i);
                    i++;
                }
                prevNCandies = 1;
            }
            if (j < ratings.length ) {
                if (ratings[j - 1] < ratings[j]) {
                    nCandies += ++prevNCandies;
                    i++;
                } else if (ratings[j - 1] == ratings[j]) {
                    nCandies += (prevNCandies = 1);
                    i++;
                }
            }
            j++;
        }
        return nCandies;
    }
}
