package com.Jukbox.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testcontroller {
    @RequestMapping("/")
    public String start(){
        return "index";   //thymeleaf expects the file index to be in the templates folder
    }

}
