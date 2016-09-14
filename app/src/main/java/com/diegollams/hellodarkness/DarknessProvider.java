package com.diegollams.hellodarkness;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by diegollams on 9/13/16.
 */
public class DarknessProvider extends AppWidgetProvider {
    private static final String ACTION_CLICK = "ACTION_CLICK.custom";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName componentName = new ComponentName(context, DarknessProvider.class);
        int[] ids = appWidgetManager.getAppWidgetIds(componentName);
        for (int id:ids) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);

            Intent intent = new Intent(context, DarknessProvider.class);
            intent.setAction(ACTION_CLICK);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            remoteViews.setOnClickPendingIntent(R.id.layout, pendingIntent);
            appWidgetManager.updateAppWidget(componentName, remoteViews);
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(ACTION_CLICK.equals(intent.getAction())){
            MediaPlayer mp = MediaPlayer.create(context, R.raw.darkness);
            try {
                mp.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
