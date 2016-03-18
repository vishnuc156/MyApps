package com.jersey.smartmi.utils;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by Vishnu on 12/02/16.
 */
public class SmartMIBus  extends Bus {

    private final Handler handler = new Handler(Looper.getMainLooper());

    public SmartMIBus(ThreadEnforcer enforcer) {
        super(enforcer);
    }

    @Override
    public void post(final Object event) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            super.post(event);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    SmartMIBus.super.post(event);
                }
            });
        }
    }


}