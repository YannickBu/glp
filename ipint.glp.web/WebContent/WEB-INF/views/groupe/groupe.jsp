<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!-- Icone groupe officiel : glyphicon glyphicon-education -->
<!-- Icone animateur groupe : glyphicon glyphicon-flag  -->

<div class="col-md-6 publication">
	<div class="container">
		<div class="row">	
			<div class="col-md-offset-3 col-md-8">
				<h1 class="nomEtu">Groupe ${leGroupe.nomGroupe}   <span class="glyphicon glyphicon-education" aria-hidden="true"></span></h1>

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
							<li class="active col-md-6">
								<a href="#panel-2"data-toggle="tab">Publications</a>
							</li>
							<li class="col-md-6">
								<a href="#panel-1" data-toggle="tab">Informations</a>
							</li>

						</ul>
						<div class="tab-content">
							<div class="tab-pane " id="panel-1">
								<div class="row">
									<div class="col-md-12">
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Informations
													du groupe</li>
												<li>
													Nom de groupe : ${leGroupe.nomGroupe}
												</li>
												<li>
													Description du groupe : ${leGroupe.description}
												</li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Personnes importantes
													du groupe </li>
												<li>
													Modérateur(s) du groupe : ${leGroupe.utilisateurResponsable}
												</li>
												<li>
													Animateur(s) du groupe :  TOI !
												</li>
											</ul>
										</div>
										<div class="bloc">
											<ul>
												<li class="nomBloc" style="list-style-type: none;">Membres
													du groupe</li>
												<c:forEach items="${membresGroupe}" var="membre">
													<li>
													Prénom / Nom : ${membre.prenom} ${membre.nom}
													</li>
												</c:forEach>
											</ul>
										</div>
									</div>
								</div>
							</div>
							<div class="tab-pane active" id="panel-2">
								<div class="row">
									<div class="col-md-12">
										<c:forEach items="${articlesGroupe}" var="article">
											<div class="article">
												<ul>
													<li class="nomEtu" style="list-style-type: none;">${article.utilisateur.prenom}
														${article.utilisateur.nom} - <fmt:formatDate type="both"
														dateStyle="short" timeStyle="short"
														value="${article.datePublication.time}" />
													</li>
													<li style="list-style-type: none;">${article.contenu}</li>
												</ul>
											</div>
										</c:forEach>
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


