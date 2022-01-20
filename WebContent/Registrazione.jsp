<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.bean.*" %>
	
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>MyBomber</title>
  
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	<link href="../mybomber/style/global.css" rel="stylesheet" type="text/css">
</head>

<body>

<%@ include file="../fragments/header.jsp" %>

<div class="container mt-5 mb-5">	


<div class="mb-3">
	<h3>Sono un:</h3>
    <button type="button" id="gop" name="gop" class="btn btn-primary">Giocatore</button>
    <button type="button" id="geop"name="geop" class="btn btn-primary">Gestore</button>
</div>


  <%
      if (request.getAttribute("emailRe") != null) {
      %>
      <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>attenzione, email già in uso!</strong>
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
      </button>
      </div>
      <%}%>

      <%
      if (request.getAttribute("userRe") != null) {
      %>
      <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>attenzione, username già in uso!</strong>
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
      </button>
      </div>
      <%}%>
      <%
      if (request.getAttribute("strutturaRe") != null) {
      %>
      <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>attenzione, nomeStruttura già in uso!</strong>
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
      </button>
      </div>
      <%}%>
      <%
      if (request.getAttribute("errorReg") != null) {
    	  String x = (String)request.getAttribute("errorReg");
      %>
      <div class="alert alert-warning alert-dismissible fade show" role="alert">
      <strong>attenzione! errore di registrazione <%=x%></strong>
      <button type="button" class="close" data-dismiss="alert" aria-label="Close">
      <span aria-hidden="true">&times;</span>
      </button>
      </div>
      <%}%>
      
 <!-- Registrazione Giocatore -->
 
  <form id="registration" action="RegistrazioneServlet" method="post" onSubmit="event.preventDefault(); validateGiocatore(this);">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email">E-mail</label>
      <input type="email" class="form-control" id="email" placeholder="es. rossi@mybomber.it" name="email" required>
      <p id="errEmail">
      </p>
    </div>
    <div class="form-group col-md-6">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" placeholder="es. Rossi10" name="username" required>
      <p id="errUsername">
      </p>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" placeholder="es. Mario" name="nome" required>
      <p id="errNome"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cognome">Cognome</label>
      <input type="text" class="form-control" id="cognome" placeholder="es. Rossi" name="cognome" required>
      <p id="errCognome"></p>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" placeholder="Inserisci password qui" name="password" required>
      <p id="errPassword"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cpassword">Conferma Password</label>
      <input type="password" class="form-control" id="cpassword" placeholder="Conferma password qui" name="cpassword" required>
      <p id="errCpassword"></p>
    </div>
  </div>
  
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="nazione">Nazione residenza</label>
    <input type="text" class="form-control" id="nazione" placeholder="es. Italia" name="nazione" required>
    <p id="errNazione"></p>
  </div>
  <div class="form-group col-md-6">
    <label for="provincia">Provincia residenza</label>
    <input type="text" class="form-control" id="provincia" placeholder="es. Napoli" name="provincia" required>
    <p id="errProvincia"></p>
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="citta">Città residenza</label>
    <input type="text" class="form-control" id="citta" placeholder="es. Napoli" name="citta" required>
    <p id="errCitta"></p>
  </div>
  <div class="form-group col-md-6">
      <label for="cap_residenza">Cap residenza</label>
      <input type="text" class="form-control" id="cap" placeholder="es. 80056" name="cap" required>
      <p id="errCap"></p>
  </div>
  </div>
  
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="telefono">Telefono</label>
      <input type="text" class="form-control" id="telefono" placeholder="es. 3332211566" name="telefono" required>
      <p id="errTelefono"></p>
  </div>
  </div>
  
   <div class="form-row">
    <div class="form-group col-md-6">
      <label for="data">Data di nascita</label>
      <input type="date" class="form-control" id="data" name="data" required>
      <p id="errData"></p>
  </div>  
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <p id="errReg">
      </p>
  </div>  
  </div>
  <button type="submit" class="btn btn-primary">Registrati</button>
   <input type="hidden" name="cf" id="cf" value="giocatore">
</form>
 
 <!-- Registrazione Gestore -->
 
<form id="registrationGestore" action="RegistrazioneServlet" method="post" style="display:none;" onSubmit="event.preventDefault(); validateGestore(this);">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="emailG">E-mail</label>
      <input type="email" class="form-control" id="emailG" placeholder="es. rossi@mybomber.it" name="emailG" required>
      <p id="errEmailG">
      </p>
    </div>
    <div class="form-group col-md-6">
      <label for="telefonoGestore">Telefono</label>
      <input type="text" class="form-control" id="telefonoGestore" placeholder="es. 2114578955" name="telefonoGestore" required>
      <p id="errTelefonoGestore"></p>
  </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="nomeG">Nome</label>
      <input type="text" class="form-control" id="nomeG" placeholder="es. Mario" name="nomeG" required>
      <p id="errNomeG"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cognomeG">Cognome</label>
      <input type="text" class="form-control" id="cognomeG" placeholder="es. Rossi" name="cognomeG" required>
      <p id="errCognomeG"></p>
    </div>
  </div>
 
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="strutturaG">Struttura</label>
      <input type="text" class="form-control" id="strutturaG" placeholder="es. Stadio Olimpico" name="strutturaG" required>
      <p id="errStruttura">
      </p>
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="nazione">Nazione struttura</label>
    <input type="text" class="form-control" id="nazioneG" placeholder="es. Italia" name="nazioneG" required>
    <p id="errNazioneG"></p>
  </div>
  <div class="form-group col-md-6">
    <label for="provincia">Provincia struttura</label>
    <input type="text" class="form-control" id="provinciaG" placeholder="es. Roma" name="provinciaG" required>
    <p id="errProvinciaG"></p>
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="citta">Città struttura</label>
    <input type="text" class="form-control" id="cittaG" placeholder="es. Roma" name="cittaG" required>
    <p id="errCittaG"></p>
  </div>
  <div class="form-group col-md-6">
      <label for="cap">Cap struttura</label>
     <input type="text" class="form-control" id="capG" placeholder="es. 00135" name="capG" required>
     <p id="errCapG"></p>
     </div>
  </div>
  
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="telefono">Indirizzo struttura</label>
      <input type="text" class="form-control" id="indirizzoG" placeholder="es. Viale dei Gladiatori 19" name="indirizzoG" required>
      <p id="errIndirizzoG"></p>
  </div>
   <div class="form-group col-md-6">
      <label for="telefonoStruttura">Telefono struttura</label>
      <input type="text" class="form-control" id="telefonoStruttura" placeholder="es. 0636851" name="telefonoStruttura" required>
      <p id="errTelefonoStruttura"></p>
  </div>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="passwordG">Password</label>
      <input type="password" class="form-control" id="passwordG" placeholder="Inserisci password qui" name="passwordG" required>
      <p id="errPasswordG"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cpassword">Conferma Password</label>
      <input type="password" class="form-control" id="cpasswordG" placeholder="Conferma password qui" name="cpasswordG" required>
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