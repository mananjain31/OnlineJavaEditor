package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Download extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		OutputStream out = response.getOutputStream();
		String contentToDownload = request.getParameter("contentToDownload");
		String contentNameToDownload = request.getParameter("contentNameToDownload");
		response.setContentType("application/octet-stream");
		response.setHeader("Content-disposition","attachment; filename="+contentNameToDownload);
		byte[] b = contentToDownload.getBytes();
		out.write(b);
	}
}