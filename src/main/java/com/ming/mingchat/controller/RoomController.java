package com.ming.mingchat.controller;

import com.ming.mingchat.domain.Room;
import com.ming.mingchat.dto.request.RoomCreateRequest;
import com.ming.mingchat.dto.request.RoomEnterRequest;
import com.ming.mingchat.service.RoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /* 방 생성 */
    @PostMapping("/create")
    public ResponseEntity<String> createRoom(@RequestBody RoomCreateRequest request) {
        Room room = roomService.createRoom(request.getCreatorId());
        return ResponseEntity.ok(room.getRoomCode());
    }

    /* 기존에 생성된 방 입장하기 */
    @PostMapping("/enter")
    public ResponseEntity<String> enterRoom(@RequestBody RoomEnterRequest request) {
        boolean success = roomService.enterRoom(request.getRoomCode(), request.getUserId());
        if (success) {
            return ResponseEntity.ok("입장 성공");
        } else {
            return ResponseEntity.badRequest().body("입장 실패: 이미 입장한 커플입니다.");
        }
    }
}
