package com.example.minesweeper;

import java.util.ArrayList;
import java.util.HashMap;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class RecordBook extends Activity implements OnClickListener{
	ListView lvMain;
	Logic logic = new Logic();	
	String i,j;
	Button home;
	
	ArrayList <HashMap<String,String>> recBook = new ArrayList <HashMap<String,String>>();
	HashMap<String, String> hm;
	String NAME = "NAME";
	String TIME = "TIME";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_record_book);
		home = (Button)findViewById(R.id.home);
		home.setOnClickListener(this);
		Intent in= getIntent();
	    Bundle b = in.getExtras();

	        if(b!=null)
	        {
	           i =(String) b.get("name");
	           j =(String) b.get("time");
	        }
				
		hm = new HashMap<String, String>(); 
		hm.put(NAME, i);
		hm.put(TIME, j);
		recBook.add(hm);
		
		String from[] = {NAME,TIME};
		int to[] = {R.id.text1, R.id.text2};
		
		lvMain = (ListView) findViewById(R.id.list);
		SimpleAdapter adapter = new SimpleAdapter(this,recBook, R.layout.list,  from, to);

		lvMain.setAdapter(adapter);    
	}

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.home){
			Intent intent = new Intent(this, MainActivity.class);
		    startActivity(intent);
		}
		
	}

	
}
