// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// View
var win, view, vin;

win = Ti.UI.createWindow({ backgroundColor:'white'});
view = Ti.UI.createView({ layout: 'vertical', width: Ti.UI.SIZE, height: Ti.UI.SIZE });
vin = Ti.UI.createTextField({ top: 10, width: 250, hintText: 'Enter VIN'});

view.add(vin);
win.add(view);
win.open();

// Controller
var kb = require('com.looprecur.vinkeyboard.android')

//+ showVinKeyboard :: Event -> Action(UI)
  , showVinKeyboard = function() {
      kb.show();
    }
  ;

Ti.API.info("module is => " + kb);
vin.addEventListener('click', showVinKeyboard);
