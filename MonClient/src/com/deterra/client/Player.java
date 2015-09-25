package com.deterra.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;


/**
 * Created by Tanya Srinidhi on 25/09/2015.
 * &
 * Created by Daniel Bugeja on 25/09/2015.
 */

public class Player implements KeyListener {

	private String name;
	private String idNum;
	private int posX;
	private int posY;
	
	public Player(String name, int posX, int posY, String idNum){
		
		this.name = name;
		this.posX = posX;
		this.posY = posY;
		this.idNum = idNum;
		
	}
	
	public void setPostion(int x , int y){
		
		posX = x;
		posY = y;
		
	}
	
	public void move(int x , int y) throws RemoteException{
		
		Client.stub.requestMove(Client.callbackObj, x ,y);	
		
	}
	
	public void checkInput(){
		
		
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		try{
		        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
		           //move(posX+1,posY);
		           System.out.println("key pressed");
		        }
		        
		        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			          // move(posX-1,posY);
			        }
		        
		        if (e.getKeyCode() == KeyEvent.VK_UP) {
			           //move(posX,posY+1);
			        }
		        
		        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			          // move(posX,posY-1);
			        }
		}catch(Exception e1){
			
		}

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
	

