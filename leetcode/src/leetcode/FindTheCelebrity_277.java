package leetcode;

public class FindTheCelebrity_277 {
    boolean knows(int a, int b) {
        // Stub
        return true;
    }
    
    public int findCelebrity(int n) {
        int candidate = 0;
        for (int i = 1; i < n; i++) {
            int a = i;
            boolean candidateKnowsA = knows(candidate, a);
            boolean aKnowsCandidate = knows(a, candidate);
            
            if (candidateKnowsA && !aKnowsCandidate) {
                candidate = a;
            } else if (!candidateKnowsA && aKnowsCandidate) {
                // Do nothing
            } else {
                // candidateKnowsA && aKnowsCandidate or
                // !candidateKnowsA && !aKnowsCandidate
                // Drop candidate
                if ((candidate = ++i) >= n - 1) {
                    break;
                }
            }
        }
        if (candidate >= n) {
            return -1;
        }
        
        for (int i = 0; i < candidate; i++) {
            int a = i;
            boolean candidateKnowsA = knows(candidate, a);
            boolean aKnowsCandidate = knows(a, candidate);
            
            if (candidateKnowsA || !aKnowsCandidate) {
                return -1;
            }
        }
        return candidate;
    }
}