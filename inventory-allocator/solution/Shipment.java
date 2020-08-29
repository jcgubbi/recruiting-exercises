package solution;

import java.util.List;
import java.util.LinkedList;

public class Shipment{
	private List<Warehouse> shipment;

	public Shipment(){
		this.shipment = new LinkedList<Warehouse>();
	}
	public Shipment(List<Warehouse> shipment){
		this.shipment = shipment; 
	}
	public void addOrder(Warehouse order){
		shipment.add(order);
	}
	public List<Warehouse> getShipment(){
		return shipment;
	}
}