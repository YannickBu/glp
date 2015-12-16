<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<div class="col-md-6 publication">
	<div class="container">
		<div class="row">
			<div class="col-md-2">
				<img src="${pageContext.servletContext.contextPath}/resources/img/logoA.png" class="img-responsive" alt="Responsive image">
			</div>
			<div class="col-md-10">
				<h1 class="nomEtu">Nom Etudiant</h1>
				<h2>Statut de la personne</h2>
			</div>
			<hr/>
		</div>
	</div>
	<div class="navProfil">
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-12">
					<div class="tabbable" >
						<ul class="nav nav-tabs">
							<li class="active col-md-6">
								<a href="#panel-1" data-toggle="tab">Voir les publications</a>
							</li>
							<li class="col-md-6">
								<a href="#panel-2" data-toggle="tab">Informations profil</a>
							</li>
						</ul>
						<div class="tab-content">
							<div class="tab-pane active" id="panel-1">
								<div class="row">
									<div class="col-md-12">
										<p>
											Lorem ipsum dolor sit amet, <strong>consectetur adipiscing elit</strong>. Aliquam eget sapien sapien. Curabitur in metus urna. In hac habitasse platea dictumst. Phasellus eu sem sapien, sed vestibulum velit. Nam purus nibh, lacinia non faucibus et, pharetra in dolor. Sed iaculis posuere diam ut cursus. <em>Morbi commodo sodales nisi id sodales. Proin consectetur, nisi id commodo imperdiet, metus nunc consequat lectus, id bibendum diam velit et dui.</em> Proin massa magna, vulputate nec bibendum nec, posuere nec lacus. <small>Aliquam mi erat, aliquam vel luctus eu, pharetra quis elit. Nulla euismod ultrices massa, et feugiat ipsum consequat eu.</small>
										</p>
									</div>
								</div>
							</div>
							<div class="tab-pane" id="panel-2">
								<div class="row">
									<div class="col-md-12">
										<ul>
											<li>
												Formation : 
											</li>
											<li>
												Diplomé en :
											</li>
											<li>
												E-mail : 
											</li>
											<li>
												Ville actuelle :
											</li>
											<li>
												Emploi actuel :
											</li>
											<li>
												Entreprise :
											</li>
											<li>
												Groupes :
											</li>
											<li>
												Expériences Professionnelles : <hr/>
												<div class="panel-group" id="panel-1">
													<div class="panel panel-default">
														<div class="panel-heading">
															<a class="panel-title" data-toggle="collapse" data-parent="#panel-1" href="#panel-element-1">Metier 2 - boite 2</a>
														</div>
														<div id="panel-element-1" class="panel-collapse collapse">
															<div class="panel-body">
																Missions du métier 2
															</div>
														</div>
													</div>
													<div class="panel panel-default">
														<div class="panel-heading">
															<a class="panel-title" data-toggle="collapse" data-parent="#panel-1" href="#panel-element-2">Metier 1 - boite 1</a>
														</div>
														<div id="panel-element-2" class="panel-collapse collapse">
															<div class="panel-body">
																Missions du métier 1
															</div>
														</div>
													</div>
												</div>
											</li>
											<li>
												Compétences : <hr/>
												<ul class="nav nav-pills">
													<li class="active">
														<a><span class="badge pull-right">3</span> Java </a>
													</li>	
													<li class="active">
														<a><span class="badge pull-right">1</span> SQL </a>
													</li>
												</ul>
											</li>
											<li>
												Centre d'interet :
											</li>
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