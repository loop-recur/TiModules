// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.
var gestures = require('com.looprecur.gestures');

var window = Ti.UI.createWindow({backgroundColor: "white"});
var view = gestures.createGesturesView();

var title = Ti.UI.createLabel({top: "20dp", text: "Event Name: "});
var eventLabel = Ti.UI.createLabel({top: "40dp"});

window.add(view);
window.add(title);
window.add(eventLabel);
window.open();


var notify = function(name) {
	return function(e) {
		eventLabel.text = name;
		Ti.API.info(name);
		Ti.API.info(e);
	}
}

view.addEventListener("onSwipe", notify("onSwipe"));
view.addEventListener("onScroll", notify("onScroll"));
view.addEventListener("onFling", notify("onFling"));
view.addEventListener("onDown", notify("onDown"));
view.addEventListener("onSingleTapUp", notify("onSingleTapUp"));
view.addEventListener("onDoubleTap", notify("onDoubleTap"));