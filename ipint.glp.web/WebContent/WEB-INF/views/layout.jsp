<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPEhtml>

<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>

<title><tiles:insertAttribute name="title" /></title>
<tiles:insertAttribute name="stylecss"/>
<tiles:insertAttribute name="scriptjs" />
</head>
	<tiles:insertAttribute name="header" ignore="true" />
<body>
	<tiles:insertAttribute name="menu-gauche" ignore="true" />
	<tiles:insertAttribute name="body" />
	<tiles:insertAttribute name="menu-droite" ignore="true" />
</body>
<tiles:insertAttribute name="footer" ignore="true" />
</html>