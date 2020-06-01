package com.example.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final int[] i = {0};
        final int[] startTime = new int[1];

        final MediaPlayer player = MediaPlayer.create(this, R.raw.airhorn);
        final TextView vText = (TextView) findViewById(R.id.timer);
        final SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        Button button = (Button) findViewById(R.id.GO);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (i[0]==0) {
                    vText.setText(formateDate(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i[0] = 0;
                startTime[0] = seekBar.getProgress();
                new CountDownTimer(startTime[0],1000){
                    @Override
                    public void onTick(long millisUntilFinished) {
                        i[0]++;
                        int currentProgress = startTime[0]-i[0]*1000;
                        vText.setText(formateDate(currentProgress));
                        seekBar.setProgress(currentProgress);
                    }

                    @Override
                    public void onFinish() {
                        player.start();
                        i[0] = 0;
                    }
                }.start();
            }
        });



    }

    public String formateDate(int i) {
        i = i/1000;
        int min = i/60;
        return Integer.toString(min)+":"+Integer.toString(i - min*60);
    }
}