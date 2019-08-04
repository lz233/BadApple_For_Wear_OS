package com.lz233.badapple;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends Activity {

    private TextView ba_textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ba_textview = findViewById(R.id.ba_textview);
        final Button start_butoon = findViewById(R.id.start_button);
        start_butoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start_butoon.setVisibility(View.GONE);
                run();
            }
        });
        ba_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });
    }
    private void run() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    MediaPlayer mMediaPlayer= MediaPlayer.create(MainActivity.this, R.raw.ba);
                    mMediaPlayer.start();
                    for(int i = 0;i<=3286;i++){
                        InputStream in = getAssets().open("pic/"+i+".txt");
                        int len = in.available();
                        byte[] buffer = new byte[len];
                        in.read(buffer);
                        final String temp = new String(buffer,"utf8");
                        ba_textview.post(new Runnable() {
                            @Override
                            public void run() {
                                ba_textview.setText(temp);
                            }
                        });
                        Thread.sleep(67);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        }).start();
    }
}
