function onloadFunction()
{}
function execute()
{
  var mainform = document.getElementById("mainform");
  mainform.action = "execute";
  mainform.target = "";
  console.log(mainform);
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
  let mainform = document.getElementById("mainform");
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
  //perform logout
  //or
  //perform open saved files
}
