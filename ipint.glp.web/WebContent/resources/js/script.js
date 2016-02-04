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