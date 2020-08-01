package com.example.eveutopia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class carControlsFragment extends Fragment {

    //declaring elements present in the view
    private CardView control_open_doors, control_close_doors;
    private Button control_sunroof;
    private TextView status_car_doors, status_sunroof;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_car_controls,container,false);

        //Referencing elements of the views
        control_open_doors = v.findViewById(R.id.control_open_doors);
        control_close_doors = v.findViewById(R.id.control_close_doors);
        control_sunroof = v.findViewById(R.id.control_sunroof);
//        control_open_trunk = v.findViewById(R.id.control_open_trunk);
//        control_open_frunk = v.findViewById(R.id.control_open_frunk);

        //Referencing Text views
        status_car_doors = v.findViewById(R.id.status_car_doors);
        status_sunroof = v.findViewById(R.id.status_sunroof);

        //setting on Click Listener for door controls
        control_open_doors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDoors();
            }
        });
        control_close_doors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeDoors();
            }
        });


        //checking status of sunroof
        checkSunroofStatus();

        //Setting On CLick Listener For sunroof Controls
        control_sunroof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkSunroofControl();
                checkSunroofStatus();
            }
        });

        return v;
    }

    private void checkSunroofControl() {
        switch (control_sunroof.getText().toString()){
            case "Open":
                status_sunroof.setText("Open");
                break;
            case "Close":
                status_sunroof.setText("Vent");
                break;
        }
    }

    private void checkSunroofStatus() {
        switch (status_sunroof.getText().toString()){
            case "Vent":
                control_sunroof.setText("Open");
                break;
            case "Open":
                control_sunroof.setText("Close");
                break;
        }
    }

    private void closeDoors() {
        status_car_doors.setText("Locked");
    }

    private void openDoors() {
        status_car_doors.setText("Unlocked");
    }
}
