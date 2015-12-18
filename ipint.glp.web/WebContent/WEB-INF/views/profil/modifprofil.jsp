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
                                <h1 class="nomEtu">${utilisateur.nom} ${utilisateur.prenom}</h1>
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
                                                        <label for="InputEmail">
                                                                Email address
                                                        </label>
                                                        <form:input path="email" type="email" class="form-control" id="InputEmail" placeholder="ex: Jean.dupont@mail.com"/>
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputPassword">
                                                                New Password
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
                                                        <label for="InputSkills">
                                                                Compétences
                                                        </label>
                                                        <form:textarea path="profil.competence" type="text-aera" rows="3" class="form-control" id="InputSkills" placeholder="ex: Java 3; SQL 1; (<Langage> <NIVEAU>;...)"></form:textarea>
                                                </div>
                                                <div class="form-group">         
                                                        <label for="InputInterets">
                                                                Centres d'interets
                                                        </label>
                                                        <form:textarea path="profil.centreInteret" type="text-aera" rows="3" class="form-control" id="InputInterets" placeholder="ex: Emeu, Aqua-poney, ..."></form:textarea>
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