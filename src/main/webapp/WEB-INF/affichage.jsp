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
<c:choose>
    <c:when test="${type == 'CLIENT'}">
        <p>Liste de clients</p>
    </c:when>
    <c:when test="${type == 'PROSPECT'}">
        <p>Liste de prospect</p>
    </c:when>
</c:choose>
<table>
    <thead>
    <tr>
        <th>Raison sociale</th>
        <th>eMail</th>
        <th>Téléphone</th>
        <th>Numéro rue</th>
        <th>Nom rue</th>
        <th>Code Postal</th>
        <th>Ville</th>
        <c:choose>
            <c:when test="${type == 'CLIENT'}">
                <th>Chiffre d'affaire</th>
                <th>Nombre d'employés</th>
            </c:when>
            <c:when test="${type == 'PROSPECT'}">
                <th>Date prospection</th>
                <th>Interesse ?</th>
            </c:when>

        </c:choose>
    </tr>
    </thead>
    <tbody>

        <c:forEach var="societe" items="${societes}">
        <tr>
            <td><c:out value="${ societe.raisonSociale }" /></td>
            <td><c:out value="${ societe.EMail }" /></td>
            <td><c:out value="${ societe.telephone }" /></td>
            <td><c:out value="${ societe.adresse.numeroRue }" /></td>
            <td><c:out value="${ societe.adresse.nomRue }" /></td>
            <td><c:out value="${ societe.adresse.codePostal }" /></td>
            <td><c:out value="${ societe.adresse.ville }" /></td>
            <c:choose>
                <c:when test="${type == 'CLIENT'}">
                    <td><c:out value="${ societe.chiffreAffaires }" /></td>
                    <td><c:out value="${ societe.nbrEmployes }" /></td>
                </c:when>
                <c:when test="${type == 'PROSPECT'}">
                    <td><c:out value="${ societe.dateProspection }" /></td>
                    <td><c:out value="${ societe.interesse }" /></td>
                </c:when>
            </c:choose>
        </tr>
        </c:forEach>

    </tbody>
</table>
</body>
</html>
