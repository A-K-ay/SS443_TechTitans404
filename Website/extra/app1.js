
var database = firebase.database();
register.addEventListener('click', e => {
  e.preventDefault();
function writeUserData() {
  firebase.database().ref('users/' + userId).set({
    fname: fname.value,
	lname:lname.value,
    email: email.value,
    address : address.value
  });
}
});
 
 