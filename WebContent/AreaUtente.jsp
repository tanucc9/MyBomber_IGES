<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.utente.giocatore.*,model.utente.gestore.*, java.util.*"
	pageEncoding="ISO-8859-1"%>
<%
	GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
    if(giocatore==null && gestore==null)
    {
    	response.sendRedirect("./Login.jsp");
    }
%>
<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<link href="../style/personalpage.css" rel="stylesheet" type="text/css">
	<title>Area utente</title>
</head>
<body>
	<%@ include file="../fragments/header.jsp"%>
	<%
      if (request.getAttribute("cu") == "giocatore") {
      GiocatoreBean g=(GiocatoreBean)request.getSession().getAttribute("giocatore");
      %>
	<div class="page-content page-container container mt-5"
		id="page-content">
		<div class="padding">
			<div class="row container d-flex justify-content-center">
				<div class="col-xl-6 col-md-12">
					<div class="card user-card-full">
						<div class="row m-l-0 m-r-0">
							<div class="col-sm-4 bg-c-lite-green user-profile">
								<div class="card-block text-center text-white">
									<div class="m-b-25">
										<img src="https://img.icons8.com/bubbles/100/000000/user.png"
											class="img-radius" alt="User-Profile-Image">
									</div>
									<h6 class="f-w-600">Hembo Tingor</h6>
									<p>Web Designer</p>
									<i
										class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="card-block">
									<h6 class="m-b-20 p-b-5 b-b-default f-w-600">I miei dati</h6>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Email</p>
											<h6 class="text-muted f-w-400"><%=g.getEmail()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Username</p>
											<h6 class="text-muted f-w-400"><%=g.getUsername()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Nome</p>
											<h6 class="text-muted f-w-400"><%=g.getNome()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Cognome</p>
											<h6 class="text-muted f-w-400"><%=g.getCognome()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Telefono</p>
											<h6 class="text-muted f-w-400"><%=g.getTelefono()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Data di nascita</p>
											<h6 class="text-muted f-w-400"><%=g.getDataNascita()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Nazione</p>
											<h6 class="text-muted f-w-400"><%=g.getNazioneResidenza()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Provincia</p>
											<h6 class="text-muted f-w-400"><%=g.getProvinciaResidenza()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Citta</p>
											<h6 class="text-muted f-w-400"><%=g.getCittaResidenza()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Cap</p>
											<h6 class="text-muted f-w-400"><%=g.getCapResidenza()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Valutazione</p>
											<h6 class="text-muted f-w-400"><%=g.getValutazione()%></h6>
										</div>

									</div>
									<ul class="social-link list-unstyled m-t-40 m-b-10">
										<li><a href="#!" data-toggle="tooltip"
											data-placement="bottom" title=""
											data-original-title="facebook" data-abc="true"><i
												class="mdi mdi-facebook feather icon-facebook facebook"
												aria-hidden="true"></i></a></li>
										<li><a href="#!" data-toggle="tooltip"
											data-placement="bottom" title=""
											data-original-title="twitter" data-abc="true"><i
												class="mdi mdi-twitter feather icon-twitter twitter"
												aria-hidden="true"></i></a></li>
										<li><a href="#!" data-toggle="tooltip"
											data-placement="bottom" title=""
											data-original-title="instagram" data-abc="true"><i
												class="mdi mdi-instagram feather icon-instagram instagram"
												aria-hidden="true"></i></a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%} else{   if (request.getAttribute("cu") == "gestore") {
	        GestoreBean ge=(GestoreBean)request.getSession().getAttribute("gestore");%>

	<div class="page-content page-container container mt-5"
		id="page-content">
		<div class="padding">
			<div class="row container d-flex justify-content-center">
				<div class="col-xl-6 col-md-12">
					<div class="card user-card-full">
						<div class="row m-l-0 m-r-0">
							<div class="col-sm-4 bg-c-lite-green user-profile">
								<div class="card-block text-center text-white">
									<div class="m-b-25">
										<img src="https://img.icons8.com/bubbles/100/000000/user.png"
											class="img-radius" alt="User-Profile-Image">
									</div>
									<h6 class="f-w-600">Hembo Tingor</h6>
									<p>Web Designer</p>
									<i
										class=" mdi mdi-square-edit-outline feather icon-edit m-t-10 f-16"></i>
								</div>
							</div>
							<div class="col-sm-8">
								<div class="card-block">
									<h6 class="m-b-20 p-b-5 b-b-default f-w-600">I miei dati</h6>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Email</p>
											<h6 class="text-muted f-w-400"><%=ge.getEmail()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Nome</p>
											<h6 class="text-muted f-w-400"><%=ge.getNome()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Cognome</p>
											<h6 class="text-muted f-w-400"><%=ge.getCognome()%></h6>
										</div>
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Telefono</p>
											<h6 class="text-muted f-w-400"><%=ge.getTelefono()%></h6>
										</div>
									</div>
									<div class="row">
										<div class="col-sm-6">
											<p class="m-b-10 f-w-600">Struttura</p>
											<h6 class="text-muted f-w-400"><%=ge.getStruttura()%></h6>
										</div>

									</div>
									<ul class="social-link list-unstyled m-t-40 m-b-10">
										<li><a href="#!" data-toggle="tooltip"
											data-placement="bottom" title=""
											data-original-title="facebook" data-abc="true"><i
												class="mdi mdi-facebook feather icon-facebook facebook"
												aria-hidden="true"></i></a></li>
										<li><a href="#!" data-toggle="tooltip"
											data-placement="bottom" title=""
											data-original-title="twitter" data-abc="true"><i
												class="mdi mdi-twitter feather icon-twitter twitter"
												aria-hidden="true"></i></a></li>
										<li><a href="#!" data-toggle="tooltip"
											data-placement="bottom" title=""
											data-original-title="instagram" data-abc="true"><i
												class="mdi mdi-instagram feather icon-instagram instagram"
												aria-hidden="true"></i></a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%} }%>
</body>
</html>