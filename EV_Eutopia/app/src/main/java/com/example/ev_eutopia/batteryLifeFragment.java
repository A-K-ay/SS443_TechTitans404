package com.example.eveutopia;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class batteryLifeFragment extends Fragment {

    private TextView ev_battery_percent, eta_charging_time, charging_state, charge_limit_tv;
    private LinearLayout eta_charging;
    private Button btn_control_charge_port;
    private Switch charge_limit_switch;
    private SeekBar charge_limit_seekBar;
    private Context context;
    private DatabaseReference mref;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_battery_life, container, false);

        mref = FirebaseDatabase.getInstance().getReference("battery");

        ev_battery_percent = v.findViewById(R.id.ev_battery_percent);
        eta_charging_time = v.findViewById(R.id.eta_charging_time);
        charging_state = v.findViewById(R.id.charging_state);
        charge_limit_tv = v.findViewById(R.id.charge_limit_tv);

        eta_charging = v.findViewById(R.id.eta_charging);

        btn_control_charge_port = v.findViewById(R.id.btn_control_charge_port);

        charge_limit_switch = v.findViewById(R.id.charge_limit_switch);

        charge_limit_seekBar = v.findViewById(R.id.charge_limit_seekBar);

        context = getContext();

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ev_battery_percent.setText(dataSnapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        final SharedPreferences.Editor editor = context.getSharedPreferences("com.techtitans404.eveutopia",Context.MODE_PRIVATE).edit();
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.techtitans404.eveutopia",Context.MODE_PRIVATE);

        Boolean charge_limit_button_state = sharedPreferences.getBoolean("charge_limit_switch_saved_state",false);
        charge_limit_switch_check(charge_limit_button_state);
        charge_limit_switch.setChecked(charge_limit_button_state);
        final int charge_limit = sharedPreferences.getInt("charge_limit_value",18);
        charge_limit_tv.setText(Integer.toString(charge_limit*5));
        charge_limit_seekBar.setProgress(charge_limit);

        context.registerReceiver(this.mBatInfoReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));


        charge_limit_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                charge_limit_switch_check(isChecked);
                editor.putBoolean("charge_limit_switch_saved_state",isChecked).commit();

            }
        });

        charge_limit_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                charge_limit_tv.setText(Integer.toString(progress*5));
                editor.putInt("charge_limit_value",progress).commit();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return v;
    }

    private void charge_limit_switch_check(Boolean b) {
        charge_limit_seekBar.setEnabled(b);
    }

    private BroadcastReceiver mBatInfoReceiver = new BroadcastReceiver(){
        @Override
        public void onReceive(Context ctxt, Intent intent) {
            int charge_plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
            boolean USB_charge = charge_plug == BatteryManager.BATTERY_PLUGGED_USB;
            boolean AC_charge = charge_plug == BatteryManager.BATTERY_PLUGGED_AC;

            if(USB_charge == false && AC_charge == true){
                charging_state.setText("SuperCharging");
                eta_charging.setVisibility(View.VISIBLE);
                btn_control_charge_port.setText("Close Charge Port");
            }
            else if(AC_charge == true || USB_charge == true){
                charging_state.setText("Plugged In");
                eta_charging.setVisibility(View.VISIBLE);
                btn_control_charge_port.setText("Close Charge Port");
            }
            else{
                charging_state.setText("Unplugged");
                eta_charging.setVisibility(View.GONE);
                btn_control_charge_port.setText("Open Charge Port");
            }
        }
    };
}
