package com.compa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

class Huffman {

    int alphabetSize = 0;
    PriorityQueue<HuffmanNode> Q;
    HuffmanNode head;
    HashMap lookupTable;

    Huffman(char[] alphabet, int[] freqs) {
        this.BuildQueue(alphabet, freqs);
        for (int i=0; i<(alphabet.length-1); i++) {
            HuffmanNode node = new HuffmanNode();
            node.left = Q.poll();
            node.right = Q.poll();
            node.freq = node.left.freq + node.right.freq;
            Q.add(node);
        }
        this.head = Q.poll();
        CreateLookupTable(this.head);
    }
// traverses Huffman tree to create codes for every symbol in alphabet
    public void CreateLookupTable(HuffmanNode head) {
        this.lookupTable = new HashMap();
        String code = "";
        TraverseHuffman(head, lookupTable, code);
    }

//    Traverses Huffman tree and adds every leaf to code lookup table
    public void TraverseHuffman(HuffmanNode head, HashMap lookupTable, String code) {
        if (head.left == null & head.right ==null) {
            lookupTable.put(head.letter, code);
            return;
        }
        if (head.left != null) {
            String tempCode = code;
            code += "0";
            TraverseHuffman(head.left, lookupTable, code);
            code = tempCode;
        }
        if (head.right != null) {
            String tempCode = code;
            code += "1";
            TraverseHuffman(head.right, lookupTable, code);
            code = tempCode;
        }
    }

    //takes in string of symbols and encodes into string of binary bits
//    public String Encode(String input) {
//
//
//    }


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
