package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpSession;

/**
 * Owners are the users that make the rooms
 * They likely need a spotify premium account
 * MOST LIKELY WILL NEED TO ADD MORE TO THIS CLASS LIKE SPOTIFY CREDENTIALS/USER INFO LATER ON
 *
 */
public class Owner{

    //spotify username
    private String spotifyName;
    private String firstName;

    private HttpSession httpSession;

    /**
     * create owner object
     * @param spotifyName spotify username
     */
    public Owner(@JsonProperty("sname") String spotifyName, @JsonProperty("fname") String firstName) {
        this.spotifyName = spotifyName;
        this.firstName=firstName;
        this.httpSession = null;

    }

    /**
     * get the spotify name
     * @return spotify username
     */
    public String getSpotifyName(){

        return spotifyName;
    }

    public String getFirstName() {
        return firstName;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void updateSession(HttpSession session){

        HttpSession httpSession = session;
    }

    public String toString() {
        return "Owner{" +
                "spotifyName='" + spotifyName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

}
