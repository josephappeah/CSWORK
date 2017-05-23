package com.kenthuang.lotterywapp;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PastDraw
{
	//Numbers for the first 5 balls of the draws
	String[] numbers;
	//number of the powerball for the draw
	String powerball;
	
	public PastDraw(String[] numbers)
	{
		//create numbers array of correct size
		this.numbers=new String[numbers.length-1];
		//set the numbers
		for(int i=0; i<numbers.length-1; i++)
		{
			this.numbers[i]=numbers[i];
		}
		//set powerball number
		this.powerball=numbers[numbers.length-1];
	}
	
	public static class DrawAdapter extends ArrayAdapter<PastDraw>
	{

		public DrawAdapter(Context context, ArrayList<PastDraw> draws)
		{
			super(context, 0, draws);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			// Get the data item for this position
			PastDraw item = getItem(position);    
			// Check if an existing view is being reused, otherwise inflate the view
			if (convertView == null)
			{
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.past_draw_item, parent, false);
			}
			ArrayList<TextView> balls=new ArrayList<TextView>();
			//sets local view variables to the view from the convert view
			balls.add((TextView) convertView.findViewById(R.id.whiteBall1Stats));
			balls.add((TextView) convertView.findViewById(R.id.whiteBall2Stats));
			balls.add((TextView) convertView.findViewById(R.id.whiteBall3Stats));
			balls.add((TextView) convertView.findViewById(R.id.whiteBall4Stats));
			balls.add((TextView) convertView.findViewById(R.id.whiteBall5Stats));
			
			TextView tvPowerBall = (TextView) convertView.findViewById(R.id.powerBallStats);
			// Populate the data into the template view using the data object
			
			int count=0;
			for(TextView tv:balls)
			{
				tv.setText(item.numbers[count]);
				count++;
			}
			
			tvPowerBall.setText(item.powerball);
			
			// Return the completed view to render on screen
			return convertView;
		}
	}
}
