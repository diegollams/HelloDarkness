package com.diegollams.hellodarkness;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

/**
 * Created by diegollams on 9/13/16.
 */
public class DarknessProvider extends AppWidgetProvider {
    private static final String ACTION_CLICK_FINISH = "ACTION_CLICK_FINISH.custom";
    public static final String ACTION_MUSIC_PLAYING = "action_click.playing";
    public static final String ACTION_MUSIC_STOP = "action_click.stop";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        ComponentName componentName = new ComponentName(context, DarknessProvider.class);
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        Intent clickIntent = new Intent(context, DarknessProvider.class);
        clickIntent.setAction(ACTION_CLICK_FINISH);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        remoteViews.setOnClickPendingIntent(R.id.sadness_button, pendingIntent);
        appWidgetManager.updateAppWidget(componentName, remoteViews);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(ACTION_CLICK_FINISH.equals(intent.getAction())){
            Toast.makeText(context, R.string.start_darkess, Toast.LENGTH_LONG).show();
            Intent playService = new Intent(context, MusicPlayerService.class);
            context.startService(playService);
        }else if(ACTION_MUSIC_PLAYING.equals(intent.getAction())){
            setTextInWidget("Playing", context);
        }else if(ACTION_MUSIC_STOP.equals(intent.getAction())){
            setTextInWidget("Start Darkness", context);
        }
    }

    /**
     * Will update the widgets with the the message
     * @param message the text to set in the widget view
     * @param context the context of the receive method
     */
    private static void setTextInWidget(String message,Context context){
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                R.layout.widget_layout);
        remoteViews.setTextViewText(R.id.sadness_button, message);
        AppWidgetManager.getInstance(context).updateAppWidget(
                new ComponentName(context, DarknessProvider.class),remoteViews);
    }
}
