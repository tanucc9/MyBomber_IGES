<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>MyBomber</title>
  
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
</head>

<body>

<%@ include file="../fragments/header.jsp" %>

<div class="container mt-5 mb-5">	


<div class="mb-3">
	<h3>Sono un:</h3>
    <button type="button" id="gop" name="gop" class="btn btn-primary">Giocatore</button>
    <button type="button" id="geop"name="geop" class="btn btn-primary">Gestore</button>
</div>
  <form id="registration" onSubmit="event.preventDefault(); validateGiocatore(this);">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" placeholder="Email" name="email" required>
      <p id="errEmail"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" placeholder="Username" name="username" required>
      <p id="errUsername"></p>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" placeholder="Nome" name="nome" required>
      <p id="errNome"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cognome">Cognome</label>
      <input type="text" class="form-control" id="cognome" placeholder="Cognome" name="cognome" required>
      <p id="errCognome"></p>
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
      <p id="errPassword"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cpassword">Conferma Password</label>
      <input type="password" class="form-control" id="cpassword" placeholder="Conferma password" name="cpassword" required>
      <p id="errCpassword"></p>
    </div>
  </div>
  
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="nazione">Nazione residenza</label>
    <input type="text" class="form-control" id="nazione" placeholder="Italia" name="nazione" required>
    <p id="errNazione"></p>
  </div>
  <div class="form-group col-md-6">
    <label for="provincia">Provincia residenza</label>
    <input type="text" class="form-control" id="provincia" placeholder="Napoli" name="provincia" required>
    <p id="errProvincia"></p>
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="citta">Città residenza</label>
    <input type="text" class="form-control" id="citta" placeholder="Napoli" name="citta" required>
    <p id="errCitta"></p>
  </div>
  <div class="form-group col-md-6">
      <label for="cap_residenza">Cap reisdenza</label>
      <input type="text" class="form-control" id="cap" placeholder="80056" name="cap" required>
      <p id="errCap"></p>
  </div>
  </div>
  
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="telefono">Telefono</label>
      <input type="text" class="form-control" id="telefono" placeholder="telefono" name="telefono" required>
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
  
  <button type="submit" class="btn btn-primary">Registrati</button>
</form> 
<form id="registrationGestore" style="display:none;" onSubmit="event.preventDefault(); validateGestore(this);">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="emailGestore" placeholder="Email" name="email" required>
      <p id="errEmail"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="telefono">Telefono</label>
      <input type="text" class="form-control" id="telefonoGestore" placeholder="Telefono" name="telefonoGestore" required>
      <p id="errTelefonoGestore"></p>
  </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nomeGestore" placeholder="Nome" name="nome" required>
      <p id="errNome"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cognome">Cognome</label>
      <input type="text" class="form-control" id="cognomeGestore" placeholder="Cognome" name="cognome" required>
      <p id="errCognome"></p>
    </div>
  </div>
 
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="telefono">Struttura</label>
      <input type="text" class="form-control" id="struttura" placeholder="Nome struttura" name="struttura" required>
      <p id="errTelefono"></p>
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="nazione">Nazione struttura</label>
    <input type="text" class="form-control" id="nazione" placeholder="Italia" name="nazione" required>
    <p id="errNazione"></p>
  </div>
  <div class="form-group col-md-6">
    <label for="provincia">Provincia struttura</label>
    <input type="text" class="form-control" id="provincia" placeholder="Napoli" name="provincia" required>
    <p id="errProvincia"></p>
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="citta">Città struttura</label>
    <input type="text" class="form-control" id="citta" placeholder="Napoli" name="citta" required>
    <p id="errCitta"></p>
  </div>
  <div class="form-group col-md-6">
      <label for="cap">Cap struttura</label>
     <input type="text" class="form-control" id="cap" placeholder="80043" name="cap" required>
     <p id="errCap"></p>
     </div>
  </div>
  
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="telefono">Indirizzo struttura</label>
      <input type="text" class="form-control" id="indirizzo" placeholder="via dei pastai 22" name="indirizzo" required>
      <p id="errIndirizzo"></p>
  </div>
   <div class="form-group col-md-6">
      <label for="telefonoStruttura">Telefono struttura</label>
      <input type="text" class="form-control" id="telefonoStruttura" placeholder="telefono" name="telefonoStruttura" required>
      <p id="errTelefonoStruttura"></p>
  </div>
  </div>
  
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" placeholder="Password" name="password" required>
      <p id="errPassword"></p>
    </div>
    <div class="form-group col-md-6">
      <label for="cpassword">Conferma Password</label>
      <input type="password" class="form-control" id="cpassword" placeholder="Conferma password" name="cpasseord" required>
      <p id="errCpassword"></p>
    </div>
  <div class="form-row">
  <button type="submit" class="btn btn-primary">Registrati</button>
  </div>
  </div>
</form>
</div>
<%@ include file="../fragments/footer.html"%>
<script src="script/register-validation.js"></script>
</body>
</html>