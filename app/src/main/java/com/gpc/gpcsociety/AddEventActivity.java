package com.gpc.gpcsociety;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gpc.gpcsociety.model.Client;
import com.gpc.gpcsociety.model.Event;
import com.gpc.gpcsociety.util.Utils;

import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private TextInputEditText title, venue, date, budget, status;
    private Button submitButton;
    private Spinner spinner;
    private Client selectedClient;
    private List<String> clientNames = new ArrayList<String>();
    private ArrayList<Client> clients = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        title = (TextInputEditText) findViewById(R.id.add_event_title);
        venue = (TextInputEditText) findViewById(R.id.add_event_venue);
        date = (TextInputEditText) findViewById(R.id.add_event_date);
        budget = (TextInputEditText) findViewById(R.id.add_event_budget);
        status = (TextInputEditText) findViewById(R.id.add_event_status);
        submitButton = (Button) findViewById(R.id.submitButtonEvent);
        spinner = (Spinner) findViewById(R.id.clients_spinner);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTextData();
            }
        });
        spinnerData();
    }

    private void addTextData() {
        if (title.getText().length() > 3) {
            if (venue.getText().length() > 3) {
                if (date.getText().length() > 4) {
                    if (budget.getText().length() > 3) {
                        if (status.getText().length() > 3) {
                            Firebase newEvent = FirebaseHandler.getInstance().getAllEvents().push();
                            newEvent.setValue(new Event(newEvent.getKey()
                                    , title.getText().toString()
                                    , date.getText().toString()
                                    , venue.getText().toString()
                                    , Integer.valueOf(budget.getText().toString())
                                    , selectedClient
                                    , status.getText().toString()), new Firebase.CompletionListener() {
                                @Override
                                public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                                    if (firebaseError == null) {

                                        finish();
                                    } else {
                                        Utils.toast(AddEventActivity.this, "Error posting new Event");
                                    }
                                }
                            });
                        } else {
                            status.setError("Please insert valid status");
                        }
                    } else {
                        budget.setError("Please insert valid budget");
                    }
                } else {
                    date.setError("Please insert valid date");
                }
            } else {
                venue.setError("Please insert valid venue");
            }
        } else {
            title.setError("Please insert Valid Title");
        }
    }

    private void spinnerData() {
        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        FirebaseHandler.getInstance().getClients().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clients.clear();
                clientNames.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Client client = snapshot.getValue(Client.class);
                    clients.add(client);
                    clientNames.add(client.getName());
                }
                selectedClient = clients.get(0);

                // Creating adapter for spinner
                ArrayAdapter<String> dataAdapter =
                        new ArrayAdapter<String>(AddEventActivity.this, android.R.layout.simple_spinner_item, clientNames);
                // Drop down layout style - list view with radio button
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                // attaching data adapter to spinner
                spinner.setAdapter(dataAdapter);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Utils.toast(AddEventActivity.this, "Error getting data from internet");
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        selectedClient = clients.get(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        selectedClient = clients.get(0);
    }
}
