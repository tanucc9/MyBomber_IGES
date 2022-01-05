

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
function checkLenght(data)
{
var lenght= 0;
for(var key in data) {
    if(data.hasOwnProperty(key)){
        length++;
    }
}
return lenght;
}
function checkIndirizzo(inputtxt) {
	var indirizzo =/^[A-Za-zà-ù0-9, ]*$/;
	if(inputtxt.match(indirizzo)) 
		return true

	return false;	
}
function checkCitta(inputtxt) {
	var citta =/^[A-zÀ-ù ‘-]*$/;
	if(inputtxt.match(citta)) 
		return true

	return false;	
}
function checkProvincia(inputtxt) {
	var provincia =/^[A-zÀ-ù ‘-]*$/;
	if(inputtxt.match(provincia)) 
		return true

	return false;	
}
function checkNazione(inputtxt) {
	var nazione = /^[A-zÀ-ù ‘-]*$/;
	if(inputtxt.match(nazione)) 
		return true

	return false;	
}
function checkStruttura(inputtxt) {
	var struttura = /^[A-Za-z0-9 \'_.-]*$/;
	if(inputtxt.match(struttura)) 
		return true

	return false;	
}
function checkNome(inputtxt) {
	var nome =/^[A-Za-z \']*$/;
	if(inputtxt.match(nome)) 
		return true

	return false;	
}
function checkCognome(inputtxt) {
	var cognome =/^[A-Za-z \']*$/;
	if(inputtxt.match(cognome)) 
		return true

	return false;	
}

function checkEmail(inputtxt) {
	var email =/^[a-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]*$/;
	
	if(inputtxt.match(email)) 
		return true;
	
	return false;	
}
function checkTelefono(inputtxt) {
	var telefono =/^[0-9]*$/;
	if(inputtxt.match(telefono)) 
		return true;
	
	return false;	
}

function checkCap(inputtxt) {
	var telefono =/^[A-Za-z0-9 -]*$/;
	if(inputtxt.match(telefono)) 
		return true;
	
	return false;	
}

function checkData(inputtxt) {
	var data =/^\d{4}\-(0?[1-9]|1[012])\-(0?[1-9]|[12][0-9]|3[01])$/; //yyyy-mm-dd
	if(inputtxt.match(data)) 
		return true;
	
	return false;	
}


function checkUsername(inputtxt) {
	var username = /^[A-Za-z0-9-_]*$/;
	if(inputtxt.match(username)) 
		return true;
	
	return false;	
}

function validateGiocatore(obj) {	
	var valid = true;	
	
	var email = document.getElementsByName("email")[0].value;
	let errEmail=document.getElementById("errEmail");
	if(!checkEmail(email)||!(email.length>1)||!(email.length<30)) {
		valid = false;
		
		errEmail.innerHTML = "email non valida";
		errEmail.style.color ="red";
		}
		else {
			errEmail.innerHTML = "";	
		}
	var username = document.getElementsByName("username")[0].value;
	let errUsername=document.getElementById("errUsername");
	console.log(username.length);
	if(!checkUsername(username)||!(username.length>1)||!(username.length<30)) {
		valid = false;
		
		errUsername.innerHTML = "username non valido";
		errUsername.style.color = "red";
	} else {
		errUsername.innerHTML = "" ;
	}	
			
	var nome = document.getElementsByName("nome")[0].value;
	let errNome=document.getElementById("errNome");
	if(!checkNome(nome)||!(nome.length>1)||!(nome.length<=30)) {
		valid = false;
		errNome.innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		errNome.innerHTML = "" ;
	}	

	
	var cognome = document.getElementsByName("cognome")[0].value;
	let errCognome=document.getElementById("errCognome");
	if(!checkCognome(cognome)||!(cognome.length>1)||!(cognome.length<=30)) {
		valid = false;
		
		document.getElementById("errCognome").innerHTML = "cognome non valido";
		errCognome.style.color = "red";

		} else {
			errCognome.innerHTML = "";
	}
	
	var pw = document.getElementsByName("password")[0].value;				
	var cpw = document.getElementsByName("cpassword")[0].value;
	let errCpassword=document.getElementById("errCpassword");
	if((pw!=cpw)||!(pw.length>1)||!(pw.length<=30)) {
		valid = false;
		errCpassword.innerHTML = "le password non corrispondono";
		errCpassword.style.color = "red";
		}
		else {
			errCpassword.innerHTML = "";
		}	
	var nazione = document.getElementsByName("nazione")[0].value;
	let errNazione=document.getElementById("errNazione");
	if(!checkNazione(nazione)||!(nazione.length>1)||!(nazione.length<=30)) {
		valid = false;
		errNazione.innerHTML = "nazione non valida" ;
		errNazione.style.color = "red";
	} else {
		errNazione.innerHTML = "" ;
	}
	var provincia = document.getElementsByName("provincia")[0].value;
	let errProvincia=document.getElementById("errProvincia");
	if(!checkProvincia(provincia)||!(provincia.length>1)||!(provincia.length<=30)) {
		valid = false;
		
		document.getElementById("errProvincia").innerHTML = "provincia non valida" ;
		errProvincia.style.color = "red";
	} else {
		errProvincia.innerHTML = "" ;
	}
	var citta = document.getElementsByName("citta")[0].value;
	let errCitta=document.getElementById("errCitta");
	if(!checkCitta(citta)||!(citta.length>1)||!(citta.length<=30)) {
		valid = false;
		errCitta.innerHTML = "citta non valido" ;
		errCitta.style.color = "red";
	} else {
		errCitta.innerHTML = "" ;
	}
	var cap = document.getElementsByName("cap")[0].value;
	let errCap=document.getElementById("errCap");
	if(!checkCap(cap)||!(cap.length>1)||!(cap.length<=10)) {
		valid = false;
		errCap.innerHTML = "cap non valido" ;
		errCap.style.color = "red";
	} else {
		errCap.innerHTML = "" ;
	}	
	var telefono = document.getElementsByName("telefono")[0].value;
	let errTelefono=document.getElementById("errTelefono");
	if(!checkTelefono(telefono)||!(telefono.length>3)||!(telefono.length<=10)) {
		valid = false;
		errTelefono.innerHTML = "telefono non valido" ;
		errTelefono.style.color = "red";
	} else {
		errTelefono.innerHTML = "" ;
	}	
	
	var data = document.getElementsByName("data")[0].value;
	let errData=document.getElementById("errData");
	if(!checkData(data)) {
		valid = false;
		errData.innerHTML = "data non valida";
		errData.style.color = "red";
		} else {
			errData.innerHTML = "";
		}		
	
	
	if(valid)
		obj.submit();
}

function validateGestore(obj) {	
	var valid = true;
		
	var email = document.getElementsByName("emailG")[0].value;
	let errEmail=document.getElementById("errEmailG");
	if(!checkEmail(email)||!(email.length>1)||!(email.length<=30)) {
		valid = false;
		errEmail.innerHTML = "email non valida";
		errEmail.style.color = "red";
		}
		else {
			errEmail.innerHTML = "";	
		}
	var telefonoGestore = document.getElementsByName("telefonoGestore")[0].value;
	let errTelefonoGestore=document.getElementById("errTelefonoGestore");
	if(!checkTelefono(telefonoGestore)||!(telefonoGestore.length>3)||!(telefonoGestore.length<=10)) {
		valid = false;
		errTelefonoGestore.innerHTML = "telefono non valido";
		errTelefonoGestore.style.color = "red";
		}
		else {
			errTelefonoGestore.innerHTML = "";	
		}			
	var nome = document.getElementsByName("nomeG")[0].value;
	let errNome=document.getElementById("errNomeG");
	if(!checkNome(nome)||!(nome.length>1)||!(nome.length<=30)) {
		valid = false;
		errNome.innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		errNome.innerHTML = "" ;
	}
	
	var cognome = document.getElementsByName("cognomeG")[0].value;
	let errCognome=document.getElementById("errCognomeG");
	if(!checkCognome(cognome)||!(cognome.length>1)||!(cognome.length<=30)) {
		valid = false;
		errCognome.innerHTML = "cognome non valido";
		errCognome.style.color = "red";

		} else {
			errCognome.innerHTML = "";
	}
	var struttura = document.getElementsByName("strutturaG")[0].value;
	let errStruttura=document.getElementById("errStruttura");
	if(!checkStruttura(struttura)||!(struttura.length>1)||!(struttura.length<=30)) {
		valid = false;
		errStruttura.innerHTML = "struttura non valida";
		errStruttura.style.color = "red";

		} else {
			errStruttura.innerHTML = "";
	}
	var nazione = document.getElementsByName("nazioneG")[0].value;
	let errNazione=document.getElementById("errNazioneG");
	if(!checkNazione(nazione)||!(nazione.length>1)||!(nazione.length<=30)) {
		valid = false;
		errNazione.innerHTML = "nazione non valida";
		errNazione.style.color = "red";

		} else {
			errNazione.innerHTML = "";
	}
	var provincia = document.getElementsByName("provinciaG")[0].value;
		let errProvincia=document.getElementById("errProvinciaG");
	if(!checkProvincia(provincia)||!(provincia.length>1)||!(provincia.length<=30)) {
		valid = false;
		errProvincia.innerHTML = "provincia non valida";
		errProvincia.style.color = "red";

		} else {
			errProvincia.innerHTML = "";
	}
	var citta = document.getElementsByName("cittaG")[0].value;
	let errCitta=document.getElementById("errCittaG");
	if(!checkCitta(citta)||!(citta.length>1)||!(citta.length<=30)) {
		valid = false;
		errCitta.innerHTML = "città non valida";
		errCitta.style.color = "red";

		} else {
			errCitta.innerHTML = "";
	}
	var cap = document.getElementsByName("capG")[0].value;
	let errCap=document.getElementById("errCapG");
	if(!checkCap(cap)||!(cap.length>1)||!(cap.length<=10)) {
		valid = false;
		errCap.innerHTML = "cap non valido";
		errCap.style.color = "red";

		} else {
			errCap.innerHTML = "";
	}
	var indirizzo = document.getElementsByName("indirizzoG")[0].value;
	let errIndirizzo=document.getElementById("errIndirizzoG");
	if(!checkIndirizzo(indirizzo)||!(indirizzo.length>1)||!(indirizzo.length<=30)) {
		valid = false;
		errIndirizzo.innerHTML = "indirizzo non valido";
		errIndirizzo.style.color = "red";

		} else {
			errIndirizzo.innerHTML = "";
	}
	var telefonoStruttura = document.getElementsByName("telefonoStruttura")[0].value;
	let errTelefonoStruttura=document.getElementById("errTelefonoStruttura");
	if(!checkTelefono(telefonoStruttura)||!(telefonoStruttura.length>1)||!(telefonoStruttura.length<=10)) {
		valid = false;
		errTelefonoStruttura.innerHTML = "telefono non valido";
		errTelefonoStruttura.style.color = "red";

		} else {
			errTelefonoStruttura.innerHTML = "";
	}
	var password = document.getElementsByName("passwordG")[0].value;
	var cpassword = document.getElementsByName("cpasswordG")[0].value;
	let errCpassword=document.getElementById("errCpasswordG");
	if((password!=cpassword)||!(password.length>1)||!(password.length<=30)) {
		valid = false;
		errCpassword.innerHTML = "le password non corrispondono";
		errCpassword.style.color = "red";

		} else {
			errCpassword.innerHTML = "";
	}
		
	
	if(valid)
		obj.submit();
	
}


function myFunction(x) {
  		x.style.background = "yellow";
		}
		
