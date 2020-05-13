package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.model.Owner;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

import com.Jukbox.model.Owner;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLOutput;

@RequestMapping("/joiningRoom")
@RestController
public class JoinRoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public void createNewRoom(@RequestBody Member member){

        System.out.println("this is creating a new room");
        roomService.updateRoom(member);
    }

}
