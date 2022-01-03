
function validate(obj) {	
	var valid = true;	
	
	var descrizione = document.getElementsByName("descrizione")[0].value;
	if(!(descrizione.length<=100)) {
		valid = false;
		let errDescrizione=document.getElementById("errDescrizione");
		errDescrizione.innerHTML = "descrizione non valida";
		errDescrizione.style.color = "red";
		}
		else {
			errDescrizione.innerHTML = "";	
		}
	
	
	if(valid)
		obj.submit();
}
