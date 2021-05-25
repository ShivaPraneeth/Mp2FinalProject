package com.example.bookapi.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookapi.R;
import com.example.bookapi.models.ImageLinks;
import com.example.bookapi.models.Items;

import java.util.ArrayList;
import java.util.List;

public class AuthorAdapter extends RecyclerView.Adapter<AuthorAdapter.BaseViewHolder> {

    private List<Items> data;
    private Context context;
    private ImageLinks clickedItem;
    private AuthorAdapter.OnRecycleClickListener onRecycleClickListener;



    public AuthorAdapter(List<Items> data, AuthorAdapter.OnRecycleClickListener onRecycleClickListener) {
        this.data = data;
        this.onRecycleClickListener = onRecycleClickListener;
    }


    public AuthorAdapter() {
    }

    public void setdata( List<Items> data) {
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AuthorAdapter.BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new AuthorAdapter.BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.child_author,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AuthorAdapter.BaseViewHolder holder, int position) {
        Items user= data.get(position);
        List<String> t= user.getVolumeInfo().getAuthors();
        //String i = user.getImageLinks().getSmallThumbnail();
        String temp="";
        for(int i=0;i<t.size();i++)
        {
            temp=temp+t.get(i)+"\n";
            holder.title.setText(temp);
        }

        if(user.getVolumeInfo().getImageLinks()!=null) {
            Glide.with(holder.itemView.getContext()).load(user.getVolumeInfo().getImageLinks().getSmallThumbnail()).into(holder.image);
        }
        else
        {
            Glide.with(holder.itemView.getContext()).load("https://cdn.dribbble.com/users/201599/screenshots/1545461/book.jpg?compress=1&resize=400x300").into(holder.image);;
        }
        
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  clickedItem.getSmallThumbnail(data);
            }
        });

    }

    @Override
    public int getItemCount() {

        if(data!=null) {
            return data.size();
        }
        else
        {
            return 0;
        }

    }

    public interface Clickeditem {
        // Log.e("clicked user",data.toString());
    }

    public class BaseViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView image;
        public BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.booktitle);
            image=itemView.findViewById(R.id.bookImage);
        }
    }
    public interface OnRecycleClickListener{

        void onMovieClick(int position);

    }
}
