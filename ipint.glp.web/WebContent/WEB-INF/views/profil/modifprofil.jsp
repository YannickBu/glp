<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>




<div class="col-md-6 publication">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
					class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-10">
				<h1 class="nomEtu">${utilisateur.prenom} ${utilisateur.nom}</h1>
				<h2>${utilisateur.statut}</h2>
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
					<form:form role="form" method="post" action=""
						commandName="utilisateur">
						<div class="form-group">
							<label for="InputNom"> Nom </label>
							<form:input path="nom" type="text" class="form-control"
								id="InputNom" placeholder="ex: Dupont" />
						</div>
						<div class="form-group">
							<label for="InputPrenom"> Prénom </label>
							<form:input path="prenom" type="text" class="form-control"
								id="InputPrenom" placeholder="ex: Jean" />
						</div>
						<div class="form-group">
							<label for="InputPassword"> Mot de passe </label>
							<form:input path="password" type="password" class="form-control"
								id="InputPassword" />
						</div>
						<div class="form-group">
							<label for="InputTel"> Téléphone </label>
							<form:input path="profil.telephone" type="text"
								class="form-control" id="InputTel" />
						</div>
						<div class="form-group">
							<label for="InputCursus"> Diplômes </label>

							<div id="diplForm">
								<c:forEach begin="0" end="${utilisateur.profil.diplomes.size()}"
									var="i">
									<div class="row">
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
										<div class="col-md-6">
											<form:input path="profil.diplomes[${i}].libelle"
												value="${profil.diplomes[i].libelle}" type="text"
												class="form-control" id="InputDipDesc" placeholder="Libelle" />
										</div>
										<div class="col-md-2">
											<form:input path="profil.diplomes[${i}].lieu" value="${profil.diplomes[i].lieu}" type="text"
												class="form-control" id="InputDipDesc" placeholder="Lieu" />
										</div>

									</div>
								</c:forEach>
								<div class="nouveau_dipl"></div>

								<button type="button" style="margin-top: 1%;"
									class="btn btn-default" id="btn_new_dipl"
									onClick="newDiplome();">+</button>
							</div>

						</div>
						<div class="form-group">
							<label for="InputExp"> Expériences Professionnelles </label>
							<div id="expForm">
								<c:forEach begin="0"
									end="${utilisateur.profil.experiences.size()}" var="i">
									<div class="row">
										<div class="col-md-2">
											<form:input path="profil.experiences[${i}].anneeDebut"
												value="${profil.experiences[i].anneeDebut}" type="text"
												class="form-control" id="InputExpDebut" placeholder="Début" />
										</div>
										<div class="col-md-2">
											<form:input path="profil.experiences[${i}].anneFin"
												value="${profil.experiences[i].anneFin}" type="text"
												class="form-control" id="InputExpAnneFin" placeholder="Fin" />
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
										<div class="col-md-4">
											<form:input path="profil.experiences[${i}].poste"
												value="${profil.experiences[i].poste}" type="text"
												class="form-control" id="InputExpPoste" placeholder="Poste" />
										</div>
									</div>
									<div class="row" style="margin-top: 1%">
										<div class="col-md-12">
											<form:input path="profil.experiences[${i}].description"
												value="${profil.experiences[i].description}" type="text"
												class="form-control" id="InputExpDesc"
												placeholder="Description de votre mission" />
										</div>
									</div>
								</c:forEach>
							</div>

							<div class="nouvelle_exp"></div>

							<button type="button" style="margin-top: 1%;"
								class="btn btn-default" id="btn_new_exp"
								onClick="newExperience();">+</button>
							<hr>

						</div>
						<div class="form-group">
							<label for="InputSkills"> Compétences </label>
							<div id="compForm">
								<c:forEach begin="0"
									end="${utilisateur.profil.competence.size()}" var="i">
									<div class="row" id="idComp">
										<div class="col-md-3">
											<form:input path="profil.competence[${i}].libelle"
												value="${profil.competence[i].libelle}" type="text"
												class="form-control" id="InputDipDebut"
												placeholder="Libelle" />
										</div>
										<div class="col-md-2">
											<form:input path="profil.competence[${i}].note"
												value="${profil.competence[i].note}" type="text"
												class="form-control" id="InputDipAnneFin" placeholder="Note" />
										</div>
										<div class="col-md-1">
											<button type="button" style="margin-top: 1%;"
												class="btn btn-default" id="btn_new_comp"
												onClick="deleteCompetence();">-</button>
										</div>
									</div>
								</c:forEach>
								<div id="nouvelle_comp"></div>

								<button type="button" style="margin-top: 1%;"
									class="btn btn-default" id="btn_new_comp"
									onClick="newCompetence();">+</button>
							</div>
						</div>
						<div class="form-group">
							<label for="InputInterets"> Centres d'interets </label>
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
						<button type="submit" class="btn btn-default">Modifier</button>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function newCompetence() {
		var comp = document.getElementById('nouvelle_comp');
		comp.innerHTML = comp.innerHTML
				+ '<div class="row">'
				+ '<div class="col-md-3">'
				+ '<form:input path="utilisateur" type="text" class="form-control" id="InputDipDebut" placeholder="Libelle" />'
				+ '</div><div class="col-md-1"><form:input path="utilisateur" type="text" class="form-control" id="InputDipAnneFin" placeholder="Note" /></div>'
				+ '<div class="col-md-1"><button type="button" style="margin-top: 1%;" class="btn btn-default" id="btn_new_comp" onClick="deleteCompetence();">-</button> </div></div>';
	};
	function deleteCompetence() {
		var comp = document.getElementById('nouvelle_comp');
		comp.innerHTML = '';
	};
	function newDiplome() {
		var comp = document.getElementById('nouveau_dipl');
		comp.innerHTML = '';
	};
	function newExperience() {
		var comp = document.getElementById('nouvelle_exp');
		comp.innerHTML = '';
	};
</script>