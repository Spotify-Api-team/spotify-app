package com.Jukbox.controller;

import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLOutput;

@RequestMapping("/createRoom")
@RestController
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping
    public void createNewRoom(@RequestBody Owner owner, HttpSession session){

        session.setAttribute("name", owner.getRoomName());
        session.setAttribute("roomName", owner.getRoomName());
        System.out.println("this is creating a new room");
        /*System.out.println(owner.getSpotifyName());
        System.out.println(owner.getFirstName());*/
        int id = roomService.addRoom(owner);
        session.setAttribute("roomId", id);
    }

}
