package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Member {

    private String name;
    private String memberPassword;


    public Member(@JsonProperty("fname") String name, @JsonProperty("memberPassword") String memberPassword) {
        this.name = name;
        this.memberPassword = memberPassword;
    }


    public String getName() {
        return name;
    }

    public String getMemberPassword() {
        return memberPassword;
    }
}


