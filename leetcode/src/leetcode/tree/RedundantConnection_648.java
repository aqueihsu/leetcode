package leetcode.tree;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RedundantConnection_648 {
    class DisjointSetUnion {
        private int[] parents;
        private int[] ranks;
        public DisjointSetUnion(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
            ranks = new int[n];
        }
        
        public boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if (px == py) {
                return false;
            }
            if (ranks[px] < ranks[py]) {
                parents[px] = py;
            } else if (ranks[px] > ranks[py]) {
                parents[py] = px;
            } else {
                parents[py] = px;
                ranks[px]++;
            }
            return true;
        }
        
        private int find(int x) {
            if (parents[x] != x) {
                parents[x] = find(parents[x]);
            }
            return parents[x];
        }
    }
    
    public int[] findRedundantConnection(int[][] edges) {
        DisjointSetUnion dsu = new DisjointSetUnion(1001);
        for (int[] edge : edges) {
            if (!dsu.union(edge[0], edge[1])) {
                return edge;
            }
        }
        return null;
    }
    
    public int[] findRedundantConnection1(int[][] edges) {
        Set<Set<Integer>> sets = new HashSet<>();
        for (int[] edge : edges) {
            if (!unionFind1(sets, edge)) {
                return edge;
            }
        }
        return null;
    }
    
    private boolean unionFind1(Set<Set<Integer>> sets, int[] edge) {
        int i = edge[0], j = edge[1];
        Set<Integer> setI = null, setJ = null;
        Iterator<Set<Integer>> itor = sets.iterator();
        while (itor.hasNext()) {
            Set<Integer> set = itor.next();
            boolean removeSet = false;
            if (removeSet = set.contains(i)) {
                setI = set;
            }
            if (set.contains(j)) {
                removeSet = true;
                setJ = set;
            }
            if (removeSet) {
                itor.remove();
            }
        }
        if (setI != null) {
            if (setJ != null) {
                if (setI == setJ) {
                    return false;
                }
                setI.addAll(setJ);
            } else {
                setI.add(j);
            }
            sets.add(setI);
        } else {
            // setI is null
            if (setJ != null) {
                setJ.add(i);
            } else {
                setJ = new HashSet<>();
                setJ.add(i);
                setJ.add(j);
            }
            sets.add(setJ);
        }
        return true;
    }
}
