package leetcode.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DesignSearchAutocompleteSystem_642 {
    class AutocompleteSystem {
        private HeatedTriTree triTree;
        
        public AutocompleteSystem(String[] sentences, int[] frequencies) {
            triTree = new HeatedTriTree(sentences, frequencies);
        }
        
        public List<String> input(char c) {
            if (c == '#') {
                triTree.completeLookup();;
                return new ArrayList<>();
            } else {
                return triTree.lookup(c);
            }
        }
    }
    
    class HeatedTriTree {
        Node root = new Node();
        
        private StringBuilder builder = new StringBuilder();
        Node curNode = root;
        
        HeatedTriTree(String[] sentences, int[] frequencies) {
            for (int i = 0; i < sentences.length; i++) {
                Node node = root;
                for (char c : sentences[i].toCharArray()) {
                    if (!node.children.containsKey(c)) {
                        node.children.put(c, new Node());
                    }
                    node = node.children.get(c);
                }
                node.sentenceToFrequency.put(sentences[i], frequencies[i]);
            }
        }
        
        List<String> lookup(Character c) {
            builder.append(c);
            if (!curNode.children.containsKey(c)) {
                curNode.children.put(c, new Node());
            }
            curNode = curNode.children.get(c);
            
            // Collect all the descendants
            PriorityQueue<StringFrequencyPair> stringsByFreqQueue = new PriorityQueue<>(
                    (s1, s2) -> {
                        if (s2.frequency == s1.frequency) {
                            return s1.sentence.compareTo(s2.sentence);
                        }
                        return s2.frequency - s1.frequency;
                    });
            Deque<Node> queue = new ArrayDeque<>();
            queue.addLast(curNode);
            while (!queue.isEmpty()) {
                Node node = queue.pollFirst();
                for (Map.Entry<String, Integer> entry : node.sentenceToFrequency.entrySet()) {
                    stringsByFreqQueue.add(new StringFrequencyPair(entry.getKey(), entry.getValue()));
                }
                queue.addAll(node.children.values());
            }
            List<String> results = new ArrayList<>();
            for (int i = 0; i < 3 && !stringsByFreqQueue.isEmpty(); i++) {
                results.add(stringsByFreqQueue.poll().sentence);
            }
            return results;
        }
        
        void completeLookup() {
            if (builder.length() == 0) {
                return;
            }
            String sentence = builder.toString();
            int frequency = curNode.sentenceToFrequency.getOrDefault(sentence, 0);
            curNode.sentenceToFrequency.put(sentence, frequency + 1);
            
            builder = new StringBuilder();
            curNode = root;
        }
    }
    
    class Node {
        Map<Character, Node> children = new HashMap<>();
        Map<String, Integer> sentenceToFrequency = new HashMap<>();
    }
    
    class StringFrequencyPair {
        String sentence;
        int frequency;
        
        StringFrequencyPair(String sentence, int frequency) {
            this.sentence = sentence;
            this.frequency = frequency;
        }
    }
}
