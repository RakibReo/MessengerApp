package com.example.sayhi.ui.activity;

import static com.example.sayhi.Utils.ShowAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.sayhi.databinding.ActivityLoginBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                    finish();


                }else{

                    String errorMsg=task.getException().getLocalizedMessage();
                    ShowAlert(LoginActivity.this,errorMsg);



                }

            }
        });



    }
}