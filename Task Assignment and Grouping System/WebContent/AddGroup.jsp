<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Group</title>
</head>
<script type="text/javascript">
	
</script>
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
		<span style="color: red"><h3><%=message%></h3></span>
	</center>
	<form action="AddGroupController" method="post">

		<table align="center">
<center><h3><font color="green">Add Group</font></h3></center>
<tr>
&nbsp;
&nbsp;
</tr>
			<td>Group Name :</td>
			<td><input type="text" name="GroupName" id="GroupName" />
			</td>
			</tr>
			<tr>
				<td><br></td>
			</tr>
			<tr>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="submit" name="Add" id="Add" value="Add" /></td>
				<td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="reset" name="Reset" id="Reset" />
			</tr>

		</table>
	</form>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	


	<%@  include file="Footer.jsp"%>
</body>
</html>