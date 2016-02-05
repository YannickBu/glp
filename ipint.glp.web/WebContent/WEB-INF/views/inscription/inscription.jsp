<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body class="body_without_menu">
	<div class="entete-inscrip">
		<div class="row">
			<center>
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
					class="img-responsive" alt="Responsive image">
				<h1 class="name-logo">L1nk</h1>
				<h3>Inscription</h3>
			</center>
		</div>
	</div>
	<div class="label col-md-9">
		<br> <label>(*) Champs obligatoires</label>
	</div>
	<div class="form-inscrip">
		<form:form class="form-horizontal" role="form" method="post" action=""
			commandName="utilisateurTmp">
			<div class="form-group">
				<label for="inputNom" class="col-md-4 control-label">Nom : <label
					class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<form:input path="nom" type="text" class="form-control"
						id="inputNom" placeholder="Ex: Dupond"></form:input>
					<form:errors path="nom" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputPrenom" class="col-md-4 control-label">Prenom
					: <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<form:input path="prenom" type="text" class="form-control"
						id="inputPrenom" placeholder="Ex: Jean"></form:input>
					<form:errors path="prenom" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="col-md-4 control-label">Email
					: <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<form:input path="email" type="email" class="form-control"
						id="inputEmail" placeholder="Ex: Jean.Dupond@example.com"></form:input>
					<form:errors path="email" />
				</div>
			</div>
			<div class="form-group">
				<label for="inputBirthday" class="col-md-4 control-label">Date
					de naissance : <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<div class="input-append date form_datetime">
						<form:input path="dateNaissance" class="form-control"
							id="inputBirthday" placeholder="Ex: 1990/10/28" readonly="true"></form:input>
						<span class="add-on"><i class="icon-th"></i></span>

						<spring:bind path="dateNaissance">
							<c:forEach items="${status.errorMessages}" var="error">
						    La date de naissance est invalide
						</c:forEach>
						</spring:bind>

					</div>
				</div>
			</div>
			<div class="form-group">
				<label for="inputDiplome" class="col-md-4 control-label">Dernier
					diplome obtenu à l'université : <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<form:input path="diplome" type="text" class="form-control"
						id="inputDiplome" placeholder="Ex: Master Informatique"></form:input>
					<form:errors path="diplome" />
				</div>
			</div>
			<div class="form-group">
				<label for="selectAnnee" class="col-md-4 control-label">Année
					d'obtention du diplôme : <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<form:input path="anneeDiplome" type="number" id="selectAnnee"
						class="form-control" placeholder="Ex: 2012"></form:input>
					<form:errors path="anneeDiplome" />
				</div>
			</div>
			<div class="form-group">
				<label for="selectFormation" class="col-md-4 control-label">Groupe
					selectionné : <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<form:select path="groupePrincipal.idGroupe" name="groupe"
						id="selectFormation" class="form-control">
						<form:option value="0"> -- Choissez un groupe --</form:option>
						<c:forEach var="groupe" items="${groupes}">
							<form:option value="${groupe.idGroupe}">${groupe.nomGroupe}</form:option>
						</c:forEach>
					</form:select>
					<form:errors path="groupePrincipal" />
				</div>
			</div>
			<div class="row ">
				<label for="" class="col-md-4 control-label"></label> <a
					href="${pageContext.servletContext.contextPath}/profil/"><button
						type="button" class="btn btn-default col-md-2" style="margin-top: 1%;">Retour</button></a>
				<button type="submit" class="btn btn-default col-md-2"
					style="margin-top: 1%;">S'inscrire</button>
			</div>
		</form:form>
	</div>