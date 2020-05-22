package com.Jukbox.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;

/**
 * Object that represents the room or lobby that users will join to listen to music
 * Owner creates the room and must have spotify premium account
 * Members join with the room password
 *
 */

@Document(collection = "Rooms")
public class Room {

    //list of members
    private ArrayList<Member> members;

    //id of the room
    @Id
    private int id;

    //owner of the room
    private Owner owner;

    //password to room
    private String roomPassword;

    private String deviceId;


    /**
     * Default Constructor
     */
    public Room() {

        this.owner = null;
        this.id = -1;
        members = new ArrayList<>();
        this.roomPassword = "none";

    }

    /**
     * Constructor for new room
     *
     * @param owner The owner of the room
     * @param id The room id
     */

    public Room(@JsonProperty("owner") Owner owner, @JsonProperty("id") int id, String roomPassword) {

        this.owner = owner;
        this.id = id;
        members = new ArrayList<>();
        this.roomPassword = roomPassword;
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
     * get the password for the room, to be used by members
     * @return roomPassword
     */
    public String getRoomPassword() {
        return roomPassword;
    }


    /**
     * sets a rooms deviceId
     * @param deviceId deviceid of owners playback
     */
    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    /**
     * gets a rooms device Id
     * @return the rooms device Id
     */
    public String getDeviceId() {
        return deviceId;
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
