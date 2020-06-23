package com.Jukbox.model;

import java.util.ArrayList;

public class SongQueue {

    private ArrayList<Track> queue;

    /**
     * Default constructor
     *
     */
    public SongQueue(){

        queue = new ArrayList<Track>();

    }

    /**
     * Gets and removes the top of the queue
     *
     * @return Track at the top of the queue
     */
    public Track pop(){

        if(!queue.isEmpty()) {
            Track track = queue.get(0);
            queue.remove(0);
            return track;
        }else
            return null;

    }

    /**
     * Gets the top of the queue without removing it
     *
     * @return
     */
    public Track top(){

        if(!queue.isEmpty()) {
            Track track = queue.get(0);
            return track;
        }else
            return null;

    }

    /**
     * Gets the last element in the queue
     *
     * @return Track the last track
     */
    public Track getLast(){

        if(!queue.isEmpty()){
            return queue.get(queue.size()-1);
        }
        else
            return null;

    }

    /**
     * return list representation of this queue
     *
     * @return ArrayList representation of the queue
     */
    public ArrayList<Track> getQueue() {
        return queue;
    }

    /**
     * Add a track to the queue
     *
     * @param track Track to add
     */
    public void addTrack(Track track){

        queue.add(track);

    }

    /**
     * Check if queue is empty
     *
     * @return boolean whether queue is empty
     */
    public boolean isEmpty(){
        return queue.isEmpty();

    }
}
