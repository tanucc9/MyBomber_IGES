<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*,model.bean.*" %>
	
	<% 
    ArrayList<?> daRecensire = (ArrayList<?>) request.getAttribute("giocatoriDaRecensire");
	ArrayList<?> recensiti = (ArrayList<?>) request.getAttribute("giocatoriRecensiti");
	if(daRecensire == null) {
		response.sendRedirect("./recensione");	
		return;
	}
%>
	
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
<%
			Iterator<?> it = daRecensire.iterator();
			while (it.hasNext()) {
				String giocatore = (String) it.next();
	%>
<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id=<%=giocatore %>>
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        <%=giocatore %><span class="badge badge-light">Da recensire</span>
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <form id="dairecensione" action="recensione?action=addR&nomeG="<%=giocatore %> onSubmit="event.preventDefault(); validate(this);">
  
      <div class="mb-3">
      <label for="valutazione" class="form-label">Valutazione</label>
      <input type="number" class="form-control" name="valutazione" id="valutazione" min="1" max="5" step=".5" aria-describedby="valutazione" required>
      <div id="valutazioneHelp" class="form-text">Dai una valutazione da 1 a 5</div>
      </div>  

      <div class="mb-3">
      <label for="descrizione" class="form-label">Descrizione</label>
      <textarea class="form-control" name="descrizione" id="descrizione"></textarea>
      <p id="errDescrizione"></p>
      </div>
      
  
 <button type="submit" class="btn btn-primary">Salva recensione</button>
</form>  
      </div>
    </div>
  </div>
	
	<%
		}
	%>
	
	<!-- pippo -->
	<%
			Iterator<?> it2 = recensiti.iterator();
			while (it2.hasNext()) {
				String giocatore = (String) it2.next();
	%>
<div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header" id=<%=giocatore %>>
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
        <%=giocatore %><span class="badge badge-light">Già recensito</span>
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" aria-labelledby="headingOne" data-bs-parent="#accordionExample">
      <div class="accordion-body">
      <form id="dairecensione" method="post" action="recensione?action=DeleteR&nomeG="<%=giocatore %> onSubmit="event.preventDefault(); validate(this);">
  
      <div class="mb-3">
      <label for="valutazione" class="form-label">Valutazione</label>
      <input type="number" class="form-control" name="valutazione" id="valutazione" min="1" max="5" step=".5" aria-describedby="valutazione" required>
      <div id="valutazioneHelp" class="form-text">Dai una valutazione da 1 a 5</div>
      </div>  

      <div class="mb-3">
      <label for="descrizione" class="form-label">Descrizione</label>
      <textarea class="form-control" name="descrizione" id="descrizione"></textarea>
      <p id="errDescrizione"></p>
      </div>
      
 <button type="submit" class="btn btn-primary">Elimina recensione</button>
</form>  
      </div>
    </div>
  </div>
	
	<%
		}
	%>
	
</div>
<%@ include file="../fragments/footer.html"%>
<script src="script/dairecensione-validation.js"></script>
</body>
</html>