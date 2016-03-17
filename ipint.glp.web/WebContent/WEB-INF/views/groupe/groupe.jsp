<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<!-- Icone groupe officiel : glyphicon glyphicon-education -->
<!-- Icone animateur groupe : glyphicon glyphicon-flag  -->

<div class="col-md-6 publication">
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
										<c:forEach items="${articlesGroupe}" var="article">
											<div class="article">
												<ul>

													<li class="nomEtu" style="list-style-type: none;"><a
														href="${pageContext.servletContext.contextPath}/profil/${article.utilisateur.idUtilisateur}">${article.utilisateur.prenom}
															${article.utilisateur.nom}</a> - <fmt:formatDate type="both"
															dateStyle="short" timeStyle="short"
															value="${article.datePublication.time}" /></li>
													<li style="list-style-type: none;" class="titreArt">${article.titre}</li>
													<c:choose>
														<c:when
															test="${fn:startsWith(article.contenu, 'http://') || fn:startsWith(article.contenu, 'https://') || fn:startsWith(article.contenu, 'www.')}">
															<li style="list-style-type: none;"><a
																href="${article.contenu}" target="_blank"
																class="hrefChocolate">${article.contenu}</a></li>
														</c:when>
														<c:otherwise>
															<li style="list-style-type: none;">${article.contenu}</li>
														</c:otherwise>
													</c:choose>
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
												<li>Nom de groupe : ${leGroupe.nomGroupe}</li>
												<li>Description du groupe : ${leGroupe.description}</li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Personnes
													importantes du groupe</li>
												<li>Modérateur(s) du groupe :
													${leGroupe.utilisateurResponsable.prenom}
													${leGroupe.utilisateurResponsable.nom}</li>
												<li>Animateur(s) du groupe : <c:forEach
														items="${animateursGroupe}" var="animateur">
														<li>Prénom / Nom : ${animateur.prenom}
															${animateur.nom}</li>
													</c:forEach>
												</li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Membres
													du groupe</li>
												<c:forEach items="${membresGroupe}" var="membre">
													<li>Prénom / Nom : ${membre.prenom} ${membre.nom}</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>

							</div>
						</div>
						<c:if test="${inscription == 1}">
							<a
								href="${pageContext.servletContext.contextPath}/groupe/${leGroupe.idGroupe}/desinscriptionGroupe"><button
									type="button" style="margin-top: 1%; float: right"
									class="btn btn-default" id="btn_new_exp">Se
									désinscrire</button></a>

						</c:if>
						<c:if test="${inscription == 0}">

							<a
								href="${pageContext.servletContext.contextPath}/groupe/${leGroupe.idGroupe}/inscriptionGroupe"><button
									type="button" style="margin-top: 1%; float: right"
									class="btn btn-default" id="btn_new_exp">S'inscrire à
									ce groupe</button></a>
						</c:if>
						<c:if test="${inscription == 2}">
							<button type="button" style="margin-top: 1%; float: right"
								class="btn btn-default" id="btn_new_exp" disabled="true">En
								attente de validation d'inscription</button>
						</c:if>

					</div>
					<ul>


					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
