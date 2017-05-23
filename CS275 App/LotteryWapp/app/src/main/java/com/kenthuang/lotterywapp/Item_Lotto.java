package com.kenthuang.lotterywapp;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class Item_Lotto
{
	public int[] numbers;
	
	public Item_Lotto(int[] numbers)
	{
		this.numbers=numbers;
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
			// Populate the data into the template view using the data object
			tvBall1.setText(item.getNumber(0));
			tvBall2.setText(item.getNumber(1));
			tvBall3.setText(item.getNumber(2));
			tvBall4.setText(item.getNumber(3));
			tvBall5.setText(item.getNumber(4));
			tvPowerBall.setText(item.getNumber(5));
			// Return the completed view to render on screen
			return convertView;
		}
	}
}
