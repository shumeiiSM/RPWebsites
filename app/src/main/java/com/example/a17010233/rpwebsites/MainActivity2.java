package com.example.a17010233.rpwebsites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity2 extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        wvPage = findViewById(R.id.webViewRP);

        wvPage.setWebViewClient(new WebViewClient());

        Intent intentReceived = getIntent();
        String myURL = intentReceived.getStringExtra("URL");

        Log.i("URL",myURL+"");
        wvPage.loadUrl(myURL);
        // wvPage.getSettings().setJavaScriptEnabled(true);
        wvPage.getSettings().setBuiltInZoomControls(true);
    }
}
