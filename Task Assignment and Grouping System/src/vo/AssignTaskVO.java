package vo;

public class AssignTaskVO {
	private int groupId;
	private int employeeId;
	private String taskETA;
	private String taskDetail;
	private int taskId;
	public int getGroupId() {
		return groupId;
	}
	public int getTaskId() {
		return taskId;
	}
	public void setTaskId(final int taskId) {
		this.taskId = taskId;
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
	public String getETA() {
		return taskETA;
	}
	public void setETA(final String eTA) {
		taskETA = eTA;
	}
	public String getTaskDetail() {
		return taskDetail;
	}
	public void setTaskDetail(final String taskDetail) {
		this.taskDetail = taskDetail;
	}
	


}
