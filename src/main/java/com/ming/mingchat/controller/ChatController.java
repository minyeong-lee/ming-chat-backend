package com.ming.mingchat.controller;

import com.ming.mingchat.domain.ChatMessage;
import com.ming.mingchat.dto.request.SendMessageRequest;
import com.ming.mingchat.service.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    /* 메시지 전송 */
    @PostMapping("/send")
    public ResponseEntity<ChatMessage> sendMessage(@RequestBody SendMessageRequest request) {
        ChatMessage chatMessage = chatService.saveMessage(request.getRoomCode(), request.getSenderId(), request.getMessage());
        return ResponseEntity.ok(chatMessage);
    }

    /* 방의 모든 메시지 조회 */
    @GetMapping("/{roomCode}")
    public ResponseEntity<List<ChatMessage>> getMessages(@PathVariable String roomCode) {
        List<ChatMessage> messages = chatService.getMessagesByRoomCode(roomCode);
        return ResponseEntity.ok(messages);
    }
}
