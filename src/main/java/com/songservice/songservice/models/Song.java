package com.songservice.songservice.models;


import org.springframework.data.annotation.Id;

public class Song{


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;

    private String title;
    private String artist;


    private String length;

    public Song() {	}

    public Song(String title, String artist, String length) {
        super();
        this.title = title;
        this.artist = artist;
        this.length = length;
    }

    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArtist() {
        return this.artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

//	public String getDurationPretty() {
//		long s  = length.toMillis()/1000 % 60;
//		long min = length.toMinutes() / 1;
//		long h = length.toHours() / 1 ;
//		return String.format("%d:%02d:%02d", h, min, s);
//	}

}
