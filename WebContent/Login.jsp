<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>MyBomber</title>
</head>
<body id="login_page">

	<%@ include file="../fragments/header.jsp"%>

	<div class="max_width_mybomber container_login">
		<div class="row">
			<div class="col-lg-6">
				<div id="carouselExampleIndicators" class="carousel slide" data-bs-ride="carousel">
					<div class="carousel-indicators">
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
						<button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
					</div>
					<div class="carousel-inner">
						<div class="carousel-item active">
							<img src="img/calcio.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/ronaldo.jpg" class="d-block w-100" alt="...">
						</div>
						<div class="carousel-item">
							<img src="img/lewa.jpg" class="d-block w-100" alt="...">
						</div>
					</div>
					<button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
						<span class="carousel-control-prev-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Previous</span>
					</button>
					<button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
						<span class="carousel-control-next-icon" aria-hidden="true"></span>
						<span class="visually-hidden">Next</span>
					</button>
				</div>
			</div>
			<div class="col-lg-6">
				<div
						class="container border align-items-center container_form_login">
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


		</div>
	</div>

	<script src="script/login-validation.js"></script>
	<%@ include file="../fragments/footer.html"%>
</body>
</html>