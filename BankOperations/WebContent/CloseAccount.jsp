<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%@page import=" mybeans.DBConnector" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style bgcolor="Wheat">
<%
int no=Integer.parseInt(request.getParameter("acn"));
%>
<h2>Deleting account <%=no %>...</h2>
<hr>
<%
Connection con;
PreparedStatement pst;
try
{
	DBConnector dbc=new DBConnector();
	con=dbc.getDbconnection();
	pst=con.prepareStatement("delete from accounts where accno=?;");
	pst.setInt(1,no);
	pst.executeUpdate();
	out.println("account deleted successfully");
	con.close();
}
catch(Exception e)
{
	out.println(e);
}
%>
<br>
<a href="Manager.jsp">Home</a>


</body>
</html>