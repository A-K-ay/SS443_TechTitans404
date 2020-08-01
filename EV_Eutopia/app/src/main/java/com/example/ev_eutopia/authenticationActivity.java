package com.example.eveutopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ev_eutopia.signupActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class authenticationActivity extends AppCompatActivity {

    private Button btnToSignup, btnLogin;
    private FirebaseAuth mAuth;
    private EditText auth_password;
    private TextInputEditText auth_email;
    private ProgressBar progress_bar_auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();

        btnToSignup = findViewById(R.id.btnToSignup);
        btnLogin = findViewById(R.id.btnLogin);
        auth_email = findViewById(R.id.auth_email);
        auth_password = findViewById(R.id.auth_password);
        progress_bar_auth = findViewById(R.id.progress_bar_auth);


        btnToSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSignup = new Intent(authenticationActivity.this, signupActivity.class);
                startActivity(toSignup);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = auth_email.getText().toString();
                final String password = auth_password.getText().toString();

                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(authenticationActivity.this, "Please fill all the fields!", Toast.LENGTH_SHORT).show();
                } else {
                    progress_bar_auth.setVisibility(View.VISIBLE);
                    btnLogin.setClickable(false);
                    mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                updateUI(task.getResult().getUser());
                            } else {
                                //FirebaseAuthException e = (FirebaseAuthException) task.getException();
                                progress_bar_auth.setVisibility(View.GONE);
                                btnLogin.setClickable(true);
                                Toast.makeText(authenticationActivity.this, "Failed Login : " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });
                }
            }
        });

    }

    private void updateUI(final FirebaseUser user) {
        if(user != null) {
            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cUsers");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.hasChild(user.getUid())) {
                        finish();
                        Intent intent = new Intent(authenticationActivity.this,coorporateActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    } else {
                        finish();
                        Intent intent = new Intent(authenticationActivity.this, homeActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    Toast.makeText(authenticationActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}