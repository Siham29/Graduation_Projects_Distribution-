package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
	private ArrayList<Projects> projects = new ArrayList<>();
	private List<Supervisor> supervisors = new ArrayList<>();
	private int numberOfProjects;
	private List<Students> groups = new ArrayList<>();
	
	String Gfile;
	String Pfile;

	public int getNumberOfProjects() {
		return numberOfProjects;
	}

	public void setNumberOfProjects(int numberOfProjects) {
		this.numberOfProjects = numberOfProjects;
	}

	public List<Students> getGroups() {
		return groups;
	}

	public void setGroups(List<Students> groups) {
		this.groups = groups;
	}

	public ArrayList<Projects> getProjects() {
		return projects;
	}

	public void setProjects(ArrayList<Projects> projects) {
		this.projects = projects;
	}

	public void initializeProjects() throws FileNotFoundException, IOException {
		File file = new File("..\\AI-Project-1-1182319-1180548\\src\\projects.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String str;

		while ((str = br.readLine()) != null) {
			String[] i = str.split("@"); // project Id, SuperName, projectName, Details
			Supervisor superr = new Supervisor(i[1], i[0]);
			supervisors.add(superr);
			Projects pro = new Projects(i[0], superr, i[2], i[3]);
			pro.setSupervisorName(superr);

			numberOfProjects++;
			projects.add(pro);
		}
	}

	public void initializeSuper() {
		List<Supervisor> newsup = new ArrayList<>();
		for (int i = 0; i < supervisors.size(); i++) {
			int flag = 0;
			for (int j = i + 1; j < supervisors.size(); j++) {
				if (supervisors.get(i).getSuperName().trim().equals(supervisors.get(j).getSuperName().trim()))
					flag = 1;
			}
			if (flag == 0)
				newsup.add(supervisors.get(i));
		}

		supervisors = newsup;

		for (int i = 0; i < projects.size(); i++) {
			for (int j = 0; j < newsup.size(); j++) {
				if (projects.get(i).getSupervisorName().getSuperName().equals(newsup.get(j).getSuperName()))
					newsup.get(j).getSuperProjects().add((projects.get(i).getProjectId()));
			}
		}
	}

	public void initializeGroups() throws FileNotFoundException {
		File file = new File("..\\AI-Project-1-1182319-1180548\\src\\groups.txt");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st;
		try {
			while ((st = br.readLine()) != null) {
				String[] i = st.split("@"); // groupNum, StudentsName, Prefernces
				String[] j = i[1].split(","); // studentsnames
				List<String> s = new ArrayList<>();
				s = Arrays.asList(j); // studentsnames
				String[] jj = i[2].split(",");// Preferences

				Students g = new Students(i[0], s, jj);
				groups.add(g);
			}
		} catch (IOException ex) {
			System.out.println("Problem in reading groups.txt");
		}
	}

	public void initTpoicsRelated() {
		for (int i = 0; i < projects.size(); i++) {
			for (int j = 0; j < supervisors.size(); j++)
				if (projects.get(i).getSupervisorName().getSuperName().equals(supervisors.get(j).getSuperName()))
					projects.get(i).setTopicsRelated(supervisors.get(j).getSuperProjects());
		}
	}

	@Override
	public String toString() {
		return "Data [projects=" + projects + "\n, supervisors=" + supervisors + "\n, numberOfProjects=" + numberOfProjects
				+ "\n, groups=" + groups + "]";
	}
}
