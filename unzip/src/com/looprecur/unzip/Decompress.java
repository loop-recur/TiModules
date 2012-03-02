package com.looprecur.unzip;

import android.util.Log; 
import java.io.File; 
import java.io.FileInputStream; 
import java.io.FileOutputStream; 
import java.util.zip.ZipEntry; 
import java.util.zip.ZipInputStream; 

public class Decompress { 
  private String _zipFile; 
  private String _location; 
 
  public Decompress(String zipFile, String location) { 
    _zipFile = zipFile; 
    _location = location; 
 
    _dirChecker(""); 
  } 
 
  public void unzip() { 
		try {
				String filename = _zipFile;
        String destinationname = _location;
        byte[] buf = new byte[1024];
        ZipInputStream zipinputstream = null;
        ZipEntry zipentry;
        zipinputstream = new ZipInputStream(
                new FileInputStream(filename));

				ensureDirectory(destinationname);

        zipentry = zipinputstream.getNextEntry();
        while (zipentry != null) {
            //for each entry to be extracted
						
						String fnm = zipentry.getName();
	          File nf = new File(destinationname + File.separator + fnm);
						new File(nf.getParent()).mkdirs();
	    	   
            
            String entryName = destinationname +"/"+ zipentry.getName();
            entryName = entryName.replace('/', File.separatorChar);
            entryName = entryName.replace('\\', File.separatorChar);
						
						
						
            int n;
            FileOutputStream fileoutputstream;
            File newFile = new File(entryName);
            if (zipentry.isDirectory()) {
                if (!newFile.mkdirs()) {
                    break;
                }
                zipentry = zipinputstream.getNextEntry();
                continue;
            }

            fileoutputstream = new FileOutputStream(entryName);

            while ((n = zipinputstream.read(buf, 0, 1024)) > -1) {
                fileoutputstream.write(buf, 0, n);
            }

            fileoutputstream.close();
            zipinputstream.closeEntry();
            zipentry = zipinputstream.getNextEntry();

        }//while

        zipinputstream.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
	}
	
	private void ensureDirectory(String loc) {
		File dir = new File(loc);
		String[] children = dir.list();
		
		if (children == null) {
		    // Either dir does not exist or is not a directory
		} else {
		    for (int i=0; i<children.length; i++) {
		        String fname = children[i];
						_dirChecker(fname);
		    }
		}
	}
 
  private void _dirChecker(String dir) { 
    File f = new File(_location + dir); 
 
    if(!f.isDirectory()) { 
      f.mkdirs(); 
    } 
  } 
}
