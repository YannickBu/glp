<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<script>
	$(document).ready(function viderForm() {
		document.getElementById("titrePubli").value = "";
		document.getElementById("contenuPubli").value = "";
	});
</script>

<div class="col-md-6 accueilBody">
	<div class="publication">
		<ul>
			<form:form id="pubForm" role="form"
				action="${pageContext.servletContext.contextPath}/publication"
				method="post" commandName="article">
				<li>
					<div class="row">
						<div class="col-md-4 publierArt">Publier un article via</div>
						<div class="col-md-8" style="padding-right: 5.5%">
							<form:select class="form-control" id="selectGroupePublication"
								path="groupe.idGroupe">
								<%-- 								<c:forEach items="${utilisateur.groupes}" var="grp"> --%>
								<%-- 									<form:option value="Selectionner un groupe de publication" --%>
								<%--  										selected="selected"></form:option>  --%>
<%-- 								<form:option value="${utilisateur.groupePrincipal.nomGroupe}"></form:option> --%>
								<form:options items="${utilisateur.groupes}"
									itemValue="idGroupe" itemLabel="nomGroupe"></form:options>
								<%-- 								</c:forEach> --%>
							</form:select>
						</div>
					</div>
				</li>

				<li class="formPubli">

					<form:input class="inputForm form-control"
						path="titre" placeholder="Titre de la publication" id="titrePubli" value=""/> 

					<form:textarea
						type="text-area" rows="3" class="inputForm form-control" path="contenu"
						placeholder="Tapez votre publication..." id="contenuPubli"/>
					
					<input
					class="inputBtn btn btn-default btnModifProfif" type="submit" value="Publier l'article" />
				</li>
			</form:form>
		</ul>
	</div>
	<c:forEach items="${articles}" var="art">

		<div class="article">
			<ul>
				<li class="nomEtu"><a
					href="${pageContext.servletContext.contextPath}/profil/${art.utilisateur.idUtilisateur}">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
					via <a
					href="${pageContext.servletContext.contextPath}/groupe/${art.groupe.idGroupe}">
						${art.groupe.nomGroupe} </a> - <fmt:formatDate type="both"
						dateStyle="short" timeStyle="short"
						value="${art.datePublication.time}" /></li>
				<li class="titreArt">${art.titre}</li>
				<li>${art.contenu}</li>
			</ul>
		</div>
	</c:forEach>

</div>
