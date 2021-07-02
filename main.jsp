<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%!
    String currFile,code,className,inputText,outputText;
    int curr_id;
%>
<%
  if(code==null) code = "class MyCode\n"+
                        "{\n"+
                        "public static void main(String...args)\n"+
                        "{\n"+
                        "System.out.println(\"Hey There Coder\");\n"+
                        "}\n"+
                        "}\n";
  if(currFile == null) currFile = "MyCode.java";
  if(inputText == null) inputText = "";
  if(outputText == null) outputText = "";
%>
<html>
  <head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Girassol&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="style.css"/>
    <script  type="text/javascript" src="script.js"></script>
    <script src="https://kit.fontawesome.com/26f93dc270.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <form action="index.jsp" method="post" id="mainform">
        <header>
            <h1>Online Java IDE</h1>
            <nav>
                <div class="currFileDiv" value="" name="files">
                    <input type="text" name="currFile" class="currFile" value="<%=currFile%>">
                </div>
                <div class="code-interactions">
                    <input type="button" value="New" name="New" class="btn" id="New" onclick="newF()">
                    <input type="button" value="Upload" name="Upload" class="btn Upload" id="Upload" onclick="newF()">
                    <input type="button" value="Execute" name="submitB" class="btn execute" onclick="execute()">
                    <input type="button" value="Login" name="LoginB" class="btn-inverted">
                </div>
            </nav>
            
        </header>
      <main>
      <div class="ide">
        <div class="code-container">
            <textarea name="code" class="code" spellcheck="false" wrap="off"><%=code%></textarea>
        </div>
        <div class="ipop">
            <div class="ioNav">
                <div class="ioNavTitle">Input</div>
                <a><i class="fas fa-copy"></i></a>
                <a><i class="fas fa-upload"></i></a>
            </div>
          <textarea name="inputText" class="ip" placeholder="Enter Input Here" wrap="off" spellcheck="false"><%=inputText%></textarea>
            <div class="ioNav">
                <div class="ioNavTitle">Output</div>
                <a><i class="fas fa-copy"></i></a>
                <a><i class="fas fa-download"></i></a>
            </div>
          <textarea name="outputText" class="op" placeholder="Output Will Be Displayed Here" readonly wrap="off" spellcheck="false"><%=outputText%></textarea>
        </div>
      </div>
     </main>
    </form>
  </body>
</html>