package com.compa;

public class MinQueue {

    public char[] queue;
    public int heapSize = 0; //keeps track of how many nodes are in heap to know where in array to insert new letter


    /* Representing a binary heap with a char array. The array will need to accommodate max
    * the number of letter in the alphabet given. The first element in the array will be unused
    * to make calculations easier.*/
    MinQueue(char[] alphabet, int[] freqs) {
        //I add an extra element in the array than needed to fit the entire alphabet in order to avoid null
        //pointer errors when comparing left and right children of nodes. Without this, if the very last node of the
        //tree was a left child, then we would get a null pointer when we try to compare it to the right child.
        this.queue = new char[alphabet.length + 2];
        for (int i=0; i< alphabet.length; i++) {
            AddLetterToQueue(alphabet[i], alphabet, freqs, this.heapSize);
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

        this.heapSize++;
    }

    public char ExtractMin(char[] alphabet, int[] freqs) {
        //if statement for when all nodes have been extracted. Returns a space
        if (this.heapSize == 0){
            return ' ';
        }

        char min = this.queue[1]; //min is root of tree i.e. 2nd element of array
        char letter = this.queue[this.heapSize];//letter represents last node of heap (highest priority node)
        int letterIndex = 1;
        this.queue[1] = letter; // place last node of heap at head
        this.heapSize--;


        //reorder heap
        //the node is at the bottom depth at tree if its index in array is greater than the max number of nodes integer divided by 2
        while (letterIndex<= (this.heapSize/2)) {
            //node switches parent with left child if priority is greater than left's but smaller than or equal to right's
            if ((FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2], alphabet, freqs)) &&
                    (FindFreq(letter, alphabet, freqs) <= FindFreq(this.queue[letterIndex*2+1], alphabet, freqs))) {
                this.queue[letterIndex] = this.queue[letterIndex*2];
                this.queue[letterIndex*2] = letter;
                letterIndex *= 2;
                System.out.println("1");
            }
            //node switches parent with right child if priority is greater than right's but smaller than or equal to left's
            else if ((FindFreq(letter, alphabet, freqs)<=FindFreq(this.queue[letterIndex*2], alphabet, freqs)) &&
                    (FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2+1], alphabet, freqs))) {
                this.queue[letterIndex] = this.queue[letterIndex*2+1];
                this.queue[letterIndex*2+1] = letter;
                letterIndex = letterIndex*2+1;
                System.out.println("2");
            }
            //otherwise, node is greater than both children and we must compare the priorities of the children
            else if ((FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2], alphabet, freqs)) &&
                    (FindFreq(letter, alphabet, freqs) > FindFreq(this.queue[letterIndex*2+1], alphabet, freqs))){
                //left child greater than right child in terms of priority, so we switch with right
                if (FindFreq(this.queue[letterIndex*2], alphabet, freqs) > FindFreq(this.queue[letterIndex*2+1], alphabet, freqs)) {
                    this.queue[letterIndex] = this.queue[letterIndex*2+1];
                    this.queue[letterIndex*2+1] = letter;
                    letterIndex = letterIndex*2+1;
                    System.out.println("3");
                }
                //left child is lesser than or equal to right in terms of priority, so we switch with left
                else {
                    this.queue[letterIndex] = this.queue[letterIndex*2];
                    this.queue[letterIndex*2] = letter;
                    letterIndex *= 2;
                    System.out.println("4");
                }
            }
            else break;
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
