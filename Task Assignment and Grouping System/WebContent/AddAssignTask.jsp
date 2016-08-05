<%@page import="dao.EmployeeDAO"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="dao.GroupDAO" %>
    <%@page import="vo.AssignOwnerVO" %>
    <%@page import="java.util.ArrayList" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Assign Task</title>
</head>
<script type="text/javascript">

function showValue() {
	var x = document.getElementById("id1");
	x.innerHTML = ' Format :"dd/mm/yyyy" ';

}

function showValue1() {
	var x = document.getElementById("id1");
	x.innerHTML = '';
}

		
	</script>
<%

String message = null;

if (request.getAttribute("message")!=null) {
	message = request.getAttribute("message").toString();
	request.setAttribute("message","");
}
else
{
	message="";
}

%>
<body background="2.jpg" style="">
	<%@  include file="Header.jsp"%>
	<br>
	<br>
	<br>
	<br>

	
	<center><span style="color: red"><h3><%=message%></h3></span></center>
<%message=""; %>

<form action="AddAssignTaskController" method="post">

<table align="center" height="10">
<center><h3><font color="green">Add and Assign Task</font></h3></center>
<tr>
&nbsp;
&nbsp;
</tr>

<tr>
<td >&nbsp;&nbsp;&nbsp;Group Name :</td>
<td >
<select name="GroupNames" id="GroupNames" >
<option value="-1">select</option>
<%
					
						GroupDAO groupDao = new GroupDAO();
							ArrayList<AssignOwnerVO> groupNames = new ArrayList<AssignOwnerVO>();
							groupNames = groupDao.getGroupNames();
							for (AssignOwnerVO localVo : groupNames) {
								
							
								if(request.getAttribute("status")=="1")
								{
								
								
								if(Integer.valueOf((String)request.getAttribute("groupId"))==localVo.getGroupId())
								{
							%>
						<option value="<%=localVo.getGroupId()%>" selected="selected"><%=localVo.getGroupName()%></option>
						<% 
								}
								else{
									%>

									<option value="<%=localVo.getGroupId()%>"><%=localVo.getGroupName()%></option>
								<%}}else{
								
					
								%>

						<option value="<%=localVo.getGroupId()%>"><%=localVo.getGroupName()%></option>
						<%
							}
								}
						%>


</select></td>
</tr>
<tr>
				<td><br></td>
			</tr>
<tr >
<td >&nbsp;&nbsp;&nbsp;Employee Name :</td>
<td >
<select id="EmployeeNames" name="EmployeeNames">
<option value="-1" >select</option>

						<%		

                    if(request.getAttribute("status")!=null)
						{	
				request.setAttribute("status",null);
					
	EmployeeDAO empNameDao = new EmployeeDAO();
	ArrayList<AssignOwnerVO> employeeNames = (ArrayList)request.getAttribute("employeeNames");
	for (AssignOwnerVO localEmployeeVo : employeeNames) {
	%><option value="<%=localEmployeeVo.getEmployeeId()%>"><%=localEmployeeVo.getEmployeeName()%></option>
	<%
	}}
	 %>



</select></td>
<td><input type="submit" name="getEmployees" id="getEmployees" value="Get Employees" /></td>
</tr>
<tr>
				<td><br></td>
			</tr>
<tr>
<td >&nbsp;&nbsp;&nbsp;TaskDetail :</td>
<td >
<input type="text" id="TaskDetail" name="TaskDetail"/>
</td>

</tr><tr>
				<td><br></td>
			</tr>
<tr>
<td >&nbsp;&nbsp;&nbsp;ETA : </td>
<td >
<input type="text" name="ETA" id="ETA" value="" onclick="showValue()"
 onmouseout="showValue1()"/></td>
<td> <div id="id1"></div></td></tr>
<tr>
<td  align="right">
<input type="submit" name="submit" id="submit" 
value="Add"/>
</td>
</tr>
</table>
</form>
<br>
<br>

<%@  include file="Footer.jsp"%>
</body>
</html>