package com.deterra.client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Pavel Nikolaev on 27/08/2015.
 */
public class ClientBoard extends JFrame {

   static byte[][] board = new byte[11][11];

    public void drawBoard(){
        setTitle("Multiplayer Game");
        setSize(975,975);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void paintUpdate(){
    	repaint();
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
    public void paint(Graphics g) {

        super.paint(g);

    int x=50;
    int y=50;


        for(int i=0;i<11;i++){
            for(int j=0;j<11;j++){

                if(board[i][j]==1){
                    g.setColor(Color.darkGray);
                    g.fill3DRect(x,y,80,80,true);
                    x+=80;
                }

                else{
                    g.setColor(Color.lightGray);
                    g.fill3DRect(x,y,80,80,true);
                    x+=80;
                }

               if(j==10){
                   y+=80;
                   x=50;

                }
            }
    }
        for(int i=50;i<=930;i+=80) {
            g.drawLine(i,50,i,930);
        }

        for(int j=50;j<=930;j+=80){
            g.drawLine(50,j,930,j);
        }

    }


}
