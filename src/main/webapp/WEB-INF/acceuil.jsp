<%--
  Created by IntelliJ IDEA.
  User: CDA07
  Date: 10/04/2024
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Page d'accueil</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/ressources/css/acceuil.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
<%@ include file="menu.jsp" %>
<c:set var="foo" scope="request" value="..."/>
<h2>Que souhaitez vous gérez ?</h2>
<div id="boutons-choix-societe">
    <a href="#" id="clients" class="btn">CLIENTS</a>
    <a href="#" id="propects" class="btn">PROSPECTS</a>
</div>
<h2 id="test"></h2>
<div id="boutons-action" style="display: none;">
    <a href="formulaire" id="creation" class="btn btn-secondary">CREATION</a>
    <a href="#" data-toggle="modal" data-target="#exampleModalLong" id="modification" class="btn btn-secondary">MODIFICATION</a>
    <a href="#" data-toggle="modal" data-target="#exampleModalLong" id="suppression" class="btn btn-secondary">SUPPRESSION</a>
    <a href="affichage" id="affichage" class="btn btn-secondary">AFFICHAGE</a>
</div>

<!-- Modal -->
<div class="modal fade exampleModalLong" id="exampleModalLong" tabindex="-1" role="dialog" aria-labelledby="exampleModalLong" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle"></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div id="liste" class="modal-body">
                <ul id="listeClients" style="display: none;">
                    <c:forEach var="societe" items="${clients}">
                        <li><a data="${ societe.raisonSociale }" href="#"><c:out value="${ societe.raisonSociale }" /></a></li>
                    </c:forEach>
                </ul>
                <ul id="listeProspects" style="display: none;">
                    <c:forEach var="societe" items="${prospects}">
                        <li><a data="${ societe.raisonSociale }" href="#"><c:out value="${ societe.raisonSociale }" /></a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>
<script>
    const boutonChoixClients = document.getElementById('clients');
    const boutonChoixPropects = document.getElementById('propects');
    const boutonsSupplementaires = document.getElementById('boutons-action');

    const test = document.getElementById('test');

    const boutonModification = document.getElementById('modification');
    const boutonSuppression = document.getElementById('suppression');
    const liste = document.getElementById('liste');

    const titreModal = document.getElementById('exampleModalLongTitle');
    const listeChoixClients = document.getElementById('listeClients');
    const listeChoixProspects = document.getElementById('listeProspects');

    let choix;

    boutonChoixClients.addEventListener('click', function() {
        boutonsSupplementaires.style.display = 'block';

        boutonChoixClients.classList.add("btn-primary")
        boutonChoixPropects.classList.remove("btn-primary")
        test.innerText = "Gestion des clients";
        listeChoixClients.style.display = 'block';
        listeChoixProspects.style.display = 'none';

        choix = 'client'

        updateChoixLinks();
    });

    boutonChoixPropects.addEventListener('click', function() {
        boutonsSupplementaires.style.display = 'block';

        boutonChoixPropects.classList.add("btn-primary")
        boutonChoixClients.classList.remove("btn-primary")
        test.innerText = "Gestion des prospects";
        listeChoixClients.style.display = 'none';
        listeChoixProspects.style.display = 'block';

        choix = 'prospect'

        updateChoixLinks();
    });


    boutonModification.addEventListener('click', function() {


        titreModal.innerText = "Veuillez choisir le " + choix + " à modifier :"

        updateActionLinks('modification');
    });

    boutonSuppression.addEventListener('click', function() {


        titreModal.innerText = "Veuillez choisir le " + choix + " à supprimer :"

        updateActionLinks('suppression');
    });

    function updateChoixLinks() {

        const actionLinks = boutonsSupplementaires.querySelectorAll('a');

        for (const link of actionLinks) {
            let url = new URL(window.location.origin);
            if(link.getAttribute('id') === 'creation') {
                url = new URL(window.location.origin + '/reverso/formulaire');
            } else if (link.getAttribute('id') === 'affichage') {
                url = new URL(window.location.origin + '/reverso/affichage');
            }
            url.searchParams.append('choix', choix);
            link.setAttribute('href', url.href);
        }
    }

    function updateActionLinks(boutonAction) {

        const actionLinks = liste.querySelectorAll('a');

        for (const link of actionLinks) {



            let url = new URL(window.location.origin + "/reverso/formulaire");
            url.searchParams.append('action', boutonAction);
            url.searchParams.append('choix', choix);
            url.searchParams.append('societe', link.getAttribute('data'));
            link.setAttribute('href', url.href);

        }
    }
</script>
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>
