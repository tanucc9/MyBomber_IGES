<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"
         import="java.time.*,model.utente.giocatore.*,model.utente.gestore.*,model.struttura.*"%>
<%@ page import="model.squadra.SquadraBean" %>
<%@ page import="model.squadra.LogoSquadraBean" %>
<%@ page import="java.lang.reflect.Array" %>

<%
    GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
    SquadraBean miaSquadra = (SquadraBean) request.getSession().getAttribute("squadra");
    SquadraBean squadra = (SquadraBean) request.getAttribute("squadra");
    LogoSquadraBean logo = (LogoSquadraBean) request.getAttribute("logo");
    ArrayList<GiocatoreBean> giocatori = (ArrayList<GiocatoreBean>) request.getAttribute("giocatori");
    GiocatoreBean capitano = (GiocatoreBean) request.getAttribute("capitano");
    boolean isCaptain = squadra.getCapitano().equals(giocatore.getEmail());

    if(giocatore==null && gestore==null) {
        response.sendRedirect("./Login.jsp");
        return;
    }
    if (giocatore == null) {
        response.sendRedirect("./");
        return;
    }

    if (squadra == null && logo == null) {
        response.sendRedirect("./");
        return;
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="../fragments/head.jsp"%>
    <title><%= squadra.getNome() %></title>
    <link href="../mybomber/style/squadra.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="./fragments/header.jsp"%>

<div class="container">
    <div class="row">
        <div class="col-lg-4">
            <div class="container_logo">
                <img src="<%= logo.getUrl() %>" alt="<%= logo.getNome() %>" >
            </div>
        </div>
        <div class="col-lg-8">
            <div class="row container_info">
                <div class="col-lg-12">
                    <h3><%= squadra.getNome() %></h3>
                </div>
                <div class="col-lg-6">
                    <p><b>Nome abbreviato:</b> <%= squadra.getNomeAbbreviato() %></p>
                </div>
                <div class="col-lg-6">
                    <p><b>Città:</b> <%= squadra.getCitta() %></p>
                </div>
                <div class="col-lg-12">
                    <p><b>Descrizione:</b> <%= squadra.getDescrizione() %></p>
                </div>
                <div class="col-lg-6">
                    <p><b>Capitano:</b> <%= capitano.getUsername() %></p>
                </div>
                <div class="col-lg-12">
                    <p><b>Componenti:</b>
                    <%
                        Iterator<GiocatoreBean> iterGio = giocatori.iterator();
                        while (iterGio.hasNext()) {
                            GiocatoreBean g = iterGio.next();
                            if (iterGio.hasNext())
                                g.setUsername(g.getUsername() + ", ");
                            else
                                g.setUsername(g.getUsername() + ".");
                    %>
                        <%= g.getUsername()%>
                        <% } %>
                    </p>
                </div>
                <div class="col-lg-12 container_buttons_actions">
                    <div class="d-grid gap-2">
                        <% if (isCaptain) { %>
                        <a href="#" class="btn btn-outline-danger" id="eliminaSquadraBTN">Elimina la squadra</a>
                        <% } %>
                        <% if (miaSquadra == null) { %>
                        <a href="#" class="btn btn-primary" id="uniscitiSquadraBTN" data-id-squadra="<%= squadra.getIdSquadra() %>">Unisciti alla squadra</a>
                        <% } %>
                        <% if (miaSquadra != null && !isCaptain) { %>
                        <a href="#" class="btn btn-outline-danger" id="abbandonaSquadraBTN" data-id-squadra="<%= squadra.getIdSquadra() %>">Abbandona squadra</a>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="./fragments/footer.html"%>

<script src="script/actions-buttons-squadra.js"></script>

</body>
</html>