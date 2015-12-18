<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-default">
	<div class="header">
		<div class="row">
			<div class="col-md-12">
				<div class="container-fluid">
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="name-logo"><img class="img-logo" alt="Logo"
								src="${pageContext.servletContext.contextPath}/resources/img/petit-logo.png">
								L1nk</li>
						</ul>
						<ul id="barre-recherche" class="nav navbar-nav navbar-left">
							<form class="navbar-form" role="search">
								<div class="form-group">
									<input type="text" size="50" class="form-control"
										placeholder="Rechercher...">
								</div>
								<button type="submit" class="btn btn-default">Rechercher</button>
							</form>
						</ul>
						<ul style="margin-top: 0.5%" class="nav navbar-nav navbar-right">
							<li class="nom-etudiant">${utilisateur.nom}</li>
							<li><a href="#"><span
									class="glyphicon glyphicon-user couleurgly" aria-hidden="true"></span></a></li>
							<li><a href="#"><span
									class="glyphicon glyphicon-bell couleurgly" aria-hidden="true"></span></a></li>
							<li class="dropdown-parametre"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false"><span
									class="glyphicon glyphicon-cog couleurgly" aria-hidden="true"></span><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<li><a href='${pageContext.servletContext.contextPath}/modifprofil/${utilisateur.idUtilisateur}'>Modifier profil</a></li>
									<li><a href="#">Déconnexion</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>
