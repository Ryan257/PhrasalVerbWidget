package com.ryan.phraveverb;

import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.widget.RemoteViews;

import com.ryan.phraveverb.linhtinh.Helper;

public class AlarmManagerBroadcastReceiver extends BroadcastReceiver {

    private static final String TAG = "com.ryan.phraveverb";

    @Override
    public void onReceive(Context context, Intent intent) {
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        wl.acquire();

        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.new_app_widget);

        int randomVerbIndex = Helper.getRandomVerbIndex();

        remoteViews.setTextViewText(R.id.appwidget_word,
                Helper.getVerbList().get(randomVerbIndex).word);

        remoteViews.setTextViewText(R.id.appwidget_meaning,
                Helper.getVerbList().get(randomVerbIndex).meaning);

        ComponentName thiswidget = new ComponentName(context, PhrasalVerbAppWidget.class);
        AppWidgetManager manager = AppWidgetManager.getInstance(context);
        manager.updateAppWidget(thiswidget, remoteViews);
        wl.release();
    }
}