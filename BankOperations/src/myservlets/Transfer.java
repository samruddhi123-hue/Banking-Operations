package myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import mybeans.DBConnector;

/**
 * Servlet implementation class Transfer
 */
@WebServlet("/Transfer")
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int acc1=Integer.parseInt(request.getParameter("no"));
		int bal=Integer.parseInt(request.getParameter("amount"));
         int acc2=Integer.parseInt(request.getParameter("to"));
         PrintWriter out=response.getWriter();
			Connection con;
			PreparedStatement pst;
			try
			{
				DBConnector dbc=new DBConnector();
				con=dbc.getDbconnection();
				Statement st=con.createStatement();
				
				ResultSet rs=st.executeQuery("select balance from accounts where accno="+acc1+"");
				
				int balance1=0;
				while(rs.next())
				{
					balance1=rs.getInt("balance");
				}
				
				ResultSet rs1=st.executeQuery("select balance from accounts where accno="+acc2+"");
				
				int balance2=0;
				while(rs1.next())
				{
					balance2=rs1.getInt("balance");
				}
				
		     	int bal1=balance1-bal;
			    int bal2=balance2+bal;
			
			   st.addBatch("update accounts set balance="+bal1+"where accno="+acc1+"");
			   st.addBatch("update accounts set balance="+bal2+"where accno="+acc2+"");
			   st.executeBatch();
			   out.println("Transaction has been Sucessfully done");
			}	
			catch(Exception e)
			{
				out.println(e);
			}
	}
}
