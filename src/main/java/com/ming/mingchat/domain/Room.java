package com.ming.mingchat.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rooms")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int id;

    @Column(name = "room_name")
    private String roomName;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "room_code", unique = true)
    private String roomCode;

    public Room() {
        this.createdAt = LocalDateTime.now();
    }

    public Room(String roomName) {
        this.roomName = roomName;
        this.createdAt = LocalDateTime.now();
        this.roomCode = generateRoomCode();
    }

    //랜덤 방 코드 생성
    private String generateRoomCode() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    public int getId() {
        return id;
    }

    public String getRoomName() {
        return roomName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", roomName='" + roomName + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
