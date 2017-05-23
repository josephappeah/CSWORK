package com.kenthuang.lotterywapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import com.temboo.core.TembooException;
import com.temboo.core.TembooSession;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by kenthuang on 3/14/16.
 */
public class Favorite_Activity {



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

                Iterator<String> keys=root.keys();

                JSONObject currentObj;

                //Iterate over root object
                while(keys.hasNext())
                {
                    //Get the first key to string
                    String key=keys.toString();
                    //currentObj holds the JSONobject
                    currentObj = root.getJSONObject(key);

                    //Get the next key from the iterator
                    key=keys.next();

                    //get the currentObject from the root using the current key
                    currentObj=root.getJSONObject(key);
                    //temprary holds the int array to create Item_lotto
                    int[] temp = new int[6];
                    temp[0] = currentObj.getInt("Ball1");
                    temp[1] = currentObj.getInt("Ball2");
                    temp[2] = currentObj.getInt("Ball3");
                    temp[3] = currentObj.getInt("Ball4");
                    temp[4] = currentObj.getInt("Ball5");
                    temp[5] = currentObj.getInt("powerball");



                    items.add(new Item_Lotto());
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
            //Get the list view of close friends
            ListView lvFav = (ListView) findViewById(R.id.activity_favorite);
            //Create an arrayadapter off of the friends array
            Item_Lotto.LottoAdapter itemAdapter=new Item_Lotto.LottoAdapter(getApplicationContext(), items);
            //set the listview arrayadapter to that adapter
            lvFrav.setAdapter(itemAdapter);

        }

    }



}
