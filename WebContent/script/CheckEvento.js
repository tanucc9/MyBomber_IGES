/**
 * 
 */
function checkNome(inputtxt) {
	var nome = /^[A-Za-z0-9 \'_.-]*$/;
	if(inputtxt.match(nome))
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
	if(!checkNome(nome) || !(nome.length>1) || !(nome.length<30)) {
		valid = false;
		let errNome = document.getElementById("errNome");
		document.getElementById("errNome").innerHTML = "nome non valido" ;
		errNome.style.color = "red";
	} else {
		document.getElementById("errNome").innerHTML = "" ;
	}
	
	if(valid)
		obj.submit();
			
}