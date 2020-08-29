package solution;

import java.util.Map;
import java.util.HashMap;
import org.junit.*;

public class WarehouseTest {
	@Test
	public void howManyOfItem_empty(){
		Map<String, Integer> exists = new HashMap<String, Integer>();
		exists.put("apple", 4);
		exists.put("banana", 5);
		exists.put("orange", 3);
		Warehouse wh = new Warehouse(exists, "wh1");

		int val = wh.howManyOfItem("pear");
		
		Assert.assertEquals(-1, val);
	}
	
	@Test
	public void howManyOfItem_nonEmpty(){
		Map<String, Integer> exists = new HashMap<String, Integer>();
		exists.put("pear", 3);
		exists.put("grapes", 4);
		exists.put("pineapple", 20);
		Warehouse wh = new Warehouse(exists, "wh1");

		int grape = wh.howManyOfItem("grapes");
		int pineapple = wh.howManyOfItem("pineapple");

		Assert.assertEquals(4, grape);
		Assert.assertEquals(20, pineapple); 
	}

	@Test
	public void getName_success(){
		Map<String, Integer> exists = new HashMap<String, Integer>();
		Warehouse wh = new Warehouse(exists, "whName");

		String name = wh.getName();

		Assert.assertEquals("whName", name);
	}
	@Test
	public void addItem_success(){
		Map<String, Integer> exists = new HashMap<String, Integer>();
		exists.put("apple", 4);
		exists.put("banana", 5);
		exists.put("orange", 3);
		Warehouse wh = new Warehouse(exists, "wh1");

		int val = wh.howManyOfItem("pear");
		Assert.assertEquals(-1, val);

		wh.addItem("pear", 123); 
		val = wh.howManyOfItem("pear");
		Assert.assertEquals(123, val);
	}

	@Test
	public void isEmpty_empty(){
		Map<String, Integer> exists = new HashMap<String, Integer>();
		Warehouse wh = new Warehouse(exists, "wh1");

		boolean empty = wh.isEmpty();

		Assert.assertEquals(true, empty);
	}

	@Test
	public void isEmpty_nonEmpty(){
		Map<String, Integer> exists = new HashMap<String, Integer>();
		exists.put("apple", 3);
		Warehouse wh = new Warehouse(exists, "wh1");

		boolean empty = wh.isEmpty();

		Assert.assertEquals(false, empty);
	}
}