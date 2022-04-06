package application;

import java.util.Comparator;

public class RandomSolutionProject {
	private Projects proj;
	private Students group;

	public RandomSolutionProject(Projects proj, Students group) {
		this.proj = proj;
		this.group = group;
	}

	public RandomSolutionProject() {

	}

	public Projects getProj() {
		return proj;
	}

	public void setProj(Projects proj) {
		this.proj = proj;
	}

	public Students getGroup() {
		return group;
	}

	public void setGroup(Students group) {
		this.group = group;
	}

	int i = 0;

	@Override
	public String toString() {
		i++;
		return "RandomSolutionProject [proj=" + proj + ", group=" + group + "]\n " + i + "\n\n";
	}
	
	public static Comparator<RandomSolutionProject> StuNameComparator = new Comparator<RandomSolutionProject>() {
		public int compare(RandomSolutionProject s1, RandomSolutionProject s2) {
			String group1 = s1.getGroup().getGroupNumber();
			String group2 = s2.getGroup().getGroupNumber();
		   	   
			//ascending order
			return extractInt(group1) - extractInt(group2);

	    }
	};
	    
	static int extractInt(String s) {
		String num = s.replaceAll("\\D", "");
	    // return 0 if no digits found
	    return num.isEmpty() ? 0 : Integer.parseInt(num);
	}
	
	public static Comparator<RandomSolutionProject> ProNumComparator = new Comparator<RandomSolutionProject>() {
		public int compare(RandomSolutionProject s1, RandomSolutionProject s2) {
			String pro1 = s1.getProj().getProjectId();
			String pro2 = s2.getProj().getProjectId();
		   	   
			//ascending order
			return extractInt(pro1) - extractInt(pro2);

	    }
	};
	    
	

}
