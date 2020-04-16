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
<h2>Accounts Statement...</h2>
<hr>

<table width="500px" border="1" cellspacing="0" bordercolor="azure">
<tr style="background-color:seashell">

<th>Transaction Date
<th>Account Number
<th>Transaction Type
<th>Amount
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
	pst=con.prepareStatement("select  transdt,transactiontype,amount from acctransactions where accno=?;");
	pst.setInt(1,no);
	rs=pst.executeQuery();
	while(rs.next())
	{
		no=rs.getInt("accno");
	%>
		<tr>
	<td><%=no %>
		<td><%=rs.getString("transdt") %>
	
	
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
	



</body>
</html>