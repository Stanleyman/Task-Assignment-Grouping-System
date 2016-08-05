package vo;

public class AssignOwnerVO {
private String employeeName=null;
private int employeeId=0;


public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(final int employeeId) {
	this.employeeId = employeeId;
}
public int getGroupId() {
	return groupId;
}
public void setGroupId(final int groupId) {
	this.groupId = groupId;
}
private int groupId;
private boolean owner=false;
private String groupName;
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(final String employeeName) {
	this.employeeName = employeeName;
}


public boolean isOwner() {
	return owner;
}
public void setOwner(final boolean owner) {
	this.owner = owner;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(final String groupName) {
	this.groupName = groupName;
}

}
