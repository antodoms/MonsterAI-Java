import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.io.Console;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Stack;


class MonsterAI {
	
	List<Board> nodes = new ArrayList<>();
	List<Board> players = new ArrayList<>();
	Board monster = new Board();
	
	public int Monsterid = 2;
	
	public byte[][] boarddata = {{1,1,1,1,1,1,3,1,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,1,1,1,1,1,2,1,1},
								 {1,0,0,0,1,0,0,0,1},
								 {1,0,0,0,1,0,0,0,1},
								 {4,0,0,0,1,0,0,0,1},
								 {1,1,1,1,1,1,1,1,1}};
	
	public void changeboard(byte[][] data){
		this.boarddata = data;
	}
	
	public void addnode(Board l,List<Board> node){
		
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
	public void drawGraph() {
		
		for(int i=0;i<boarddata.length;i++){
			
			for(int j=0; j<boarddata[i].length; j++){
				Board data = new Board();
				data.setBoard(i,j,boarddata[i][j]);
				int a=i-1;
				int b=i+1;
				int c=j-1;
				int d=j+1;
				
				if(a >= 0 && boarddata[a][j] != 0){
					Board l = new Board();
					l = data.getBoard(nodes,a,j,boarddata[a][j]);
					addnode(l,nodes);
					data.neighbors.put(l, 1);
					
				}
				else if(c >=0  && boarddata[i][c] != 0){
					Board m = new Board();
					m = data.getBoard(nodes,i,c,boarddata[i][c]);
					addnode(m,nodes);
					data.neighbors.put(m, 1);
				
				}
				else if(b < 9 && boarddata[b][j] != 0){
					Board n = new Board();
					n = data.getBoard(nodes,b,j,boarddata[b][j]);
					addnode(n,nodes);
					data.neighbors.put(n, 1);		
				}	
				else if(d < 9 && boarddata[i][d] != 0){
					Board p = new Board();
					p = data.getBoard(nodes,i,d,boarddata[i][d]);
					addnode(p,nodes);
					data.neighbors.put(p, 1);
				}
				if(boarddata[i][j]>2){
					players.add(data);	
				}
				if(boarddata[i][j]==2){
					monster= data;
				}
				nodes.add(data);
			}
		}
        
    }
	
	public Board searchnearest(){
		List<Board> nodesCopy = new ArrayList<>();
		List<Board> playersCopy = new ArrayList<>();
        nodesCopy.addAll(nodes);
        playersCopy.addAll(players);
        Board shortest = new Board();
        int min = Integer.MAX_VALUE;
        
        
        for (Board board : playersCopy) {
        	int distance = Math.abs(board.x - monster.x) + Math.abs(board.y - monster.y);
        	System.out.println(board.z + " = " + distance);
        	if(distance < min){
        		min = distance;
        		shortest = board;
        	}
        	
		}
        
        System.out.println(" shortest is = " + shortest.z);
        return shortest;
	}

	public List<Board> findpath(Board start, Board end){
		Set<Board> closed = null;
		Searchnode temp = new Searchnode();
		temp.setfunc(start,null);
		Stack<Searchnode> fringe = new Stack<Searchnode>();
		fringe.push(temp);
		
		while(!fringe.isEmpty()){
			temp = fringe.pop();
			System.out.println(fringe);
			if(temp.node == end){
				return temp.arraylist;
			}
			
			boolean cd = false;
			for(Board ab : closed){
				if(temp.node==ab){
					cd = true;
				}
			}
			
			if(cd==true){
				continue;
			}
			
			closed.add(temp.node);
			
			for(Board ab: temp.node.getNeighbours()){
				Searchnode temp2 = new Searchnode();
				temp2.setfunc(ab,(ArrayList<Board>) temp.arraylist);
				temp2.addarray(ab);
				fringe.push(temp2);
				System.out.println(fringe);
				
			}
			
		}
		
		return null;
	}
	
	
	public void nextpath(Board start, Board end){

		int distancex = start.x-end.x;
		int distancey = start.y-end.y;
		
		Board temp = start;
		List<Board> nodesCopy = new ArrayList<>();
		
		
		
		
	}
	
	
	
	
    public MonsterAI() {
    	
    }
    
    public static void main(String[] args){
        MonsterAI ab = new MonsterAI();
        ab.drawGraph();
        Board shortest = ab.searchnearest();
        List<Board> finalpath = new ArrayList<>();
        finalpath=ab.findpath(ab.monster, shortest);
        System.out.println(finalpath);
    }
}
