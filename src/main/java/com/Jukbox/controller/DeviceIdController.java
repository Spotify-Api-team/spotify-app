package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RequestMapping("/getId")
@RestController
public class DeviceIdController {
    @PostMapping
    public void recieveId{

    }
}
