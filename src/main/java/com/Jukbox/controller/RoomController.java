package com.Jukbox.controller;

import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;

@RequestMapping("/createRoom")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public void createNewRoom(@RequestBody Owner owner){

        System.out.println("this is creating a new room");
        System.out.println(owner.getSpotifyName());
        System.out.println(owner.getFirstName());
        roomService.addRoom(owner);

    }

}
