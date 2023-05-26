package com.example.clinicapp;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Objects;

public class InvestigationsFragment extends Fragment {

    DatabaseHelper databaseHelper;
    ArrayList<String> investigation_id, investigation_name, investigation_price;
    CustomAdapter customAdapter;
    FloatingActionButton notifActionButton;


    public InvestigationsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(getActivity());
        investigation_id = new ArrayList<>();
        investigation_name = new ArrayList<>();
        investigation_price = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(getActivity(), investigation_id, investigation_name, investigation_price);

    }

    void storeDataInArrays() {
        Cursor cursor = databaseHelper.readAllData();
        if (cursor.getCount() == 0) {
            Toast.makeText(getActivity(), "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                investigation_id.add(cursor.getString(0));
                investigation_name.add(cursor.getString(1));
                investigation_price.add(cursor.getString(2));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Intent notificationClickIntent = new Intent("notifTest");
        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, notificationClickIntent, PendingIntent.FLAG_IMMUTABLE);

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_investigations, container, false);

        notifActionButton = rootView.findViewById(R.id.notifActionButton);


        notifActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(getActivity());

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    String channelId = "notifTest";
                    CharSequence channelName = "Notification Test";
                    int importance = NotificationManager.IMPORTANCE_HIGH;

                    NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
                    notificationManager.createNotificationChannel(channel);
                }

//                INTENT-FILTER IN ANDROID MANIFEST
                NotificationCompat.Builder builder = new NotificationCompat.Builder(
                        getContext(),
                        "notifTest"
                ).setSmallIcon(R.drawable.baseline_update_24)
                        .setContentTitle("Database Update")
                        .setContentText("Everything is up to date!")
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setContentIntent(pendingIntent)
                        .setChannelId("notifTest")
                        .addAction(R.drawable.cardiogram, "test", pendingIntent);

                if (ActivityCompat.checkSelfPermission(getContext(), android.Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.POST_NOTIFICATIONS}, 1);
                    return;
                }
                notificationManager.notify(0, builder.build());
            }
        });
        RecyclerView recyclerView = rootView.findViewById(R.id.recyclerViewInvestigations);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return rootView;
    }
}