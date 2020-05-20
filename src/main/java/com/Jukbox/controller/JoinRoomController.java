package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/joiningRoom")
@RestController
public class JoinRoomController {
    @Autowired
    private RoomService roomService;

    @PostMapping
    public void joinRoom(@RequestBody Member member, HttpSession session){
        session.setAttribute("memberPassword", member.getMemberPassword() );
        //session.setAttribute("whichmember",)

        int roomId= roomService.updateRoom(member);
        session.setAttribute("roomId", roomId);
        session.setAttribute("memberId",member.getId());
    }

}
