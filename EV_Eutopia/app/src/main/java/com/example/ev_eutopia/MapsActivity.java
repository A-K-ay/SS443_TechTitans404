package com.example.eveutopia;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.textfield.TextInputEditText;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapView mapFragment;
    private String LATITUDE, LONGITUDE;
    private Button btnBeginNavigation;
    private TextInputEditText destination_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        btnBeginNavigation = findViewById(R.id.btnBeginNavigation);
        destination_address = findViewById(R.id.destination_address);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = findViewById(R.id.map);
        mapFragment.onCreate(savedInstanceState);
        mapFragment.getMapAsync(this);


        LATITUDE = getIntent().getStringExtra("LATITUDE_COORDINATE");
        LONGITUDE = getIntent().getStringExtra("LONGITUDE_COORDINATE");



        btnBeginNavigation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Uri navigation_coordinates = Uri.parse("google.navigation:q=" + Double.parseDouble(LATITUDE) + "," + Double.parseDouble(LONGITUDE) +"&waypoints=Delhi");
                String dst_addrs = destination_address.getText().toString();
                Uri navigation_coordinates =null;
                if(dst_addrs.isEmpty() || dst_addrs.equals("") || dst_addrs.equals(null)){
                    navigation_coordinates= Uri.parse("http://maps.google.com/maps?daddr="+ Double.parseDouble(LATITUDE) + "," + Double.parseDouble(LONGITUDE));
                }
                else{
                    navigation_coordinates= Uri.parse("http://maps.google.com/maps?daddr="+ Double.parseDouble(LATITUDE) + "," + Double.parseDouble(LONGITUDE) +"+to:"+dst_addrs);
                }

                Intent mapIntent = new Intent(Intent.ACTION_VIEW, navigation_coordinates);
                mapIntent.setPackage("com.google.android.apps.maps");
                startActivity(mapIntent);
            }
        });

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // mMap.setMyLocationEnabled(true);
        LatLng coordinate = new LatLng(Double.parseDouble(LATITUDE), Double.parseDouble(LONGITUDE));
        mMap.addMarker(new MarkerOptions().position(coordinate));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinate));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10));
        // Add a marker in Sydney and move the camera
//
    }

    @Override
    public void onResume() {
        mapFragment.onResume();
        super.onResume();
    }


    @Override
    public void onPause() {
        super.onPause();
        mapFragment.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapFragment.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapFragment.onLowMemory();
    }
}