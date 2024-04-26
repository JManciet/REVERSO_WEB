<%@ page import="com.example.reverso.utilitaires.Interessement" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%--
  Created by IntelliJ IDEA.
  User: CDA07
  Date: 23/04/2024
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@ include file="menu.jsp" %>
<c:set var="societe" value="${fn:toLowerCase(typeSociete)}" scope="page" />
<c:set var="action" value="${fn:toLowerCase(typeAction)}" scope="page" />
<p>Page de <c:out value="${ action }" /> d'un <c:out value="${ societe }" /></p>
<form method="post" action="#">

    <div class="form">
        <label for="rs">Raison sociale: </label>
        <input type="text" name="rs" id="rs" required />
    </div>
    <div class="form">
        <label for="tel">Telephone: </label>
        <input type="text" name="tel" id="tel" required />
    </div>
    <div class="form">
        <label for="email">Email: </label>
        <input type="email" name="email" id="email" required />
    </div>
    <fieldset>
        <legend>Adresse</legend>
        <label for="num">Numéro: </label>
        <input type="text" name="num" id="num" required />
        <label for="rue">Rue: </label>
        <input type="text" name="rue" id="rue" required />
        <label for="cp">Code postal: </label>
        <input type="text" name="cp" id="cp" required />
        <label for="ville">Ville: </label>
        <input type="text" name="ville" id="ville" required />
    </fieldset>
    <c:if test="${typeSociete=='CLIENT'}">
        <div class="form">
            <label for="ca">Chiffre d'affaire: </label>
            <input type="number" name="ca" id="ca" required />
        </div>
        <div class="form">
            <label for="nbrEmp">Nombre d'employés: </label>
            <input type="number" name="nbrEmp" id="nbrEmp" required />
        </div>
    </c:if>
    <c:if test="${typeSociete=='PROSPECT'}">
        <div class="form">
            <label for="date">Date prospection: </label>
            <input type="date" name="date" id="date" required />
        </div>
        <fieldset>
            <legend>Prospect interessé ?</legend>
            <div>
                <input type="radio" id="oui" name="interesse" value="<c:out value="<%=Interessement.OUI%>"/>" />
                <label for="oui">oui</label>
            </div>
            <div>
                <input type="radio" id="non" name="interesse" value="<c:out value="<%=Interessement.NON%>"/>" />
                <label for="non">non</label>
            </div>
        </fieldset>
    </c:if>
    <label for="commentaires">Commentaires: </label>
    <textarea id="commentaires" name="commentaires" rows="4" cols="50"></textarea>
    <button id="bouton" type="submit">
        <c:if test="${typeSociete=='CLIENT'}">

        </c:if>
        <c:if test="${typeSociete=='PROSPECT'}">

        </c:if>
    </button>
</form>
<script>

    const bouton = document.getElementById('bouton')


    if("<c:out value="${typeAction}"/>" === "CREATION"){
        bouton.innerText = "Valider la création du "+ ("<c:out value="${typeSociete}"/>" === "CLIENT")? "client" : "prospect"
    } else {
        zoneProspect.style.display = 'block';
    }
</script>
</body>
</html>
