package com.abhiyanth.busemkumar.abhiyanth2k18;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

public class Workshops extends AppCompatActivity {
    FirebaseAuth auth;
    private RecyclerView recyclerView;
    private DatabaseReference myRef;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workshops_other);
        recyclerView=(RecyclerView)findViewById(R.id.worksop_recyclerview);
        if(!isNetworkAvailable())
        {
            Toast.makeText(this, "Please Enable Internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog = new ProgressDialog(Workshops.this);
        progressDialog.setMessage("Workshops Loading Please Wait....");
        progressDialog.show();
        auth=FirebaseAuth.getInstance();
        myRef= FirebaseDatabase.getInstance().getReference().child("/Workshops");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FirebaseRecyclerAdapter<Workshops_Blog,Workshops.WorkshopViewHolder> adapter=new FirebaseRecyclerAdapter<Workshops_Blog, WorkshopViewHolder>(
                Workshops_Blog.class,
                R.layout.activity_worksops_row_other,
                Workshops.WorkshopViewHolder.class,
                myRef
        ) {
            @Override
            protected void populateViewHolder(Workshops.WorkshopViewHolder viewHolder, Workshops_Blog model, int position) {
                viewHolder.setImage(model.getWorkshopurl());
                viewHolder.setTitle(model.getWorkshoptitle());
                viewHolder.setDescription(model.getWorkshopname());
                progressDialog.dismiss();
            }
        };
        recyclerView.setAdapter(adapter);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(this,workshops_main.class));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.nav_contact)
        {
            startActivity(new Intent(this,Contact_page.class));
        }
        if (item.getItemId()==R.id.nav_fb)
        {
            startActivity(new Intent(this,fb_page.class));
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
        if (item.getItemId()==R.id.nav_events)
        {
            startActivity(new Intent(this,events_main.class));
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
                                Toast.makeText(Workshops.this, "Verification link was send to your mail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        return super.onOptionsItemSelected(item);
    }
    public static class WorkshopViewHolder extends RecyclerView.ViewHolder {
        View mView;
        TextView textView_title;
        TextView textView_decription;
        ImageView imageView;
        public WorkshopViewHolder(View itemView) {
            super(itemView);
            mView=itemView;
            textView_title = (TextView)itemView.findViewById(R.id.workshop_title);
            textView_decription = (TextView) itemView.findViewById(R.id.workshop_description);
            imageView=(ImageView)itemView.findViewById(R.id.workshop_image);
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
