<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-2 gauche ">
		<ul data-spy="affix" class="menu-gauche">

			<%
				if (!request.isUserInRole("personnel") && !request.isUserInRole("etudiant")) {
			%>
			<a href="${pageContext.servletContext.contextPath}/profil">
				<li>Mon profil <span class="glyphicon glyphicon-user gly-menu"
					aria-hidden="true"> </span>
			</li>
			</a>
			<%
				}
			%>
			<a href="${pageContext.servletContext.contextPath}/publication">
				<li>Fil d'actualité <span
					class="glyphicon glyphicon-home gly-menu" aria-hidden="true"></span>
			</li>
			</a>
			<a href="#">
				<li>Mes groupes <span
					class="glyphicon glyphicon-heart gly-menu" aria-hidden="true"></span>
			</a>
			<ul>
				<c:forEach items="${utilisateur.groupes}" var="grp">

					<li class="nomGroupe"><a
						href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}">${grp.nomGroupe}</a></li>
				</c:forEach>
				<%
					if (request.getAttribute("grpPrincipal") != null) {
				%>
				<a
					href="${pageContext.servletContext.contextPath}/groupe/${utilisateur.groupePrincipal.idGroupe}">
					<li class="nomGroupe">${utilisateur.groupePrincipal.nomGroupe}</li>
				</a>
				<%
					}
				%>
			</ul>
			</li>
			<%
				if (request.isUserInRole("moderateur")) {
			%>
			<a href="${pageContext.servletContext.contextPath}/moderation"><li>Moderation</li></a>
			<%
				}
			%>
			<%
				if (request.isUserInRole("administrateur")) {
			%>
			<a href="${pageContext.servletContext.contextPath}/administration"><li>Administration</li></a>
			<%
				}
			%>
		</ul>
	</div>