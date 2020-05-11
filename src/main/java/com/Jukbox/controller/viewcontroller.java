package com.Jukbox.controller;

import com.Jukbox.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
public class viewcontroller {

    @RequestMapping("/") public String start(){
    return "index";   //thymeleaf expects the file index to be in the templates folder
    }
    @RequestMapping("/Room") public String Startagain(){
        return "index2";   //thymeleaf expects the file index to be in the templates folder
    }
}
