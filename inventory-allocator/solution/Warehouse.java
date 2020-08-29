package solution;

import java.util.Map;
import java.util.HashMap;

public class Warehouse{
	//each warehouse has a finite inventory
	private Map<String, Integer> inventory;
	private String name;
	public Warehouse(String name){
		this.inventory = new HashMap<String, Integer>();
		this.name = name; 
	}
	public Warehouse(Map<String, Integer> inventory, String name){
		this.inventory = inventory;
		this.name = name;
	}
	//function that returns the value of a certain item if it exists in the warehouse
	//if it is not in the warehouse then returns -1
	public int howManyOfItem(String a){
		if(this.inventory.containsKey(a)){
			return inventory.get(a);
		} else {
			return -1;
		}
	}
	public void addItem(String item, Integer count){
		this.inventory.put(item, count);
	}
	public String getName(){
		return this.name;
	}
	public boolean isEmpty(){
		return this.inventory.isEmpty(); 
	}
	public Map<String, Integer> getInventory() {
		return this.inventory;
	}
}