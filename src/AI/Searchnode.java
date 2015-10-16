package AI;
import java.util.ArrayList;
import java.util.List;

// This is a node used for AI as Stack object


public class Searchnode {

	public AIBoard node;
	public List<AIBoard> arraylist = new ArrayList<>();
	
	public void setfunc(AIBoard a, List<AIBoard> arraylist2){
		this.node = a;
		this.arraylist.clear();
		this.arraylist.addAll(arraylist2);
		//System.out.println(this.arraylist);
		this.arraylist.add(a);
		//System.out.println(this.arraylist);
	}
	
	public void addarray(AIBoard a){
		this.arraylist.add(a);
	}
	
	public void initialise(AIBoard a){
		this.node = a;
		this.arraylist.add(a);
	}

}
