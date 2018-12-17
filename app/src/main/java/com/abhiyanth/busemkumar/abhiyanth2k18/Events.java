package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class Events extends AppCompatActivity {
    FirebaseAuth auth;
    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_other);
        this.setTitle("EVENTS-ALL");
        recyclerView=(RecyclerView)findViewById(R.id.myrecyclerview);
        myRef= FirebaseDatabase.getInstance().getReference().child("/Events");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter<Events_Blog,BlogViewHolder> adapter=new FirebaseRecyclerAdapter<Events_Blog, BlogViewHolder>(
                Events_Blog.class,
                R.layout.activity_events_row_other,
                BlogViewHolder.class,
                myRef
        ) {
            @Override
            protected void populateViewHolder(BlogViewHolder viewHolder, Events_Blog model, int position) {
                viewHolder.setImage(model.getUrl());
                viewHolder.setTitle(model.getName());
                viewHolder.setDescription(model.getId());
            }
        };
        recyclerView.setAdapter(adapter);
        auth=FirebaseAuth.getInstance();
    }

    public void onBackPressed() {
        startActivity(new Intent(this,events_main.class));
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
        if (item.getItemId()==R.id.nav_fb)
        {
            startActivity(new Intent(this,fb_page.class));
        }
        if (item.getItemId()==R.id.nav_logut)
        {
            auth.signOut();
            startActivity(new Intent(this,LoginScreen.class));
        }
        if(item.getItemId()==R.id.nav_workshops)
        {
            startActivity(new Intent(this,Workshops.class));
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
            auth.sendPasswordResetEmail(auth.getCurrentUser().getEmail())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(Events.this, "Verification link was send to your mail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
    public static class BlogViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        TextView textView_decription;
        ImageView imageView;
        public BlogViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView_title = (TextView)itemView.findViewById(R.id.title);
            textView_decription = (TextView) itemView.findViewById(R.id.description);
            imageView=(ImageView)itemView.findViewById(R.id.image);
        }
        public void setTitle(String title)
        {
            textView_title.setText(title+"");
        }
        public void setDescription(String description)
        {
            textView_decription.setText(description);
        }
        public void setImage(String image)
        {
            Picasso.with(itemView.getContext())
                    .load(image)
                    .into(imageView);
        }
    }
}