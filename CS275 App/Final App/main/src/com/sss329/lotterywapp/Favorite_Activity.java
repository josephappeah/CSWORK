package com.kenthuang.lotterywapp;

import java.util.ArrayList;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

import com.temboo.Library.CloudMine.ObjectStorage.ObjectDelete;
import com.temboo.Library.CloudMine.ObjectStorage.ObjectDelete.ObjectDeleteInputSet;
import com.temboo.Library.CloudMine.ObjectStorage.ObjectGet;
import com.temboo.Library.CloudMine.ObjectStorage.ObjectGet.ObjectGetInputSet;
import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

/**
 * Created by kenthuang on 3/14/16.
 */
public class Favorite_Activity extends Activity implements OnClickListener{

	public SharedPreferences sharedPref;

	public OnClickListener listener=this;

	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.page_main);

		setContentView(R.layout.activity_favorite);

		//Button to go back to main activity
		Button back=(Button)findViewById(R.id.backButtonFav);
		back.setEnabled(true);
		back.setOnClickListener(this);

		//Shared prefs storing temboo and cloudmine credentials
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

		//Use async task to get current favorite objects
		new CloudmineGetTask().execute();
	}

	//Async task to remove a favorite
	public class CloudmineRemoveTask extends AsyncTask<String, Void, Void>
	{
		//TembooSession to be used throughout class
		public TembooSession session;

		protected void onPreExecute()
		{
			super.onPreExecute();

			//Get temboo credentials
			String tembooID=sharedPref.getString("Temboo ID", "kent");
			String tembooKey=sharedPref.getString("Temboo Key", "PToJ707BRKAaqH8FKjZggQBAMNJ28ZoM");
			String tembooName=sharedPref.getString("Temboo App Name", "projectcs275");

			try
			{
				//create session from credentials
				session=new TembooSession(tembooID, tembooName, tembooKey);
			} catch (TembooException e) {
				Log.e("Temboo Exception", e.getMessage());
			}
		}

		@Override
		protected Void doInBackground(String... args)
		{
			//Get cloudmine credentials
			String cloudmineID=sharedPref.getString("Cloudmine ID", "c62c3798e887ae47d9fb22b7076fa60e");
			String cloudmineKey=sharedPref.getString("Cloudmine Key", "cf2503fc7ea0452a84346987ef6deb36");

			//Create ObjectDelete Choreo
			ObjectDelete odChoreo=new ObjectDelete(session);

			//Create new input set
			ObjectDeleteInputSet odInput=odChoreo.newInputSet();

			//set inputs
			odInput.set_APIKey(cloudmineKey);
			odInput.set_ApplicationIdentifier(cloudmineID);
			odInput.set_All("false");
			odInput.set_Keys(args[0]);
			

			try
			{
				//run choreo
				odChoreo.execute(odInput);
			} catch (TembooException e)
			{
				Log.e("Temboo Exception", e.getMessage());
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
			//Get new list of favorites to update display
			new CloudmineGetTask().execute();
		}
		
	}
	
	//used to retrieve the favorite data.
	public class CloudmineGetTask extends AsyncTask<Void, Void, String>
	{
		//Temboo session used throughout class
		public TembooSession session;

		protected void onPreExecute()
		{
			super.onPreExecute();

			//Get temboo credentials
			String tembooID=sharedPref.getString("Temboo ID", "kent");
			String tembooKey=sharedPref.getString("Temboo Key", "PToJ707BRKAaqH8FKjZggQBAMNJ28ZoM");
			String tembooName=sharedPref.getString("Temboo App Name", "projectcs275");

			try
			{
				//create session from credentials
				session=new TembooSession(tembooID, tembooName, tembooKey);
			} catch (TembooException e) {
				Log.e("Temboo Exception", e.getMessage());
			}
		}

		@Override
		protected String doInBackground(Void... params)
		{
			//Holds the response from the choreo
			String result="";

			//Get cloudmine credentials
			String cloudmineID=sharedPref.getString("Cloudmine ID", "c62c3798e887ae47d9fb22b7076fa60e");
			String cloudmineKey=sharedPref.getString("Cloudmine Key", "cf2503fc7ea0452a84346987ef6deb36");

			//Create ObjectGet Choreo
			ObjectGet ogChoreo=new ObjectGet(session);

			//Create new input set
			ObjectGetInputSet ogInput=ogChoreo.newInputSet();

			//set inputs
			ogInput.set_APIKey(cloudmineKey);
			ogInput.set_ApplicationIdentifier(cloudmineID);

			try
			{
				//execute choreo get the response string and save it as result
				result=ogChoreo.execute(ogInput).get_Response();
			} catch (TembooException e)
			{
				Log.e("Temboo Exception", e.getMessage());
			}

			return result;
		}

		@Override
		protected void onPostExecute(String result)
		{
			super.onPostExecute(result);
			//Call display task with the result string
			new DisplayTask().execute(result);
		}

	}

	public class DisplayTask extends AsyncTask<String, Void, Void>
	{
		//ArrayList to be used through out the class and in the arrayadapter later
		public ArrayList<Item_Lotto> items =new ArrayList<Item_Lotto>();
		

		@Override
		protected void onPreExecute()
		{
			super.onPreExecute();
		}

		protected Void doInBackground(String... params)
		{
			try
			{
				//Create the root object from the string passed in and under the first tag success
				JSONObject root=new JSONObject(params[0]).getJSONObject("success");
				
				//Iterator of keys to run over json object
				Iterator<String> keys=root.keys();
				JSONObject currentObj;

				//Iterate over root object
				while(keys.hasNext())
				{
					//Get the first key to string
					String key=keys.next();
					//currentObj holds the JSONobject
					currentObj = root.getJSONObject(key);

					//get the currentObject from the root using the current key
					currentObj=root.getJSONObject(key);
					//temporary holds the int array to create Item_lotto
					int[] temp = new int[6];
					temp[0] = currentObj.getInt("Ball1");
					temp[1] = currentObj.getInt("Ball2");
					temp[2] = currentObj.getInt("Ball3");
					temp[3] = currentObj.getInt("Ball4");
					temp[4] = currentObj.getInt("Ball5");
					temp[5] = currentObj.getInt("Powerball");

					//add new Item_Lotto to arraylist
					items.add(new Item_Lotto(temp, key, listener));
				}
			} catch (JSONException e){
				Log.e("JSONException", e.getMessage());
			}
			return null;

		}

		@Override
		protected void onPostExecute(Void result)
		{
			super.onPostExecute(result);
			//Get the list view of favorite numbers
			ListView lvFav = (ListView) findViewById(R.id.favorites);
			//Create an arrayadapter off of the numbers array
			Item_Lotto.LottoAdapter itemAdapter=new Item_Lotto.LottoAdapter(getApplicationContext(), items);
			//set the listview arrayadapter to that adapter
			lvFav.setAdapter(itemAdapter);

		}

	}

	
	@Override
	public void onClick(View v)
	{
		//Create a new intent for the main activity if the views id is back button
		if(v.getId()==R.id.backButtonFav)
		{
			Intent myIntent = new Intent(Favorite_Activity.this, Main_Activity.class);
			Favorite_Activity.this.startActivity(myIntent);
		}
		else
		{
			//remove the object with the key stored in v's tag from favroites on cloudmine
			new CloudmineRemoveTask().execute(v.getTag().toString());
		}
	}



}
