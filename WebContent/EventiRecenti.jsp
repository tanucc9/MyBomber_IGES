<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.util.*,model.evento.*,model.utente.giocatore.*,model.recensione.*,model.utente.gestore.*"%>

<%  ArrayList<?> eventi = (ArrayList<?>) request.getAttribute("eventiRecenti");
	GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
	GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
	if(giocatore==null && gestore==null) {
	  	response.sendRedirect("./Login.jsp");
	}
	else if(giocatore==null && gestore!=null) {
%>
non puoi accedere a questa pagina
<% 
	return;
	}
	else{
      if(eventi == null) {
	     response.sendRedirect("./eventiRecenti");	
	     }
    }
%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>Partecipa evento</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>

	<div class="container">

		<h3 class="text-center">Eventi recenti</h3>
		<div class="row">
			<%
		if (eventi != null && eventi.size() != 0) {
			Iterator<?> it = eventi.iterator();
			while (it.hasNext()) {
				EventoBean e = (EventoBean) it.next();
	%>
			<div class="col-lg-4 cusom_event_class mt-5">
				<div class="card">
					<div class="card-body">
						<h3 class="card-title"><%=e.getNome() %>
							<%if(e.getStato().equals("attivo")) { %>
							<span class="badge badge-warning"><%=e.getStato() %></span>
							<%} else { %>
							<span class="badge badge-success"><%=e.getStato() %></span>
							<%} %>
						</h3>
						<p class="card-text">
							descrizione:
							<%=e.getDescrizione()%></p>
						<p class="card-text">
							data :
							<%=e.getData()%>
						<p class="card-text">
							ora :
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
						<%
    			if(e.isFinished()) {
    		%>
						<a href="recensione?&action=cercagiocatori&nome=<%=e.getNome()%>"
							class="btn btn-primary">Recensisci</a>
						<%
				}
    		%>
					</div>
				</div>
			</div>
			<%
			}
		} else {
	%>

			<h2>Non hai partecipato a nessuna partita nell'ultima settimana</h2>

			<%
		}
	%>
		</div>
	</div>

	<%@ include file="./fragments/footer.html"%>
</body>
</html>