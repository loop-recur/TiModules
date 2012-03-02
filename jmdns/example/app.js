// Functionality

var Demo = (function() {
	var jmdns = require('com.looprecur.jmdns')
	, service_name = '_xbmc-web._tcp'
	, domain = 'local.'
	, services = [];
	
	function _foundHost(service) {
		var hasService = service && services.indexOf(service.host) < 0;
		if(hasService) services.push(service.host);
		return hasService;
	}

	function discover(callbacks) {
		jmdns.discover(service_name, domain, function(service) {
			_foundHost(service) ? callbacks.success(service) : callbacks.error();
		});
	}
	
	return { discover : discover }
})();



// GUI
var window = Ti.UI.createWindow({
	backgroundColor:'white'
});

var label = Ti.UI.createLabel({
	textAlign:"center",
	width:"50%",
	top: "10dp",
	text: ""
});

var button = Ti.UI.createButton({
	height:"40dp",
	width:"80dp",
	bottom: "10dp",
	title:"Discover"
});

button.addEventListener('click', function() {
	Demo.discover({
		success: function(service) {
			label.text += "\n" + service.name + " " + service.port + " " + service.host;
		},
		error: function() {
			alert("No hosts found");
		}
	});
});

window.add(label);
window.add(button);
window.open();
