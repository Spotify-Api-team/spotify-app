package com.Jukbox.controller;

import com.Jukbox.model.Room;
import com.Jukbox.model.Track;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;


@RequestMapping("/currentSong")
@RestController
public class CurrentSongController {

    @Autowired
    RoomService roomService;

    @PostMapping
    public void postCurrentSongToRoom(@RequestBody Track track, HttpSession session){

        Room room = roomService.getRoomById((int)session.getAttribute("roomId"));
        roomService.addCurrentTrack(track, room);

    }
    @GetMapping
    public Track getCurrentSongToRoom(HttpSession session){
        return roomService.getCurrentTrack((int)session.getAttribute("roomId"));
    }
}
