package com.compa;

import java.util.Comparator;
import java.util.PriorityQueue;

public class BuildHuffman {

    int alphabetSize = 0;
    PriorityQueue<HuffmanNode> Q;
    HuffmanNode head;

    BuildHuffman(char[] alphabet, int[] freqs) {
        this.BuildQueue(alphabet, freqs);
        for (int i=0; i<(alphabet.length-1); i++) {
            HuffmanNode node = new HuffmanNode();
            node.left = Q.poll();
            node.right = Q.poll();
            node.freq = node.left.freq + node.right.freq;
            Q.add(node);
        }
        head = Q.poll();
    }

    public void PrintHuffman(HuffmanNode head) {
        if (head.left == null & head.right ==null) {
            System.out.print(head.letter);
            System.out.print("^");
            return;
        }
        if (head.left != null) {
            System.out.print("v");
            PrintHuffman(head.left);
        }
        if (head.right != null) {
            System.out.print("v");
            PrintHuffman(head.right);
        }
        System.out.println("\n^");
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
