package genAlgorithmV2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticThread extends Thread{

	ArrayList<Chromosome> population;
	Chromosome best;
	
	public static final int POP_SIZE = 100;
	public static final int NUM_EPOCHS = 1000;
	public static final int NUM_THREADS = 1;
	
	public void run() {
		
		Random rng = new Random();
		int num;
		int maincounter = 0;
		
		while(maincounter < NUM_EPOCHS) {
			//Pair off and perform crossovers making parents and producing children 
			int counter = 0;
			int numchild = 0;
			while(numchild < 10) {
				Chromosome parent1 = population.get(counter);
				Chromosome parent2 = population.get(counter+1);
				Chromosome child = parent1.crossover(parent2);
				population.add(child);
				numchild++;
				counter++;
			}
			//Mutate 10% of population
			int mutatecounter = 0;
			while(mutatecounter < 2) {
				num = rng.nextInt(20);
				population.get(num).mutate();
				mutatecounter++;
			}
			//Sort individuals by fitness
			Collections.sort(population);
			
			//Clear bottom half of population
			ArrayList<Chromosome> top10 = new ArrayList<>();
			
			for(int i = 0; i < 10; i++) {
				top10.add(population.get(i));
			}
			population = new ArrayList<Chromosome>(top10);
			maincounter++;
		}
		
		best = population.get(0);
		System.out.println(best.toString());
	}
	
	public void setPopulation(ArrayList<Chromosome> pop) {
		population = pop;
	}
	
	public Chromosome getBestChromosome() {
		return best;
	}
	
}
