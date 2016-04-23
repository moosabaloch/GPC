package com.gpc.gpcsociety;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gpc.gpcsociety.adaptor.AdaptorEventInformation;
import com.gpc.gpcsociety.model.Event;
import com.gpc.gpcsociety.util.Utils;

import java.util.ArrayList;

public class EventInformation extends AppCompatActivity {
    private ArrayList<Event> eventArrayList;
    private ListView listView;
    private AdaptorEventInformation adaptor;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information);
        eventArrayList = new ArrayList<>();
        addButton = (Button) findViewById(R.id.addEvent);

        listView = (ListView) findViewById(R.id.event_info_list_view);
        adaptor = new AdaptorEventInformation(this, eventArrayList);
        listView.setAdapter(adaptor);
        getData();
    }

    private void getData() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EventInformation.this, AddEventActivity.class);
                startActivity(intent);
            }
        });
        FirebaseHandler.getInstance().getAllEvents().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventArrayList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Event event = snapshot.getValue(Event.class);
                    eventArrayList.add(event);
                }
                adaptor.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Utils.toast(EventInformation.this, "Error getting data from internet");
            }
        });

/*
        ArrayList<Client> clients = new ArrayList<>();

       // clients.add(new Client(0, "Ziad", "0346-8334950", "Nazimabad"));
        eventArrayList.add(new Event(0, "Combat", "12-12-12", "PAF KIET Main Campus", 25000, clients, "Pending"));
       // clients.add( new Client(0, "Sir Qazi", "0346-8334950", "Baldia"));
        eventArrayList.add(new Event(0, "KIET-MUN", "12-12-12", "PAF KIET Main Campus", 15000, clients, "Pending"));
      //  clients.add( new Client(0, "Sir Atif", "0346-8334950", "Baldia"));
        eventArrayList.add(new Event(0, "GPC - MUN", "12-12-12", "Gaurds Public College", 45000, clients, "Pending"));
     //   clients.add( new Client(0, "Sherry", "0346-8334950", "Baldia"));
        eventArrayList.add(new Event(0, "Annual Function", "12-12-12", "Air Men Golf Club", 35000, clients, "Pending"));
        adaptor.notifyDataSetChanged();
*/
    }


}
