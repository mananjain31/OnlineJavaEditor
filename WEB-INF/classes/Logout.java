package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Logout extends HttpServlet
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

		userName = request.getParameter("userName");
		
		try
		{
			request.setAttribute("outputText", outputText);
			request.setAttribute("inputText", inputText);
			request.setAttribute("code", code);
			request.setAttribute("currFile", currFile);

			session = request.getSession();
			session.setAttribute("userName", null);

			RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
			rd.forward(request, response);
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}
}