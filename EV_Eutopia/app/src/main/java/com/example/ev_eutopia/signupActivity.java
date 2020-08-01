package com.example.eveutopia;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class signupActivity extends AppCompatActivity implements PopupMenu.OnMenuItemClickListener {

    private Button btnToLogin, btnRegister;
    private EditText user_fname, user_lname, user_email, user_password, user_address,ev_type_popup_menu;
    private FirebaseAuth mAuth;
    private DatabaseReference mref;

    @Override
    protected void onStart() {
        super.onStart();

        btnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String fname = user_fname.getText().toString();
                final String lname = user_lname.getText().toString();
                final String email = user_email.getText().toString();
                final String password = user_password.getText().toString();
                final String addLocation = user_address.getText().toString();
                final String ev_type = ev_type_popup_menu.getText().toString();


                if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || password.isEmpty() || addLocation.isEmpty() || ev_type.isEmpty()) {
                    Toast.makeText(signupActivity.this, "Please Fill all the Fields", Toast.LENGTH_LONG).show();
                }else{
                    Geocoder geocoder = new Geocoder(signupActivity.this, Locale.getDefault());
                    List<Address> addressList = null;
                    try {
                        addressList = geocoder.getFromLocationName(addLocation,1);

                        if(!(addressList.size()>0)){
                            Toast.makeText(signupActivity.this,"Not a Valid Address",Toast.LENGTH_LONG).show();
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(addressList.size()>0) {
                        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(fname+" "+lname).build();
                                    user.updateProfile(profileUpdates);
                                    updateUI(fname, lname, user, addLocation,ev_type);
                                } else {
                                    Exception e = task.getException();
                                    Toast.makeText(signupActivity.this, "Failed Registration : " + e.getMessage(), Toast.LENGTH_LONG).show();
                                    return;
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
        mref = FirebaseDatabase.getInstance().getReference();
        btnToLogin = findViewById(R.id.btnToLogin);
        btnRegister = findViewById(R.id.btnRegister);

        user_fname = findViewById(R.id.user_fname);
        user_lname = findViewById(R.id.user_lname);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        user_address = findViewById(R.id.user_address);
        ev_type_popup_menu = findViewById(R.id.ev_type_popup_menu);


        ev_type_popup_menu.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){popup_menu(v);}
            }
        });
        ev_type_popup_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popup_menu(v);
            }
        });
    }

    private void updateUI(String fname, String lname, FirebaseUser user, String address,String ev_type) {
        writeNewUser(fname, lname, user.getUid(), user.getEmail(), address, ev_type);
        finish();
        startActivity(new Intent(signupActivity.this, homeActivity.class));
    }

    private void writeNewUser(String fname, String lname, String UID, String email, String address,String ev_type) {
        User local_user = new User(fname, lname, address, email, "","",ev_type);
        mref.child("users").child(UID).setValue(local_user);
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        try {
            List<Address> addressList = geocoder.getFromLocationName(address,1);

            if(addressList.size()>0){
                Address coor = addressList.get(0);
                mref.child("users").child(mAuth.getCurrentUser().getUid()).child("latitude").setValue(coor.getLatitude());
                mref.child("users").child(mAuth.getCurrentUser().getUid()).child("longitude").setValue(coor.getLongitude());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void popup_menu(View view) {
        PopupMenu popup = new PopupMenu(this,view);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu_ev_type);
        popup.show();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch(item.getItemId()){
            case R.id.ev2w1:
            case R.id.ev2w2:
            case R.id.ev2w3:
            case R.id.ev_auto_z1:
            case R.id.ev_eRickshaw:
            case R.id.ev3w1:
            case R.id.ev4w1:
            case R.id.ev4w2:
            case R.id.ev4w3:
                ev_type_popup_menu.setText(item.getTitle());
                return true;
            default:
                return false;
        }
    }
}