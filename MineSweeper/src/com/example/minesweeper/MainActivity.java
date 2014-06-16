package com.example.minesweeper;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



public class MainActivity extends Activity implements OnClickListener{
	
	Button btn1,btn2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		btn1 = (Button)findViewById(R.id.button1);
		btn1.setOnClickListener(this);
		
		
	}

	

	@Override
	public void onClick(View v) {
		
		if(v.getId() == R.id.button1)
		{
			Intent intent = new Intent(this, PlayField.class);
		    startActivity(intent);
		 
		}
		
		
	}

}
