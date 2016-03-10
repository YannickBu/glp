<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>
<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center">Panel de modération
				</h1>
				<hr />
				<c:set var="count" value="1" scope="page" />
				<c:forEach items="${groupesGeres}" var="groupe">
					<div class="panel-group" id="panel1-${count}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="panel-title" data-toggle="collapse"
									data-parent="#panel1-${count}" href="#panel-element1-${count}">${groupe.nomGroupe}</a>
							</div>
							<div id="panel-element1-${count}" class="panel-collapse collapse">
								<div class="panel-body">
									<ul>
										<li> Inscriptions en attente :
										<ul>
											<c:forEach items="${list}" var="utilEnAttente">
												<c:if test="${utilEnAttente.groupePrincipal.idGroupe == groupe.idGroupe}">
												<li>${utilEnAttente.prenom}&nbsp;${utilEnAttente.nom}</li>
												</c:if>
											</c:forEach>
										</ul>
										</li>
							
									</ul>
								</div>
							</div>
						</div>
					</div>
					<c:set var="count" value="${count + 1}" scope="page" />
				</c:forEach>
				
			</div>
		</div>
	</div>
</div>