package com.example.eveutopia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnRide;

    @Override
    protected void onStart() {
        super.onStart();

//        startUpCheck();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRide = findViewById(R.id.btnRide);
        btnRide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toauthActivty = new Intent(MainActivity.this,authenticationActivity.class);
                startActivity(toauthActivty);
            }
        });
    }

//    private void startUpCheck() {
//        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        if (user != null) {
//            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("cUsers");
//
//            ref.addListenerForSingleValueEvent(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    if(dataSnapshot.hasChild(user.getUid())) {
//                        finish();
//                        startActivity(new Intent(MainActivity.this, coorporateActivity.class));
//                    }
//                        else {
//                        finish();
//                        startActivity(new Intent(MainActivity.this, homeActivity.class));
//                    }
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError databaseError) {
//                    Toast.makeText(MainActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            return;
//        }
//    }
}