<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <title>Crea evento</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
</head>
<body>
	
	<%@ include file="./fragments/header.jsp" %>
	<div class="container mt-5 mb-5">
	<form action="Creazione evento" method="post" id="myform" onsubmit="event.preventDefault(); validate(this);">
		<div class="form mb-3">
		<label for="nome">Nome</label>
		<input type="text" name="nome" class="form-control" placeholder="Nome" required>
		<p id="errNome"></p>
		<!-- Controllare se il nome già esiste -->
		</div>
		
		<div class="form mb-3">
    	<label for="descrizione">Descrizione</label>
    	<textarea class="form-control" name="descrizione" rows="3"></textarea>
  		</div>
  		
  		<div class="form mb-3">
		<label for="struttura">Struttura</label>
		<input type="text" name="struttura" class="form-control" placeholder="NomeStruttura" required>
		<p id="errStruttura"></p>
		</div>
		
		<div class="form mb-3">
		<label for="data e ora">Data e ora</label>
		<input type="datetime-local" name="data e ora" class="form-control" required>
		<p id="errData"></p>	
		</div>
 		
 		<div class="form mb-3">
 		<input type="submit" value="Crea">
 		</div>
 		
	</form>
	</div>
	<%@ include file="./fragments/footer.html" %>
	
	<script src="script/CreaEvento.js"></script>
	
</body>
</html>