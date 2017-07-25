package com.examples.shubu.pebblesripples;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Ankur on 23-07-2017.
 */

public class PebblesRipples extends Application{
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
