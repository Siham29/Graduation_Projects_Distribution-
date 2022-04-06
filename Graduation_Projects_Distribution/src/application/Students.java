package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Students {
	private String groupNumber;
	private List<String> StudentsNames = new ArrayList<>();
	String[] prefrances = new String[3];
	private String choosenPro;

	public Students(String groupNumber, List<String> studentsNames, String[] prefrances) {
		this.groupNumber = groupNumber;
		StudentsNames = studentsNames;
		this.prefrances = prefrances;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public List<String> getStudentsNames() {
		return StudentsNames;
	}

	public void setStudentsNames(List<String> studentsNames) {
		StudentsNames = studentsNames;
	}

	public String getChoosenPro() {
		return choosenPro;
	}

	public void setChoosenPro(String choosenPro) {
		this.choosenPro = choosenPro;
	}

	public String[] getPrefrances() {
		return prefrances;
	}

	public void setPrefrances(String[] prefrances) {
		this.prefrances = prefrances;
	}

	@Override
	public String toString() {
		return "Students [groupNumber=" + groupNumber + ", StudentsNames=" + StudentsNames + ", prefrances="
				+ Arrays.toString(prefrances) + ", choosenPro=" + choosenPro + "]";
	}
}
