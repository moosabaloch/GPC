package com.gpc.gpcsociety;

import com.firebase.client.Firebase;
import com.gpc.gpcsociety.util.Utils;

/**
 * Created by Moosa moosa.bh@gmail.com on 4/24/2016 24 April.
 * Everything is possible in programming.
 */
public class FirebaseHandler {
    private static FirebaseHandler ourInstance;
    private Firebase firebaseRef;
    private Firebase allEvents;
    private Firebase clients;

    private FirebaseHandler() {
        firebaseRef = new Firebase(Utils.FIREBASE_URL);
        allEvents = firebaseRef.child("events");
        clients = firebaseRef.child("clients");
    }

    public static FirebaseHandler getInstance() {
        if (ourInstance != null) {
            return ourInstance;
        }
        ourInstance = new FirebaseHandler();
        return ourInstance;
    }


    public Firebase getFirebaseRef() {
        return firebaseRef;
    }

    public Firebase getClients() {
        return clients;
    }

    public Firebase getAllEvents() {
        return allEvents;
    }


}
