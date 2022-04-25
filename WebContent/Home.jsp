<%@ page language="java" contentType="text/html; charset=UTF-8"
	import="model.utente.gestore.*,model.utente.giocatore.*,java.util.*"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>MyBomber</title>
</head>
<body id="home">
	<%@ include file="../fragments/header.jsp"%>

	<div id="carousel_home_mybomber" class="carousel slide max_width_mybomber" data-bs-ride="carousel">
		<div class="carousel-indicators">
			<button type="button" data-bs-target="#carousel_home_mybomber" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
			<button type="button" data-bs-target="#carousel_home_mybomber" data-bs-slide-to="1" aria-label="Slide 2"></button>
			<button type="button" data-bs-target="#carousel_home_mybomber" data-bs-slide-to="2" aria-label="Slide 3"></button>
		</div>
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="img/Sfondo3.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Benvenuto su MyBomber!</h5>
					<p>Entra nel nuovo mondo dedicato agli appassionati del calcio</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="img/Sfondo2.jpg" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Benvenuto su MyBomber!</h5>
					<p>Entra nel nuovo mondo dedicato agli appassionati del calcio</p>
				</div>
			</div>
			<div class="carousel-item">
				<img src="img/Sfondo.png" class="d-block w-100" alt="...">
				<div class="carousel-caption d-none d-md-block">
					<h5>Benvenuto su MyBomber!</h5>
					<p>Entra nel nuovo mondo dedicato agli appassionati del calcio</p>
				</div>
			</div>
		</div>
		<button class="carousel-control-prev" type="button" data-bs-target="#carousel_home_mybomber" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button" data-bs-target="#carousel_home_mybomber" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span>
			<span class="visually-hidden">Next</span>
		</button>
	</div>

	<div class="max_width_mybomber value_proposition">
		<h1 class="title_values">We think about the team, You think about winning!</h1>

		<div class="row">
			<div class="col-lg-4" align="center">
				<img src="img/Logo_MyBomber.png" width="150" height="150">

				<h3>Dicono di noi</h3>
				<p>MyBomber nasce nel 2021 dopo un grave periodo di pandemia,
					per permettere alla UEFA e alle aziende di riprendersi e
					fortificarsi.</p>

			</div>
			<div class="col-lg-4" align="center">
				<img src="img/Pallone.png" width="150" height="150">

				<h3>Quando nasce MyBomber?</h3>
				<p>MyBomber nasce nel 2021, a capo della progettazione i famosi
					5: Mauro, Bonavita, D'Antuono, Palladino e Viglione.</p>
			</div>
			<div class="col-lg-4" align="center">
				<img src="img/novita.png" width="auto" height="150">

				<h3>Perché sceglierci?</h3>
				<p>L’idea è quella di realizzare una piattaforma che sia in
					grado di connettere tutti gli appassionati di calcio, che permetta
					di sfidare gli utenti nelle vicinanze e di ovviamente prenotare una
					struttura in cui giocare.</p>
			</div>
		</div>
	</div>
	<%@ include file="../fragments/footer.html"%>
</body>
</html>