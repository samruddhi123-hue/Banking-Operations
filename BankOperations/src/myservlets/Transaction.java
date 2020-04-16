package myservlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybeans.DBConnector;

/**
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int ano,tno;
		String ty;
		double am;
		
		tno=Integer.parseInt(request.getParameter("tn"));
		ano=Integer.parseInt(request.getParameter("acn"));
		ty=request.getParameter("acty");
		am=Double.parseDouble(request.getParameter("amt"));
		
		PrintWriter out=response.getWriter();
			Connection con;
			PreparedStatement pst;
			try
			{
				DBConnector dbc=new DBConnector();
				con=dbc.getDbconnection();
				
				pst=con.prepareStatement("insert into acctransactions values(?,now(),?,?,?);");
				  pst.setInt(1,tno);
				  pst.setInt(2,ano);
				  pst.setString(3,ty);
				  pst.setDouble(4, am);
				  pst.executeUpdate();
				
				if(ty.equalsIgnoreCase("deposit"))
				{
				 
				  pst=con.prepareStatement("update accounts set balance=balance+? where accno=?;");
				}
				else
				{
				pst=con.prepareStatement("update accounts set balance=balance-? where accno=?;");
				} 
				
				pst.setDouble(1,am);
			    pst.setInt(2,ano);
			    
			    int cnt= pst.executeUpdate();
			    if(cnt>0)
			     out.println("balance updated sucessfully");
			   else
			   out.println("transcation failed.....check your account number");
			    
			    out.println("<a href='Cashier.jsp'>Home</a>");

				con.close();
			}
			catch(Exception e)
			{
				out.println(e);	
			}
				  
		
		
	}

}
