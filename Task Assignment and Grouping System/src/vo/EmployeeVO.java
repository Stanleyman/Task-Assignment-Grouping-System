package vo;

//Function to implement the VO object for Employee details
public class EmployeeVO {
private String employeeId=null;
private String employeeName=null;
private String employeeDOJ=null;
private int grade;
public String getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(final String employeeId) {
	this.employeeId = employeeId;
}
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(final String employeeName) {
	this.employeeName = employeeName;
}
public String getDOJ() {
	return employeeDOJ;
}
public void setDOJ(final String dOJ) {
	employeeDOJ = dOJ;
}
public int getGrade() {
	return grade;
}
public void setGrade(final int grade) {
	this.grade = grade;
}

}
