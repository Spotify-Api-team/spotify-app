package com.Jukbox.controller;

import com.Jukbox.model.Room;
import com.Jukbox.model.Track;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/getSong")
@RestController
public class GetSongController {

    @Autowired
    RoomService roomService;

    @GetMapping
    @ResponseBody
    public Track getSongToAdd(HttpSession session){

        Track track =  roomService.getSong((int)session.getAttribute("roomId"));

        return track;

    }

}
