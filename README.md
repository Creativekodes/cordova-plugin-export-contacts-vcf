cordova-plugin-exportContactsVCF
===========================
By Neeraj Tuteja

Updated by Vasiliy Mikhaylovsky

##Description
Export all contacts (device, Google, Facebook etc) to a file and use its path for further processing

## Installation

This requires cordova 5.0+ ( current stable v1.0.0 )

    cordova plugin add cordova-plugin-export-contacts-vcf

It is also possible to install via repo url directly ( unstable )

    cordova plugin add https://github.com/Ti-webdev/cordova-plugin-export-contacts-vcf.git

## Supported Platforms

* Android


## Usage

1. Wait for `deviceReady`.
1. Call plugins.exportContactsVCF with parameters

## API
plugins.exportContactsVCF(onSuccess, onFail, fileName, fileExtension)
default `fileName` is 'exportedContactsInVCF', `fileExtension` is 'vcf'


```javascript
plugins.exportContactsVCF(function(pathToVcfFile) {
    console.log('vcf stored at: ' + pathToVcfFile)
}, function(err) {
  console.error('Error: ' + err)
}, 'my-contacts')
```
