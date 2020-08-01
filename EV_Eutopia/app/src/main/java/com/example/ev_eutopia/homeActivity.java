package com.example.eveutopia;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.eveutopia.App.CHANNEL_1_ID;

public class homeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private boolean pos = true;
    private NavigationView nav_view;
    private FirebaseUser user;
    private TextView nav_user_name, nav_user_email;
    private Button btnLogout;
    private String first_name, last_name;

    DatabaseReference mref;
    NotificationManagerCompat notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        user = FirebaseAuth.getInstance().getCurrentUser();

        notificationManager = NotificationManagerCompat.from(this);
        mref = FirebaseDatabase.getInstance().getReference();



        mref.child("battery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int battery = dataSnapshot.getValue(Integer.class);
                if(battery < 30) {
                    String menu_item = getIntent().getStringExtra("is_notification");
                    if(menu_item == null){
                        showNotfication();
                    }else{
                        Bundle bundle = new Bundle();
                        bundle.putStringArray("toEvStationListActivity", new String[]{"Nearby EV stations","Deployed"});
                        evStationListActivity obj = new evStationListActivity();
                        obj.setArguments(bundle);
                        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, obj).commit();
                    }
                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(homeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav_view = findViewById(R.id.nav_view);
        nav_view.setNavigationItemSelectedListener(this);

        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            nav_view.setCheckedItem(R.id.nav_home);
        }

        //Navigation View Code
        //..View for linking elements in navigation view
        View nav_header = nav_view.getHeaderView(0);

        //Updating user name and email in navigation view
        nav_user_name = nav_header.findViewById(R.id.nav_user_name);
        nav_user_email = nav_header.findViewById(R.id.nav_user_email);



        mref.child("users").child(user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                first_name = dataSnapshot.child("first_name").getValue(String.class);
                last_name = dataSnapshot.child("last_name").getValue(String.class);
                getSupportActionBar().setTitle("Hi, " + first_name);
                nav_user_name.setText(first_name+" "+last_name);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(homeActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        nav_user_email.setText(user.getEmail());

        //logout button in navigation view
        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(homeActivity.this,authenticationActivity.class));
            }
        });

    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if (!pos) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            nav_view.setCheckedItem(R.id.nav_home);
            pos = true;
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
                pos = true;
                break;
            case R.id.nav_nearest_station:
                Bundle bundle = new Bundle();
                bundle.putStringArray("toEvStationListActivity", new String[]{"Nearby EV stations","Deployed"});
                evStationListActivity obj = new evStationListActivity();
                obj.setArguments(bundle);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, obj).commit();
                pos = false;
                break;
            case R.id.nav_battery_life:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new batteryLifeFragment()).commit();
                pos = false;
                break;
            case R.id.nav_community:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new communtyFragment()).commit();
                pos = false;
                break;
            case R.id.nav_car_control:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new carControlsFragment()).commit();
                pos = false;
                break;
            case R.id.nav_climate_control:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new climateControlsFragment()).commit();
                pos = false;
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void showNotfication(){
        Intent resultIntent = new Intent(this, homeActivity.class);
        resultIntent.putExtra("is_notification","evStationListActivity");
// Create the TaskStackBuilder and add the intent, which inflates the back stack
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addNextIntentWithParentStack(resultIntent);
// Get the PendingIntent containing the entire back stack
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_battery_life)
                .setContentTitle("EV battery level below 30%")
                .setContentText("Please prioritise charging your EV")
                .setContentIntent(resultPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setCategory(Notification.CATEGORY_REMINDER)
                .build();
        notificationManager.notify(1, notification);
    }
}