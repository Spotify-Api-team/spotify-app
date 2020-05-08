package com.Jukbox.controller;

import com.Jukbox.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class testcontroller {

    @RequestMapping("/")
    public String start(){
        return "index";   //thymeleaf expects the file index to be in the templates folder
    }

    @RequestMapping("controller/test")
    @PostMapping
    public void addMember(Member member){

        System.out.println(member.getName());
        
    }

}
