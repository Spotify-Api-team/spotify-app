package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    private String name;

    public Member(@JsonProperty("name") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
