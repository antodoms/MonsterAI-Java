package com.deterra.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.InputMismatchException;
import java.util.Scanner;

import common.ClientInterface;
import common.ServerInterface;
import com.deterra.server.RMIServer;


public class Client {
	
	//Initialization of static values 
	static ClientInterface callbackObj;
	static Player playerClient;
	static ServerInterface stub;
	static GUIHandler ui;
	static RMIServer server;
	
	
	public static boolean isHost = false;
	public static int players = 2;
	public static boolean gameRunning = false;
	
  public static void main(String args[]) {
	  
	 //menu loop and int input variables
	 boolean running = true;
	 int input = 0;
	
	   javax.swing.SwingUtilities.invokeLater(new Runnable() {
           public void run() {
           	ui = new GUIHandler();
           }
       });
	 
	/*
		
		//invoke unregister method on remote object	 
		 
			//try catch to test for success
			 try{
				 stub.unregisterForCallback(callbackObj);
				 System.out.println("Unregistered for callback.");
			 }
			 catch(Exception e){
				 System.out.println("Unregistered FAIL."); 
			 }
		 
		*/
	
 } //close main
  
//function to manage the game and initialization    
  public static void game(){
	  
	  //runs appropriate code once the server tells client to run game;
	  if(gameRunning == true){  
			
			//System.out.println("game running");
		  
		    //Creates player object if none exists and initializes values.
			if (playerClient == null){
				playerClient = new Player("steve","1");
				System.out.println("player made");
			}
			
			
	  }
  }
  public static void startServer(){
	  
	  if(server == null){
		  server = new RMIServer();
	  }
	  
  }
  
  //Connects to a server using the passed port and host name as an address.
  public static void serverConnect(String portNum, String hostName, String playerName){
	  
	 try{
	  int RMIPort = Integer.parseInt(portNum);
	   
	  //defines address for stub for remote object
	   String registryURL = "rmi://"+ hostName + ":" + RMIPort + "/callback";  
	   
	   // find the remote object and cast it to an interface object
	   stub = (ServerInterface)Naming.lookup(registryURL);
	   
	   // print remote object lookup completion, hello test handshake
	   System.out.println("Lookup completed " );

	 
	   //initialize callback object
	   callbackObj = new ClientImpl();
	   
	 
	   if(isHost == true){
		   System.out.println(stub.lobbyMsgUpdate(players));
	   }
	   
	   
	   //register for callback
	   stub.registerForCallback(callbackObj,playerName);
	   
	   
	   ClientBoard.board = stub.getServerBoard();
	
	   System.out.println("Registered for callback.");

	  
	   ui.refresh();
	   
   	} // close try 
   catch (Exception e) {
	   
	  
	   System.out.println("Exception in CallbackClient: " + e);


} // close catch

  }
  
  //Utilities for gathering integer input
  
	static Scanner scanner = new Scanner(System.in);
	
	
	public static int getIntInput(int output)
	{
	
		boolean complete = false;
		do {
			try
			{
				output = scanner.nextInt();
				System.out.println();
				complete = true;
			}
			catch(InputMismatchException ime)
			{
				System.out.println("error-mismatch");
			}
			
		}while (!complete);{
			return output;
		}
	}

}//close class