package com.Jukbox.controller;


import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/token")
@RestController
public class TokenController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public String getToken(HttpSession session){

        //System.out.println("getting token");
        Room temp = roomService.getRoomById((int)session.getAttribute("roomId"));
        String token = temp.getOwner().getSpotifyToken();
        //System.out.println(token);
        return token;
    }


    @PostMapping
    public void setToken(@RequestBody String token, HttpSession session){

        //System.out.println("postToken");
        int id = (int)session.getAttribute("roomId");
        roomService.addSpotifyToken(token, id);
    }


}
