/**
 * 
 */
function checkNome(inputtxt) {
	var nome = /^[A-Za-z0-9 \'_.-]*$/;
	if(inputtxt.value.match(nome))
		return true

	return false;	
}


function checkStruttura(inputtxt) {
	var struttura = /^[A-Za-z\'_.-]*$/;
	if(inputtxt.value.match(struttura))
		return true

	return false;	
}


function checkData(inputtxt) {
	var data =  /^\d{1,2}-\d{1,2}-\d{4} \d{1,2}:\d{1,2}$/;
	if(inputtxt.value.match(data)) 
		return true;
	
	return false;	
}


function validate(obj) {	
	var valid = true;	
	
	var nome = document.getElementsByName("nome")[0];
	if(!checkNome(nome)) {
		valid = false;
		document.getElementById("errNome").innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		document.getElementById("errNome").innerHTML = "" ;
	}
	
	var struttura = document.getElementsByName("struttura")[0];
	if(!checkStruttura(struttura)) {
		valid = false;
		document.getElementById("errStruttura").innerHTML = "nome struttura non valido" ;
		errStruttura.style.color = "red";
	} else {
		document.getElementById("errStruttura").innerHTML = "" ;
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