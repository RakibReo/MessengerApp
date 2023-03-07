package com.example.sayhi.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sayhi.R;

public class UserViewHolder extends RecyclerView.ViewHolder {
   // ImageView userImage;
    TextView userName,userMail;
    ImageView userImage,chat_icon;


    public UserViewHolder(@NonNull View itemView) {
        super(itemView);

        userName=itemView.findViewById(R.id.userName);
        userMail=itemView.findViewById(R.id.userEmail);
        userImage=itemView.findViewById(R.id.userImage);
        chat_icon=itemView.findViewById(R.id.chat_icon);





    }
}
