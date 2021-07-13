package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.*;
import java.util.*;

@MultipartConfig(maxFileSize = 1024*1024*10)
public class UploadCodeFile extends HttpServlet
{
	String inputText, code, currFile, outputText;
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		PrintWriter out = response.getWriter();
		// inputText = request.getParameter("inputText");
		code = request.getParameter("code");
		// currFile = request.getParameter("currFile");
		// outputText = request.getParameter("outputText");

		Part file = request.getPart("codeFile");
		currFile = file.getSubmittedFileName();
		String code = null;
		StringBuilder sb = new StringBuilder();
		Scanner sc = new Scanner(file.getInputStream());
		while(sc.hasNextLine())
		{
			sb.append(sc.nextLine());
			sb.append("\n");
		}
		code = sb.toString();


		// request.setAttribute("outputText", outputText);
		// request.setAttribute("inputText", inputText);
		request.setAttribute("code", code);
		request.setAttribute("currFile", currFile);
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
	}
}