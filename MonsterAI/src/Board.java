
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

public class Board {
	
	boolean showMoreInfo = true;
    int distanceFromOrigin = 0;
    int x,y,z;
    public Map<Board, Integer> neighbors = new HashMap<>();

    public Board(){
    	this.x = 0;
		this.y = 0;
		this.z = 0;
    }
	public void setBoard(int x, int y, int z) {
		// TODO Auto-generated constructor stub
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public List<Board> getNeighbours(){
		return (List<Board>) this.neighbors;
	}
	
	public Board getBoard(List<Board> node, int x, int y, int z){
		List<Board> nodesCopy = new ArrayList<>();
        nodesCopy.addAll(node);
        
        for (Board board : nodesCopy) {
        	
        	if(board.x == x && board.y == y){
        		return board;
        	}
        }
        
        Board data = new Board();
        setBoard(x, y, z);
		return data;
	}
	
}

