package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.servlet.http.HttpSession;

/**
 * Owners are the users that make the rooms
 * They need a spotify premium account
 *
 */
public class Owner{

    private String roomName;
    private String firstName;
    private HttpSession httpSession;
    private String spotifyToken;

    /**
     * create owner object
     * @param roomName Room's anme
     */
    public Owner(@JsonProperty("roomName") String roomName, @JsonProperty("fname") String firstName) {
        this.roomName = roomName;
        this.firstName=firstName;
        this.httpSession = null;

    }

    /**
     * Add the spotify token to the Owner
     * @param token String spotify token
     */
    public void setSpotifyToken(String token){

        this.spotifyToken = token;
    }

    /**
     * get name of owner
     * @return name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * get the room name the owner created
     * @return String name of the room
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * Get the session of the owner
     * @return Httpsession from owner
     */
    public HttpSession getHttpSession() {
        return httpSession;
    }

    /**
     * Get token of the owner
     * @return owners spotify token
     */
    public String getSpotifyToken() {
        return spotifyToken;
    }

    /**
     * udate the session
     *
     * @param session the sessiom change to
     */
    public void updateSession(HttpSession session){

        HttpSession httpSession = session;
    }


    /**
     * to string method
     * @return
     */
    public String toString() {
        return "Owner{" +
                "roomName='" + roomName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

}
