<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>
<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h2 class="text-center">Panel d'administation des inscriptions </h2>
				<label>Informations du demandeur :</label>
				<ul>
					<li>Nom : utilisateurTmp.nom</li>
					<li>Prénom : utilisateurTmp.prenom</li>
					<li>Date de naissance : utilisateurTmp.birthday</li>
					<li>E-mail : utilisateurTmp.email</li>
					<li>Diplome obtenu : utilisateurTmp.diplome</li>
					<li>Année d'obtention : utilisateurTmp.anneeDiplome</li>
					<li>Groupe sélectionné : utilisateurTmp.groupePrincipal</li>

				</ul>
				<form:form role="form" method="post" action="2"
					commandName="utilisateurTmp">
					<div class="form-group">
						<label for="OptionalMessage"> Message complémentaire : </label>
						<form:textarea path="profil.competence" type="text-aera" rows="3"
							class="form-control" id="OptionalMessage"></form:textarea>
					</div>

					<button type="submit" class="btn btn-default">Accepter</button>
					<button type="submit" class="btn btn-default">Refuser</button>
				</form:form>
			</div>
		</div>
	</div>



</div>