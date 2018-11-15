package leetcode.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CourseSchedule_207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses == 1 || prerequisites.length <= 1) {
            return true;
        }
        
        List<Set<Integer>> adj = new ArrayList<>(numCourses);
        for (int i = 0; i < numCourses; i++) {
            adj.add(new HashSet<>());
        }
        for (int[] prereq : prerequisites) {
            adj.get(prereq[1]).add(prereq[0]);
        }
        
        // Visit every node
        boolean[] discovered = new boolean[numCourses], completed = new boolean[numCourses];
        for (int course = 0; course < numCourses; course++) {
            if (!completed[course] && hasCycle(adj, discovered, completed, course)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean hasCycle(List<Set<Integer>> adj, boolean[] discovered, boolean[] completed, int course) {
        if (completed[course]) {
            return false;
        }
        
        discovered[course] = true;
        if (adj.get(course).size() > 0) {
            for (int nextCourse : adj.get(course)) {
                if (!completed[nextCourse] && discovered[nextCourse]
                        || hasCycle(adj, discovered, completed, nextCourse)) {
                    // cycle detected
                    return true;
                }
            }
        }
        completed[course] = true;
        return false;
    }
}
