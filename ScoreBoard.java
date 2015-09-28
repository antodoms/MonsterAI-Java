package ScoreBoard;

import java.util.*;

/**
 * Created by Pavel Nikolaev on 28/09/2015.
 */
public class ScoreBoard {

   static Scores[] ScoreBoard = new Scores[10];


    public static void initScoreBoard(){     //initializing every array variable to a default value
       int i;
        Scores player = new Scores("N/A",0);
        for(i=0;i<10;i++) {
            ScoreBoard[i]=player;
        }
    }

    public static void addScore(int score, String name) {
        int i;
        Scores player = new Scores(name, score);        //inserting player scores into ScoreBoard array
                                                        // then sorting them
            if (player.getScore() > ScoreBoard[9].getScore()) {
             ScoreBoard[9]=player;
                Arrays.sort(ScoreBoard);
            }
    }

    public static void printScores(){
        int i;
        System.out.println("Name-------------|Score---------");
        for(i=0;i<10;i++) {
            System.out.printf("%16s %4d\n",ScoreBoard[i].getName(),ScoreBoard[i].getScore());
        }

    }

    public Scores[] returnScores(){
        return ScoreBoard;
    }


    public static void main (String args[]){
       initScoreBoard();  // here for testing

        addScore(30,"jerry");
        addScore(30,"max");
        addScore(55,"luigi");
        addScore(55,"luigi");
        addScore(55,"luigi");
        addScore(30,"max");

        printScores();
    }


}
