package com.Jukbox.controller;


import com.Jukbox.model.Member;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RequestMapping("/getMembers")
@RestController
public class GetMemberList {

    @Autowired
    private RoomService roomService;

    /**
     *
     * @param session
     * @return the ArrayList<Member> from repository
     */
    @GetMapping
    public ArrayList<Member> getMembers(HttpSession session){
        int id = (int) session.getAttribute("roomId");
        return roomService.getMembers(id);
    }
}

