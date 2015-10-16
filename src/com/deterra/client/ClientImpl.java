package com.deterra.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import common.ClientInterface;


public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
  
	private static final long serialVersionUID = 1L;

	public ClientImpl() throws RemoteException {
      super( );
    }

	
	//message method to check callbacks and print msg to client
   public String notifyMe(String message){
	   
      String returnMessage = "Call back received: " + message;
      System.out.println(returnMessage);
      
      return returnMessage;
   }

   
   //sets the gui to the board and sets and runs game startup code
   public void startGame() {	   
	   GUIHandler.swap("BOARD");
	   ClientBoard.focus();
	   Client.gameRunning = true;
	   Client.game();
	   Client.ui.refresh();
   }
   
   //updates the msg on the lobby gui screen
   public void updateLobby(String Message, String[] playerList) {
	   ClientLobby.updateMsg(Message,playerList);
	   Client.ui.refresh();
   }

   //updates the board and synchs with servers board
   public void updateBoard(byte[][] serverboard) throws RemoteException{	   
	   ClientBoard.board = serverboard;
	   Client.ui.refresh();
   }

   //sets the position variables in the player class
   public void setPlayerPos(int i, int j) throws RemoteException {
	
	   Player.setPosition(i, j);
	
   }


}// close CallbackClientImpl 
