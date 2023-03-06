package com.example.sayhi.ui.fragment;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.sayhi.R;
import com.example.sayhi.databinding.FragmentProfileBinding;
import com.example.sayhi.databinding.FragmentUserBinding;
import com.example.sayhi.ui.User;
import com.example.sayhi.ui.UserAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ProfileFragment extends Fragment {

    FragmentProfileBinding binding;
    TextView textview;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding=FragmentProfileBinding.inflate(inflater, container, false);

        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                User user =snapshot.getValue(User.class);

               // Log.i("TAG ","onDataChange "+user.getUserName());//single profile

                binding.myName.setText(user.getUserName());
                binding.myEmail.setText(user.getUserEmail());
                binding.myPhone.setText(user.getUserPhone());
                binding.myCountry.setText(user.getUserCountry());
                binding.myImage.setImageResource(R.drawable.avatar_placeholder);

//                Glide.with(context)
//                        .load(path)
//                        .into(myImage);




                }



            @Override
            public void onCancelled(@NonNull DatabaseError error) {




            }
        });
        // Inflate the layout for this fragment
        return binding.getRoot();
    }
}