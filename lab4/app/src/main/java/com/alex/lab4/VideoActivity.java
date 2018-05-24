package com.alex.lab4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity implements View.OnClickListener {

    VideoView videoPlayer;

    Button playButton;
    Button stopButton;
    Button goToSong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        playButton = findViewById(R.id.playBtn);
        stopButton = findViewById(R.id.stopBtn);
        goToSong = findViewById(R.id.goToSongBtn);

        videoPlayer = findViewById(R.id.videoView);
        Uri uri = Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video);
        videoPlayer.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        videoPlayer.setMediaController(mediaController);
        mediaController.setMediaPlayer(videoPlayer);

        playButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        goToSong.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goToSongBtn:
                Intent intent = new Intent(this, SongActivity.class);
                startActivity(intent);
                break;
            case R.id.playBtn:
                if (videoPlayer.isPlaying()){
                    videoPlayer.pause();
                    playButton.setText(R.string.play);
                } else{
                    videoPlayer.start();
                    playButton.setText(R.string.pause);
                }
                break;
            case R.id.stopBtn:
                videoPlayer.stopPlayback();
                videoPlayer.resume();
                playButton.setText(R.string.play);
        }
    }
}
