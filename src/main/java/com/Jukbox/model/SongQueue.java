package com.Jukbox.model;

import java.util.ArrayList;

public class SongQueue {

    private ArrayList<Track> queue;

    public SongQueue(){

        queue = new ArrayList<Track>();

    }

    public Track pop(){

        if(!queue.isEmpty()) {
            Track track = queue.get(0);
            queue.remove(0);
            return track;
        }else
            return null;

    }


    public Track getLast(){

        if(!queue.isEmpty()){
            return queue.get(queue.size()-1);
        }
        else
            return null;

    }

    public ArrayList<Track> getQueue() {
        return queue;
    }

    public void addTrack(Track track){

        queue.add(track);

    }

    public boolean isEmpty(){
        return queue.isEmpty();

    }
}
