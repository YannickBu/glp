<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<script>
	$(document).ready(function viderForm() {
		var titre = document.getElementById("titrePubli");
		var contenu = document.getElementById("contenuPubli");
		titre.value = '';
		contenu.value = '';
	});
</script>

<script type="text/javascript">
	function confirmerSuppression() {
		width = 300;
		height = 200;
		if (window.innerWidth) {
			var left = (window.innerWidth - width) / 2;
			var top = (window.innerHeight - height) / 2;
		} else {
			var left = (document.body.clientWidth - width) / 2;
			var top = (document.body.clientHeight - height) / 2;
		}
		window.open('popupSupprimerArticle.jsp', 'Supprimer un article',
				'menubar=no, scrollbars=no, top=' + top + ', left=' + left
						+ ', width=' + width + ', height=' + height + '');
	}
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

				<li class="formPubli"><form:input
						class="inputForm form-control" path="titre"
						placeholder="Titre de la publication" id="titrePubli" value="" />

					<form:textarea type="text-area" rows="3"
						class="inputForm form-control" path="contenu"
						placeholder="Tapez votre publication..." id="contenuPubli" /> <input
					class="inputBtn btn btn-default btnModifProfif" type="submit"
					value="Publier l'article" onclick="viderForm();"/> <form:errors class="error" path="contenu" /></li>
			</form:form>
		</ul>
	</div>
		<c:forEach items="${articles}" var="art">

		<div class="article">
			<c:if
				test="${art.utilisateur.idUtilisateur == utilisateur.idUtilisateur}">
				<div class="col-md-1" style="float: right">
					<a
						href="${pageContext.servletContext.contextPath}/supprimerArticleDuFilDactualite/${art.idArticle}"
						style="margin-top: 1%; float: right;"> <img
						src="${pageContext.servletContext.contextPath}/resources/img/deleteArticle.png"
						style="margin-top: 1%;" class="img-responsive3"
						alt="Responsive image" data-toggle="tooltip" title="Supprimer" /></a>
				</div>
			</c:if>
			<ul>
				<li class="infoArticle">
				<c:choose>
					<c:when test="${art.utilisateur.idUtilisateur == utilisateur.idUtilisateur}">
						<a class="blackLink" href="${pageContext.servletContext.contextPath}/profil">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
					</c:when>
					<c:otherwise>
					<a href="${pageContext.servletContext.contextPath}/profil/${utilisateur.idUtilisateur}">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
					</c:otherwise>
				</c:choose> via <a class="blackLink"
					href="${pageContext.servletContext.contextPath}/groupe/${art.groupe.idGroupe}">
						${art.groupe.nomGroupe} </a> - <fmt:formatDate type="both"
						dateStyle="short" timeStyle="short"
						value="${art.datePublication.time}" />
				<li class="titreArt" style="margin-top: 1%;">${art.titre}</li>
				<c:choose>
					<c:when
						test="${fn:contains(art.contenu, 'http://') || fn:contains(art.contenu, 'https://') || fn:contains(art.contenu, 'www.')}">
						<c:set var="string" value="${fn:split(art.contenu,' ')}" />
						<c:forEach var="i" begin="0" end="${fn:length(string)}">
							<c:choose>
								<c:when
									test="${fn:startsWith(string[i], 'http://') || fn:startsWith(string[i], 'https://') || fn:startsWith(string[i], 'www.')}">
									<a href="${string[i]}" target="_blank"
										class="hrefChocolate">${string[i]}</a>
								</c:when>
								<c:otherwise>
									${string[i]} 
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<li>${art.contenu}</li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</c:forEach>

</div>