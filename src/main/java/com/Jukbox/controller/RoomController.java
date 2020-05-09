package com.Jukbox.controller;

import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/createRoom")
@RestController
public class RoomController {


    @Autowired
    private RoomService roomService;

    @PostMapping
    public void createNewRoom(@RequestBody Owner owner){

        roomService.addRoom(owner);

    }


}
