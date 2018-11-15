package leetcode.list;

import java.util.List;

public class NestedListWeightSum_339 {
    public class NestedInteger {
        private int value;
        private List<NestedInteger> values;
        
        public NestedInteger(boolean isInteger, int value, List<NestedInteger> values) {
            if (isInteger) {
                this.value = value;
            } else {
                this.values = values;
            }
        }
        
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        public boolean isInteger() {
            return values == null;
        }

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        public Integer getInteger() {
            return value;
        }

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return null if this NestedInteger holds a single integer
        public List<NestedInteger> getList() {
            return values;
        }
    }
    
    public int depthSum(List<NestedInteger> nestedList) {
        return depthSum(1, nestedList);
    }
    
    public int depthSum(int depth, List<NestedInteger> nestedList) {
        if (nestedList.isEmpty()) {
            return 0;
        }
        
        int sum = 0;
        for (NestedInteger num : nestedList) {
            if (num.isInteger()) {
                sum += depth * num.getInteger();
            } else {
                sum += depthSum(depth + 1, num.getList());
            }
        }
        return sum;
    }
}
