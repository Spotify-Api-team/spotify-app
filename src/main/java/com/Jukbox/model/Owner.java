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
    private String roomName;
    private String firstName;

    private HttpSession httpSession;

    /**
     * create owner object
     * @param roomName Room's anme
     */
    public Owner(@JsonProperty("roomName") String roomName, @JsonProperty("fname") String firstName) {
        this.roomName = roomName;
        this.firstName=firstName;
        this.httpSession = null;

    }

    public String getFirstName() {
        return firstName;
    }

    /**
     * get the spotify name
     * @return spotify username
     */


    public String getRoomName() {
        return roomName;
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void updateSession(HttpSession session){

        HttpSession httpSession = session;
    }

    public String toString() {
        return "Owner{" +
                "roomName='" + roomName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

}
