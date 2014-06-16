package com.example.minesweeper;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PlayField extends Activity implements OnCheckedChangeListener, OnClickListener, OnLongClickListener{
	boolean flag = true;
	Button btn;
	Logic logic = new Logic();
	TextView bomb_count;
	boolean isChon = false;
	int iCell = 0;
	int isBomb = 5;
	int jCell = 0;
	ToggleButton smile;
	Chronometer chronometer;
	Button arr_but[][] = new Button[10][10];
	int i, j, k;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_play_field);
		isChon = true;
		
		chronometer = (Chronometer) findViewById(R.id.chronometer1);
		
		bomb_count = (TextView)findViewById(R.id.bomb_count);
		bomb_count.setText("" + isBomb);
				
		Display display = getWindowManager().getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		int width = size.x;
		int height = size.y;
		btn = (Button)findViewById(R.id.b1);
		btn.setOnClickListener(this);				
		smile = (ToggleButton) findViewById(R.id.image_smile);
		smile.setOnCheckedChangeListener(this);
		smile.setBackgroundResource(R.drawable.smile);
		smile.setText(" ");
		smile.setTextOff(" ");
		smile.setTextOn(" ");
		
		TableLayout tableLayout =(TableLayout)findViewById(R.id.table1);
		TableLayout.LayoutParams tableparams = new TableLayout.LayoutParams(width/2, height/2);
		TableRow.LayoutParams params = new TableRow.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
        
		
		if(width > height){
			params.height = height/15;
	        params.width = width/10;
		}
		params.height = height/15;
        params.width = width/10;
        params.leftMargin = 1;
        params.rightMargin = 1;
        params.topMargin = 2;
        
       	k = 0;
        for(i = 0; i < 9; i++)
        {
        	TableRow tablerow = new TableRow(this);
            tableLayout.addView(tablerow);
        	for(j = 0; j < 9; j++)
        	{
        		 k++;
        		 Button btn = new Button(this);
        		 btn.setText(" ");
        		 btn.setOnClickListener(this);
        		 btn.setOnLongClickListener(this);
        		 arr_but[i][j] = btn;
        		 btn.setBackgroundResource(R.drawable.image);
        		 tablerow.addView(btn,params);
        		 
        	}
        }
       
	}

	public void openOneCell(int i,int j) {
		switch(logic.arr[i][j]) {
		case 0: arr_but[i][j].setBackgroundResource(R.drawable.empty_image); break;
		case 1: arr_but[i][j].setBackgroundResource(R.drawable.image_one); break;
		case 2: arr_but[i][j].setBackgroundResource(R.drawable.image_two); break;
		case 3: arr_but[i][j].setBackgroundResource(R.drawable.image_three); break;
		case -1: arr_but[i][j].setBackgroundResource(R.drawable.bomb);break;
		case 100: arr_but[i][j].setBackgroundResource(R.drawable.flag);break;
	}
		arr_but[i][j].setEnabled(false);
	}
	
	 public void onSaveInstanceState(Bundle outState) {
	        super.onSaveInstanceState(outState);
	        int butR[] = new int[100];
	        for(int i = 0; i <= 8; i++){
	        	for(int j = 0; j <= 8; j++){
	        		butR[(i*10)+j] = logic.arr[i][j];
	          	}
	        }
	        outState.putIntArray("butR", butR);
	        
	    }
	 protected void onRestoreInstanceState(Bundle savedInstanceState) {
	        super.onRestoreInstanceState(savedInstanceState);
	        int butR[] = savedInstanceState.getIntArray("butR");
	        for(int i = 0; i <= 8; i++){
	        	for(int j = 0; j <= 8; j++){
	        		logic.arr[i][j] = butR[(i*10)+j];
	        	}
	        }
	       
	        
	        
	 }
	 

		@Override
		public boolean onLongClick(View v) {
			int i0 = 0;
			int j0 = 0;
			for(i = 0; i < 9; i++)
	        	for(j = 0; j < 9; j++){
	        		if (arr_but[i][j] == v) {
	        			i0 = i;
	        			j0 = j;		
	        		}
	        	}
			if(logic.arr[i0][j0] < 99)
			{
				logic.arr[i0][j0] = logic.arr[i0][j0] + 100;
				arr_but[i0][j0].setBackgroundResource(R.drawable.flag);
				if(logic.arr[i0][j0] == 99){
					isBomb--;
					bomb_count.setText("" + isBomb);
				}
			}
			else
			{
				logic.arr[i0][j0] = logic.arr[i0][j0] - 100;
				System.out.println("syso " + logic.arr[i0][j0] );
				arr_but[i0][j0].setBackgroundResource(R.drawable.image);
				if(logic.arr[i0][j0] == -1){
					isBomb++;
					bomb_count.setText("" + isBomb);
				}
			}
			
			
			if(isBomb == 0){
				chronometer.stop();
				final String chronoText = chronometer.getText().toString();
			    AlertDialog.Builder alert = new AlertDialog.Builder(this);
	        		alert.setMessage("Введите имя");
	        		final EditText input = new EditText(this);
	        		alert.setView(input);
	        		alert.setPositiveButton("ОК", new DialogInterface.OnClickListener() {
	        		public void onClick(DialogInterface dialog, int whichButton) {
	        				String name = input.getText().toString();
	        				String Smin = chronoText.substring(0, 2);
	        				String Ssec = chronoText.substring(3);
	        				int min = Integer.parseInt(Smin);
	        				int sec = Integer.parseInt(Ssec);
	        				int time = (min*60) + sec;
	        				String t = ""+time;
	        				logic.fillRecord(name, t);
	        				System.out.println("syso " + logic._name + logic._time);
	        			  }
	        			});
	        		
	        			alert.setNegativeButton("Нет", new DialogInterface.OnClickListener() {
	        			  public void onClick(DialogInterface dialog, int whichButton) {
	        				  dialog.cancel();
	        			  }
	        			});
	        		alert.show();
			}
		return true;
		}
		
	
	 @Override
		public void onClick(View v) {
		 for(i = 0; i < 9; i++)
	        	for(j = 0; j < 9; j++){
	        		if (arr_but[i][j] == v) {
	        			iCell = i;
	        			jCell = j;
	        			if(flag == true){
	        				if(isChon == true)
	        				{
	        					chronometer.start();
	        					isChon = false;
	        				}
	        				logic.generBomb(iCell, jCell);
	        				logic.generFieldWeight(iCell, jCell);
	        				flag = false;
	        			}
	        			
	        			openOneCell(iCell, jCell);
	        	        if(logic.arr[iCell][jCell] == 0){
	        	        	logic.getWave(iCell, jCell);
	        	        	for(int h = 0; h <= logic.MFSize; h++)
	        	        		openOneCell(logic.MainFront[h][0], logic.MainFront[h][1]);
	        	        			
	        	        }

	        			
	        			
	        			if(logic.arr[i][j] == -1) {
	        				 smile.setBackgroundResource(R.drawable.sad_smile);
	        	        		for(i = 0; i < 9; i++)
	        	        			for(j = 0; j < 9; j++)
	        	        				if (logic.arr[i][j] == -1)
	        	        					arr_but[i][j].setBackgroundResource(R.drawable.bomb); 
	        	        	
	        	        }
	        		}
	         	}
		 if(v.getId() == R.id.b1)
			{
				Intent intent = new Intent(this, RecordBook.class);
				intent.putExtra("name",logic._name);
				intent.putExtra("time",logic._time);
			    startActivity(intent);
			    
			}
		}
	 
	 
	 
	 
	
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
	int i,j;
	if(buttonView.getId() == R.id.image_smile){
        	smile.setBackgroundResource(R.drawable.smile);
			chronometer.setBase(SystemClock.elapsedRealtime());
			isBomb = 5;
			bomb_count.setText(""+isBomb);
			for(i = 0; i < 9; i++)
	        	for(j = 0; j < 9; j++){
	        		logic.cleanField();
	        		arr_but[i][j].setBackgroundResource(R.drawable.image);
	        		arr_but[i][j].setEnabled(true);
	        		flag = true;
	    		}
        }
		
	}


	

	

	

}
