package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;

import java.util.ArrayList;


public class Track {

    //spotify id of the track
    private String id;

    //Name of the track
    private String name;

    //Name of the artist
    private String artist;

    //Cover image of the track
    private Image image;

    public Track(@JsonProperty("id") String id, @JsonProperty("name") String name,
                 @JsonProperty("artist") String artist, @JsonProperty("image") Image image){

        this.id = id;
        this.name = name;
        this.artist = artist;
        this.image = image;

    }

    //getters all needed to convert to Json
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public Image getImage() {
        return image;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return id.equals(track.id);
    }

}
