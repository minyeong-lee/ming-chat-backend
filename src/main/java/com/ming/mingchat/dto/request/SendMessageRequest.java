package com.ming.mingchat.dto.request;

public class SendMessageRequest {
    private String roomCode;
    private int senderId;
    private String message;

    public SendMessageRequest(String roomCode, int senderId, String message) {
        this.roomCode = roomCode;
        this.senderId = senderId;
        this.message = message;
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
}
