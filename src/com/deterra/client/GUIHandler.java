package com.deterra.client;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.Box.Filler;
import javax.swing.BoxLayout;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUIHandler extends JFrame{
	
//JPanel cards;
//final static String BUTTONPANEL = "Card with JButtons";
//final static String TEXTPANEL = "Card with JTextField";
//GUI gui;
//ClientBoard clientboard;

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
public  CardLayout cardLayout;
public static JPanel cardContainer;
//public  JComboBox comboBox;
public static  JPanel gui;


public GUIHandler(){
	
	super();
	setTitle("Mon Game");
	
	
	cardLayout = new CardLayout();
	cardContainer = new JPanel(cardLayout);
	

	getContentPane().add(cardContainer, BorderLayout.CENTER);
	
	
	
	debug();
	
	setSize(650, 700);
	setResizable(false);
	setLocationRelativeTo(null);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setVisible(true);
	
	
	
}

public void debug(){

	
	
	JPanel titleP = new JPanel();
	titleP.setLayout(new BoxLayout(titleP, BoxLayout.Y_AXIS));

	titleP.setOpaque(false);
	String title = "Title Panel";
	cardContainer.add(titleP,title);
	
	
	
	Filler filler = new Box.Filler(new Dimension(140,100), new Dimension(140,100), new Dimension(140,100));
	titleP.add(filler);
	
	final JButton b = new JButton("Host Game");
	titleP.add(b);  
	b.setPreferredSize(new Dimension(200, 100));
	//b.setLocation(100, 100);
	//b.setAlignmentX(Component.CENTER_ALIGNMENT);
	b.setAlignmentY(Component.BOTTOM_ALIGNMENT);
	
	final JButton b2 = new JButton("Join Game");
	titleP.add(b2);  
	b2.setPreferredSize(new Dimension(200, 100));
	//b2.setAlignmentX(Component.CENTER_ALIGNMENT);
	b2.setAlignmentY(Component.BOTTOM_ALIGNMENT); 
	  
	JPanel board = new ClientBoard(cardLayout);
	
	board.setOpaque(true);
	//board.setBackground(Color.BLUE);
	cardContainer.add(board,"BOARD");
	//comboBox.addItem("BOARD");
	
  
	JPanel lobby = new ClientLobby(cardLayout);
	
	lobby.setOpaque(true);
	
		cardContainer.add(lobby,"LOBBY");
	
	
	cardLayout.show(cardContainer, "titleP");
	
	b.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			Client.isHost = true;
			if (gui == null){
				Client.startServer();
			    gui = new GUI(cardLayout);
				gui.setOpaque(true);
				cardContainer.add(gui,"GUI");
			}
			
			swap("GUI");
			
		}
	});
	
	b2.addActionListener(new ActionListener()
	{
		public void actionPerformed(ActionEvent e)
		{
			
			Client.isHost = false;
			if (gui == null){
			    gui = new GUI(cardLayout);
				gui.setOpaque(true);
				cardContainer.add(gui,"GUI");
			}
			swap("GUI");
			
		}
	});
	
	
}

public void refresh(){
	repaint();
}


public static void swap(String pane){
	  CardLayout cl = (CardLayout)(cardContainer.getLayout());
	    cl.show(cardContainer, pane);
	    System.out.println(pane);
	   
}


}
