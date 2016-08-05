<%@page import="vo.SearchReportVO"%>
<%@page import="vo.AssignTaskVO"%>
<%@page import="vo.AssignOwnerVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.GroupDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SearchReport</title>
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
	
	<form action="SearchUpdateController" method="post">

		<table align="center" height="10">
<center><h3><font color="green">Search Report</font></h3></center>
<tr>
&nbsp;
&nbsp;
</tr>
<center>
	<tr>
				<td >&nbsp;&nbsp;&nbsp;Group Name :</td>
				<td ><select name="GroupNames"
					id="GroupNames">
					<option value="-1">select</option>
					<%
							GroupDAO groupDao = new GroupDAO();
							ArrayList<AssignOwnerVO> groupNames = new ArrayList<AssignOwnerVO>();
							groupNames = groupDao.getGroupNames();
							for (AssignOwnerVO localVo : groupNames) {

								if (request.getAttribute("status")!=null) {

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
			
			</tr><tr>
				<td><br>
				</td>
			</tr>
			<tr>
				<td >&nbsp;&nbsp;&nbsp;Employee Name:</td>
				<td ><select id="EmployeeNames"
					name="EmployeeNames">
						<option value="-1">select</option>
						
						<%
													if (request.getAttribute("status") != null) {
																		if (!request.getAttribute("status").equals("3")) {

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

						

				</select></td>
				<td><input type="submit" name="getEmployees" id="getEmployees"
					value="Get Employees" /></td>
			</tr><tr>
				<td><br>
				</td>
			</tr>
			<tr>
				<td >&nbsp;&nbsp;&nbsp;Task Detail :</td>
				<td><select id="TaskDetails"
					name="TaskDetails">
						<option value="-1">select</option>
<%	
							if (request.getAttribute("status") != null) {
								
								if (!request.getAttribute("status").equals("1")) {
									
									ArrayList<AssignTaskVO> taskDetails = (ArrayList<AssignTaskVO>) request.getAttribute("taskDetails");
									for (AssignTaskVO assignTaskVo : taskDetails) {
										
										
						%>
						<option value="<%=assignTaskVo.getTaskId()%>" ><%=assignTaskVo.getTaskDetail()%></option>
						<%
							} }}
						%>

				</select></td>
				<td><input type="submit" name="getTasks" id="getTasks"
					value="Get Tasks" /></td>
			</tr><tr>
				<td><br>
				</td>
			</tr>
			<tr>
				<td >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="submit" name="viewReport" value="View Report" />
				</td>
			</tr>
			</table>
			</form>
			<br>
			<br>
			<br>
			<br>
			<%
		
			if(request.getAttribute("report")!=null)
			{
				if(request.getAttribute("taskDetails")!=null) 
				{
				out.write("<h2>Report : </h2>");			
				ArrayList<SearchReportVO> reportDetails=(ArrayList<SearchReportVO>)request.getAttribute("taskDetails"); 
				out.write("<table width='100%' border='1' bordercolor='black' style='border-collapse:collapse;'>");
				out.write("<tr>");
				out.write("<td><h4>Group Name :</h4></td>");
				out.write("<td><h4>Employee Name :</h4></td>");
				out.write("<td><h4>Task Details :</h4></td>");
				out.write("<td><h4>Task Update :</h4></td>");
				out.write("<td><h4>Created Date :</h4></td>");
				out.write("</tr>");
				for(SearchReportVO report:reportDetails)
				{
				
				out.write("<tr>");
				out.write("<td>");
				out.write(report.getGroupName());
				out.write("</td>");
				out.write("<td>");
				out.write(report.getEmployeeName());
				out.write("</td>");
				out.write("<td>");
				out.write(report.getTaskDetail());
				out.write("</td>");
				out.write("<td>");
				out.write(report.getTaskUpdate());
				out.write("</td>");
				out.write("<td>");
				out.write(report.getCreatedDate());
				out.write("</td>");
				out.write("</tr>");
			
				}
				out.write("</table>");
				
				}
				else{
					out.write("<h1>No Records Found</h1>");
				}
			
			} %>
			<%@ include file="Footer.jsp"%>
</body>
</html>