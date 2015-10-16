package com.deterra.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.rmi.RemoteException;

/**
 * Created by Pavel Nikolaev on 27/08/2015.
 * Modified by Daniel Bugeja on 21/09/2015.
 */


public class ClientBoard extends JPanel {

   /**
	 * 
	 */
   private static final long serialVersionUID = 1L;
   
   static byte[][] board = new byte[11][11];
   
   private int startPos = 50;
   private int sqrSize = 50;
   
   private static JTextField in;
   
   
   
   public ClientBoard(LayoutManager layout){
	   
       super();
	
	   initialiseBoard();
	
	   //creates a textfield and sets it off screen
	   in = new JTextField(8);	
	   setLayout(null);
	   in.setLocation(0, 0);	
	   add(in);
	   
	   //sets size and sets textfield to get cursor focus
	   in.requestFocusInWindow ();
	   in.setMaximumSize( new Dimension(in.getPreferredSize().width, in.getPreferredSize().height) );

	   //adds a key listerner to allow for keyboard presses to be registered
	   in.addKeyListener(new KeyAdapter(){
		   
		   
			@Override
			public void keyPressed(KeyEvent ev)
			{
				
					
					int key = ev.getKeyCode();
					try {
						if( key == KeyEvent.VK_RIGHT )
							Player.move(0,1);
					
						if( key == KeyEvent.VK_LEFT )
							Player.move(0,-1);
				
						if( key == KeyEvent.VK_DOWN )
							Player.move(1,0);
				
						if( key == KeyEvent.VK_UP )
							Player.move(-1,0);
				
						if( key == KeyEvent.VK_K )
							Player.kill();
				
				
					} catch (RemoteException e) {
						e.printStackTrace();
					}	
				}
			
		});
   }
   
   
   //sets focus to textfield
   public static void focus(){
		in.requestFocusInWindow ();
   }
    
   //Initializes boards default values
    public void initialiseBoard(){

        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){

                if(i==0 || i==5 || i==10){
                    board[i][j]=1;
                }

                if(j==0 || j==5 || j==10){
                    board[i][j]=1;
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {

        super.paintComponent(g);

    int x= startPos;
    int y= startPos;
    Color[] col = new Color[] 
    		{
    		 Color.lightGray,
    		 Color.darkGray,
    		 Color.black,
    		 Color.red,
    		 Color.blue,
    		 Color.green,
    		 Color.yellow
    		};

        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){

            	
            	for(int c = 0; c < col.length;c++)
            	{
            		 if(board[i][j]==c){
                         g.setColor(col[c]);
                         g.fill3DRect(x,y,sqrSize,sqrSize,true);
                         x+=sqrSize;
                     }
            		
            	}
               
               if(j==10){
                   y+=sqrSize;
                   x= startPos;

                }
            }
    }
        /*
        for(int i=50;i<=930;i+=80) {
            g.drawLine(i,50,i,930);
        }

        for(int j=50;j<=930;j+=80){
            g.drawLine(50,j,930,j);
        }
	*/
    
}

}