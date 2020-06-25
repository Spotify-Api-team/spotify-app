package com.Jukbox.controller;

import com.Jukbox.model.Room;
import com.Jukbox.model.SongQueue;
import com.Jukbox.model.Track;
import com.Jukbox.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@RequestMapping("/getFullSongList")
@RestController
public class getFullSongList {

    @Autowired
    RoomService roomService;

    @GetMapping
    @ResponseBody
    public String [] getSongToAdd(HttpSession session){

        Room room =  roomService.getRoomById((int)session.getAttribute("roomId"));

        SongQueue queue = room.getFullQueue();

        ArrayList<Track> list = queue.getQueue();



        String [] fullList = new String[list.size()];
        for(int i =0 ;i<list.size();i++) {

            //fullList.add("spotify:track:" + list.get(i).getId());
            fullList[i]=(list.get(i).getId());
        }

        return fullList;

    }

}
