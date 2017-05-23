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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.kenthuang.lotterywapp.PastDraw.DrawAdapter;

public class Statistics extends Activity implements OnClickListener
{
	
	public class getPastDraws extends AsyncTask<Void, Void, Void>
	{
		ArrayList<PastDraw> draws;
		
		@Override
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
				URL url=new URL("https://data.ny.gov/api/views/d6yy-54nr/rows.json");
				
				HttpURLConnection request=(HttpURLConnection)url.openConnection();
				request.connect();
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(new BufferedInputStream(request.getInputStream())));
				
				StringBuilder result=new StringBuilder();
				
				String line;
				
				while((line=reader.readLine()) != null)
				{
					result.append(line);
				}
								
				JSONArray root=new JSONObject(result.toString()).getJSONArray("data");
				
				for(int i=0; i<root.length(); i++)
				{
					JSONArray currArray=root.getJSONArray(i);
					
					String nums=currArray.getString((currArray.length())-2);
					
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
			DrawAdapter adapter=new DrawAdapter(getApplicationContext(), draws);
			ListView lvDraws=(ListView)findViewById(R.id.pastDraws);
			lvDraws.setAdapter(adapter);
		}
		
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_statistics);
		
		new getPastDraws().execute();
		
		Button back=(Button) findViewById(R.id.backButtonStats);
		back.setEnabled(true);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v)
	{
		 Intent myIntent = new Intent(Statistics.this, Main_Activity.class);
         Statistics.this.startActivity(myIntent);
	}
}
