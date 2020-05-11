package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Object that represents the room or lobby that users will join to listen to music
 * Owner creates the room and must have spotify account
 * Members join with the room id
 *
 */

public class Room {

    //list of members
    private ArrayList<Member> members;

    //id of the room
    private int id;

    //owner of the room
    private Owner owner;

    /**
     * Constructor for new room
     *
     * @param owner The owner of the room
     * @param id The room id
     */

    public Room(@JsonProperty("owner") Owner owner, @JsonProperty("id") int id) {

        this.owner = owner;
        this.id = id;
        members = new ArrayList<>();

    }

    /**
     * add new member
     * @param member the member to be added
     */
    public void addMember(Member member){

        this.members.add(member);
    }

    /**
     * get all members
     * @return arraylist of members
     */
    public ArrayList<Member> getMembers(){

        return this.members;
    }

    /**
     * get room id
     * @return room id
     */
    public int getId(){
        return this.id;
    }

    /**
     * get owner of the room
     * @return owner
     */
    public Owner getOwner() {
        return this.owner;
    }


    /**
     * checks if two rooms are equal if the ids are the same
     * @param o object(room)
     * @return true/false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return id == room.id;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", owner=" + owner +
                '}';
    }
}
