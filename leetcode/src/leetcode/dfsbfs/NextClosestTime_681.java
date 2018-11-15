package leetcode.dfsbfs;

public class NextClosestTime_681 {
    private static final int[] TO_MINUTE = {600, 60, 10, 1};
    
    public String nextClosestTime(String time) {
        if (time.charAt(0) == time.charAt(1) && time.charAt(1) == time.charAt(3)
                && time.charAt(3) == time.charAt(4)) {
            return time;
        }
        String digits = time.replace(":","");
        char[] closestTime = new char[] {'9', '9', '9', '9'};
        helper(digits.toCharArray(), closestTime, new char[4], 0);
        return new String(new char[] {closestTime[0], closestTime[1], ':', closestTime[2], closestTime[3]});
    }
    
    private void helper(char[] time, char[] closestTime, char[] curTime, int curPos) {
        if (curPos == 4) {
            int minDiff = closestTime[0] == '9' ? Integer.MAX_VALUE : diffInMinutes(closestTime, time);
            int curDiff = diffInMinutes(curTime, time);
            if (curDiff < minDiff) {
                copyTime(closestTime, curTime);
            }
            return;
        }
        
        for (int i = 0; i < 4; i++) {
            curTime[curPos] = time[i];
            if (isValid(curTime, curPos)) {
                helper(time, closestTime, curTime, curPos + 1);
            }
        }
    }
    
    private boolean isValid(char[] time, int curPos) {
        if (curPos >= 0 && time[0] > '2') {
            return false;
        }
        if (curPos >= 1 &&
                ((time[0] == '2' && time[1] > '4') || time [0] > '2')) {
            return false;
        }
        if (curPos >= 2 && time[2] > '5') {
            return false;
        }
        return true;
    }
    
    private int diffInMinutes(char[] time1, char[] time2) {
        int diff = 0;
        for (int i = 0; i < 4; i++) {
            diff += TO_MINUTE[i] * (time1[i] - time2[i]);
        }
        return diff == 0 ? Integer.MAX_VALUE : diff < 0 ? diff + 24 * 60 : diff;
    }
    
    private void copyTime(char[] target, char[] source) {
        for (int i = 0; i < source.length; i++) {
            target[i] = source[i];
        }
    }
}
