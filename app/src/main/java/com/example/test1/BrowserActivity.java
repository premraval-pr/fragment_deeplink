package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;

public class BrowserActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browser);

        Intent intent = getIntent();
        Uri data = intent.getData();

        webView = findViewById(R.id.webview);

        webView.loadUrl(data.toString());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.host3,new LinkFragment(data.toString()))
                .commit();
    }
}