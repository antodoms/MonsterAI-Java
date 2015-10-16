package com.deterra.server;
import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import common.ClientInterface;
import common.ServerInterface;
import common.Utilities;

import AI.AIBoard;
import AI.MonsterAI;



public class ServerImpl extends UnicastRemoteObject implements ServerInterface {

	private static final long serialVersionUID = 1L;
	
	//creates vector to store all clients
	private Vector<ClientInterface> clientList;

	//creates board handler variable;
	private BoardHandler boardhandler;
	
	//varibles to track maximum players and amount still alive
	private int maxPlayers;
	private boolean[] playerAlive = new boolean[] {false,false,false,false};
	private String[] playerNames = new String[4];
	private int[] playerPos = new int[4];
	private int t;
    
   public ServerImpl() throws RemoteException {
	   
      super( );
      clientList = new Vector<ClientInterface>();
      
   }
 
 
   //registers clients if not already contained in list and runs a callback
   public synchronized void registerForCallback(ClientInterface callbackClientObject, String passedName)
      throws java.rmi.RemoteException{
	  
      // store client callback object in vector
      if (!(clientList.contains(callbackClientObject))) {clientList.addElement(callbackClientObject);
      
      // converts vector back to callback object
   
      System.out.println("Registered new client ");
      
      playerNames[clientList.size() - 1] = passedName;
    

      if(checkMaxPlayers()){
	 
    	  for (int i = 0; i < clientList.size(); i++){
  	    	
  	    	
  	    	// converts vector back to callback object
  	    	ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
  		      
  	    	
  			nextClient.updateLobby("Game Starts in 5 seconds", playerNames);
  		
  		}// close for
  		    
    	  
Timer timer = new Timer();

timer.schedule(new TimerTask() {
		
	@Override
	public void run() {
		
		try {
			  setupGame();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	}, 5000);

	
  }
      
      
      
    //runs a callback method
      setupCallback();
      
      
     } 
  }  

 
  
  //removes the client callback object from  vector otherwise returns fail message
  public synchronized void unregisterForCallback( ClientInterface callbackClientObject) 
      throws java.rmi.RemoteException{
	  
	  
	  //remove callback object condition
      if (clientList.removeElement(callbackClientObject)) {
    	  
    	 System.out.println("Unregistered client ");
    	 
      }else{
    	  
         System.out.println(
         "Unregister Warning: client wasn't registered.");
      }
      
      
  } 

  public synchronized boolean requestMove(ClientInterface callbackClientObject, int x , int y, int oldx, int oldy)
  	throws  java.rmi.RemoteException{
	  
	  
	  if (x >= 0 && x < 11){
		 if (y >= 0 && y < 11){
			   
			if (BoardHandler.getBoardValue(x,y) == 1) {
				
			    for (int i = 0; i < clientList.size(); i++){
			    	 
				    ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
				  
				    if (nextClient.equals(callbackClientObject)){
					  
					   BoardHandler.SetSingleBoard(oldx,oldy,1);
					   BoardHandler.SetSingleBoard(x,y,i+3);
					   updatePush();
					   return true;
				   }
			    }
			 }	
		  }
	  }
	  return false;
	  
	  
  }
  
  
//make a callback to each individual registered client in vector
  private synchronized void setupCallback( ) throws java.rmi.RemoteException{
    
	  
     for (int i = 0; i < clientList.size(); i++){
    	
        // converts vector back to callback object
        ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
      
        // invokes callback to message client
        nextClient.notifyMe("Number of registered clients="+  clientList.size());
        
        if(maxPlayers - clientList.size() != 0)
        	nextClient.updateLobby("waiting on - " + (maxPlayers - clientList.size()) + " players",playerNames);
        
        //board handler not declared, it is and board is passed to client
        if(boardhandler == null){
           boardhandler = new BoardHandler(); 
        }
        
        
        if(i == 0){
     	   BoardHandler.SetSingleBoard(0,0,3);
     	   nextClient.setPlayerPos(0,0);
        }
        if(i == 1){
            BoardHandler.SetSingleBoard(0,10,4);
            nextClient.setPlayerPos(0,10);
        }   
        if(i == 2){
            BoardHandler.SetSingleBoard(10,0,5);
            nextClient.setPlayerPos(10,0);
        }  
        if(i == 3){
            BoardHandler.SetSingleBoard(10,10,6);
            nextClient.setPlayerPos(10,10);
        }
         
        nextClient.updateBoard(BoardHandler.ReturnBoard());
        
     }// close for
   
    
     
     
  } // close callbacks

  

public String lobbyMsgUpdate(int players){
	
	maxPlayers = players;
	
	for(int i = (maxPlayers); i < 4; i++){
		System.out.println("blank");
		playerNames[i] = " ";
	}
	
	if(clientList.size() < players ){
		return("waiting on - " + (maxPlayers - clientList.size()) + " players");
	}
	
	return null;

}

public String playerListMsgUpdate(int players){
	
	maxPlayers = players;
	
	
	
	return null;


}


public boolean checkMaxPlayers()  throws java.rmi.RemoteException{
	
	if(clientList.size() < maxPlayers ){
		return false;
	}

	return true;
	
}

public synchronized void setupGame() throws java.rmi.RemoteException{
	
   
    for (int i = 0; i < clientList.size(); i++){
    	
    	playerAlive[i] = true;
    	
    	// converts vector back to callback object
    	ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
	      
    	nextClient.startGame();      
    	nextClient.updateBoard(BoardHandler.ReturnBoard());
    	
    	
	}// close for
     
    Runnable helloRunnable = new Runnable() {
    	int[] aiMove = new int[4];
        public void run() {
        	AI.MonsterAI ab = new MonsterAI();
       	 aiMove = ab.runmonster(BoardHandler.ReturnBoard());
         //aiMove = ab.displaypoint(finalpath,n);
         
       	 System.out.println(aiMove[0]+"  " +aiMove[1] + " "+ aiMove[2] +  " " + aiMove[3]);
         BoardHandler.SetSingleBoard(aiMove[0],aiMove[1],1);
         BoardHandler.SetSingleBoard(aiMove[2],aiMove[3],2);
         
   	   	 try {
			updatePush();
   	   	 } catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        }
    };

    ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
    executor.scheduleAtFixedRate(helloRunnable, 0, 1, TimeUnit.SECONDS);
  
}

	    

  
  
 public synchronized void updatePush()  throws java.rmi.RemoteException{
	  
	 for (int i = 0; i < clientList.size(); i++){
	    	
	    ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
	        
	    nextClient.updateBoard(BoardHandler.ReturnBoard());
	 }
	  
 }

public synchronized void killPlayer(ClientInterface callbackClientObject, int x, int y)  
		throws java.rmi.RemoteException{
	  
	 for (int i = 0; i < clientList.size(); i++){
		 
		  ClientInterface nextClient = (ClientInterface)clientList.elementAt(i);
		  
		  if (nextClient.equals(callbackClientObject)){
			  playerAlive[i] = false;
			  BoardHandler.SetSingleBoard(x,y,1);
			  updatePush();
		  }
	  }
	 
	 if(lastManCheck()){System.out.println("win condition met");}
	  
}


@Override
public byte[][] getServerBoard() throws RemoteException {
	return BoardHandler.ReturnBoard();
}


public boolean lastManCheck(){
	
	int livingCount = 0;
	
	for (int i = 0; i < clientList.size(); i++){
		 if (playerAlive[i] == true)
			 livingCount++;
	}
	
	if(livingCount <= 1 ){
		return true;
	}
	
	return false;
}


}// close CallbackServerImpl class   