package com.diegollams.hellodarkness;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;



/**
 * Created by diegollams on 9/24/16.
 */
public class MusicPlayerService extends Service {

    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(MusicPlayerService.this, R.raw.darkness);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        startPlaying();
        return Service.START_NOT_STICKY;
    }

    /**
     * Will create a new media player and starts the song
     */
    private void startPlaying() {
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(MusicPlayerService.this, R.raw.darkness);
        mediaPlayer.setVolume(100, 100);
        mediaPlayer.setOnCompletionListener(completionListener);
        mediaPlayer.start();
        sendActionToDarkness(DarknessProvider.ACTION_MUSIC_PLAYING);
    }

    /**
     * Will send an action to the widget so we can notifiy
     * what happend
     * @param action valid action of {@code DarknessProvider} actions lists
     */
    private void sendActionToDarkness(String action){
        Intent clickIntent = new Intent(MusicPlayerService.this, DarknessProvider.class);
        clickIntent.setAction(action);
        sendBroadcast(clickIntent);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onDestroy() {
        sendActionToDarkness(DarknessProvider.ACTION_MUSIC_STOP);
        mediaPlayer.stop();
        mediaPlayer.release();
    }

//    Listeners

    MediaPlayer.OnCompletionListener completionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mp) {
            sendActionToDarkness(DarknessProvider.ACTION_MUSIC_STOP);
        }
    };
}
