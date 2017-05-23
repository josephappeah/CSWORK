package com.kenthuang.lotterywapp;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.temboo.Library.CloudMine.ObjectStorage.ObjectSet;
import com.temboo.Library.CloudMine.ObjectStorage.ObjectSet.ObjectSetInputSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;
/**
 * Created by kenthuang on 3/13/16.
 */

public class NumGenPage extends Activity {

	private NumberPicker numpicklist[];
	private Button buttonlist[];

	private NumberPicker powerballnumberPicker;
	private Button powerballButton;

	private GenerateNumbers genNum = new GenerateNumbers();

	private SharedPreferences sharedPref;


	public class CloudmineSetTask extends AsyncTask<String, Void, Void>
	{
		//Temboo Session to be used throughout this class
		public TembooSession session;

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
			//Get temboo credentials from sharedPrefs
			String tembooID=sharedPref.getString("Temboo ID", "kent");
			String tembooKey=sharedPref.getString("Temboo Key", "PToJ707BRKAaqH8FKjZggQBAMNJ28ZoM");
			String tembooName=sharedPref.getString("Temboo App Name", "projectcs275");

			try
			{
				Log.i("tembooSession", "Creating Session");
				//Create tembooSession from credentials
				session=new TembooSession(tembooID, tembooName, tembooKey);
			} catch (Exception e) {
				Log.e("tembooException", e.getMessage());
			}
			Log.i("tembooSession", "Created Session");

		}

		@Override
		protected Void doInBackground(String... args)
		{

			Log.i("asyncData", args[0]);
			//Get cloudmine Credentials from shared Prefs
			String cloudmineID=sharedPref.getString("Cloudmine ID", "c62c3798e887ae47d9fb22b7076fa60e");
			String cloudmineKey=sharedPref.getString("Cloudmine Key", "cf2503fc7ea0452a84346987ef6deb36");

			//Create a cloudmine objectSet choreo off of the session
			ObjectSet osChoreo=new ObjectSet(session);

			//Create a new input set
			ObjectSetInputSet osInput=osChoreo.newInputSet();

			//Set ObjectSet inputs
			osInput.set_APIKey(cloudmineKey);
			osInput.set_ApplicationIdentifier(cloudmineID);
			osInput.set_Data(args[0]);

			try
			{
				//Execute Choreo
				osChoreo.execute(osInput);
			} catch (TembooException e)
			{
				Log.e("TembooException", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
		}

	}


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.page_main);

		setContentView(R.layout.randomnumgen);

		sharedPref = this.getSharedPreferences(getString(R.string.preference_file_key), Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();

		//Check for shared prefs and set them if they don't exist
		if(!sharedPref.contains("Temboo Key"))
		{
			editor.putString("Temboo Key", "PToJ707BRKAaqH8FKjZggQBAMNJ28ZoM");
		}
		if(!sharedPref.contains("Temboo App Name"))
		{
			editor.putString("Temboo App Name", "projectcs275");
		}
		if(!sharedPref.contains("Temboo ID"))
		{
			editor.putString("Temboo ID", "kent");
		}

		if(!sharedPref.contains("Cloudmine ID"))
		{
			editor.putString("Cloudmine ID", "c62c3798e887ae47d9fb22b7076fa60e");
		}
		if(!sharedPref.contains("Cloudmine Key"))
		{
			editor.putString("Cloudmine Key", "cf2503fc7ea0452a84346987ef6deb36");
		}

		//Create list of numberpickers and get the views from the layout
		numpicklist = new NumberPicker[5];
		numpicklist[0] = (NumberPicker) findViewById(R.id.numberpicker1);
		numpicklist[1] = (NumberPicker) findViewById(R.id.numberpicker2);
		numpicklist[2] = (NumberPicker) findViewById(R.id.numberpicker3);
		numpicklist[3] = (NumberPicker) findViewById(R.id.numberpicker4);
		numpicklist[4] = (NumberPicker) findViewById(R.id.numberpicker5);

		//Create list of buttons and get views from the layout
		buttonlist = new Button[5];
		buttonlist[0] = (Button) findViewById(R.id.ball1);
		buttonlist[1] = (Button) findViewById(R.id.ball2);
		buttonlist[2] = (Button) findViewById(R.id.ball3);
		buttonlist[3] = (Button) findViewById(R.id.ball4);
		buttonlist[4] = (Button) findViewById(R.id.ball5);

		//Create powerball picker and powerball button
		powerballnumberPicker = (NumberPicker) findViewById(R.id.numberpicker6);
		powerballButton = (Button) findViewById(R.id.powerball);

		//button to update
		Button genButton = (Button) findViewById(R.id.genButton);
		genButton.setOnClickListener(new GenButtonListner());
		genButton.setEnabled(true);

		//Setting up button to favorite/save
		Button favButton=(Button)findViewById(R.id.favButton);
		favButton.setEnabled(true);
		//OnClickListener for adding the current set to favorites
		favButton.setOnClickListener(new View.OnClickListener()
		{
			//Random number
			Random rand=new Random();


			@Override
			public void onClick(View v)
			{
				//key to be used as object id
				int key=1;

				for(int i=0; i<3; i++)
				{
					//Multiply key by 3 random digits from 1-25
					key*=rand.nextInt(25)+1;
				}

				//Start building json data
				String result="{\"Favorite"+key+"\": {";

				//Retrieve each ball and number for the ball and add to result string
				for(int i=0; i<buttonlist.length; i++)
				{
					result+="\"Ball"+(i+1)+"\":"+"\""+buttonlist[i].getText()+"\",";
				}

				//add powerball to the result
				result+="\"Powerball\": \""+powerballButton.getText()+"\"}}";

				//call async task to set the new favorite passing in the result string
				new CloudmineSetTask().execute(result);
			}
		});


		//setting up backbutton
		Button backbutton = (Button) findViewById(R.id.backbutton);
		backbutton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Create intent to return to main screen
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
			numpicklist[x].setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
			numpicklist[x].setValue(set[x]);
			//the powerballnumpick is assigned below

			//the first time you enter the NumGen screen it will print a random set.
			//buttonlist[x].setText("1");

			buttonlist[x].setText(set[x] + "");
			buttonlist[x].setOnClickListener(new ButtonClassListner(x));
			buttonlist[x].setEnabled(true);
			buttonlist[x].getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);

		}

		//setting powerballnumpick
		powerballnumberPicker.setMaxValue(26);
		powerballnumberPicker.setMinValue(1);
		powerballnumberPicker.setWrapSelectorWheel(true);
		powerballnumberPicker.setOnValueChangedListener(new numPickClassListner(5));
		powerballnumberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
		powerballnumberPicker.setValue(set[5]);

		//setting the powerballbutton
		powerballButton.setText(set[5]+"");
		powerballButton.setOnClickListener(new ButtonClassListner(5));
		powerballButton.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);


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

			
			int num=Integer.parseInt(((Button)view).getText().toString());
			//keeps track of what position is locked
			genNum.lockPosition(position, num);
			//put some color change here
//			numpicklist[position].setEnabled(false);
			Button b=(Button)view;
			if(genNum.getLockPos(position) == 0){
				b.getBackground().setColorFilter(Color.LTGRAY, PorterDuff.Mode.MULTIPLY);
				if(position==5)
					powerballnumberPicker.setEnabled(true);
				else
					numpicklist[position].setEnabled(true);
			}
			else 
			{
				b.getBackground().setColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY);
				if(position==5)
					powerballnumberPicker.setEnabled(false);
				else
					numpicklist[position].setEnabled(false);
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
			int[] nums = genNum.getSet();
			for(int i=0; i<5; i++)
			{
				numpicklist[i].setValue(nums[i]);
			}
			powerballnumberPicker.setValue(nums[5]);

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
