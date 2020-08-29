package solution;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import org.junit.*;

public class InventoryAllocatorTest {
	@Test 
	public void findCheapestShipment_singleWarehouse(){
		//Input: { apple: 1 }, [{ name: owd, inventory: { apple: 1 } }]
		//Output: [{ owd: { apple: 1 } }]
		InventoryAllocator allocator = new InventoryAllocator();
		Map<String, Integer> order = new HashMap<String, Integer>();
		order.put("apple", 1);
		Warehouse wh = new Warehouse("owd");
		wh.addItem("apple", 1);
		List<Warehouse> warehouses = new LinkedList<Warehouse>();
		warehouses.add(wh);

		Shipment sh = allocator.findCheapestShipment(order, warehouses);

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(1, shipment.size());
		Assert.assertEquals(wh.getName(), shipment.get(0).getName());
		Assert.assertEquals(shipment.get(0).howManyOfItem("apple"), 1);
	}

	@Test
	public void findCheapestShipment_multipleWarehouses(){
		//Input: { apple: 10 }, [{ name: owd, inventory: { apple: 5 } }, { name: dm, inventory: { apple: 5 }}]
		//Output: [{ dm: { apple: 5 }}, { owd: { apple: 5 } }]
		InventoryAllocator allocator = new InventoryAllocator();		
		Map<String, Integer> order = new HashMap<String, Integer>();
		order.put("apple", 10);
		List<Warehouse> warehouses = new LinkedList<Warehouse>();
		Warehouse owd = new Warehouse("owd");
		owd.addItem("apple", 5);
		warehouses.add(owd);
		Warehouse dm = new Warehouse("dm");
		dm.addItem("apple", 5);
		warehouses.add(dm);

		Shipment sh = allocator.findCheapestShipment(order, warehouses); 

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(2, shipment.size());
		Assert.assertEquals("owd", shipment.get(0).getName());
		Assert.assertEquals("dm", shipment.get(1).getName());
		Assert.assertEquals(5, shipment.get(0).howManyOfItem("apple"));
		Assert.assertEquals(5, shipment.get(1).howManyOfItem("apple"));
	}

	@Test
	public void findCheapestShipment_noInventory(){
		//Input: { apple: 1 }, [{ name: owd, inventory: { apple: 0 } }]
		//Output: []
		InventoryAllocator allocator = new InventoryAllocator();		
		Map<String, Integer> order = new HashMap<String, Integer>();
		order.put("apple", 1);
		List<Warehouse> warehouses = new LinkedList<Warehouse>();
		Warehouse owd = new Warehouse("owd");
		owd.addItem("apple", 0);
		warehouses.add(owd);

		Shipment sh = allocator.findCheapestShipment(order, warehouses);

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(true, shipment.isEmpty());
	}

	@Test
	public void findCheapestShipment_notEnoughInventory(){
		//Input: { apple: 2 }, [{ name: owd, inventory: { apple: 1 } }]
		//Output: []
		InventoryAllocator allocator = new InventoryAllocator();
		Map<String, Integer> order = new HashMap<String, Integer>();
		order.put("apple", 2);
		List<Warehouse> warehouses = new LinkedList<Warehouse>();
		Warehouse owd = new Warehouse("owd");
		owd.addItem("apple", 1);
		warehouses.add(owd);

		Shipment sh = allocator.findCheapestShipment(order, warehouses);

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(true, shipment.isEmpty());
	}

	@Test
	public void findCheapestShipment_multipleItems(){
		//Input: { apple: 2, pear: 4, banana: 999}, [{ name: owd, inventory { banana: 998 }}, 
		//											 { name: dm, inventory { pear: 1, banana: 1}},
		//											 { name: th, inventory {apple: 2, pear 1}}]
		//Output: [{ owd: {banana: 998}}, {dm: { banana: 1}},{ th: { apple: 2 } }]
		InventoryAllocator allocator = new InventoryAllocator();		
		Map<String, Integer> order = new HashMap<String, Integer>();
		order.put("apple", 2);
		order.put("pear", 4);
		order.put("banana", 999); 
		List<Warehouse> warehouses = new LinkedList<Warehouse>();
		Warehouse owd = new Warehouse("owd");
		owd.addItem("banana", 998);
		warehouses.add(owd);
		Warehouse dm = new Warehouse("dm");
		dm.addItem("pear", 1);
		dm.addItem("banana", 1);
		warehouses.add(dm);
		Warehouse th = new Warehouse("th");
		th.addItem("apple", 2);
		th.addItem("pear", 1); 
		warehouses.add(th);

		Shipment sh = allocator.findCheapestShipment(order, warehouses); 

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(3, shipment.size());
		Assert.assertEquals("owd", shipment.get(0).getName());
		Assert.assertEquals("dm", shipment.get(1).getName());
		Assert.assertEquals("th", shipment.get(2).getName());
		Assert.assertEquals(998, shipment.get(0).howManyOfItem("banana"));
		Assert.assertEquals(1, shipment.get(1).howManyOfItem("banana"));
		Assert.assertEquals(2, shipment.get(2).howManyOfItem("apple"));
		Assert.assertEquals(-1, shipment.get(1).howManyOfItem("pear"));
	}

	@Test
	public void findCheapestShipment_multipleItemsNoWarehouses(){
		//Input: { apple: 1, pear: 2 }, []
		//Output: []		
		InventoryAllocator allocator = new InventoryAllocator();		
		Map<String, Integer> order = new HashMap<String, Integer>();
		order.put("apple", 1);
		order.put("pear", 2);
		List<Warehouse> warehouses = new LinkedList<Warehouse>();

		Shipment sh = allocator.findCheapestShipment(order, warehouses);

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(0, shipment.size());
	}


	@Test
	public void findCheapestShipment_noItemsMultipleWarehouses(){
		//Input: {}, [{name: owd, inventory {apple: 1}},{name: dm, inventory {pear: 1}} ]
		//Output: []
		InventoryAllocator allocator = new InventoryAllocator();		
		Map<String, Integer> order = new HashMap<String, Integer>();
		List<Warehouse> warehouses = new LinkedList<Warehouse>();
		Warehouse owd = new Warehouse("owd");
		owd.addItem("apple", 1);
		warehouses.add(owd);
		Warehouse dm = new Warehouse("dm");
		dm.addItem("pear", 1);
		warehouses.add(dm);

		Shipment sh = allocator.findCheapestShipment(order, warehouses);

		List<Warehouse> shipment = sh.getShipment();

		Assert.assertEquals(0, shipment.size());
	}
}