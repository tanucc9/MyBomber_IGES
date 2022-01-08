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
 	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
</head>

<body style="background-color:rgb(203, 228, 231);">
<%@ include file="../fragments/header.jsp" %>
<div class="mt-5 mb-5 mr-5 ml-5">
<div id="carouselExampleControls" class="col-sm carousel slide" data-ride="carousel">
  <div class="carousel-inner">
    <div class="carousel-item active">
      <img class="d-block w-100" src="img/Sfondo.png" alt="First slide" style="height:500px; object-fit:cover;">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="img/Sfondo2.jpg" alt="Second slide" style="height:500px; object-fit:cover;">
    </div>
    <div class="carousel-item">
      <img class="d-block w-100" src="img/Sfondo3.jpg" alt="Third slide" style="height:500px; object-fit:cover;">
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
</div >
<div class="mt-5 mb-5 mr-5 ml-5">


     <h1>
           We think about the team, You think about winning!
      </h1>
    
</div>
</div>


<br><br><br>

<div class="mt-5 mb-5 mr-5 ml-5">

    <div class="row">
      <div class="col-lg-4">
        <img src="img/Logo_MyBomber.png" width="250" height="250">

        <h2>DICONO DI NOI</h2>
        <p>MyBomber nasce nel 2021 dopo un grave periodo di pandemia, per permettere alla UEFA e alle aziende di riprendersi e fortificarsi.</p>
 
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
        <img src="img/Pallone.png" width="250" height="250">

        <h2>QUANDO NASCE MyBomber?</h2>
        <p>MyBomber nasce nel 2021, a capo della progettazione i famosi  5: Mauro, Bonavita, D'Antuono, Palladino e Viglione.</p>
      </div><!-- /.col-lg-4 -->
      <div class="col-lg-4">
        <img src="img/novita.png" width="250" height="250">

        <h2>PERCHE SCEGLIERCI?</h2>
        <p>L’idea è quella di realizzare una piattaforma che sia in grado di connettere tutti gli appassionati di calcio, che permetta di sfidare gli utenti nelle vicinanze e di ovviamente prenotare una struttura in cui giocare.</p>
      </div><!-- /.col-lg-4 -->
    </div><!-- /.row -->
    </div>
    <%@ include file="../fragments/footer.html"%>
</body>
</html>