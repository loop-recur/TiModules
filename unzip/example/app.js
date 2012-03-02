// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


// open a single window
var window = Ti.UI.createWindow({
	backgroundColor:'white'
});
var label = Ti.UI.createLabel();
window.add(label);
window.open();

var getAll = function(fileproxy) {
	var dirItems = fileproxy.getDirectoryListing()
	, r = [];
	
	for (var i=0; i<dirItems.length; i++) { r.push(fileproxy.nativePath+Ti.Filesystem.separator+dirItems[i].toString()); }
	return r;
}

var xhr = Titanium.Network.createHTTPClient();

var Unzipper = require('com.looprecur.unzip');

xhr.onload = function(){
   var f = Ti.Filesystem.getFile(Ti.Filesystem.applicationDataDirectory, 'hangman.zip');
   var fdir = Ti.Filesystem.getFile(Ti.Filesystem.applicationDataDirectory);
	 if(!fdir.exists()) fdir.createDirectory();
   f.write(this.responseData);
   Unzipper.unzip(f.nativePath, fdir.nativePath);
	 alert("================unzipped: "+getAll(fdir).join(", "));
};
xhr.open('GET','http://looprecur.com/hangman.zip');
xhr.send();
