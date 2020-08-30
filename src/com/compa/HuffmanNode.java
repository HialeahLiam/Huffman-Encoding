package com.compa;

public class HuffmanNode {
    char letter;
    int freq;
    HuffmanNode left;
    HuffmanNode right;

    HuffmanNode(char letter, int freq) {
        this.letter = letter;
        this.freq = freq;
    }
}
