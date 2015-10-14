package com.deterra.client;

	import java.awt.*;
	import java.awt.event.*;

	import javax.swing.*;

	public class GUI extends JPanel{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		

		int val;
		public JTextField text;
		public JTextField text2;
		public JTextField hosttext;
		
		private String port = "1099";
		private String host;
		private String playerName;
		
		GUI(LayoutManager layout)
		{		       

			// label 1
			final JLabel label = new JLabel("", JLabel.CENTER);
			label.setLocation(100, 50);
			add(label);
			label.setSize(400, 400);
			label.setFont (label.getFont ().deriveFont (20.0f));
			
			label.setText("Enter number of players");	
			
			
			//buttons
			final JButton b = new JButton("Enter");
			final JButton b2 = new JButton();
			final JButton b3 = new JButton();
			
			
			//text 1
		

		
			if(Client.isHost == true){
				
				text = new JTextField(1);
				setField(text);
				setButton(b,"OK");
			
				//Text should be number only
				text.addKeyListener(new KeyAdapter(){
					
				@Override
				public void keyPressed(KeyEvent ev)
				{
					int key = ev.getKeyCode();
					//String value = text.getText();
					//int val = Integer.parseInt(text.getText());
					
					if(!(key < 53 && key > 49 ) && key != KeyEvent.VK_BACK_SPACE )
						System.out.println("[!] mis-match input");		
					
					//if(val < 2 || val > 4)
						//label.setText("mismatch");
				}
			});
			
			}else{
				
				//clearAll(text,b);
				label.setText("Enter name ");
				setButton(b2,"OK");
			
			}
			
		
			
			//text 2	
			text2 = new JTextField();
			setField(text2);
			
			
			
			//Host Address Text field
			hosttext = new JTextField("");
			hosttext.setLocation(250, 300);
			hosttext.setSize(100,30);
			add(hosttext);
			
			
			//button 1 on click
			b.addActionListener(new ActionListener()
					{
						
						
						public void actionPerformed(ActionEvent e)
						{
							System.out.println(text.getText());
							try{
							
							
							 val = Integer.parseInt(text.getText());
							 
							if(val >= 2 && val <= 4){
							Client.players = val;
							
							clearAll(text,b);
							label.setText("Enter name ");
							setButton(b2,"OK");
							
							}else{
								label.setText("Enter number of players - (2 to 4)");
							}
							}
							catch(NumberFormatException e1){
								
								label.setText("Enter number of players - (2 to 4)");
								System.out.println(val);
							}
						}
					});
			
			
			
			
			//button 2 on click
			b2.addActionListener(new ActionListener()
					{
						 
			
				
						public void actionPerformed(ActionEvent e)
						{
							playerName = text2.getText();
							   
							text2.setText(null);
							b2.removeNotify();
							text2.removeNotify();
							
							
							//b3.setText("Set");
							label.setText("Enter Host");
							hosttext.setText(null);		
							b3.setText("OK");
							add(b3);  
							b3.setBounds(250,400,100, 40);	
							
							
						}
					});
			
						
					b3.addActionListener(new ActionListener()
						{
							 
				
					
							public void actionPerformed(ActionEvent e)
							{
																
							removeAll();		
							revalidate();
							repaint();
							
							host = hosttext.getText();
							GUIHandler.swap("LOBBY");
							
							
							
							Client.serverConnect(port,host,playerName);
							
								
						
							
							}
				
					});
						
			
			
			

			//f.getContentPane().setBackground(Color.GRAY);
	        setSize(900,900);			
	        setLayout(null);  
	    	setVisible(true);  
	    		    	
	    	
		}
		
		public void setField(JTextField field){
			
			field.setLocation(250, 300);;	
			add(field);
			field.setSize(100, 30);

		}
		
		public void setButton(JButton button , String bText){
			
			button.setText(bText);
			button.setBounds(250,400,100, 40);
			add(button);  
		
		}
		
		
		public void clearAll(JTextField field, JButton button){
			
			field.setText(null);
			field.removeNotify();
			button.removeNotify();
			
		}
	
		
	/*
	
		public void GetPosition()
		{
			
			//label 2
			JLabel label2 = new JLabel("Click on starting position", JLabel.CENTER);	
			f.add(label2);
			label2.setSize(300, 100);
			label2.setFont (label2.getFont ().deriveFont (15.0f));
		
			
			// ArrayList<Point2D> PtList = new ArrayList<Point2D>();
			
			//Get mouse position
			f.addMouseListener(new MouseListener() 
			{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					int x=e.getX();
				    int y=e.getY();
				   // Point2D pt = new Point2D.Double(x, y);
				    //PtList.add(pt);
				    System.out.println(x+","+y);
					JOptionPane.showMessageDialog(f, "Position clicked: " +x+","+y);

				}

				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
			
		}
		*/
}
