/*
## Licence ##

The MIT License

Copyright (c) 2014 Neeraj Tuteja

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

package org.apache.cordova.plugin;

import android.content.*;
import android.util.*;
import android.widget.*;
import org.apache.cordova.api.*;
import org.json.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.Contacts;
import android.util.Log;
import android.view.View;

import java.io.IOException;	

public class ExportVCFsToFilePlugin extends CordovaPlugin
{ 
	Cursor cursor;
	Set<String> contactsSet;
	String vfile, fileName, fileExtension;
	@Override 
	public boolean execute(String action, JSONArray data, final CallbackContext callBackContext)
	{ 
		try{
			fileName = data.getString(0);
			fileExtension = data.getString(1);
		}catch(JSONException e){
			
		}
		cordova.getThreadPool().execute(new Runnable() {
			public void run(){
	   	    	try{
    	          String fullFilePath = getVcardString(fileName, fileExtension);
				  callBackContext.success(fullFilePath);
        	    }catch(Exception e){
				  callBackContext.error("Exception thrown: " + e);
				}
			}
		});
		return true; 
	} 
	
	private String getVcardString(String fileName, String fileExtension) throws IOException 
	{
		contactsSet = new HashSet<String>()	;
		cursor = this.cordova.getActivity().getContentResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null, null, null);
        if( cursor!=null && cursor.getCount()>0 ) 
        {		
        	int i;		
        	File outputDir = this.cordova.getActivity().getCacheDir();
			String fullFilePath = outputDir.getAbsolutePath() + "/" + fileName +"." + fileExtension;
            FileOutputStream mFileOutputStream = new FileOutputStream(fullFilePath, false);

			cursor.moveToFirst();
						
            for(i = 0;i<cursor.getCount();i++){	
       		  	 Uri uri = Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_VCARD_URI, cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY)) );
	    	   	 AssetFileDescriptor fd;
            	try {
                	fd = this.cordova.getActivity().getContentResolver().openAssetFileDescriptor(uri, "r");
                	FileInputStream fis = fd.createInputStream();
                	byte[] buf = new byte[(int) fd.getDeclaredLength()];
                	fis.read(buf);
                	contactsSet.add( new String(buf) );
            	} catch (Exception e1) {}
	                cursor.moveToNext();
    	        }
      			Iterator it = contactsSet.iterator();
				while(it.hasNext()){
					try {
            	    	mFileOutputStream.write(it.next().toString().getBytes());
                	} catch (IOException e){
                }
			}
            mFileOutputStream.close();	
            cursor.close();
            return fullFilePath;
        }
        else{
            Log.d("TAG", "No Contacts in Your Phone");
			return "";
        }
	}
} 
