package com.kenthuang.lotterywapp;
/**
 * Created by kenthuang on 2/29/16.
 */

import java.util.Random;

public class GenerateNumbers {

    int[] set;   //array holds the drawing set
    int[] lockposition; //holds the lock value for each position

    public GenerateNumbers()
    {
    	//Create a new set
        set = new int[6]; //
        //Create a lockpositions and initialize to 0
        lockposition = new int[6];
        for(int x = 0; x < 6; x++){
            lockposition[x] = 0;
        }


    }

    //Generates new random numbers
    public GenerateNumbers(int num1, int num2, int num3, int num4, int num5, int pb)
    {
    	//sets each set to new numbers
        set[0] = num1;
        set[1] = num2;
        set[2] = num3;
        set[3] = num4;
        set[4] = num5;
        set[5] = pb;

    }

    //turns the values to 0
    public void reset(){

        for(int x = 0; x < set.length; x++){
            set[x] = 0;
        }

    }

    public void drawing(){
        Random rand = new Random();

        //only needs to check 1 through 5
        int[] checkforsame = new int[5];

        int temprand = 0;
        //random numbers for balls 1-5
        for(int x = 0; x < 5; x++){
            //if the position is locked, it will equal 1
            //only if it is 0 we will generate a random
            if(lockposition[x]==0) {
                //Generates the random number, if it generates a number that already exists it will generate anther number
                do {
                    int temp1 = 0;
                    temprand = rand.nextInt(69) + 1;

                    //checks if the rand number generated has already been generated if so incredment the counter temp1
                    for (int y = 0; y < checkforsame.length; y++) {
                        if (checkforsame[y] == temprand) {
                            temp1++;
                        }
                    }
                    //temp1 is a counter used to indicate if there was a dup, if there is a dup it will generate another num
                    if (temp1 == 0) {
                        break;
                    }
                } while (true);
                checkforsame[x] = temprand;
                set[x] = temprand;
            }

        }

        //if the position is locked, it will equal 1
        //only if it is 0 we will generate a random
        if(lockposition[5]==0) {
            //random number for the powerball
            set[5] = rand.nextInt(26) + 1;
        }

    }


    //keep track of which position is locked
    public void lockPosition(int location, int num){
    	set[location]=num;
        if(lockposition[location] == 1) {
        	lockposition[location] = 0;
        }else {
            lockposition[location] = 1;
        }


    }



    //return the set
    public int[] getSet(){
        return set;
    }

    //get a specific number from the drawing
    public int getSpecific(int num){
        return set[num];
    }

    public int getLockPos(int num){
        return lockposition[num];
    }

}
