package AI;
import java.util.ArrayList;
import java.util.List;

public class AIBoard {
	
	boolean showMoreInfo = true;
    int distanceFromOrigin = 0;
    int x,y,z;
    public List<AIBoard> neighbors = new ArrayList<>();
    public int pathCost;
	public AIBoard parent;

    public AIBoard(){
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
	
	public List<AIBoard> getNeighbours(AIBoard a){
		
		// this returns back all the neighboring nodes of the current node
		
		return (List<AIBoard>) a.neighbors;
	}
	
	public void addNeighbours(AIBoard master,AIBoard child){
		
		// this function adds a new neighbor to the current node
		
		boolean check=false;
		for(AIBoard ab: master.getNeighbours(master)){
			
			if(ab==child){
				check=true;
			}
		}
		
		if(check==false){
			master.neighbors.add(child);
			//System.out.println("     " + child.x + "  " + child.y);
		}
	}
	
	
	public void displayNeighbours(AIBoard master){
		
		// this function will display all the neighbors of the given node
		
		System.out.println("main is : (" + master.x + "," + master.y + ")");
		for(AIBoard ab: master.getNeighbours(master)){
			
			System.out.println("(" + ab.x + "," + ab.y + ")");
		}
	}


	public AIBoard getBoard(List<AIBoard> node, int a, int b, int c){
		
		// This function is used to return the board node if the list and
		// x & y coordinates are provided
		
		List<AIBoard> nodesCopy = new ArrayList<>();
        nodesCopy.addAll(node);
        
        for (AIBoard board : nodesCopy) {
        	
        	if((board.x == a) && (board.y == b)){
        		return board;
        	}
        }
        
        AIBoard data = new AIBoard();
        data.setBoard(a, b, c);
		return data;
	}
	
	public AIBoard testBoard(List<AIBoard> node){
        // Display the Board if the List node is provided
		
        for (AIBoard board : node) {
        	System.out.print("\n main : ("+ board.x + "," + board.y+") --");
        	System.out.print(board.getNeighbours(board).size() + " ->");
        	for (AIBoard ab : board.getNeighbours(board)){
        		System.out.print(" ("+ ab.x + "," + ab.y+")  ");
        	}
        }
        
        AIBoard data = new AIBoard();
        setBoard(x, y, z);
		return data;
	}
	
}

