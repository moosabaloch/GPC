package com.gpc.gpcsociety.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gpc.gpcsociety.R;
import com.gpc.gpcsociety.model.Client;

import java.util.ArrayList;

/**
 * Created by Moosa moosa.bh@gmail.com on 4/17/2016 17 April.
 * Everything is possible in programming.
 */
public class AdaptorClientInfo extends BaseAdapter {
    private Context context;
    private ArrayList<Client> clientArrayList;

    public AdaptorClientInfo(Context context, ArrayList<Client> clientArrayList) {
        this.clientArrayList = clientArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return clientArrayList.size();
    }

    @Override
    public Client getItem(int position) {
        return clientArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder view;
        if (convertView == null) {
            view = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.adaptor_event_information, null);
            view.title = (TextView) convertView.findViewById(R.id.titleEventAdaptor);
            view.budget = (TextView) convertView.findViewById(R.id.budgetEventAdaptor);
            view.clientName = (TextView) convertView.findViewById(R.id.clientEventAdaptor);
            view.date = (TextView) convertView.findViewById(R.id.dateEventAdaptor);
            view.status = (TextView) convertView.findViewById(R.id.statusEventAdaptor);
            view.venue = (TextView) convertView.findViewById(R.id.venueEventAdaptor);
            convertView.setTag(view);
        } else {
            view = (ViewHolder) convertView.getTag();
        }
        Client client = clientArrayList.get(position);
        String title = "Name: " + client.getName();
        view.title.setText(title);
        String venue = "Phone: " + client.getPhone();
        view.venue.setText(venue);
        String status = "Address: " + client.getAddress();
        view.status.setText(status);

        view.date.setVisibility(View.GONE);
        view.budget.setVisibility(View.GONE);
        view.clientName.setVisibility(View.GONE);


        return convertView;
    }

    private static class ViewHolder {
        TextView title, date, venue, budget, clientName, status;

    }
}

