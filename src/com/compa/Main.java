package com.compa;


import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
	// write your code here
        char[] alphabet = "abcdefg".toCharArray();
        int[] freqs = {1,3,6,2,7,4,8};
        BuildHuffman huff = new BuildHuffman(alphabet, freqs);
        

    }
}
