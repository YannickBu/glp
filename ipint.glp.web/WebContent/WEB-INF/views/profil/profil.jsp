<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<div class="col-md-6 publication">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
					class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-10">
				<% if(request.getAttribute("nomWithId")!=null && !"".equals(request.getAttribute("nomWithId"))){%>
					<h1 class="nomEtu"><%=request.getAttribute("prenomWithId") %>&nbsp;<%=request.getAttribute("nomWithId") %></h1>
				<%
				}else{ 
				%>
					<h1 class="nomEtu"><%=session.getAttribute("prenomUtil") %>&nbsp;<%=session.getAttribute("nomUtil") %></h1>
				<% } %>
				<h1>${utilisateur.profil.situation}<c:if test="${not empty utilisateur.profil.lieuSituation}">,
					<span class="glyphicon glyphicon-map-marker" aria-hidden="true"></span>${utilisateur.profil.lieuSituation}</c:if>
				</h1>
				<div class='diplomePrincipal'>
					${utilisateur.profil.diplomePrincipal}-${utilisateur.profil.anneeDiplome}
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
						<div class="tab-content accueilBody">
							<div class="tab-pane " id="panel-1">
								<div class="row">
									<div class="col-md-12">
										<c:forEach items="${articles}" var="art">

											<div class="article">
												<c:if
													test="${art.utilisateur.idUtilisateur == utilisateur.idUtilisateur}">
													<div class="col-md-1" style="float: right">
														<a
															href="${pageContext.servletContext.contextPath}/supprimerArticleDuProfil/${art.idArticle}"
															style="margin-top: 1%; float: right;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/deleteArticle.png"
															style="margin-top: 1%;" class="img-responsive3"
															alt="Responsive image" data-toggle="tooltip"
															title="Supprimer" /></a>
													</div>
												</c:if>
												<ul>
													<li class="nomEtu">
														<% if(request.getAttribute("id")==null){ %>
														<c:choose>
															<c:when
																test="${art.utilisateur.idUtilisateur == utilisateur.idUtilisateur}">
																<a
																	href="${pageContext.servletContext.contextPath}/profil">Moi</a>
															</c:when>
															<c:otherwise>
																<a
																	href="${pageContext.servletContext.contextPath}/profil/${art.utilisateur.idUtilisateur}">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
															</c:otherwise>
														</c:choose>
														<% } else {%>
																<a
																	href="${pageContext.servletContext.contextPath}/profil/${art.utilisateur.idUtilisateur}">${art.utilisateur.prenom}&nbsp;${art.utilisateur.nom}</a>
														<% } %>
														 via <a
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
							<div class="tab-pane active" id="panel-2">
								<div class="row">
									<div class="col-md-12" id="scrollable">
										<div class="bloc">
											<ul>
												<li class="nomBlocProfil" style="list-style-type: none;">Informations
													personnelles</li>
												<li><b>E-mail : </b>${utilisateur.email}</li>
												<li><b>Téléphone : </b>${utilisateur.profil.telephone}</li>
												<li><b>Mes attentes du réseau L1nk.fr : </b>
													${utilisateur.profil.mesAttentes}</li>
											</ul>
										</div>

										<div class="bloc">
											<ul>
												<li class="nomBlocProfil" style="list-style-type: none;">Informations
													professionnelles</li>
												<br>
												<li><b>Expériences Professionnelles : </b>
												<c:set var="count" value="1" scope="page" /> <c:forEach
														items="${utilisateur.profil.experiences}" var="exp">
														<div style="margin-top: 2%" class="panel-group"
															id="panel-${count}">
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
													</c:forEach></li>
												<br>
												<li><b>Compétences : </b>
													<ul class="nav nav-pills">
														<c:forEach items="${utilisateur.profil.competence}"
															var="comp">
															<li class="active"><a> <span
																	class="badge pull-right"> ${comp.note}</span>
																	${comp.libelle}
															</a></li>
														</c:forEach>
													</ul></li>
												<br>
												<li><b>Cursus : </b>
													<ul>
														<c:forEach items="${utilisateur.profil.diplomes}"
															var="diplome">
															<li>${diplome.anneeDebut}/${diplome.anneFin}&nbsp;-&nbsp;${diplome.libelle}&nbsp;-&nbsp;${diplome.lieu}</li>
														</c:forEach>
													</ul></li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBlocProfil" style="list-style-type: none;">Informations
													complémentaires</li>
												<li style="margin-top: 1%;"><b>Centres d'interêt : </b>
													${utilisateur.profil.centreInteret}</li>
												<li style="margin-top: 1%;"><b>Groupe principal : </b><a
													href="${pageContext.servletContext.contextPath}/groupe/${utilisateur.groupePrincipal.idGroupe}">${utilisateur.groupePrincipal.nomGroupe}</a></li>
												<li style="margin-top: 1%;"><b>Mes groupes : </b>
													<ul>

														<c:forEach items="${utilisateur.groupes}" var="grp">

															<li><a
																href="${pageContext.servletContext.contextPath}/groupe/${grp.idGroupe}">${grp.nomGroupe}</a></li>
														</c:forEach>
													</ul></li>
												<c:forEach items="${profil.reseauxSociaux}" var="reseau">
													<c:if test="${fn:contains(reseau.lien, 'facebook')}">
														<a href="${reseau.lien}" target="_blank"
															style="margin-top: 1%;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/facebook.png"
															style="margin-top: 1%;" class="img-responsive2"
															alt="Responsive image" /></a>
													</c:if>
													<c:if test="${fn:contains(reseau.lien, 'twitter')}">
														<a href="${reseau.lien}" target="_blank"
															style="margin-top: 1%;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/twitter.png"
															style="margin-top: 1%;" class="img-responsive2"
															alt="Responsive image" /></a>
													</c:if>
													<c:if test="${fn:contains(reseau.lien, 'viadeo')}">
														<a href="${reseau.lien}" target="_blank"
															style="margin-top: 1%;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/viadeo.png"
															style="margin-top: 1%;" class="img-responsive2"
															alt="Responsive image" /></a>
													</c:if>
													<c:if test="${fn:contains(reseau.lien, 'linkedin')}">
														<a href="${reseau.lien}" target="_blank"
															style="margin-top: 1%;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/linkedin.png"
															style="margin-top: 1%;" class="img-responsive2"
															alt="Responsive image" /></a>
													</c:if>
													<c:if test="${fn:contains(reseau.lien, 'plus.google')}">
														<a href="${reseau.lien}" target="_blank"
															style="margin-top: 1%;"> <img
															src="${pageContext.servletContext.contextPath}/resources/img/google.png"
															style="margin-top: 1%;" class="img-responsive2"
															alt="Responsive image" /></a>
													</c:if>
												</c:forEach>
											</ul>
										</div>
										<%
											if (request.getAttribute("id") == null) {
										%>
										<a
											href="${pageContext.servletContext.contextPath}/profil/modifprofil"><button
												type="button" style="margin-top: 1%; float: right"
												class="btn btn-default btnModifProfif" id="btn_new_exp">Modifier
												mon profil</button></a>
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
