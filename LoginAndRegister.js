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
window.addEventListener('load', ()=>{
	console.log('loaded');
	switchButton = document.getElementById('switchPage');
	console.log(switchButton);
	switchButton.addEventListener('click', switchPage);
});