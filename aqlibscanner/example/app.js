// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var win = Ti.UI.createWindow({
  backgroundColor:'white',
  layout: 'vertical'
});

var aqlibscanner = require('com.looprecur.aqlibandroid');
Ti.API.info("module is => " + aqlibscanner);

var scan = Ti.UI.createButton({title: 'Scan Vin'})
  , vin = Ti.UI.createLabel({});

win.add(scan);
win.add(vin);

scan.addEventListener('click', function() {
  aqlibscanner.scanVin({
    success: function(e) {
      Ti.API.info('logging vin from success callback  ====================');
      Ti.API.info(e.vin);
    },
    error: function(e) {
      Ti.API.info('logging error from error callback =====================');
    }
  });
});

win.open();

