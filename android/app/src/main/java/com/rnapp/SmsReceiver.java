package com.rnapp;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class SmsReceiver extends ReactContextBaseJavaModule {
    private static ReactApplicationContext reactApplicationContext;

    public SmsReceiver(ReactApplicationContext context) {
        super(context);
        reactApplicationContext = context;
    }


    public static void sendMessage(WritableNativeMap message) {
        reactApplicationContext.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class).emit("onSmsReceive", message);
    }

    @NonNull
    @Override
    public String getName() {
        return "name";
    }
}