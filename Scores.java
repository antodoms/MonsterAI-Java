package ScoreBoard;

/**
 * Created by Pavel Nikolaev on 28/09/2015.
 */
public class Scores implements Comparable<Scores> { // implementing comparable
                                                  //to allow objects to be sorted according to their properties
    private String NAME;
    private int SCORE;


    public Scores(String name,int score){ //constructor
        this.NAME=name;
        this.SCORE=score;
    }


   public String getName(){ //getters & setters
       return NAME;
   }
    public int getScore(){
        return SCORE;
    }

    @Override
    public int compareTo(Scores score) {         //overriding the compareTo to allow score objects to be
                                                // compared according to their SCORE value
       int compareScore = ((Scores)score).getScore();
        return compareScore-this.SCORE;
    }
}
