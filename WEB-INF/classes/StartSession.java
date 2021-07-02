package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import model.*;

public class StartSession extends HttpServlet
{
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		//read files from DAO

		//prepare model and put in session
		List<TheClasses> theClasses = new ArrayList<>();
		TheClasses theClass = new TheClasses();
		theClass.id = 0;
		theClass.className = "MyCode";
		theClass.code = "class MyCode\n"+
						"{\n"+
						"public static void main(String...args)\n"+
						"{\n"+
						"System.out.println(\"Hey There Coder\");\n"+
						"}\n"+
						"}\n";
		theClasses.add(theClass);
		HttpSession session = request.getSession();
		session.setAttribute("theClasses", theClasses);
		int curr_id = 0;
		session.setAttribute("curr_id",curr_id);
		response.sendRedirect("main.jsp");
	}
}