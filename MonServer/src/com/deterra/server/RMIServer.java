package com.deterra.server;
import java.rmi.*;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.io.*;



public class RMIServer  {
	
  public static void main(String args[]) {
	
	//Initialize readers for string input
    InputStreamReader is = new InputStreamReader(System.in);
    BufferedReader br = new BufferedReader(is);
    
	//Initialize port number and registry address variables
    String portNum, registryURL;
    
    //Try to get input, create and bind server to remote object
    try{  
    	
      //Prompt and get of server port number default 1099
      System.out.println("Enter the port number:");
      portNum = (br.readLine()).trim();
      
      //parse port string to int
      int RMIPortNum = Integer.parseInt(portNum);
      
 
      //creates registry on selected port
      startRegistry(RMIPortNum); 
      
      //creates remote object to be bound
      ServerImpl exportedObj = new ServerImpl();
 
      //sets registry address
      registryURL =  "rmi://127.0.0.1:" + RMIPortNum + "/callback";
      
      //binds the remote object to registry
      Naming.rebind(registryURL, exportedObj);
      
      //print server status and registry address
      System.out.println("Server is ready." + registryURL);
     
    }// close try
    
    catch (Exception e) {
    
      //catch server setup exceptions
      System.out.println(
        "Exception in Server.main: " + e);
      
    } // close catch
  } // close main

  
  //starts a RMI registry 
  private static void startRegistry(int RMIPortNum) throws RemoteException{
	  
    try {
    
     //gets registry 
      Registry registry = LocateRegistry.getRegistry(RMIPortNum);
      
   // if the registry does not already exist it will throw an exception
      registry.list( );
          
    }
    catch (RemoteException e) {
    	
     // creates registry if none exists
      Registry registry = LocateRegistry.createRegistry(RMIPortNum);
      
    }
    
  } // close startRegistry


} // close class