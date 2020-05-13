package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    private String name;
    private String sessionID;

    public Member(@JsonProperty("fname") String name,@JsonProperty("sessionID")String sessionID) {
        this.name = name;
        this.sessionID=sessionID;
    }

    public String getName() {
        return name;
    }

    public String getSessionID() {
        return sessionID;
    }
}
