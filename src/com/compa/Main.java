package com.compa;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Files;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        //if an alphabet, a message and frequencies are provided, run the program with java Main -maf message.txt alphabet.txt frequencies.txt
        //if just a message is provid ed, run with java Main message.txt

        char[] alphabet = new char[0];
        int[] freqs = new int[0];
        String message = null;

        if (args.length < 1) {            //no arguments provided
            System.out.println("\nPlease provide proper arguments.");
        } else if (args[0].equals("-maf")) {            //reads -maf
            File messageFile = new File(args[1]);
            File alphabetFile = new File(args[2]);
            File frequenciesFile = new File(args[3]);

            Scanner s1 = new Scanner(messageFile);
            Scanner s2 = new Scanner(alphabetFile);
            Scanner s3 = new Scanner(frequenciesFile);
            s2.useDelimiter(",");


            //retrieving alphabet
            String alphabetString = "";
            while (s2.hasNext()) {
                alphabetString += s2.next();
            }
            alphabetString = alphabetString.replace("\"\"", ","); // this is because Alphabet2.txt represents , with ","
            alphabet = alphabetString.toCharArray();

//            retrieving frequencies
            String freqString = "";
            while (s3.hasNext()) {
                freqString += s3.next();
            }
            String[] array = freqString.split(",");
            freqs = new int[array.length];
            for (int i = 0; i < array.length; i++) {
                freqs[i] = Integer.parseInt(array[i]);
            }

//            retrieving message
            byte[] b = Files.readAllBytes(messageFile.toPath());
            message = new String(b);

        } else {                                        //reads Tale of Two Cities
            List<Character> symList = new ArrayList<>();
            List<Integer> freqList = new ArrayList<>();
            Charset charset = Charset.forName("UTF-8");
            File file = new File(args[0]);
            byte[] b = Files.readAllBytes(file.toPath());
            message = new String(b, charset);
            for (int i = 0; i < message.length(); i++) {
                System.out.println(".");
                if (symList.contains(message.charAt(i))) {
                    int index = symList.indexOf(message.charAt(i));
                    freqList.set(index, freqList.get(index) + 1);
                } else {
                    symList.add(message.charAt(i));
                    int index = symList.indexOf(message.charAt(i));
                    freqList.add(index, 1);
                }
//            InputStream file = new FileInputStream(args[0]);
//            Reader reader = new InputStreamReader(file, charset);
////            FileReader fr = new FileReader(file);
//            Reader br = new BufferedReader(reader);
//            message = "";
//            int ci;
//            while ((ci = br.read()) != -1) {
////                System.out.println(".");
//                char c = (char) ci;
//                message += c;
//                System.out.print(c);
//                if (symList.contains(c)) {
//                    int index = symList.indexOf(c);
//                    freqList.set(index, freqList.get(index) + 1);
//                } else {
//                    symList.add(c);
//                    int index = symList.indexOf(c);
//                    freqList.add(index, 1);
//                }
            }

            System.out.println("Characters:");
            alphabet = new char[symList.size()];
            for (int i = 0; i < symList.size(); i++) {
                alphabet[i] = symList.get(i);
                System.out.print(alphabet[i] + " ");
            }
            System.out.println("");
//
            freqs = new int[freqList.size()];
            System.out.println("Frequencies:");
            for (int i = 0; i < freqList.size(); i++) {
                freqs[i] = freqList.get(i);
                System.out.print(freqs[i] + " ");
            }
            System.out.println("");


            System.out.println("Number of characters in this file:\t" + message.length());
            System.out.println("Number of unique characters:\t" + symList.size());
//            int maxFreqIndex;
//            for
            System.out.println("Most common character:\t" + symList.get(freqList.indexOf(Collections.max(freqList))));
            System.out.println("Number of time most common character appears:\t" + Collections.max(freqList));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("File scanned. Build Huffman Tree?");

        String ans = sc.next();
        if (ans == "no" || ans == "No") {
            return;
        }
        Huffman huff = new Huffman(alphabet, freqs);
        System.out.println("Huffman Tree built. Encode text?");
        ans = sc.next();
        if (ans == "no" || ans == "No") {
            return;
        }
        double start = System.currentTimeMillis();
        String encryption = huff.Encode(message);
        double end = System.currentTimeMillis();
        System.out.println("Encryption time:\t" + (end-start));
        System.out.println("Decode?");
        ans = sc.next();
        if (ans == "no" || ans == "No") {
            return;
        }
        start = System.currentTimeMillis();
        huff.Decode(encryption);
        end = System.currentTimeMillis();
        System.out.println("\nDecryption time:\t" + (end-start));
        System.out.println("Number of alphabet symbols:\t" + alphabet.length);
        System.out.println("Number of bits for each symbol without encryption:\t" + huff.NumberOfBits(alphabet));
        System.out.println("Number of total bits in uniform-length encryption:\t" + message.length()* huff.NumberOfBits(alphabet));
        System.out.println("Number of bits in encoded message:\t" + encryption.length());
        System.out.println("Characters and their corresponding frequencies:\n");

        int mean = 0;
        double std = 0;
        for (int i=0; i<freqs.length; i++) {
            mean += freqs[i];
            System.out.println(alphabet[i] + "\t" + freqs[i]);
        }
        mean /= freqs.length;
        for (int i=0; i<freqs.length; i++) {
            std += Math.pow(freqs[i] - mean, 2);
        }
        std = Math.sqrt(std/ freqs.length);

        System.out.println("Standard Deviation of Frequencies:\t" + std);



    }
}
