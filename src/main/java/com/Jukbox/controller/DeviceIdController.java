package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.model.Room;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/deviceId")
@RestController
public class DeviceIdController {


    @Autowired
    private RoomService roomService;

    @GetMapping
    public String getDeviceId(HttpSession session){

        Room temp = roomService.getRoomById((int)session.getAttribute("roomId"));
        String deviceId = temp.getDeviceId();
        return deviceId;
    }


    @PostMapping
    public void setDeviceID(@RequestBody String deviceId, HttpSession session){

        int id = (int)session.getAttribute("roomId");
        roomService.addDeviceId(deviceId, id);
    }
}
