<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<script type="text/javascript"
	src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>
<div class="col-md-6 publication">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
					class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-10">
				<h1 class="nomEtu">${utilisateur.prenom} ${utilisateur.nom}</h1>
				<h2>${utilisateur.statut}</h2>
			</div>
			<hr />
		</div>
	</div>
	<div class="navProfil">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable">
						<ul class="nav nav-tabs">
							<li class="active col-md-6"><a href="#panel-1"
								data-toggle="tab">Voir les publications</a></li>
							<li class="col-md-6"><a href="#panel-2" data-toggle="tab">Informations
									profil</a></li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="panel-1">
								<div class="row">
									<div class="col-md-12">
										<c:forEach items="${articles}" var="art">

											<div class="article">
												<ul>
													<li class="nomEtu">${utilisateur.nom}</li>
													<li>${art.contenu}</li>
												</ul>
											</div>
										</c:forEach>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="panel-2">
								<div class="row">
									<div class="col-md-12" id="scrollable">
										<ul>
<%-- 											<li>Formation : ${utilisateur.profil.cursus}</li> --%>
											<li>E-mail : ${utilisateur.email}</li>
											<li>Téléphone : ${utilisateur.profil.telephone}</li>
<!-- 											<li>Emploi actuel :</li> -->
<!-- 											<li>Entreprise :</li> -->
<!-- 											<li>Groupes :</li> -->
<!-- 											<li>Expériences Professionnelles : -->
<!-- 												<hr /> -->
<!-- 												<div class="panel-group" id="panel-1"> -->
<!-- 													<div class="panel panel-default"> -->
<!-- 														<div class="panel-heading"> -->
<!-- 															<a class="panel-title" data-toggle="collapse" -->
<!-- 																data-parent="#panel-1" href="#panel-element-1">Metier -->
<!-- 																2 - boite 2</a> -->
<!-- 														</div> -->
<!-- 														<div id="panel-element-1" class="panel-collapse collapse"> -->
<!-- 															<div class="panel-body">Missions du métier 2</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 													<div class="panel panel-default"> -->
<!-- 														<div class="panel-heading"> -->
<!-- 															<a class="panel-title" data-toggle="collapse" -->
<!-- 																data-parent="#panel-1" href="#panel-element-2">Metier -->
<!-- 																1 - boite 1</a> -->
<!-- 														</div> -->
<!-- 														<div id="panel-element-2" class="panel-collapse collapse"> -->
<!-- 															<div class="panel-body">Missions du métier 1</div> -->
<!-- 														</div> -->
<!-- 													</div> -->
<!-- 												</div> -->
<!-- 											</li> -->
											<li>Compétences : ${utilisateur.profil.competence}
<!-- 												<hr /> -->
<!-- 												<ul class="nav nav-pills"> -->
<!-- 													<li class="active"><a><span -->
<!-- 															class="badge pull-right">3</span> Java </a></li> -->
<!-- 													<li class="active"><a><span -->
<!-- 															class="badge pull-right">1</span> SQL </a></li> -->
<!-- 												</ul> -->
											</li>
											<li>Centre d'interet : ${utilisateur.profil.centreInteret}</li>
										</ul>

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