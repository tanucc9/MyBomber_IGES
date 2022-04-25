<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>MyBomber</title>
</head>
<body>

	<%@ include file="../fragments/header.jsp"%>
	<div class="row">

		<div id="carouselExampleControls"
			class="carousel slide col-sm mt-5 mb-5 ml-5 mr-5"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100" src="img/calcio.jpg" alt="First slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/ronaldo.jpg" alt="Second slide">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="img/lewa.jpg" alt="Third slide">
				</div>
			</div>
			<a class="carousel-control-prev" href="#carouselExampleControls"
				role="button" data-slide="prev"> <span
				class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="sr-only">Previous</span>
			</a> <a class="carousel-control-next" href="#carouselExampleControls"
				role="button" data-slide="next"> <span
				class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="sr-only">Next</span>
			</a>
		</div>

		<div
			class="container border mt-5 mb-5 ml-5 mr-5 col-sm align-items-center">
			<form id="login" action="LoginServlet" method="post"
				onSubmit="event.preventDefault(); validate(this);">
				<div class="form-group">
					<label for="email">Indirizzo email :</label> <input type="email"
						class="form-control" placeholder="Inserisci email" name="email"
						id="email">
					<p id="errEmail"></p>
				</div>
				<div class="form-group">
					<label for="pwd">Password:</label> <input type="password"
						class="form-control" placeholder="Inserisci password"
						name="password" id="pwd">
					<p id="errPassword"></p>
				</div>
				<div class="form-group form-check">
					<label class="form-check-label"> <input
						class="form-check-input" type="checkbox"> Ricordami
					</label>
				</div>
				<div class="form-row">
					<div class="form-group col-md-6">
						<p id="errLog">
							<%
      if (request.getAttribute("errorLog") != null) {
      %>
							email o password errate
							<%}%>
						</p>
					</div>
				</div>
				<button type="submit" class="btn btn-primary">Accedi</button>
			</form>

		</div>
	</div>
	<script src="script/login-validation.js"></script>
	<%@ include file="../fragments/footer.html"%>
</body>
</html>