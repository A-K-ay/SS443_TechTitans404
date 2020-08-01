package com.example.eveutopia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class homeFragment extends Fragment {

    private DatabaseReference mref;
    private TextView ev_battery, ev_type;
    private ProgressBar progress_bar;
    private CardView get_directions, community_card;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);

        mref = FirebaseDatabase.getInstance().getReference();
        progress_bar = v.findViewById(R.id.progress_bar);

        ev_battery = v.findViewById(R.id.ev_battery);
        ev_type = v.findViewById(R.id.ev_type);

        get_directions = v.findViewById(R.id.get_directions);
        community_card = v.findViewById(R.id.communty_card);

        mref.child("battery").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int battery = dataSnapshot.getValue(Integer.class);
                ev_battery.setText(Integer.toString(battery));
                progress_bar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        mref.child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild("vehicle_type")) {
                    ev_type.setText(dataSnapshot.child("vehicle_type").getValue().toString());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        get_directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putStringArray("toEvStationListActivity", new String[]{"Nearby EV stations", "Deployed"});
                evStationListActivity obj = new evStationListActivity();
                obj.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, obj).addToBackStack(null).commit();
            }
        });

        community_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new communtyFragment()).addToBackStack(null).commit();
            }
        });

        return v;
    }

}
