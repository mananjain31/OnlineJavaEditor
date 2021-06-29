function rd( )
{
  document.getElemenstByClassName("inp")[0].innerHtml="";
}
function resetF( )
{
  if(confirm("All Changes will be Lost!"))
  {
    document.getElementsByClassName("ip")[0].value="";
    document.getElementsByClassName("op")[0].value="";
    document.getElementsByClassName("code")[0].value="class Code\n{\npublic static void main(String...args)\n{\nSystem.out.println(\"Hey Coder\");\n}\n}";
  }
}
function openF()
{
//  var openFile = document.createElement("p");
//  openFile.className='openFile';
//  openFile.innerHtml="hello this is new";
//  openFile.style.width="1000px";
//  openFile.style.height="1000px";
//  document.getElementById("mainform").appendChild(openFile);
}
var currFile = "Code.java";
function filechanged(filedatas)
{
  var openedFile = document.getElementById("openedFile").value;
  console.log(openedFile);
  var code = document.querySelector(".code");
  filedatas[currFile] = code.value;
  code.value = filedatas[openedFile];
  currFile = openedFile;
  console.log(filedatas[openedFile]);
}
function newF()
{
  document.querySelector("#New").disabled=true;
  console.log("New clicked!");
  var newFileDiv = document.createElement("div");
  newFileDiv.className='newFileDiv';
  document.body.appendChild(newFileDiv);

  var closebutton =document.createElement("button");
  closebutton.className='closebutton';
  closebutton.innerHTML="X";

  var newFileHtml = document.createElement("div");
  newFileHtml.className='newFileHtml';
  newFileHtml.innerHTML="<h2><u>New File</u></h2><br>"+
                        "<form id='newFileForm' action='index.jsp'>"+
                        "<p>Name:<input type='text' name='newFileName' class='fileName'>.java</p><br>"+
                        "<input type='button' value='Create' class='create' onclick='createFile()'>"+
                        "</form>";
  newFileDiv.appendChild(closebutton);
  newFileDiv.appendChild(newFileHtml);
  closebutton.setAttribute('onclick','closeNewFileMenu()');
}
function closeNewFileMenu()
{
  document.getElementsByClassName("newFileDiv")[0].remove();
  document.querySelector("#New").disabled=false;
}
function createFile()
{
  console.log("createFile called");
  // validation start

  // validation end
  var form = document.getElementById("newFileForm");
  console.log(form.id);
  form.submit();
  console.log("createFile called");
}