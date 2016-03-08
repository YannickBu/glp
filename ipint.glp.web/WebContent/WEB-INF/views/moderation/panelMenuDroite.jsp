<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-2 droite">
	<div class="menu-droite">
		<p class="suggestion-titre">
			<label>En attente d'inscription</label>
		</p>
		<ul class="menu-droite-1">
			<c:forEach var="listUtilisateurEnAttente" items="${list}">
				<a href="${pageContext.servletContext.contextPath}/moderation/panelInscription/${listUtilisateurEnAttente.idUtilisateurEnAttente}"><li><c:out
							value="${listUtilisateurEnAttente.nom}" /> <c:out
							value="${listUtilisateurEnAttente.prenom}" /><span
						class="glyphicon glyphicon-chevron-right gly-menu"
						aria-hidden="true"></span></li></a>
			</c:forEach>
		</ul>
	</div>
</div>



<div class="col-md-1"></div>
</div>
<!-- fin de la row menu-droite -->