package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    private String name;
    private int roomId;

    public Member(@JsonProperty("fname") String name,@JsonProperty("roomId")int roomId) {
        this.name = name;
        this.roomId= roomId;
    }


    public String getName() {
        return name;
    }

    public int getRoomId() {
        return roomId;
    }
}
