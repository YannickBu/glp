<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="col-md-6 publication">
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<h1 class="text-center">Panel de création de groupe</h1>
				<hr/>
				<form:form class="form-horizontal" role="form" method="post" action=""
					commandName="groupeTmp">
					<div class="form-group">
						<label for="inputNomGroupe" class="control-label">Nom
							du groupe * :</label>
						<div class="">
							<form:input path="nomGroupe" type="text" class="form-control"
								id="inputNomGroupe" placeholder="Ex: Biologie"></form:input>
							<form:errors path="nomGroupe" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputDescription" class="control-label">Description * : </label>
						<div class="">
							<form:textarea path="description" type="text-aera" rows="4"
								class="form-control" id="inputDescription"
								placeholder="Ex: Groupe institutionnel pour les anciens étudiants de biologie."></form:textarea>
							<form:errors path="description" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputModerateur" class="control-label">Modérateur * : </label>
						<div class="">
							<form:select path="utilisateurResponsable" class="form-control">
							  <option disabled selected>Choissiez un modérateur</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</form:select>
							<form:errors path="utilisateurResponsable" />
						</div>
					</div>
					<div class="form-group">
						<label for="inputModerateur" class="control-label">Animateurs * : </label>
						<div class="">
							<form:select multiple="true" path="utilisateurResponsable" class="form-control">
							  <option disabled>Choissiez un ou des animateurs</option>
							  <option>2</option>
							  <option>3</option>
							  <option>4</option>
							  <option>5</option>
							</form:select>
							<form:errors path="utilisateurResponsable" />
						</div>
					</div>
					<button type="submit" class="btn btn-default ">Creer le groupe</button>
				</form:form>

			</div>
		</div>
	</div>
</div>
