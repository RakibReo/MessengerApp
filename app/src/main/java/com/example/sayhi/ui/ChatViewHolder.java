package com.example.sayhi.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sayhi.R;

public class ChatViewHolder extends RecyclerView.ViewHolder {


      TextView chatUiTv;

    public ChatViewHolder(@NonNull View itemView) {
        super(itemView);

        chatUiTv=itemView.findViewById(R.id.chatUiTv);


    }
}
