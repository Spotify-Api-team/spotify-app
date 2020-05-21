package com.Jukbox.services;

import com.Jukbox.dao.RoomRepository;
import com.Jukbox.model.Member;
import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.config.GeneratePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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

    private static int roomId = 1;

    private static int memberId = 1;

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

        //create Password
        String roomPassword;
        roomPassword=GeneratePassword.createPassword();

        //verify password is unique

        roomRepository.save(new Room(owner,roomId,roomPassword));
        roomId++;
        return roomId - 1;
    }

    /**
     *  Gets a room by its id number
     * @param id the id of the room
     * @return the room that is being looked for
     */
    public Room getRoomById(int id){

        return roomRepository.findById(id).get();
    }

    /*
    public Member getMemberById(int id){
        Member temp= new Member();
        temp.setId(id);

        return roomRepository.findMembersById(id).get();
    }
    */

    /**
     *
     * @param room
     * @param id
     * @return the correct memeber inside of the correct roomId
     */
    public Member getMemberByIdandRoom(Room room, int id){
        ArrayList<Member> list = room.getMembers();
        Member temp = new Member();
        temp.setId(id);
        return list.get(list.indexOf(temp));
    }

    /**
     * Get all the rooms available
     * @return arraylist of all rooms
     */
    public List<Room> getRooms(){
        return roomRepository.findAll();

    }

    public Room findByPassword(String roomPassword){
        return roomRepository.findByRoomPassword(roomPassword).get();
    }


    public int updateRoom(Member member){
        //where is findID?
        //data base query

        //check the data base for the memember password that the memeber inputted
        Room temp= findByPassword(member.getMemberPassword());

        member.setId(memberId);
        memberId++;

        temp.addMember(member);
        roomRepository.save(temp);

        return temp.getId();
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


    public void addSpotifyToken(String token, int id){

        Room room = roomRepository.findById(id).get();
        room.getOwner().setSpotifyToken(token);
        roomRepository.save(room);



    }



}
