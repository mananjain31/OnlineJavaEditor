package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class RemoveCode extends HttpServlet
{
  HttpSession session;
  String code,currFile,userName,codeFileName;

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    PrintWriter out = response.getWriter();

    session = request.getSession(false);

    if(session != null) userName = (String) session.getAttribute("userName");
    else response.sendRedirect("main.jsp");

    codeFileName = request.getParameter("codeFileName");
    out.println(codeFileName);
    try
    {
      Class.forName("com.mysql.cj.jdbc.Driver"); 
      String db_url = "jdbc:mysql://localhost:3306/OnlineJavaIDE?allowPublicKeyRetrieval=true&useSSL=false";
      String db_uname = "manan";
      String db_upass = "Manan+31";
      Connection con = DriverManager.getConnection(db_url,db_uname,db_upass);

      String q = "Delete from CODEFILES where userName = ? and codeFileName = ?";
      PreparedStatement pst = con.prepareStatement(q);
      pst.setString(1, userName);
      pst.setString(2, codeFileName);
      pst.executeUpdate();
      
      response.sendRedirect("savedCodes");
    }
    catch(Exception e)
    {
      out.println(e);
    }
  }
}