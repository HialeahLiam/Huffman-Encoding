package com.compa;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Huffman {

    int alphabetSize = 0;
    PriorityQueue<HuffmanNode> Q;
    HuffmanNode head;
    Map<Character, String> lookupTable;

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
// traverses Huffman tree to create lookup table with binary codes for every symbol in alphabet
    public void CreateLookupTable(HuffmanNode head) {
        this.lookupTable = new HashMap();
        String code = "";
        TraverseHuffman(head, this.lookupTable, code);
    }

//    Traverses Huffman tree and adds every leaf to code lookup table
    public void TraverseHuffman(HuffmanNode head, Map lookupTable, String code) {
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
    public String Encode(String input) {
        String output = "";
        for (int i=0; i<input.length(); i++) {
            output += this.lookupTable.get(input.charAt(i));
        }
        return output;
    }
    //takes in string of binary bits and encodes into string of symbols
    public String Decode(String input) {
        String output = "";
        String code = "";
        for (int i=0; i<input.length(); i++) {
            //reading input until a viable code is found
            code += input.substring(i,i+1);
            if (this.lookupTable.containsValue(code)) {
                //retrieving symbol mapped to code
                for (Map.Entry entry : this.lookupTable.entrySet()) {
                    if (entry.getValue().equals(code)) {
                        output += entry.getKey();   //retrieving decoded symbol
                    }
                }
                code = "";//reset code for next encrypted symbol
            }
        }
        return output;
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
