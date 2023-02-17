package com.rnapp.Sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;

import com.facebook.react.bridge.WritableNativeMap;
import com.rnapp.SmsReceiver;

public class SmsBroadCast extends BroadcastReceiver {

    public static final String pdu_type = "pdus";


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        SmsMessage[] msgs;

        String format = bundle.getString("format");
        Object[] pdus = (Object[]) bundle.get(pdu_type);

        if (pdus != null) {
            // Check the Android version.
            boolean isVersionM = (Build.VERSION.SDK_INT >=
                    Build.VERSION_CODES.M);


            msgs = new SmsMessage[pdus.length];
            for (int i = 0; i < msgs.length; i++) {
                // Check Android version and use appropriate createFromPdu.
                if (isVersionM) {
                    // If Android version M or newer:
                    msgs[i] =
                            SmsMessage.createFromPdu((byte[]) pdus[i]);
                } else {
                    // If Android version L or older:
                    msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }

                WritableNativeMap writableNativeMap = new WritableNativeMap();
                writableNativeMap.putString("sender", msgs[i].getOriginatingAddress());
                writableNativeMap.putString("body", msgs[i].getMessageBody());
                writableNativeMap.putString("simIndex", String.valueOf(msgs[i].getIndexOnSim()));
                SmsReceiver.sendMessage(writableNativeMap);
            }
        }
    }

}