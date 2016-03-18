<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-6 accueilBody">
	<div class="publication">
		<h1 class="titreRech" align="center">Recherche de résultats pour : "
		<% 
            String attribut = (String) request.getAttribute("recherche");
            out.println( attribut );
            %>
           "</h1>
		<hr size=4 align=center width="90%">
		<h2 class="soustitreRech">Profil(s) trouvé(s) :</h2>
		<ul>
		<c:forEach items="${utilisateurs}" var="util">
			<li style="margin:2%"><a href="${pageContext.servletContext.contextPath}/profil/${util.idUtilisateur}"> <span
					class="glyphicon glyphicon-user" style="padding-right:1%" aria-hidden="true"></span>${util.prenom}&nbsp;${util.nom}</a></li>
		</c:forEach>
		</ul>
		<hr size=4 align=center width="70%">
		<h2 class="soustitreRech">Groupe(s) trouvé(s) :</h2>
			<ul>
		<c:forEach items="${groupes}" var="grp">
			<li style="margin:2%"><a href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}"><span
					class="glyphicon glyphicon-star" style="padding-right:1%" aria-hidden="true"></span>${grp.nomGroupe}</a></li>
		</c:forEach>
		</ul>
	</div>


</div>
