package com.compa;

public class MinQueue {

    public char[] queue;
    public int heapSize = 0; //keeps track of how many nodes are in heap to know where in array to insert new letter


    /* Representing a binary heap with a char array. The array will need to accommodate max
    * the number of letter in the alphabet given. The first element in the array will be unused
    * to make calculations easier.*/
    MinQueue(char[] alphabet, int[] freqs) {
        this.queue = new char[alphabet.length + 1];
        for (int i=0; i< alphabet.length; i++) {
            AddLetterToQueue(alphabet[i], alphabet, freqs, this.heapSize);
            this.heapSize++;
        }
    }

    public void AddLetterToQueue(char letter, char[] alphabet, int[] freqs, int heapSize) {
        int letterIndex = heapSize+1;
        this.queue[letterIndex] = letter;
        //every child in binary heap must have freq greater than parent, since we're doing min priority queue
        //in our queue array, parent index = (child index)/2
        while (FindFreq(letter, alphabet, freqs) < FindFreq(this.queue[letterIndex/2], alphabet, freqs)) {
            //switch parent and child
            this.queue[letterIndex] = this.queue[letterIndex/2];
            this.queue[letterIndex/2] = letter;
            letterIndex /= 2;
        }
    }

    public char ExtractMin(char[] alphabet, int[] freqs) {
        char min = this.queue[1]; //min is root of tree i.e. 2nd element of array
        char letter = this.queue[heapSize];//letter represents last node of heap (highest priority node)
        int letterIndex = heapSize;
        this.queue[1] =letter; // place last node of heap at head

        //reorder heap
        //the first conditional
        while (((FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2], alphabet, freqs)) ||
                (FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2+1], alphabet, freqs))) &&
                letterIndex<= (alphabet.length+1)) {
            //switches parent with left child
            if (FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2], alphabet, freqs)) {
                this.queue[letterIndex] = this.queue[letterIndex*2];
                this.queue[letterIndex*2] = letter;
                letterIndex *= 2;
            } else {
                //switches parent with right child
                this.queue[letterIndex] = this.queue[letterIndex*2+1];
                this.queue[letterIndex*2] = letter;
                letterIndex = letterIndex*2+1;
            }
        }
        return min;
    }

    public int FindFreq(char letter, char[] alphabet, int[] freqs) {
        int index = 0;
        for (int i=0; i< alphabet.length; i++) {
            if (letter == alphabet[i]) {
                index = i;
            }
        }
        return freqs[index];
    }


}
