<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script>
	function notif(id1){
		var span = document.createElement("span");
		span.className = "glyphicon glyphicon-warning-sign";
		var element = document.getElementById(id1).childNodes[1].childNodes[1].childNodes[1];
/* 		alert(element.innerHTML); */
 		element.insertBefore(span,element.childNodes[0]); 
	}
</script>
<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center">Panel de modération
				</h1>
				<hr />
				<div style="margin-bottom: 1%"><span class="glyphicon glyphicon-warning-sign"></span> = des incriptions sont en attentes de validation. </div>
				<c:set var="count" value="1" scope="request" />
				<c:forEach items="${groupesGeres}" var="groupe">
					<c:set var="countUEA" value="0" scope="page" />
					<div class="panel-group" id="panel1-${count}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="panel-title" data-toggle="collapse"
									data-parent="#panel1-${count}" href="#panel-element1-${count}">&nbsp;${groupe.nomGroupe}<span class="glyphicon glyphicon-menu-down" style="float:  right;"></span></a>
							</div>

							<div id="panel-element1-${count}" class="panel-collapse collapse">
								<div class="panel-body">
									<ul>
										<li> Inscriptions en attente :
										<ul>
											<c:forEach items="${list}" var="utilEnAttente">
												<c:if test="${utilEnAttente.groupePrincipal.idGroupe == groupe.idGroupe}">
												<c:set var="countUEA" value="${count + 1}" scope="page" />
												<li><a href="${pageContext.servletContext.contextPath}/moderation/panelInscription/${utilEnAttente.idUtilisateurEnAttente}">${utilEnAttente.prenom}&nbsp;${utilEnAttente.nom}</a></li>
												</c:if>
											</c:forEach>
										</ul>
										</li>
							
									</ul>
								</div>
							</div>
						</div>
						<c:if test="${countUEA != 0}">
						<script>
							var nbr = <%= request.getAttribute("count") %>
							var idPanel = "panel1-"+nbr;
							notif(idPanel);
						</script>
					</c:if>
					</div>
					<c:set var="count" value="${count + 1}" scope="request" />
					<c:remove var="countUEA" scope="page"/>
				</c:forEach>
				
			</div>
		</div>
	</div>
</div>
