package com.example.foodies10119255;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

        /*
        NIM: 10119255
        Nama : Rizki Lail Rahman
        Kelas : IF-7
         */

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    mapFragment Mapfragment = new mapFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    InfoFragment infoFragment = new InfoFragment();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.navigation);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, Mapfragment).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, Mapfragment).commit();
                        return true;

                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        return true;

                    case R.id.info:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, infoFragment).commit();
                        return true;
                }

                return false;
            }
        });


    }
}