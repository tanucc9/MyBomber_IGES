<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.util.*,model.evento.*,model.utente.giocatore.*,model.recensione.*,model.utente.gestore.*"%>
<%@ page import="model.squadra.SquadraBean" %>

<%  
    ArrayList<?> eventi = (ArrayList<?>) request.getAttribute("eventi");
    GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
	SquadraBean miaSquadra = (SquadraBean) request.getSession().getAttribute("squadra");
	boolean isCaptain = false;
	if (miaSquadra != null)
		isCaptain = miaSquadra.getCapitano().equals(giocatore.getEmail());

	if(giocatore==null && gestore==null)
    {
	  response.sendRedirect("./Login.jsp");
    }
    else if(giocatore==null&&gestore!=null)
    {
%>
non puoi accedere a questa pagina
<% 
	return;
    }
	else{
    	if(eventi == null) {
    		response.sendRedirect("./partecipa");	
    	}
  	} 
%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>Partecipa ad un evento</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>

	<div class="container">
		<%if(eventi != null && eventi.size() != 0) { %>
		<h3 class="text-center">A quale evento vuoi partecipare?</h3>
		<%} %>
		<div class="row">
			<%
		if(eventi != null && eventi.size() != 0) {
			Iterator<?> it = eventi.iterator();
			while (it.hasNext()) {
				EventoBean e = (EventoBean) it.next();
				if (isCaptain || !e.getTipologia().equals("squadra")) {
	%>
			<div class="col-lg-4 cusom_event_class mt-5">
				<div class="card">
					<div class="card-body">
						<h3 class="card-title"><%=e.getNome()%>
							<span class="badge bg-warning text-dark"><%=e.getStato()%></span>
						</h3>
						<p class="card-text">
							descrizione:
							<%=e.getDescrizione()%></p>
						<p class="card-text">
							data:
							<%=e.getData()%>
							<%=e.getOra()%></p>
						<p class="card-text">
							struttura:
							<%=e.getStruttura()%></p>
						<p class="card-text">
							gestore:
							<%=e.getGestore()%></p>
						<p class="card-text">
							organizzatore:
							<%=e.getOrganizzatore()%></p>
						<p class="card-text">
							Partecipanti:
							<%=e.getNumPartecipanti()%></p>
						<p class="card-text">
							Valutazione:
							<%=e.getMedia()%></p>
						<p class="card-text">
							Tipologia:
							<%=e.getTipologia()%></p>

						<form action="partecipa" method="post">
							<input type="hidden" value="<%=e.getCode() %>" name="code"
								id="nome"> <input type="submit" class="btn btn-primary"
								value="Partecipa">
						</form>
					</div>
				</div>
			</div>
			<%
			}}
		} else {
	%>

			<h2>Non ci sono eventi disponibili al momento</h2>

			<%
		}
	%>

		</div>
	</div>

	<%@ include file="./fragments/footer.html"%>
</body>
</html>