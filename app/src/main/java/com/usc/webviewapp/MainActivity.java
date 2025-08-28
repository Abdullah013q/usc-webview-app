package com.usc.webviewapp;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Full-screen mode: Hide status bar and action bar
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable if site requires JS (e.g., for QR scanning)
        webSettings.setDomStorageEnabled(true);
        webSettings.setAllowFileAccess(false); // Security: Disable local file access
        webSettings.setAllowContentAccess(false); // Security: Disable content access
        webSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_NEVER_ALLOW); // Security: Block HTTP on HTTPS
        webSettings.setSafeBrowsingEnabled(true); // Security: Enable safe browsing for malware protection

        webView.loadUrl("https://usc-managment.vercel.app");
    }

    @Override
    public void onBackPressed() {
        WebView webView = findViewById(R.id.webview);
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
