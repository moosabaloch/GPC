package com.gpc.gpcsociety.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gpc.gpcsociety.R;
import com.gpc.gpcsociety.model.Event;

import java.util.ArrayList;

/**
 * Created by Moosa moosa.bh@gmail.com on 4/16/2016 16 April.
 * Everything is possible in programming.
 */
public class AdaptorEventInformation extends BaseAdapter {
    private Context context;
    private ArrayList<Event> eventArrayList;

    public AdaptorEventInformation(Context context, ArrayList<Event> eventArrayList) {
        this.context = context;
        this.eventArrayList = eventArrayList;
    }

    @Override
    public int getCount() {
        return eventArrayList.size();
    }

    @Override
    public Event getItem(int position) {
        return eventArrayList.get(position);
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
        Event event = eventArrayList.get(position);
        String title = "Title: " + event.getTitle();
        view.title.setText(title);
        String venue = "Venue: " + event.getVenue();
        view.venue.setText(venue);
        String status = "Status: " + event.getStatus();
        view.status.setText(status);
        String date = "Date:" + event.getDate();
        view.date.setText(date);
        String budget = "Budget: " + event.getBudget();
        view.budget.setText(budget);
        String client = "Client: " + event.getClient().get(position).getName();
        view.clientName.setText(client);


        return convertView;
    }

    private static class ViewHolder {
        TextView title, date, venue, budget, clientName, status;

    }
}
