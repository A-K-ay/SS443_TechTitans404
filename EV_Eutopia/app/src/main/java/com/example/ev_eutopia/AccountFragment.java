package com.example.eveutopia;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A fragment representing a list of Items.
 */
public class AccountFragment extends Fragment {

    private ImageView account_pic;
    private TextView account_name,
            account_email,
            account_edit_profile,
    account_charging_history,
    account_battery_swap_history,
    account_booking_history,
    account_help,
    account_payment_history;
    private Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account_list, container, false);
        context = view.getContext();

        account_pic = view.findViewById(R.id.account_pic);

        account_name = view.findViewById(R.id.account_user_name);
        account_email = view.findViewById(R.id.account_email);
        account_edit_profile = view.findViewById(R.id.account_edit_profile);
        account_charging_history = view.findViewById(R.id.account_charging_history);
        account_battery_swap_history = view.findViewById(R.id.account_battery_swap_history);
        account_help = view.findViewById(R.id.account_help);
        account_payment_history = view.findViewById(R.id.account_payment_history);
        account_booking_history = view.findViewById(R.id.account_booking_history);

        homeActivity homeActivityObj = (homeActivity) getActivity();

        account_name.setText(homeActivityObj.user.getDisplayName());
        account_email.setText(homeActivityObj.user.getEmail());

        account_edit_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.fragment_container,new account_edit_profile()).commit();
            }
        });

        account_charging_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new account_charging_history()).addToBackStack(null).commit();
            }
        });

        account_battery_swap_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new account_battery_swap_history()).addToBackStack(null).commit();
            }
        });

        account_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new account_help()).addToBackStack(null).commit();
            }
        });

        account_payment_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new account_payment_history()).addToBackStack(null).commit();
            }
        });

        account_booking_history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragment_container,new account_booking_history()).addToBackStack(null).commit();
            }
        });


        return view;
    }

}