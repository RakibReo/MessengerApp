package com.example.sayhi.ui.activity;

import static com.example.sayhi.ui.ext.Utils.ShowAlert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import com.example.sayhi.databinding.ActivityRegisterBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.yesterselga.countrypicker.CountryPicker;
import com.yesterselga.countrypicker.Theme;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    ActivityRegisterBinding binding;
    FirebaseAuth firebaseAuth;
    ProgressDialog dialog;

    CountryPicker picker;

    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        firebaseAuth=FirebaseAuth.getInstance();   //Firebase Initialize

        databaseReference= FirebaseDatabase.getInstance().getReference("user");  // //real time database Initialize
                                                                                      //top most folder is user
        dialog=new ProgressDialog(RegisterActivity.this);
        dialog.setMessage("Please Wait...");

        binding.loginBtn.setOnClickListener(v -> {

            Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
            startActivity(intent);



        });


        //user theke mail pass nitw hbe
        binding.registerBtn.setOnClickListener(v -> {

            String nameStr=binding.nameEdit.getText().toString();
            String phone=binding.phnNoEdit.getText().toString();
            String email=binding.emailEdit.getText().toString();
            String password=binding.passEdit.getText().toString();

            if(nameStr.equals("")){
                ShowAlert(RegisterActivity.this,"Name field can't be empty");
            } else if(phone.equals("")){
                ShowAlert(RegisterActivity.this,"Phone field can't be empty");
            }else if(email.equals("")){
                ShowAlert(RegisterActivity.this,"Email field can't be empty");
            }else if(password.equals("")){
                ShowAlert(RegisterActivity.this,"Password field can't be empty");
            }else{
                
                    dialog.show();// //progressbar ekhane start hbe jokhn successfully new page asar age
                createAccount(email,password,nameStr,phone);

            }

        });

        picker = CountryPicker.newInstance("Select Country", Theme.LIGHT);  // Set Dialog Title and Theme
        picker.setListener((name, code, dialCode, flagDrawableResID) -> {


            binding.countryCode.setText(code);
            binding.countryName.setText(name);
            binding.countryDialCode.setText(dialCode);
            binding.countryIcon.setImageResource(flagDrawableResID);

            picker.dismiss();
        });

        binding.showCountry.setOnClickListener(v -> picker.show(getSupportFragmentManager(), "COUNTRY_PICKER"));

    }


    private void createAccount(String email, String password,String nameStr, String phone) {


        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    FirebaseUser firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
                    String userId=firebaseUser.getUid();

                    //database reference creation

                    HashMap<String,Object> userMap=new HashMap<>();
                    userMap.put("userName",nameStr);
                    userMap.put("userId",userId);   //unique id and it help to avoid overlap account
                    userMap.put("userEmail",email);
                    userMap.put("userPhone",phone);
                    userMap.put("profileImage","");
                    userMap.put("countryName",binding.countryName.getText().toString());

                    databaseReference.child(userId).setValue(userMap).addOnSuccessListener(unused -> {          //alt+enter


                        dialog.dismiss();;  //progressbar ekhane stop hbe jokhn successfully new page jabe
                        startActivity(new Intent(RegisterActivity.this,ImageUploadActivity.class));
                        //register compleete then go to dashboard



                    }).addOnFailureListener(e ->             //alt+enter

                            ShowAlert(RegisterActivity.this,e.getLocalizedMessage()));



                }


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                ShowAlert(RegisterActivity.this,e.getMessage().toString());//Aita automatic firebase theke asa message print korbe

                dialog.dismiss();;  //progressbar ekhane stop hbe jokhn successfully new page jabe


            }
        });




    }
}