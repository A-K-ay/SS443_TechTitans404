package com.example.eveutopia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account_charging_history#newInstance} factory method to
 * create an instance of this fragment.
 */
public class account_charging_history extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private MaterialCardView test_charge;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public account_charging_history() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account_charging_history.
     */
    // TODO: Rename and change types and number of parameters
    public static account_charging_history newInstance(String param1, String param2) {
        account_charging_history fragment = new account_charging_history();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account_charging_history, container, false);
        test_charge = v.findViewById(R.id.test_charge);
        test_charge.setVisibility(View.GONE);
        DatabaseReference mref = FirebaseDatabase.getInstance().getReference("Load_balancing_demo");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String visi = dataSnapshot.child("ev_charge").getValue(String.class);
                switch (visi){
                    case "Stage 2 -Slow Charging":
                    case "Stage 1 - Fast Charging":
                        test_charge.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        // Inflate the layout for this fragment
        return v;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new AccountFragment()).commit();
    }
}