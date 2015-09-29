import java.util.List;
import java.util.ArrayList;
import java.util.Stack;


class MonsterAI {
	
	List<Board> nodes = new ArrayList<>();    // the original node where we store the Board node class objects.
	List<Board> players = new ArrayList<>();  // stores class objects of all the players
	Board monster = new Board();			// stores the class object of monster
	
	public byte[][] boarddata = {{1,1,1,1,1,1,1,1,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,4,0,0,0,1},
								 {2,1,1,1,1,1,1,1,3},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,1,1,1,1,1,1,1,1}};
	
	
	public void changeboard(byte[][] data){
		// this function should be called from class object from the server side
		// each time to insert a new board values for MonsterAI algorith.
		
		this.boarddata = data;
	}
	
	public void addnode(Board l,List<Board> node){
		
		// This is used return a new node if the node is previously not created 
		// and if it is previously created return the previous node from nodes.
		
		List<Board> nodesCopy = new ArrayList<>();
        nodesCopy.addAll(node);
        boolean check=false;
        for (Board board : nodesCopy) {
        	
        	if(board.x == l.x && board.y == l.y){
        		check = true;
        	}
        }
        
        if(check==false){
        	nodes.add(l);
        }
	}
	
	public Board getcharacter(Board node){
		
		// this is used to return the actual Board node at a particular (x,y)
		// location if a new class object is created with (x,y) coordinates.
		
        for (Board board : nodes) {
        	
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
		List<Board> nodestest = new ArrayList<>();
		
		
		for(int i=0;i<boarddata.length;i++){
			
			for(int j=0; j<boarddata[i].length; j++){
				
				if(boarddata[i][j] !=0){
					
				Board data = new Board();
				data.setBoard(i,j,boarddata[i][j]);
				
				int a=i-1;
				int b=i+1;
				int c=j-1;
				int d=j+1;
				//System.out.println("a=" +a + " b=" +b +" c="+ c + " d=" +d);
				if((a < 9) && (a >= 0) && (boarddata[a][j] != 0)){
					Board l = new Board();
					l = data.getBoard(nodes,a,j,boarddata[a][j]);
					//System.out.println(a+ " " + j + "->" + l.x + " " +  l.y);
					addnode(l,nodes);
					data.addNeighbours(data,l);
					
				}
				if((c< 9) && (c >=0)  && (boarddata[i][c] != 0)){
					Board m = new Board();
					m = data.getBoard(nodes,i,c,boarddata[i][c]);
					//System.out.println(i+ " " + c + "->" + m.x+ " " +  m.y);
					addnode(m,nodes);
					data.addNeighbours(data,m);
				
				}
				if((b>=0) && (b < 9) && (boarddata[b][j] != 0)){
					Board n = new Board();
					n = data.getBoard(nodes,b,j,boarddata[b][j]);
					//System.out.println(b+ " " + j + "->" + n.x + " " +  n.y);
					addnode(n,nodes);
					data.addNeighbours(data,n);	
				}	
				if((d>=0) && (d < 9) && (boarddata[i][d] != 0)){
					Board p = new Board();
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
					//                         data.displayNeighbours(data);
					addnode(data,nodestest);
					nodestest.add(data);
				}
				
			}
		}
		this.nodes = nodestest;
		//Board test = new Board();
		//test.testBoard(this.nodes);
    }
	
	public Board searchnearest(){
		
		// This searches the nearest player from monster using manhattan distance algorithm.
        monster=getcharacter(monster);
        Board shortest = new Board();
        int min = Integer.MAX_VALUE;
        
        
        for (Board board : players) {
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

	
	
	public List<Board> findpathdfs(Board start, Board end){
		
		// Depth First Search (DFS) path code. Maybe we can use this for easy game
		// monster path code.
		//
		// This code displays the path as arraylist from Start (ie, monster location)
		// to shortest distance player location.
		
		
		List<Board> closed = new ArrayList<Board>();
		Searchnode temp = new Searchnode();
		
		List<List<Board>> total = new ArrayList<List<Board>>();
		List<Board> finallist = new ArrayList<Board>();
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
				for(Board ab : closed){
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
				
				for(Board ab: temp.node.getNeighbours(temp.node)){
					Searchnode temp2 = new Searchnode();
					temp2.initialise(ab);
					temp2.setfunc(ab,temp.arraylist);
					fringe.push(temp2);
				}
			}	
		}
		
		finallist = total.get(0);
		for (List<Board> ab: total){
			if(ab.size() < finallist.size()){
				finallist = ab;
				System.out.println("size = = = " + ab.size());
			}
		}
		return finallist;
	}
	
	
	public void nextpath(Board start, Board end){
		// Still need to be implemented , this is supposed to send the next possible location 
		// of monster instead of sending the whole path.
		int distancex = start.x-end.x;
		int distancey = start.y-end.y;
		
		Board temp = start;
		List<Board> nodesCopy = new ArrayList<>();
		
		
		
		
	}

		public void displaypath(List<Board> path){
			System.out.println("the path from monster to nearest player is ");
	        for( Board cd : path){
	        	System.out.println(cd.x + " " + cd.y + " ");
	        	
	        }
		}
	    
	    public static void main(String[] args){
	    	
	    	// This is how you have to call the class functions in its order in the server
	    	// side to receive the path of monster.
	    	List<Board> finalpath = new ArrayList<>();
	    	byte[][] randomboard = {{1,1,1,3,1,1,1,1,1},
									{1,0,0,0,1,0,0,0,1},
									{1,0,0,0,1,0,0,0,1},
									{1,0,0,0,1,0,0,0,1},
									{1,1,2,1,1,1,1,1,1},
									{1,0,0,0,1,0,0,0,1},
									{4,0,0,0,1,0,0,0,1},
									{1,0,0,0,1,0,0,0,1},
									{1,1,1,1,1,1,1,1,1}};
	    	
	        MonsterAI ab = new MonsterAI();
	        ab.changeboard(randomboard);
	        ab.drawGraph();
	        Board shortest = ab.searchnearest();
	        //System.out.println(ab.monster + " 123 " + shortest);
	        
	        finalpath=ab.findpathdfs(ab.monster, shortest);
	        
	        // uncomment below lines to display the final path 
	         System.out.println("start : " + ab.monster.x + " " + ab.monster.y + "  stop : " + shortest.x + " " + shortest.y);
	         ab.displaypath(finalpath);
	        
	    }
	}
