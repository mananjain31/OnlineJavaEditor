<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%!
    String currClass;
%>
<%
  session = response.getSession(false);
  if(session == null)
  {
    response.sendRedirect("Initialise");
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
                    <!--
                    <input type="button" value="Open" name="Open" class="buttons" onclick="openF()">
                    -->
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
          <textarea name="outputText" class="op" placeholder="Output Will Be Displayed Here" readonly wrap="off" spellcheck="false"><%=outputText%></textarea>
        </div>
      </div>
     </main>
    </form>
  </body>
</html>