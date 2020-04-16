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
<body>
<h2>Daily Transaction Report......</h2>
<hr>

<table width="500px" border="1" cellspacing="0" bordercolor="azure">
<tr style="background-color:seashell">
<th>Transaction Number
<th>Account Number
<th>Transaction Type
<th>Amount

</tr>

<%
Connection con;
PreparedStatement pst;
ResultSet rs;
Date date=request.getParameter("dt");

try
{
	DBConnector dbc=new DBConnector();
	con=dbc.getDbconnection();
	pst=con.prepareStatement("select * from acctransactions where transdt=?;");
	
	rs=pst.executeQuery();
	while(rs.next())
	{
		
	%>
		<tr>
		<td><%=rs.getString("transno") %>
		<td><%=rs.getString("accno") %>
		<td><%=rs.getString("transactiontype") %>
		<td><%=rs.getString("amount") %>
		
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
<a href="Cashier.jsp">Home</a>


</body>
</html>

</body>
</html>