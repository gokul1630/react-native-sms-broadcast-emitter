package com.rnapp.Sms

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.provider.Telephony
import com.facebook.react.bridge.WritableNativeMap
import com.rnapp.SmsReceiver

class SmsBroadCast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action != Telephony.Sms.Intents.SMS_RECEIVED_ACTION) {
            return
        }

        for (smsMessage in Telephony.Sms.Intents.getMessagesFromIntent(intent)) {
            val writableNativeMap = WritableNativeMap()

            writableNativeMap.putString("sender", smsMessage.originatingAddress)
            writableNativeMap.putString("body", smsMessage.messageBody)
            writableNativeMap.putString("simIndex", smsMessage.indexOnIcc.toString())
            SmsReceiver.sendMessage(writableNativeMap)
        }
    }
}