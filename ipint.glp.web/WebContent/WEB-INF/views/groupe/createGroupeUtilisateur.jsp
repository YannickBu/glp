<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header alert-white">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Succès !</h4>
			</div>
			<div class="modal-body alert-white">
				<p>Le groupe a été correctement créé.</p>
			</div>
			<div class="modal-footer alert-white">
				<button type="button" id="Close" class="btn btn-default"
					data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header alert-white">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Echec !</h4>
			</div>
			<div class="modal-body alert-white">
				<p>Le groupe n'a pas été créé suite à une erreur.</p>
			</div>
			<div class="modal-footer alert-white">
				<button type="button" id="Close" class="btn btn-default"
					data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>

<span id="hiddenResponse" style="display: none">${createdGroupe}</span>
<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="nomEtu text-center">Panel de création de groupe</h1>
				<hr />
				<form:form class="form-horizontal" role="form" method="post"
					action="" commandName="groupeTmp">
					<div class="form-group">
						<label for="inputNomGroupe" class="control-label">Nom du
							groupe * :</label>
						<div class="">
							<form:input path="nomGroupe" type="text" class="form-control"
								id="inputNomGroupe" placeholder="Ex: Biologie"></form:input>
							<form:errors path="nomGroupe" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputDescription" class="control-label">Description
							* : </label>
						<div class="">
							<form:textarea path="description" type="text-aera" rows="4"
								class="form-control" id="inputDescription"
								placeholder="Ex: Groupe institutionnel pour les anciens étudiants de biologie."></form:textarea>
							<form:errors path="description" />
						</div>
					</div>


					<button type="submit" class="btn btn-default ">Créer le
						groupe</button>
				</form:form>

			</div>
		</div>
	</div>
</div>
<span id="hiddenUrl" style="display: none">${pageContext.servletContext.contextPath}</span>
<script>
	var response = document.getElementById("hiddenResponse").innerHTML;
	var url = document.getElementById("hiddenUrl").innerHTML;
	//url = url + "/administration";
	if (response == "SUCCESS") {
		$('#myModal1').modal('toggle');
		window.history.replaceState('administration', 'L1NK', url);
	}
	if (response == "FAIL") {
		$('#myModal2').modal('toggle');
		window.history.replaceState('administration', 'L1NK', url);
	}
</script>
