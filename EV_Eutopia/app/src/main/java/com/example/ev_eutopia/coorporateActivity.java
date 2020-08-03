package com.example.eveutopia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class coorporateActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseReference mref;
    private String[] slots_avail = {"-1", "-1"};
    //private int slots_avail = -1, total_slots = -1;
    Boolean USB_charge, AC_charge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coorporate);

        mref = FirebaseDatabase.getInstance().getReference();


        toolbar = findViewById(R.id.ctoolbar);
        setSupportActionBar(toolbar);

        mref.child("Deployed/-MDVdraGiG7kWirR1dkt/").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                slots_avail = dataSnapshot.child("slots_avail").getValue(String.class).split("/");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(coorporateActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        getSupportFragmentManager().beginTransaction().replace(R.id.c_fragment_container, new coorporateHomeFragment()).commit();

        this.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


    }

    //
    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int charge_plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            USB_charge = charge_plug == BatteryManager.BATTERY_PLUGGED_USB;
            AC_charge = charge_plug == BatteryManager.BATTERY_PLUGGED_AC;
            int scale = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
            int slot = Integer.parseInt(slots_avail[0]);
            if (AC_charge == true || USB_charge == true) {

                if (slot != -1) {
                    mref.child("Deployed/-MDVdraGiG7kWirR1dkt/slots_avail").setValue(String.valueOf(slot - 1) + "/" + slots_avail[1]);
                }
            } else {
                if (slot != -1 && !slots_avail[0].equals(slots_avail[1])) {
                    mref.child("Deployed/-MDVdraGiG7kWirR1dkt/slots_avail").setValue(String.valueOf(slot + 1) + "/" + slots_avail[1]);
                }
            }

            if (USB_charge == false && AC_charge == true) {
                mref.child("Load_balancing_demo/ev_charge").setValue("Stage 2 -Slow Charging");
                mref.child("Load_balancing_demo/").child("battery_scale").setValue(scale);
            } else if (AC_charge == true || USB_charge == true) {
                mref.child("Load_balancing_demo/ev_charge").setValue("Stage 1 - Fast Charging");
                mref.child("Load_balancing_demo/").child("battery_scale").setValue(scale);
            } else {
                mref.child("Load_balancing_demo/ev_charge").setValue("Not Charging");
                mref.child("Load_balancing_demo/").child("battery_scale").setValue(scale);
            }
        }
    };
}