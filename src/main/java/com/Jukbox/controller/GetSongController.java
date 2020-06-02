package com.Jukbox.controller;

import com.Jukbox.model.Room;
import com.Jukbox.model.Track;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


@RestController
@RequestMapping("/getSong")
public class GetSongController {

    @Autowired
    RoomService roomService;

    @GetMapping
    public Track getSong( HttpSession session){

        return roomService.getSong((int)session.getAttribute("roomId"));

    }



}
