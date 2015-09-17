package com.deterra.server;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Vector;

import com.deterra.common.ClientInterface;
import com.deterra.common.ServerInterface;



public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

 
	private static final long serialVersionUID = 1L;
	
	//creates vector to store all clients
	private Vector<ClientInterface> clientList;

	//creates board handler variable;
	private BoardHandler boardhandler;


   public ServerImpl() throws RemoteException {
	   
      super( );
      clientList = new Vector<ClientInterface>();
   }

   //message method to check information passing
   public String sayHello( )   
	  throws java.rmi.RemoteException {
      return("hello");
   }
  
 
  //registers clients if not already contained in list and runs a callback
  public synchronized void registerForCallback(ClientInterface callbackClientObject)
      throws java.rmi.RemoteException{
	  
      // store client callback object in vector
      if (!(clientList.contains(callbackClientObject))) {clientList.addElement(callbackClientObject);
      
      System.out.println("Registered new client ");
      
      //runs a callback method 
      doCallbacks();
     } // close if
  }  

  //removes the client callback object from  vector otherwise returns fail message
  public synchronized void unregisterForCallback( ClientInterface callbackClientObject) 
    throws java.rmi.RemoteException{
	  
	//remove callback object condition
    if (clientList.removeElement(callbackClientObject)) {
    	System.out.println("Unregistered client ");
    } else {
    	
       System.out.println(
         "Unregister Warning: client wasn't registered.");
    }
  } 

  
//make a callback to each individual registered client in vector
  private synchronized void doCallbacks( ) throws java.rmi.RemoteException{
    
    for (int i = 0; i < clientList.size(); i++){
    	
      // converts vector back to callback object
      ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
      
      // invokes callback to message client
        nextClient.notifyMe("Number of registered clients="+  clientList.size());
 
        //board handler not declared, it is and board is passed to client
        if(boardhandler == null){
            
           boardhandler = new BoardHandler(); 
           BoardHandler.SetBoard(clientList.size());
           nextClient.getBoardClient(BoardHandler.ReturnBoard());
           }
           
           else
           {
        	 BoardHandler.SetBoard(clientList.size());
        	   nextClient.getBoardClient(BoardHandler.ReturnBoard());
           }
       
    }// close for
    
  
    
    System.out.println("********************************\n" +
                       "Server completed callbacks ---");
   
  } // close callbacks
  
  
  /*
  public synchronized void updatePush()  throws java.rmi.RemoteException{
	  
	    for (int i = 0; i < clientList.size(); i++){
	    	
	        ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
	        
	        if(boardhandler == null){
	            
	            boardhandler = new BoardHandler(); 
	           BoardHandler.SetBoard(clientList.size());
	           nextClient.getBoardClient(BoardHandler.ReturnBoard());
	           }
	           
	           else
	           {
	        	 BoardHandler.SetBoard(clientList.size());
	        	   nextClient.getBoardClient(BoardHandler.ReturnBoard());
	           }
	    }
	  
	}
*/
  
}// close CallbackServerImpl class   