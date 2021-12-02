function onloadFunction()
{}
function loading(action,btn)
{
  if(action == 1) btn.style.opacity=0.5;
  if(action == 0) btn.style.opacity=1;
}
function execute(btn)
{
  let outputText = document.getElementById("outputText");
  let inputText = document.getElementById("inputText");
  let code = document.getElementById("code");
  let currFile = document.getElementById("currFile");
  let xmlHttpRequest = new XMLHttpRequest();
  let dataString = "inputText="+encodeURIComponent(inputText.value)
  + "&code="+encodeURIComponent(code.value)
  + "&currFile="+encodeURIComponent(currFile.value);
  console.log(dataString);
  xmlHttpRequest.onreadystatechange = function(){
    if(this.readyState == 4 && this.status == 200){
      loading(0,btn);
      outputText.value = this.responseText;
    }
  }
  xmlHttpRequest.open("POST", "execute", true); // true written so that the request will be send Asynchronouslly
  xmlHttpRequest.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
  xmlHttpRequest.send(dataString);
  loading(1,btn);
}
function login()
{
  var mainform = document.getElementById("mainform");
  mainform.action = "Login.jsp";
  let target = mainform.target;
  mainform.submit(); 
}
function saveFile()
{
  mainform.action="saveFile";
  mainform.submit();
}
/*
function resetF( )
{
  if(confirm("All Changes will be Lost!"))
  {
    document.getElementsByClassName("ip")[0].value="";
    document.getElementsByClassName("op")[0].value="";
    document.getElementsByClassName("code")[0].value="class MyCode\n{\npublic static void main(String...args)\n{\nSystem.out.println(\"Hey There Coder\");\n}\n}";
  }
}
*/
function newF()
{
  let mainform = document.getElementById("mainform");
  mainform.action = "index.jsp";
  let target = mainform.target;
  mainform.target = "_blank";
  mainform.submit();
  mainform.target = target;
}
function copyText(text)
{
  let copyText = null;
  if(text == 'input')copyText =  document.getElementsByClassName("ip")[0];
  else if(text == 'output')copyText =  document.getElementsByClassName("op")[0];
  else if(text == 'code')copyText =  document.getElementsByClassName("code")[0];
  copyText.select();
  copyText.setSelectionRange(0, 9999999999); 
  document.execCommand("copy");
  let notification = document.getElementById("notification");
  notification.innerText = "Copied "+text;
  notification.style.display = "flex";
  setTimeout(function(){
      notification.style.display = "none";    
  },1500);
  console.log(notification);
}
function downloadFile(which)
{

  let mainform = document.getElementById("mainform");
  let contentToDownload =  document.getElementById("contentToDownload")
  let contentNameToDownload =  document.getElementById("contentNameToDownload")
  contentNameToDownload.style.display = "flex";
  contentToDownload.style.display = "flex";
  let target = mainform.target;
  switch(which)
  {
    case "code":
    {
      mainform.action = "download";
      mainform.target = "_blank";
      contentNameToDownload.value = document.getElementById("currFile").value;
      contentToDownload.value = document.getElementById("code").value;
      mainform.submit();
    }break;

    case "output":
    {
      mainform.action = "download";
      mainform.target = "_blank";
      contentNameToDownload.value = "output.txt";
      contentToDownload.value = document.getElementById("outputText").value;
      mainform.submit();
    }break;
  }
  mainform.target = target;
  contentNameToDownload.style.display = "none";
  contentToDownload.style.display = "none";
}
function uploadFile(whichFile)
{
  switch(whichFile)
  {
    case "input":
    {
      let inputFile = document.getElementById("inputFile");
      inputFile.style.display = "flex";
      inputFile.style.width = 0;
      inputFile.style.height = 0;
      inputFile.click();
      inputFile.style.display = "none";
    }break;
    case "code":
    {
      let codeFile = document.getElementById("codeFile");
      codeFile.style.display = "flex"
      codeFile.style.width = 0;
      codeFile.style.height = 0
      codeFile.click();
      codeFile.style.display = "none"
    }break;
  }
}
function selectedFileToUpload(tag)
{
  console.log(tag);
  let enctype = mainform.enctype;
  let target = mainform.target;
  switch(tag.id)
  {
    case "codeFile":
    {
      mainform.target = "_blank";
      mainform.action = "uploadCodeFile";
      mainform.enctype="multipart/form-data";
      // console.log(mainform.enctype);
      mainform.submit();
      mainform.enctype=enctype;
      mainform.target = target;
      // console.log(mainform.enctype);
    }break;
    case "inputFile":
    {
      mainform.action = "uploadInputFile";
      mainform.enctype="multipart/form-data";
      mainform.submit();  
      mainform.enctype=enctype;
    }break;
  }
}

function userOptionChanged(option)
{
  console.log(option.value);
  if(option.value == 'Logout')
  {
    console.log('logging out');
    mainform.action = "logout";
    mainform.submit();
  } 
  if(option.value == 'Saved Codes')
  {
    console.log('opening files');
    let target = mainform.target;
    mainform.target = "_blank";
    mainform.action = "savedCodes";
    mainform.submit();
    mainform.target = target;
 }
  option.value = userName;
}

function maintainDotJava()
{
  let i =currFile.value.indexOf('.');
  if(i<0){
    currFile.value += ".java";
  }
  else{
    currFile.value = currFile.value.substring(0,i) + ".java";
  }
}


function showLineNumber(e)
{
  let lineNumber = document.getElementById("line-number");
  let code = document.getElementById("code");
  let currLine = code.value.substring(0,code.selectionStart).split("\n").length;
  let totalLines = code.value.split("\n").length;
  lineNumber.innerText = "Line:"+currLine+"/"+totalLines;
}

function indent(e)
{
  const tabspace = "  ";
  let code = e.currentTarget.value;
  let codeEle = e.target;
  let ss = codeEle.selectionStart;
  if(e.ctrlKey && e.key=='Enter') 
  {
    const submit = document.querySelector("input.btn.execute");
    submit.click();
    return;
  }
  if(e.key == "Tab") 
  {
    e.preventDefault();
    codeEle.value = code.substring(0,ss) + "  " + code.substring(ss);
    codeEle.selectionStart = codeEle.selectionEnd = ss+2;
  }
  if(e.key == "Enter") 
  {
    e.preventDefault();
    let stack = [];
    let stack2 = [];
    for(let i=0;i<code.substring(0,ss).length;i++)
    {
      let c = code[i];
      if(c == '{') stack.push(c);
      else if(c == `'` || c == `"` ) i = code.indexOf(c, i+1);
      else if(c == '}') 
      {
        stack2 = [];
        while(stack.length != 0)
        {
          let cc = stack.pop();
          console.log(cc);
          if(cc == '{') break;
          stack2.push(cc);
        }
        while(stack2.length != 0) stack.push(stack2.pop());
      }
    }
    // console.log(stack);
    console.log("Final " ,stack)
    let indentation = stack.reduce((acc,i)=>(acc+tabspace),"\n");
    if(code[ss]=='}') 
    {
      stack.pop();
      let secondaryIndendation = stack.reduce((acc,i)=>(acc+tabspace),"\n");
      codeEle.value = code.substring(0,ss) + indentation + secondaryIndendation + code.substring(ss);
    }
    else
    {
      codeEle.value = code.substring(0,ss) + indentation + code.substring(ss);
    }
    codeEle.selectionStart = codeEle.selectionEnd = ss+indentation.length;
  }
}

window.addEventListener('load', function(){
  mainform = document.getElementById("mainform");

  let user = document.getElementById('user');
  if(user) userName = user.value;

  notification = document.getElementById('notification');
  if(notification.innerText != "null")
  {
    console.log(notification.innerText);
    notification.style.display = "flex";
    setTimeout(function(){
      notification.style.display = "none";    
    },1500);
  }

  currFile = document.getElementById('currFile');
  currFile.addEventListener('change',maintainDotJava);

  let code = document.getElementById("code");
  showLineNumber();
  code.addEventListener('change', showLineNumber);
  code.addEventListener('keypress', showLineNumber);
  code.addEventListener('keydown', showLineNumber);
  code.addEventListener('keyup', showLineNumber);
  code.addEventListener('click', showLineNumber);

  code.addEventListener('keydown', indent);

});