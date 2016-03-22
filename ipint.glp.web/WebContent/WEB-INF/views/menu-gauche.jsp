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
			<c:set var="countUEA" value="0" scope="page" />
			<div class="panel-group" id="panel1-${count}">

				<a class="panel-title" data-toggle="collapse"
					data-parent="#panel1-${count}" href="#panel-element1-${count}"><li>Mes
						groupes <span class="glyphicon glyphicon-menu-down gly-menu"></span>
				</li></a>


				<div id="panel-element1-${count}" class="panel-collapse collapse">
					<div class="panel-body">
					
							<ul>
							<%
								if (request.getAttribute("grpPrincipal") != null) {
							%>
							<li class="nomGroupe"><img alt="" class="img-responsive3"
								src="${pageContext.servletContext.contextPath}/resources/img/groupeInstitutionnel.png"
								data-toggle="tooltip" title="Mon groupe principal"><a
								href="${pageContext.servletContext.contextPath}/groupe/${utilisateur.groupePrincipal.idGroupe}"
								class="nomGroupe" data-toggle="tooltip"
								title="Mon groupe principal">${utilisateur.groupePrincipal.nomGroupe}</a></li>
							<%
								}
							%>
							<c:forEach items="${utilisateur.groupes}" var="grp">
								<c:choose>
									<c:when
										test="${grp.utilisateurResponsable.nom == utilisateur.nom }">
										<li class="nomGroupe"><img alt="" class="img-responsive3"
											src="${pageContext.servletContext.contextPath}/resources/img/createdByMe.png"
											data-toggle="tooltip" title="Groupe créé par moi même"><a
											href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}"
											data-toggle="tooltip" title="Groupe créé par moi même">${grp.nomGroupe}</a></li>
									</c:when>
									<c:otherwise>
										<li><a class="nomGroupe"
											href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}">${grp.nomGroupe}</a></li>
									</c:otherwise>
								</c:choose>


							</c:forEach>

						</ul>
					</div>
				</div>
			</div>
			<%
				if (request.isUserInRole("moderateur")) {
			%>
			<a href="${pageContext.servletContext.contextPath}/moderation"><li>Moderation<span
					class="glyphicon glyphicon-flag gly-menu" aria-hidden="true"></span></li></a>
			<%
				}
			%>
			<%
				if (request.isUserInRole("administrateur")) {
			%>
			<a href="${pageContext.servletContext.contextPath}/administration"><li>Administration<span
					class="glyphicon glyphicon-blackboard gly-menu" aria-hidden="true"></span></li></a>
			<%
				}
			%>
		</ul>
		

	</div>