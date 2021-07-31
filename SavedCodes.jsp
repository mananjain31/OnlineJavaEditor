<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="model.*"%>
<%!
    List<String> savedCodes;
    String userName;
%>
<%
    savedCodes = (List<String>) request.getAttribute("savedCodes"); 
    if(savedCodes == null)
    {
      response.sendRedirect("main.jsp");   
    }

    session = request.getSession(false);
    if(session != null){
        userName = (String)session.getAttribute("userName");
    }
    else response.sendRedirect("main.jsp");
%>
<html>
  <head>
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Girassol&display=swap" rel="stylesheet">
    <!-- <link rel="stylesheet" href="style.css"/> -->
    <link rel="stylesheet" href="./css/secondaryPages.css"/>
    <!-- <script  type="text/javascript" src="LoginAndRegister.js"></script> -->
    <script src="https://kit.fontawesome.com/26f93dc270.js" crossorigin="anonymous"></script>

  <script>
    function openCode(a)
    {    
        let mainForm = document.getElementById('mainform');
        let codeFileName = document.getElementById('codeFileName');
        codeFileName.style.display='flex';
        codeFileName.value = a;
        mainForm.action = "openCode";
        mainForm.submit();
        codeFileName.style.display='none';
    }
    function removeCode(a)
    {
        let mainForm = document.getElementById('mainform');
        let codeFileName = document.getElementById('codeFileName');
        codeFileName.style.display='flex';
        codeFileName.value = a;
        mainForm.action = "removeCode";
        mainForm.submit();
        codeFileName.style.display='none';   
    }
  </script>

  </head>
  <body>
    <form method="post" id="mainform">
        <div class="breadcrumbs">
            <a href="main.jsp"  id="home-link">IDE</a> > Saved Codes
        </div>
        <header>
            <h1>Online Java IDE</h1>            
            <h2>Saved Codes of <%=userName%></h2>
        </header>
            <main>

                <table class="savedCodesTable" id="savedCodesTable">
                <%
                    if(savedCodes.size() == 0)
                    {
                    %>
                        <tr><th>No Saved code found</th></tr>
                    <%
                    }
                    else
                    {
                    for(String codeFileName : savedCodes)
                    {
                %>
                    <tr>
                        <td><%=codeFileName%></td>
                        <td><input type="button" onclick="openCode('<%=codeFileName%>')" value="Open"></td>
                        <td><input type="button" onclick="removeCode('<%=codeFileName%>')" value="Remove"></td>
                    </tr>
                <%
                    }
                    }
                %>
                </table>
            </main>
  <!--           <footer>
                Back to Home : <a href="main.jsp">Home</a>
            </footer> -->
        </div>
        <input type="text" id="codeFileName" name="codeFileName" style="display:none">
    </form>
  </body>
</html>