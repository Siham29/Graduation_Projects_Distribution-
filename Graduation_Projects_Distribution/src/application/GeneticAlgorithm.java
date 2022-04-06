package application;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class GeneticAlgorithm {
	private Data data;

	public GeneticAlgorithm(Data data) {
		this.data = data;
	}

	// CrossOver and Mutation together between different distributions
	public Population evolve(Population population) {
		return mutatePopulation(crossoverPopulation(population));
	}

	Population crossoverPopulation(Population population) {
		Population crossoverPopulation = new Population(population.getDistributions().size(), data);
		IntStream.range(0, AI.NUMB_OF_ELITE_DISTRIBUTIONS)
				.forEach(x -> crossoverPopulation.getDistributions().set(x, population.getDistributions().get(x)));
		IntStream.range(AI.NUMB_OF_ELITE_DISTRIBUTIONS, population.getDistributions().size()).forEach(x -> {
			if (AI.CROSSOVER_RATE > Math.random()) {
				Distribution dist1 = selectTournamentPopulation(population).sortByFitness().getDistributions().get(0);
				Distribution dist2 = selectTournamentPopulation(population).sortByFitness().getDistributions().get(0);
				crossoverPopulation.getDistributions().set(x, crossoverDistribution(dist1, dist2));
			} else
				crossoverPopulation.getDistributions().set(x, population.getDistributions().get(x));
		});

		return crossoverPopulation;
	}

	Population selectTournamentPopulation(Population population) {
		Population tournamentPopulation = new Population((AI.TOURNAMENT_SELECTION_SIZE), data);
		IntStream.range(0, AI.TOURNAMENT_SELECTION_SIZE).forEach(x -> {
			tournamentPopulation.getDistributions().set(x,
					population.getDistributions().get((int) (Math.random() * population.getDistributions().size())));
		});
		return tournamentPopulation;
	}

	Distribution crossoverDistribution(Distribution dist1, Distribution dist2) {
		Distribution crossoverDist = new Distribution(data).intialize1();
		IntStream.range(0, crossoverDist.getStudents().size()).forEach(x -> {
			if (Math.random() > 0.5)
				crossoverDist.getStudents().set(x, dist1.getStudents().get(x));
			else
				crossoverDist.getStudents().set(x, dist2.getStudents().get(x));
		});
		return crossoverDist;
	}

	Population mutatePopulation(Population population) {
		Population mutatePopulation = new Population(population.getDistributions().size(), data);
		ArrayList<Distribution> distributions = mutatePopulation.getDistributions();
		IntStream.range(0, AI.NUMB_OF_ELITE_DISTRIBUTIONS).forEach(x -> distributions.set(x, population.getDistributions().get(x)));
		IntStream.range(AI.NUMB_OF_ELITE_DISTRIBUTIONS, population.getDistributions().size()).forEach(x -> {
			distributions.set(x, mutateDistribution(population.getDistributions().get(x)));
		});
		return mutatePopulation;
	}

	Distribution mutateDistribution(Distribution mutateDistr) {
		Distribution distr = new Distribution(data).intialize1(); // randomly
		IntStream.range(0, mutateDistr.getStudents().size()).forEach(x -> {
			if (AI.MUTATION_RATE > Math.random())
				mutateDistr.getStudents().set(x, distr.getStudents().get(x));
		});
		return mutateDistr;
	}
}
