<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row">
	<div class="col-md-1"></div>
	<div class="col-md-2 gauche">
			<ul class="menu-gauche">
				<a href="${pageContext.servletContext.contextPath}/profil"><li>Mon profil<span
						class="glyphicon glyphicon-user gly-menu" aria-hidden="true"></span></li></a>
				<a href="${pageContext.servletContext.contextPath}/publication"><li>Fil d'actualité<span
						class="glyphicon glyphicon-home gly-menu" aria-hidden="true"></span></li></a>
				<a href="#"><li>Mes groupes<span
						class="glyphicon glyphicon-heart gly-menu" aria-hidden="true"></span></a>
				<ul>
					<a href="${pageContext.servletContext.contextPath}/groupe"><li>Lien Groupe 1</li></a>
				</ul>
				</li>
			</ul>
	</div>