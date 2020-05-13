package com.Jukbox.services;

import com.Jukbox.dao.RoomRepository;
import com.Jukbox.model.Member;
import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

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
    public int addRoom(Owner owner){
        roomRepository.save(new Room(owner, id));
        id++;
        return id - 1;
    }

    /**
     *  Gets a room by its id number
     * @param id the id of the room
     * @return the room that is being looked for
     */
    public Room getRoomById(int id){

        return roomRepository.findById(id).get();
    }

    /**
     * Get all the rooms available
     * @return arraylist of all rooms
     */
    public List<Room> getRooms(){

        return roomRepository.findAll();
    }
    public void updateRoom(Member member){
        Room temp= roomRepository.findById(member.getRoomId() ).get();
        temp.addMember(member);
        roomRepository.save(temp);
    }

    public Room getRoomFromSession(HttpSession httpSession){
        List<Room> rooms = roomRepository.findAll();
        for(Room i: rooms){

            System.out.println(i.getOwner().getHttpSession());

            if (i.getOwner().getHttpSession() == httpSession)
                return i;
        }

        return null;
    }



}
