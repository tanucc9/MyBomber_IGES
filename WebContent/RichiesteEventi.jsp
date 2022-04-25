<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"
	import="java.util.*,model.evento.*,model.utente.giocatore.*,model.recensione.*,model.utente.gestore.*"%>

<% 
       ArrayList<?> eventi = (ArrayList<?>) request.getAttribute("richieste");
       GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
       GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
       if(giocatore==null && gestore==null) {
    	  response.sendRedirect("./Login.jsp");
       }
       else if(gestore == null && giocatore != null) {
 	%>
non puoi accedere a questa pagina
<% 
    	return;
    	}
      	else{
	         if(eventi == null) {
		     response.sendRedirect("./richieste?action=trovarichieste");	
		     }
           }
%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>Richieste eventi</title>
</head>
<body>
	<%@ include file="./fragments/header.jsp"%>

	<div class="container">
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
						<h3 class="card-title"><%=e.getNome()%></h3>
						<p class="card-text">
							descrizione:
							<%=e.getDescrizione()%></p>
						<p class="card-text">
							data:
							<%=e.getData()%></p>
						<p class="card-text">
							ora:
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
						<a href="richieste?action=addE&nome=<%=e.getNome()%>"
							class="btn btn-primary">Accetta</a> <a
							href="richieste?action=deleteE&nome=<%= e.getNome()%>"
							class="btn btn-outline-danger">Rifiuta</a>
					</div>
				</div>
			</div>
			<%
			}
		} else {
	%>

			<h2>Non ci sono nuove richieste al momento</h2>

			<%
		}
	%>
		</div>
	</div>

	<%@ include file="./fragments/footer.html"%>
</body>
</html>