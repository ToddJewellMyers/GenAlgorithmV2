/* Todd J Myers
 * 07/20/20
 * genetic algorithm project 
 * project 4 multithreading 
 * 
 */

/*
Video game console, 3.0, 500
Cell phone 2, 0.25, 600
Kindle 2, 0.5, 300
Cell phone 3, 0.25, 600
Jewelry 3, 0.5, 500
Small cuckoo clock 3, 5.0, 1500
Kindle 5, 0.5, 300
4300


Video game console, 3.0, 500
Gaming laptop 2, 10.0, 2000
Video game console 2, 3.0, 500
Small cuckoo clock 2, 5.0, 1500
Cell phone 3, 0.25, 600
Small cuckoo clock 3, 5.0, 1500
Jewelry 4, 0.5, 500
Silver paperweight 4, 2.0, 400
Cell phone 5, 0.25, 600
Small cuckoo clock 5, 5.0, 1500
Silver paperweight 5, 2.0, 400
0

Cell phone, 0.25, 600
Silver paperweight, 2.0, 400
Silver paperweight 2, 2.0, 400
Kindle 3, 0.5, 300
Kindle 4, 0.5, 300
Video game console 4, 3.0, 500
2500

Cell phone, 0.25, 600
Gaming laptop, 10.0, 2000
Kindle, 0.5, 300
Video game console, 3.0, 500
Small cuckoo clock, 5.0, 1500
Silver paperweight, 2.0, 400
Jewelry 2, 0.5, 500
Kindle 2, 0.5, 300
Video game console 2, 3.0, 500
Small cuckoo clock 2, 5.0, 1500
Cell phone 3, 0.25, 600
Jewelry 3, 0.5, 500
Kindle 3, 0.5, 300
Silver paperweight 3, 2.0, 400
Gaming laptop 4, 10.0, 2000
Video game console 4, 3.0, 500
Jewelry 5, 0.5, 500
Kindle 5, 0.5, 300
0
 and keeps repeating 
 */




package genAlgorithmV2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class GeneticAlgorithm 
{
	
	public static final int POP_SIZE = 100;
	public static final int NUM_EPOCHS = 1000;
	public static final int NUM_THREADS = 1;
	
// reads in data file with specific format and creates and returns an ArrayList of the Item objects.
	public static ArrayList<Item> readData(String filename) throws FileNotFoundException
	{
		ArrayList<Item> items = new ArrayList<>();
		
		String name;
		double weight;
		int value;
		
		String holder;
		 
		Scanner in = new Scanner(new FileReader(filename));
		while(in.hasNext()) {
			holder = in.nextLine();
			String[] array1 = holder.split(", ", 3);
			name = array1[0];
			weight = Double.parseDouble(array1[1]);
			value = Integer.parseInt(array1[2]);
			Item placeholderItem = new Item(name, weight, value);
			items.add(placeholderItem);
		}
		return items;
	}
	
	//(**** i think my Error is here somewhere****) my outcome varies on each run and should not do so?
	//creates and returns an ArrayList of poplutionSize Chromosome objects that each contain the items, with their
	// included field randomly set to true and false 
	public static ArrayList<Chromosome> initializePopulation(ArrayList<Item> items, int populationSize)
	{
		ArrayList<Chromosome> population = new ArrayList<>();
		ArrayList<Item> itemlist = items;
		int popsize = populationSize;
		Chromosome chrom = new Chromosome(itemlist);
		int counter = 0;
		
		while(counter < popsize) {
			chrom = new Chromosome(itemlist);
			population.add(chrom);
			counter++;
		}
		
		return population;
	}
	
	// main that reads in data about the items in from a file called items.txt and then performs the steps described to complete this 
	public static void main(String[] args) throws FileNotFoundException
	{	
		
		// reads the smaller item list perfectly not so sure about with the longer list if this is workign perfectly 
		ArrayList<Item> items = readData("items.txt");
		
		//Create set of 10 random individuals
		//Add individuals to next generation
		ArrayList<Chromosome> population = initializePopulation(items, POP_SIZE);
		Collections.shuffle(population);
		
		GeneticThread thread = new GeneticThread();
		ArrayList<GeneticThread> threadArray = new ArrayList<GeneticThread>();
		
		for(int i = 0; i < NUM_THREADS; i++) {
			threadArray.add(thread);
		}
		
		thread.setPopulation(population);
		thread.start();
		
	}

}