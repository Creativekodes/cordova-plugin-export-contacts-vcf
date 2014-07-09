export-vcf-cordova-phonegap
===========================
By Neeraj Tuteja

##Description##
Export all contacts (device, Google, Facebook etc) to a file and use its path for further processing

##Devices tested## 

This plugin was successfully tested with Phonegap 2.9 and Android 4.1.2. 

## Adding this plugin to your project ## 

0. (Make sure you are using Phonegap > 2.0) 
1. Move ExportVCFsToFile.js to your project's www folder and include a reference to it in your html files. 
2. Add the java files from src to your project's src hierarchy 
3. Reference the plugin in your res/config.xml file ```<plugin name="ExportVCFsToFilePlugin" value="org.apache.cordova.plugin.ExportVCFsToFilePlugin"/>``` 
4. Ensure that your manifest contains the necessary permissions to send SMS messages: ```<uses-permission android:name="android.permission.READ_CONTACTS" />``` 

