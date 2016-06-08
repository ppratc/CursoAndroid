package com.curso.sergio.mapagoogle;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String vapor_snippet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        vapor_snippet = getResources().getString(R.string.vapor);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        //Control de gestos
        UiSettings uiSettings = mMap.getUiSettings();
        uiSettings.setScrollGesturesEnabled(true);
        uiSettings.setTiltGesturesEnabled(true);
        uiSettings.setZoomGesturesEnabled(true);
        uiSettings.setRotateGesturesEnabled(true);
        //uiSettings.setAllGesturesEnabled(true);

        //Controles
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);
        uiSettings.setMapToolbarEnabled(true);




        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        LatLng vapor = new LatLng(41.558914, 2.023294);
       // mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
       // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.addMarker(new MarkerOptions().position(vapor).draggable(true).title("Vapor universitari").snippet(vapor_snippet));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(vapor));


        //LatLng andorra = new LatLng(42.55, 1.58);
        //CameraPosition cameraPosition = new CameraPosition.Builder()
        //        .target(andorra)
        //        .zoom(10)
        //        .bearing(60)
        //        .tilt(45)
        //        .build();
        //mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
    }


}
