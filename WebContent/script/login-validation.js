

function checkEmail(inputtxt) {
	var email =/^[a-z0-9._%+-]+@[A-z0-9.-]+\.[A-z]*$/;
	
	if(inputtxt.match(email)) 
		return true;
	
	return false;	
}


function validate(obj) {	
	var valid = true;	
	
	var email = document.getElementsByName("email")[0].value;
	if(!checkEmail(email)||!(email.length>1)||!(email.length<=30)) {
		valid = false;
		let errEmail=document.getElementById("errEmail");
		errEmail.innerHTML = "email non valida";
		errEmail.style.color = "red";
		}
		else {
			errEmail.innerHTML = "";	
		}
	var pw = document.getElementsByName("password")[0].value;				
	if(!(pw.length>1)||!(pw.length<=30)) {
		valid = false;
		let errPassword=document.getElementById("errPassword");
		errPassword.innerHTML = "password non valida";
		errPassword.style.color = "red";
		}
		else {
			errPassword.innerHTML = "";
		}	
	
	if(valid)
		obj.submit();
}



function myFunction(x) {
  		x.style.background = "yellow";
		}
		
