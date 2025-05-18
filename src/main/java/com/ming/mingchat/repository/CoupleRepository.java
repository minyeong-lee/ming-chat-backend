package com.ming.mingchat.repository;

import com.ming.mingchat.domain.Couple;
import com.ming.mingchat.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CoupleRepository extends JpaRepository<Couple, Integer> {

    Optional<Couple> findByRoom(Room room);
}
