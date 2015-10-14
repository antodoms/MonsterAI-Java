package AI;
import java.util.List;
import java.util.ArrayList;
import java.util.Stack;


public class MonsterAI {
	
	List<AIBoard> nodes = new ArrayList<>();    // the original node where we store the Board node class objects.
	List<AIBoard> players = new ArrayList<>();  // stores class objects of all the players
	public AIBoard monster = new AIBoard();			// stores the class object of monster
	
	public byte[][] boarddata = {{1,1,1,1,1,1,1,1,1,1,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,0,0,0,1,0,0,0,0,0,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,1,1,1,1,1,1,1,1,1,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,0,0,0,0,1,0,0,0,0,1},
								 {1,1,1,1,1,1,1,1,1,1,1}};
	
	
	public void changeboard(byte[][] data){
		// this function should be called from class object from the server side
		// each time to insert a new board values for MonsterAI algorith.
		
		this.boarddata = data;
	}
	
	public void addnode(AIBoard l,List<AIBoard> node){
		
		// This is used return a new node if the node is previously not created 
		// and if it is previously created return the previous node from nodes.
		
		List<AIBoard> nodesCopy = new ArrayList<>();
        nodesCopy.addAll(node);
        boolean check=false;
        for (AIBoard board : nodesCopy) {
        	
        	if(board.x == l.x && board.y == l.y){
        		check = true;
        	}
        }
        
        if(check==false){
        	nodes.add(l);
        }
	}
	
	public AIBoard getcharacter(AIBoard node){
		
		// this is used to return the actual Board node at a particular (x,y)
		// location if a new class object is created with (x,y) coordinates.
		
        for (AIBoard board : nodes) {
        	
        	if(board.x == node.x && board.y == node.y){
        		node=board;
        	}
        }
      return node;
	}
	public void drawGraph() {
		
		// This function assigns the array received from server and adds it into a
		// class object or node. ie, rather than drawing the whole array to class 
		// objects this just creates the object of location in array not equal to 0.
		List<AIBoard> nodestest = new ArrayList<>();
		
		
		for(int i=0;i<boarddata.length;i++){
			
			for(int j=0; j<boarddata[i].length; j++){
				
				if(boarddata[i][j] !=0){
					
				AIBoard data = new AIBoard();
				data.setBoard(i,j,boarddata[i][j]);
				
				int a=i-1;
				int b=i+1;
				int c=j-1;
				int d=j+1;
				//System.out.println("a=" +a + " b=" +b +" c="+ c + " d=" +d);
				if((a < 11) && (a >= 0) && (boarddata[a][j] != 0)){
					AIBoard l = new AIBoard();
					l = data.getBoard(nodes,a,j,boarddata[a][j]);
					//System.out.println(a+ " " + j + "->" + l.x + " " +  l.y);
					addnode(l,nodes);
					data.addNeighbours(data,l);
					
				}
				if((c< 11) && (c >=0)  && (boarddata[i][c] != 0)){
					AIBoard m = new AIBoard();
					m = data.getBoard(nodes,i,c,boarddata[i][c]);
					//System.out.println(i+ " " + c + "->" + m.x+ " " +  m.y);
					addnode(m,nodes);
					data.addNeighbours(data,m);
				
				}
				if((b>=0) && (b < 11) && (boarddata[b][j] != 0)){
					AIBoard n = new AIBoard();
					n = data.getBoard(nodes,b,j,boarddata[b][j]);
					//System.out.println(b+ " " + j + "->" + n.x + " " +  n.y);
					addnode(n,nodes);
					data.addNeighbours(data,n);	
				}	
				if((d>=0) && (d < 11) && (boarddata[i][d] != 0)){
					AIBoard p = new AIBoard();
					p = data.getBoard(nodes,i,d,boarddata[i][d]);
					//System.out.println(i+ " " + d + "->" + p.x + " " + p.y);
					addnode(p,nodes);
					data.addNeighbours(data,p);
				}
				if(boarddata[i][j]>2){
					players.add(data);
				}
				if(boarddata[i][j]==2){
					monster= data;
				}
					addnode(data,nodes);
					//data.displayNeighbours(data);
					addnode(data,nodestest);
					nodestest.add(data);
				}
				
			}
		}
		this.nodes = nodestest;
		//Board test = new Board();
		//test.testBoard(this.nodes);
    }
	
	public AIBoard searchnearest(){
		
		// This searches the nearest player from monster using manhattan distance algorithm.
        monster=getcharacter(monster);
        AIBoard shortest = new AIBoard();
        int min = Integer.MAX_VALUE;
        
        
        for (AIBoard board : players) {
        	int distance = Math.abs(board.x - monster.x) + Math.abs(board.y - monster.y);
        	System.out.println(board.z + " = " + distance);
        	if(distance < min){
        		min = distance;
        		shortest = board;
        	}
        	
		}
        
        System.out.println(" shortest is = (" + shortest.x + "," + shortest.y + ")");
        return shortest;
	}

	
	
	public List<AIBoard> findpathdfs(AIBoard start, AIBoard end){
		
		// Depth First Search (DFS) path code. Maybe we can use this for easy game
		// monster path code.
		//
		// This code displays the path as arraylist from Start (ie, monster location)
		// to shortest distance player location.
		
		
		List<AIBoard> closed = new ArrayList<AIBoard>();
		Searchnode temp = new Searchnode();
		
		List<List<AIBoard>> total = new ArrayList<List<AIBoard>>();
		List<AIBoard> finallist = new ArrayList<AIBoard>();
		start=getcharacter(start);
		end=getcharacter(end);
		temp.initialise(start);
		
		Stack<Searchnode> fringe = new Stack<Searchnode>();
		fringe.push(temp);
		System.out.println(fringe);
		
		
		
		while(!fringe.isEmpty()){
			temp = fringe.pop();
			temp.node= getcharacter(temp.node);
			//System.out.println(temp);
			//System.out.println(temp.node + " " + end);
			
			if(temp.node == end){
				System.out.println("END END END" + temp.node);
				//return temp.arraylist;
				total.add(temp.arraylist);
				//temp=fringe.pop();
			}
			
			boolean cd = false;
			//System.out.println(cd);
			
			if(closed !=null){
				for(AIBoard ab : closed){
					if(temp.node==ab){
						cd = true;	
					}
				}
			}
			
			if(cd==true){
				continue;
			}
			else{
				closed.add(temp.node);
				
				for(AIBoard ab: temp.node.getNeighbours(temp.node)){
					Searchnode temp2 = new Searchnode();
					temp2.initialise(ab);
					temp2.setfunc(ab,temp.arraylist);
					fringe.push(temp2);
				}
			}	
		}
		
		finallist = total.get(0);
		for (List<AIBoard> ab: total){
			if(ab.size() < finallist.size()){
				finallist = ab;
				System.out.println("size = = = " + ab.size());
			}
		}
		return finallist;
	}
	
	
	public void nextpath(AIBoard start, AIBoard end){
		// Still need to be implemented , this is supposed to send the next possible location 
		// of monster instead of sending the whole path.
		int distancex = start.x-end.x;
		int distancey = start.y-end.y;
		
		AIBoard temp = start;
		List<AIBoard> nodesCopy = new ArrayList<>();
		
		
	}

	public int[] displaypoint(List<AIBoard> path,int n){
		System.out.println("the path from monster to nearest player is ");
			int[] nextMove = new int[4];
        	System.out.println(path.get(1 + n).x + " " + path.get(1 + n).y + " " + "n:" + n);
        	
        	 nextMove[0] = path.get(0 + n).x;
        	 nextMove[1] = path.get(0 + n).y;	
        	 nextMove[2] = path.get(1 + n).x;
        	 nextMove[3] = path.get(1 + n).y;	
        
        return nextMove;
	}
	
		public void displaypath(List<AIBoard> path){
			System.out.println("the path from monster to nearest player is ");
	        for( AIBoard cd : path){
	        	System.out.println(cd.x + " " + cd.y + " ");
	        	
	        }
		}
	    
	 
	}
