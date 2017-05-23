package com.kenthuang.lotterywapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class Item_Lotto implements OnClickListener
{
	//array of the numbers in the draw
	public int[] numbers;
	//Listener for the remove button
	public OnClickListener listener;
	//id of the favorite object
	public String id;
	
	//Initialize object
	public Item_Lotto(int[] numbers, String id, OnClickListener listener)
	{
		this.numbers=numbers;
		this.listener=listener;
		this.id=id;
	}

	public void setNumbers(int[] numbers)
	{
		this.numbers=numbers;
	}
	
	public int[] getNumbers()
	{
		return numbers;
	}
	
	public String getNumber(int position)
	{
		return numbers[position]+"";
	}
	
	public static class LottoAdapter extends ArrayAdapter<Item_Lotto>
	{
		//Constructor
		public LottoAdapter(Context context, ArrayList<Item_Lotto> items) 
		{
			super(context, 0, items);
		}

		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) 
		{
			// Get the data item for this position
			Item_Lotto item = getItem(position);    
			// Check if an existing view is being reused, otherwise inflate the view
			if (convertView == null)
			{
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.lotto_item, parent, false);
			}
			//sets local view variables to the view from the convert view
			TextView tvBall1 = (TextView) convertView.findViewById(R.id.whiteBall1);
			TextView tvBall2 = (TextView) convertView.findViewById(R.id.whiteBall2);
			TextView tvBall3 = (TextView) convertView.findViewById(R.id.whiteBall3);
			TextView tvBall4 = (TextView) convertView.findViewById(R.id.whiteBall4);
			TextView tvBall5 = (TextView) convertView.findViewById(R.id.whiteBall5);
			TextView tvPowerBall = (TextView) convertView.findViewById(R.id.powerBall);
			//gets the remove button from the convertview enables it and sets it's listener
			Button bRemove=(Button) convertView.findViewById(R.id.removeButton);
			bRemove.setEnabled(true);
			bRemove.setOnClickListener(item.listener);
			// Populate the data into the template view using the data object
			tvBall1.setText(item.getNumber(0));
			tvBall2.setText(item.getNumber(1));
			tvBall3.setText(item.getNumber(2));
			tvBall4.setText(item.getNumber(3));
			tvBall5.setText(item.getNumber(4));
			tvPowerBall.setText(item.getNumber(5));
			
			bRemove.setTag(item.id);
			// Return the completed view to render on screen
			return convertView;
		}
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		
	}
}
