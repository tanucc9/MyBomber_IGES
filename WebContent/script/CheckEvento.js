/**
 * 
 */
function checkNome(inputtxt) {
	var nome = /^[A-Za-z0-9 \'_.-]*$/;
	if(inputtxt.match(nome))
		return true

	return false;	
}


function checkStruttura(inputtxt) {
	var struttura = /^[A-Za-z\'_.-]*$/;
	if(inputtxt.match(struttura))
		return true

	return false;	
}


/*function checkData(inputtxt) {
	var data =  /^\d{1,2}\d{1,2}\d{4} \d{1,2}:\d{1,2}$/;
	if(inputtxt.match(data)) 
		return true;
	
	return false;	
}*/


function validate(obj) {	
	var valid = true;	
	
	var nome = document.getElementsByName("nome")[0].value;
	if(!checkNome(nome)) {
		valid = false;
		let errNome = document.getElementById("errNome");
		document.getElementById("errNome").innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		document.getElementById("errNome").innerHTML = "" ;
	}
	
	var struttura = document.getElementsByName("struttura")[0].value;
	if(!checkStruttura(struttura)) {
		valid = false;
		let errStruttura = document.getElementsByName("struttura");
		document.getElementById("errStruttura").innerHTML = "nome struttura non valido" ;
		errStruttura.style.color = "red";
	} else {
		document.getElementById("errStruttura").innerHTML = "" ;
	}
	
	if(valid)
		obj.submit();
			
}