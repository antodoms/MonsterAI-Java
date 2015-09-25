package com.deterra.client;

import javax.swing.*;
import java.awt.*;

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
   
   
   public ClientBoard(LayoutManager layout){
	   
	super(layout);
	
	initialiseBoard();
	
   }

    
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


        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){

                if(board[i][j]==1){
                    g.setColor(Color.darkGray);
                    g.fill3DRect(x,y,sqrSize,sqrSize,true);
                    x+=sqrSize;
                }

                else{
                    g.setColor(Color.lightGray);
                    g.fill3DRect(x,y,sqrSize,sqrSize,true);
                    x+=sqrSize;
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
