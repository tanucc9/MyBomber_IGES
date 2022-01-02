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
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
</head>

<body>

<%@ include file="../fragments/header.jsp" %>
<div class="container mt-5 mb-5">	
<div class="mb-3">
	<h3>Sono un:</h3>
    <button type="button" id="gop" name="gop" class="btn btn-primary">Giocatore</button>
    <button type="button" id="geop"name="geop" class="btn btn-primary">Gestore</button>
</div>
<div id="formgop">
  <form id="registration">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="email">Email</label>
      <input type="email" class="form-control" id="email" placeholder="Email">
    </div>
    <div class="form-group col-md-6">
      <label for="username">Username</label>
      <input type="text" class="form-control" id="username" placeholder="Username">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="nome">Nome</label>
      <input type="text" class="form-control" id="nome" placeholder="Nome">
    </div>
    <div class="form-group col-md-6">
      <label for="cognome">Cognome</label>
      <input type="text" class="form-control" id="cognome" placeholder="Cognome">
    </div>
  </div>
  <div class="form-row">
    <div class="form-group col-md-6">
      <label for="password">Password</label>
      <input type="password" class="form-control" id="password" placeholder="Password">
    </div>
    <div class="form-group col-md-6">
      <label for="cpassword">Conferma Password</label>
      <input type="password" class="form-control" id="cpassword" placeholder="Conferma password">
    </div>
  </div>
  
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="nazione_residenza">Nazione residenza</label>
    <input type="text" class="form-control" id="nazione_residenza" placeholder="Italia">
  </div>
  <div class="form-group col-md-6">
    <label for="provincia_residenza">Provincia residenza</label>
    <input type="text" class="form-control" id="provincia_residenza" placeholder="Napoli">
  </div>
  </div>
  <div class="form-row">
  <div class="form-group col-md-6">
    <label for="citta_residenza">Città residenza</label>
    <input type="text" class="form-control" id="citta_residenza" placeholder="Napoli">
  </div>
  <div class="form-group col-md-6">
      <label for="cap_residenza">Cap reisdenza</label>
      <input type="text" class="form-control" id="cap_residenza" placeholder="80056">
  </div>
  </div>
  
  <div class="form-row">
   <div class="form-group col-md-6">
      <label for="telefono">Telefono</label>
      <input type="text" class="form-control" id="telefono" placeholder="telefono">
  </div>
  </div>
  
   <div class="form-row">
    <div class="form-group col-md-6">
      <label for="data_nascita">Data di nascita</label>
      <input type="date" class="form-control" id="data_nascita">
  </div>  
  </div>
  
  <div class="form-group">
    <div class="form-check">
      <input class="form-check-input" type="checkbox" id="gridCheck">
      <label class="form-check-label" for="gridCheck">
        ricordami
      </label>
    </div>
  </div>
  <button type="submit" class="btn btn-primary">Registrati</button>
</form> 
</div>
</div>

<%@ include file="../fragments/footer.html"%>
<script src="script/register-validation.js"></script>
</body>
</html>