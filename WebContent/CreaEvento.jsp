<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="java.time.*,model.bean.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
	

  <title>Crea evento</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
</head>
<body>
	<%@ include file="./fragments/header.jsp" %>
	<% 
	ArrayList<?> strutture = (ArrayList<?>) request.getAttribute("strutture");
	GiocatoreBean giocatore=(GiocatoreBean)request.getSession().getAttribute("giocatore");
    GestoreBean gestore=(GestoreBean)request.getSession().getAttribute("gestore");
    if(giocatore == null && gestore == null)
    {
    	response.sendRedirect("./Login.jsp");
    }
	
%>
	<div class="container mt-5 mb-5">
	<% 
		if (strutture != null && strutture.size() != 0) {
	%>
	<form action="creaEvento" id="myform" onSubmit="event.preventDefault(); validate(this);">
		<div class="form mb-3">
		<label for="nome">Nome</label>
		<input type="text" name="nome" class="form-control" placeholder="Nome" required>
		<p id="errNome">
		</p>
		</div>
		
		<div class="form mb-3">
    	<label for="descrizione">Descrizione</label>
    	<textarea class="form-control" name="descrizione" rows="3"></textarea>
  		</div>
  		
  		<div class="form mb-3">
		<label for="struttura">Struttura</label>
		<select name="struttura" id="struttura">
	
	<%
		Iterator<?> it = strutture.iterator();
		while (it.hasNext()) {
			StrutturaBean s = (StrutturaBean) it.next();
	%>
		<option value="<%=s.getNome() %>"><%=s.getNome() %></option>
		
	<%
		}
	%>
		</select>
		<p id="errStruttura"></p>
		</div>
		
		<div class="form mb-3">
		<label for="data">Data</label>
		<input type="date" name="data" class="form-control" min="<%=LocalDate.now().toString() %>" required>	
		</div>
		
		<div class="form mb-3">
		<label for="ora">Ora</label>
		<input type="number" name="ora" class="form-control" min="00" max="23" required>	
		</div>
 		
 		<div class="form mb-3">
 		<input type="submit" class="btn btn-primary" value="Crea">
 		</div>
 		
	</form>
	<%
		} else {
	%>
	
	<h2>Non esistono strutture</h2>
		
	<%
		}
	%>
	</div>
	<%@ include file="./fragments/footer.html" %>
	
	<script src="script/CheckEvento.js"></script>
	
</body>
</html>