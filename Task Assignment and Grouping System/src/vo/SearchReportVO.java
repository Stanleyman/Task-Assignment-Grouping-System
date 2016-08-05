package vo;

public class SearchReportVO {
private int groupId;
private int taskId;
private int employeeId;
private int taskUpdateId;
private String taskUpdate;
private String createdDate;
private String employeeName;
private String groupName;
private String taskDetail;
public String getEmployeeName() {
	return employeeName;
}
public void setEmployeeName(final String employeeName) {
	this.employeeName = employeeName;
}
public String getGroupName() {
	return groupName;
}
public void setGroupName(final String groupName) {
	this.groupName = groupName;
}
public String getTaskDetail() {
	return taskDetail;
}
public void setTaskDetail(final String taskDetail) {
	this.taskDetail = taskDetail;
}
public int getGroupId() {
	return groupId;
}
public void setGroupId(final int groupId) {
	this.groupId = groupId;
}
public int getTaskId() {
	return taskId;
}
public void setTaskId(final int taskId) {
	this.taskId = taskId;
}
public int getEmployeeId() {
	return employeeId;
}
public void setEmployeeId(final int employeeId) {
	this.employeeId = employeeId;
}
public int getTaskUpdateId() {
	return taskUpdateId;
}
public void setTaskUpdateId(final int taskUpdateId) {
	this.taskUpdateId = taskUpdateId;
}
public String getTaskUpdate() {
	return taskUpdate;
}
public void setTaskUpdate(final String taskUpdate) {
	this.taskUpdate = taskUpdate;
}
public String getCreatedDate() {
	return createdDate;
}
public void setCreatedDate(final String createdDate) {
	this.createdDate = createdDate;
}
}
