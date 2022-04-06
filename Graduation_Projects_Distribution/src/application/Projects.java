package application;

import java.util.ArrayList;
import java.util.List;

public class Projects {
	private String projectId;
	private Supervisor supervisorName;
	private String projectName;
	List<String> topicsRelated = new ArrayList<>();
	private String projectDes;

	public Projects(String projectId, Supervisor supervisorName, String projectName, String projectDes) {
		this.projectId = projectId;
		this.supervisorName = supervisorName;
		this.projectName = projectName;
		this.projectDes = projectDes;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public Supervisor getSupervisorName() {
		return supervisorName;
	}

	public void setSupervisorName(Supervisor supervisorName) {
		this.supervisorName = supervisorName;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public List<String> getTopicsRelated() {
		return topicsRelated;
	}

	public void setTopicsRelated(List<String> topicsRelated) {
		this.topicsRelated = topicsRelated;
	}

	public String getProjectDes() {
		return projectDes;
	}

	public void setProjectDes(String projectDes) {
		this.projectDes = projectDes;
	}

	@Override
	public String toString() {
		return "Projects [projectId=" + projectId + ", supervisorName=" + supervisorName + ", projectName=" + projectName
				+ ", topicsRelated=" + topicsRelated + ", projectDes=" + projectDes + "]";
	}
}
