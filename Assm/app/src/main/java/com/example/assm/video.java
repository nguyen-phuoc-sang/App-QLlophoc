package com.example.assm;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

public class video extends AppCompatActivity {

    VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        vv = findViewById(R.id.videoView);

        vv.setMediaController(new MediaController(video.this));
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.haivui1);
        vv.setVideoURI(uri);
        vv.start();
    }
}