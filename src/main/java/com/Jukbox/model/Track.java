package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Track {

    private String id;

    private String name;

    private ArrayList<String> artists;

    private Image image;

    public Track(@JsonProperty("id") String id, @JsonProperty("name") String name,
                 @JsonProperty("artists") ArrayList<String> artists, @JsonProperty("image") Image image){

        this.id = id;
        this.name = name;
        this.artists = artists;
        this.image = image;

    }



}
