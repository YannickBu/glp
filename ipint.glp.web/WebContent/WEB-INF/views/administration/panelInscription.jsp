<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>

<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center">Panel d'administation des inscriptions
				</h1>
				<br /> <label>Informations du demandeur:</label>
				<ul>
					<li><u>Nom:</u> ${utilisateurTmp.nom}</li>
					<li><u>Prénom:</u> ${utilisateurTmp.prenom}</li>
					<li><u>Date de naissance:</u> <fmt:formatDate
							value="${utilisateurTmp.dateNaissance}" pattern="dd/MM/yyyy" /></li>
					<li><u>E-mail:</u> ${utilisateurTmp.email}</li>
					<li><u>Diplome obtenu:</u> ${utilisateurTmp.diplome}</li>
					<li><u>Année d'obtention:</u> ${utilisateurTmp.anneeDiplome}</li>
					<li><u>Groupe sélectionné:</u>
						${utilisateurTmp.groupePrincipal.nomGroupe}</li>

				</ul>
				<br />
				<form:form role="form" method="post"
					action="${utilisateurTmp.idUtilisateurEnAttente}"
					commandName="utilisateurTmp">
					<div class="form-group">
						<label for="OptionalMessage"> Message complémentaire
							(optionnel): </label>
						<form:textarea path="" type="text-aera" rows="4"
							class="form-control" name="optionalMessage"
							placeholder="Entrer un message à ajouter au mail automatique de réponse à la demande d'inscription."></form:textarea>
					</div>

					<button type="submit" class="btn btn-default" name="action"
						value="Accepter">Accepter</button>
					<button type="submit" class="btn btn-default" name="action"
						value="Refuser">Refuser</button>
				</form:form>
			</div>
		</div>
	</div>



</div>