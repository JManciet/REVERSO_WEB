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
<form method="post" action="${pageContext.request.contextPath}/formulaire">

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
    <div class="form">
        <label for="ca">Chiffre d'affaire: </label>
        <input type="number" name="ca" id="ca" required />
    </div>
    <div class="form">
        <label for="nbrEmp">Nombre d'employés: </label>
        <input type="number" name="nbrEmp" id="nbrEmp" required />
    </div>
    <label for="commentaires">Commentaires: </label>
    <input type="text" name="commentaires" id="commentaires" required />
    <button type="submit">clique</button>
</form>
</body>
</html>
