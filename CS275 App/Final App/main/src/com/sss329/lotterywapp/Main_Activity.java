package com.kenthuang.lotterywapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


/**
 * Created by kenthuang on 3/13/16.
 */
public class Main_Activity extends Activity{
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_main);

        //Button to go to number generator
        Button button1 = (Button) findViewById(R.id.changescreen1);
        //Button to go to favorites list
        Button button2 = (Button) findViewById(R.id.changescreen2);
        //Button to go to list of past drawings
        Button button3 = (Button) findViewById(R.id.changescreen3);

        //enable button1 
        button1.setEnabled(true);
        //set onclick listener to new listener defined here
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            //Create intent to go to generator activity
            public void onClick(View v) {
                Intent myIntent = new Intent(Main_Activity.this, NumGenPage.class);
                Main_Activity.this.startActivity(myIntent);
            }
        });

        //enable button2
        button2.setEnabled(true);
        //set onclick listener to new listener defined here
        button2.setOnClickListener(new View.OnClickListener() {
            //Create intent to go to favorites activity
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main_Activity.this, Favorite_Activity.class);
                Main_Activity.this.startActivity(myIntent);
            }
        });

        //enable button3
        button3.setEnabled(true);
        //set onclick listener to new listener defined here
        button3.setOnClickListener(new View.OnClickListener() {
            //Create intent to go to pastDraws activity
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main_Activity.this, Statistics.class);
                Main_Activity.this.startActivity(myIntent);
            }
        });
    }
}
