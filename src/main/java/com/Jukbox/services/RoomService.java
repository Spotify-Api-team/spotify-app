package com.Jukbox.services;

import com.Jukbox.dao.RoomRepository;
import com.Jukbox.model.Member;
import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.config.GeneratePassword;
import com.Jukbox.model.Track;
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

    /**
     *  Gets a room from the password
     * @param roomPassword String the password entered
     * @return
     */
    public Room findByPassword(String roomPassword){
        return roomRepository.findByRoomPassword(roomPassword).get();
    }


    /**
     *
     *
     * Adds a new member to the room
     *
     * @param member the member to add
     * @return the id of the room
     */
    public int updateRoom(Member member){
        //TODO this may need some reworking kinda a wierd way to do this
        Room temp= findByPassword(member.getMemberPassword());

        member.setId(memberId);
        memberId++;

        temp.addMember(member);
        roomRepository.save(temp);

        return temp.getId();
    }


    /**
     * Adds a spotify authorization token to a room
     *
     * @param token String the authorization token
     * @param id The id of the room
     */
    public void addSpotifyToken(String token, int id){

        Room room = roomRepository.findById(id).get();
        room.getOwner().setSpotifyToken(token);
        roomRepository.save(room);



    }


    /**
     * Adds a device id of the player to the room
     *
     * @param deviceId String device ID
     * @param id int the id number of the room
     */
    public void addDeviceId(String deviceId, int id){

        Room room = roomRepository.findById(id).get();
        room.setDeviceId(deviceId);
        roomRepository.save(room);

    }

    /**
     *
     * @param id
     * @return the ArrayList<Member> from repository
     */
    public ArrayList<Member> getMembers (int id){

        Room room= getRoomById(id);
        return room.getMembers();
    }

    /**
     * Adds a song to the to be added queue of a room
     * @param track the track to add
     * @param room the room to add the song to
     */
    public void addToTBAQueue(Track track, Room room){

        room.addToTBAQueue(track);
        roomRepository.save(room);

    }

    /**
     * Adds a song to the to be added queue of a room
     * @param track the track to add
     * @param room the room to add the song to
     */
    public void addToQueue(Track track, Room room){

        room.addToFinalQueue(track);
        roomRepository.save(room);

    }


    /**
     * gets the song from the top of the to be added queue
     * and removes the element from the tba queue
     *
     * @param id id of the room
     * @return the track at the top
     */
    public Track getSong(int id){

        Room room = roomRepository.findById(id).get();

        return room.popSong();

    }


}
