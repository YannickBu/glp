<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-2 droite">
	<div data-spy="affix" class="menu-droite">
		<p class="suggestion-titre">Suggestions de groupes</p>
		<ul class="menu-droite-2">
			<c:forEach begin="0" end="15" items="${tousLesGroupes}" var="grp">
			
				<a
					href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}"><li class="nomGroupe">${grp.nomGroupe}</li></a>
			
			</c:forEach>
		</ul>
		<!-- 				<li><a -->
		<%-- 					href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}">${grp.nomGroupe}</a></li> --%>


	</div>
</div>



<div class="col-md-1"></div>
</div>
<!-- fin de la row menu-droite -->