
<body>
	<div class="jumbotron container-table">
		<div class="vertical-center-row ">
			<div class="row row-logo">
				<div class="col-md-6 col-md-offset-3">
					<center>
						<img
							src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
							class="img-responsive" alt="Responsive image">
						<h1 class="name-logo">L1nk</h1>
						<h3>Le réseau d'anciens de Lille</h3>
					</center>
				</div>
			</div>
			<div class="row container-bas">
				<div class="col-md-5 col-md-offset-3">
					<form class="form-horizontal" action="j_security_check"
						method="POST">
						<div class="form-group">
							<label for="inputEmail" class="col-md-4 control-label">Adresse
								email :</label>
							<div class="input-group col-md-8">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
								<input type="email" class="form-control" id="inputEmail"
									placeholder="Email" name="j_username">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword" class="col-md-4 control-label">Mot
								de passe :</label>
							<div class="input-group col-md-8">
								<span class="input-group-addon"><span
									class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
									type="password" class="form-control" id="inputPassword"
									placeholder="Password" name="j_password">
							</div>
						</div>
						<div class="row ">
							
							<% if(request.getAttribute("errorConnexion")!=null && (Boolean)request.getAttribute("errorConnexion")){ %>
								<p class="col-md-12" style="color:red;text-align:center;font-size:14px"> Combinaison adresse électronique/mot de passe incorrecte </p>
							<% } %>
							
						</div>
						<div class="row ">
							<button type="button" onclick="window.location='./inscription'"
								class="btn btn-default col-md-offset-2 col-md-5">S'inscrire</button>
							<!--                   <button type="submit" class="btn btn-default col-md-5">Connexion</button> -->
							<input value="Se connecter" type="submit"
								class="btn btn-default col-md-5" />
						</div>
					</form>

					<div class="row">

						<a class="small-link col-md-offset-8 col-md-4"
							href="panelInscription/">Administration</a> <a
							class="col-md-offset-8  col-md-6 small-link" href="#">Mot de
							passé oublié ?</a>

					</div>
				</div>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
</body>
