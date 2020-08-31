package com.compa;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        //if an alphabet, a message and frequencies are provided, run the program with java Main -maf message.txt alphabet.txt frequencies.txt
        //if just a message is provid ed, run with java Main message.txt

        if (args.length<1) {
            System.out.println("\nPlease provide proper arguments.");
        } else if (args[0].equals("-maf")) {
            File messageFile = new File(args[1]);
            File alphabetFile = new File(args[2]);
            File frequenciesFile = new File(args[3]);

            Scanner s1 = new Scanner(messageFile);
            Scanner s2 = new Scanner(alphabetFile);
            Scanner s3 = new Scanner(frequenciesFile);
            s2.useDelimiter(",");


            //retrieving alphabet
            String alphabetString = "";
            while (s2.hasNext()){
                alphabetString += s2.next();
            }
            alphabetString = alphabetString.replace("\"\"",","); // this is because Alphabet2.txt represents , with ","
            char[] alphabet = alphabetString.toCharArray();

//            retrieving frequencies
            String freqString = "";
            while(s3.hasNext()) {
                freqString += s3.next();
            }
            String[] array = freqString.split(",");
            int[] freqs = new int[array.length];
            for (int i=0; i< array.length; i++) {
                freqs[i] = Integer.parseInt(array[i]);
            }

//            retrieving message
            byte[] b = Files.readAllBytes(messageFile.toPath());
            String message = new String(b);


//            System.out.println(alphabetString);
//            for (int i : freqs) System.out.print(" "+i);
//
//            System.out.println("\nAlphabet length:" + alphabet.length);
//            System.out.println("\nFreqs length:" + freqs.length);


            Huffman huff = new Huffman(alphabet, freqs);
            String encryption = huff.Encode(message);
            System.out.println(huff.Decode(encryption));


        } else {

        }

//        char[] alphabet = "abcdefg".toCharArray();
//        int[] freqs = {1,3,6,2,7,4,8};
//        Huffman huff = new Huffman(alphabet, freqs);
//        huff.CreateLookupTable(huff.head);
//        System.out.println(huff.Encode("fedac"));
//        System.out.println(huff.Decode(huff.Encode("fedac")));
    }
}
