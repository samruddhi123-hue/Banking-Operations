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
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id,ps,nm,gn,ct,mo,em,sq,an;
		int ag;
		id=request.getParameter("uid");
		nm=request.getParameter("unm");
		ps=request.getParameter("psw");
		ag=Integer.parseInt(request.getParameter("age"));
		gn=request.getParameter("gen");
		ct=request.getParameter("cit");
		mo=request.getParameter("mob");
		em=request.getParameter("eml");
		sq=request.getParameter("sec");
		an=request.getParameter("ans");
		
		
		PrintWriter out=response.getWriter();
		Connection con;
		PreparedStatement pst;
		try
		{
			DBConnector dbc=new DBConnector();
			con=dbc.getDbconnection();
			  pst=con.prepareStatement("insert into users values(?,?,?,default,default);");
			  pst.setString(1,id );
			  pst.setString(2,nm);
			  pst.setString(3,ps);
			  pst.executeUpdate();
			  
			  pst=con.prepareStatement("insert into userpersonal values(?,now(),?,?,?,?,?,?,?);");
				pst.setString(1, id);
				pst.setInt(2, ag);
				pst.setString(3, gn);
				pst.setString(4, ct);
				pst.setString(5, mo);
				pst.setString(6, em);
				pst.setString(7, sq);
				pst.setString(8, an);
				pst.executeUpdate();

				
				
			  out.println("<h3>user registeration sucessfully</h3><hr>");
			  out.println("<a href='index.jsp',Home</a>");
			  con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
