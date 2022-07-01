package com.example.foodies10119255;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

        /*
        NIM: 10119255
        Nama : Rizki Lail Rahman
        Kelas : IF-7
         */

public class mapFragment extends Fragment {
    private FusedLocationProviderClient client;
    private SupportMapFragment mapsFragment;
    private OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(GoogleMap googleMap) {
            LatLng Ricis = new LatLng(-6.8877880212363705, 107.61515765704189);
            LatLng spg = new LatLng(-6.886563291380018, 107.61502812558317);
            LatLng babeh = new LatLng(-6.886950986731256, 107.6155077114621);
            LatLng warkop = new LatLng(-6.8865126554641565, 107.6163616077804);
            LatLng geprek = new LatLng(-6.888209616169096, 107.61608885497304);
            googleMap.addMarker(new MarkerOptions().position(Ricis).title("Richeese Factory Dipatiukur"));
            googleMap.addMarker(new MarkerOptions().position(spg).title("Warung Nasi SPG"));
            googleMap.addMarker(new MarkerOptions().position(babeh).title("Warung Nasi Babeh"));
            googleMap.addMarker(new MarkerOptions().position(warkop).title("Warkop 99"));
            googleMap.addMarker(new MarkerOptions().position(geprek).title("AYAM GEPREK CHEF EDOY"));
            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            client = LocationServices.getFusedLocationProviderClient(getActivity());
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Ricis, 11.0F));
            mapsFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
            getCurrentLocation();
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }

    private void getCurrentLocation() {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        client = LocationServices.getFusedLocationProviderClient(getActivity());



        client.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    mapsFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(@NonNull GoogleMap googleMap) {
                            Log.e("Last Location: ", location.toString());
                            LatLng currentLoc = new LatLng(location.getLatitude(), location.getLongitude());
                            googleMap.addMarker(new MarkerOptions().position(currentLoc).title("Lokasi saat ini"));
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLoc, 17));
                        }
                    });

                }
            }
        });
    }



}