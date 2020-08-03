package com.example.eveutopia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class coorporateHomeFragment extends Fragment implements View.OnClickListener {

    private MaterialCardView deployed_ev_stations, output_ev_stations, complaints;
    private DatabaseReference mref;
    private TextView total_users, total_ev;
    private Button btnLogoutCoor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_coorporate_home,container,false);

        deployed_ev_stations = v.findViewById(R.id.deployed_ev_stations);
        output_ev_stations = v.findViewById(R.id.output_ev_stations);
        complaints = v.findViewById(R.id.complaints);

        total_users = v.findViewById(R.id.total_users);
        total_ev = v.findViewById(R.id.total_ev);
        btnLogoutCoor = v.findViewById(R.id.btnLogoutCoor);


        mref = FirebaseDatabase.getInstance().getReference("users");


        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String count = String.valueOf((int)dataSnapshot.getChildrenCount());
                total_users.setText(count);
                total_ev.setText(count);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnLogoutCoor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    FirebaseAuth.getInstance().signOut();
                    getActivity().finish();

            }
        });

        deployed_ev_stations.setOnClickListener(this);
        output_ev_stations.setOnClickListener(this);

        complaints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.google.com/");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

        return v;
    }

    @Override
    public void onClick(View v) {
        Bundle bundle = new Bundle();
        if (v.getId() == R.id.deployed_ev_stations){
            bundle.putStringArray("toEvStationListActivity",new String[]{"Deployed EV Stations","Deployed"});
            bundle.putInt("coorporate_process",1);
        }
        else{
            bundle.putStringArray("toEvStationListActivity",new String[]{"EV Stations from ML Outputs ","Outputs"});
            bundle.putInt("coorporate_process",2);
        }

        evStationListActivity evStationListActivityObj = new evStationListActivity();
        evStationListActivityObj.setArguments(bundle);
        getFragmentManager().beginTransaction().replace(R.id.c_fragment_container, evStationListActivityObj).addToBackStack(null).commit();
    }
}
