package application;

import java.util.ArrayList;
import java.util.List;

public class Supervisor {
	private List<String> superProjects = new ArrayList<>();
	private String superName;
	private String projectName;

	public Supervisor(String superName, String projectName) {
		this.superName = superName;
		this.projectName = projectName;
		this.superProjects.add(projectName);
	}

	public List<String> getSuperProjects() {
		return superProjects;
	}

	public void setSuperProjects(List<String> superProjects) {
		this.superProjects = superProjects;
	}

	public String getSuperName() {
		return superName;
	}

	public void setSuperName(String superName) {
		this.superName = superName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Override
	public String toString() {
		return "Supervisor [superProjects=" + superProjects + ", superName=" + superName + ", projectName=" + projectName
				+ "]";
	}
}
