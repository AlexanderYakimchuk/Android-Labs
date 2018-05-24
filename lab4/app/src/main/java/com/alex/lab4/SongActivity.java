package com.alex.lab4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class SongActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer musicPlayer;
    Button playButton;
    Button stopButton;
    Button goToVideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song);

        playButton = findViewById(R.id.playBtn);
        stopButton = findViewById(R.id.stopBtn);
        goToVideo = findViewById(R.id.goToVideoBtn);

        musicPlayer = MediaPlayer.create(this, R.raw.song);
        musicPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                musicPlayer.stop();
            }
        });

        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        goToVideo.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.goToVideoBtn:
                musicPlayer.stop();
                Intent intent = new Intent(this, VideoActivity.class);
                startActivity(intent);
                break;
            case R.id.playBtn:
                if (musicPlayer.isPlaying()){
                    musicPlayer.pause();
                    playButton.setText(R.string.play);
                } else{
                    musicPlayer.start();
                    playButton.setText(R.string.pause);
                }
                break;
            case R.id.stopBtn:
                musicPlayer.stop();
                playButton.setText(R.string.play);
                try {
                    musicPlayer.prepare();
                    musicPlayer.seekTo(0);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicPlayer.stop();
    }
}
