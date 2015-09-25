package com.deterra.client;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.deterra.common.ClientInterface;


public class ClientImpl extends UnicastRemoteObject implements ClientInterface {
  
	
	private static final long serialVersionUID = 1L;

	public ClientImpl() throws RemoteException {
      super( );
    }

	//message method to check callback
   public String notifyMe(String message){
      String returnMessage = "Call back received: " + message;
      System.out.println(returnMessage);
      return returnMessage;
   }

  //method prints to board values  
   public byte[][] printBoardClient(byte[][] serverboard) throws RemoteException{
	  
	   //loops though all board indices and prints value
		for(int i = 0; i < 11 ;i++){
			for(int j = 0; j < 11;j++){
				 System.out.print(serverboard[i][j]);
			}
			 System.out.println();
		}
		   
		   ClientBoard.board = serverboard;
		   Client.ui.refresh();
		
	  return serverboard;
   }
   
   public void getBoardServer(byte[][] serverboard) throws RemoteException{
		  
	 
	   System.out.println("Board Updated");
	   ClientBoard.board = serverboard;
	   Client.ui.refresh();
		
   }
   
   public void startGame() {	   
	   GUIHandler.swap("BOARD");
	   Client.gameRunning = true;
	   Client.game();
	   Client.ui.refresh();
   }
   
   public void updateLobby(String Message) {
	   ClientLobby.updateMsg(Message);
	   Client.ui.refresh();
   }


   public void updateBoard(byte[][] serverboard) throws RemoteException{	   
	   ClientBoard.board = serverboard;
	   Client.ui.refresh();
   }

}// close CallbackClientImpl 
