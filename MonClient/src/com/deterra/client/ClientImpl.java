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
   public byte[][] getBoardClient(byte[][] board) throws RemoteException{
	  
	   //loops though all board indices and prints value
		for(int i = 0; i < 11 ;i++){
			for(int j = 0; j < 11;j++){
				 System.out.print(board[i][j]);
			}
			 System.out.println();
		}
			
	  
	  return board;
   }


}// close CallbackClientImpl 