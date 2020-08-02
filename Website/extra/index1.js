firebase.auth().onAuthStateChanged(function(user) {
  if (user) {
    // User is signed in.

    document.getElementById("user_div").style.display = "block";
    document.getElementById("login_div").style.display = "none";
	
	
	var userId = firebase.auth().currentUser.uid;

});

    if(user != null){

      return firebase.database().ref('/users/' + userId).once('value').then(function(snapshot) {
  var username = (snapshot.val() && snapshot.val().first_name) || 'Anonymous';
  var email_id = (snapshot.val() && snapshot.val().email) || 'Anonymous';
  var address1 = (snapshot.val() && snapshot.val().address) || 'Anonymous';
  // ...
  
  document.getElementById("user_para").innerHTML = "Welcome User : "+ username +" " + email_id +" " + address;

    }

  } else {
    // No user is signed in.

    document.getElementById("user_div").style.display = "none";
    document.getElementById("login_div").style.display = "block";
	

  }
});

function login(){

  var userEmail = document.getElementById("email_field").value;
  var userPass = document.getElementById("password_field").value;

  firebase.auth().signInWithEmailAndPassword(userEmail, userPass).catch(function(error) {
    // Handle Errors here.
    var errorCode = error.code;
    var errorMessage = error.message;

    window.alert("Error : " + errorMessage);

    // ...
  });

}
function signup(){

  var email = document.getElementById("email_field").value;
  var npassword = document.getElementById("password_field").value;

  firebase.auth().createUserWithEmailAndPassword(email, npassword).catch(function(error) {
  // Handle Errors here.
  var errorCode = error.code;
  var errorMessage = error.message;
  // ...
    window.alert("Error : " + errorMessage);

    // ...
  });

}

function logout(){
  firebase.auth().signOut();
  user2.value='';
		pass.value='';
		firstname.value='';
		lastname.value='';
		address.value='';
}
