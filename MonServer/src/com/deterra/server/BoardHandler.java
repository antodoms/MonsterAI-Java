package com.deterra.server;

public class BoardHandler {

	//Initializes board
	private static byte[][] serverBoard = new byte[11][11]; 
	
	public BoardHandler(){
		
		//loops and assigns all board elements 
		for(int i = 0; i < 11;i++){
			for(int j = 0; j < 11;j++){
			
				serverBoard[i][j] = 1;
	
			}
		}
		
	}
	
	//sets board to passed in value for testing reasons
	public static byte[][] SetBoard(int value){
		
		//loops and assigns all board elements 
		for(int i = 0; i < 11;i++){
			for(int j = 0; j <11 ;j++){
				
				serverBoard[i][j] = (byte) value;
			}
		}
		return serverBoard;
	}
	
	//returns the board 
	public static byte[][] ReturnBoard(){
		return serverBoard;
	}


	
	
	
}
