import java.util.ArrayList;
import java.util.List;

public class Searchnode {

	public Board node;
	public List<Board> arraylist = new ArrayList<>();
	
	
	public Searchnode() {
		// TODO Auto-generated constructor stub
		
		
	}
	
	public void setfunc(Board a, ArrayList<Board> b){
		this.node = a;
		this.arraylist = b;
	}
	
	public void addarray(Board a){
		this.arraylist.add(a);
	}

}
