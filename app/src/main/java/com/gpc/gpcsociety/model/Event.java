package com.gpc.gpcsociety.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Moosa moosa.bh@gmail.com on 4/16/2016 16 April.
 * Everything is possible in programming.
 */
public class Event implements Parcelable {
    public static final Parcelable.Creator<Event> CREATOR = new Parcelable.Creator<Event>() {
        @Override
        public Event createFromParcel(Parcel source) {
            return new Event(source);
        }

        @Override
        public Event[] newArray(int size) {
            return new Event[size];
        }
    };
    private String id;
    private String title;
    private String date;
    private String venue;
    private int budget;
    private Client client;
    private String status;

    public Event(String id, String title, String date, String venue, int budget, Client client, String status) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.venue = venue;
        this.budget = budget;
        this.client = client;
        this.status = status;
    }

    public Event() {
    }

    protected Event(Parcel in) {
        this.id = in.readString();
        this.title = in.readString();
        this.date = in.readString();
        this.venue = in.readString();
        this.budget = in.readInt();
        this.client = in.readParcelable(Client.class.getClassLoader());
        this.status = in.readString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getVenue() {
        return venue;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.title);
        dest.writeString(this.date);
        dest.writeString(this.venue);
        dest.writeInt(this.budget);
        dest.writeParcelable(this.client, flags);
        dest.writeString(this.status);
    }
}
