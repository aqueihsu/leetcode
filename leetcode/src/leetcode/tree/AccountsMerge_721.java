package leetcode.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountsMerge_721 {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, String> emailToUser = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        for (List<String> account : accounts) {
            emailToUser.put(account.get(1) /* first email */, account.get(0) /* user */);
            for (String email : account.subList(1, account.size())) {
                parents.put(email, email);
            }
        }
        for (List<String> account : accounts) {
            String parent = find(parents, account.get(1));
            for (String email : account.subList(1, account.size())) {
                // Don't care about the ranking, slightly slower but less bug prune
                // Note: move the parent of the email under another parent!!
                parents.put(find(parents, email), parent);
            }
        }
        Map<String, List<String>> mergedAccounts = new HashMap<>();
        for (String email : parents.keySet()) {
            String parent = find(parents, email);
            if (!mergedAccounts.containsKey(parent)) {
                mergedAccounts.put(parent, new ArrayList<>());
            }
            mergedAccounts.get(parent).add(email);
        }
        for (String email : mergedAccounts.keySet()) {
            Collections.sort(mergedAccounts.get(email));
            mergedAccounts.get(email).add(0, emailToUser.get(email));
        }
        return new ArrayList<>(mergedAccounts.values());
    }
    
    private String find(Map<String, String> parents, String email) {
        if (!parents.get(email).equals(email)) {
            parents.put(email, find(parents, parents.get(email)));
        }
        return parents.get(email);
    }
    
    public List<List<String>> accountsMerge1(List<List<String>> accounts) {
        Map<String, String> emailToUser = new HashMap<>();
        Map<String, String> parents = new HashMap<>();
        Map<String, Integer> ranks = new HashMap<>();
        for (List<String> account : accounts) {
            emailToUser.put(account.get(1), account.get(0));
            addEmails(parents, ranks, account.subList(1, account.size()));
        }
        Map<String, List<String>> mergedAccounts = new HashMap<>();
        for (String email : parents.keySet()) {
            String parent = find(parents, email);
            if (!mergedAccounts.containsKey(parent)) {
                mergedAccounts.put(parent, new ArrayList<>());
            }
            mergedAccounts.get(parent).add(email);
        }
        for (String email : mergedAccounts.keySet()) {
            Collections.sort(mergedAccounts.get(email));
            mergedAccounts.get(email).add(0, emailToUser.get(email));
        }
        return new ArrayList<>(mergedAccounts.values());
    }
    
    private void addEmails(Map<String, String> parents, Map<String, Integer> ranks, List<String> emails) {
        String maxParent = null;
        int maxRank = -1;
        for (String email : emails) {
            if (parents.containsKey(email)) {
                String parent = find(parents, email);
                if (ranks.get(parent) > maxRank) {
                    maxRank = ranks.get(parent);
                    maxParent = parent;
                }
            }
        }
        
        if (maxParent != null) {
            for (String email : emails) {
                if (!parents.containsKey(email)) {
                    parents.put(email, maxParent);
                } else {
                    String parent = find(parents, email);
                    parents.put(parent, maxParent);
                    if (ranks.get(parent) == maxRank) {
                        ranks.put(maxParent, ranks.get(maxParent) + 1);
                    }
                }
            }
        } else {
            String firstEmail = emails.get(0);
            for (String email : emails) {
                ranks.put(email, 0);
                parents.put(email, firstEmail);
            }
            ranks.put(firstEmail, 1);
        }
    }
}
