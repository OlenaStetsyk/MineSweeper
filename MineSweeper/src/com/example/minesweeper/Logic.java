package com.example.minesweeper;

import java.util.Random;

public class Logic {
	int MainFront[][] = new int[400][2];
	int MFSize;
	int arr[][] = new int[9][9];
	String _name, _time;
	
	public Logic() {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
				arr[i][j] = 0;
		
	}
	
	public void fillRecord(String name, String time){
		_name = name; 
		_time = time;
	}
	
	public void getWave(int i0, int j0) {
		int i1 = 0;
		int j1 = 0;
		int i2 = 0;
		int j2 = 0;
		
		int nOldFront, nNewFront;
		int NewFront[][] = new int[100][2];
		int OldFront[][] = new int[100][2];
		int alpha[][] = {{-1, 0,},{0, 1,},{1, 0,},{0, -1,},{-1, -1,},{-1, 1,},{1, -1,},{1, 1,}};
		int isContinue = 1;
		MFSize = -1;
		OldFront[0][0] = i0;
		OldFront[0][1] = j0;
		nOldFront = 0;
		
		
		while(isContinue == 1) {
			isContinue = 0;
			nNewFront = -1;
			arr[i0][j0] = 9;
			for(int i = 0; i <= nOldFront; i++) {
				for(int j = 0; j < 4; j++) {
					i1 = OldFront[i][0] + alpha[j][0];
					j1 = OldFront[i][1] + alpha[j][1];
					if(i1 >= 0 && j1 >= 0 && i1 < 9 && j1 < 9){
							if(arr[i1][j1] == 0){
								arr[i1][j1] = 9;
								isContinue = 1;
								nNewFront ++;
								NewFront[nNewFront][0] = i1;
								NewFront[nNewFront][1] = j1;
								MFSize++;
								MainFront[MFSize][0] = i1;
								MainFront[MFSize][1] = j1;
								System.out.println("MFS= " + MFSize+ "  " + MainFront[MFSize][0] + " " + MainFront[MFSize][1]);								
							}
						}
					}
				}
			
				
			for(int i = 0; i <= nNewFront; i++) {
				OldFront[i][0] = NewFront[i][0];
				OldFront[i][1] = NewFront[i][1];
			}
			nOldFront = nNewFront;
		}
		
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++) 
				if(arr[i][j] == 9)
					arr[i][j] = 0;
		
		int size = MFSize;
		for(int i = 0; i <= MFSize; i++) 
			for(int j = 0; j < 8; j++) {
				i2 = MainFront[i][0] + alpha[j][0];
				j2 = MainFront[i][1] + alpha[j][1];
				if(i2 >= 0 && j2 >= 0 && i2 < 9 && j2 < 9){
					if(arr[i2][j2] > 0){
						size++;
						MainFront[size][0] = i2;
						MainFront[size][1] = j2;
						System.out.println("MFSize2= " + size + MainFront[size][0] + " " + MainFront[size][1]);
					}
				}
			}
		
		MFSize = size;
			
		}

	
	
	public void generBomb(int iCell, int jCell) {
		int icell  = iCell;
		int jcell = jCell;
		
		Random rnd = new Random();
		int bombNumber = 0;
		while(bombNumber < 5){
			int i = rnd.nextInt(9);
			int j = rnd.nextInt(9);
			if(arr[i][j] != -1){	
				if(Math.abs(i-icell) + Math.abs(j-jcell)>2){	
					arr[i][j] = -1;
					bombNumber++;
				}
			}
		}
	}
	
	public void generFieldWeight(int iCell, int jCell) {
		int delta[][] = {{-1, -1,},{-1, 0,},{-1, 1,},{0, 1,},{1, 1,},{1, 0,},{1, -1,},{0, -1,}};
		int i1 = 0;
		int j1 = 0;
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				if(arr[i][j] == -1)
				{	
					for(int k = 0; k < 8; k++)
					{
						i1 = i + delta[k][0];
						j1 = j + delta[k][1];
						if(i1 >= 0 && j1 >= 0 && i1 < 9 && j1 < 9)
						{
							if(arr[i1][j1] != -1)
								arr[i1][j1]++;
						}
					}
				}
			}
	}
	
	public void cleanField() {
		for(int i = 0; i < 9; i++)
			for(int j = 0; j < 9; j++)
			{
				arr[i][j] = 0;
			}

	}
}
