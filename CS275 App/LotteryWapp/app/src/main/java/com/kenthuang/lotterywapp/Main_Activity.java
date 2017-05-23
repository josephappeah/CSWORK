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

        Button button1 = (Button) findViewById(R.id.changescreen1);
        Button button2 = (Button) findViewById(R.id.changescreen2);
        Button button3 = (Button) findViewById(R.id.changescreen3);


        button1.setEnabled(true);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main_Activity.this, NumGenPage.class);
                Main_Activity.this.startActivity(myIntent);
            }
        });

        /*
        button2.setEnabled(true);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main_Activity.this, NumGenPage.class);
                Main_Activity.this.startActivity(myIntent);
            }
        });
    */
        button3.setEnabled(true);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(Main_Activity.this, Statistics.class);
                Main_Activity.this.startActivity(myIntent);
            }
        });

    }
}
