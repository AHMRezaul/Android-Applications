package com.example.medkit;

import android.annotation.SuppressLint;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.PowerManager;
import android.support.v4.app.NotificationCompat;

public class NotificationLunch extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = pm.isScreenOn();
        if (!isScreenOn) {
            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "lunch:MyLock");
            wl.acquire(10000);
            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "lunch:MyCpuLock");
            wl_cpu.acquire(10000);
        }
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context, MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 701, repeating_intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setContentIntent(pendingIntent)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.splash_image))
                .setSmallIcon(R.drawable.dupur2small)
                .setContentTitle("Lunch Time")
                .setContentText("Tap to see your Nutrition Plan")
                .setAutoCancel(true)
                .setDefaults(-1);
        notificationManager.notify(1002, builder.build());
    }
}

