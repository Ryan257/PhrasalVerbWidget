package com.ryan.phraveverb;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.ryan.phraveverb.linhtinh.Helper;

/**
 * Implementation of App Widget functionality.
 */
public class PhrasalVerbAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, final AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // Get all ids
        ComponentName thisWidget = new ComponentName(context,
                PhrasalVerbAppWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);

        for (final int widgetId : allWidgetIds) {
            int randomVerbIndex = Helper.getRandomVerbIndex();
            int randomColor = Helper.getRandomColor();

            final RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.new_app_widget);

            remoteViews.setTextViewText(R.id.appwidget_word,
                    Helper.getVerbList().get(randomVerbIndex).word);
            remoteViews.setTextColor(R.id.appwidget_word, randomColor);

            remoteViews.setTextViewText(R.id.appwidget_meaning,
                    Helper.getVerbList().get(randomVerbIndex).meaning);

            Intent intent = new Intent(context, PhrasalVerbAppWidget.class);
            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.refresh, pendingIntent);

            remoteViews.setOnClickPendingIntent(R.id.change_color,
                    actionPendingIntent(context));

            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

    static PendingIntent actionPendingIntent(Context context) {
        Intent intent = new Intent(context,PhrasalVerbAppWidgetAAConfigureActivity.class);
        intent.setAction("LAUNCH_ACTIVITY");
        return PendingIntent.getActivity(context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

    @Override
    public void onEnabled(Context context) {
        super.onEnabled(context);
        AlarmManager am=(AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intent, 0);
        am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+ 1000 * 10, 10000 , pi);
    }

    @Override
    public void onDisabled(Context context) {
        Intent intent = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent sender = PendingIntent.getBroadcast(context, 0, intent, 0);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);
        super.onDisabled(context);
    }

    public static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.new_app_widget);

        int randomVerbIndex = Helper.getRandomVerbIndex();
        int randomColor = Helper.getRandomColor();

        remoteViews.setTextViewText(R.id.appwidget_word,
                Helper.getVerbList().get(randomVerbIndex).word);
        remoteViews.setTextColor(R.id.appwidget_word, randomColor);

        remoteViews.setTextViewText(R.id.appwidget_meaning,
                Helper.getVerbList().get(randomVerbIndex).meaning);

        Intent intentRefresh = new Intent(context, AlarmManagerBroadcastReceiver.class);
        PendingIntent pi = PendingIntent.getBroadcast(context, 0, intentRefresh, 0);
        remoteViews.setOnClickPendingIntent(R.id.refresh, pi);

        remoteViews.setOnClickPendingIntent(R.id.change_color,
                actionPendingIntent(context));

        appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
    }
}

