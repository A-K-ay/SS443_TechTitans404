const userId = document.getElementById('userId'); 
const fname = document.getElementById('fname'); 
const lname = document.getElementById('lname'); 
const address = document.getElementById('address'); 
const register = document.getElementById('register');
var email = document.getElementById("email");
const database = firebase.database();

const usersRef = database.ref('/users');
register.addEventListener('click', e => {
  e.preventDefault();
  const autoId = usersRef.push().key
  usersRef.child(autoId).set({
    first_name: fname.value,
    last_name: lname.value,
    address: address.value,
	email:email.value
	
  });
 
});
		 

function signup(){

  var nemail = document.getElementById("email").value;
  var npassword = document.getElementById("password").value;

  firebase.auth().createUserWithEmailAndPassword(nemail, npassword).catch(function(error) {
  // Handle Errors here.
  var errorCode = error.code;
  var errorMessage = error.message;
  // ...
    window.alert("Error : " + errorMessage);

    // ...
  });

}
var user = firebase.auth().currentUser;
