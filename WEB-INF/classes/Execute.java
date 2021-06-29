package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Execute extends HttpServlet
{
	static String executionPath = ".\\webapps\\OnlineJavaIDE\\IDE_Codes\\";
	static File executionFolder = new File(executionPath);
	static File inputTextFile = new File(executionPath+"inputText.txt");
	static File codeFile = new File(executionPath+"Code.java");
	static FileWriter fw;
	static HttpSession session;
	static String inputText,code,varArgs,currClass,outputText;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		PrintWriter out = response.getWriter();
		
		inputText = request.getParameter("inputText");
		code = request.getParameter("code");
		varArgs = null;
		
		session = request.getSession();
		currClass = (String)session.getAttribute("currClass");

		try
		{
		writeFiles(); // this will write into Input.java and Code.java
		outputText = getOutput();

		deleteClassFiles();

		request.setAttribute("outputText", outputText);
		RequestDispatcher rd = request.getRequestDispatcher("index.html");
		rd.forward(request, response);
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

	static public boolean writeFiles() throws IOException
	{
		if(inputText == null) inputText = "";
		fw = new FileWriter(inputTextFile);
		fw.write(inputText);

		fw = new FileWriter(codeFile);
		fw.write(code);
		fw.write(""
         + "\nclass inputTaker"
         + "{"
         + "public static void main(String...args) throws Exception"
         + "{"
         + "\ntry{System.setIn(new java.io.FileInputStream(\"inputFile.txt\"));}catch(Exception e){}"
         + "\n"+currClass+".main("+varArgs+");"
         + "\n}"
         + "}" // 4 lines of extra code
     	);

		fw.close();
		return true;
	}

	static public String getOutput()throws Exception
	{
		String output = null;

		Process p = Runtime.getRuntime().exec("javac --release 8 Code.java",null, executionFolder);
		p.waitFor();
		if (p.exitValue() != 0) 
		{
			StringBuilder op = printLines(p.getErrorStream());
			output = (op.toString()).trim();
		} 
		else 
		{
			p = Runtime.getRuntime().exec("java inputTaker",null, executionFolder);
			p.waitFor();
			StringBuilder op = printLines(p.getInputStream());
			output = op.toString().trim();
			if(p.exitValue()!=0)
			{
				StringBuilder exception = printLines(p.getErrorStream());
				output += "\n"+exception; 
			}
		}

		return output;
	}


	static public StringBuilder printLines(InputStream is/*, JspWriter out*/) throws Exception 
	{
		StringBuilder lines = new StringBuilder();
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
		  lines.append(sc.nextLine()+"\n");
		}
		return lines;
	}



	public static void deleteClassFiles()
	{
		File folder = executionFolder;
	  	for(File file: folder.listFiles())
	  	{
	    	if(file.getName().endsWith(".class"))
	    	{
	     		file.delete();
	    	}
  		}
	}

}