

function checkEmail(inputtxt) {
	var email =/^[a-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]*$/;
	if(inputtxt.value.match(email)) 
		return true;
	
	return false;	
}


function validate(obj) {	
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
	
	
	if(valid)
		obj.submit();
}



function myFunction(x) {
  		x.style.background = "yellow";
		}
		
