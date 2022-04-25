<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.utente.giocatore.*,model.utente.gestore.*, model.evento.*,java.util.*"
	pageEncoding="UTF-8"%>
<% 
   ArrayList<?> eventi = (ArrayList<?>) request.getAttribute("eventi");
   GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
   GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
   if(giocatore==null && gestore==null)
   {
     response.sendRedirect("./Login.jsp");
   }
   else if(gestore==null && giocatore!=null)
   {%>
non puoi accedere a questa pagina
<% return;}
%>
<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>MyBomber</title>
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>

	<div class="container mt-5 mb-5">
		<div class="row align-items-center">
			<div class="col">
				<h3 class="text-center">Cronologia eventi</h3>
			</div>
		</div>
		<div class="row">
			<%
		if (eventi != null && eventi.size() != 0) {
			Iterator<?> it = eventi.iterator();
			while (it.hasNext()) {
				EventoBean e = (EventoBean) it.next();
	%>
			<div class="col-lg-4 cusom_event_class mt-5">
				<div class="card" style="width: 18rem;">
					<div class="card-body">
						<h3 class="card-title"><%=e.getNome()%>
							<%if(e.getStato().equals("attivo")) { %>
							<span class="badge bg-warning text-dark"><%=e.getStato() %></span>
							<%} else { %>
							<span class="badge bg-success"><%=e.getStato() %></span>
							<%} %>
						</h3>
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
					</div>
				</div>
			</div>
			<%
			}
		} else {
	%>

			<h2>Non ci sono eventi al momento</h2>

			<%
		}
	%>
		</div>

	</div>


	<%@ include file="../fragments/footer.html"%>

</body>
</html>
