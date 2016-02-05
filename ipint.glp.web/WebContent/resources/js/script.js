
/**************************************************************/
/* 															  */
/* 	  Conseil de lecture et écriture sur le fichier jp :  	  */
/* 															  */
/* 	  	  Se fichier est rangé  par ordre alphabétique.	 	  */
/* 			Ne  bouleverser pas l'ordre deja établi. 		  */
/* 															  */
/*				Merci beaucoup, Pierre-Louis ;) 			  */
/* 															  */
/**************************************************************/


$(document).ready(function(){ 

	$("#inputBirthday").datetimepicker({
		format : "yyyy/mm/dd",
		startView : 'decade',
		minView : 'month',
		autoclose : true,
		endDate : new Date(),
		language : "fr"
	});

});