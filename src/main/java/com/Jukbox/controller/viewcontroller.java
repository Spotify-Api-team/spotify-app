package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Jukbox.services.RoomService;
import com.Jukbox.model.Room;

import java.util.Optional;


@Controller
public class viewcontroller {
    
    @Autowired 
    private RoomService roomService;

    @RequestMapping("/") public String start(){
    return "index";   //thymeleaf expects the file index to be in the templates folder
    }

    /**
     *
     * @param model
     * @return //return index found in templates
     */
    
    @RequestMapping("/Room")
    public String RoomView(Model model){

        Room temp = roomService.getRoomById(1);

        model.addAttribute("ownerName",temp.getOwner().getFirstName());
        model.addAttribute("ownerSname",temp.getOwner().getSpotifyName());

        System.out.println(temp.getOwner().getFirstName());
        System.out.println(temp.getOwner().getSpotifyName());

        return "index2";   //thymeleaf expects the file index to be in the templates folder
    }





}
