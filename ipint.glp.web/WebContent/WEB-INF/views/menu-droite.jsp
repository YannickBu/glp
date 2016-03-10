<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-2 droite">
	<div data-spy="affix" class="menu-droite">
		<p class="suggestion-titre">Suggestions de profils</p>
		<ul class="menu-droite-1">
			<a href="#"><li>Profil 1<span
					class="glyphicon glyphicon-chevron-right gly-menu"
					aria-hidden="true"></span></li></a>
		</ul>

		<p class="suggestion-titre">Suggestions de groupes</p>
		<ul class="menu-droite-2">
			<c:forEach items="${tousLesGroupes}" var="grp">
			
				<a
					href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}"><li><span class="nomGroupe">${grp.nomGroupe}</span></li></a>
			
			</c:forEach>
		</ul>
		<!-- 				<li><a -->
		<%-- 					href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}">${grp.nomGroupe}</a></li> --%>


	</div>
</div>



<div class="col-md-1"></div>
</div>
<!-- fin de la row menu-droite -->