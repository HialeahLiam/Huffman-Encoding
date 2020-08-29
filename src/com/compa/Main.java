package com.compa;


public class Main {

    public static void main(String[] args) {
	// write your code here
        char[] alphabet = "abcdefg".toCharArray();
        int[] freqs = {1,3,6,2,7,4,8};
        MinQueue Q = new MinQueue(alphabet, freqs);

        System.out.println("Alphabet:");

        for (int i=0; i< alphabet.length; i++) {
            System.out.print("|" + alphabet[i]);
        }
        System.out.println("\n" + Q.heapSize);


        for (int i=0; i< Q.queue.length; i++) {
            System.out.print("|" + Q.queue[i]);
        }

        Q.ExtractMin(alphabet, freqs);


    }
}
