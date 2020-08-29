package solution;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InventoryAllocator{
	private Map<String, Integer> allItems;

	public InventoryAllocator(){
		this.allItems = new HashMap<String, Integer>();
	}
	private void populateAllItems(List<Warehouse> warehouses){
		for(Warehouse wh: warehouses){
			for(Map.Entry<String, Integer> item: wh.getInventory().entrySet()){
				String key = item.getKey();
				int value = item.getValue();

				boolean exists = this.allItems.containsKey(key);
				if(exists){
					int curr = this.allItems.get(key);
					this.allItems.replace(key, curr + value);
				} else {
					this.allItems.put(key, value);
				}
			}
		}
	}
	public Shipment findCheapestShipment(Map<String,Integer> order, List<Warehouse> warehouses){
		//First populate items so we know if we can fulfill orders
		populateAllItems(warehouses); 
		//while the order is not empty we should check if this warehouse
		//has our items. If so add it to the return List
		Shipment ship = new Shipment(); 
		for(Warehouse wh: warehouses){
			if(order.isEmpty()){
				break;
			}
			Warehouse suborder = new Warehouse(wh.getName());
			Iterator<Map.Entry<String, Integer>> it = order.entrySet().iterator();
			while(it.hasNext()){
				Map.Entry<String, Integer> item = it.next();
				String key = item.getKey();
				int value = item.getValue();

				int numberInWarehouse = wh.howManyOfItem(item.getKey());
				if(numberInWarehouse <= 0 || allItems.get(key) < value){
					continue;
				}
				if(numberInWarehouse >= value){
					suborder.addItem(key, value);
					it.remove();
				} else {
					suborder.addItem(key, numberInWarehouse);
					order.replace(key, value - numberInWarehouse);
				}
			}
			if(!suborder.isEmpty()){
				ship.addOrder(suborder);
			}
		}
		return ship;
	}
}