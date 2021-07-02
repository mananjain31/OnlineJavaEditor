package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Execute extends HttpServlet
{
	 String executionPath = ".\\webapps\\OnlineJavaIDE\\IDE_Codes\\";
	 File executionFolder = new File(executionPath);
	 File inputTextFile = new File(executionPath+"inputText.txt");
	 File codeFile = new File(executionPath+"Code.java");
	 FileWriter fw;
	 HttpSession session;
	 String inputText,code,varArgs,currClass,outputText;

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
		RequestDispatcher rd = request.getRequestDispatcher("main.jsp");
		rd.forward(request, response);
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

	 public boolean writeFiles() throws IOException
	{
		if(inputText == null) inputText = "";
		fw = new FileWriter(inputTextFile);
		fw.write(inputText);

		fw = new FileWriter(codeFile);
		fw.write(code);
		fw.write(""
         + "\nclass inputTaker"
         + "{"
         + "public  void main(String...args) throws Exception"
         + "{"
         + "\ntry{System.setIn(new java.io.FileInputStream(\"inputFile.txt\"));}catch(Exception e){}"
         + "\n"+currClass+".main("+varArgs+");"
         + "\n}"
         + "}" // 4 lines of extra code
     	);

		fw.close();
		return true;
	}

	 public String getOutput()throws Exception
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


	 public StringBuilder printLines(InputStream is/*, JspWriter out*/) throws Exception 
	{
		StringBuilder lines = new StringBuilder();
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
		  lines.append(sc.nextLine()+"\n");
		}
		return lines;
	}



	public  void deleteClassFiles()
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