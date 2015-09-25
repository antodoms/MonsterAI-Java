package com.deterra.client;

import javax.swing.*;
import java.awt.*;


/**
 * Created by Daniel Bugeja on 25/09/2015.
 */


public class ClientLobby extends JPanel {

   /**
	 * 
	 */
private static final long serialVersionUID = 1L;
	
   private static  JLabel label;
   private static  JLabel msg;
   
   public ClientLobby(LayoutManager layout){
	   
	

	       

		// label 1
		label = new JLabel("Lobby", JLabel.CENTER);
		label.setLocation(100, 50);
		add(label);
		label.setSize(400, 400);
		label.setFont (label.getFont ().deriveFont (20.0f));
		
		msg = new JLabel("Waiting for", JLabel.CENTER);
		msg.setLocation(100, 100);
		add(msg);
		msg.setSize(400, 400);
		msg.setFont (msg.getFont ().deriveFont (20.0f));
		
		
		setBackground(Color.GRAY);
		
		
		

		//f.getContentPane().setBackground(Color.GRAY);
       setSize(900,900);			
       setLayout(null);  
       setVisible(true);  
   		    	
   	
	}
   
   public static void updateMsg(String Message){
    msg.setText(Message);
   }
   
   

	
}
