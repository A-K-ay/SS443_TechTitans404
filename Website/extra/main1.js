function showResult(result) {
    document.getElementById('latitude').value = result.geometry.location.lat();
    document.getElementById('longitude').value = result.geometry.location.lng();
}
document.getElementById('formReg').addEventListener('submit',function(e){
	e.preventDefault();
	
	var user2 = document.getElementById('email');
	var pass = document.getElementById('password');
	var firstname = document.getElementById('fname');
	var lastname = document.getElementById('lname');
	var address = document.getElementById('address');
	geocoder = new google.maps.Geocoder();
    if (geocoder) {
        geocoder.geocode({
            'address': address.value,
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                showResult(results[0]);
            }
        });
    }
	firebase.auth().createUserWithEmailAndPassword(user2.value, pass.value).then(function(response){
		firebase.database().ref('users/'+ firebase.auth().currentUser.uid).set({
			first_name: fname.value,
			last_name: lname.value,
			address: address.value,
			latitude:result.geometry.location.lat(),
			longitude:result.geometry.location.lng(),
			userId :firebase.auth().currentUser.uid,
		email: firebase.auth().currentUser.email})
		firebase.auth().signOut();
		user2.value='';
		pass.value='';
		firstname.value='';
		lastname.value='';
		address.value='';
	})
	.catch(function(error){
		
		var errorCode = error.code;
		var errorMessage = error.message;
		window.alert("Error : " + errorMessage + "Please refill the form with valid information.");
		console.log(errorCode);
		console.log(errorMessage);
	});
});

var button = document.getElementById('register');

button.addEventListener("click", function () {
    var address = document.getElementById('address').value;
    getLatitudeLongitude(showResult, address)
});