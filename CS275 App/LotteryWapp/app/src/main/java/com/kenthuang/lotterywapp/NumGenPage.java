package com.kenthuang.lotterywapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.graphics.Color;
/**
 * Created by kenthuang on 3/13/16.
 */

public class NumGenPage extends Activity {

    private NumberPicker numpicklist[];
    private Button buttonlist[];

    private NumberPicker powerballnumberPicker;
    private Button powerballButton;

    private GenerateNumbers genNum = new GenerateNumbers();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.page_main);

        setContentView(R.layout.randomnumgen);
        numpicklist = new NumberPicker[5];
        numpicklist[0] = (NumberPicker) findViewById(R.id.numberpicker1);
        numpicklist[1] = (NumberPicker) findViewById(R.id.numberpicker2);
        numpicklist[2] = (NumberPicker) findViewById(R.id.numberpicker3);
        numpicklist[3] = (NumberPicker) findViewById(R.id.numberpicker4);
        numpicklist[4] = (NumberPicker) findViewById(R.id.numberpicker5);


        buttonlist = new Button[5];
        buttonlist[0] = (Button) findViewById(R.id.ball1);
        buttonlist[1] = (Button) findViewById(R.id.ball2);
        buttonlist[2] = (Button) findViewById(R.id.ball3);
        buttonlist[3] = (Button) findViewById(R.id.ball4);
        buttonlist[4] = (Button) findViewById(R.id.ball5);

        powerballnumberPicker = (NumberPicker) findViewById(R.id.numberpicker6);
        powerballButton = (Button) findViewById(R.id.powerball);


        //button to update
        Button genButton = (Button) findViewById(R.id.genButton);
        genButton.setOnClickListener(new GenButtonListner());
        genButton.setEnabled(true);

        //Setting up button to favorite/save



        //setting up backbutton
        Button backbutton = (Button) findViewById(R.id.backbutton);
        backbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(NumGenPage.this, Main_Activity.class);
                NumGenPage.this.startActivity(myIntent);
            }
        });

        //setup
        setup();

    }

    public void setup() {
        genNum.drawing();
        //Calls a method that generates a random set of numbers
        int[] set = genNum.getSet();
        //length is always 5
        for (int x = 0; x < numpicklist.length; x++) {
            numpicklist[x].setMaxValue(69);
            numpicklist[x].setMinValue(1);
            numpicklist[x].setWrapSelectorWheel(true);
            numpicklist[x].setOnValueChangedListener(new numPickClassListner(x));
            //the powerballnumpick is assigned below

            //the first time you enter the NumGen screen it will print a random set.
            //buttonlist[x].setText("1");

            buttonlist[x].setText(set[x] + "");
            buttonlist[x].setOnClickListener(new ButtonClassListner(x));
            buttonlist[x].setEnabled(true);
            buttonlist[x].setBackgroundColor(Color.GRAY);

        }

        //setting powerballnumpick
        powerballnumberPicker.setMaxValue(26);
        powerballnumberPicker.setMinValue(1);
        powerballnumberPicker.setWrapSelectorWheel(true);
        powerballnumberPicker.setOnValueChangedListener(new numPickClassListner(5));

        //setting the powerballbutton
        powerballButton.setText(set[5]+"");
        powerballButton.setOnClickListener(new ButtonClassListner(5));
        powerballButton.setBackgroundColor(Color.GRAY);


    }

    public class numPickClassListner implements NumberPicker.OnValueChangeListener {
        int whichButton = 0;
        //default constructor
        numPickClassListner(){}
        //alter constructor that takes in an int parameter to indiciate the location
        numPickClassListner(int whichButton) {
            this.whichButton = whichButton;
        }

        //If it alternative constructor was not used to indicate with ball, it will be the powerball
        //it changes the text on the button.
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                if(whichButton == 5){
                    powerballButton.setText(newVal+"");
                    powerballnumberPicker.setValue(newVal);

                }else{
                    buttonlist[whichButton].setText(newVal+"");
                    numpicklist[whichButton].setValue(newVal);
                }
        }
    }

    private class ButtonClassListner implements View.OnClickListener{
        int position;
        int lockposCount;
        public ButtonClassListner(int position){
            this.position = position;
        }

        @Override
        public void onClick(View view){
            //keeps track of what position is locked
            genNum.lockPosition(position);
            //put some color change here
            Button b=(Button)view;
            lockposCount = genNum.getLockPos(position);
            if(lockposCount == 0){
                b.setBackgroundColor(Color.LTGRAY);
            }else {
                b.setBackgroundColor(Color.GREEN);
            }
        }

    }


    //class used to override the onclick for this button
    public class GenButtonListner implements View.OnClickListener{
        //default constructor we are just trying to override the onclick
        public GenButtonListner(){
        }

        @Override
        public void onClick(View view){
            generateSet();

        }
    }

    //method used to generateSet of numbers and display it.
    public void generateSet(){
        genNum.drawing();
        int[] set = genNum.getSet();
        for(int x = 0 ; x < 5; x++) {
            buttonlist[x].setText(set[x] + "");
        }
        powerballButton.setText(set[5]+"");

    }

}
