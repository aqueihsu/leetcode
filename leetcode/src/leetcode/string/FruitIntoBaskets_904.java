package leetcode.string;

public class FruitIntoBaskets_904 {
    public int totalFruit(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }
        int fruit1 = -1;
        int fruit2 = tree[0];
        
        int posFruit2 = 0;
        int len = 1, maxLen = 1;
        for (int i = 1; i < tree.length; i++) {
            if (tree[i - 1] != tree[i]) {
                if (tree[i] != fruit1) {
                    len = i - posFruit2;
                }
                fruit1 = fruit2;
                fruit2 = tree[i];
                posFruit2 = i;
            }
            len++;
            maxLen = Math.max(maxLen, len);
        }
        return maxLen;
    }
}
