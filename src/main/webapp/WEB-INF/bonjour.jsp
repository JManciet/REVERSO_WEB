<%--
  Created by IntelliJ IDEA.
  User: CDA07
  Date: 10/04/2024
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<p>Bonjour</p>
<ul>
    <c:forEach var="adresse" items="${ adresses }">
        <li><c:out value="${ adresse.numeroRue }" /></li>
    </c:forEach>
</ul>
</body>
</html>
