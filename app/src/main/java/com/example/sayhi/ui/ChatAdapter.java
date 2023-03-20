package com.example.sayhi.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sayhi.R;
import com.example.sayhi.ui.activity.Chat;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolder> {


    List <Chat> chatList;
    private String currentUserID;

    private  final  static  int RIGHT=0;
    private  final  static  int LEFT=1;

    public ChatAdapter(List<Chat> chatList, String currentUserID) {
        this.chatList = chatList;
        this.currentUserID = currentUserID;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
          View view;
        if(viewType==RIGHT){

            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.right_chat_ui,parent,false);

        }else {
            view= LayoutInflater.from(parent.getContext()).inflate(R.layout.left_chat_ui,parent,false);

        }

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {

        Chat chat=chatList.get(position);
        holder.chatUiTv.setText(chat.getMessage());


    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(chatList.get(position).getSenderId().equals(currentUserID)){

      return RIGHT;

        }else{

            return LEFT;

        }


    }
}
