function unhideNone()
{
	document.getElementById('currFile').style.display = "flex";
	document.getElementById('inputText').style.display = "flex";
	document.getElementById('outputText').style.display = "flex";
	document.getElementById('code').style.display = "flex";
}
function hideAgain()
{
	document.getElementById('currFile').style.display = "none";
	document.getElementById('inputText').style.display = "none";
	document.getElementById('outputText').style.display = "none";
	document.getElementById('code').style.display = "none";
}
function switchPage() 
{
	unhideNone();
	switchButton = document.getElementById('switchPage');
	let mainform = document.getElementById("mainform");
	mainform.action = switchButton.name+".jsp";
	mainform.submit();
	hideAgain();
}
function gotoHome(e) 
{
	e.preventDefault();
	unhideNone();
	let mainform = document.getElementById("mainform");
	mainform.action = "main.jsp";
	mainform.submit();
	hideAgain();
}
window.addEventListener('load', ()=>{
	switchButton = document.getElementById('switchPage');
	switchButton.addEventListener('click', switchPage);
	homeLink = document.getElementById('home-link');
	homeLink.addEventListener('click', gotoHome);
});