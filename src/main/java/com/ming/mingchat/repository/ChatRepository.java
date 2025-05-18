package com.ming.mingchat.repository;

import com.ming.mingchat.domain.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<ChatMessage, Integer> {

    List<ChatMessage> findByRoomCodeOrderByTimestampAsc(String roomCode);
}
