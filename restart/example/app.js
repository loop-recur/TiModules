// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
	backgroundColor:'white'
});


// TODO: write your module tests here
var restartModule = require('com.looprecur.restart');

var button = Ti.UI.createButton({
	height:"40dp",
	width:"80dp",
	bottom: "10dp",
	title:"Click to restart"
});

button.addEventListener('click', function() {
	restartModule.restart();
});

window.add(button);
window.open();