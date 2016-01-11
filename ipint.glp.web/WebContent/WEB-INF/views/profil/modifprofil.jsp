<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/script.js"></script>




<div class="col-md-6 publication">
        <div class="container">
                <div class="row">
                        <div class="col-md-2">
                                <img src="${pageContext.servletContext.contextPath}/resources/img/logoA.png" class="img-responsive" alt="Responsive image">
                        </div>
                        <div class="col-md-10">
                                <h1 class="nomEtu">${utilisateur.prenom} ${utilisateur.nom}</h1>
                                <h2>${utilisateur.statut}</h2>
                        </div>
                        <hr/>
                </div>
        </div>
        <div class="navProfil">
                <div class="container-fluid">
                        <div class="row">
                                <div class="col-md-12">
                                        <div class="alert alert-success alert-dismissable" hidden>         
                                                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">
                                                        ×
                                                </button>
                                                <h4>
                                                        Alert!
                                                </h4> <strong>Warning!</strong> Best check yo self, you're not looking too good. <a href="#" class="alert-link">alert link</a>
                                        </div>
                                        <form:form role="form" method="post" action="2" commandName="utilisateur">
                                                <div class="form-group">         
                                                        <label for="InputNom">
                                                                Nom
                                                        </label>
                                                        <form:input path="nom" type="text" class="form-control" id="InputNom" placeholder="ex: Dupont"/>
                                                </div>
                                                <div class="form-group">
                                                        <label for="InputPrenom">
                                                                Prénom
                                                        </label>
                                                        <form:input path="prenom" type="text" class="form-control" id="InputPrenom" placeholder="ex: Jean"/>
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputPassword">
                                                                Mot de passe
                                                        </label>
                                                        <form:input path="password" type="password" class="form-control" id="InputPassword"/>
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputTel">
                                                                Téléphone
                                                        </label>
                                                        <form:input path="profil.telephone" type="text" class="form-control" id="InputTel"/>
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputCursus">
                                                                Cursus
                                                        </label>
                                                        <form:textarea path="profil.diplomes" type="text-aera" rows="3" class="form-control" placeholder="ex: [annéeDébut/annéeFin - Diplome1],[annéeDébut/annéeFin - Diplome1],..." id="InputCursus"/>                                                      
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputExp">
                                                                Expériences Professionnelles
                                                        </label>
                                                        <div id="expForm">
	                                                        <div class="row">
		                                                        <div class="col-md-1"><form:input path="" type="text" class="form-control" id="InputExpDebut" placeholder="Début"/></div>
		                                                        <div class="col-md-1"><form:input path="" type="text" class="form-control" id="InputExpAnneFin" placeholder="Fin"/></div>
		                                                        <div class="col-md-3"><form:input path="" type="text" class="form-control" id="InputExpEnt" placeholder="Entreprise"/></div>
		                                                        <div class="col-md-3"><form:input path="" type="text" class="form-control" id="InputExpLieu"  placeholder="Ville"/></div>                                                    
		                                                        <div class="col-md-4"><form:input path="" type="text" class="form-control" id="InputExpPoste" placeholder="Poste"/> </div>
		                                                    </div>	                    
		                                                    <div class="row" style="margin-top:1%">
		                                                        <div class="col-md-12"><form:textarea path="" type="text-area" class="form-control" id="InputExpDesc" placeholder="Description de votre mission"/></div>   
	                                                       </div>
	                                                       </div>
                                                      <div id="nouvelle_exp"></div>
                                                       
                                                       <button type="button" style="margin-top:1%;" class="btn btn-default" id="btn_new_exp">
                                                        +
                                               	   	 </button>  
                                                       <hr>      
                                                                          
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputSkills">
                                                                Compétences
                                                        </label>
                                                        <form:textarea path="profil.competence" type="text-aera" rows="3" class="form-control" id="InputSkills" placeholder="ex: [Compétence1],[Compétence2],..."></form:textarea>
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputInterets">
                                                                Centres d'interets
                                                        </label>
                                                        <form:textarea path="profil.centreInteret" type="text-aera" rows="3" class="form-control" id="InputInterets" placeholder="ex: [Interet1],[Interet2],..."></form:textarea>
                                                </div>
                                                <button type="submit" class="btn btn-default">
                                                        Modifier
                                                </button>
                                        </form:form>
                                </div>
                        </div>
                </div>
        </div>
</div>

<script type="text/javascript">
var btn = document.getElementById("btn_new_exp");
var comp = document.getElementById("nouvelle_exp");
var create = document.createElement("<p>LOLLLLL</p>");
btn.addEventListener("click",function(){window.alert("lol");
comp.appendChild(create);});


</script>