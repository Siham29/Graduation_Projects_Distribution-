package application;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Population {
	private ArrayList<Distribution> distributions;

	// function to get solutions (getter function)
	public ArrayList<Distribution> getDistributions() {
		return distributions;
	}

	public void setDistributions(ArrayList<Distribution> distributions) {
		this.distributions = distributions;
	}

	// constructors
	public Population(int size, Data data) {
		distributions = new ArrayList<Distribution>(size);
		IntStream.range(0, size).forEach(x -> {
			distributions.add(new Distribution(data).intialize1());
		});
	}

	public Population(ArrayList<Distribution> solutions) {
		this.distributions = solutions;
	}

	// sort solutions using fitness
	public Population sortByFitness() {
		distributions.sort((distribution1, distribution2) -> {
			int returnValue = 0;
			if (distribution1.getFitness() > distribution2.getFitness())
				returnValue = -1;
			else if (distribution1.getFitness() < distribution2.getFitness())
				returnValue = 1;
			return returnValue;
		});
		return this;
	}
}
