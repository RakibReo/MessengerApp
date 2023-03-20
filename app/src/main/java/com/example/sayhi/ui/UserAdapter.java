package com.example.sayhi.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.sayhi.R;
import com.example.sayhi.ui.activity.ChatActivity;
import com.example.sayhi.ui.ext.UserListener;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private Context context;

     List <User> userList;

    UserListener userListener;

    public UserAdapter(Context context, List<User> userList, UserListener userListener) {
        this.context = context;
        this.userList = userList;
        this.userListener = userListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view=LayoutInflater.from(context).inflate(R.layout.user_item,parent,false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {

   holder.userName.setText(userList.get(position).getUserName());
        holder.userMail.setText(userList.get(position).getUserName());

        Glide.with(context)
                .load(userList.get(position)
                        .getProfileImage())
                .placeholder(R.drawable.avatar_placeholder)
                .into(holder.userImage);



        holder.chat_icon.setOnClickListener(v->{

         userListener.ItemClick(userList.get(position));


        });





    }

    @Override
    public int getItemCount() {
        return userList.size();
    }
}
