package com.deterra.client;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.Naming;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.deterra.common.ClientInterface;
import com.deterra.common.ServerInterface;


public class Client {
	
	//Initialization of static values 
	static ClientInterface callbackObj;
	static ClientBoard cb;
	static ServerInterface stub;

	
  public static void main(String args[]) {
	  
	 //menu loop and int input variables
	 boolean running = true;
	 int input = 0;
	
	 

	//Menu Loop
	while(running == true){  
		
		 //Print menu
		 System.out.println("          MENU          ");
		 System.out.println("------------------------");
		 System.out.println("1.Connect to server     ");
		 System.out.println("2.Disconnect from server");
		 System.out.println("3.Currently nothing     ");
		 System.out.println("------------------------");
		 System.out.print("Choice:");
		 
		//Switch gets users input from input method
		switch((getIntInput(input))){
	
		
			//connection to server case
			case 1: 
	  
				   try {
    	
					   //Initialize readers for string input
					   InputStreamReader is = new InputStreamReader(System.in);
					   BufferedReader br = new BufferedReader(is);
      
					   //Prompt and get of host name (ip address)
					   System.out.println("Enter the RMIRegistry host name:");
					   String hostName = br.readLine();
      
					   //Prompt and get of server port number default 1099
					   System.out.println("Enter the RMIregistry port number:");
					   String portNum = br.readLine();
					   
					   //parse port string to int
					   int RMIPort = Integer.parseInt(portNum);
					   
					  //defines address for stub for remote object
					   String registryURL = "rmi://"+ hostName + ":" + RMIPort + "/callback";  
					   
					   // find the remote object and cast it to an interface object
					   stub = (ServerInterface)Naming.lookup(registryURL);
					   
					   // print remote object lookup completion, hello test handshake
					   System.out.println("Lookup completed " );
					   System.out.println("Server said " + stub.sayHello());
					 
					   //initialize callback object
					   callbackObj = new ClientImpl();
					   
					    cb = new ClientBoard();
					   
					    cb.drawBoard();
					    
					   //register for callback
					   stub.registerForCallback(callbackObj);
					   
					   ClientBoard.board = stub.getClientBoard();
					   cb.paintUpdate();
					   
					   System.out.println("Registered for callback.");
  
					  
					   
					   
				   	} // close try 
				   catch (Exception e) {
					   
					  
					   System.out.println("Exception in CallbackClient: " + e);
		
		
		} // close catch
   
			  break;
		
		//invoke unregister method on remote object	  
		case 2:
		 
			//try catch to test for success
			 try{
				 stub.unregisterForCallback(callbackObj);
				 System.out.println("Unregistered for callback.");
			 }
			 catch(Exception e){
				 System.out.println("Unregistered FAIL."); 
			 }
		 
			 break;
		 
		default:
		
		}// close switch
	 }// close while loop
 } //close main
  
  
  
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