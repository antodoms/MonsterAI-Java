import java.util.ArrayList;
import java.util.List;

public class Board {
	
	boolean showMoreInfo = true;
    int distanceFromOrigin = 0;
    int x,y,z;
    public List<Board> neighbors = new ArrayList<>();

    public Board(){
    	this.x = 0;
		this.y = 0;
		this.z = 0;
    }
	public void setBoard(int x, int y, int z) {
		// setting the location (x,y) of the object z is the type of object like 
		// if z=1 then its available , if z=2 then thats monster , likewise
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public List<Board> getNeighbours(Board a){
		
		// this returns back all the neighboring nodes of the current node
		
		return (List<Board>) a.neighbors;
	}
	
	public void addNeighbours(Board master,Board child){
		
		// this function adds a new neighbor to the current node
		
		boolean check=false;
		for(Board ab: master.getNeighbours(master)){
			
			if(ab==child){
				check=true;
			}
		}
		
		if(check==false){
			master.neighbors.add(child);
			//System.out.println("     " + child.x + "  " + child.y);
		}
	}
	
	
	public void displayNeighbours(Board master){
		
		// this function will display all the neighbors of the given node
		
		System.out.println("main is : (" + master.x + "," + master.y + ")");
		for(Board ab: master.getNeighbours(master)){
			
			System.out.println("(" + ab.x + "," + ab.y + ")");
		}
	}


	public Board getBoard(List<Board> node, int a, int b, int c){
		
		// This function is used to return the board node if the list and
		// x & y coordinates are provided
		
		List<Board> nodesCopy = new ArrayList<>();
        nodesCopy.addAll(node);
        
        for (Board board : nodesCopy) {
        	
        	if((board.x == a) && (board.y == b)){
        		return board;
        	}
        }
        
        Board data = new Board();
        data.setBoard(a, b, c);
		return data;
	}
	
	public Board testBoard(List<Board> node){
        // Display the Board if the List node is provided
		
        for (Board board : node) {
        	System.out.print("\n main : ("+ board.x + "," + board.y+") --");
        	System.out.print(board.getNeighbours(board).size() + " ->");
        	for (Board ab : board.getNeighbours(board)){
        		System.out.print(" ("+ ab.x + "," + ab.y+")  ");
        	}
        }
        
        Board data = new Board();
        setBoard(x, y, z);
		return data;
	}
	
}

