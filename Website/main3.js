function showResult(result) {
	
    window.lati = result.geometry.location.lat();
    window.longi = result.geometry.location.lng();
}

function getLatitudeLongitude(callback, address) {
    // If adress is not supplied, use default value 'Ferrol, Galicia, Spain'
    address = address || 'Ferrol, Galicia, Spain';
    // Initialize the Geocoder
    geocoder = new google.maps.Geocoder();
    if (geocoder) {
        geocoder.geocode({
            'address': address
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                callback(results[0]);
            }
        });
    }
}

document.getElementById('formReg').addEventListener('submit',function(e){
	e.preventDefault();
	
	var user2 = document.getElementById('email');
	var pass = document.getElementById('password');
	var firstname = document.getElementById('fname');
	var lastname = document.getElementById('lname');
	var address = document.getElementById('address');
	
	firebase.auth().createUserWithEmailAndPassword(user2.value, pass.value).then(function(response){
		firebase.database().ref('users/'+ firebase.auth().currentUser.uid).set({
			first_name: fname.value,
			last_name: lname.value,
			address: address.value,
			latitude:window.lati,
			longitude:window.longi,
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

var rbutton = document.getElementById('register');

rbutton.addEventListener("click", function () {
    var address = document.getElementById('address').value;
    getLatitudeLongitude(showResult, address)
});