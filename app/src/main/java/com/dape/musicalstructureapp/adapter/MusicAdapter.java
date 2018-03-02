package com.dape.musicalstructureapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dape.musicalstructureapp.R;
import com.dape.musicalstructureapp.model.Music;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Danale on 22/02/2018.
 */

public class MusicAdapter extends ArrayAdapter<Music> {
    private Context mContext;
    private ArrayList <Music> musics;

    public MusicAdapter(Context mContext, List<Music> musics) {
      super(mContext,0, musics);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = view;
        if (itemView==null){
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.song_items,parent,false);
        }
        Music currentMusic = getItem(position);
        TextView text_name = (TextView)itemView.findViewById(R.id.name);
        TextView text_artist = (TextView)itemView.findViewById(R.id.artist);
        TextView text_time = (TextView)itemView.findViewById(R.id.time);
        String song_name = currentMusic.getSong_name();
        text_name.setText(song_name);
        String artist = currentMusic.getArtist();
        text_artist.setText(artist);
        String time = currentMusic.getTime();
        text_time.setText(time);
        return itemView;
    }
}
