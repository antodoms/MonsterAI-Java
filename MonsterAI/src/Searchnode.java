import java.util.ArrayList;
import java.util.List;

// This is a node used for AI as Stack object


public class Searchnode {

	public Board node;
	public List<Board> arraylist = new ArrayList<>();
	
	public void setfunc(Board a, List<Board> arraylist2){
		this.node = a;
		this.arraylist.clear();
		this.arraylist.addAll(arraylist2);
		//System.out.println(this.arraylist);
		this.arraylist.add(a);
		//System.out.println(this.arraylist);
	}
	
	public void addarray(Board a){
		this.arraylist.add(a);
	}
	
	public void initialise(Board a){
		this.node = a;
		this.arraylist.add(a);
	}

}
