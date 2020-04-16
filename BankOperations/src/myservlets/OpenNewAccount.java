package myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybeans.DBConnector;

/**
 * Servlet implementation class OpenNewAccount
 */
@WebServlet("/OpenNewAccount")
public class OpenNewAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OpenNewAccount() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String accnm,act;
		int accno;
		double bal;
		
		accnm=request.getParameter("nm");
		accno=Integer.parseInt(request.getParameter("acn"));
		act=request.getParameter("acty");
		bal=Integer.parseInt(request.getParameter("acb"));
		
		
		PrintWriter out=response.getWriter();
		Connection con;
		PreparedStatement pst;
		try
		{
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			  
			  pst=con.prepareStatement("insert into accounts values(?,?,?,?,now());");
				pst.setInt(1, accno);
				pst.setString(2, accnm);
				pst.setString(3, act);
				pst.setDouble(4, bal);
				
				pst.executeUpdate();
				
				
			  out.println("<h3>Account Open Sucessfully</h3><hr>");
			  out.println("<a href='Manager.jsp'>Home</a>");
			  con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
