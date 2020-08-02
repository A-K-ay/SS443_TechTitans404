firebase.database().ref('/Deployed/').once('value').then(function(snapshot) {
  var count = (snapshot.numChildren()) || 'Anonymous';
  // ...
  
  document.getElementById("user_para1").innerHTML = "Number of powerstations deployed: "+ count;
});

firebase.database().ref('/users/').once('value').then(function(snapshot) {
  var count1 = (snapshot.numChildren()) || 'Anonymous';
  // ...
  
  document.getElementById("user_para2").innerHTML = "Number of users registered: "+ count1;
});
