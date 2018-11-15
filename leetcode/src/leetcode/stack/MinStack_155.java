package leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class MinStack_155 {
    class MinStack {
        private long min;
        private Stack<Long> values = new Stack<>();
        
        /** initialize your data structure here. */
        public MinStack() {
        }
        
        public void push(int x) {
            if (values.isEmpty()) {
                values.push(0L);
                min = x;
            } else {
                values.push(x - min);
                if (x < min) {
                    min = x;
                }
            }
        }
        
        public void pop() {
            Long x = values.pop();
            if (x < 0) {
                min -= x;
            }
        }
        
        public int top() {
            Long x = values.peek();
            if (x < 0) {
                return (int) min;
            } else {
                return (int) (min + x);
            }
        }
        
        public int getMin() {
            return (int) min;
        }
    };
    
    class MinStack1 {
        Deque<Integer> mins = new ArrayDeque<>();
        Deque<Integer> values = new ArrayDeque<>();
        
        /** initialize your data structure here. */
        public MinStack1() {
        }
        
        public void push(int x) {
            values.addLast(x);
            if (mins.isEmpty() || x <= mins.peekLast()) {
                mins.addLast(x);
            }
        }
        
        public void pop() {
            int x = values.pollLast();
            if (!mins.isEmpty() && mins.peekLast() == x) {
                mins.pollLast();
            }
        }
        
        public int top() {
            return values.peekLast();
        }
        
        public int getMin() {
            return mins.peekLast();
        }
    };
}
