package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class Register extends HttpServlet
{
	FileWriter fw;
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

		userName = request.getParameter("userName");
		password = request.getParameter("password");
		contactNumber = request.getParameter("contactNumber");


		try
		{
			
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			String db_url = "jdbc:mysql://localhost:3306/OnlineJavaIDE?allowPublicKeyRetrieval=true&useSSL=false";
			String db_uname = "manan";
			String db_upass = "Manan+31";
			Connection con = DriverManager.getConnection(db_url,db_uname,db_upass);

			Statement st = con.createStatement();

			String q = "insert into users values('"+userName+"', '"+password+"', '"+contactNumber+"')";
			st.execute(q);

			session = request.getSession();
			session.setAttribute("userName", userName);

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