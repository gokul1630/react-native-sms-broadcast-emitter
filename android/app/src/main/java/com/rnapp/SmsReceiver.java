package com.rnapp;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.rnapp.Sms.SmsBroadCast;

public class SmsReceiver extends ReactContextBaseJavaModule {
  

    @ReactMethod
    public String getMessage() {
        SmsBroadCast smsBroadcast=new SmsBroadCast();
    
    return smsBroadcast.message();
    }

    @NonNull
    @Override
    public String getName() {
        return "name";
    }
}