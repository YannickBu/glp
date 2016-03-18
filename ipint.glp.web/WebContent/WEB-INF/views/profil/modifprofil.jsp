<%@page import="org.springframework.web.servlet.ModelAndView"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.GregorianCalendar"%>
<%@ page import="ipint.glp.api.DTO.ProfilDTO"%>
<%@ page import="ipint.glp.api.DTO.DiplomeDTO"%>
<%@ page import="ipint.glp.api.DTO.ExperienceDTO"%>
<%@ page import="ipint.glp.api.DTO.UtilisateurDTO"%>
<%@ page import="ipint.glp.api.DTO.DiplomeDTO"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>
<script type="text/javascript">

function SoumettreFormulaire(){  
     document.forms["modif_form"].submit();
 }
  
var nbComp = ${utilisateur.profil.competence.size()};
var nbComp2 = ${utilisateur.profil.diplomes.size()};
var nbComp3 = ${utilisateur.profil.experiences.size()};
var nbReseau =  ${utilisateur.profil.reseauxSociaux.size()};

function varComp() {
    nbComp++;
}  

function varReseaux() {
    nbReseau++;
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
        input1.setAttribute('data-toggle','tooltip');
        input1.setAttribute('title','Libelle');
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
        var comp, div1, div2, div3, div4, div5, div6, input1, input2, input3, input4, button, option, date, annee, text;
        comp = document.getElementById('diplForm');
        div1 = document.createElement('div');
        div1.setAttribute('class','row');
        div1.setAttribute('id','idDipl'+nbComp2);
        comp.appendChild(div1);

        div2 = document.createElement('div');
        div2.setAttribute('class','col-md-2');
        div1.appendChild(div2);
        
        input1 = document.createElement('select');
        input1.setAttribute('name','profil.diplomes['+nbComp2+'].anneeDebut');
        input1.setAttribute('class','form-control');
        input1.setAttribute('placeholder','Début');
        input1.setAttribute('data-toggle','tooltip');
        input1.setAttribute('title','Année de début');
        
        date = new Date();
        annee = date.getFullYear();
        for (var i = annee; i > 1950; i--) {
            option = document.createElement('option');
            option.setAttribute('value', i);
            text = document.createTextNode(i); 
            option.appendChild(text);
            input1.appendChild(option);
        }
        div2.appendChild(input1);    
        
        div3 = document.createElement('div');
        div3.setAttribute('class','col-md-2');
        div1.appendChild(div3);
        
        input2 = document.createElement('select');
        input2.setAttribute('name','profil.diplomes['+nbComp2+'].anneFin');
        input2.setAttribute('class','form-control');
        input2.setAttribute('placeholder','Fin');
        input2.setAttribute('data-toggle','tooltip');
        input2.setAttribute('title','Année de fin');
        
        annee = date.getFullYear();
        for (var i = annee+1; i > 1950; i--) {
            option = document.createElement('option');
            option.setAttribute('value', i);
            text = document.createTextNode(i); 
            option.appendChild(text);
            input2.appendChild(option);
        }
        div3.appendChild(input2);
        
        div4 = document.createElement('div');
        div4.setAttribute('class','col-md-5');
        div1.appendChild(div4);
        
        input3 = document.createElement('input');
        input3.setAttribute('name','profil.diplomes['+nbComp2+'].libelle');
        input3.setAttribute('type','text');
        input3.setAttribute('class','form-control');
        input3.setAttribute('placeholder','Libelle');
        input3.setAttribute('data-toggle','tooltip');
        input3.setAttribute('title','Libelle du diplome');
        div4.appendChild(input3);
        
        div5 = document.createElement('div');
        div5.setAttribute('class','col-md-2');
        div1.appendChild(div5);
        
        input4 = document.createElement('input');
        input4.setAttribute('name','profil.diplomes['+nbComp2+'].lieu');
        input4.setAttribute('type','text');
        input4.setAttribute('class','form-control');
        input4.setAttribute('placeholder','Lieu');
        input4.setAttribute('data-toggle','tooltip');
        input4.setAttribute('title','Lieu');
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
		var div = document.getElementById('idExp0');
	    clone = div.cloneNode(true); 
		var nbExp = varExp();
		clone.id = 'idExp'+(nbExp);
		form = document.getElementById('expForm');
		form.appendChild(clone);
		oChild1 = clone.getElementsByTagName('select')[0];
		oChild2 = clone.getElementsByTagName('select')[1];
		oChild1.setAttribute('id','selectPays'+(nbExp));
		oChild1.setAttribute('onchange','chargeRegions('+(nbExp)+')');
		oChild2.setAttribute('id','selectRegion'+(nbExp));

	};
	
	function newReseauSocial() {
		var div1, div2, input1, comp, select1, option1, option2, option3, option4, option4, option5, option6, div4, button;
        comp = document.getElementById('resForm');
        div1 = document.createElement('div');
        div1.setAttribute('class','row');
        div1.setAttribute('id','idReseau'+nbReseau);
        comp.appendChild(div1);
        
        
        div2 = document.createElement('div');
        div2.setAttribute('class','col-md-3');
        div1.appendChild(div2);
        
        input1 = document.createElement('input');
        input1.setAttribute('name','profil.reseauxSociaux['+nbReseau+'].lien');
        input1.setAttribute('type','text');
        input1.setAttribute('class','form-control');
        input1.setAttribute('placeholder','Reseau social');
        input1.setAttribute('data-toggle','tooltip');
        input1.setAttribute('title','Reseau social');
        div2.appendChild(input1);
        
        div4 = document.createElement('div');
        div4.setAttribute('class','col-md-1');
        div1.appendChild(div4);
        
        button = document.createElement(button);
        button.setAttribute('type','button');
        button.setAttribute('style','margin-top: 1%;');
        button.setAttribute('class','btn btn-default');
        button.setAttribute('id','btn_new_rs');
        button.setAttribute('onclick','deleteReseauSocial('+nbReseau+');');
        button.innerHTML='x';

        div4.appendChild(button);

	};
    
    function deleteCompetence(i) {
        var comp = document.getElementById('idComp'+i);  
        comp.innerHTML = '';
    };
    
    function deleteDiplome(i) {
        var dipl = document.getElementById('idDipl'+i);  
        dipl.innerHTML = '';
    };
    
    function deleteReseauSocial(i) {
        var reseau = document.getElementById('idReseau'+i);  
        reseau.innerHTML = '';
    };
    
    function deleteExperience(i) {
        var exp = document.getElementById('idExp'+i);  
        exp.innerHTML = '';
    };
    
    function chargeRegions(i){
       var pays = document.getElementById('selectPays'+i);
		var selectedPays = pays.options[pays.selectedIndex].innerHTML;
		var region = document.getElementById('selectRegion'+i);
		var ville = document.getElementById('selectVille');
		region.selectedIndex=0;
		region.disabled=true;
		ville.value='';
		if(selectedPays == 'France'){
			region.disabled=false;
		}
    }

    $(document).ready(function(){
        $('[data-toggle="tooltip"]').tooltip(); 
    });
    
    $(document).change(function(){
        $('[data-toggle="tooltip"]').tooltip(); 
    });
</script>



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
						<label for="InputDiplomePrincipal"> Situation
							professionnelle actuelle </label>


						<div class="row" style="margin-top: 1%;">
							<div class="col-md-3">
								<form:input path="profil.situation" type="text"
									class="form-control" id="InputSituation"
									placeholder="ex: Chef de projet à CGI France" />
							</div>
							<div class="col-md-3">
								<form:input path="profil.lieuSituation" type="text"
									class="form-control" id="InputlieuSituation"
									placeholder="ex: Paris" />
							</div>
							
							<form:errors path="profil.situation" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>


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
						<c:set var="TEST" value="${profil.anneeDiplome}" scope="request" />
						<form:select path="profil.anneeDiplome" class="form-control"
							id="InputAnneDiplome">
							<%
								//(Integer) request.getAttribute("yannick")).intValue()
										Calendar calendar = new GregorianCalendar();
										calendar.setTime(new Date());
										int annee = calendar.get(Calendar.YEAR);
										for (int i = annee; i > 1950; i--) {
											out.println(
													"<option value='" + i + "' "
															+ ((((UtilisateurDTO) request.getAttribute("utilisateur")).getProfil()
																	.getAnneeDiplome() == i) ? "selected='selected'" : "")
															+ ">" + i + "</option>");
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
						<div class="form-group">
							<label for="InputNom"> Nom * : </label>
							<form:input path="nom" type="text" class="form-control"
								id="InputNom" placeholder="ex: Dupont" />
							<form:errors path="nom" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>
						<div class="form-group">
							<label for="InputPrenom"> Prénom * : </label>
							<form:input path="prenom" type="text" class="form-control"
								id="InputPrenom" placeholder="ex: Jean" />
							<form:errors path="prenom" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>
						<div class="form-group">
							<label for="InputPassword"> Mot de passe * :</label>
							<form:input path="password" type="password" class="form-control"
								id="InputPassword" />
							<form:errors path="password" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>
						<div class="form-group">
							<label for="InputTel"> Téléphone :</label>
							<form:input path="profil.telephone" type="text"
								class="form-control" id="InputTel" />
							<form:errors path="profil.telephone" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>
						<div class="form-group">
							<label for="InputMesAttentes"> Mes attentes du réseau
								L1nk.fr </label>
							<form:input path="profil.mesAttentes" type="text"
								class="form-control" id="InputMesAttentes" />
							<form:errors path="profil.mesAttentes" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>
						<div class="form-group">
							<label for="InputCursus"> Diplômes : </label>
							<div id="diplForm">
								<c:forEach begin="0" end="${utilisateur.profil.diplomes.size()}"
									var="i">
									<div class="row" id="idDipl${i}" style="margin-top: 1%;">
										<div class="col-md-2">
											<form:select path="profil.diplomes[${i}].anneeDebut"
												data-toggle="tooltip" title="Année de début"
												value="${profil.diplomes[i].anneeDebut}"
												class="form-control" id="InputDipDebut" placeholder="Début">
												<%
													Calendar calendar = new GregorianCalendar();
																calendar.setTime(new Date());
																List<DiplomeDTO> diplomes = ((UtilisateurDTO) request.getAttribute("utilisateur")).getProfil()
																		.getDiplomes();
																int varI = Integer.parseInt(pageContext.getAttribute("i").toString());
																int annee = calendar.get(Calendar.YEAR);
																for (int i = annee; i > 1950; i--) {
																	out.println("<option value='" + i + "' "
																			+ ((diplomes.get(varI) != null && diplomes.get(varI).getAnneeDebut() != null
																					&& i == diplomes.get(varI).getAnneeDebut()) ? " selected='true'" : "")
																			+ "  >" + i + "</option>");
																}
												%>
											</form:select>

										</div>
										<div class="col-md-2">
											<form:select path="profil.diplomes[${i}].anneFin"
												data-toggle="tooltip" title="Année de fin"
												value="${profil.diplomes[i].anneFin}"
												class="form-control" id="InputDipFin" placeholder="Fin">

												<%
													Calendar calendar = new GregorianCalendar();
																calendar.setTime(new Date());
																List<DiplomeDTO> diplomes = ((UtilisateurDTO) request.getAttribute("utilisateur")).getProfil()
																		.getDiplomes();
																int varI = Integer.parseInt(pageContext.getAttribute("i").toString());
																int annee = calendar.get(Calendar.YEAR);
																for (int i = annee; i > 1950; i--) {
																	out.println("<option value='" + i + "' "
																			+ ((diplomes.get(varI) != null && diplomes.get(varI).getAnneFin() != null
																					&& i == diplomes.get(varI).getAnneFin()) ? " selected='true'" : "")
																			+ "  >" + i + "</option>");
																}
												%>
											</form:select>
										</div>
										<div class="col-md-5">
											<form:input path="profil.diplomes[${i}].libelle"
												data-toggle="tooltip" title="Libelle du diplome"
												value="${profil.diplomes[i].libelle}" type="text"
												class="form-control" id="InputDipDesc" placeholder="Libelle" />
										</div>
										<div class="col-md-2">
											<form:input path="profil.diplomes[${i}].lieu"
												data-toggle="tooltip" title="Lieu"
												value="${profil.diplomes[i].lieu}" type="text"
												class="form-control" id="InputDipLieu" placeholder="Lieu" />
										</div>
										<div class="col-md-1">
											<button type="button" style="margin-top: 1%;"
												class="btn btnModifProfif" id="btn_delete_comp"
												onClick="deleteDiplome(${i});">x</button>
										</div>
										<form:errors path="profil.diplomes[${i}].*" cssStyle="color:#FF6600;font-style:italic;"/>
									</div>
								</c:forEach>


							</div>
							<button type="button" style="margin-top: 1%;"
								class="btn btnModifProfif" id="btn_new_dipl"
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
													<form:select path="profil.experiences[${i}].anneeDebut"
														value="${profil.experiences[i].anneeDebut}"
														class="form-control" id="InputExpDebut"
														placeholder="Début" data-toggle="tooltip"
														title="Année de début">
														<%
													Calendar calendar = new GregorianCalendar();
																calendar.setTime(new Date());
																List<ExperienceDTO> experiences = ((UtilisateurDTO) request.getAttribute("utilisateur")).getProfil()
																		.getExperiences();
																int varI = Integer.parseInt(pageContext.getAttribute("i").toString());
																int annee = calendar.get(Calendar.YEAR);
																for (int i = annee; i > 1950; i--) {
																	out.println("<option value='" + i + "' "
																			+ ((experiences.get(varI) != null && experiences.get(varI).getAnneeDebut() != null
																					&& i == experiences.get(varI).getAnneeDebut()) ? " selected='true'" : "")
																			+ "  >" + i + "</option>");
																}
												%>
													</form:select>
												</div>
												<div class="col-md-2">
													<form:select path="profil.experiences[${i}].anneFin"
														value="${profil.experiences[i].anneeFin}"
														class="form-control" id="InputExpFin"
														placeholder="Fin" data-toggle="tooltip"
														title="Année de Fin">
														<%
													Calendar calendar = new GregorianCalendar();
																calendar.setTime(new Date());
																List<ExperienceDTO> experiences = ((UtilisateurDTO) request.getAttribute("utilisateur")).getProfil()
																		.getExperiences();
																int varI = Integer.parseInt(pageContext.getAttribute("i").toString());
																int annee = calendar.get(Calendar.YEAR);
																for (int i = annee; i > 1950; i--) {
																	out.println("<option value='" + i + "' "
																			+ ((experiences.get(varI) != null && experiences.get(varI).getAnneFin() != null
																					&& i == experiences.get(varI).getAnneFin()) ? " selected='true'" : "")
																			+ "  >" + i + "</option>");
																}
												%>
													</form:select>
												</div>
												<div class="col-md-2">
													<form:input path="profil.experiences[${i}].entreprise"
														value="${profil.experiences[i].entreprise}" type="text"
														class="form-control" id="InputExpEnt"
														placeholder="Entreprise" data-toggle="tooltip"
														title="Entreprise" />
												</div>
											</div>
											<div class="row" style="margin-top: 1%">
												<div class="col-md-3">
													<%-- 													<form:input path="profil.experiences[${i}].pays" --%>
													<%-- 														value="${profil.experiences[i].pays}" type="text" --%>
													<%-- 														class="form-control" id="InputExpPays" placeholder="Pays" data-toggle="tooltip" title="Pays" /> --%>
													<form:select path="profil.experiences[${i}].pays"
														name="pays" id="selectPays${i}" class="form-control"
														onchange="chargeRegions(${i});"
														data-CommandArgument="${regions}" selected="selected">
														<form:option value=""> -- Choissez le pays --</form:option>
														<c:forEach var="paysI" items="${pays}">
															<form:option value="${paysI.nom}">${paysI.nom}</form:option>
														</c:forEach>
													</form:select>
												</div>
												<div class="col-md-3">
													<%-- 													<form:input path="profil.experiences[${i}].region" --%>
													<%-- 														value="${profil.experiences[i].region}" type="text" --%>
													<%-- 														class="form-control" id="InputExpRegion" --%>
													<%-- 														placeholder="Région" data-toggle="tooltip" title="Région" /> --%>
													<form:select path="profil.experiences[${i}].region"
														name="region" class="form-control" disabled="true"
														id="selectRegion${i}">
														<form:option value=""> -- Choissez la region --</form:option>
														<c:forEach var="regionI" items="${regions}">
															<form:option value="${regionI.nom}">${regionI.nom}</form:option>
														</c:forEach>
														<%-- 														<form:option id="region" value=""></form:option> --%>
													</form:select>
												</div>
												<div class="col-md-3">
													<form:input path="profil.experiences[${i}].lieu"
														value="${profil.experiences[i].lieu}" type="text"
														class="form-control" id="selectVille" placeholder="Ville"
														data-toggle="tooltip" title="Ville" />
												</div>
											</div>
											<div class="row" style="margin-top: 1%">
												<div class="col-md-12">
													<form:input path="profil.experiences[${i}].poste"
														value="${profil.experiences[i].poste}" type="text"
														class="form-control" id="InputExpPoste"
														placeholder="Intitulé de poste" data-toggle="tooltip"
														title="Poste occupé" />
												</div>
											</div>
											<div class="row" style="margin-top: 1%">
												<div class="col-md-12">
													<form:textarea path="profil.experiences[${i}].description"
														value="${profil.experiences[i].description}"
														type="text-area" class="form-control" id="InputExpDesc"
														placeholder="Description de votre mission"
														data-toggle="tooltip" title="Description de votre mission" />
												</div>
											</div>
											<form:errors path="profil.experiences[${i}].*" cssStyle="color:#FF6600;font-style:italic;"/>
											<div class="row">
												<div class="col-md-11"></div>
												<div class="col-md-1"
													style="margin-top: 1%; text-align: right">
													<button type="button" class="btn btnModifProfif"
														id="btn_new_exp" onClick="deleteExperience(${i});">x</button>
												</div>
											</div>
											<hr>
										</div>
									</div>
								</c:forEach>
							</div>

							<button type="button" class="btn btnModifProfif" id="btn_new_exp"
								onClick="varExp();newExperience(${i});">Ajouter une
								expérience</button>


						</div>
						<div class="form-group">
							<label for="InputSkills"> Compétences :</label>
							<div id="compForm">
								<c:forEach begin="0"
									end="${utilisateur.profil.competence.size()}" var="i">
									<div class="row" id="idComp${i}" style="margin-top: 1%;">
										<div class="col-md-3">
											<form:input path="profil.competence[${i}].libelle"
												value="${profil.competence[i].libelle}" type="text"
												class="form-control" id="InputDipDebut"
												placeholder="Libelle" data-toggle="tooltip" title="Libelle" />
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
												class="btn btnModifProfif" id="btn_new_comp"
												onClick="deleteCompetence(${i});">x</button>
										</div>
										<form:errors path="profil.competence[${i}].*" cssStyle="color:#FF6600;font-style:italic;"/>
									</div>
								</c:forEach>
							</div>
							<button type="button" style="margin-top: 1%;"
								class="btn btnModifProfif" id="btn_new_comp"
								onClick="varComp();newCompetence();">Ajouter une
								compétence</button>
						</div>
						<div class="form-group">
							<label for="InputInterets"> Centres d'interets :</label>
							<form:textarea path="profil.centreInteret" type="text-aera"
								rows="3" class="form-control" id="InputInterets"
								placeholder="ex: [Interet1],[Interet2],..."></form:textarea>
							<form:errors path="profil.centreInteret" cssStyle="color:#FF6600;font-style:italic;"/>
						</div>
						<div class="form-group" id="formReseaux">
							<label for="InputInterets"> Réseaux sociaux </label>
							<div id="resForm">
								<c:forEach begin="0"
									end="${utilisateur.profil.reseauxSociaux.size()}" var="i">
									<div class="row" style="margin-top: 1%" id="idReseau${i}">
										<div class="col-md-3">
											<form:input path="profil.reseauxSociaux[${i}].lien"
												value="${profil.reseauxSociaux[i].lien}" type="text"
												class="form-control" id="InputDipDebut" placeholder="Lien"
												data-toggle="tooltip" title="Lien" />
										</div>
										<div class="col-md-1">
											<button type="button" style="margin-top: 1%;"
												class="btn btnModifProfif" id="btn_remove_comp"
												onClick="deleteReseauSocial(${i});">x</button>
										</div>
										<form:errors path="profil.reseauxSociaux[${i}].*" cssStyle="color:#FF6600;font-style:italic;"/>
									</div>
								</c:forEach>
							</div>
							<button type="button" style="margin-top: 1%;"
								class="btn btnModifProfif" id="btn_new_rs"
								onClick="varReseaux();newReseauSocial();">Ajouter un
								réseau social</button>
						</div>

						<a href="${pageContext.servletContext.contextPath}/profil/"><button
								style="margin-top: 2%;" type="button"
								class="btn hrefBlack btnValidModifProfif">Annuler</button></a>
						<button style="margin-top: 2%;" type="submit"
							class="btn hrefBlack btnValidModifProfif"
							onclick="SoumettreFormulaire();">Enregistrer</button>

					</div>
				</div>
			</div>
		</div>
</form:form>
