<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.utente.gestore.*,model.utente.giocatore.*,java.util.*"
	pageEncoding="UTF-8"%>
<% 
    GestoreBean ges=(GestoreBean)request.getSession().getAttribute("gestore");
    GiocatoreBean gio=(GiocatoreBean)request.getSession().getAttribute("giocatore");  
%>
<div class="container_header bg-primary sticky-top">
	<nav class="navbar navbar-expand-lg sticky-top navbar-dark bg-primary max_width_mybomber">
		<div class="container-fluid">
			<a class="navbar-brand" href="./Home.jsp">MyBomber</a>
			<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarText">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<%if((ges==null)&&(gio==null))
					{%>
					<li class="nav-item"><a class="nav-link"
											href="./Registrazione.jsp">Registrazione</a></li>
				<%} %>

				<%if((gio!=null))
				{%>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./areaUtenteServlet">Area Utente</a></li>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./struttura">Crea Evento</a></li>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./PartecipaEventi.jsp">Partecipa Eventi</a></li>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./EventiRecenti.jsp">Eventi Recenti</a></li>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./Squadre.jsp">Squadre</a></li>
				<%} if(ges!= null){%>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./areaUtenteServlet">Area Utente</a></li>

					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./RichiesteEventi.jsp">Richieste Eventi</a></li>
					<li class="nav-item navbar-nav"><a class="nav-link"
													   href="./cronologiaEventiServlet">Cronologia Eventi</a></li>
				<%}%>
				</ul>
				<%if((ges!=null)||(gio!=null))
				{%>
				<form action="./Logout" class="form-inline my-2 my-lg-0">
					<button class="btn btn-primary my-2 my-sm-0" type="submit">Logout</button>
				</form>
				<%} else{ %>
				<form action="./Login.jsp" class="form-inline my-2 my-lg-0">
					<button class="btn btn-primary my-2 my-sm-0" type="submit">Login</button>
				</form>
				<%} %>
			</div>
		</div>
	</nav>
</div>


