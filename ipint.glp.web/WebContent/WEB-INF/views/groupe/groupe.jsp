<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">p
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

<!-- Icone groupe officiel : glyphicon glyphicon-education -->
<!-- Icone animateur groupe : glyphicon glyphicon-flag  -->
<span id="hiddenResponse" style="display: none">${createdGroupe}</span>
<div class="col-md-6 publication accueilBody">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<center>
					<h1 class="nomEtu">
						Groupe ${leGroupe.nomGroupe} <span
							class="glyphicon glyphicon-education" aria-hidden="true"></span>
					</h1>
				</center>
			</div>
		</div>
	</div>
	<br>
	<div class="navProfil">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable">
						<ul class="nav nav-tabs">
							<li class="active col-md-6"><a href="#panel-1"
								data-toggle="tab">Informations</a></li>
							<li class="col-md-6"><a href="#panel-2" data-toggle="tab">Publications</a></li>


						</ul>
						<div class="tab-content">
							<div class="tab-pane " id="panel-2">
								<div class="row">
									<div class="col-md-12">
										<c:forEach items="${articlesGroupe}" var="art">
											<div class="article">
												<c:if
													test="${art.utilisateur.idUtilisateur == utilisateur.idUtilisateur}">
													<div class="col-md-1" style="float: right">
														<a
															href="${pageContext.servletContext.contextPath}/supprimerArticleDuGroupe/${art.groupe.idGroupe}/${art.idArticle}"
															style="margin-top: 1%; float: right;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/deleteArticle.png"
															style="margin-top: 1%;" class="img-responsive3"
															alt="Responsive image" data-toggle="tooltip"
															title="Supprimer" /></a>
													</div>
												</c:if>
												<ul>
													<li class=infoArticle><c:choose>
															<c:when
																test="${art.utilisateur.idUtilisateur == utilisateur.idUtilisateur}">
																<a
																	href="${pageContext.servletContext.contextPath}/profil">Moi</a>
															</c:when>
															<c:otherwise>
																<a
																	href="${pageContext.servletContext.contextPath}/profil/${art.utilisateur.idUtilisateur}">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
															</c:otherwise>
														</c:choose> via <a
														href="${pageContext.servletContext.contextPath}/groupe/${art.groupe.idGroupe}">
															${art.groupe.nomGroupe} </a> - <fmt:formatDate type="both"
															dateStyle="short" timeStyle="short"
															value="${art.datePublication.time}" />
													<li class="titreArt" style="margin-top: 1%;">${art.titre}</li>
													<li style="margin-top: 1%;">${art.contenu}</li>

												</ul>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>

							<div class="tab-pane active" id="panel-1">
								<div class="row">
									<div class="col-md-12">
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Informations
													du groupe</li>
												<li><b>Nom de groupe : </b>${leGroupe.nomGroupe}</li>
												<li><b>Description du groupe : </b>${leGroupe.description}</li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Personnes
													importantes du groupe</li>
												<li><b>Modérateur(s) du groupe : </b></li>

												<c:choose>
													<c:when
														test="${leGroupe.utilisateurResponsable.idUtilisateur == utilisateur.idUtilisateur}">
														<li style="margin-left: 4%"><a
															href="${pageContext.servletContext.contextPath}/profil">${leGroupe.utilisateurResponsable.prenom}&nbsp;${leGroupe.utilisateurResponsable.nom}</a></li>
													</c:when>
													<c:otherwise>
														<li style="margin-left: 4%"><a
															href="${pageContext.servletContext.contextPath}/profil/${leGroupe.utilisateurResponsable.idUtilisateur}">${leGroupe.utilisateurResponsable.prenom}&nbsp;${leGroupe.utilisateurResponsable.nom}</a></li>
													</c:otherwise>
												</c:choose>
												<li><b>Animateur(s) du groupe : </b> <c:forEach
														items="${animateursGroupe}" var="animateur">
														<c:choose>
															<c:when
																test="${animateur.idUtilisateur == utilisateur.idUtilisateur}">
																<li style="margin-left: 4%"><a
																	href="${pageContext.servletContext.contextPath}/profil">${animateur.prenom}&nbsp;${animateur.nom}</a></li>
															</c:when>
															<c:otherwise>
																<li style="margin-left: 4%"><a
																	href="${pageContext.servletContext.contextPath}/profil/${animateur.idUtilisateur}">${animateur.prenom}&nbsp;${animateur.nom}</a></li>
															</c:otherwise>
														</c:choose>
													</c:forEach></li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Membres
													du groupe</li>
												<c:forEach items="${membresGroupe}" var="membre">
													<c:choose>
														<c:when
															test="${membre.idUtilisateur == utilisateur.idUtilisateur}">
															<li style="margin-left: 4%"><a
																href="${pageContext.servletContext.contextPath}/profil">${membre.prenom}&nbsp;${membre.nom}</a></li>
														</c:when>
														<c:otherwise>
															<li style="margin-left: 4%"><a
																href="${pageContext.servletContext.contextPath}/profil/${membre.idUtilisateur}">${membre.prenom}&nbsp;${membre.nom}</a></li>
														</c:otherwise>
													</c:choose>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>

							</div>
						</div>
						<c:if test="${inscription == 1 && createur == 0}">
							<a
								href="${pageContext.servletContext.contextPath}/groupe/${leGroupe.idGroupe}/desinscriptionGroupe"><button
									type="button" style="margin-top: 1%; float: right"
									class="btn btn-default" id="btn_new_exp">Se
									désinscrire</button></a>

						</c:if>
						<c:if test="${inscription == 0 && createur == 0}">

							<a
								href="${pageContext.servletContext.contextPath}/groupe/${leGroupe.idGroupe}/inscriptionGroupe"><button
									type="button" style="margin-top: 1%; float: right"
									class="btn btn-default" id="btn_new_exp">S'inscrire à
									ce groupe</button></a>
						</c:if>

						<c:if test="${createur == 1 && typeGroupe == 0}">

							<form method="POST"
								action="${pageContext.servletContext.contextPath}/groupe/${leGroupe.idGroupe}/supprimerGroupe">
								<input type="submit" value="Supprimer ce
									groupe"
									style="margin-top: 1%; float: right" class="btn btn-default"
									id="btn_new_exp" />
							</form>

						</c:if>

					</div>
					<ul>


					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<span id="hiddenUrl" style="display: none">${pageContext.servletContext.contextPath}</span>
<script>
	var response = document.getElementById("hiddenResponse").innerHTML;
	var url = document.getElementById("hiddenUrl").innerHTML;
	//url = url + "/administration";
	if (response == "SUCCESS") {
		$('#myModal1').modal('toggle');
		window.history.replaceState('administration', 'L1NK', url);
	}
	if (response == "FAIL") {
		$('#myModal2').modal('toggle');
		window.history.replaceState('administration', 'L1NK', url);
	}
</script>
