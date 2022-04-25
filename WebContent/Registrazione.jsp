<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="java.util.*,model.evento.*,model.utente.giocatore.*,model.recensione.*,model.utente.gestore.*"%>

<!DOCTYPE html>
<html lang="it">
<head>
	<%@ include file="../fragments/head.jsp"%>
	<title>MyBomber</title>
</head>

<body>

	<%@ include file="../fragments/header.jsp"%>

	<div class="container mt-5 mb-5">


		<div class="mb-3">
			<h3>Sono un:</h3>
			<button type="button" id="gop" name="gop" class="btn btn-primary">Giocatore</button>
			<button type="button" id="geop" name="geop" class="btn btn-outline-primary">Gestore</button>
		</div>


		<%
      if (request.getAttribute("emailRe") != null) {
      %>
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>attenzione, email già in uso!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%}%>

		<%
      if (request.getAttribute("userRe") != null) {
      %>
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>attenzione, username già in uso!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%}%>
		<%
      if (request.getAttribute("strutturaRe") != null) {
      %>
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>attenzione, nomeStruttura già in uso!</strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%}%>
		<%
      if (request.getAttribute("errorReg") != null) {
    	  String x = (String)request.getAttribute("errorReg");
      %>
		<div class="alert alert-warning alert-dismissible fade show"
			role="alert">
			<strong>attenzione! errore di registrazione <%=x%></strong>
			<button type="button" class="close" data-dismiss="alert"
				aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
		</div>
		<%}%>

		<!-- Registrazione Giocatore -->

		<form id="registration" action="RegistrazioneServlet" method="post"
			onSubmit="event.preventDefault(); validateGiocatore(this);">
			<div class="form-row row">
				<div class="form-group col-md-6">
					<label for="email">E-mail</label> <input type="email"
						class="form-control" id="email"
						placeholder="es. rossi@mybomber.it" name="email" required>
					<p id="errEmail"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="username">Username</label> <input type="text"
						class="form-control" id="username" placeholder="es. Rossi10"
						name="username" required>
					<p id="errUsername"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="nome">Nome</label> <input type="text"
						class="form-control" id="nome" placeholder="es. Mario" name="nome"
						required>
					<p id="errNome"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="cognome">Cognome</label> <input type="text"
						class="form-control" id="cognome" placeholder="es. Rossi"
						name="cognome" required>
					<p id="errCognome"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="password">Password</label> <input type="password"
						class="form-control" id="password"
						placeholder="Inserisci password qui" name="password" required>
					<p id="errPassword"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="cpassword">Conferma Password</label> <input
						type="password" class="form-control" id="cpassword"
						placeholder="Conferma password qui" name="cpassword" required>
					<p id="errCpassword"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="nazione">Nazione residenza</label> <input type="text"
						class="form-control" id="nazione" placeholder="es. Italia"
						name="nazione" required>
					<p id="errNazione"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="provincia">Provincia residenza</label> <input
						type="text" class="form-control" id="provincia"
						placeholder="es. Napoli" name="provincia" required>
					<p id="errProvincia"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="citta">Città residenza</label> <input type="text"
						class="form-control" id="citta" placeholder="es. Napoli"
						name="citta" required>
					<p id="errCitta"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="cap_residenza">Cap residenza</label> <input type="text"
						class="form-control" id="cap" placeholder="es. 80056" name="cap"
						required>
					<p id="errCap"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="telefono">Telefono</label> <input type="text"
						class="form-control" id="telefono" placeholder="es. 3332211566"
						name="telefono" required>
					<p id="errTelefono"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="data">Data di nascita</label> <input type="date"
						class="form-control" id="data" name="data" required>
					<p id="errData"></p>
				</div>
				<div class="form-group col-md-6">
					<p id="errReg"></p>
				</div>
			</div>
			<button type="submit" class="btn btn-primary">Registrati</button>
			<input type="hidden" name="cf" id="cf" value="giocatore">
		</form>

		<!-- Registrazione Gestore -->

		<form id="registrationGestore" action="RegistrazioneServlet"
			method="post" style="display: none;"
			onSubmit="event.preventDefault(); validateGestore(this);">
			<div class="form-row row">
				<div class="form-group col-md-6">
					<label for="emailG">E-mail</label> <input type="email"
						class="form-control" id="emailG"
						placeholder="es. rossi@mybomber.it" name="emailG" required>
					<p id="errEmailG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="telefonoGestore">Telefono</label> <input type="text"
						class="form-control" id="telefonoGestore"
						placeholder="es. 2114578955" name="telefonoGestore" required>
					<p id="errTelefonoGestore"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="nomeG">Nome</label> <input type="text"
						class="form-control" id="nomeG" placeholder="es. Mario"
						name="nomeG" required>
					<p id="errNomeG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="cognomeG">Cognome</label> <input type="text"
						class="form-control" id="cognomeG" placeholder="es. Rossi"
						name="cognomeG" required>
					<p id="errCognomeG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="strutturaG">Struttura</label> <input type="text"
						class="form-control" id="strutturaG"
						placeholder="es. Stadio Olimpico" name="strutturaG" required>
					<p id="errStruttura"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="nazione">Nazione struttura</label> <input type="text"
						class="form-control" id="nazioneG" placeholder="es. Italia"
						name="nazioneG" required>
					<p id="errNazioneG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="provincia">Provincia struttura</label> <input
						type="text" class="form-control" id="provinciaG"
						placeholder="es. Roma" name="provinciaG" required>
					<p id="errProvinciaG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="citta">Città struttura</label> <input type="text"
						class="form-control" id="cittaG" placeholder="es. Roma"
						name="cittaG" required>
					<p id="errCittaG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="cap">Cap struttura</label> <input type="text"
						class="form-control" id="capG" placeholder="es. 00135" name="capG"
						required>
					<p id="errCapG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="telefono">Indirizzo struttura</label> <input
						type="text" class="form-control" id="indirizzoG"
						placeholder="es. Viale dei Gladiatori 19" name="indirizzoG"
						required>
					<p id="errIndirizzoG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="telefonoStruttura">Telefono struttura</label> <input
						type="text" class="form-control" id="telefonoStruttura"
						placeholder="es. 0636851" name="telefonoStruttura" required>
					<p id="errTelefonoStruttura"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="passwordG">Password</label> <input type="password"
						class="form-control" id="passwordG"
						placeholder="Inserisci password qui" name="passwordG" required>
					<p id="errPasswordG"></p>
				</div>
				<div class="form-group col-md-6">
					<label for="cpassword">Conferma Password</label> <input
						type="password" class="form-control" id="cpasswordG"
						placeholder="Conferma password qui" name="cpasswordG" required>
					<p id="errCpasswordG"></p>
				</div>
			</div>
			<input type="hidden" name="cf" id="cf" value="gestore">
			<button type="submit" class="btn btn-primary">Registrati</button>
		</form>
	</div>
	<%@ include file="../fragments/footer.html"%>
	<script src="script/register-validation.js"></script>
</body>
</html>