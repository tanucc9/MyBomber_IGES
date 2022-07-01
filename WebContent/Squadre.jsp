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
    ArrayList<SquadraBean> squadre = (ArrayList<SquadraBean>) request.getAttribute("squadre");
    ArrayList<LogoSquadraBean> loghi = (ArrayList<LogoSquadraBean>) request.getAttribute("loghi");

    if(giocatore==null && gestore==null) {
        response.sendRedirect("./Login.jsp");
        return;
    }
    if (giocatore == null) {
        response.sendRedirect("./");
        return;
    }

    if (squadre == null && loghi == null) {
        response.sendRedirect("./mostraSquadre");
        return;
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="../fragments/head.jsp"%>
    <title>Squadre</title>
    <link href="../mybomber/style/squadre.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="./fragments/header.jsp"%>

<div class="container">
    <div class="row container_squadre">
        <% for (SquadraBean sq : squadre) { %>
            <div class="col-lg-4">
                <div class="card" style="width: 18rem;">
                    <%
                        for (LogoSquadraBean logo : loghi) {
                            if (logo.getIdLogoSquadra() == sq.getLogo()) {
                    %>
                        <img src="<%= logo.getUrl() %>" class="card-img-top" alt="<%= logo.getNome() %>">
                    <% }} %>
                    <div class="card-body">
                        <h5 class="card-title"><%= sq.getNome() %></h5>
                        <p class="card-text">Città: <%= sq.getCitta() %></p>
                        <a href="#" class="btn btn-primary stretched-link">Vedi di più</a>
                    </div>
                </div>
            </div>
        <% } %>
    </div>
</div>

<%@ include file="./fragments/footer.html"%>

</body>
</html>