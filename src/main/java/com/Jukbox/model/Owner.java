package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Owners are the users that make the rooms
 * They likely need a spotify premium account
 * MOST LIKELY WILL NEED TO ADD MORE TO THIS CLASS LIKE SPOTIFY CREDENTIALS/USER INFO LATER ON
 *
 */
public class Owner{

    //spotify username
    private String spotifyName;

    /**
     * create owner object
     * @param spotifyName spotify username
     */
    public Owner(@JsonProperty("name") String spotifyName) {
        this.spotifyName = spotifyName;
    }

    /**
     * get the spotify name
     * @return spotify username
     */
    public String getSpotifyName(){

        return spotifyName;
    }

}
