package com.compa;


import java.sql.SQLOutput;

public class Main {

    public static void main(String[] args) {
	// write your code here
        char[] alphabet = "abcdefg".toCharArray();
        int[] freqs = {1,3,6,2,7,4,8};
        BuildHuffman huff = new BuildHuffman(alphabet, freqs);
        while(true) {
            HuffmanNode head = huff.Q.poll();
            if (head == null) {
                break;
            }
            System.out.println(head.letter + " " + head.freq);
        }


//        MinQueue Q = new MinQueue(alphabet, freqs);

//        System.out.println("Priority Min Queue");
//
//        for (int i=0; i< Q.queue.length; i++) {
//            System.out.print("|" + Q.queue[i]);
//        }

//        while (true) {
//            char extracted = Q.ExtractMin(alphabet, freqs);
//            if (extracted == ' ') break;
//            System.out.println("\nMin extracted:");
//            System.out.println(extracted);
//            System.out.println("Priority Min Queue");
//            for (int i = 0; i < Q.queue.length; i++) {
//                System.out.print("|" + Q.queue[i]);
//            }
//        }

    }
}
