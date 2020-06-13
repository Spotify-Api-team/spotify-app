package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity joinRoom(@RequestBody Member member, HttpSession session){

        Room room = roomService.findByPassword(member.getMemberPassword());

        if(room != null) {
            int roomId = room.getId();
            roomService.updateRoom(member, room);

            //add session attributes
            session.setAttribute("roomId", roomId);
            session.setAttribute("memberId", member.getId());
            session.setAttribute("memberPassword", member.getMemberPassword());

            return ResponseEntity.ok().build();
        }
        else
            return ResponseEntity.badRequest().build();
    }

}
