package greedy;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class QueueReconstructionByHeight_406 {
    public int[][] reconstructQueue(int[][] peopleArray) {
        List<int[]> people = Arrays.asList(peopleArray);
        Collections.sort(people, (p1, p2) -> (p1[0] == p2[0] ? p1[1] - p2[1] : p2[0] - p1[0]));
        
        List<int[]> results = new LinkedList<>();
        for (int[] person : people) {
            results.add(person[1], person);
        }
        int[][] resultArray = new int[results.size()][2];
        return results.toArray(resultArray);
    }
}
