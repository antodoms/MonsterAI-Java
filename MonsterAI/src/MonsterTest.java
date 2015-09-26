import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MonsterTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test1() {
		
		MonsterAI ab= new MonsterAI();
		
		byte[][] boarddata = {{1,1,1,1,1,1,3,1,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,1,1,1,1,1,2,1,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{4,0,0,0,1,0,0,0,1},
				 				{1,1,1,1,1,1,1,1,1}};
		ab.changeboard(boarddata);
		ab.drawGraph();
        Board shortest = ab.searchnearest();
        
        if(shortest.z == 3){
        	
        }
        else
        	fail("Some Problem with the Code");
	}
	
	@Test
	public void test2() {
		
		MonsterAI ab= new MonsterAI();
		
		byte[][] boarddata =   {{1,1,1,1,1,1,1,1,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,1,1,5,1,1,2,1,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{4,0,0,0,1,0,0,0,1},
				 				{1,1,1,1,1,1,3,1,1}};
		ab.changeboard(boarddata);
		ab.drawGraph();
        Board shortest = ab.searchnearest();
        
        if(shortest.z == 5){
        	
        }
        else
        	fail("Some Problem with the Code");
	}
	
	@Test
	public void test3() {
		
		MonsterAI ab= new MonsterAI();
		
		byte[][] boarddata = 	{{1,1,1,1,1,1,1,1,1},
				 				{3,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{5,1,1,1,1,1,2,1,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,0,0,0,4,0,0,0,1},
				 				{1,0,0,0,1,0,0,0,1},
				 				{1,1,1,1,1,1,1,1,1}};
		ab.changeboard(boarddata);
		ab.drawGraph();
        Board shortest = ab.searchnearest();
        
        if(shortest.z == 4){
        	
        }
        else
        	fail("Some Problem with the Code");
	}

}
