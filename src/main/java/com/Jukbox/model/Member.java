package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Member {

    private String name;
    private String memberPassword;
    private int id;

    public Member(){
        String name="none";
        String memberPassword="none";
        int id = 0;
    }

    public Member(@JsonProperty("fname") String name, @JsonProperty("memberPassword") String memberPassword, int id) {
        this.name = name;
        this.memberPassword = memberPassword;
        this.id=id;
    }


    public String getName() {
        return name;
    }

    public String getMemberPassword() {
        return memberPassword;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //if two members have the same id number then they are equal
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Member member = (Member) o;
        return id == member.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}


