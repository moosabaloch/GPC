package com.gpc.gpcsociety.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Moosa moosa.bh@gmail.com on 4/16/2016 16 April.
 * Everything is possible in programming.
 */
public class Client implements Parcelable {
    private int id;
    private String name;
    private String phone;
    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Client() {
    }

    public Client(int id, String name, String phone, String address) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeString(this.address);
    }

    protected Client(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
    }

    public static final Parcelable.Creator<Client> CREATOR = new Parcelable.Creator<Client>() {
        @Override
        public Client createFromParcel(Parcel source) {
            return new Client(source);
        }

        @Override
        public Client[] newArray(int size) {
            return new Client[size];
        }
    };
}
