package com.kenthuang.lotterywapp;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.kenthuang.lotterywapp.PastDraw.DrawAdapter;

public class Statistics extends Activity implements OnClickListener
{
	
	public class getPastDraws extends AsyncTask<Void, Void, Void>
	{
		//Arraylist containg past draw items
		ArrayList<PastDraw> draws;
		
		@Override
		//create the arraylist
		protected void onPreExecute()
		{
			super.onPreExecute();
			draws=new ArrayList<PastDraw>();
		}
		
		@Override
		protected Void doInBackground(Void... params)
		{
			try
			{
				//Create url to get past draws from
				URL url=new URL("https://data.ny.gov/api/views/d6yy-54nr/rows.json");
				
				//Connect to the url
				HttpURLConnection request=(HttpURLConnection)url.openConnection();
				request.connect();
				
				//Create Buffered reader off inputstream from the url connection
				BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(request.getInputStream())));
				
				//Create string builder
				StringBuilder result=new StringBuilder();
				
				//holds current line of the reader
				String line;
				
				while((line=reader.readLine()) != null)
				{
					//append current line to result
					result.append(line);
				}
								
				//Create JSONArray from JsonObject of the result string and get data array
				JSONArray root=new JSONObject(result.toString()).getJSONArray("data");
				
				for(int i=0; i<root.length(); i++)
				{
					//Hold current array
					JSONArray currArray=root.getJSONArray(i);
					
					//Get string of drawn numbers
					String nums=currArray.getString((currArray.length())-2);
					
					//add new past draw using the nums string split on " "(blank space);
					draws.add(new PastDraw(nums.split(" ")));
				}
				
			} catch (Exception e)
			{
				Log.e("getPastDrawsTask", e.getMessage());
			}
			
			
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result)
		{
			//Create new adapter
			DrawAdapter adapter=new DrawAdapter(getApplicationContext(), draws);
			//Get listview
			ListView lvDraws=(ListView)findViewById(R.id.favorites);
			//set list view adapter
			lvDraws.setAdapter(adapter);
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		//call async to get past draws and populate list view
		new getPastDraws().execute();
		
		//setup for button back to main activity
		Button back=(Button) findViewById(R.id.backButtonStats);
		back.setEnabled(true);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		//Create intent to return to main activity
		 Intent myIntent = new Intent(Statistics.this, Main_Activity.class);
         Statistics.this.startActivity(myIntent);
	}
}
