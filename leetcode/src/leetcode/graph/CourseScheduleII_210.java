package leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseScheduleII_210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 1) {
            return new int[] {0};
        }
        
        List<Set<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new HashSet<>());
        }
        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]);
        }
        
        boolean[] discovered = new boolean[numCourses], completed = new boolean[numCourses];
        int[] sortedCourses = new int[numCourses];
        int[] i = new int[] {numCourses - 1};
        for (int course = 0; course < numCourses; course++) {
            if (!topologySort(adj, discovered, completed, course, sortedCourses, i)) {
                return new int[] {};
            }
        }
        return sortedCourses;
    }
    
    private boolean topologySort(List<Set<Integer>> adj, boolean[] discovered, boolean[] completed, int course, int[] sortedCourses, int[] i) {
        if (completed[course]) {
            return true;
        }
        
        discovered[course] = true;
        Set<Integer> nextCourses = adj.get(course);
        if (nextCourses.size() != 0) {
            for (int nextCourse : nextCourses) {
                if (!completed[nextCourse] && discovered[nextCourse] ||
                        !topologySort(adj, discovered, completed, nextCourse, sortedCourses, i)) {
                    return false;
                }
            }
        }
        completed[course] = true;
        sortedCourses[i[0]--] = course;
        return true;
    }
}
