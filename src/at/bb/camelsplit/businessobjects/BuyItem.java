package at.bb.camelsplit.businessobjects;

/**
 * This class represents an item that can be bought 
 * from an reseller
 * @author gue
 *
 */
public class BuyItem extends Item 
{
	double price;
	
	public BuyItem(String name, String description, double d)
	{
		this.name = name;
		this.description = description;
		this.price = d;
	}
	
	public String toString()
	{
		return "BuyItem(name=" + this.name + ", description=" + this.description + ", price=" + this.price + ")";
	}
}
