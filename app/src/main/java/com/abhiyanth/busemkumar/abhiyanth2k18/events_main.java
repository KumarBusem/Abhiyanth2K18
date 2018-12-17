package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class events_main extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_main);
        auth=FirebaseAuth.getInstance();
        this.setTitle("Post a memory");
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void call_event_1(View view){startActivity(new Intent(this,events_1.class));}
    public void call_event_2(View view){startActivity(new Intent(this,events_2.class));}
    public void call_event_3(View view){startActivity(new Intent(this,events_3.class));}
    public void call_event_4(View view){startActivity(new Intent(this,events_4.class));}
    public void call_event_5(View view){startActivity(new Intent(this,events_5.class));}
    public void call_event_6(View view){startActivity(new Intent(this,events_6.class));}
    public void call_event_7(View view){startActivity(new Intent(this,events_7.class));}
    public void call_event_8(View view){startActivity(new Intent(this,Events.class));}
    public void onBackPressed() {
        startActivity(new Intent(this,DisplayImagesActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.nav_contact)
        {
            startActivity(new Intent(this,Contact_page.class));
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
        if (item.getItemId()==R.id.nav_fb)
        {
            startActivity(new Intent(this,fb_page.class));
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
                                Toast.makeText(events_main.this, "Verification link was send to your mail", Toast.LENGTH_SHORT).show();
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
