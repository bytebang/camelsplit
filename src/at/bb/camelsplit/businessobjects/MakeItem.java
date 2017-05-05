package at.bb.camelsplit.businessobjects;

/**
 * This class represents an item that  is somehow
 * handcrafted
 * @author gue
 *
 */
public class MakeItem extends Item 
{
	int hoursToBuild;
	
	public MakeItem(String name, String description, int effort)
	{
		this.name = name;
		this.description = description;
		this.hoursToBuild = effort;
	}
	
	public String toString()
	{
		return "MakeItem(name=" + this.name + ", description=" + this.description + ", hoursToBuild=" + this.hoursToBuild + ")";
	}
}
