

document.getElementById("gop").addEventListener("click", mostraGiocatore);
document.getElementById("geop").addEventListener("click", mostraGestore);
function mostraGiocatore() {
	document.getElementById("registration").style.display="block";
	document.getElementById("registrationGestore").style.display ="none";
	}
function mostraGestore() {
	document.getElementById("registration").style.display ="none";
	document.getElementById("registrationGestore").style.display ="block";
	}	
/**
 * 
 */

function checkIndirizzo(inputtxt) {
	var indirizzo =/^[A-Za-zà-ù0-9, ]*$/;
	if(inputtxt.value.match(indirizzo)) 
		return true

	return false;	
}
function checkCitta(inputtxt) {
	var citta =/^[A-zÀ-ù ‘-]*$/;
	if(inputtxt.value.match(citta)) 
		return true

	return false;	
}
function checkProvincia(inputtxt) {
	var provincia =/^[A-zÀ-ù ‘-]*$/;
	if(inputtxt.value.match(provincia)) 
		return true

	return false;	
}
function checkNazione(inputtxt) {
	var nazione = /^[A-zÀ-ù ‘-]*$/;
	if(inputtxt.value.match(nazione)) 
		return true

	return false;	
}
function checkStruttura(inputtxt) {
	var struttura = /^[A-Za-z0-9 \'_.-]*$/;
	if(inputtxt.value.match(struttura)) 
		return true

	return false;	
}
function checkNome(inputtxt) {
	var nome =/^[A-Za-z \']*$/;
	if(inputtxt.value.match(nome)) 
		return true

	return false;	
}
function checkCognome(inputtxt) {
	var cognome =/^[A-Za-z \']*$/;
	if(inputtxt.value.match(cognome)) 
		return true

	return false;	
}

function checkEmail(inputtxt) {
	var email =/^[a-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]*$/;
	if(inputtxt.value.match(email)) 
		return true;
	
	return false;	
}
function checkTelefono(inputtxt) {
	var telefono =/^[0-9]*$/;
	if(inputtxt.value.match(telefono)) 
		return true;
	
	return false;	
}

function checkCap(inputtxt) {
	var telefono =/^[A-Za-z0-9 -]*$/;
	if(inputtxt.value.match(telefono)) 
		return true;
	
	return false;	
}

function checkData(inputtxt) {
	var data =/^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/; //yyyy-mm-dd
	if(inputtxt.value.match(data)) 
		return true;
	
	return false;	
}


function checkUsername(inputtxt) {
	var username = /^[A-Za-z0-9-_]*$/;
	if(inputtxt.value.match(username)) 
		return true;
	
	return false;	
}

function validateGiocatore(obj) {	
	var valid = true;	
	
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		document.getElementById("errEmail").innerHTML = "email non valida";
		errEmail.style.color = "red";
		}
		else {
			document.getElementById("errEmail").innerHTML = "";	
		}
	var username = document.getElementsByName("username")[0];
	if(!checkUsername(username)) {
		valid = false;
		document.getElementById("errUsername").innerHTML = "username non valido" ;
		errUsername.style.color = "red";
	} else {
		document.getElementById("errUsername").innerHTML = "" ;
	}	
			
	var nome = document.getElementsByName("nome")[0];
	if(!checkNome(nome)) {
		valid = false;
		document.getElementById("errNome").innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		document.getElementById("errNome").innerHTML = "" ;
	}	
	
	
	
	
	var cognome = document.getElementsByName("cognome")[0];
	if(!checkCognome(cognome)) {
		valid = false;
		document.getElementById("errCognome").innerHTML = "cognome non valido";
		errCognome.style.color = "red";

		} else {
			document.getElementById("errCognome").innerHTML = "";
	}
	
	var pw = document.getElementsByName("password")[0];				
	var cpw = document.getElementsByName("cpassword")[0];
	if(pw.value!=cpw.value) {
		valid = false;
		document.getElementById("errCpassword").innerHTML = "le password non corrispondono";
		errCpassword.style.color = "red";
		}
		else {
			document.getElementById("errCpassword").innerHTML = "";
		}	
	var nazione = document.getElementsByName("nazione")[0];
	if(!checkNazione(nazione)) {
		valid = false;
		document.getElementById("errNazione").innerHTML = "nazione non valida" ;
		errNazione.style.color = "red";
	} else {
		document.getElementById("errNazione").innerHTML = "" ;
	}
	var provincia = document.getElementsByName("provincia")[0];
	if(!checkProvincia(provincia)) {
		valid = false;
		document.getElementById("errProvincia").innerHTML = "provincia non valida" ;
		errProvincia.style.color = "red";
	} else {
		document.getElementById("errProvincia").innerHTML = "" ;
	}
	var citta = document.getElementsByName("citta")[0];
	if(!checkCitta(citta)) {
		valid = false;
		document.getElementById("errCitta").innerHTML = "citta non valido" ;
		errCitta.style.color = "red";
	} else {
		document.getElementById("errCitta").innerHTML = "" ;
	}
	var cap = document.getElementsByName("cap")[0];
	if(!checkCap(cap)) {
		valid = false;
		document.getElementById("errCap").innerHTML = "cap non valido" ;
		errCap.style.color = "red";
	} else {
		document.getElementById("errCap").innerHTML = "" ;
	}	
	var telefono = document.getElementsByName("telefono")[0];
	if(!checkTelefono(telefono)) {
		valid = false;
		document.getElementById("errTelefono").innerHTML = "telefono non valido" ;
		errTelefono.style.color = "red";
	} else {
		document.getElementById("errTelefono").innerHTML = "" ;
	}	
	
	var data = document.getElementsByName("data")[0];
	if(!checkData(data)) {
		valid = false;
		document.getElementById("errData").innerHTML = "data non valida";
		errData.style.color = "red";
		} else {
			document.getElementById("errData").innerHTML = "";
		}		
	
	
	if(valid)
		obj.submit();
}

function validateGestore(obj) {	
	var valid = true;
		
	var email = document.getElementsByName("email")[0];
	if(!checkEmail(email)) {
		valid = false;
		document.getElementById("errEmail").innerHTML = "email non valida";
		errEmail.style.color = "red";
		}
		else {
			document.getElementById("errEmail").innerHTML = "";	
		}
	var telefonoGestore = document.getElementsByName("telefonoGestore")[0];
	if(!checkTelefono(telefonoGestore)) {
		valid = false;
		document.getElementById("errTelefonoGestore").innerHTML = "telefono non valido";
		errTelefonoGestore.style.color = "red";
		}
		else {
			document.getElementById("errTelefonoGestore").innerHTML = "";	
		}			
	var nome = document.getElementsByName("nome")[0];
	if(!checkNome(nome)) {
		valid = false;
		document.getElementById("errNome").innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		document.getElementById("errNome").innerHTML = "" ;
	}
	
	var cognome = document.getElementsByName("cognome")[0];
	if(!checkCognome(cognome)) {
		valid = false;
		document.getElementById("errCognome").innerHTML = "cognome non valido";
		errCognome.style.color = "red";

		} else {
			document.getElementById("errCognome").innerHTML = "";
	}
	var struttura = document.getElementsByName("struttura")[0];
	if(!checkStruttura(struttura)) {
		valid = false;
		document.getElementById("errStruttura").innerHTML = "struttura non valida";
		errStruttura.style.color = "red";

		} else {
			document.getElementById("errStruttura").innerHTML = "";
	}
	var nazione = document.getElementsByName("nazione")[0];
	if(!checkNazione(nazione)) {
		valid = false;
		document.getElementById("errNazione").innerHTML = "nazione non valida";
		errNazione.style.color = "red";

		} else {
			document.getElementById("errNazione").innerHTML = "";
	}
	var provincia = document.getElementsByName("provincia")[0];
	if(!checkProvincia(provincia)) {
		valid = false;
		document.getElementById("errProvincia").innerHTML = "provincia non valida";
		errProvincia.style.color = "red";

		} else {
			document.getElementById("errProvincia").innerHTML = "";
	}
	var citta = document.getElementsByName("citta")[0];
	if(!checkCitta(citta)) {
		valid = false;
		document.getElementById("errCitta").innerHTML = "città non valida";
		errCitta.style.color = "red";

		} else {
			document.getElementById("errCitta").innerHTML = "";
	}
	var cap = document.getElementsByName("cap")[0];
	if(!checkCap(cap)) {
		valid = false;
		document.getElementById("errCap").innerHTML = "cap non valido";
		errCap.style.color = "red";

		} else {
			document.getElementById("errCap").innerHTML = "";
	}
	var indirizzo = document.getElementsByName("indirizzo")[0];
	if(!checkIndirizzo(indirizzo)) {
		valid = false;
		document.getElementById("errIndirizzo").innerHTML = "indirizzo non valido";
		errIndirizzo.style.color = "red";

		} else {
			document.getElementById("errIndirizzo").innerHTML = "";
	}
	var telefonoStruttura = document.getElementsByName("telefonoStruttura")[0];
	if(!checkTelefono(telefonoStruttura)) {
		valid = false;
		document.getElementById("errTelefonoStruttura").innerHTML = "telefono non valido";
		errTelefonoStruttura.style.color = "red";

		} else {
			document.getElementById("errTelefonoStruttura").innerHTML = "";
	}
	var password = document.getElementsByName("password")[0];
	var cpassword = document.getElementsByName("cpassword")[0];
	if(password.value!=cpassword.value) {
		valid = false;
		document.getElementById("errCpassword").innerHTML = "le password non corrispondono";
		errCpassword.style.color = "red";

		} else {
			document.getElementById("errCpassword").innerHTML = "";
	}
		
	
	if(valid)
		obj.submit();
	
}


function myFunction(x) {
  		x.style.background = "yellow";
		}
		
