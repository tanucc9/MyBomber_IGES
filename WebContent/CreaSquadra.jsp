<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1"
         import="java.time.*,model.utente.giocatore.*,model.utente.gestore.*,model.struttura.*"%>
<%@ page import="model.squadra.SquadraBean" %>

<%
    GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
    SquadraBean squadra = (SquadraBean) request.getSession().getAttribute("squadra");
    if(giocatore==null && gestore==null) {
        response.sendRedirect("./Login.jsp");
    } else if(giocatore==null || squadra != null) {
        response.sendRedirect("./");
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="../fragments/head.jsp"%>
    <title>Crea squadra</title>
</head>
<body>
<%@ include file="./fragments/header.jsp"%>

<div class="container">
    <form class="row g-3">
        <div class="col-md-6">
            <label for="inputNomeSquadra" class="form-label">Nome squadra*</label>
            <input type="text" class="form-control" id="inputNomeSquadra" placeholder="Digita nome..." required>
        </div>
        <div class="col-md-6">
            <label for="inputNomeAbbreviato" class="form-label">Nome abbreviato*</label>
            <input type="text" class="form-control" id="inputNomeAbbreviato" placeholder="Digita nome..." required>
        </div>
        <div class="col-md-6">
            <label for="inputCitta" class="form-label">Città*</label>
            <input type="text" class="form-control" id="inputCitta" placeholder="Digita città..." required>
        </div>
        <div class="col-md-6">
            <label for="inputLogo" class="form-label">Logo</label>
            <input type="file" class="form-control" id="inputLogo">
        </div>
        <div class="col-12">
            <label for="inputDesc" class="form-label">Descrizione</label>
            <textarea class="form-control" id="inputDesc" placeholder="Aggiungi una descrizione della squadra..." rows="3" required ></textarea>
        </div>

        <div class="col-12">
            <button type="submit" class="btn btn-primary">Crea squadra</button>
        </div>
    </form>
</div>

<%@ include file="./fragments/footer.html"%>

</body>
</html>