package genAlgorithmV2;

import java.util.ArrayList;

public class BruteForce extends Exception{
	
	static ArrayList<Item> optimallist;
	static ArrayList<Item> initiallist;
	static int listsize;
	
	public static ArrayList<Item> getOptimalSet(ArrayList<Item> items){
		
		//Exception for when items > 10
		if(items.size() > 10) {
			throw new IllegalArgumentException("Number of items in list is greater than 10.");
		}
		
		int counter = 0;
		listsize = items.size();
		initiallist = new ArrayList<Item>(items);
		optimallist = new ArrayList<Item>(items);
		optimallist = optimalhelper(items, counter);
		return optimallist;
	}
	
	public static double getFitness(ArrayList<Item> items) {
		
		double totalweight = 0;
		
		for(int i = 0; i < items.size(); i++) {
			totalweight += items.get(i).getWeight();
		}
		
		if(totalweight > 10) {
			return 0;
		}else {
			
			double totalworth = 0;
			
			for(int i = 0; i < items.size(); i++) {
				totalworth += items.get(i).getValue();
			}
			
			return totalworth;		
		}
	}
	
	//Recursively searches every subset
	public static ArrayList<Item> optimalhelper(ArrayList<Item> items, int counter) {
		
		//If new subset fitness is better than current list fitness, overwrite
		if(getFitness(items) > getFitness(optimallist)) {
			optimallist = new ArrayList<Item>(items);
		}

		//If more items can be removed from list, recursively call with new subset
		if(items.size() > 1) {
			items.remove((items.size() - 1) - counter);
			optimalhelper(items, counter);
		}
		
		items = initiallist;
		counter++;
		
		//All subset options have been exhausted
		if(counter == listsize) {
			return optimallist;
		}
		
		optimalhelper(items, counter);
		
		return optimallist;
	}
}
