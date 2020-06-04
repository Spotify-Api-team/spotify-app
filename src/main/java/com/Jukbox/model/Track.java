package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.DataAmount;

import java.util.ArrayList;


public class Track {

    private String id;

    private String name;

    private String artist;

    private Image image;

    public Track(@JsonProperty("id") String id, @JsonProperty("name") String name,
                 @JsonProperty("artist") String artist, @JsonProperty("image") Image image){

        this.id = id;
        this.name = name;
        this.artist = artist;
        this.image = image;

    }

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
}
