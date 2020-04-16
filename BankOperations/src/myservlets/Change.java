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
 * Servlet implementation class Change
 */
@WebServlet("/Change")
public class Change extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Change() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();
		Connection con;
		PreparedStatement pst;
		
		String id,curps,newps,conps;
		id=request.getParameter("uid");
		curps=request.getParameter("curps");
		newps=request.getParameter("newps");
		conps=request.getParameter("conps");
		
		if(newps.equals(conps))
		{
			try
			{
				DBConnector dbc=new DBConnector();
				con=dbc.getDbconnection();
				pst=con.prepareStatement("update users set pswd=? where userid=? and pswd=?;");
				pst.setString(1, newps);
				pst.setString(2,id);
				pst.setString(3,curps);
				int cnt=pst.executeUpdate();
				if(cnt==0)
					out.println("userid/current password incorrect");
				else
					out.println("<h3style=\"color:green\">Password Change Sucessfully</h3><hr><br>");
				out.println("<a href='Cashier.jsp'>Home</a>");
				
				con.close();
				
			}
			catch(Exception e)
			{
				out.println(e);
			}
		}
		else
		{
			out.println("New passwords mismatched");
			
		}
	}

}
