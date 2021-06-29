<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%!
  public static StringBuilder printLines(InputStream is/*, JspWriter out*/) throws Exception {
    StringBuilder lines = new StringBuilder();
    Scanner sc = new Scanner(is);
    while (sc.hasNextLine()) {
      lines.append(sc.nextLine()+"\n");
    }
    return lines;
  }
%>
<%
   String submitB = request.getParameter("submitB");
   String code;
   String opStr = "";
   String inputText = "";
   String executionPath = ".\\webapps\\OnlineJavaIDE\\IDE_Codes";
   File executionFolder = new File(executionPath);
   if (submitB == null) {
     code = "class Code\n"
         + "{\n"
         + "public static void main(String...args)\n"
         + "{\n"
         + "System.out.println(\"Hey Coder\");\n"
         + "}\n"
         + "}";
   } else {
     code = request.getParameter("code");
   }
   submitB = request.getParameter("submitB");
   if (submitB != null) {     
   String mainclass = request.getParameter("code");
   inputText = request.getParameter("inputText");
   FileWriter fw = new FileWriter(executionPath+"\\inputFile.txt");
   fw.write(inputText);
   fw.close();
   fw = new FileWriter(executionPath+"\\Code.java");
   fw.write(mainclass);
   fw.write(""
       + "\nclass inputTaker"
       + "{"
       + "public static void main(String...args) throws Exception"
       + "{"
       + "\ntry{System.setIn(new java.io.FileInputStream(\"inputFile.txt\"));}catch(Exception e){}"
       + "\nCode.main(null);"
       + "\n}"
       + "}" // 4 lines of extra code
   );
   fw.close();
   Scanner sc = new Scanner(new File(executionPath+"\\Code.java"));
   Process p = Runtime.getRuntime().exec("javac --release 8 Code.java",null, executionFolder);
   p.waitFor();
   if (p.exitValue() != 0) {
     StringBuilder op = printLines(p.getErrorStream());
     opStr = (op.toString()).trim();
   } else {
     p = Runtime.getRuntime().exec("java inputTaker",null, executionFolder);
     p.waitFor();
     StringBuilder op = printLines(p.getInputStream());
     opStr = op.toString().trim();
     if(p.exitValue()!=0)
     {
       StringBuilder exception = printLines(p.getErrorStream());
       opStr += "\n"+exception; 
     }
   }
   }
  File folder = executionFolder;
  for(File file: folder.listFiles())
  {
    if(file.getName().endsWith(".class"))
    {
     file.delete();
    }
  }
%>
<html>
  <head>
    <link rel="stylesheet" href="style.css"/>
    <link rel="preconnect" href="https://fonts.gstatic.com">
<link href="https://fonts.googleapis.com/css2?family=Girassol&display=swap" rel="stylesheet">
    <script  type="text/javascript" src="script.js"></script>
    <script>
      var filedatas = {};
      filedatas["Code.java"] = `<%= code %>`;
      filedatas["Code2.java"] = "hello 2";
    </script>
  </head>
  <body>
    <form action="index.jsp" method="post" id="mainform">
        <header>
            <h1>Online Java IDE</h1>
            <nav>
                <div class="files" value="" name="files">
                <!--     <input type="button" value="Code.java">
                    <input type="button" value="Code2.java">
                    <input type="button" value="+"> -->
                    <select onchange="filechanged(filedatas)" id="openedFile">
                      <option value="Code.java">Code.java</option>
                      <option value="Code2.java">Code2.java</option>
                    </select>
                </div>
                <div class="code-interactions">
                    <input type="button" value="New" name="New" class="buttons" id="New" onclick="newF()">
                    <input type="button" value="Open" name="Open" class="buttons" onclick="openF()">
                    <input type="button" value="Save" name="Save" class="buttons" onclick="rd()">
                    <input type="button" value="Reset" name="resetB" class="buttons" onclick="resetF()">
                    <input type="submit" value="Execute" name="submitB" class="buttons">
                </div>
            </nav>
            
        </header>
      <main>
      <div class="ide">
        <div class="code-container">
            <textarea name="code" class="code" spellcheck="false" wrap="off"><%=code%></textarea>
        </div>
        <div class="ipop">
          <textarea name="inputText" class="ip" placeholder="Enter Input Here" wrap="off" spellcheck="false"><%=inputText%></textarea>
          <textarea name="outputText" class="op" placeholder="Output Will Be Displayed Here" readonly wrap="off" spellcheck="false"><%=opStr%></textarea>
        </div>
      </div>
     </main>
    </form>
  </body>
</html>