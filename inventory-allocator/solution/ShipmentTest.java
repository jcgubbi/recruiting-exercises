package solution;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.LinkedList;
import org.junit.*;

public class ShipmentTest{
	@Test 
	public void addOrder_success(){
		Shipment sh = new Shipment();

		Map<String, Integer> itemsWh1 = new HashMap<String, Integer>();
		itemsWh1.put("apple", 12);
		itemsWh1.put("pear", 31);
		itemsWh1.put("banana", 1);
		Warehouse order1 = new Warehouse(itemsWh1, "order1");

		sh.addOrder(order1);

		List<Warehouse> orders = sh.getShipment();
		Assert.assertEquals(order1, orders.get(0));
	}
	@Test
	public void getShipment_success(){
		Map<String, Integer> itemsWh1 = new HashMap<String, Integer>();
		itemsWh1.put("apple", 12);
		itemsWh1.put("pear", 31);
		itemsWh1.put("banana", 1);
		Warehouse wh1 = new Warehouse(itemsWh1, "wh1");
		Map<String, Integer> itemsWh2 = new HashMap<String, Integer>();
		itemsWh2.put("pinapple", 12);
		itemsWh2.put("coconut", 31);
		itemsWh2.put("grapes", 1);
		Warehouse wh2 = new Warehouse(itemsWh2, "wh2");

		List<Warehouse> order = new LinkedList<Warehouse>();
		order.add(wh1);
		order.add(wh2);

		Shipment sh = new Shipment(order);

		List<Warehouse> returnOrder = sh.getShipment(); 

		Assert.assertEquals(returnOrder.size(), 2);
		Assert.assertEquals(returnOrder.get(0), wh1);
	}
}