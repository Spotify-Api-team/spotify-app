package com.Jukbox.services;

import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/**
 * This will keep track of all the rooms
 * create new rooms
 * Service between controller and model
 *
 */

@Repository
public class RoomService {

    private ArrayList<Room> rooms;

    private static int id = 1;

    /**
     * constructor of room service
     */
    public RoomService(){

        rooms = new ArrayList<>();
    }

    /**
     * Creates a new room
     * @param owner the creator of the room
     */
    public void addRoom(Owner owner){
        rooms.add(new Room(owner, id));
        id++;
    }

    /**
     *  Gets a room by its id number
     * @param id the id of the room
     * @return the room that is being looked for
     */
    public Room getRoomById(int id){

        Room temp = new Room(null, id);
        return rooms.get(rooms.indexOf(temp));

    }

    /**
     * Get all the rooms available
     * @return arraylist of all rooms
     */
    public ArrayList<Room> getRooms(){

        return rooms;
    }


}
