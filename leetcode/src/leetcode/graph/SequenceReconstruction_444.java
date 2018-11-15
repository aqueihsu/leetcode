package leetcode.graph;

import java.util.List;

public class SequenceReconstruction_444 {
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if (org.length == 0 || seqs.size() == 0) {
            return false;
        }
        int[] pos = new int[org.length];
        for (int i = 0; i < org.length; i++) {
            pos[org[i] - 1] = i;
        }
        boolean allEmpty = true;
        int remainingEdges = org.length - 1;
        boolean[] adj = new boolean[org.length];
        for (List<Integer> seq : seqs) {
            if (seq.size() > 0 && (seq.get(0) > org.length || seq.get(0) < 0)) {
                return false;
            }
            if (seq.size() > 0) {
                allEmpty = false;
            }
            for (int i = 1; i < seq.size(); i++) {
                int x = seq.get(i - 1) - 1;
                int y = seq.get(i) - 1;
                if (y >= org.length || y < 0) {
                    return false;
                }
                if (pos[x] >= pos[y]) {
                    return false;
                }
                if (!adj[pos[x]] && pos[x] + 1 == pos[y]) {
                    adj[pos[x]] = true;
                    remainingEdges--;
                }
            }
        }
        return !allEmpty && remainingEdges == 0;
    }
}
