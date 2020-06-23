package com.Jukbox.controller;

import com.Jukbox.model.Room;
import com.Jukbox.model.Track;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RequestMapping("/songOver")
@RestController
public class SongOverController {

    @Autowired
    RoomService roomService;

    @PostMapping
    public void popSong(@RequestBody Track track, HttpSession session){

        roomService.songOver((int)session.getAttribute("roomId"), track);

    }



}