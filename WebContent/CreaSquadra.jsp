<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"
         import="java.time.*,model.utente.giocatore.*,model.utente.gestore.*,model.struttura.*"%>
<%@ page import="model.squadra.SquadraBean" %>
<%@ page import="model.squadra.LogoSquadraBean" %>

<%
    GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
    SquadraBean squadra = (SquadraBean) request.getSession().getAttribute("squadra");
    ArrayList<LogoSquadraBean> loghi = (ArrayList<LogoSquadraBean>) request.getSession().getAttribute("loghiSquadra");

    if(giocatore==null && gestore==null) {
        response.sendRedirect("./Login.jsp");
    } else if(giocatore==null || squadra != null) {
        response.sendRedirect("./");
        return;
    }

    String messageError = (String) request.getAttribute("messageError");
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="../fragments/head.jsp"%>
    <title>Crea squadra</title>
    <link href="../mybomber/style/creasquadra.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="./fragments/header.jsp"%>

<div class="container">
    <% if (messageError != null) { %>
    <div class="alert alert-danger alert-dismissible fade show" role="alert">
        <strong>Ci sono degli errori:</strong> <%= messageError %>
    </div>
    <% } %>
    <form action="creaSquadra" method="post" class="row g-3" id="form_creasquadra">
        <div class="col-md-6" id="container_nomeSquadra">
            <label for="inputNomeSquadra" class="form-label">Nome squadra*</label>
            <input type="text" class="form-control" name="nomeSquadra" id="inputNomeSquadra" placeholder="Digita nome...">
            <div class="valid-feedback">
                Ok!
            </div>
            <div class="invalid-feedback"></div>
        </div>
        <div class="col-md-6" id="container_nomeSquadraAbbr">
            <label for="inputNomeAbbreviato" class="form-label">Nome abbreviato*</label>
            <input type="text" class="form-control" name="nomeAbbr" id="inputNomeAbbreviato" placeholder="Digita nome...">
            <div class="valid-feedback">
                Ok!
            </div>
            <div class="invalid-feedback"></div>
        </div>
        <div class="col-md-12" id="container_citta">
            <label for="inputCitta" class="form-label">Città*</label>
            <input type="text" class="form-control" name="citta" id="inputCitta" placeholder="Digita città...">
            <div class="valid-feedback">
                Ok!
            </div>
            <div class="invalid-feedback"></div>
        </div>
        <div class="col-12" id="container_descr">
            <label for="inputDesc" class="form-label">Descrizione</label>
            <textarea class="form-control" id="inputDesc" name="descr" placeholder="Aggiungi una descrizione della squadra..." rows="3"></textarea>
            <div class="valid-feedback">
                Ok!
            </div>
            <div class="invalid-feedback"></div>
        </div>

        <% if (loghi != null) { %>
        <div class="loghi_container">
            <div class="row">
            <% for (LogoSquadraBean logo : loghi) {
                if (!logo.getNome().equals("no_image")) {
            %>
            <div class="col-lg-4">
                <img src="<%= logo.getUrl() %>" alt="<%= logo.getNome() %>" data-id="<%= logo.getIdLogoSquadra() %>">
            </div>
            <% }} %>
            </div>
        </div>
        <% } %>

        <input type="hidden" name="id_logo_scelto" id="id_logo_scelto" value="0">

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Crea squadra</button>
        </div>
    </form>
</div>

<%@ include file="./fragments/footer.html"%>

<script src="script/scegli-logo.js"></script>
<script src="script/creasquadra-validation.js"></script>
</body>
</html>