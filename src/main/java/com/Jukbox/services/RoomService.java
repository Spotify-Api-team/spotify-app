package com.Jukbox.services;

import com.Jukbox.dao.RoomRepository;
import com.Jukbox.model.Member;
import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.utils.GeneratePassword;
import com.Jukbox.model.Track;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

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
     *  Gets a room(in repository) by its id number
     * @param id the id of the room
     * @return the room that is being looked for
     */
    public Room getRoomById(int id){

        try {
            return roomRepository.findById(id).get();
        }catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     *
     * @param room the room
     * @param id the members id
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
        try {
            return roomRepository.findByRoomPassword(roomPassword).get();
        }catch (NoSuchElementException e){

            return null;
        }
    }


    /**
     *  Adds a new member to a room
     *
     * @param member the member object to add
     * @param room the room to add to
     */
    public void updateRoom(Member member, Room room){


        member.setId(memberId);
        memberId++;

        room.addMember(member);
        roomRepository.save(room);

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
     * Get all the mebers of a room by  room id
     *
     * @param id id of the room
     * @return the ArrayList<Member> from repository
     */
    public ArrayList<Member> getMembers (int id){

        Room room= getRoomById(id);
        return room.getMembers();
    }

    /**
     * Adds a song to the to be added queue of a room
     * Only adds if there has not been a duplicate song within the last second
     * this is because that displayQueue.js will be enacting a pop off the TBA queue in that timeframe
     *
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
     * used for the  queueSongs.js which is called every second
     *
     * @param id id of the room
     * @return the track at the top
     */
    public Track getSong(int id){

        Room room = roomRepository.findById(id).get();

        Track track = room.popTBASong();
        roomRepository.save(room);
        return track;

    }

    /**
     * Adds a track to the current track
     *
     * @param track the track currenty playing
     * @param room the room to add to
     */
    public void addCurrentTrack(Track track, Room room) {
        room.addCurrentTrack(track);
        roomRepository.save(room);
    }

    /**
     * Gets the current playing track of a room
     *
     * @param id the id of the room
     * @return the current tack playing to the current song controller
     */
    public Track getCurrentTrack(int id){

        Room room = getRoomById(id);
        //Track function
        return room.getCurrentTrack();
    }

    /**
     * When a state change happens check id the song has changed and remove the song
     * from the top of the queue
     *
     * @param id room id
     * @param track the track
     */
    public void songOver(int id, Track track){

        Room room = getRoomById(id);

        if(room != null) {

            if (track.equals(room.getTop())) {

                room.popSong();
                roomRepository.save(room);

            }
        }

    }
}
