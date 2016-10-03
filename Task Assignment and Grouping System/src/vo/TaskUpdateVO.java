package vo;

//Function to implement the VO object for task update related data
public class TaskUpdateVO {
private String taskUpdates;
private int taskId;
private int groupId;
private int employeeId;
public String getTaskUpdates() {
	return taskUpdates;
}
public void setTaskUpdates(final String taskUpdates) {
	this.taskUpdates = taskUpdates;
}
public int getTaskId() {
	return taskId;
}
public void setTaskId(final int taskId) {
	this.taskId = taskId;
}
public int getGroupId() {
	return groupId;
}
public void setGroupId(final int groupId) {
	this.groupId = groupId;
}
public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(final int employeeId) {
	this.employeeId = employeeId;
}
}
