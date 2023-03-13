package com.example.sayhi.ui.activity;

import static com.example.sayhi.ui.ext.Utils.ShowAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sayhi.databinding.ActivityLoginBinding;
import com.example.sayhi.ui.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();

        binding.registerBtn.setOnClickListener(v -> {

            Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
            startActivity(intent);
        });

        binding.loginBtn.setOnClickListener(v -> {

            String email=binding.emailEdit.getText().toString().trim();
            String password=binding.passEdit.getText().toString().trim();

            userLoginIn(email,password);




        });
    }

    private void userLoginIn(String email, String password) {

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                    FirebaseDatabase.getInstance().getReference("user").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                         User user= snapshot.getValue(User.class);
                         if(user!=null){

                             startActivity(new Intent(getApplicationContext(),ImageUploadActivity.class));
                             finish();


                         } else{

                             startActivity(new Intent(getApplicationContext(),MainActivity.class));
                             finish();
                         }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });



                }else{

                    String errorMsg=task.getException().getLocalizedMessage();
                    ShowAlert(LoginActivity.this,errorMsg);



                }

            }
        });



    }
}