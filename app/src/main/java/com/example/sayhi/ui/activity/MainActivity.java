package com.example.sayhi.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.sayhi.R;
import com.example.sayhi.databinding.ActivityMainBinding;
import com.example.sayhi.ui.fragment.HomeFragment;
import com.example.sayhi.ui.fragment.LogoutFragment;
import com.example.sayhi.ui.fragment.ProfileFragment;
import com.example.sayhi.ui.fragment.UserFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
 FirebaseAuth firebaseAuth;
    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    firebaseAuth = FirebaseAuth.getInstance();


        fragmentManager = getSupportFragmentManager();

       fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment(), "").commit();



        binding.bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {


                switch (item.getItemId()) {

                    case R.id.homeMenu:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment(), "").commit();
                        break;

                    case R.id.userMenu:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new UserFragment(), "").commit();
                        break;

                    case R.id.profileMenu:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new ProfileFragment(), "").commit();
                        break;

                    case R.id.logOutMenu:
                        fragmentManager.beginTransaction().replace(R.id.fragment_container, new LogoutFragment(), "").commit();
                        break;



                }


                return true;
            }
        });

    }
}