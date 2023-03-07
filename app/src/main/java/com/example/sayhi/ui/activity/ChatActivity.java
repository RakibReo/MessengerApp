package com.example.sayhi.ui.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.example.sayhi.R;
import com.example.sayhi.databinding.ActivityChatBinding;
import com.example.sayhi.ui.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {

    Intent intent;

    DatabaseReference databaseReference;

    Toolbar toolbar;
    ActivityChatBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        toolbar = findViewById(R.id.toolbar);   //toolbar
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        intent=getIntent();
        String user_id=intent.getStringExtra("user_id");
        databaseReference= FirebaseDatabase.getInstance().getReference("user");
       getRemoteUser(user_id);

    }

    private void getRemoteUser(String user_id) {

databaseReference.child(user_id).addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {

          User remoteUser=snapshot.getValue(User.class);


        Glide.with(getApplicationContext()).load(remoteUser.getProfileImage()).
                placeholder(R.drawable.avatar_placeholder).into(binding.userImage);

        binding.userName.setText(remoteUser.getUserName());
        binding.userEmail.setText(remoteUser.getUserEmail());


    }

    public void onCancelled(@NonNull DatabaseError error) {

    }
});


    }
}