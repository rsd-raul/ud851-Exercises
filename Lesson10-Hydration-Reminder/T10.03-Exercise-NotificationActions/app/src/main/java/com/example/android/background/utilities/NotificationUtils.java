/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.background.utilities;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import com.example.android.background.MainActivity;
import com.example.android.background.R;
import com.example.android.background.sync.ReminderTasks;
import com.example.android.background.sync.WaterReminderIntentService;

/**
 * Utility class for creating hydration notifications
 */
public class NotificationUtils {

    /*
     * This notification ID can be used to access our notification after we've displayed it. This
     * can be handy when we need to cancel the notification, or perhaps update it. This number is
     * arbitrary and can be set to whatever you like. 1138 is in no way significant.
     */
    private static final int WATER_REMINDER_NOTIFICATION_ID = 1138;
    /**
     * This pending intent id is used to uniquely reference the pending intent
     */
    private static final int WATER_REMINDER_PENDING_INTENT_ID = 3417;

    //  TODO (x1) Create a method to clear all notifications
    public static void clearNotifications(Context context){
        NotificationManager manager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.cancelAll();
    }

    public static void remindUserBecauseCharging(Context context) {
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context)
                .setColor(ContextCompat.getColor(context, R.color.colorPrimary))
                .setSmallIcon(R.drawable.ic_drink_notification)
                .setLargeIcon(largeIcon(context))
                .setContentTitle(context.getString(R.string.charging_reminder_notification_title))
                .setContentText(context.getString(R.string.charging_reminder_notification_body))
                .setStyle(new NotificationCompat.BigTextStyle().bigText(
                        context.getString(R.string.charging_reminder_notification_body)))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                // TODO (x17) Add the two new actions using the addAction method and your helper methods
                .addAction(drinkWaterAction(context))
                .addAction(ignoreReminderAction(context))
                .setAutoCancel(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            notificationBuilder.setPriority(Notification.PRIORITY_HIGH);
        }

        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(WATER_REMINDER_NOTIFICATION_ID, notificationBuilder.build());
    }

    //  TODO (x5) Add a static method called ignoreReminderAction
    public static NotificationCompat.Action ignoreReminderAction(Context context) {
        //      TODO (x6) Create an Intent to launch WaterReminderIntentService
        Intent ignoreIntent = new Intent(context, WaterReminderIntentService.class);
        //      TODO (x7) Set the action of the intent to designate you want to dismiss the notification
        ignoreIntent.setAction(ReminderTasks.ACTION_DISMISS_NOTIFICATION);
        //      TODO (x8) Create a PendingIntent from the intent to launch WaterReminderIntentService
        PendingIntent pendingIntent = PendingIntent.getService(context,
                WATER_REMINDER_PENDING_INTENT_ID, ignoreIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //      TODO (x9) Create an Action for the user to ignore the notification (and dismiss it)
        //      TODO (x10) Return the action
        return new NotificationCompat.Action(R.drawable.ic_cancel_black_24px,
                                             context.getString(R.string.dismiss),
                                             pendingIntent);
    }

    //  TODO (x11) Add a static method called drinkWaterAction
    public static NotificationCompat.Action drinkWaterAction(Context context) {
        //      TODO (x12) Create an Intent to launch WaterReminderIntentService
        Intent ignoreIntent = new Intent(context, WaterReminderIntentService.class);
        //      TODO (x13) Set the action of the intent to designate you want to increment the water count
        ignoreIntent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);
        //      TODO (x14) Create a PendingIntent from the intent to launch WaterReminderIntentService
        PendingIntent pendingIntent = PendingIntent.getService(context,
                WATER_REMINDER_PENDING_INTENT_ID, ignoreIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        //      TODO (x15) Create an Action for the user to tell us they've had a glass of water
        //      TODO (x16) Return the action
        return new NotificationCompat.Action(R.drawable.ic_drink_notification,
                context.getString(R.string.done),
                pendingIntent);
    }

    private static PendingIntent contentIntent(Context context) {
        Intent startActivityIntent = new Intent(context, MainActivity.class);

        return PendingIntent.getActivity(
                context,
                WATER_REMINDER_PENDING_INTENT_ID,
                startActivityIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private static Bitmap largeIcon(Context context) {
        Resources res = context.getResources();
        Bitmap largeIcon = BitmapFactory.decodeResource(res, R.drawable.ic_local_drink_black_24px);
        return largeIcon;
    }
}
