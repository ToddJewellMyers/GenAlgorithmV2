package genAlgorithmV2;

import java.util.ArrayList;
import java.util.Random;

@SuppressWarnings("serial")
public class Chromosome extends ArrayList<Item> implements Comparable<Chromosome> 
{
// used for random number generation
	private static Random rng;
	public static long dummy = 0;
	
	public Chromosome()
	{
		
	}
	//adds a copy of each of the items passed in to this chromosome. uses a random number to decide whether each 
	//items included field is set to true or false
	public Chromosome(ArrayList<Item> items)
	{
		int number;
		rng = new Random();
		
		//This for loop will go through every item in the passed in ArrayList
		for (int counter = 0; counter < items.size(); counter++) {
			number = rng.nextInt(2);
			//If rng generates a 1, the item inclusion is set to true and it is added to the Chromosome list
			//If it generates a 0, its set to false and added to the Chromosome list
			
			Item tempItem = new Item(items.get(counter));
			
			if(number == 1) {
				tempItem.setIncluded(true);
			}else {
				tempItem.setIncluded(false);
			}
			this.add(tempItem);
		}
	}
	//Creates and returns a	new	child chromosome by	performing the crossover	
	//operation	on this chromosome	and	the	other one that is passed in
	public Chromosome crossover(Chromosome other) 
	{
		Chromosome child = new Chromosome();
		int number;
		rng = new Random();
		//This for loop will go through every item in the chromosome
		for (int counter = 0; counter < this.size(); counter++) 
		{
			number = rng.nextInt(2);
			
			if(number == 1) {
				Item tempItem = new Item(this.get(counter));
				child.add(tempItem);
			}else {
				Item otherItem = new Item(other.get(counter));
				child.add(otherItem);
			}
		}
		return child;
	}
	//performs the mutation operation on this chromosome (each item in this chromosome use a random number to decide 
	//whether or not to flip its included field from true or false or vice versa
	public void mutate() 
	{
		int number;
		rng = new Random();
		//This for loop will go through and check every item currently in the list
		for (int counter = 0; counter < this.size(); counter++)
		{
			//Will generate a 0 or a 1 in the number variable
			number = rng.nextInt(2);
			if(number == 1) {
				//If rng generates a 1, this flips the current items inclusion
				this.get(counter).setIncluded(!this.get(counter).isIncluded());
			}
		}
	}
	// returns the fitness of this chromosome. if the sum of all the included items weights are greater than 10, the fitness
	// is zero. otherwise the fitness is equal to the sum of all of the included items values.
	public int getFitness()
	{
		dummy = 0;
		for(int i = 0; i < this.size() * 1000; i++) {
			dummy += i;
		}
		
		double totalweight = 0;
		int totalvalue = 0;
		//This for loop will go through and check every item currently in the list
		for (int counter = 0; counter < this.size(); counter++)
		{
			//Checks if the current value is included, if not it is skipped
			if(this.get(counter).isIncluded() == true) {
				totalweight = totalweight + this.get(counter).getWeight();
				totalvalue = totalvalue + this.get(counter).getValue();
			}
		}
		if(totalweight > 10.0)
		{
			return 0;
		}else {
			return totalvalue;
		}
	}
	//returns -1 if this chromosomes fitness is greater than the other fitness, +1 if this chromosome fitness is less than the other ones,
	// and 0, if their fitness is the same.
	public int compareTo(Chromosome other)
	{
		if (getFitness() > other.getFitness()) {
			return -1;
		}else if(getFitness() < other.getFitness()) {
			return 1;
		}else {
			return 0;
		}
	}
	// Displays the name, weight and value of all items in this chromosome whose included value iis true, followed byu the fitness of this 
	//chromosome.
	public String toString() 
	{
		//Initialization of returnstring
		String returnstring = "";
		//This for loop will go through and check every item currently in the list
		for (int counter = 0; counter < this.size(); counter++) {
			//Checks if the current value is included, if not it is skipped
			if (this.get(counter).isIncluded() == true) {
				//Appends the included items values on to the returnstring
				returnstring = returnstring + this.get(counter).getName() + ", " +
				this.get(counter).getWeight() + ", " + this.get(counter).getValue() + "\n";
			}
		}
		returnstring = returnstring + getFitness() + "\n";
		return returnstring;
	}
	
}
