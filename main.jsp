<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%!
    String currFile,code,inputText,outputText,userName,userId;
%>
<%
  session = request.getSession(false);
  if(session != null) userName = (String) session.getAttribute("userName");
  if(session != null) userId = (String) session.getAttribute("userId");

  if(userName == null) userName = null;
  if(userName == null) userId = null;
  if(currFile == null) currFile = "MyCode.java";
  if(code==null) code = "class MyCode\n"+"{\n"+"public static void main(String...args)\n"+"{\n"+"System.out.println(\"Hey There Coder\");\n"+"}\n"+"}\n";
  if(inputText == null) inputText = "";
  if(outputText == null) outputText = "";
  userName = "manan";
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
                    <a onclick="copyText('code')"><i class="far fa-copy white"></i></a>
                    <a onclick="uploadFile('code')" ><i class="fas fa-upload white"></i></a>
                    <a onclick="downloadFile('code')" ><i class="fas fa-download white"></i></a>
                    <%
                    if(userName!=null)
                    {
                    %>
                    <a onclick="saveFile('code')" class="white"> <i class="fas fa-save" ></i></a>
                    <%
                    }
                    %>
                </div>
                <div class="code-interactions">
                    <input type="button" value="New" name="New" class="btn" id="New" onclick="newF()">
                    <input type="button" value="Upload" name="Upload" class="btn Upload" id="Upload" onclick="newF()">
                    <input type="button" value="Execute" name="submit" class="btn execute" onclick="execute()">
                    <%
                    if(userName == null)
                    {
                    %>
                    <input type="button" value="Login" name="Login" class="btn-inverted">
                    <%
                    }
                    else
                    {
                    %>
                    <select class="btn-inverted" id="user" class="user" onchange="userOptionChanged(this)">
                        <option id="userName" style="display:none">manan</option>
                        <option id="Logout">Logout</option>
                        <option id="openSaved">Open</option>
                    </select>
                    <%
                    }
                    %>
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
                <a onclick="copyText('input')"><i class="fas fa-copy"></i></a>
                <a onclick="uploadFile('input')" ><i class="fas fa-upload"></i></a>
            </div>
          <textarea name="inputText" class="ip" placeholder="Enter Input Here" wrap="off" spellcheck="false"><%=inputText%></textarea>
            <div class="ioNav">
                <div class="ioNavTitle">Output</div>
                <a onclick="copyText('output')"><i class="fas fa-copy"></i></a>
                <a><i class="fas fa-download"></i></a>
            </div>
          <textarea name="outputText" class="op" placeholder="Output Will Be Displayed Here" readonly wrap="off" spellcheck="false"><%=outputText%></textarea>
        </div>
      </div>
     </main>
    <!-- Hidden inputs resides here -->
    <input type="file" name="inputFile" id="inputFile" style="display:none" onchange="uploadedInputFile()">
    <input type="file" name="codeFile" id="codeFile" style="display:none" onchange="uploadedCodeFile()">
    </form>
    <!-- Hidden Notification Bar -->
    <div class="notification" id="notification" style="display:none;"></div>
  </body>
</html>