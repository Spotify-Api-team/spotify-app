package com.Jukbox.dao;

import com.Jukbox.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface RoomRepository extends MongoRepository<Room, Integer> {
    Optional<Room> findByRoomPassword(String roomPassword);

}
