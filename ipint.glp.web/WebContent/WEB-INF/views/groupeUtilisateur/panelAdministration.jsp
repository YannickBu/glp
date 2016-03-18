<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header alert-white">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Succès !</h4>
			</div>
			<div class="modal-body alert-white">
				<p>Le groupe a été correctement créé.</p>
			</div>
			<div class="modal-footer alert-white">
				<button type="button" id="Close" class="btn btn-default"
					data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header alert-white">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Echec !</h4>
			</div>
			<div class="modal-body alert-white">
				<p>Le groupe n'a pas été créé suite à une erreur.</p>
			</div>
			<div class="modal-footer alert-white">
				<button type="button" id="Close" class="btn btn-default"
					data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>



<span id="hiddenResponse" style="display: none">${createdGroupe}</span>
<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="nomEtu text-center">Panel d'administration</h1>
				<hr />
				<a
					href="${pageContext.servletContext.contextPath}/administration/creergroupe">
					<button type="button" style="margin-bottom: 1%" class="btn btn-default"
						id="btn_new_exp">Créer un nouveau groupe</button>
				</a>
				<c:set var="count" value="1" scope="page" />
				<c:forEach items="${groupesOfficiel}" var="groupe">
					<div class="panel-group" id="panel1-${count}">
						<div class="panel panel-default">
							<div class="panel-heading">
								<a class="panel-title" data-toggle="collapse"
									data-parent="#panel1-${count}" href="#panel-element1-${count}">${groupe.nomGroupe} <span class="glyphicon glyphicon-menu-down" style="float:  right;"></span></a>
							</div>
							<div id="panel-element1-${count}" class="panel-collapse collapse">
								<div class="panel-body">
									<ul>
										<li> Description : ${groupe.description}</li>
										<li> Modérateur : ${groupe.utilisateurResponsable.prenom}&nbsp;${groupe.utilisateurResponsable.nom}
										<li> Animateurs :
										<ul>
											<c:forEach items="${groupe.animateurs}" var="animateur">
												<li>${animateur.prenom}&nbsp;${animateur.nom}</li>
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
				<a
					href="${pageContext.servletContext.contextPath}/administration/creergroupe">
					<button type="button" class="btn btn-default"
						id="btn_new_exp">Créer un nouveau groupe</button>
				</a>
			</div>
		</div>
	</div>
</div>
<span id="hiddenUrl" style="display: none">${pageContext.servletContext.contextPath}</span>
<script>
	var response = document.getElementById("hiddenResponse").innerHTML;
	var url = document.getElementById("hiddenUrl").innerHTML;
	url = url + "/administration";
	if (response == "SUCCESS") {
		$('#myModal1').modal('toggle');
		window.history.replaceState('administration', 'L1NK', url);
	}
	if (response == "FAIL") {
		$('#myModal2').modal('toggle');
		window.history.replaceState('administration', 'L1NK', url);
	}
</script>