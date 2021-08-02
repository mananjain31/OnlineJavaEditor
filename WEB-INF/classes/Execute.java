package controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class Execute extends HttpServlet
{
	 String executionPath = ".\\webapps\\OnlineJavaIDE\\IDE_Codes\\";
	 File executionFolder = new File(executionPath);
	 File inputFile = new File(executionPath+"inputFile.txt");
	 File outputFile = new File(executionPath+"outputFile.txt");
	 File codeFile = new File(executionPath+"Code.java");
	 FileWriter fw;
	 HttpSession session;
	 String inputText,code,currClass,currFile,outputText;
	 String varArgs[];

	 PrintWriter out;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
	{
		PrintWriter out = response.getWriter();
		this.out = out;
		inputText = request.getParameter("inputText");
		code = request.getParameter("code");
		currFile = request.getParameter("currFile");
		String[] s = currFile.split(".java");
		currClass = s[0];
		varArgs = null;
		
		try
		{
		writeFiles(); // this will write into Input.java and Code.java
		// outputText = getOutput();
		generateOutput();

		deleteClassFiles();

		FileInputStream outputFileInputStream = new FileInputStream(outputFile);
		request.setAttribute("outputFileInputStream", outputFileInputStream);
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

	 public boolean writeFiles() throws IOException
	{
		if(inputText == null) inputText = "";
		fw = new FileWriter(inputFile);
		fw.write(inputText);
		fw.close();
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

	public void generateOutput()throws Exception
	{
		OutputStream outputFileOutputStream = new FileOutputStream(outputFile);

		Process p = Runtime.getRuntime().exec("javac --release 8 Code.java",null, executionFolder);
		p.waitFor();
		if (p.exitValue() != 0) 
		{
			/*
			StringBuilder op = printLines(p.getErrorStream());
			output = (op.toString()).trim();
			*/
			appendOutput(p.getErrorStream(), outputFileOutputStream);
		} 
		else 
		{
			p = Runtime.getRuntime().exec("java inputTaker",null, executionFolder);
			p.waitFor();
			/*
			StringBuilder op = printLines(p.getInputStream());
			output = op.toString().trim();
			*/
			appendOutput(p.getInputStream(), outputFileOutputStream);
			if(p.exitValue()!=0)
			{
				// StringBuilder exception = printLines(p.getErrorStream());
				appendOutput(p.getErrorStream(), outputFileOutputStream);
			}
		}
		outputFileOutputStream.close();
	}

	public void appendOutput(InputStream is, OutputStream os)throws Exception
	{
		int size = 1024;
		byte buffer[] = new byte[size];
		int recieved = 0;
		while(true)
		{
			recieved = is.read(buffer, 0, size);
			if(recieved <= 0) break;
			else if(recieved < size)
			{
				size = recieved;
			}
			// out.write(buffer.toString());
			os.write(buffer, 0, size);
			os.flush();	
		}
	}

/*
	public StringBuilder printLines(InputStream is) throws Exception 
	{
		StringBuilder lines = new StringBuilder();
		Scanner sc = new Scanner(is);
		while (sc.hasNextLine()) {
		  lines.append(sc.nextLine()+"\n");
		}
		return lines;
	}

*/

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