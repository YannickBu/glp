<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Succès</h4>
			</div>
			<div class="modal-body">
				<p>Le groupe a été correctement créé.</p>
			</div>
			<div class="modal-footer">
				<button type="button" id="Close" class="btn btn-default"
					data-dismiss="modal">Fermer</button>
			</div>
		</div>
	</div>
</div>



<div class="modal fade" id="myModal2" tabindex="-1" role="dialog">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal"
					aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<h4 class="modal-title">Echec</h4>
			</div>
			<div class="modal-body">
				<p>Le groupe n'a pas été créé suite à un erreur.</p>
			</div>
			<div class="modal-footer">
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
			<div class="col-md-12"></div>
		</div>
	</div>
</div>
<script>
var response = document.getElementById("hiddenResponse").innerHTML;
if(response=="SUCCESS"){
	$('#myModal1').modal('toggle')
}
if(response=="FAIL"){
	$('#myModal2').modal('toggle')
}
</script>