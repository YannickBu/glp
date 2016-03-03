
/**************************************************************/
/* 															  */
/* 	  Conseil de lecture et écriture sur le fichier jp :  	  */
/* 															  */
/* 	  		  Se fichier est rangé  par page. 				  */
/* 			Ne  bouleverser pas l'ordre deja établi. 		  */
/* 															  */
/*				Merci beaucoup, Pierre-Louis ;) 			  */
/* 															  */
/**************************************************************/

//Inscription

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

// ModifProfil
//
//$(document).ready(function(){ 
//
//function SoumettreFormulaire(){  
//     document.forms["modif_form"].submit();
// };
//  
//var nbComp = ${utilisateur.profil.competence.size()-1};
//var nbComp2 = ${utilisateur.profil.diplomes.size()-1};
//var nbComp3 = ${utilisateur.profil.experiences.size()-1};
//
//function varComp() {
//	nbComp++;
//};
//
//function varDipl() {
//	nbComp2++;
//};
//
//function varExp() {
//	nbComp3++;
//};
//
//
//	function newCompetence() {
//		var div1, div2, input1, comp, select1, option1, option2, option3, option4, option4, option5, option6, div4, button;
//		comp = document.getElementById('compForm');
//		div1 = document.createElement('div');
//		div1.setAttribute('class','row');
//		div1.setAttribute('id','idComp'+nbComp);
//		comp.appendChild(div1);
//		
//		
//		div2 = document.createElement('div');
//		div2.setAttribute('class','col-md-3');
//		div1.appendChild(div2);
//		
//		input1 = document.createElement('input');
//		input1.setAttribute('name','profil.competence['+nbComp+'].libelle');
//		input1.setAttribute('type','text');
//		input1.setAttribute('class','form-control');
//		input1.setAttribute('placeholder','Libelle');
//		input1.setAttribute('data-toggle','tooltip');
//		input1.setAttribute('title','Libelle');
//		div2.appendChild(input1);	
//		
//		div3 = document.createElement('div');
//		div3.setAttribute('class','col-md-2');
//		div1.appendChild(div3);
//		
//		select1 = document.createElement('select');
//		select1.setAttribute('class','form-control');
//		select1.setAttribute('name','profil.competence['+nbComp+'].note');
//		div3.appendChild(select1);
//		
//		option1 = document.createElement('option');
//		option1.setAttribute('selected','selected');
//		option1.setAttribute('value','');
//		option1.innerHTML='Selectionner une note';
//		
//		
//		option2 = document.createElement('option');
//		option2.setAttribute('value','1');
//		option2.innerHTML='1';
//		
//		option3 = document.createElement('option');
//		option3.setAttribute('value','2');
//		option3.innerHTML='2';
//		
//		option4 = document.createElement('option');
//		option4.setAttribute('value','3');
//		option4.innerHTML='3';
//		
//		option5 = document.createElement('option');
//		option5.setAttribute('value','4');
//		option5.innerHTML='4';
//		
//		option6 = document.createElement('option');
//		option6.setAttribute('value','5');
//		option6.innerHTML='5';
//		
// 		select1.appendChild(option1);
//		select1.appendChild(option2);
//		select1.appendChild(option3);
//		select1.appendChild(option4);
//		select1.appendChild(option5);
//		select1.appendChild(option6);
//		
//		div4 = document.createElement('div');
//		div4.setAttribute('class','col-md-1');
//		div1.appendChild(div4);
//		
//		button = document.createElement(button);
//		button.setAttribute('type','button');
//		button.setAttribute('style','margin-top: 1%;');
//		button.setAttribute('class','btn btn-default');
//		button.setAttribute('id','btn_new_comp');
//		button.setAttribute('onclick','deleteCompetence('+nbComp+');');
//		button.innerHTML='x';
//
//		div4.appendChild(button);
//		
//	};
//	
//	function newDiplome() {
//		var comp, div1, div2, div3, div4, div5, div6, input1, input2, input3, input4, button;
//		comp = document.getElementById('diplForm');
//		div1 = document.createElement('div');
//		div1.setAttribute('class','row');
//		div1.setAttribute('id','idDipl'+nbComp2);
//		comp.appendChild(div1);
//
//		div2 = document.createElement('div');
//		div2.setAttribute('class','col-md-2');
//		div1.appendChild(div2);
//		
//		input1 = document.createElement('input');
//		input1.setAttribute('name','profil.diplomes['+nbComp2+'].anneeDebut');
//		input1.setAttribute('type','text');
//		input1.setAttribute('class','form-control');
//		input1.setAttribute('placeholder','Début');
//		input1.setAttribute('data-toggle','tooltip');
//		input1.setAttribute('title','Année de début');
//		div2.appendChild(input1);	
//		
//		div3 = document.createElement('div');
//		div3.setAttribute('class','col-md-2');
//		div1.appendChild(div3);
//		
//		input2 = document.createElement('input');
//		input2.setAttribute('name','profil.diplomes['+nbComp2+'].anneFin');
//		input2.setAttribute('type','text');
//		input2.setAttribute('class','form-control');
//		input2.setAttribute('placeholder','Fin');
//		input2.setAttribute('data-toggle','tooltip');
//		input2.setAttribute('title','Année de fin');
//		div3.appendChild(input2);
//		
//		div4 = document.createElement('div');
//		div4.setAttribute('class','col-md-5');
//		div1.appendChild(div4);
//		
//		input3 = document.createElement('input');
//		input3.setAttribute('name','profil.diplomes['+nbComp2+'].libelle');
//		input3.setAttribute('type','text');
//		input3.setAttribute('class','form-control');
//		input3.setAttribute('placeholder','Libelle');
//		input3.setAttribute('data-toggle','tooltip');
//		input3.setAttribute('title','Libelle du diplome');
//		div4.appendChild(input3);
//		
//		div5 = document.createElement('div');
//		div5.setAttribute('class','col-md-2');
//		div1.appendChild(div5);
//		
//		input4 = document.createElement('input');
//		input4.setAttribute('name','profil.diplomes['+nbComp2+'].lieu');
//		input4.setAttribute('type','text');
//		input4.setAttribute('class','form-control');
//		input4.setAttribute('placeholder','Lieu');
//		input4.setAttribute('data-toggle','tooltip');
//		input4.setAttribute('title','Lieu');
//		div5.appendChild(input4);
//		
//		div6 = document.createElement('div');
//		div6.setAttribute('class','col-md-1');
//		div1.appendChild(div6);
//
//		button = document.createElement(button);
//		button.setAttribute('type','button');
//		button.setAttribute('style','margin-top: 1%;');
//		button.setAttribute('class','btn btn-default');
//		button.setAttribute('id','btn_new_comp');
//		button.setAttribute('onclick','deleteDiplome('+nbComp2+');');
//		button.innerHTML='x';
//
//		div6.appendChild(button);
//		
//	};
//	function newExperience() {
//		var hr, comp, divP, divP2, div1, div2, div3, div4, div5, div6, div7, div8, div9, div10, div11, div12, div13, div14, input1, input2, input3, input4, input5, input6, input7, input8, button;
//		comp = document.getElementById('expForm');
//		
//		divP2 = document.createElement('div');
//		divP2.setAttribute('class','row');
//		divP2.setAttribute('id','idExp'+nbComp3);
//		comp.appendChild(divP2);		
//		
//		divP = document.createElement('div');
//		divP.setAttribute('class','col-md-12');
//		divP2.appendChild(divP);		
//		
//		div1 = document.createElement('div');
//		div1.setAttribute('class','row');
//		divP.appendChild(div1);		
//
//		div2 = document.createElement('div');
//		div2.setAttribute('class','col-md-2');
//		div1.appendChild(div2);
//		
//		input1 = document.createElement('input');
//		input1.setAttribute('name','profil.experiences['+nbComp3+'].anneeDebut');
//		input1.setAttribute('type','text');
//		input1.setAttribute('class','form-control');
//		input1.setAttribute('placeholder','Début');
//		input1.setAttribute('data-toggle','tooltip');
//		input1.setAttribute('title','Année de début');
//		div2.appendChild(input1);	
//		
//		div3 = document.createElement('div');
//		div3.setAttribute('class','col-md-2');
//		div1.appendChild(div3);
//		
//		input2 = document.createElement('input');
//		input2.setAttribute('name','profil.experiences['+nbComp3+'].anneFin');
//		input2.setAttribute('type','text');
//		input2.setAttribute('class','form-control');
//		input2.setAttribute('placeholder','Fin');
//		input2.setAttribute('data-toggle','tooltip');
//		input2.setAttribute('title','Année de fin');
//		div3.appendChild(input2);
//		
//		div4 = document.createElement('div');
//		div4.setAttribute('class','col-md-2');
//		div1.appendChild(div4);
//		
//		input3 = document.createElement('input');
//		input3.setAttribute('name','profil.experiences['+nbComp3+'].entreprise');
//		input3.setAttribute('type','text');
//		input3.setAttribute('class','form-control');
//		input3.setAttribute('placeholder','Entreprise');
//		input3.setAttribute('data-toggle','tooltip');
//		input3.setAttribute('title','Entreprise');
//		div4.appendChild(input3);
//		
//		div5 = document.createElement('div');
//		div5.setAttribute('class','col-md-2');
//		div1.appendChild(div5);
//
//		input4 = document.createElement('input');
//		input4.setAttribute('name','profil.experiences['+nbComp3+'].lieu');
//		input4.setAttribute('type','text');
//		input4.setAttribute('class','form-control');
//		input4.setAttribute('placeholder','Ville');
//		input4.setAttribute('data-toggle','tooltip');
//		input4.setAttribute('title','Ville');
//		div5.appendChild(input4);
//		
//		div6 = document.createElement('div');
//		div6.setAttribute('class','col-md-2');
//		div1.appendChild(div6);
//
//		input5 = document.createElement('input');
//		input5.setAttribute('name','profil.experiences['+nbComp3+'].region');
//		input5.setAttribute('type','text');
//		input5.setAttribute('class','form-control');
//		input5.setAttribute('placeholder','Region');
//		input5.setAttribute('data-toggle','tooltip');
//		input5.setAttribute('title','Region');
//		div6.appendChild(input5);
//		
//		div7 = document.createElement('div');
//		div7.setAttribute('class','col-md-2');
//		div1.appendChild(div7);
//
//		input6 = document.createElement('input');
//		input6.setAttribute('name','profil.experiences['+nbComp3+'].pays');
//		input6.setAttribute('type','text');
//		input6.setAttribute('class','form-control');
//		input6.setAttribute('placeholder','Pays');
//		input6.setAttribute('data-toggle','tooltip');
//		input6.setAttribute('title','Pays');
//		div7.appendChild(input6);
//		
//		div8 = document.createElement('div');
//		div8.setAttribute('class','row');
//		div8.setAttribute('style','margin-top: 1%;');
//		divP.appendChild(div8);
//	
//		div9 = document.createElement('div');
//		div9.setAttribute('class','col-md-12');
//		div8.appendChild(div9);
//		
//		input7 = document.createElement('input');
//		input7.setAttribute('name','profil.experiences['+nbComp3+'].poste');
//		input7.setAttribute('type','text');
//		input7.setAttribute('class','form-control');
//		input7.setAttribute('placeholder','Poste');
//		input7.setAttribute('data-toggle','tooltip');
//		input7.setAttribute('title','Poste occupé');
//		div9.appendChild(input7);
//		
//		div10 = document.createElement('div');
//		div10.setAttribute('class','row');
//		div10.setAttribute('style','margin-top: 1%;');
//		divP.appendChild(div10);
//	
//		div11 = document.createElement('div');
//		div11.setAttribute('class','col-md-12');
//		div10.appendChild(div11);
//		
//		input8 = document.createElement('textarea');
//		input8.setAttribute('name','profil.experiences['+nbComp3+'].description');
//		input8.setAttribute('type','text-area');
//		input8.setAttribute('class','form-control');
//		input8.setAttribute('placeholder','Description de votre mission');
//		input8.setAttribute('data-toggle','tooltip');
//		input8.setAttribute('title','Description de votre mission');
//		div11.appendChild(input8);
//		
//		
//		div12 = document.createElement('div');
//		div12.setAttribute('class','row');
//		divP.appendChild(div12);
//		
//		div13 = document.createElement('div');
//		div13.setAttribute('class','col-md-11');
//		div12.appendChild(div13);
//		
//		div14 = document.createElement('div');
//		div14.setAttribute('class','col-md-1');
//		div14.setAttribute('style','margin-top:1%;text-align:right');
//		div12.appendChild(div14);
//		
//		button = document.createElement(button);
//		button.setAttribute('type','button');
//		button.setAttribute('style','margin-top: 1%;text-align:right');
//		button.setAttribute('class','btn btn-default');
//		button.setAttribute('id','btn_new_comp');
//		button.setAttribute('onclick','deleteExperience('+nbComp3+');');
//		button.innerHTML='x';
//
//		div14.appendChild(button);
//		
//		hr = document.createElement('hr');
//		divP.appendChild(hr);
//		
//	};
//	
//	function deleteCompetence(i) {
//		var comp = document.getElementById('idComp'+i);  
//		comp.innerHTML = '';
//	};
//	
//	function deleteDiplome(i) {
//		var dipl = document.getElementById('idDipl'+i);  
//		dipl.innerHTML = '';
//	};
//	
//	function deleteExperience(i) {
//		var exp = document.getElementById('idExp'+i);  
//		exp.innerHTML = '';
//	};
//	
//});
//
//	$(document).ready(function(){
//	    $('[data-toggle="tooltip"]').tooltip(); 
//	});
//	
//	$(document).change(function(){
//	    $('[data-toggle="tooltip"]').tooltip(); 
//	});
