package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class fb_page extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fb_page);
        Intent intent=getIntent();
        WebView webView = (WebView)findViewById(R.id.webview);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
// you can load an URL
        this.setTitle("Department Sites");
        webView.loadUrl("https://www.facebook.com/abhiyanth2k18");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,fb_page.class));
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.nav_contact)
        {
            startActivity(new Intent(this,Contact_page.class));
        }
        if (item.getItemId()==R.id.nav_events)
        {
            startActivity(new Intent(this,events_main.class));
        }
        if (item.getItemId()==R.id.nav_abc)
        {
            startActivity(new Intent(this,abc.class));
        }
        if (item.getItemId()==R.id.nav_logut)
        {
            auth.signOut();
            startActivity(new Intent(this,LoginScreen.class));
        }
        if(item.getItemId()==R.id.nav_departments)
        {
            startActivity(new Intent(this,Departments.class));
        }
        if (item.getItemId()==R.id.nav_workshops)
        {
            startActivity(new Intent(this,workshops_main.class));
        }
        if(item.getItemId()==R.id.nav_sponsors)
        {
            startActivity(new Intent(this,Sponsors.class));
        }
        if(item.getItemId()==R.id.nav_notification)
        {
            startActivity(new Intent(this,Notifications.class));
        }
        if(item.getItemId()==R.id.nav_about)
        {
            startActivity(new Intent(this,About_abhiyanth.class));
        }
        if(item.getItemId()==R.id.nav_ourteam)
        {
            startActivity(new Intent(this,OurTeam.class));
        }
        if(item.getItemId()==R.id.nav_news)
        {
            startActivity(new Intent(this,DisplayImagesActivity.class));
        }
        if (item.getItemId()==R.id.resetpassword)
        {
            if(!isNetworkAvailable())
            {
                Toast.makeText(this, "Please Enable Internet Connection", Toast.LENGTH_SHORT).show();
            }
            auth.sendPasswordResetEmail(auth.getCurrentUser().getEmail())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(fb_page.this, "Verification link was send to your mail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}