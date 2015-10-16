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
   private static  JLabel[] playerLabel = new JLabel[4];
   private static JButton[] postionButtons = new JButton[4];
   private int[] pos = new int[] {0,1,0,1};
   
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
		
		for(int i = 0; i < 4; i++){
			playerLabel[i] = new JLabel(" ",JLabel.CENTER );
			playerLabel[i].setForeground(Color.CYAN);
			playerLabel[i].setLocation(100, 150 + (50 * i));
			add(playerLabel[i]);
			playerLabel[i].setSize(400, 400);
			playerLabel[i].setFont (msg.getFont ().deriveFont (20.0f));
			
			postionButtons[i] = new JButton();
        	postionButtons[i].setText( String.valueOf(i+1));
        	postionButtons[i].setBounds(100+(100 * i),600 ,100, 40);
			add(postionButtons[i]);  
		}
	
		
		setBackground(Color.GRAY);
		showButtons(pos);
		
		

		//f.getContentPane().setBackground(Color.GRAY);
       setSize(900,900);			
       setLayout(null);  
       setVisible(true);  
   		    	
   	
	}
   
   public static void updateMsg(String Message,String[] playerList){
    msg.setText(Message);
    for(int i = 0; i < 4; i++){
    	if(playerList[i] != null)
    	  playerLabel[i].setText(playerList[i]);
    	else
    	  playerLabel[i].setText("waiting");
    }
   }
   
 
    
    public static void showButtons(int[] buttons){
     
        for(int i = 0; i < 4; i++){
        	if(buttons[i] == 0){
        		postionButtons[i].setVisible(false);
        	}
       }
  
   }
   
   

	
}
