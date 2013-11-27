package com.orasystems.libs.utils.log.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.fima.cardsui.objects.Card;
import com.orasystems.libs.utils.R;

public class MyCard extends Card {

	private String description = "";
	public MyCard(String title,String description){
		super(title);
		this.description = description;
	}

	@Override
	public View getCardContent(Context context) {
		View view = LayoutInflater.from(context).inflate(R.layout.card_ex, null);

		((TextView) view.findViewById(R.id.title)).setText(title);
		((TextView) view.findViewById(R.id.description)).setText(description);

		
		return view;
	}

	
	
	
}