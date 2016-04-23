package com.gpc.gpcsociety;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.gpc.gpcsociety.adaptor.AdaptorClientInfo;
import com.gpc.gpcsociety.model.Client;
import com.gpc.gpcsociety.util.Utils;

import java.util.ArrayList;

public class ClientInfoActivity extends AppCompatActivity {
    private ArrayList<Client> clients;
    private ListView listView;
    private AdaptorClientInfo adaptor;
    private Button addClient;
    private Firebase clientsFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
        clients = new ArrayList<>();
        clientsFB = FirebaseHandler.getInstance().getClients();
        listView = (ListView) findViewById(R.id.client_info_list_view);
        adaptor = new AdaptorClientInfo(this, clients);
        listView.setAdapter(adaptor);
        addClient = (Button) findViewById(R.id.addNewClient);
        addClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewClient();
            }
        });

        getData();
    }

    private void addNewClient() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.adaptor_client_add, null);
        dialog.setView(view);
        final TextInputEditText name, number, loc;
        name = (TextInputEditText) view.findViewById(R.id.adaptor_client_name);
        loc = (TextInputEditText) view.findViewById(R.id.adaptor_client_location);
        number = (TextInputEditText) view.findViewById(R.id.adaptor_client_number);

        dialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                if (name.getText().length() > 2) {
                    if (loc.getText().length() > 3) {
                        if (number.getText().length() > 3) {

                            Firebase newClient = clientsFB.push();
                            newClient.setValue(new Client(newClient.getKey(),
                                    name.getText().toString()
                                    , number.getText().toString(),
                                    loc.getText().toString()));

                        } else {
                            Utils.toast(ClientInfoActivity.this, "Valid number please");
                        }
                    } else {
                        Utils.toast(ClientInfoActivity.this, "Please insert valid address");
                    }
                } else {
                    Utils.toast(ClientInfoActivity.this, "Please Insert Valid Name");
                }
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setCancelable(false);
        dialog.show();
    }

    private void getData() {

/*
        clients.add(new Client(0, "Aif Saeed", "03343535353", "MA Jinnah Road Karachi"));
        clients.add(new Client(0, "Ali", "000000000000000", "MA Jinnah Road Karachi"));
        clients.add(new Client(0, "Asad Nadeem", "03233665256", "Karachi"));
        clients.add(new Client(0, "Saad", "0265441", "Gulshan"));
*/
        clientsFB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                clients.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Client client = snapshot.getValue(Client.class);
                    clients.add(client);
                    adaptor.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Utils.toast(ClientInfoActivity.this, "Error getting data from internet");
            }
        });
    }
}
