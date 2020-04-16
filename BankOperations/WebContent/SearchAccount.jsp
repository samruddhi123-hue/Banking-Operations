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
<h2>Searching accounts information...</h2>
<hr>

<table width="500px" border="1" cellspacing="0" bordercolor="azure">
<tr style="background-color:seashell">
<th>Account Number
<th>Name
<th>Type
<th>Balance
<th>RegDt
</tr>

<%
Connection con;
PreparedStatement pst;
ResultSet rs;
int no=Integer.parseInt(request.getParameter("acn"));

try
{
	DBConnector dbc=new DBConnector();
	con=dbc.getDbconnection();
	pst=con.prepareStatement("select * from accounts where accno=?;");
	pst.setInt(1,no);
	
	rs=pst.executeQuery();
	while(rs.next())
	{
		no=rs.getInt("accno");
	%>
		<tr>
		<td><%=no %>
		<td><%=rs.getString("accnm") %>
	
		<td><%=rs.getString("acctype") %>
		<td><%=rs.getString("balance") %>
		<td><%=rs.getString("regdt") %>
		
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
<a href="LogOut.jsp">Home</a>



</body>
</html>