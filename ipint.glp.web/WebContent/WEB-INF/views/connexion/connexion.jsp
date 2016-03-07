
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="java.util.List"%>
<%@ page import="org.jasig.cas.client.authentication.AttributePrincipal"%>

<body class="body_without_menu">






	<%
		//     if (request.getUserPrincipal() != null) {
		//       AttributePrincipal principal = (AttributePrincipal) request.getUserPrincipal();

		//       /*
		//       final String password = principal.getPassword();
		//       if (password != null) {
		//         out.println("<p><b>User Credentials:</b> " + password + "</p>");
		//       }
		//       */

		//       final Map attributes = principal.getAttributes();

		//       if (attributes != null) {
		//         Iterator attributeNames = attributes.keySet().iterator();
		//         out.println("<b>Attributes:</b>");

		//         if (attributeNames.hasNext()) {
		//           out.println("<hr><table border='3pt' width='100%'>");
		//           out.println("<th colspan='2'>Attributes</th>");
		//           out.println("<tr><td><b>Key</b></td><td><b>Value</b></td></tr>");
		//           for (; attributeNames.hasNext();) {
		//             out.println("<tr><td>");
		//             String attributeName = (String) attributeNames.next();
		//             out.println(attributeName);
		//             out.println("</td><td>");
		//             final Object attributeValue = attributes.get(attributeName);
		//             if (attributeValue instanceof List) {
		//               final List values = (List) attributeValue;
		//               out.println("<strong>Multi-valued attribute: " + values.size() + "</strong>");
		//               out.println("<ul>");
		//               for (Object value: values) {
		//                 out.println("<li>" + value + "</li>");
		//               }
		//               out.println("</ul>");
		//             } else {
		//               out.println(attributeValue);
		//             }
		//             out.println("</td></tr>");
		//           }
		//           out.println("</table>");
		//         } else {
		//           out.print("No attributes are supplied by the CAS server.</p>");
		//         }
		//       } else {
		//         out.println("<pre>The attribute map is empty. Review your CAS filter configurations.</pre>");
		//       }
		//     } else {
		//         out.println("<pre>The user principal is empty from the request object. Review the wrapper filter configuration.</pre>");
		//     }
	%>


	<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
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
					<p>Votre demande d'inscription a bien été envoyée.</p>
				</div>
				<div class="modal-footer">
					<button type="button" id="Close" class="btn btn-default"
						data-dismiss="modal">Fermer</button>
				</div>
			</div>
		</div>
	</div>


	<div class="row" style="background-color: #f6921e">
		<div class="row">
			<div class="col-md-5"></div>
			<div class="col-md-2">
				<center>
					<img
						src="${pageContext.servletContext.contextPath}/resources/img/petit-logo.png"
						class="img-responsive" alt="Responsive image"
						style="width: 60%; margin-top: 30%">
				</center>
			</div>
			<div class="col-md-2"></div>
		</div>
		<div class="row" style="margin-top: 1%; margin-bottom: 1%">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<center>
					<h1 style="color: white; font-size: 40px; margin-right: 5%"
						class="name-logo">L1nk - Le réseau d'anciens de l'université
						de Lille</h1>
				</center>
			</div>
			<div class="col-md-4"></div>
		</div>

		<!-- 		<div class="row"> -->
		<!-- 			<div class="col-md-4"></div> -->
		<!-- 			<div class="col-md-4"> -->
		<!-- 				<center> -->
		<!-- 					<h3 style="color: white">Le réseau d'anciens de Lille</h3> -->
		<!-- 				</center> -->
		<!-- 			</div> -->
		<!-- 			<div class="col-md-4"></div> -->
		<!-- 		</div> -->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-6">

				<form class="form-horizontal" action="j_security_check"
					method="POST">
					<div class="form-group">
						<label for="inputEmail" class="col-md-6 control-label"
							style="color: white">Adresse email :</label>
						<div class="input-group col-md-5">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-envelope" aria-hidden="true"></span></span>
							<input type="email" class="form-control" id="inputEmail"
								placeholder="Email" name="j_username">
						</div>
						<div class="col-md-1"></div>
					</div>
					<div class="form-group">
						<label for="inputPassword" class="col-md-6 control-label"
							style="color: white">Mot de passe :</label>
						<div class="input-group col-md-5">
							<span class="input-group-addon"><span
								class="glyphicon glyphicon-lock" aria-hidden="true"></span></span> <input
								type="password" class="form-control" id="inputPassword"
								placeholder="Password" name="j_password">
						</div>
						<div class="col-md-1"></div>
					</div>
					<div class="row ">
						<div class="col-md-3"></div>

						<%
							if (request.getAttribute("errorConnexion") != null && (Boolean) request.getAttribute("errorConnexion")) {
						%>
						<p class="col-md-9"
							style="color: red; text-align: center; font-size: 14px">
							Combinaison adresse électronique/mot de passe incorrecte</p>
						<%
							}
						%>

					</div>
					<div class="row ">
						<div class="col-md-4"></div>
						<div class="col-md-4"
							style="margin-top: 1%; padding-left: 4%; color: white">
							<a href="${pageContext.servletContext.contextPath}/inscription"
								style="color: white; font-size: 13px; font-weight: bold; text-decoration: underline;">
								Pour vous inscrire sur le site, cliquez ici. </a>
						</div>
						<!-- 						<button type="button" onclick="window.location='./inscription'" -->
						<!-- 							class="btn btn-default col-md-offset-2 col-md-5">S'inscrire</button> -->
						<!-- 						<button type="submit" class="btn btn-default col-md-5">Connexion</button> -->
						<input value="Se connecter" type="submit"
							class="btn btn-default col-md-3" />
						<div class="col-md-1"></div>
					</div>
				</form>
				<div class="row">
					<a
						style="text-align: right; margin-top: 1%; margin-left: 1.5%; color: white; text-decoration: underline;"
						class="col-md-11 " href="#">Mot de passé oublié ?</a>
					<div class="col-md-1"></div>
				</div>
				<div class="row">
					<a class="col-md-11"
						style="text-align: right; margin-left: 1.5%; color: white; text-decoration: underline; margin-bottom: 5%"
						href="${pageContext.servletContext.contextPath}/cas">Connexion
						CAS (bouton temporaire)</a>
					<div class="col-md-1"></div>
				</div>

			</div>
			<div class="col-md-4"></div>
		</div>

	</div>

	<div class="row" style="background-color: white; padding-top: 4%">
		<div class="row" style="padding-bottom: 4%">
			<div class="col-md-3"></div>
			<div class="col-md-1">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/diplome.png"
					class="img-responsive" alt="Responsive image"
					style="width: 70%; margin-top: 9%; margin-left: 30%">
			</div>
			<div class="col-md-2" style="margin-top: 2%">
				<span>Diplomé de l'université de Lille ? Inscris toi-sur le
					site pour appartenir au réseau et partager ton expérience . </span>
			</div>
			<div class="col-md-1">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/etudiant.png"
					class="img-responsive" alt="Responsive image"
					style="width: 78%; margin-top: 10%; margin-left: 30%">

			</div>
			<div class="col-md-2" style="margin-top: 2%">
				<span>Etudiant ou personnel de l'université ? Connectes-toi
					avec tes identifiants de l'université. </span>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<hr>
			</div>
			<div class="col-md-4"></div>
		</div>
		<div class="row" style="margin-top: 3%; margin-bottom: 5%">
			<div class="col-md-1"></div>
			<div class="col-md-1">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/experience.png"
					class="img-responsive" alt="Responsive image"
					style="width: 70%; margin-top: 12%; margin-left: 30%">
			</div>
			<div class="col-md-2" style="margin-top: 2%">
				<span>Viens partager ton expérience auprès des étudiants et
					des autres diplomés.</span>
			</div>
			<div class="col-md-1">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/contact.png"
					class="img-responsive" alt="Responsive image"
					style="width: 80%; margin-top: -3%; margin-left: 30%">
			</div>
			<div class="col-md-2" style="margin-top: 2%">
				<span>Un réseau pour retrouver les anciens de ta formation.</span>
			</div>
			<div class="col-md-1">
				<img
					src="${pageContext.servletContext.contextPath}/resources/img/groupe.png"
					class="img-responsive" alt="Responsive image"
					style="width: 70%; margin-top: 12%; margin-left: 30%">
			</div>
			<div class="col-md-2" style="margin-top: 2%">
				<span>Abonne toi à tes groupes favoris ou crée tes propres
					groupes.</span>
			</div>
			<div class="col-md-2"></div>
		</div>


	</div>
	<span id="hiddenResponse" style="display: none">${createdInscription}</span>
	<span id="hiddenUrl" style="display : none">${pageContext.servletContext.contextPath}</span>
	<script>
		var response = document.getElementById("hiddenResponse").innerHTML;
		var url = document.getElementById("hiddenUrl").innerHTML;
		
		if (response == "SUCCESS") {
			url = url + "/connexion";
			$('#myModal').modal('toggle');
			window.history.replaceState('connexion','L1NK',url);
			
		}
	</script>
</body>
