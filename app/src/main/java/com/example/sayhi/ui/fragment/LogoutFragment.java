package com.example.sayhi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sayhi.R;
import com.example.sayhi.ui.activity.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;


public class LogoutFragment extends Fragment {

     FirebaseAuth firebaseAuth;

    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        firebaseAuth = FirebaseAuth.getInstance();
        // Inflate the layout for this fragment
        firebaseAuth.signOut();
        startActivity(new Intent(getActivity(), LoginActivity.class));
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}