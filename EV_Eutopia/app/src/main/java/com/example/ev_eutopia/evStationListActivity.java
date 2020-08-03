package com.example.eveutopia;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class evStationListActivity extends Fragment implements itemAdapter.OnItemClickListener, itemAdapter.OnButtonClickListener {

    public static final int LOCATION_REQUEST_ACCESS_CODE = 99;
    public static final int LOCATION_REQUEST_ACCESS_CODE_2 = 98;
    public static final int LOCATION_REQUEST_ACCESS_CODE_3 = 97;

    private RecyclerView recycler_view;
    private itemAdapter mAdapter;

    private ProgressBar progress_circle;

    private DatabaseReference mDatabaseReference, mref;
    private List<OutputCoor> mOutputCoor;
    private List<String> mOutputCoorKeys;
    private Context context;
    private TextView ev_station_list_Header, price_station;
    private android.location.Location last_known_location = null;
    private LocationManager locationManager;
    private String location_provider;
    private Double price;
    private String[] slots_avail, slots_avail_2, slots_avail_3;
    private Handler handler;
    private Runnable refresh;
    private homeActivity homeActivityObj;
    private int from_ccorporate = 0;
    private float search_range = 5000.00f;


//    Task<Location> location;
//    FusedLocationProviderClient fusedLocationProviderClient;

//    @SuppressLint("MissingPermission")
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        context = getContext();
//
//        GoogleApiClient googleApiClient = new GoogleApiClient.Builder(context)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//
//        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(getActivity());
//
//        location = fusedLocationProviderClient.getLastLocation();
//
//        Location loc_result = location.getResult();
//    }


    @Override
    public void onStop() {
        super.onStop();
        handler.removeCallbacks(refresh);
        getActivity().getFragmentManager().popBackStack();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_ev_station_list, container, false);

        context = v.getContext();

        Bundle bundle = this.getArguments();


        if (bundle != null) {
            from_ccorporate = bundle.getInt("coorporate_process");
        }

        if (from_ccorporate == 0) {
            homeActivityObj = (homeActivity) getActivity();
        }


        recycler_view = v.findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);
        recycler_view.setLayoutManager(new LinearLayoutManager(context));

        progress_circle = v.findViewById(R.id.progress_circle);

        ev_station_list_Header = v.findViewById(R.id.ev_station_list_Header);
        price_station = v.findViewById(R.id.price_station);


        String[] flexible_activity = new String[]{"Nearby Ev Stations", "Deployed"};
        if (bundle != null) {
            flexible_activity = bundle.getStringArray("toEvStationListActivity");
        }

        ev_station_list_Header.setText(flexible_activity[0]);


        mDatabaseReference = FirebaseDatabase.getInstance().getReference(flexible_activity[1]);
        mref = FirebaseDatabase.getInstance().getReference();

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_ACCESS_CODE);
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        location_provider = LocationManager.NETWORK_PROVIDER;


        if (checkLocationPermission()) {
            findLocation();
        }

        this.handler = new Handler();
        refresh = new Runnable() {
            @Override
            public void run() {
                evStationListActivity.this.handler.postDelayed(refresh, 10000);

                displayList();
            }
        };
        refresh.run();

        //displayList();

        return v;
    }

    private void displayList() {
        mOutputCoor = new ArrayList<>();
        mOutputCoorKeys = new ArrayList<>();
        mDatabaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    OutputCoor station_info = postSnapshot.getValue(OutputCoor.class);
                    String lat = postSnapshot.child("Latitude").getValue(String.class);
                    String lon = postSnapshot.child("Longitude").getValue(String.class);
                    if (lat == null || lon == null) {
                        continue;
                    }
                    if (from_ccorporate == 2) {
                        OutputCoor singlecoor = new OutputCoor(lat, lon, price, slots_avail, slots_avail_2, slots_avail_3);
                        mOutputCoor.add(singlecoor);
                        mOutputCoorKeys.add(postSnapshot.getKey());
                        continue;
                    }
                    if (last_known_location != null) {
                        float[] dist = new float[1];
                        android.location.Location.distanceBetween(last_known_location.getLatitude(), last_known_location.getLongitude(), Double.parseDouble(lat), Double.parseDouble(lon), dist);
                        if (dist[0] < search_range || from_ccorporate == 1) {
                            price = postSnapshot.child("price").getValue(Double.class);
                            slots_avail = postSnapshot.child("slots_avail").getValue(String.class).split("/");
                            slots_avail_2 = postSnapshot.child("slots_avail_2").getValue(String.class).split("/");
                            slots_avail_3 = postSnapshot.child("slots_avail_3").getValue(String.class).split("/");
                            OutputCoor singlecoor = new OutputCoor(lat, lon, price, slots_avail, slots_avail_2, slots_avail_3);
                            mOutputCoor.add(singlecoor);
                            mOutputCoorKeys.add(postSnapshot.getKey());
                        } else {
                            continue;
                        }
                    } else {
                        price = postSnapshot.child("price").getValue(Double.class);
                        slots_avail = postSnapshot.child("slots_avail").getValue(String.class).split("/");
                        slots_avail_2 = postSnapshot.child("slots_avail_2").getValue(String.class).split("/");
                        slots_avail_3 = postSnapshot.child("slots_avail_3").getValue(String.class).split("/");
                        OutputCoor singlecoor = new OutputCoor(lat, lon, price, slots_avail, slots_avail_2, slots_avail_3);
                        mOutputCoor.add(singlecoor);
                        mOutputCoorKeys.add(postSnapshot.getKey());
                    }
                }


                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                List<Address> addressList = null;
                List<String[]> results = new ArrayList<>();
                for (OutputCoor temp : mOutputCoor) {

                    try {

                        addressList = geocoder.getFromLocation(Double.parseDouble(temp.getLatitude()), Double.parseDouble(temp.getLongitude()), 2);

                        if ((addressList.size() > 0)) {
                            Address address = addressList.get(0);
                            if (from_ccorporate != 2) {
                                results.add(new String[]{address.getAddressLine(0), String.valueOf(price), String.valueOf(slots_avail[0]), String.valueOf(slots_avail_2[0]), String.valueOf(slots_avail_3[0])});
                            } else {
                                results.add(new String[]{address.getAddressLine(0), "0", "0", "0", "0"});
                            }
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }


                mAdapter = new itemAdapter(context, results);

                recycler_view.setAdapter(mAdapter);

                progress_circle.setVisibility(View.INVISIBLE);

                mAdapter.setOnItemClickListener(evStationListActivity.this);
                if(!(from_ccorporate == 1 || from_ccorporate == 2)){
                    mAdapter.setOnButtonClickListener(evStationListActivity.this);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                progress_circle.setVisibility(View.INVISIBLE);
            }
        });
    }
//
//    private Runnable refresh = new Runnable() {
//        @Override
//        public void run() {
//            evStationListActivity.this.handler.postDelayed(refresh,5000);
//
//            displayList();
//        }
//    };

    private void findLocation() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            boolean gps_enabled = false;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
                gps_enabled = locationManager.isLocationEnabled();
            } else {
                int mode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE,
                        Settings.Secure.LOCATION_MODE_OFF);
                gps_enabled = (mode != Settings.Secure.LOCATION_MODE_OFF);
            }
            if (!gps_enabled) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);

                alertDialog.setTitle("Enable Location Services");
                alertDialog.setMessage("GPS is not enabled. Please turn on Location Services to continue.");
                alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        context.startActivity(intent);
                    }
                });
                alertDialog.setCancelable(false);
                alertDialog.show();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, new homeFragment()).commit();
            }
            last_known_location = getUserLastLocation();
        }
//        if (last_known_location != null) {
//            double user_lat = last_known_location.getLatitude();
//            double user_long = last_known_location.getLongitude();
//        }
    }

    private Location getUserLastLocation() {
        List<String> providers = locationManager.getProviders(true);
        Location bestLocation = null;

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            for (String provider : providers) {
                Location l = locationManager.getLastKnownLocation(provider);
                if (l == null) {
                    continue;
                }
                if (bestLocation == null || l.getAccuracy() < bestLocation.getAccuracy()) {
                    bestLocation = l;
                }
            }
        }

        return bestLocation;
    }

    private boolean checkLocationPermission() {

        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // No explanation needed, we can request the permission.
            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_ACCESS_CODE);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_REQUEST_ACCESS_CODE_2);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION}, LOCATION_REQUEST_ACCESS_CODE_3);
            return false;
        } else {
            return true;
        }
    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        switch (requestCode) {
//            case LOCATION_REQUEST_ACCESS_CODE:
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                         last_known_location = locationManager.getLastKnownLocation(location_provider);
//                    }
//                    double user_lat = last_known_location.getLatitude();
//                    double user_long = last_known_location.getLongitude();
//                }
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(context, MapsActivity.class);
        intent.putExtra("LATITUDE_COORDINATE", mOutputCoor.get(position).getLatitude());
        intent.putExtra("LONGITUDE_COORDINATE", mOutputCoor.get(position).getLongitude());
        startActivity(intent);
        //Toast.makeText(this, "Normal click at position: "+ position, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onButtonClick(final int position) {
        mref.child("users/" + homeActivityObj.user.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!dataSnapshot.hasChild("Bookings")) {
                    if (homeActivityObj.battery > 30) {
                        switch (homeActivityObj.vehicle_type) {
                            case "4W-1":
                                final String key = mOutputCoorKeys.get(position);
                                mDatabaseReference.child(key).child("slots_avail").addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        String[] slots_avail = dataSnapshot.getValue(String.class).split("/");
                                        int slot = Integer.parseInt(slots_avail[0]);
                                        if (slot > 0) {
                                            mref.child("Deployed/" + key + "/slots_avail").setValue((slot - 1) + "/" + slots_avail[1])
                                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void aVoid) {
                                                            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                                                            Date date = new Date();
                                                            mref.child("users/" + homeActivityObj.user.getUid() + "/Bookings").child(key).setValue(formatter.format(date));
                                                            Toast.makeText(context, "Booked a slot successfully", Toast.LENGTH_SHORT).show();
                                                        }
                                                    });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                        }
                    } else {
                        Toast.makeText(context, "Your battery is above 30. Cannot book slot" + position, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, "Already have one booking.\nCannot book more.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}