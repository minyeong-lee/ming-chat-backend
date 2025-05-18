package com.ming.mingchat.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_messages")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "room_code")
    private String roomCode;

    @Column(name = "sender_id")
    private int senderId;

    @Column(name = "message", length = 1000)
    private String message;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    public ChatMessage() {
    }

    public ChatMessage(String roomCode, int senderId, String message) {
        this.roomCode = roomCode;
        this.senderId = senderId;
        this.message = message;
        this.timestamp = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public int getSenderId() {
        return senderId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
