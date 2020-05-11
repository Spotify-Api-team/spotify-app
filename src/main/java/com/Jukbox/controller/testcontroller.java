package com.Jukbox.controller;

import com.Jukbox.model.Member;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@RequestMapping("controller/test")
@RestController
public class testcontroller {

    /**@RequestMapping("/")
    public String start(){
        return "index";   //thymeleaf expects the file index to be in the templates folder
    }*/


    @PostMapping
    public void addMember(@RequestBody Member member){

        System.out.println("testing...");
        System.out.println(member.getName());

    }

    @GetMapping
    //possible call room service when trying to create a room view, put in a unique code
    //
    public String getMember(){

        return "working";

    }

}
