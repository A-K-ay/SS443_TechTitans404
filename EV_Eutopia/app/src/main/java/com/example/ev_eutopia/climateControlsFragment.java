package com.example.eveutopia;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;

public class climateControlsFragment extends Fragment {

    private Context context;
    private Switch control_climate_system, temperature_control_front_defrost, temperature_control_rear_defrost;
    private TextView temperature_driver, temperature_passenger, interior_temperature, exterior_temperature,temperature_driver_text, temperature_passenger_text, interior_temperature_text, exterior_temperature_text ;
    private SeekBar temperature_control_seekbar;
    private MaterialCardView control_climate_system_settings;
    private ImageView climate_switched_on_bg,climate_normal_bg;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_climate_controls,container,false);

        control_climate_system_settings = v.findViewById(R.id.control_climate_system_settings);

        control_climate_system = v.findViewById(R.id.control_climate_system);
        temperature_control_front_defrost = v.findViewById(R.id.temperature_control_front_defrost);
        temperature_control_rear_defrost = v.findViewById(R.id.temperatire_control_rear_defrost);

        temperature_driver = v.findViewById(R.id.temperature_driver);
        temperature_passenger = v.findViewById(R.id.temperature_passengers);
        interior_temperature = v.findViewById(R.id.interior_temperature);
        exterior_temperature = v.findViewById(R.id.exterior_temperature);
        exterior_temperature_text = v.findViewById(R.id.exterior_temperature_text);
        interior_temperature_text = v.findViewById(R.id.interior_temperature_text);
        temperature_driver_text = v.findViewById(R.id.temperature_driver_text);
        temperature_passenger_text = v.findViewById(R.id.temperature_passengers_text);

        temperature_control_seekbar = v.findViewById(R.id.temperature_control_seekBar);

        climate_normal_bg = v.findViewById(R.id.climate_normal_bg);
        climate_switched_on_bg = v.findViewById(R.id.climate_switched_on_bg);
        climate_switched_on_bg.setVisibility(View.GONE);

        context = v.getContext();
        final SharedPreferences.Editor editor = context.getSharedPreferences("com.techtitans404.eveutopia",Context.MODE_PRIVATE).edit();

        SharedPreferences sharedPreferences = context.getSharedPreferences("com.techtitans404.eveutopia",Context.MODE_PRIVATE);

        Boolean control_climate_system_saved_state = sharedPreferences.getBoolean("control_climate_system_saved_state",false);
        control_climate_system.setChecked(control_climate_system_saved_state);
        check_temp_control_settings(control_climate_system_saved_state);


        int temp = sharedPreferences.getInt("temperature_control_seekbar_saved_state",22);
        temperature_control_seekbar.setProgress(temp);
        temperature_driver.setText(Integer.toString(temp)+"°c");
        temperature_passenger.setText(Integer.toString(temp)+"°c");
        interior_temperature.setText(Integer.toString(temp)+"°c");
        exterior_temperature.setText(Integer.toString(temp)+"°c");

        control_climate_system.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("control_climate_system_saved_state",isChecked);
                editor.commit();

                check_temp_control_settings(isChecked);
            }
        });

        temperature_control_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                editor.putInt("temperature_control_seekbar_saved_state",progress);
                editor.commit();

                temperature_driver.setText(Integer.toString(progress)+"°c");
                temperature_passenger.setText(Integer.toString(progress)+"°c");
                interior_temperature.setText(Integer.toString(progress)+"°c");
                exterior_temperature.setText(Integer.toString(progress)+"°c");
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        temperature_control_front_defrost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context, "Front Defrost function has switched "+((isChecked)?"on":"off"), Toast.LENGTH_SHORT).show();
            }
        });

        temperature_control_rear_defrost.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(context, "Rear Defrost function has switched "+((isChecked)?"on":"off"), Toast.LENGTH_SHORT).show();
            }
        });


        return v;
    }

    private void check_temp_control_settings(boolean b) {
        temperature_control_seekbar.setEnabled(b);
        temperature_control_front_defrost.setEnabled(b);
        temperature_control_rear_defrost.setEnabled(b);
        if(b){
            control_climate_system_settings.setAlpha(1);
            climate_switched_on_bg.setVisibility(View.VISIBLE);
            climate_normal_bg.setVisibility(View.GONE);
        }
        else{
            climate_switched_on_bg.setVisibility(View.GONE);
            climate_normal_bg.setVisibility(View.VISIBLE);
            control_climate_system_settings.setAlpha(0.5f);
            temperature_control_rear_defrost.setEnabled(false);
            temperature_control_front_defrost.setChecked(false);
        }
    }
}
