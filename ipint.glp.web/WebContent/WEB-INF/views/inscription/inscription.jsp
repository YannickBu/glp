<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>L1NK | Inscription</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/style.css" rel="stylesheet">

</head>

<body>

	<div class="entete-inscrip">
		<div class="row">
			<center>
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/logoA.png"
					class="img-responsive" alt="Responsive image">
				<h1 class="name-logo">L1nk</h1>
				<h3>Inscription</h3>
			</center>
		</div>
	</div>
	<div class="form-inscrip">
		<form class="form-horizontal">
			<div class="form-group">
				<label for="inputNom" class="col-md-4 control-label">Nom :</label>
				<div class="input col-md-4">
					<input type="nom" class="form-control" id="inputNom" placeholder="">
				</div>
			</div>
			<div class="form-group">
				<label for="inputPrenom" class="col-md-4 control-label">Prenom
					:</label>
				<div class="input col-md-4">
					<input type="prenom" class="form-control" id="inputPrenom"
						placeholder="">
				</div>
			</div>
			<div class="form-group">
				<label for="inputEmail" class="col-md-4 control-label">Email
					:</label>
				<div class="input col-md-4">
					<input type="email" class="form-control" id="inputEmail"
						placeholder="">
				</div>
			</div>
			<div class="form-group">
				<label for="inputMDP" class="col-md-4 control-label">Mot de
					passe :</label>
				<div class="input col-md-4">
					<input type="mdp" class="form-control" id="inputMotDePasse"
						placeholder="">
				</div>
			</div>
			<div class="form-group">
				<label for="selectFormation" class="col-md-4 control-label">Formation
					:</label>
				<div class="input col-md-4">
					<select name="groupe" id="selectFormation" class="form-control">
						<option>1</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="selectAnnee" class="col-md-4 control-label">Année d'obtention du diplôme :</label>
				<div class="input col-md-4">
					<select name="annee" id="selectAnnee" class="form-control">
						<option>2000</option>
					</select>
				</div>
			</div>
			<div class="row ">
			<label for="" class="col-md-5 control-label"></label>
			<button type="submit" class="btn btn-default col-md-2" style="margin-top:1%;">S'inscrire</button>
			</div>
		</form>
	</div>


</body>
</html>