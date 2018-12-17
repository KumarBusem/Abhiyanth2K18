package com.abhiyanth.busemkumar.abhiyanth2k18;

/**
 * Created by Busem Kumar on 2/16/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

/**
 * Created by AndroidJSon.com on 6/18/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context,mycontext;
    List<ImageUploadInfo> MainImageUploadInfoList;
    View view_one;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference databaseReference;
    DatabaseReference mDatabaseLike;
    private boolean mProcessLike=false;
    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        mDatabaseLike= FirebaseDatabase.getInstance().getReference("Likes");
        mDatabaseLike.keepSynced(true);
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);
        Glide.with(context).load(UploadInfo.getImageURL()).into(holder.imageView);
        holder.imageNameTextView.setText(UploadInfo.getImageName());
        holder.imagename.setText(UploadInfo.getMyname());
        holder.setdataandtime.setText(UploadInfo.getDate());
        holder.imagemail.setText("  ("+UploadInfo.getMyemail()+")");
        final String key=UploadInfo.getKey();
        holder.setLikeImage(key);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference().child("Likes");
//You can use the single or the value.. depending if you want to keep track
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    if (key.equals(snap.getKey()))
                    {
                        holder.likescount.setText(snap.getChildrenCount()+"");
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        auth=FirebaseAuth.getInstance();
        user=auth.getCurrentUser();
        databaseReference = FirebaseDatabase.getInstance().getReference("All_Image_Uploads_Database");
        //Loading image from Glide library.
        holder.ShareLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int i=holder.getAdapterPosition();
                ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(i);
                Intent sendIntent = new Intent();
                FirebaseAuth a;
                a=FirebaseAuth.getInstance();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, a.getCurrentUser().getDisplayName()+ " Sent you some memories click the blue link to watch it.. \n"+UploadInfo.getImageURL());
                sendIntent.setType("text/plain");
                mycontext.startActivity(Intent.createChooser(sendIntent, "Share Via"));
            }
        });
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                mProcessLike=true;
                int i=holder.getAdapterPosition();
                final ImageUploadInfo up=MainImageUploadInfoList.get(i);
                mDatabaseLike.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (mProcessLike)
                        {
                            if(dataSnapshot.child(key).hasChild(auth.getCurrentUser().getUid()))
                            {
                                mDatabaseLike.child(key).child(auth.getCurrentUser().getUid()).removeValue();
                                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {
                                        for (DataSnapshot snap: dataSnapshot.getChildren()) {
                                            if (key.equals(snap.getKey()))
                                            {
                                                holder.likescount.setText(snap.getChildrenCount()+"");
                                            }

                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                                mProcessLike=false;
                            }
                            else
                            {
                                mDatabaseLike.child(key).child(auth.getCurrentUser().getUid()).setValue("Billa");        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snap: dataSnapshot.getChildren()) {
                                        if (key.equals(snap.getKey()))
                                        {
                                            holder.likescount.setText(snap.getChildrenCount()+"");
                                        }

                                    }
                                }

                                @Override
                                public void onCancelled(DatabaseError databaseError) {

                                }
                            });

                                mProcessLike=false;
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView,like;
        public TextView imageNameTextView,imagename,imagemail,likescount,setdataandtime,ShareLink;
       DatabaseReference mDatabaseLike;
        FirebaseAuth auth;
        public ViewHolder(View itemView) {
            super(itemView);
            view_one=itemView;
            mycontext=itemView.getContext();
            ShareLink=(TextView)itemView.findViewById(R.id.share);
            setdataandtime=(TextView)itemView.findViewById(R.id.timeanddate);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            imagename=(TextView)itemView.findViewById(R.id.usernameinrv);
            likescount=(TextView)itemView.findViewById(R.id.likescount);
            mDatabaseLike= FirebaseDatabase.getInstance().getReference().child("Likes");
            auth=FirebaseAuth.getInstance();
            imageNameTextView = (TextView) itemView.findViewById(R.id.ImageNameTextView);
            imagemail=(TextView)itemView.findViewById(R.id.mailinrv);
            like=(ImageView)itemView.findViewById(R.id.like);
            mDatabaseLike.keepSynced(true);
        }
        public void setLikeImage(final String key)
        {
            mDatabaseLike.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.child(key).hasChild(auth.getCurrentUser().getUid()))
                    {
                        like.setImageResource(R.drawable.ic_thumb_up_black_24dp);
                    }
                    else
                    {
                        like.setImageResource(R.drawable.ic_thumb_up_black_24dp_2);
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }
}