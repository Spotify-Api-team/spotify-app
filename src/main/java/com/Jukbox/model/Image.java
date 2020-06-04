package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Class that represents images for spotify tracks
 *
 */
public class Image {

    private String url;

    private int width;

    private int height;


    public Image(@JsonProperty("height") int height, @JsonProperty("url") String url, @JsonProperty("width") int width) {
        this.url = url;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
