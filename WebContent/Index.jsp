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

<div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="img/sfondo.jpg" alt="First slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="img/museo.jpg" alt="Second slide">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="img/sfondo.jpg" alt="Third slide">
    </div>
  </div>
  <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    <span class="carousel-control-next-icon" aria-hidden="true"></span>
    <span class="sr-only">Next</span>
  </a>
</div>


<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>
<h1>Ciao</h1>

<%

if (request.getAttribute("c") != null) {
	String x = (String)request.getAttribute("c");
%>
<div class="alert alert-warning alert-dismissible fade show" role="alert">
  <strong>Holy guacamole!</strong> Il  numero passato alla servlet era <%= x %>
  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
    <span aria-hidden="true">&times;</span>
  </button>
</div>
<%} %>

<form action="EsempioServlet" method="get">
	<input type="number" name="nomeAttr" min="0" max="10" value="0">
	<input type="submit" class="btn btn-primary">
</form>


<%@ include file="../fragments/footer.html"%>

</body>
</html>