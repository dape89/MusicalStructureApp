package com.dape.musicalstructureapp;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView detail_name;
    private TextView detail_artist;
    private TextView detail_time_finish;
    private TextView detail_time_start;
    private MediaPlayer mediaPlayer;
    private Button pause;
    private Button button_song_list;
    private SeekBar timeBar;
    private int pos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_song);
        init();
        listeners();
    }
    public void init(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        detail_name = (TextView)findViewById(R.id.detail_name);
        detail_artist = (TextView)findViewById(R.id.detail_artist);
        detail_time_finish = (TextView)findViewById(R.id.detail_time_finish);
        detail_time_start = (TextView)findViewById(R.id.detail_time_start);
        pause = (Button) findViewById(R.id.pause);
        button_song_list = (Button) findViewById(R.id.button_song_list);
        timeBar = (SeekBar)findViewById(R.id.timeBar);
        pos = ((getIntent().getIntExtra("POSITION",100)));
        detail_name.setText(getIntent().getStringExtra("NAME"));
        detail_artist.setText(getIntent().getStringExtra("ARTIST"));
        detail_time_finish.setText(getIntent().getStringExtra("TIME"));
        setTitle(detail_name.getText());
        mediaPlayer = MediaPlayer.create(DetailsActivity.this, getIntent().getIntExtra("MUSIC",0));
        timeBar.setMax(99);
        mediaPlayer.setLooping(true);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
           @Override
           public void onPrepared(MediaPlayer mediaPlayer) {
               timeBar.setMax(mediaPlayer.getDuration());
               mediaPlayer.start();
           }
        });
        timeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean input) {
                if(input){
                    mediaPlayer.seekTo(progress);
                    timeBar.setProgress(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
                timeBar.setMax(mediaPlayer.getDuration());
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                timeBar.setMax(mediaPlayer.getDuration());
            }
        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mediaPlayer != null){
                    try{
                        Message msg = new Message();
                        msg.what = mediaPlayer.getCurrentPosition();
                        handler.sendMessage(msg);
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int currentPosition = msg.what;
            timeBar.setProgress(currentPosition);
            detail_time_start.setText(text_time(currentPosition));
        }
    };
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                }
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void listeners(){
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    pause.setBackground(getDrawable(R.drawable.ic_play_24dp));
                }
                else{
                    mediaPlayer.start();
                    pause.setBackground(getDrawable(R.drawable.ic_pause_24dp));
                }
            }
        });
        button_song_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.stop();
                finish();
            }
        });
    }
    @Override
    public void onBackPressed() {
        if (mediaPlayer.isPlaying()){
            mediaPlayer.stop();
        }
        finish();
        super.onBackPressed();
    }
    public String text_time(int current_time){
        String time = "";
        int min = current_time/1000/60;
        int sec = current_time/1000%60;
        time = min + ":";
        if (sec < 10){
            time += "0";
        }
            time += sec;
        return time;
    }
}
