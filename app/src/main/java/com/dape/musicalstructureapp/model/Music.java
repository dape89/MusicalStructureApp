package com.dape.musicalstructureapp.model;

import android.support.annotation.NonNull;

/**
 * Created by Danale on 22/02/2018.
 */

public class Music implements Comparable<Music> {
    private String song_name;
    private String artist;
    private String time;
    private int music;
    private int id;

    public Music() {
    }

    public Music(String song_name, String artist, String time, int music) {
        this.song_name = song_name;
        this.artist = artist;
        this.time = time;
        this.music = music;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMusic() {
        return music;
    }

    public void setMusic(int music) {
        this.music = music;
    }

    @Override
    public int compareTo(@NonNull Music music) {
        return song_name.compareTo(music.getSong_name());
    }
}
