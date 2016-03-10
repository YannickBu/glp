<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-2 gauche ">
		<ul data-spy="affix" class="menu-gauche">
			<a href="${pageContext.servletContext.contextPath}/profil">
				<li>Mon profil
						<span class="glyphicon glyphicon-user gly-menu"
						aria-hidden="true">
						</span>
				</li>
			</a>
			<a href="${pageContext.servletContext.contextPath}/publication">
				<li>Fil d'actualité
					<span class="glyphicon glyphicon-home gly-menu" aria-hidden="true"></span>
				</li>
			</a>
			<a href="#">
				<li>Mes groupes
					<span class="glyphicon glyphicon-heart gly-menu" aria-hidden="true"></span>
			</a>
					<ul>
						<a href="${pageContext.servletContext.contextPath}/groupe/${utilisateur.groupePrincipal.idGroupe}">
							<li>
								${utilisateur.groupePrincipal.nomGroupe}
							</li>
						</a>
					</ul>
				</li>
			<% if(request.isUserInRole("moderateur")){ %>
				<a href="${pageContext.servletContext.contextPath}/moderation"><li>Moderation</li></a>
			<% } %>
			<% if(request.isUserInRole("administrateur")){ %>
				<a href="${pageContext.servletContext.contextPath}/administration"><li>Administration</li></a>
			<% } %>
		</ul>
	</div>