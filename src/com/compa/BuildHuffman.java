package com.compa;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuildHuffman {

    int alphabetSize = 0;
    PriorityQueue<HuffmanNode> Q;

    BuildHuffman(char[] alphabet, int[] freqs) {
        this.BuildQueue(alphabet, freqs);
    }

    public void BuildQueue(char[] alphabet, int[] freqs) {
        HuffmanComparator comp = new HuffmanComparator();
        this.Q = new PriorityQueue(comp);
        for (int i=0; i<alphabet.length; i++) {
            //make a new node containing letter and corresponding frequency
            HuffmanNode node = new HuffmanNode(alphabet[i], freqs[i]);
            //add node to priority queue
            this.Q.add(node);
        }
    }
//    public String ShowQueue(PriorityQueue Q) {
//        String result = "";
//
//    }
}
