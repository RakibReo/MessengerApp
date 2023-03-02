package com.example.sayhi.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

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

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.homeMenuTop:
                Toast.makeText(this, "TopClicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profileMenuTop:
                Toast.makeText(this, "TopClicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.userMenuTop:
                Toast.makeText(this, "TopClicked", Toast.LENGTH_SHORT).show();
                break;


            case R.id.logOutMenuTop:

                logout();
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    private void logout() {

        firebaseAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        firebaseAuth.signOut();
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();


    }
}