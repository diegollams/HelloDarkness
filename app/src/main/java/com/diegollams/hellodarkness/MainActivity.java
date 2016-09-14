package com.diegollams.hellodarkness;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.darkness);
//        Log.e("shit"," primero");
//        try {
//            mp.start();
//            Log.e("shit"," Segundo");
//        } catch (Exception e) {
//            Log.e("shit"," Sext");
//            e.printStackTrace();
//        }
    }
}
