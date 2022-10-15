package com.example.guru.Main;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.guru.Music.PlayMusicActivity;
import com.example.guru.R;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.TrackViewHolder> {

    private TypedArray trackNames;
    private MainActivity mainActivity;

    TrackAdapter(Context context) {
        trackNames = context.getResources().obtainTypedArray(R.array.tracknames);
        mainActivity = (MainActivity) context;
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        TrackViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.music_title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent intent = new Intent(mainActivity, PlayMusicActivity.class);
                    intent.putExtra("index", position);
                    intent.putExtra("title", trackNames.getString(position));
                    mainActivity.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public TrackViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tracks_item, viewGroup, false);
        return new TrackViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TrackViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(trackNames.getString(i));
        viewHolder.title.setText(trackNames.getString(i));
    }

    @Override
    public int getItemCount() {
        return trackNames.length();
    }



}
