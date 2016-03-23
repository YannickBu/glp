<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<body class="body_without_menu" style="overflow: hidden">
	<div class="entete-inscrip">
		<div class="row">
			<center>
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
					class="img-responsive" alt="Responsive image">
				<h1 class="name-logo">L1nk</h1>
				<h3>Choix du groupe</h3>
			</center>
		</div>
	</div>
	<br/>
	<div class="form-inscrip">
		<form class="form-horizontal" method="post" action="${pageContext.servletContext.contextPath}/cas">
			<div class="form-group">
				<label for="selectFormation" class="col-md-4 control-label">
					Votre groupe : <label class="label2 taille">*</label>
				</label>
				<div class="input col-md-4">
					<select name="idGroupePrincipal"
						id="selectFormation" class="form-control">
<%-- 						<option value="0"> -- Choissez un groupe --</option> --%>
						<c:forEach var="groupe" items="${groupes}">
							<option value="${groupe.idGroupe}">${groupe.nomGroupe}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
			<label for="selectFormation" class="col-md-4 control-label"></label>
				<div class="input col-md-4">
					<a href="${pageContext.servletContext.contextPath}/connexion/">
						<button type="button" class="btn btn-default col-md-6"
							style="margin-top: 1%;">Retour</button>
					</a>
					<button type="submit" class="btn btn-default col-md-6"
						style="margin-top: 1%;">S'inscrire</button>
				</div>
			</div>
		</form>
	</div>