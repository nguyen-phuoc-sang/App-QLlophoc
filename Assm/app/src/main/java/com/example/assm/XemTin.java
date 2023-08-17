package com.example.assm;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatActivity;

public class XemTin extends AppCompatActivity {

    WebView wv;
    String link;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xem_tin);
        wv=(WebView)findViewById(R.id.webView1);

        link=getIntent().getExtras().getString("link");
        wv.loadUrl(link);


    }
}