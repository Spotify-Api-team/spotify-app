package com.Jukbox.services;

import com.Jukbox.dao.RoomRepository;
import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * This will keep track of all the rooms
 * create new rooms
 * Service between controller and model
 *
 */

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    private static int id = 1;

    /**
     * constructor of room service
     */
    public RoomService(){


    }

    /**
     * Creates a new room
     * @param owner the creator of the room
     */
    public void addRoom(Owner owner){
        roomRepository.save(new Room(owner, id));
        id++;
    }

    /**
     *  Gets a room by its id number
     * @param id the id of the room
     * @return the room that is being looked for
     */
    public Optional<Room> getRoomById(int id){

        return roomRepository.findById(id);

    }

    /**
     * Get all the rooms available
     * @return arraylist of all rooms
     */
    public List<Room> getRooms(){

        return roomRepository.findAll();
    }


}
