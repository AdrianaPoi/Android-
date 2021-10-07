package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class ProviderDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider_details);
        WebView wb=findViewById(R.id.webview);
        wb.loadUrl("https://www.europages.ro/");
        wb.setWebViewClient(new WebViewClient());

    }
}