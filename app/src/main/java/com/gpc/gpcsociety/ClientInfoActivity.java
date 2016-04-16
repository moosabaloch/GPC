package com.gpc.gpcsociety;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gpc.gpcsociety.adaptor.AdaptorClientInfo;
import com.gpc.gpcsociety.model.Client;

import java.util.ArrayList;

public class ClientInfoActivity extends AppCompatActivity {
    private ArrayList<Client> clients;
    private ListView listView;
    private AdaptorClientInfo adaptor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_info);
        clients = new ArrayList<>();
        listView = (ListView) findViewById(R.id.client_info_list_view);
        adaptor = new AdaptorClientInfo(this, clients);
        listView.setAdapter(adaptor);
        getData();
    }

    private void getData() {

        clients.add(new Client(0, "Aif Saeed", "03343535353", "MA Jinnah Road Karachi"));
        clients.add(new Client(0, "Ali", "000000000000000", "MA Jinnah Road Karachi"));
        clients.add(new Client(0, "Asad Nadeem", "03233665256", "Karachi"));
        clients.add(new Client(0, "Saad", "0265441", "Gulshan"));
        adaptor.notifyDataSetChanged();
    }
}
