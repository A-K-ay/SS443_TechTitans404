package com.example.eveutopia;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.UserProfileChangeRequest;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link account_edit_profile#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class account_edit_profile extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextInputEditText user_name_in,user_email_in;
    private Button account_profile_submit;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment account_edit_profile.
     */
    // TODO: Rename and change types and number of parameters
    public static account_edit_profile newInstance(String param1, String param2) {
        account_edit_profile fragment = new account_edit_profile();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public account_edit_profile() {
        // Required empty public constructor
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_account_edit_profile, container, false);

        final homeActivity homeActivityObj = (homeActivity) getActivity();

        user_name_in = v.findViewById(R.id.user_name_in);
        user_email_in = v.findViewById(R.id.user_email_in);
        account_profile_submit = v.findViewById(R.id.account_profile_submit);

        user_name_in.setText(homeActivityObj.user.getDisplayName());
        user_email_in.setText(homeActivityObj.user.getEmail());

        account_profile_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder().setDisplayName(user_name_in.getText().toString()).build();
                homeActivityObj.user.updateProfile(profileUpdates).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getContext(), "Profile Updated Successfully!!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container,new AccountFragment()).commit();
    }
}