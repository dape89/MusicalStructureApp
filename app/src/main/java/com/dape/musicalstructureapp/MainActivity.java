package com.dape.musicalstructureapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.dape.musicalstructureapp.adapter.MusicAdapter;
import com.dape.musicalstructureapp.model.Music;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private ListView listMusic;
    private MusicAdapter musicAdapter;
    private ArrayList<Music> musics = new ArrayList<Music>();
    private Music music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        details_intent();
    }
    public void init() {
        listMusic = (ListView) findViewById(R.id.listMusic);
        musics.add(new Music("Heavy", "Linkin Park", "2:49", R.raw.heavy));
        musics.add(new Music("Let's hurt tonight", "One Republic", "3:15", R.raw.lets_hurt_tonight));
        musics.add(new Music("Without", "Avicii ft Sandro Cavazza", "3:01", R.raw.without_you));
        musics.add(new Music("Rich Love", "One Republic", "3:20", R.raw.rich_love));
        musics.add(new Music("Hello", "Lenny", "2:45", R.raw.hello));
        musicAdapter = new MusicAdapter(this, musics);
        listMusic.setAdapter(musicAdapter);
        Collections.sort(musics);
    }
    public void details_intent() {
        listMusic.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                music = (Music) listMusic.getItemAtPosition(position);
                Intent detail_intent = new Intent(MainActivity.this, DetailsActivity.class);
                detail_intent.putExtra("POSITION", position);
                detail_intent.putExtra("NAME", music.getSong_name());
                detail_intent.putExtra("ARTIST", music.getArtist());
                detail_intent.putExtra("TIME", music.getTime());
                detail_intent.putExtra("MUSIC", music.getMusic());
                startActivity(detail_intent);
            }
        });
    }
}
