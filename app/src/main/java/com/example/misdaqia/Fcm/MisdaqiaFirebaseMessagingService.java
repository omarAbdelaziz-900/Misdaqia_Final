package com.example.misdaqia.Fcm;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.misdaqia.R;
import com.example.misdaqia.View.Notification.NotificationActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MisdaqiaFirebaseMessagingService extends FirebaseMessagingService {
    private final static String misdaqia_notification_channel = "Zayed";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // ...

        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
//        Log.d("TAG", "From: " + remoteMessage.getFrom());
//
//        // Check if message contains a data payload.
//        if (remoteMessage.getData().size() > 0) {
//            Log.d("TAG", "Message data payload: " + remoteMessage.getData());
//
//            if (/* Check if data needs to be processed by long running job */ true) {
//                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
////                scheduleJob();
//            } else {
//                // Handle message within 10 seconds
////                handleNow();
//            }
//
//        }

        // Check if message contains a notification payload.
        if (remoteMessage.getNotification() != null) {
            // Create an explicit intent for an Activity in your app
//            Intent intent = new Intent(this, AlertDetails.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            NotificationManager mNotificationManager;

            NotificationCompat.Builder mBuilder =
                    new NotificationCompat.Builder(getBaseContext().getApplicationContext(), misdaqia_notification_channel);
            Intent ii = new Intent(getBaseContext().getApplicationContext(), NotificationActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getBaseContext(), 0, ii, 0);

//            NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
//            bigText.bigText(remoteMessage.getNotification().getBody());
//            bigText.setBigContentTitle(remoteMessage.getNotification().getTitle());
//            bigText.setSummaryText("Text in detail");

            mBuilder.setContentIntent(pendingIntent);
            mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
            mBuilder.setContentTitle(remoteMessage.getNotification().getTitle());
            mBuilder.setContentText(remoteMessage.getNotification().getBody());
            mBuilder.setPriority(Notification.PRIORITY_MAX);
            mBuilder.setAutoCancel(true);
//            mBuilder.setStyle(bigText);

            mNotificationManager =
                    (NotificationManager) getBaseContext().getSystemService(Context.NOTIFICATION_SERVICE);

// === Removed some obsoletes
            if (Build.VERSION.SDK_INT  >=  Build.VERSION_CODES.O)
            {
                String channelId = misdaqia_notification_channel;
                NotificationChannel channel = new NotificationChannel(
                        channelId,
                        misdaqia_notification_channel,
                        NotificationManager.IMPORTANCE_HIGH);
                mNotificationManager.createNotificationChannel(channel);
                mBuilder.setChannelId(channelId);
            }

            mNotificationManager.notify(0, mBuilder.build());
//                    mNotificationManager.notify(001, mBuilder.build());
            Log.d("TAG", "Message Notification Body: " + remoteMessage.getNotification().getBody());
        }else {
            Log.d("TAG", "Message Notification Is Null: ");
        }

        // Also if you intend on generating your own notifications as a result of a received FCM
        // message, here is where that should be initiated. See sendNotification method below.
    }


    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    @Override
    public void onNewToken(String token) {
        Log.d("TAG", "Refreshed token: " + token);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
//        sendRegistrationToServer(token);
    }
}

