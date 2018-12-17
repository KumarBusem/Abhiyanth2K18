package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
public class department_web extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department_web);
        Intent intent=getIntent();
        WebView webView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
// you can load an URL
        this.setTitle("Department Sites");
        webView.loadUrl("https://www.facebook.com/abhiyanth2k18");
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,department_web.class));
    }
}
