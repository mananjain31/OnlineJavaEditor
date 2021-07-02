function execute()
{
  let mainform = document.getElementById("mainform");
  mainform.action = "execute";
  mainform.target = "";
  mainform.submit();
}
function resetF( )
{
  if(confirm("All Changes will be Lost!"))
  {
    document.getElementsByClassName("ip")[0].value="";
    document.getElementsByClassName("op")[0].value="";
    document.getElementsByClassName("code")[0].value="class MyCode\n{\npublic static void main(String...args)\n{\nSystem.out.println(\"Hey There Coder\");\n}\n}";
  }
}
function newF()
{
  let mainform = document.getElementById("mainform");
  mainform.action = "index.jsp";
  mainform.target = "_blank";
  mainform.submit();
}