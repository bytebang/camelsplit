package at.bb.camelsplit.businessobjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This class represents an order which can be placed at a webshop.
 * It contains a list of Items (actually subclasses of items, because
 * this class is abstract and can not be instantiated)
 * 
 * @author gue
 *
 */
public class Order 
{
	protected List<Item> items = new ArrayList<>();
	
	/**
	 * ctor
	 * @param newItem
	 * @return
	 */
	public Order addItem(Item newItem)
	{
		if(newItem != null)
		{
			this.items.add(newItem);
		}
		
		return this;
	}
	
	/**
	 * Returns a copy of the items for this order
	 * @return
	 */
	public List<Item> getItems()
	{
		return Collections.unmodifiableList(this.items);
	}
	
	/**
	 * Prints some information
	 */
	public String toString()
	{
		return "Order #" + this.hashCode() + " with " + items.size() + " items";
	}
	
	
}
