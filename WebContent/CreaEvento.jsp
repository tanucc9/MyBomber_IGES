<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.time.*,model.utente.giocatore.*,model.utente.gestore.*,model.struttura.*"%>
<%@ page import="model.squadra.SquadraBean" %>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>Crea evento</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>
	<% 
	ArrayList<?> strutture = (ArrayList<?>) request.getAttribute("strutture");
	GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
	SquadraBean miaSquadra = (SquadraBean) request.getSession().getAttribute("squadra");
	boolean isCaptain = false;
	if (miaSquadra != null)
		isCaptain = miaSquadra.getCapitano().equals(giocatore.getEmail());
    if(giocatore == null && gestore == null)
    {
    	response.sendRedirect("./Login.jsp");
    }
	
%>
	<div class="container mt-5 mb-5">
		<% 
		if (strutture != null && strutture.size() != 0) {
	%>
		<form action="creaEvento" id="myform"
			onSubmit="event.preventDefault(); validate(this);">
			<div class="form mb-3">
				<label for="nome">Nome</label> <input type="text" name="nome"
					class="form-control" placeholder="Nome" required>
				<p id="errNome"></p>
			</div>

			<div class="form mb-3">
				<label for="descrizione">Descrizione</label>
				<textarea class="form-control" name="descrizione" rows="3"></textarea>
			</div>

			<div class="form mb-3">
				<label for="struttura">Struttura</label> <select name="struttura"
					id="struttura">

					<%
		Iterator<?> it = strutture.iterator();
		while (it.hasNext()) {
			StrutturaBean s = (StrutturaBean) it.next();
	%>
					<option value="<%=s.getNome() %>"><%=s.getNome() %></option>

					<%
		}
	%>
				</select>
				<p id="errStruttura"></p>
			</div>

			<div class="form mb-3">
				<label for="data">Data</label> <input type="date" name="data"
					class="form-control" min="<%=LocalDate.now().toString() %>"
					required>
			</div>

			<div class="form mb-3">
				<label for="ora">Ora</label> <input type="number" name="ora"
					class="form-control" min="00" max="23" required>
			</div>

			<% if (isCaptain) { %>
			<div class="form-check form-switch">
				<input class="form-check-input" type="checkbox" id="switch_tipologia" name="switch_tipologia" value="1">
				<label class="form-check-label" for="switch_tipologia">Evento di tipo squadra</label>
			</div>
			<% } %>

			<div class="form mb-3 mt-3">
				<input type="submit" class="btn btn-primary" value="Crea">
			</div>

		</form>
		<%
		} else {
	%>

		<h2>Non esistono strutture</h2>

		<%
		}
	%>
	</div>
	<%@ include file="./fragments/footer.html"%>

	<script src="script/CheckEvento.js"></script>

</body>
</html>