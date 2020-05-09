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

    private static int id;

    public RoomService(){

        rooms = new ArrayList<>();
    }

    public void addRoom(Owner owner){
        rooms.add(new Room(owner, id));
        id++;
    }


}
