package com.example.sayhi.ui.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.sayhi.R;
import com.example.sayhi.databinding.ActivityImageUploadBinding;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class ImageUploadActivity extends AppCompatActivity {

    ActivityImageUploadBinding binding;
    DatabaseReference databaseReference;
    StorageReference storageReference;
    FirebaseUser firebaseUser;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityImageUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        databaseReference = FirebaseDatabase.getInstance().getReference("user");
        storageReference =  FirebaseStorage.getInstance().getReference("upload");
        firebaseUser =      FirebaseAuth.getInstance().getCurrentUser();


        binding.imageUpload.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()                    //Crop image(Optional), Check Customization for more option
                    .compress(1024)            //Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)    //Final image resolution will be less than 1080 x 1080(Optional)
                    .start(101);

        });

        binding.uploadBtn.setOnClickListener(v -> {

            StorageReference uploadRef = storageReference.child("profile-img-" + firebaseUser.getUid());

            uploadRef.putFile(uri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {

                    if (task.isSuccessful()) {

                        uploadRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String imageUri = String.valueOf(uri);


                                HashMap<String, Object> userMap = new HashMap<>();

                                userMap.put("profileImage", imageUri);

                                databaseReference.child(firebaseUser.getUid()).updateChildren(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {

                                        if (task.isSuccessful()) {
                                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                            finish();
                                        }

                                    }
                                });

                            }
                        });
                    }

                }
            });


        });




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 101 && resultCode == Activity.RESULT_OK) {

            uri = data.getData();
            binding.imageUpload.setImageURI(uri);
            binding.uploadBtn.setVisibility(View.VISIBLE);


        }


    }
}