package leetcode.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EvaluateDivision_399 {
    public double[] calcEquation(String[][] equations, double[] values, String[][] queries) {
        Map<String, List<Integer>> opToIEquationsMap = new HashMap<>();
        
        for (int i = 0; i < equations.length; i++) {
            if (!opToIEquationsMap.containsKey(equations[i][0])) {
                opToIEquationsMap.put(equations[i][0], new ArrayList<>());
            }
            opToIEquationsMap.get(equations[i][0]).add(i);
            
            if (!opToIEquationsMap.containsKey(equations[i][1])) {
                opToIEquationsMap.put(equations[i][1], new ArrayList<>());
            }
            opToIEquationsMap.get(equations[i][1]).add(-i - 1);
        }
        
        double[] results = new double[queries.length];
        for (int i = 0; i < queries.length; i++) {
            List<Integer> iEquations = opToIEquationsMap.get(queries[i][0]);
            if (iEquations == null || iEquations.isEmpty()) {
                results[i] = -1;
                continue;
            }
            if (queries[i][0].equals(queries[i][1])) {
                results[i] = 1;
                continue;
            }
            double result = -1;
            for (int iEquation : iEquations) {
                result = dfs(equations, values, queries[i][1], opToIEquationsMap, iEquation);
                if (result != -1) {
                    break;
                }
            }
            results[i] = result;
        }
        return results;
    }
    
    private double dfs(String[][] equations, double[] values, String targetDenominator, Map<String, List<Integer>> opToIEquationsMap, int iCurrEquation) {
        String numerator;
        String denominator;
        double value;
        if (iCurrEquation >= 0) {
            numerator = equations[iCurrEquation][0];
            denominator = equations[iCurrEquation][1];
            value = values[iCurrEquation];
        } else {
            iCurrEquation = -iCurrEquation - 1;
            numerator = equations[iCurrEquation][1];
            denominator = equations[iCurrEquation][0];
            value = 1d / values[iCurrEquation];
        }
        if (targetDenominator.equals(denominator)) {
            return value;
        }
        
        List<Integer> iNextEquations = opToIEquationsMap.get(denominator);
        if (iNextEquations == null || iNextEquations.isEmpty()) {
            return -1;
        }
        for (int iNextEquation : iNextEquations) {
            // Otherwise you get infinite loop!
            if (iNextEquation == -iCurrEquation - 1) {
                continue;
            }
            double result = dfs(equations, values, targetDenominator, opToIEquationsMap, iNextEquation);
            if (Double.compare(result, -1) > 0) {
               return result * value;
            }
        }
        return -1;
    }
}
