<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%!
    String currFile,code,inputText,outputText,userName,userId;
%>
<html>
  <head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Girassol&display=swap" rel="stylesheet">
    <!-- <link rel="stylesheet" href="style.css"/> -->
    <link rel="stylesheet" href="./css/LoginAndRegister.css"/>
    <script  type="text/javascript" src="./js/LoginAndRegister.js"></script>
    <script src="https://kit.fontawesome.com/26f93dc270.js" crossorigin="anonymous"></script>
  </head>
  <body>
    <form action="login" method="post" id="mainform">
        <header>
            <h1>Online Java IDE</h1>            
            <h2>Login Page</h2>
        </header>
            <main>
                <%if(request.getAttribute("failed") != null)
                {
                %>
                <span style="color: red;">Invalid Username or Password</span>
                <%
                }
                %>
                <table class="loginTable" id="loginTable">
                    <tr><td>Username&nbsp;&nbsp;&nbsp;</td><td><input type="text" name="userName"></td></tr>
                    <tr><td>Password&nbsp;&nbsp;&nbsp;</td><td><input type="password" name="password"></td></tr>
                    <th colspan="2"><button type="submit">Login</th>
                </table>
            </main>
            <footer>
                Does not have an account ? <button class="switchPage" id="switchPage" name="Register">Register</button>
            </footer>
    <!-- Hidden inputs resides here -->
    <!-- Hidden Notification Bar -->
        </div>
        <div class="notification" id="notification" style="display:none;"></div>
        <%
        if(request.getAttribute("failed") != null)
        {
          outputText = (String)request.getAttribute("outputText");
          code = (String)request.getAttribute("code");
          inputText = (String)request.getAttribute("inputText");
          currFile  = (String)request.getAttribute("currFile");
        }
        else
        {
          outputText = (String)request.getParameter("outputText");
          code = (String)request.getParameter("code");
          inputText = (String)request.getParameter("inputText");
          currFile  = (String)request.getParameter("currFile");
        }
        %>
        <input type="text" name="currFile" id="currFile" style="display:none" value="<%=currFile%>">
        <textarea name="code" id="code" style="display:none"><%=code%></textarea>
        <textarea name="inputText" id="inputText" style="display:none"><%=inputText%></textarea>
        <textarea name="outputText" id="outputText" style="display:none"><%=outputText%></textarea>
    </form>
  </body>
</html>