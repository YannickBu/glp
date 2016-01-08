<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


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
	<form:form class="form-horizontal" role="form" method="post" action=""
		commandName="utilisateurTmp">
		<div class="form-group">
			<label for="inputNom" class="col-md-4 control-label">Nom :</label>
			<div class="input col-md-4">
				<form:input path="nom" type="text" class="form-control"
					id="inputNom" placeholder=""></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="inputPrenom" class="col-md-4 control-label">Prenom
				:</label>
			<div class="input col-md-4">
				<form:input path="prenom" type="text" class="form-control"
					id="inputPrenom" placeholder=""></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="inputEmail" class="col-md-4 control-label">Email
				:</label>
			<div class="input col-md-4">
				<form:input path="email" type="email" class="form-control"
					id="inputEmail" placeholder=""></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="inputBirthday" class="col-md-4 control-label">Date
				de naissance :</label>
			<div class="input col-md-4">
				<!-- <input type="date" class="form-control" id="inputBirthday"
						placeholder=""> -->
				<div class="input-append date form_datetime">
					<form:input path="dateNaissance" class="form-control"
						id="inputBirthday"></form:input>
					<span class="add-on"><i class="icon-th"></i></span>
				</div>
				<script type="text/javascript">
					$("#inputBirthday").datetimepicker({
						format : "yyyy/mm/dd",
						startView : 'decade',
						minView : 'month',
						autoclose : true,
						endDate : new Date(),
						language : "fr"
					});
				</script>
			</div>
		</div>
		<div class="form-group">
			<label for="inputDiplome" class="col-md-4 control-label">Diplome
				obetnu :</label>
			<div class="input col-md-4">
				<form:input path="diplome" type="text" class="form-control"
					id="inputDiplome" placeholder=""></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="selectAnnee" class="col-md-4 control-label">Année
				d'obtention du diplôme :</label>
			<div class="input col-md-4">
				<form:input path="anneeDiplome" type="text" id="selectAnnee"
					class="form-control"></form:input>
			</div>
		</div>
		<div class="form-group">
			<label for="selectFormation" class="col-md-4 control-label">Groupe
				selectionné :</label>
			<div class="input col-md-4">
				<form:select path="groupePrincipal" name="groupe" id="selectFormation"
					class="form-control">
					<option>1</option>
				</form:select>
			</div>
		</div>
		<div class="row ">
			<label for="" class="col-md-5 control-label"></label>
			<button type="submit" class="btn btn-default col-md-2"
				style="margin-top: 1%;">S'inscrire</button>
		</div>
	</form:form>
</div>

