// This is a test harness for your module
// You should do something interesting in this harness 
// to test out the module and to provide instructions 
// to users on how to use it by example.


var window = Ti.UI.createWindow({
	backgroundColor:'white'
});
var label = Ti.UI.createLabel();
window.add(label);
window.open();

var apkinstall = require('com.looprecur.apk_install');

var xhr = Titanium.Network.createHTTPClient();

xhr.onload = function(){
	 label.text = "finishing";
   var f = Ti.Filesystem.getFile(Ti.Filesystem.externalStorageDirectory, 'hangman.apk');
   f.write(this.responseData);
 	 label.text = "has file, about to prompt";
   apkinstall.promptInstall(f.nativePath);
};
xhr.onerror = function() {
	label.text= 'error';
}
label.text = "downloading";
xhr.open('GET','http://10.0.2.2:3000/hangman.apk');
xhr.send();
