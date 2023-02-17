package com.rnapp.Sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class SmsBroadCast extends BroadcastReceiver {
    private static final String TAG =
            SmsBroadCast.class.getSimpleName();

    public static final String pdu_type = "pdus";

    public String strMessage = "";

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
                strMessage += "SMS from " + msgs[i].getOriginatingAddress();

                strMessage += " :" + msgs[i].getMessageBody() + "\n";
                Toast.makeText(context, strMessage, Toast.LENGTH_LONG).show();
            }

        }
    }

    public String message(){
        return strMessage;
    }
}