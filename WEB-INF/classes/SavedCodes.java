package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class SavedCodes extends HttpServlet
{
  HttpSession session;
  List<String> savedCodes;
  String userName;

  public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    PrintWriter out = response.getWriter();
    savedCodes = new LinkedList<String>();

    session = request.getSession(false);
    if(session == null) response.sendRedirect("main.jsp");
    else userName = (String)session.getAttribute("userName");

    try
    {
        Class.forName("com.mysql.cj.jdbc.Driver"); 
        String db_url = "jdbc:mysql://localhost:3306/OnlineJavaIDE?allowPublicKeyRetrieval=true&useSSL=false";
        String db_uname = "manan";
        String db_upass = "Manan+31";
        Connection con = DriverManager.getConnection(db_url,db_uname,db_upass);

        String q = "select codeFileName from codefiles where userName = '"+userName+"'";
        ResultSet rs = (con.createStatement()).executeQuery(q);
        while(rs.next())
        {
          savedCodes.add(rs.getString(1));
        }
        request.setAttribute("savedCodes", savedCodes);
        RequestDispatcher rd = request.getRequestDispatcher("SavedCodes.jsp");
        rd.forward(request, response);
    }
    catch(Exception e)
    {
      out.println(e);
    }
  }
}