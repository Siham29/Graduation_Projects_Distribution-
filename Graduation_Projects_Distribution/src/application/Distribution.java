package application;

import java.util.ArrayList;
import java.util.Collections;

public class Distribution {
	private ArrayList<Students> students;
	private Data data;
	private double fitness = -1;
	private boolean isFitnessChanged = true;
	private int numberOfConflicts = 0;
	private double conflicts = 36;

	public double getConflicts() {
		return conflicts;
	}

	public void setConflicts(double conflicts) {
		this.conflicts = conflicts;
	}

	ArrayList<RandomSolutionProject> randomp = new ArrayList<>();

	// Getters and Setters

	public ArrayList<Students> getStudents() {
		return students;
	}

	public void setStudents(ArrayList<Students> students) {
		this.students = students;
	}

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public int getNumberOfConflicts() {
		return numberOfConflicts;
	}

	// Constructors
	public Distribution(Data data) {
		this.data = data;
		students = (ArrayList<Students>) data.getGroups();
	}

	public double getFitness() {
		if (isFitnessChanged == true) {
			fitness = calculateFitness();
			isFitnessChanged = false;
		}
		return fitness;
	}

	public double getFitness1() {
		fitness = moreFitted();
		return fitness;
	}

	public ArrayList<RandomSolutionProject> getRandomp() {
		isFitnessChanged = true;
		return randomp;
	}

	public Distribution intialize1() { // for each group get random project
		int cnt = 0; // counter to forEach loop.
		boolean[] mask = new boolean[data.getProjects().size() + 10];
		for (int i = 0; i < mask.length; i++) {
			mask[i] = false;
		}

		//get all groups
		ArrayList<Students> cpy = new ArrayList<Students>();
		for (int i = 0; i < students.size(); i++) {
			cpy.add(students.get(i));
		}
		Collections.shuffle(cpy);

		for (Students student : cpy) {
			RandomSolutionProject rand = new RandomSolutionProject();
			rand.setGroup(student);

			int ch1 = Integer.parseInt(student.getPrefrances()[0]);
			int ch2 = Integer.parseInt(student.getPrefrances()[1]);
			int ch3 = Integer.parseInt(student.getPrefrances()[2]);

//			System.out.println(ch1 + "-" + ch2 + "-" + ch3);
			int flag = 0;
			if (mask[ch1] == false) {
				student.setChoosenPro(student.getPrefrances()[0]);
				rand.setProj(data.getProjects().get(ch1 - 1));
				mask[ch1] = true;
				flag = 1;
			} else if (flag == 0 && mask[ch2] == false) {
				student.setChoosenPro(student.getPrefrances()[1]);
				rand.setProj(data.getProjects().get(ch2 - 1));
				mask[ch2] = true;
				flag = 1;
			} else if (flag == 0 && mask[ch3] == false) {
				student.setChoosenPro(student.getPrefrances()[2]);
				rand.setProj(data.getProjects().get(ch3 - 1));
				mask[ch3] = true;
				flag = 1;
			} else if (flag == 0) {
				for (int j = 1; j <= 37; j++) {
					if (mask[j] == false) {
						student.setChoosenPro(j + "");
						rand.setProj(data.getProjects().get(j - 1));
						mask[j] = true;
						flag = 1;
						break;
					}
				}
			}

			for (Students std1 : cpy) {
				for (Students std2 : students) {
					if (std2.getGroupNumber().equals(std1.getGroupNumber())) {
							std1.setChoosenPro(std2.getChoosenPro());
							std1.setGroupNumber(std2.getGroupNumber());
							std1.setPrefrances(std2.getPrefrances());
							std1.setStudentsNames(std2.getStudentsNames());
							break;
					}
				}
			}
//			System.out.println(student);

			if (flag == 0) {
				System.err.println("ERROR!!!");
				System.exit(1);
			}
			randomp.add(rand);
		}
		return this;
	}

	// Calculate Conflicts and Fitness
	private double calculateFitness() {
		double cpy_conflicts = 36;

		for (int i = 0; i < randomp.size(); i++) {
			boolean ch1 = randomp.get(i).getProj().getProjectId().equals(randomp.get(i).getGroup().prefrances[0]);
			boolean ch2 = randomp.get(i).getProj().getProjectId().equals(randomp.get(i).getGroup().prefrances[1]);
			boolean ch3 = randomp.get(i).getProj().getProjectId().equals(randomp.get(i).getGroup().prefrances[2]);
			if (ch1 == true) {
				cpy_conflicts -= 1;
			} else if (ch2 == true) {
				cpy_conflicts -= 1;
			} else if (ch3 == true) {
				cpy_conflicts -= 1;
			} else {
				// there is a conflicts!
			}
		}

		new AI().min_conflicts = Math.min(new AI().min_conflicts, cpy_conflicts);
		numberOfConflicts = (int) cpy_conflicts;
		return 1 / (double) (cpy_conflicts + 1);
	}

	private double moreFitted() {
		for (int i = 0; i < randomp.size(); i++) {
			for (int k = 0; k < data.getGroups().size(); k++) {
				if (randomp.get(i).getGroup().getGroupNumber().equals(data.getGroups().get(k).getGroupNumber())) {
					if (randomp.get(i).getProj().getProjectId().equals(data.getGroups().get(k).getPrefrances()[0]))
						fitness += 0.04;
					if (randomp.get(i).getProj().getProjectId().equals(data.getGroups().get(k).getPrefrances()[1]))
						fitness += 0.03;
					if (randomp.get(i).getProj().getProjectId().equals(data.getGroups().get(k).getPrefrances()[2]))
						fitness += 0.02;

					else {
						for (int j = 0; j < data.getProjects().size(); j++) { // go throug all projects that we have
							if (data.getProjects().get(j).getProjectId().equals(data.getGroups().get(k).getPrefrances()[0]))
								for (int s = 0; s < data.getProjects().get(j).getTopicsRelated().size() - 1; s++)
									if (randomp.get(i).getProj().getProjectId()
											.equals(data.getProjects().get(j).getTopicsRelated().get(s)))
										fitness += 0.013;

							if (data.getProjects().get(j).getProjectId().equals(data.getGroups().get(k).getPrefrances()[1]))
								for (int s = 0; s < data.getProjects().get(j).getTopicsRelated().size() - 1; s++)
									if (randomp.get(i).getProj().getProjectId()
											.equals(data.getProjects().get(j).getTopicsRelated().get(s)))
										fitness += 0.012;

							if (data.getProjects().get(j).getProjectId().equals(data.getGroups().get(k).getPrefrances()[2]))
								for (int s = 0; s < data.getProjects().get(j).getTopicsRelated().size() - 1; s++)
									if (randomp.get(i).getProj().getProjectId()
											.equals(data.getProjects().get(j).getTopicsRelated().get(s)))
										fitness += 0.011;
						}
					}
				}
			}
		}
		return fitness;
	}

	public String toString() {
		String returnValue = new String();
		for (int x = 0; x < randomp.size(); x++) {
			returnValue += randomp.get(randomp.size() - 1);
		}
		return returnValue;
	}
}
