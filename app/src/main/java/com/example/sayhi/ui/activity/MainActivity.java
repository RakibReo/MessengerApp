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
     //binding.logOutBtn.setOnClickListener(v->{
           // firebaseAuth.signOut();
           // startActivity(new Intent(getApplicationContext(),LoginActivity.class));
             //finish();

      //  });


        fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment(), "").commit();



        binding.logoutBTn.setOnClickListener(v -> {

            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();

        });




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


                }


                return true;
            }
        });

    }
}