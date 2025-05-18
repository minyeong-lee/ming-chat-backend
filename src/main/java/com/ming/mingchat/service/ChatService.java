package com.ming.mingchat.service;

import com.ming.mingchat.domain.ChatMessage;
import com.ming.mingchat.repository.ChatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatRepository chatRepository;

    public ChatService(ChatRepository chatRepository) {
        this.chatRepository = chatRepository;
    }

    /* 채팅 메시지 저장 */
    public ChatMessage saveMessage(String roomCode, int senderId, String message) {
        ChatMessage chatMessage = new ChatMessage(roomCode, senderId, message);
        return chatRepository.save(chatMessage);
    }

    /* 특정 방의 메시지 조회(최신순으로) */
    public List<ChatMessage> getMessagesByRoomCode(String roomCode) {
        return chatRepository.findByRoomCodeOrderByTimestampAsc(roomCode);
    }
}
