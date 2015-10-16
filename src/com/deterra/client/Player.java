package com.deterra.client;

import java.rmi.RemoteException;


/**
 * Created by Tanya Srinidhi on 25/09/2015.
 * &
 * Created by Daniel Bugeja on 25/09/2015.
 */

public class Player  {

	private static String name;
	private String idNum;
	public static int posX;
	private static int posY;
	public static boolean isAlive;
	
	
	public Player(String name, String idNum){	
		this.name = name;
		this.idNum = idNum;
		this.isAlive = true;
	}
	
	public static void setPosition(int x , int y){
		
		posX = x;
		posY = y;
		
	}
	
	public static String getName(){
	
		return name;
	}
	
	public static void move(int x , int y) throws RemoteException{
		
		if(isAlive == true){
			
		System.out.println((posX + x) + " , " + (posY  + y) );	
		if(Client.stub.requestMove(Client.callbackObj, (posX + x ),(posY + y),posX,posY)){
			posX += x;
			posY += y;
		}
	}
	}
	
	public static void kill() throws RemoteException{
		
		if(isAlive == true){
		Client.stub.killPlayer(Client.callbackObj,posX,posY);
		}
		isAlive = false;
	}

	
}
	

