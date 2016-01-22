<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
        


<div class="col-md-6 accueilBody">
        <div class="publication">
                <ul>
                        <li class="publierArt">Publier un article</li>
                        <li class="formPubli">
                                <form:form action="${pageContext.servletContext.contextPath}/publication"  method="post" commandName="article">
                                        <form:input class="inputForm" path="contenu" placeholder="Tapez votre publication..." /><br>
                                        <input class="inputBtn" type="submit" value="Publier"/>
                                </form:form>
                        </li>
                </ul>
        </div>
        <c:forEach items="${articles}" var="art">
        
                <div class="article">
                        <ul>
                        <li class="nomEtu">${utilisateur.prenom} ${utilisateur.nom} via ${art.groupe.nomGroupe} - <fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${art.datePublication.time}"/></li>
                        <li>${art.contenu}</li>
                        </ul>
                </div>
    </c:forEach>
        
</div>
