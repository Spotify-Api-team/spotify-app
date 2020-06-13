package com.Jukbox.controller;

import com.Jukbox.model.Member;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Jukbox.model.Room;

import javax.servlet.http.HttpSession;


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
    public String OwnerRoomView(Model model, HttpSession session){

        //return "spotifyTestSDK";


        Room temp = roomService.getRoomById((int)session.getAttribute("roomId"));


        model.addAttribute("ownerName",temp.getOwner().getFirstName());
        model.addAttribute("roomName",temp.getOwner().getRoomName());

        //room password
        model.addAttribute("roomPassword", temp.getRoomPassword());

        System.out.println(temp.getRoomPassword());

        /*System.out.println(temp.getOwner().getFirstName());
        System.out.println(temp.getOwner().getSpotifyName());*/

        return "ownerpage";   //thymeleaf expects the file index to be in the templates folder
    }

    @RequestMapping("/JoinRoom")
    public String JoinRoomView(Model model, HttpSession session){


        Room temp = roomService.getRoomById( (int)session.getAttribute("roomId"));
        Member tempMem = roomService.getMemberByIdandRoom(temp,(int)session.getAttribute("memberId"));

        System.out.println(tempMem.getName());

        model.addAttribute("ownerName",temp.getOwner().getFirstName());
        model.addAttribute("roomName",temp.getOwner().getRoomName());
        model.addAttribute("roomPassword", temp.getRoomPassword());
        model.addAttribute("memberName", tempMem.getName());

        return "memberpage";   //thymeleaf expects the file index to be in the templates folder
    }

    @RequestMapping("/Search")
    public String Search(Model model, HttpSession session){
        return "Search";
    }



}
