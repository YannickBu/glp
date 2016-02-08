<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="ipint.glp.api.DTO.ProfilDTO"%>
<%@ page import="ipint.glp.api.DTO.UtilisateurDTO"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>



<form:form role="form" method="post" action="" name="modif_form"
	commandName="utilisateur">

	<div class="col-md-6 publication">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2">
					<img
						src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
						class="img-responsive" alt="Responsive image">
				</div>
				<div class="col-md-10">
					<h1 class="nomEtu">${utilisateur.prenom}&nbsp${utilisateur.nom}</h1>
					<div class="col-md-12 situation form-group ">
						<label for="InputDiplomePrincipal"> Situation : </label>

						<form:input path="profil.situation" type="text"
							class="form-control" id="InputSituation"
							placeholder="ex: Chef de projet à CGI France" />


					</div>
					<div class="col-md-6 situation form-group">
						<label for="InputDiplomePrincipal"> Diplôme principal </label>

						<form:input path="profil.diplomePrincipal" type="text"
							class="form-control" id="InputDiplomePrincipal"
							placeholder="ex: Master MIAGE" />


					</div>
					<div class="col-md-6">

						<label for="InputAnneeDiplomel"> Année d'obtention du
							diplôme </label> 
							<c:set var="TEST" value="${profil.anneeDiplome}" scope="request"/> 
							<form:select path="profil.anneeDiplome" class="form-control" id="InputAnneDiplome">
							<% //(Integer) request.getAttribute("yannick")).intValue()
							Calendar calendar =new GregorianCalendar();
							calendar.setTime(new Date());
							int annee =calendar.get(Calendar.YEAR);
							for(int i =annee;i>1950;i--){
								out.println("<option value='"+i+"' "+
								((((UtilisateurDTO) request.getAttribute("utilisateur")).getProfil().getAnneeDiplome()==i)?"selected='true'":"")
								+">"+i+"</option>");
							}
							%>
						</form:select>
					</div>


				</div>
				<hr />
			</div>
		</div>
		<div class="navProfil">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="alert alert-success alert-dismissable" hidden>
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							<h4>Alert!</h4>
							<strong>Warning!</strong> Best check yo self, you're not looking
							too good. <a href="#" class="alert-link">alert link</a>
						</div>
						<div class="form-group">
							<label for="InputNom"> Nom * : </label>
							<form:input path="nom" type="text" class="form-control"
								id="InputNom" placeholder="ex: Dupont" />
						</div>
						<div class="form-group">
							<label for="InputPrenom"> Prénom * : </label>
							<form:input path="prenom" type="text" class="form-control"
								id="InputPrenom" placeholder="ex: Jean" />
						</div>
						<div class="form-group">
							<label for="InputPassword"> Mot de passe * :</label>
							<form:input path="password" type="password" class="form-control"
								id="InputPassword" />
						</div>
						<div class="form-group">
							<label for="InputTel"> Téléphone :</label>
							<form:input path="profil.telephone" type="text"
								class="form-control" id="InputTel" />
						</div>
						<div class="form-group">
							<label for="InputMesAttentes"> Mes attentes du réseau
								L1nk.fr </label>
							<form:input path="profil.mesAttentes" type="text"
								class="form-control" id="InputMesAttentes" />
						</div>
						<div class="form-group">
							<label for="InputCursus"> Diplômes : </label>

							<div id="diplForm">
								<c:forEach begin="0" end="${utilisateur.profil.diplomes.size()}"
									var="i">
									<div class="row" id="idDipl${i}">
										<div class="col-md-2">
											<form:input path="profil.diplomes[${i}].anneeDebut"
												value="${profil.diplomes[i].anneeDebut}" type="text"
												class="form-control" id="InputDipDebut" placeholder="Début" />
										</div>
										<div class="col-md-2">
											<form:input path="profil.diplomes[${i}].anneFin"
												value="${profil.diplomes[i].anneFin}" type="text"
												class="form-control" id="InputDipAnneFin" placeholder="Fin" />
										</div>
										<div class="col-md-5">
											<form:input path="profil.diplomes[${i}].libelle"
												value="${profil.diplomes[i].libelle}" type="text"
												class="form-control" id="InputDipDesc" placeholder="Libelle" />
										</div>
										<div class="col-md-2">
											<form:input path="profil.diplomes[${i}].lieu"
												value="${profil.diplomes[i].lieu}" type="text"
												class="form-control" id="InputDipLieu" placeholder="Lieu" />
										</div>
										<div class="col-md-1">
											<button type="button" style="margin-top: 1%;"
												class="btn btn-default" id="btn_delete_comp"
												onClick="deleteDiplome(${i});">x</button>
										</div>

									</div>
								</c:forEach>


							</div>
							<button type="button" style="margin-top: 1%;"
								class="btn btn-default" id="btn_new_dipl"
								onClick="varDipl();newDiplome();">Ajouter un diplome</button>

						</div>
						<div class="form-group">
							<label for="InputExp"> Expériences Professionnelles : </label>
							<div id="expForm">
								<c:forEach begin="0"
									end="${utilisateur.profil.experiences.size()}" var="i">
									<div class="row" id="idExp${i}">
										<div class="col-md-12">
											<div class="row">
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].anneeDebut"
														value="${profil.experiences[i].anneeDebut}" type="text"
														class="form-control" id="InputExpDebut"
														placeholder="Début" />
												</div>
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].anneFin"
														value="${profil.experiences[i].anneFin}" type="text"
														class="form-control" id="InputExpAnneFin"
														placeholder="Fin" />
												</div>
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].entreprise"
														value="${profil.experiences[i].entreprise}" type="text"
														class="form-control" id="InputExpEnt"
														placeholder="Entreprise" />
												</div>
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].lieu"
														value="${profil.experiences[i].lieu}" type="text"
														class="form-control" id="InputExpLieu" placeholder="Ville" />
												</div>
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].region"
														value="${profil.experiences[i].region}" type="text"
														class="form-control" id="InputExpRegion"
														placeholder="Région" />
												</div>
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].pays"
														value="${profil.experiences[i].pays}" type="text"
														class="form-control" id="InputExpPays" placeholder="Pays" />
												</div>
											</div>
											<div class="row" style="margin-top: 1%">
												<div class="col-md-12">
													<form:input path="profil.experiences[${i}].poste"
														value="${profil.experiences[i].poste}" type="text"
														class="form-control" id="InputExpPoste"
														placeholder="Poste" />
												</div>
											</div>
											<div class="row" style="margin-top: 1%">
												<div class="col-md-12">
													<form:textarea path="profil.experiences[${i}].description"
														value="${profil.experiences[i].description}"
														type="text-area" class="form-control" id="InputExpDesc"
														placeholder="Description de votre mission" />
												</div>
											</div>
											<div class="row">
												<div class="col-md-11"></div>
												<div class="col-md-1"
													style="margin-top: 1%; text-align: right">
													<button type="button" class="btn btn-default"
														id="btn_new_exp" onClick="deleteExperience(${i});">x</button>
												</div>
											</div>
											<hr>
										</div>
									</div>
								</c:forEach>
							</div>

							<button type="button" class="btn btn-default" id="btn_new_exp"
								onClick="varExp();newExperience();">Ajouter une
								expérience</button>


						</div>
						<div class="form-group">
							<label for="InputSkills"> Compétences :</label>
							<div id="compForm">
								<c:forEach begin="0"
									end="${utilisateur.profil.competence.size()}" var="i">
									<div class="row" id="idComp${i}">
										<div class="col-md-3">
											<form:input path="profil.competence[${i}].libelle"
												value="${profil.competence[i].libelle}" type="text"
												class="form-control" id="InputDipDebut"
												placeholder="Libelle" />
										</div>
										<div class="col-md-2">
											<form:select class="form-control" id="selectNoteCompetence"
												path="profil.competence[${i}].note">
												<form:option value="${profil.competence[i].note}"
													selected="selected">Selectionner une note</form:option>
												<form:option value="1"></form:option>
												<form:option value="2"></form:option>
												<form:option value="3"></form:option>
												<form:option value="4"></form:option>
												<form:option value="5"></form:option>
											</form:select>
										</div>
										<div class="col-md-1">
											<button type="button" style="margin-top: 1%;"
												class="btn btn-default" id="btn_new_comp"
												onClick="deleteCompetence(${i});">x</button>
										</div>
									</div>
								</c:forEach>
							</div>
							<button type="button" style="margin-top: 1%;"
								class="btn btn-default" id="btn_new_comp"
								onClick="varComp();newCompetence();">Ajouter une
								compétence</button>
						</div>
						<div class="form-group">
							<label for="InputInterets"> Centres d'interets :</label>
							<form:textarea path="profil.centreInteret" type="text-aera"
								rows="3" class="form-control" id="InputInterets"
								placeholder="ex: [Interet1],[Interet2],..."></form:textarea>
						</div>
						<!-- 						<div class="form-group"> -->
						<!-- 							<label for="InputInterets"> Réseaux sociaux </label> -->
						<%-- 							<c:forEach begin="0" --%>
						<%-- 								end="${utilisateur.profil.reseauxSociaux.size()}" var="i"> --%>
						<%-- 								<form:input path="profil.centreInteret" type="text-aera" --%>
						<%-- 									rows="3" class="form-control" id="InputInterets" --%>
						<%-- 									placeholder="ex: https://www.facebook.com/mon.facebook"></form:input> --%>
						<%-- 							</c:forEach> --%>
						<!-- 						</div> -->
						<a href="${pageContext.servletContext.contextPath}/profil/"><button
								type="button" class="btn btn-default">Annuler</button></a>
						<button type="submit" class="btn btn-default"
							onclick="SoumettreFormulaire();">Enregistrer</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</form:form>

<script type="text/javascript">

function SoumettreFormulaire(){  
     document.forms["modif_form"].submit();
 }
  
var nbComp = ${utilisateur.profil.competence.size()-1};
var nbComp2 = ${utilisateur.profil.diplomes.size()-1};
var nbComp3 = ${utilisateur.profil.experiences.size()-1};

function varComp() {
	nbComp++;
}	

function varDipl() {
	nbComp2++;
}	

function varExp() {
	nbComp3++;
}


	function newCompetence() {
		var div1, div2, input1, comp, select1, option1, option2, option3, option4, option4, option5, option6, div4, button;
		comp = document.getElementById('compForm');
		div1 = document.createElement('div');
		div1.setAttribute('class','row');
		div1.setAttribute('id','idComp'+nbComp);
		comp.appendChild(div1);
		
		
		div2 = document.createElement('div');
		div2.setAttribute('class','col-md-3');
		div1.appendChild(div2);
		
		input1 = document.createElement('input');
		input1.setAttribute('name','profil.competence['+nbComp+'].libelle');
		input1.setAttribute('type','text');
		input1.setAttribute('class','form-control');
		input1.setAttribute('placeholder','Libelle');
		div2.appendChild(input1);	
		
		div3 = document.createElement('div');
		div3.setAttribute('class','col-md-2');
		div1.appendChild(div3);
		
		select1 = document.createElement('select');
		select1.setAttribute('class','form-control');
		select1.setAttribute('name','profil.competence['+nbComp+'].note');
		div3.appendChild(select1);
		
		option1 = document.createElement('option');
		option1.setAttribute('selected','selected');
		option1.setAttribute('value','');
		option1.innerHTML='Selectionner une note';
		
		
		option2 = document.createElement('option');
		option2.setAttribute('value','1');
		option2.innerHTML='1';
		
		option3 = document.createElement('option');
		option3.setAttribute('value','2');
		option3.innerHTML='2';
		
		option4 = document.createElement('option');
		option4.setAttribute('value','3');
		option4.innerHTML='3';
		
		option5 = document.createElement('option');
		option5.setAttribute('value','4');
		option5.innerHTML='4';
		
		option6 = document.createElement('option');
		option6.setAttribute('value','5');
		option6.innerHTML='5';
		
 		select1.appendChild(option1);
		select1.appendChild(option2);
		select1.appendChild(option3);
		select1.appendChild(option4);
		select1.appendChild(option5);
		select1.appendChild(option6);
		
		div4 = document.createElement('div');
		div4.setAttribute('class','col-md-1');
		div1.appendChild(div4);
		
		button = document.createElement(button);
		button.setAttribute('type','button');
		button.setAttribute('style','margin-top: 1%;');
		button.setAttribute('class','btn btn-default');
		button.setAttribute('id','btn_new_comp');
		button.setAttribute('onclick','deleteCompetence('+nbComp+');');
		button.innerHTML='x';

		div4.appendChild(button);
		
	};
	
	function newDiplome() {
		var comp, div1, div2, div3, div4, div5, div6, input1, input2, input3, input4, button;
		comp = document.getElementById('diplForm');
		div1 = document.createElement('div');
		div1.setAttribute('class','row');
		div1.setAttribute('id','idDipl'+nbComp2);
		comp.appendChild(div1);

		div2 = document.createElement('div');
		div2.setAttribute('class','col-md-2');
		div1.appendChild(div2);
		
		input1 = document.createElement('input');
		input1.setAttribute('name','profil.diplomes['+nbComp2+'].anneeDebut');
		input1.setAttribute('type','text');
		input1.setAttribute('class','form-control');
		input1.setAttribute('placeholder','Début');
		div2.appendChild(input1);	
		
		div3 = document.createElement('div');
		div3.setAttribute('class','col-md-2');
		div1.appendChild(div3);
		
		input2 = document.createElement('input');
		input2.setAttribute('name','profil.diplomes['+nbComp2+'].anneFin');
		input2.setAttribute('type','text');
		input2.setAttribute('class','form-control');
		input2.setAttribute('placeholder','Fin');
		div3.appendChild(input2);
		
		div4 = document.createElement('div');
		div4.setAttribute('class','col-md-5');
		div1.appendChild(div4);
		
		input3 = document.createElement('input');
		input3.setAttribute('name','profil.diplomes['+nbComp2+'].libelle');
		input3.setAttribute('type','text');
		input3.setAttribute('class','form-control');
		input3.setAttribute('placeholder','Libelle');
		div4.appendChild(input3);
		
		div5 = document.createElement('div');
		div5.setAttribute('class','col-md-2');
		div1.appendChild(div5);
		
		input4 = document.createElement('input');
		input4.setAttribute('name','profil.diplomes['+nbComp2+'].lieu');
		input4.setAttribute('type','text');
		input4.setAttribute('class','form-control');
		input4.setAttribute('placeholder','Lieu');
		div5.appendChild(input4);
		
		div6 = document.createElement('div');
		div6.setAttribute('class','col-md-1');
		div1.appendChild(div6);

		button = document.createElement(button);
		button.setAttribute('type','button');
		button.setAttribute('style','margin-top: 1%;');
		button.setAttribute('class','btn btn-default');
		button.setAttribute('id','btn_new_comp');
		button.setAttribute('onclick','deleteDiplome('+nbComp2+');');
		button.innerHTML='x';

		div6.appendChild(button);
		
	};
	function newExperience() {
		var hr, comp, divP, divP2, div1, div2, div3, div4, div5, div6, div7, div8, div9, div10, div11, div12, div13, div14, input1, input2, input3, input4, input5, input6, input7, input8, button;
		comp = document.getElementById('expForm');
		
		divP2 = document.createElement('div');
		divP2.setAttribute('class','row');
		divP2.setAttribute('id','idExp'+nbComp3);
		comp.appendChild(divP2);		
		
		divP = document.createElement('div');
		divP.setAttribute('class','col-md-12');
		divP2.appendChild(divP);		
		
		div1 = document.createElement('div');
		div1.setAttribute('class','row');
		divP.appendChild(div1);		

		div2 = document.createElement('div');
		div2.setAttribute('class','col-md-2');
		div1.appendChild(div2);
		
		input1 = document.createElement('input');
		input1.setAttribute('name','profil.experiences['+nbComp3+'].anneeDebut');
		input1.setAttribute('type','text');
		input1.setAttribute('class','form-control');
		input1.setAttribute('placeholder','Début');
		div2.appendChild(input1);	
		
		div3 = document.createElement('div');
		div3.setAttribute('class','col-md-2');
		div1.appendChild(div3);
		
		input2 = document.createElement('input');
		input2.setAttribute('name','profil.experiences['+nbComp3+'].anneFin');
		input2.setAttribute('type','text');
		input2.setAttribute('class','form-control');
		input2.setAttribute('placeholder','Fin');
		div3.appendChild(input2);
		
		div4 = document.createElement('div');
		div4.setAttribute('class','col-md-2');
		div1.appendChild(div4);
		
		input3 = document.createElement('input');
		input3.setAttribute('name','profil.experiences['+nbComp3+'].entreprise');
		input3.setAttribute('type','text');
		input3.setAttribute('class','form-control');
		input3.setAttribute('placeholder','Entreprise');
		div4.appendChild(input3);
		
		div5 = document.createElement('div');
		div5.setAttribute('class','col-md-2');
		div1.appendChild(div5);

		input4 = document.createElement('input');
		input4.setAttribute('name','profil.experiences['+nbComp3+'].pays');
		input4.setAttribute('type','text');
		input4.setAttribute('class','form-control');
		input4.setAttribute('placeholder','Pays');
		div5.appendChild(input4);
		
		div6 = document.createElement('div');
		div6.setAttribute('class','col-md-2');
		div1.appendChild(div6);

		input5 = document.createElement('input');
		input5.setAttribute('name','profil.experiences['+nbComp3+'].region');
		input5.setAttribute('type','text');
		input5.setAttribute('class','form-control');
		input5.setAttribute('placeholder','Region');
		div6.appendChild(input5);
		
		div7 = document.createElement('div');
		div7.setAttribute('class','col-md-2');
		div1.appendChild(div7);

		input6 = document.createElement('input');
		input6.setAttribute('name','profil.experiences['+nbComp3+'].lieu');
		input6.setAttribute('type','text');
		input6.setAttribute('class','form-control');
		input6.setAttribute('placeholder','Ville');
		div7.appendChild(input6);
		
		div8 = document.createElement('div');
		div8.setAttribute('class','row');
		div8.setAttribute('style','margin-top: 1%;');
		divP.appendChild(div8);
	
		div9 = document.createElement('div');
		div9.setAttribute('class','col-md-12');
		div8.appendChild(div9);
		
		input7 = document.createElement('input');
		input7.setAttribute('name','profil.experiences['+nbComp3+'].poste');
		input7.setAttribute('type','text');
		input7.setAttribute('class','form-control');
		input7.setAttribute('placeholder','Poste');
		div9.appendChild(input7);
		
		div10 = document.createElement('div');
		div10.setAttribute('class','row');
		div10.setAttribute('style','margin-top: 1%;');
		divP.appendChild(div10);
	
		div11 = document.createElement('div');
		div11.setAttribute('class','col-md-12');
		div10.appendChild(div11);
		
		input8 = document.createElement('input');
		input8.setAttribute('name','profil.experiences['+nbComp3+'].description');
		input8.setAttribute('type','text');
		input8.setAttribute('class','form-control');
		input8.setAttribute('placeholder','Description de votre mission');
		div11.appendChild(input8);
		
		
		div12 = document.createElement('div');
		div12.setAttribute('class','row');
		divP.appendChild(div12);
		
		div13 = document.createElement('div');
		div13.setAttribute('class','col-md-11');
		div12.appendChild(div13);
		
		div14 = document.createElement('div');
		div14.setAttribute('class','col-md-1');
		div14.setAttribute('style','margin-top:1%;text-align:right');
		div12.appendChild(div14);
		
		button = document.createElement(button);
		button.setAttribute('type','button');
		button.setAttribute('style','margin-top: 1%;text-align:right');
		button.setAttribute('class','btn btn-default');
		button.setAttribute('id','btn_new_comp');
		button.setAttribute('onclick','deleteExperience('+nbComp3+');');
		button.innerHTML='x';

		div14.appendChild(button);
		
		hr = document.createElement('hr');
		divP.appendChild(hr);
		
// 	<div class="row">
// 		<div class="col-md-11"></div>
// 		<div class="col-md-1" style="margin-top: 1%;text-align:right">
// 			<button type="button" 
// 				class="btn btn-default" id="btn_new_comp"
// 				onClick="deleteCompetence(${i});">x</button>
// 		</div>
// 	</div>
// 	<hr>
	};
	
	function deleteCompetence(i) {
		var comp = document.getElementById('idComp'+i);  
		comp.innerHTML = '';
	};
	
	function deleteDiplome(i) {
		var dipl = document.getElementById('idDipl'+i);  
		dipl.innerHTML = '';
	};
	
	function deleteExperience(i) {
		var exp = document.getElementById('idExp'+i);  
		exp.innerHTML = '';
	};
</script>