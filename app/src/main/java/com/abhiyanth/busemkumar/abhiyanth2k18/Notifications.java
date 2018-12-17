package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Notifications extends AppCompatActivity {
    FirebaseAuth auth;
    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        auth=FirebaseAuth.getInstance();
        auth=FirebaseAuth.getInstance();
        if(!isNetworkAvailable())
        {
            Toast.makeText(this, "Please Enable Internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }
        recyclerView=(RecyclerView)findViewById(R.id.notification_recyclerview);
        myRef= FirebaseDatabase.getInstance().getReference().child("/Notifications");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter<Notification_Blog,Notifications.NotificationViewHolder> adapter=new FirebaseRecyclerAdapter<Notification_Blog, NotificationViewHolder>(
                Notification_Blog.class,
                R.layout.notifications_row,
                Notifications.NotificationViewHolder.class,
                myRef
        ) {
            @Override
            protected void populateViewHolder(Notifications.NotificationViewHolder viewHolder, Notification_Blog model, int position) {
                viewHolder.setTime(model.getTime());
                viewHolder.setTitle(model.getTitle());
                viewHolder.setDescription(model.getDescription());
            }
        };
        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_news,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onBackPressed() {
       startActivity(new Intent(this,DisplayImagesActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.nav_contact)
        {

            startActivity(new Intent(this,Contact_page.class));
        }
        if (item.getItemId()==R.id.nav_logut)
        {
            auth.signOut();
            startActivity(new Intent(this,LoginScreen.class));
        }
        if (item.getItemId()==R.id.nav_events)
        {
            startActivity(new Intent(this,events_main.class));
        }
        if (item.getItemId()==R.id.nav_abc)
        {
            startActivity(new Intent(this,abc.class));
        }
        if (item.getItemId()==R.id.nav_fb)
        {
            startActivity(new Intent(this,fb_page.class));
        }
        if(item.getItemId()==R.id.nav_departments)
        {
            startActivity(new Intent(this,Departments.class));
        }
        if(item.getItemId()==R.id.nav_workshops)
        {
            startActivity(new Intent(this,workshops_main.class));
        }
        if(item.getItemId()==R.id.nav_sponsors)
        {
            startActivity(new Intent(this,Sponsors.class));
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
                                Toast.makeText(Notifications.this, "Verification link was send to your mail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
    public static class NotificationViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        TextView textView_decription;
        TextView imageView;
        public NotificationViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView_title = (TextView)itemView.findViewById(R.id.notification_title);
            textView_decription = (TextView) itemView.findViewById(R.id.notification_description);
            imageView=(TextView) itemView.findViewById(R.id.notification_time);
        }
        public void setTitle(String title)
        {
            textView_title.setText(title+"");
        }
        public void setDescription(String description)
        {
            textView_decription.setText(description);
        }
        public void setTime(String image)
        {
            imageView.setText(image+"");
        }
    }
}
