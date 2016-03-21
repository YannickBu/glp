<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class="navbar navbar-default navbar-fixed-top">
	<div class="header">
		<div class="row">
			<div class="col-md-12">
				<div class="container-fluid">
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<a href="${pageContext.servletContext.contextPath}/publication"><li
								class="name-logo"><img class="img-logo" alt="Logo"
									src="${pageContext.servletContext.contextPath}/resources/img/petit-logo.png">
									L1nk</li></a>
						</ul>
						<ul id="barre-recherche" class="nav navbar-nav navbar-left">
							<li style="margin-top: 1.5%">

								<form
									action="${pageContext.servletContext.contextPath}/recherche"
									method="post">
									<div class="row">
										<div class="col-md-10">
											<div class="form-group">
												<input type="text" size="50" class="form-control"
													name="recherche" placeholder="Rechercher..." />

											</div>
										</div>
										<div class="col-md-2">
											<button type="submit" class="btn btn-default">Rechercher</button>
										</div>
									</div>
								</form>

							</li>
						</ul>
						<ul style="margin-top: 0.5%" class="nav navbar-nav navbar-right">
							<li class="nom-etudiant"><%=session.getAttribute("prenomUtil") %>&nbsp;<%=session.getAttribute("nomUtil") %></li>
							<li><a
								href="${pageContext.servletContext.contextPath}/profil"><span
									class="glyphicon glyphicon-user couleurgly" aria-hidden="true"></span></a></li>
							<li class="dropdown-parametre"><a href="#"
								class="dropdown-toggle" data-toggle="dropdown" role="button"
								aria-haspopup="true" aria-expanded="false"><span
									class="glyphicon glyphicon-cog couleurgly" aria-hidden="true"></span><span
									class="caret"></span></a>
								<ul class="dropdown-menu">
									<%
										if (!request.isUserInRole("personnel") && !request.isUserInRole("etudiant")) {
									%>
									<li><a
										href='${pageContext.servletContext.contextPath}/profil/modifprofil'>Modifier
											profil</a></li>
									<%
										}
									%>
									<%
								if (request.isUserInRole("diplome") || request.isUserInRole("personnel")) {
							%>
									<li data-toggle="tooltip" title="Créer un groupe"><a
										href="${pageContext.servletContext.contextPath}/creergroupeutilisateur">Créer
											un groupe</a></li>
									<%
								}
							%>
									<%
										if (request.isUserInRole("moderateur")) {
									%>
									<li><a
										href="${pageContext.servletContext.contextPath}/moderation">Moderation</a></li>
									<%
										}
									%>
									<%
										if (request.isUserInRole("administrateur")) {
									%>
									<li><a
										href="${pageContext.servletContext.contextPath}/administration">Administration</a></li>
									<%
										}
									%>
									<li><a
										href="${pageContext.servletContext.contextPath}/deconnexion">Déconnexion</a></li>
								</ul></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</nav>
