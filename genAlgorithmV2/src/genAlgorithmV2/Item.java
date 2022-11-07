package genAlgorithmV2;


public class Item 
{
	
	private String name;
	private double weight;
	private int value;
	private boolean included;
	
	// Initializes the items fields to the values that are passed in; the included field is initialized to false
	public Item(String name, double weight, int value)
	{
		this.name = name;
		this.weight = weight;
		this.value = value;
		included = false;
	}
	// Initializes this items fields to the be the same as the other items
	public Item(Item other) 
	{
		name = other.getName();
		weight = other.getWeight();
		value = other.getValue();
		included = other.isIncluded();
	}
	
	public String getName()
	{
		return name;
	}
	// getter for the items weight 
	public double getWeight() 
	{
		return weight;
	}
	// getter for the value 
	public int getValue() 
	{
		return value;
	}
	// getter for isIncluded 
	public boolean isIncluded() 
	{
		return included;
	}
	// setter for the items included field 
	public void setIncluded(boolean included) 
	{
		this.included = included;
	}
	// displays the item in the form below 
	public String toString() {
		return name + "(" + getWeight() + " lbs, $" + getValue() + ")";
	}
	
	
}