package com.example.bookapi.utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.bookapi.R;
import com.example.bookapi.models.Data;
import com.example.bookapi.models.ImageLinks;
import com.example.bookapi.models.Items;
import com.example.bookapi.models.ResponseModal;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import retrofit2.Response;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BaseViewHolder> {

    private List<Items> data;
    private Context context;
    private ImageLinks clickedItem;
    private OnRecycleClickListener onRecycleClickListener;



    public BooksAdapter(List<Items> data, OnRecycleClickListener onRecycleClickListener) {
        this.data = data;
        this.onRecycleClickListener = onRecycleClickListener;
    }


    public BooksAdapter() {
    }

    public void setdata( List<Items> data) {
        this.data=data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context=parent.getContext();
        return new BooksAdapter.BaseViewHolder(LayoutInflater.from(context).inflate(R.layout.recycleitem,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        Items user= data.get(position);
      String t= user.getVolumeInfo().getTitle();
      //String i = user.getImageLinks().getSmallThumbnail();
      holder.title.setText(t);
     // holder.image.setText(i);

        Glide.with(holder.itemView.getContext()).load(user.getVolumeInfo().getImageLinks().getSmallThumbnail()).into(holder.image);

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
