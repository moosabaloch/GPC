package com.gpc.gpcsociety;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Moosa moosa.bh@gmail.com on 4/16/2016 16 April.
 * Everything is possible in programming.
 */
public class AppController extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }

}
