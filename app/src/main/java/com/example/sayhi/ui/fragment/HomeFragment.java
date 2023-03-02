package com.example.sayhi.ui.fragment;

import static android.content.Context.NOTIFICATION_SERVICE;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.sayhi.R;

public class HomeFragment extends Fragment {

   Button showNotification;
   NotificationManager manager;
    NotificationCompat.Builder notification;
    public HomeFragment() {
        // Required empty public constructor



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view= inflater.inflate(R.layout.fragment_home, container, false);

      showNotification=view.findViewById(R.id.notficationBtn);

      showNotification.setOnClickListener(v->{

      ShowLocalNotification();





      });

       return view;

    }

    private void ShowLocalNotification() {
        manager=(NotificationManager) requireActivity().getSystemService(NOTIFICATION_SERVICE);

        notification=new NotificationCompat.Builder(requireActivity(),"MyNotification")
                .setContentTitle("just a title")
                .setContentText("just a body")
                .setSmallIcon(R.mipmap.ic_launcher_round);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("MyNotification","just name",NotificationManager.IMPORTANCE_HIGH));
        }


        int id=(int) System.currentTimeMillis();
        manager.notify(id,notification.build());

    }
}