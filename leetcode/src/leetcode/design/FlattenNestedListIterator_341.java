package leetcode.design;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class FlattenNestedListIterator_341 {
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
    
    public class NestedIterator implements Iterator<Integer> {
        private final List<NestedInteger> nestedList;
        private int index;
        
        private Integer nextValue = null;
        private NestedIterator itor = null;

        public NestedIterator(List<NestedInteger> nestedList) {
            this.nestedList = nestedList;
            index = -1;
            retrieveNextValue();
        }

        @Override
        public Integer next() {
            Integer value = nextValue;
            retrieveNextValue();
            return value;
        }

        @Override
        public boolean hasNext() {
            return nextValue != null;
        }
        
        private void retrieveNextValue() {
            if (itor != null && itor.hasNext()) {
                nextValue = itor.next();
                return;
            }
            nextValue = null;
            while (++index < nestedList.size()) {
                if (nestedList.get(index).isInteger()) {
                    nextValue = nestedList.get(index).getInteger();
                    break;
                } else {
                    List<NestedInteger> nextNestedList = nestedList.get(index).getList();
                    if (!nextNestedList.isEmpty() && (itor = new NestedIterator(nextNestedList)).hasNext()) {
                        nextValue = itor.next();
                        break;
                    }
                }
            }
        }
    }
}
