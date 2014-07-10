var ExportVCFsToFile = {
	fileName: "exportedContactsInVCF",
	fileExtension: "vcf",	
	export: function ()
	{ 
		cordova.exec(this.onSuccess, this.onFail, "ExportVCFsToFile", "nativeAction", [this.fileName, this.fileExtension]);
  	},
	onSuccess:function(filePath)
	{ 
		alert("Filepath of exported VCF is " + filePath);
	} ,
	onFail: function  (error)
	{ 
		alert("ERROR: " + error); 
	}
} ;
