<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="col-md-6 publication">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<img
				src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
				class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-10">
				<h1 class="nomEtu">${utilisateur.prenom}&nbsp;${utilisateur.nom}</h1>
				<h1>${utilisateur.profil.situation}</h1>
				<div class='diplomePrincipal'>${utilisateur.profil.diplomePrincipal}-
					${utilisateur.profil.anneeDiplome}</div>
					<div>
						<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>Lille,France
					</div>
				</div>
				<hr />
			</div>
		</div>
		<br>
		<div class="navProfil">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-12">
						<div class="tabbable">
							<ul class="nav nav-tabs">
								<li class="active col-md-6"><a href="#panel-2"
									data-toggle="tab">Informations profil</a></li>
									<li class="col-md-6"><a href="#panel-1" data-toggle="tab">Voir
										les publications</a></li>

									</ul>
									<div class="tab-content">
										<div class="tab-pane " id="panel-1">
											<div class="row">
												<div class="col-md-12">
													<c:forEach items="${articles}" var="art">

													<div class="article">
														<ul>
															<li class="nomEtu"><a href="${pageContext.servletContext.contextPath}/profil/${art.utilisateur.idUtilisateur}" >${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a> via <a href="${pageContext.servletContext.contextPath}/groupe/${art.groupe.idGroupe}" > ${art.groupe.nomGroupe} </a> - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${art.datePublication.time}"/></li>
															<li>${art.contenu}</li>
														</ul>
													</div>
												</c:forEach>
											</div>
										</div>
									</div>
									<div class="tab-pane active" id="panel-2">
										<div class="row">
											<div class="col-md-12" id="scrollable">
												<div class="bloc">
													<ul>
														<li class="nomBloc" style="list-style-type: none;">Informations
															personnelles</li>
															<li>E-mail : ${utilisateur.email}</li>
															<li>Téléphone : ${utilisateur.profil.telephone}</li>
															<li>Mes attentes du réseau L1nk.fr :
																${utilisateur.profil.mesAttentes}</li>
															</ul>
														</div>

														<div class="bloc">
															<ul>
																<li class="nomBloc" style="list-style-type: none;">Informations
																	professionnelles</li>
																	<br>
																	<li>Expériences Professionnelles :
																		<c:set var="count"value="1" scope="page" />
																		<c:forEach items="${utilisateur.profil.experiences}" var="exp">
																		<div class="panel-group" id="panel-${count}">
																			<div class="panel panel-default">
																				<div class="panel-heading">
																					<a class="panel-title" data-toggle="collapse"
																					data-parent="#panel-${count}"
																					href="#panel-element-${count}">${exp.anneeDebut}/${exp.anneFin}
																					- ${exp.poste} - ${exp.entreprise} à ${exp.lieu} dans
																					la région ${exp.region} en ${exp.pays}</a>
																				</div>
																				<div id="panel-element-${count}"
																				class="panel-collapse collapse">
																				<div class="panel-body">${exp.description}</div>
																			</div>
																		</div>
																	</div>
																	<c:set var="count" value="${count + 1}" scope="page" />
																</c:forEach>
															</li> <br>
															<li>Compétences :
																<ul class="nav nav-pills">
																	<c:forEach items="${utilisateur.profil.competence}"
																	var="comp">
																	<li class="active"><a> <span
																		class="badge pull-right"> ${comp.note}</span>
																		${comp.libelle}
																	</a></li>
																</c:forEach>
															</ul>
														</li> <br>
														<li>Cursus :
															<ul>
																<c:forEach items="${utilisateur.profil.diplomes}"
																var="diplome">
																<li>${diplome.anneeDebut}/${diplome.anneFin}-
																	${diplome.libelle}- ${diplome.lieu}</li>
																</c:forEach>
															</ul>
														</li>
													</ul>
												</div>
												<div class="bloc">
													<ul>
														<li class="nomBloc" style="list-style-type: none;">Informations
															sociales</li>
															<li>Centres d'interêt :
																${utilisateur.profil.centreInteret}</li>
																<li>Groupe principal : <a href="${pageContext.servletContext.contextPath}/groupe/${utilisateur.groupePrincipal.idGroupe}">${utilisateur.groupePrincipal.nomGroupe}</a></li>
																<li>Mes groupes :
																	<ul>
																		<c:forEach items="${utilisateur.groupes}" var="grp">
																		<li><a href="${pageContext.servletContext.contextPath}/groupe/${utilisateur.grp.idGroupe}">${grp.nomGroupe}</a></li>
																	</c:forEach>
																</ul>
															</li>
															<li><img
																src="${pageContext.servletContext.contextPath}/resources/img/twitter.png"
																class="img-responsive2" alt="Responsive image" /> <img
																src="${pageContext.servletContext.contextPath}/resources/img/viadeo.png"
																class="img-responsive2" alt="Responsive image" /> <img
																src="${pageContext.servletContext.contextPath}/resources/img/google.png"
																class="img-responsive2" alt="Responsive image" /> <img
																src="${pageContext.servletContext.contextPath}/resources/img/linkedin.png"
																class="img-responsive2" alt="Responsive image" /> <img
																src="${pageContext.servletContext.contextPath}/resources/img/facebook.png"
																class="img-responsive2" alt="Responsive image" />
															</ul>
														</div>
														<%
														if (request.getAttribute("id") == null) {
														%>
														<a
														href="${pageContext.servletContext.contextPath}/profil/modifprofil"><button
														type="button" style="margin-top: 1%; float: right"
														class="btn btn-default" id="btn_new_exp">Modifier mon
														profil</button></a>
														<%
													}
													%>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
