package com.bumos.vgvee.musician;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    Context context;
    ArrayList<data> data;

    public myAdapter(Context context, ArrayList<com.bumos.vgvee.musician.data> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public myAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater li = LayoutInflater.from(context);
        View view = li.inflate(R.layout.item_recomrv,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull myAdapter.ViewHolder holder, int position) {
        String title = data.get(position).data.get(1);
        String album = data.get(position).data.get(2);
        String artist = data.get(position).data.get(3);

        holder.recomTitle.setText(title);
        holder.recomArtist.setText(artist);
        holder.recomAlbum.setText(album);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView recomTitle, recomAlbum, recomArtist;

        public ViewHolder(View itemView) {
            super(itemView);
            recomTitle = itemView.findViewById(R.id.recomTitle);
            recomAlbum = itemView.findViewById(R.id.recomAlbum);
            recomArtist = itemView.findViewById(R.id.recomArtist);
        }
    }
}
