package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class SaveFile extends HttpServlet
{
  HttpSession session;
  String inputText,code,currFile,outputText,userName,password,contactNumber;
  String varArgs[];

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    PrintWriter out = response.getWriter();

    inputText = request.getParameter("inputText");
    code = request.getParameter("code");
    currFile = request.getParameter("currFile");
    outputText = request.getParameter("outputText");

    session = request.getSession(false);
    if(session != null) userName = (String) session.getAttribute("userName");
    try
    {
      if(session != null)
      {

        Class.forName("com.mysql.cj.jdbc.Driver"); 
        String db_url = "jdbc:mysql://localhost:3306/OnlineJavaIDE?allowPublicKeyRetrieval=true&useSSL=false";
        String db_uname = "manan";
        String db_upass = "Manan+31";
        Connection con = DriverManager.getConnection(db_url,db_uname,db_upass);

        String q = "select * from codefiles where userName='"+userName+"' and codeFileName='"+currFile+"'";
        ResultSet rs = (con.createStatement()).executeQuery(q);
        if(rs.next())
        {
          q = "UPDATE CODEFILES SET code = ? WHERE userName = ? and  codeFileName = ?";
          PreparedStatement pst = con.prepareStatement(q);
          pst.setBlob(1,  new ByteArrayInputStream(code.getBytes("UTF-8")));
          pst.setString(2, userName);
          pst.setString(3, currFile);
          pst.executeUpdate();
        }
        else
        {
          q = "INSERT INTO CODEFILES(userName,codeFileName,code) VALUES(?,?,?)";
          PreparedStatement pst = con.prepareStatement(q);
          pst.setString(1, userName);
          pst.setString(2, currFile);
          pst.setBlob(3,  new ByteArrayInputStream(code.getBytes("UTF-8")));
          pst.executeUpdate();
        }
        request.setAttribute("notification", "Saved file : "+currFile);
      }

      request.setAttribute("outputText", outputText);
      request.setAttribute("inputText", inputText);
      request.setAttribute("code", code);
      request.setAttribute("currFile", currFile);

      RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
      rd.forward(request, response);
    }
    catch(Exception e)
    {
      out.println(e);
    }
  }
}