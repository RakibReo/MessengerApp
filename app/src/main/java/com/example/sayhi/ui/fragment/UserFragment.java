package com.example.sayhi.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sayhi.databinding.FragmentUserBinding;
import com.example.sayhi.ui.User;
import com.example.sayhi.ui.UserAdapter;
import com.example.sayhi.ui.activity.ChatActivity;
import com.example.sayhi.ui.ext.UserListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserFragment extends Fragment implements UserListener {

    FragmentUserBinding binding;
    TextView textview;
    DatabaseReference databaseReference;
    FirebaseUser firebaseUser;

    List<User> userList;

    public UserFragment() {
        // Required empty public constructor


    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentUserBinding.inflate(inflater,container,false);   //adapter same rule
        userList=new ArrayList<>();
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();

        databaseReference= FirebaseDatabase.getInstance().getReference("user");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

//                User user =snapshot.getValue(User.class);
//                Log.i("TAG ","onDataChange "+user.getUserName());//single profile


                for(DataSnapshot ds: snapshot.getChildren()){  //multiple user

                    User user =ds.getValue(User.class);   //user er extra faka constructor declare korte hoi
                    userList.add(user);

                   // Log.i("TAG ","onDataChange "+user.getUserName());




                }
                UserAdapter adapter=new UserAdapter(getContext(),userList,UserFragment.this);
                binding.suracall.setAdapter(adapter);

              //  Log.i("TAG ","onDataChange "+userList.toString());



   }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return binding.getRoot();


        // Inflate the layout for this fragment
        //View view= inflater.inflate(R.layout.fragment_user, container, false);

        //textview=view.findViewById(R.id.justTv);
        //return view;



    }

    @Override
    public void ItemClick(User user) {

        Intent intent = new Intent(getActivity(), ChatActivity.class);
        intent.putExtra("user_id", user.getUserId());
        startActivity(intent);

    }
}