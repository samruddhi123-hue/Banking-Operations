<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.sql.*" %>
    <%@page import=" mybeans.DBConnector"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body style bgcolor="Wheat">
<h2>Activate Deactivate Users Login</h2><hr>
<table width="500px" border="1" cellspacing="0" bordercolor="azure">
<tr style="background-color:seashell">
<th>User Id
<th>User Name 
<th>User Status
</tr>
<%
Connection con;
PreparedStatement pst;
ResultSet rs;
String no;
try
{
	DBConnector dbc=new DBConnector();
	con=dbc.getDbconnection();
	pst=con.prepareStatement("select userid,usernm,userstatus from users;");
	rs=pst.executeQuery();
	while(rs.next())
	{
		no=rs.getString("userstatus");
	%>	
	    <tr>
		<td><%=rs.getString("userid") %>
	    <td><%=rs.getString("usernm") %>
		<td><%=no %>
		
		</tr>
   <%
	}
}
catch(Exception e)
{
	out.println(e);
}
%>
</table>
<br>
<br>
<a href="Manager.jsp">Home</a>


</body>
</html>