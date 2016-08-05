<%@page import="vo.AssignTaskVO"%>
<%@page import="dao.EmployeeDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="vo.AssignOwnerVO"%>
<%@page import="dao.GroupDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UpdateTaskStatus</title>
</head>

<%
	String message = null;

	if (request.getAttribute("message") != null) {
		message = request.getAttribute("message").toString();
		request.setAttribute("message", "");
	} else {
		message = "";
	}
%>

<body background="2.jpg" style="">
	<%@  include file="Header.jsp"%>
	<br>
	<br>
	<br>
	<br>
	
	<center>
		<span style="color: red"><%=message%></span>
	</center>
	<%
		message = "";
	%>
	
	<form action="UpdateTaskStatus" method="post">

		<table align="center" height="10">
<center><h3><font color="green">Update Task</font></h3></center>
<tr>
&nbsp;
&nbsp;
</tr>
			<tr>
				<td >Group Name :</td>
				<td ><select name="GroupNames"
					id="GroupNames" onchange="loadTaskDetails()"><option
							value="-1">select</option>
						<%
							GroupDAO groupDao = new GroupDAO();
							ArrayList<AssignOwnerVO> groupNames = new ArrayList<AssignOwnerVO>();
							groupNames = groupDao.getGroupNames();
							for (AssignOwnerVO localVo : groupNames) {

								if (request.getAttribute("status") == "1"
										|| request.getAttribute("status") == "2") {

									if (Integer.valueOf((String) request
											.getAttribute("groupId")) == localVo.getGroupId()) {
										/* request.setAttribute("groupId",localVo.getGroupId()); */
						%>
						<option value="<%=localVo.getGroupId()%>" selected="selected"><%=localVo.getGroupName()%></option>
						<%
							} else {
						%>

						<option value="<%=localVo.getGroupId()%>"><%=localVo.getGroupName()%></option>
						<%
							}
								} else {
						%>

						<option value="<%=localVo.getGroupId()%>"><%=localVo.getGroupName()%></option>
						<%
							}
							}
						%>

				</select>
				</td>
			</tr>
<tr>
				<td><br>
				</td>
			</tr>


			<tr>
				<td>Employee Name:</td>
				<td><select id="EmployeeNames"
					name="EmployeeNames">
						<option value="-1">select</option>

						<%
							if (request.getAttribute("status") != null) {
												if (request.getAttribute("status").equals("1")
														|| request.getAttribute("status") == "2") {

													ArrayList<AssignOwnerVO> employeeNames = (ArrayList) request
															.getAttribute("employeeNames");
													for (AssignOwnerVO localEmployeeVo : employeeNames) {
														if (request.getAttribute("status") == "2")

														{
															/* request.setAttribute("status","2"); */
															if (Integer.valueOf((String) request
																	.getAttribute("employeeId")) == localEmployeeVo
																	.getEmployeeId()) {
																/* request.setAttribute("employeeId",localEmployeeVo.getEmployee_Id()); */
						%>
						<option value="<%=localEmployeeVo.getEmployeeId()%>"
							selected="selected"><%=localEmployeeVo.getEmployeeName()%></option>
						<%
							} else {
						%>

						<option value="<%=localEmployeeVo.getEmployeeId()%>"><%=localEmployeeVo.getEmployeeName()%></option>
						<%
							}
														} else {
						%>

						<option value="<%=localEmployeeVo.getEmployeeId()%>"><%=localEmployeeVo.getEmployeeName()%></option>
						<%
							}
									}
								}
							}
						%>



				</select>
				</td>
				<td><input type="submit" name="getEmployees" id="getEmployees"
					value="Get Employees" /></td>
			</tr><tr>
				<td><br>
				</td>
			</tr>
			<tr>
				<td >Task Detail :</td>
				<td ><select id="TaskDetails"
					name="TaskDetails">
						<option value="-1">select</option>
						<%	
							if (request.getAttribute("status") != null) {
								
								if (request.getAttribute("status").equals("2")) {
									
									ArrayList<AssignTaskVO> taskDetails = (ArrayList<AssignTaskVO>) request
											.getAttribute("taskDetails");
									for (AssignTaskVO assignTaskVo : taskDetails) {
										
										
						%>
						<option value="<%=assignTaskVo.getTaskId()%>" ><%=assignTaskVo.getTaskDetail()%></option>
						<%
							} }}
						%>

						

				</select>
				</td>
				<td><input type="submit" name="getTasks" id="getTasks"
					value="Get Tasks" /></td>
			</tr><tr>
				<td><br>
				</td>
			</tr>
			<tr>
				<td >Task Update :</td>
				<td ><input type="text" name="TaskUpdate"
					id="TaskUpdate" value="" />
				</td>
			</tr><tr>
				<td><br>
				</td>
			</tr>
			<tr>
			<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="submit" id="submit"
					value="Update Status" /></td>
					</tr>
		</table>
	</form>
	<br>
<br>

	<%@  include file="Footer.jsp"%>
</body>
</html>