package com.Jukbox.controller;

import com.Jukbox.model.Room;
import com.Jukbox.model.Track;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RequestMapping("/confirmedSong")
@RestController
public class ConfirmedSongController {

    @Autowired
    RoomService roomService;

    @PostMapping
    public void addSongToQueue(@RequestBody Track track, HttpSession session){
        Room room = roomService.getRoomById((int)session.getAttribute("roomId"));
        roomService.addToQueue(track, room);
    }


    @GetMapping
    public ArrayList<Track> getQueue(HttpSession session){
        return roomService.getRoomById((int)session.getAttribute("roomId")).getQueue();
    }
}
