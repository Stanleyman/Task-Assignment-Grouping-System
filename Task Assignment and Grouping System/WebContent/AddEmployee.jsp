<%@page import="java.util.ArrayList"%>
<%@page import="dao.EmployeeDAO"%>
<%@page import="vo.GradeVO" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Employee</title>
</head>
<SCRIPT LANGUAGE="JavaScript" SRC="js/calendar.js"></SCRIPT>
<SCRIPT LANGUAGE="JavaScript" SRC="js/einsurance_Calendar.js"></SCRIPT>
<script type="text/javascript">
	function validate(formCheck) //Function with a parameter representing a form name.
	{
		return true;
	}
	function funcFromDateEntry() {
		show_calendar('Report.fromDate', 'month()', 'year()', 'DD/MM/YYYY',
				null, '');
	}

	function funcFromDateClear(dateEntry) {
		window.document.Report.fromDate.value = '';
	}
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

    <form action="AddEmployeeController" method="post">
    	
<table align="center">
<center><h3><font color="green">Add Employee</font></h3></center>
<tr>
&nbsp;
&nbsp;
</tr>
    <tr><td>Employee ID :</td><td> <input type="text" name="Employee_Id" id="Employee_Id" value=""/>
</td></tr><tr>
				<td><br>
				</td>
			</tr>
<tr>
    <td>Employee Name :</td><td><input type="text" name="Employee_Name" id="Employee_Name" value=""/>
</td>
</tr><tr>
				<td><br>
				</td>
			</tr>
<tr>
				<td>Date of Joining :</td>
				<td><input type="text" name="DOJ" id="DOJ" value=""
					onclick="showValue()" onmouseout="showValue1()" /></td>
				<td>
					<div id="id1"></div></td>
			</tr><tr>
				<td><br>
				</td>
			</tr>
<tr>
    <td>Grade :</td><td>
    <select name="Grade" id="Grade">
    <option value="-1">select</option>
    <%
    EmployeeDAO getGradeEmployeeDao=new EmployeeDAO();
    ArrayList<GradeVO> gradeList=getGradeEmployeeDao.getGrades();
    for(GradeVO grade :gradeList)
    {
    %>
    <option value="<%=grade.getGradeId()%>"><%=grade.getGradeName()%></option><%} %>
    </select> </td></tr>
<tr height="60%">
</tr><tr>
				<td><br>
				</td>
			</tr>
<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="submit" name="Add" id="Add" value="Add" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
					type="reset" name="Reset" id="Reset" />
			</tr>
</table>
</form>
<br>



	<%@  include file="Footer.jsp"%>

</body>
</html>