package com.example.eveutopia;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashActivity extends AppCompatActivity {

    private FirebaseUser user;
    private DatabaseReference mref;
    private Boolean user_type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        user = FirebaseAuth.getInstance().getCurrentUser();


        Thread welcome_thread = new Thread() {
            @Override
            public void run() {
                try {
                    super.run();
                    sleep(800);
                } catch (Exception e) {
                } finally {
                    if (user == null) {
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                        finish();
                    } else {
                        mref = FirebaseDatabase.getInstance().getReference("cUsers");

                        mref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                user_type = dataSnapshot.hasChild(user.getUid());
                                gotoActivity();
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Toast.makeText(SplashActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }
            }
        };
        welcome_thread.start();
    }

    private void gotoActivity() {
        startActivity(new Intent(SplashActivity.this, (user_type?coorporateActivity.class:homeActivity.class)));
        finish();
    }
}