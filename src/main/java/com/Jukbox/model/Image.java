package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Image {


    private String url;

    private int width;

    private int height;


    public Image(@JsonProperty("url") String url, @JsonProperty("width") int width, @JsonProperty("height") int height) {
        this.url = url;
        this.width = width;
        this.height = height;
    }



}
