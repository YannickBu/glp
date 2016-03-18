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
																	href="${pageContext.servletContext.contextPath}/profil/${utilisateur.idUtilisateur}">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
															</c:otherwise>
														</c:choose> via <a
														href="${pageContext.servletContext.contextPath}/groupe/${art.groupe.idGroupe}">
															${art.groupe.nomGroupe} </a> - <fmt:formatDate type="both"
															dateStyle="short" timeStyle="short"
															value="${art.datePublication.time}" />
													<li class="titreArt" style="margin-top: 1%;">${art.titre}</li>
													<c:choose>
														<c:when
															test="${fn:contains(art.contenu, 'http://') || fn:contains(art.contenu, 'https://') || fn:contains(art.contenu, 'www.')}">
															<c:set var="string" value="${fn:split(art.contenu,' ')}" />
															<c:forEach var="i" begin="0" end="${fn:length(string)}">
																<c:choose>
																	<c:when
																		test="${fn:startsWith(string[i], 'http://') || fn:startsWith(string[i], 'https://') || fn:startsWith(string[i], 'www.')}">
																		<a href="${string[i]}" target="_blank"
																			class="hrefChocolate">${string[i]}</a>
																	</c:when>
																	<c:otherwise>
									${string[i]} 
								</c:otherwise>
																</c:choose>
															</c:forEach>
														</c:when>
														<c:otherwise>
															<li>${art.contenu}</li>
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
