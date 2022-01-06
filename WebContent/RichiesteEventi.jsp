<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1" import="model.bean.*, java.util.*"%>
    
    <% 
    ArrayList<?> eventi = (ArrayList<?>) request.getAttribute("richieste");
	if(eventi == null) {
		response.sendRedirect("./richieste?page=RichiesteEventi.jsp");	
		return;
	}
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">

  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  
  <meta name="description" content="Mobile Application HTML5 Template">

  <meta name="copyright" content="MACode ID, https://www.macodeid.com/">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css" integrity="sha512-Fo3rlrZj/k7ujTnHg4CGR2D7kSs0v4LLanw2qksYuRlEzO+tcaEPQogQ0KaoGN26/zrn20ImR1DfuLWnOo7aBA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
  

  <title>Richieste eventi</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
   <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
 
</head>
<body>
<%@ include file="./fragments/header.jsp" %>

<div class="container" style="margin: 100px;">
	<div class="row">
	<%
		if (eventi != null && eventi.size() != 0) {
			Iterator<?> it = eventi.iterator();
			while (it.hasNext()) {
				EventoBean bean = (EventoBean) it.next();
	%>
		<div class="col-lg-4 cusom_event_class mt-5">
			<div class="card" style="width: 18rem;">
  				<div class="card-body">
    			<h3 class="card-title">e.getNome()</h3>
    			<p class="card-text">e.getDescrizione()</p>
    			<p class="card-text">e.getData() e.getTime()</p>
    			<a href="richieste?action=addE&nome=<%=bean.getNome()%>" class="btn btn-primary"><button>Accetta</button></a>
    			<a href="richieste?action=deleteE&nome=<%=bean.getNome()%>" class="btn btn-primary"><button>Rifiuta</button></a>
  				</div>
			</div>
		</div>
	<%
			}
		} else {
	%>
	
	<h2>Non ci sono nuove richieste al momento</h2>
		
	<%
		}
	%>
	</div>
</div>

<%@ include file="./fragments/footer.html" %>
</body>
</html>