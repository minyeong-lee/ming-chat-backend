package com.ming.mingchat.service;

import com.ming.mingchat.domain.Couple;
import com.ming.mingchat.domain.Room;
import com.ming.mingchat.repository.CoupleRepository;
import com.ming.mingchat.repository.RoomRepository;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final CoupleRepository coupleRepository;
    private RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository, CoupleRepository coupleRepository) {
        this.roomRepository = roomRepository;
        this.coupleRepository = coupleRepository;
    }

    /* 방 생성 */
    public Room createRoom(int creatorId) {
        Room room = new Room("방 이름없음");
        Room savedRoom = roomRepository.save(room);

        Couple couple = new Couple(creatorId, 0, savedRoom);
        couple.setStatus("ACTIVATED");
        coupleRepository.save(couple);

        return savedRoom;
    }

    /* 기존 방 입장하기 */
    public boolean enterRoom(String roomCode, int userId) {
        //유효성 검증
        Room room = roomRepository.findByRoomCode(roomCode)
                .orElseThrow(() -> new IllegalArgumentException("방을 찾을 수 없습니다."));

        Couple couple = coupleRepository.findByRoom(room)
                .orElseThrow(() -> new IllegalArgumentException("커플을 찾을 수 없습니다."));

        if (couple.getMemberBId() != 0) { //기존 초기값 0 으로 되어 있음
            return false; //누군가 이미 입장한 경우
        }

        //기존 등록된 아이디의 커플 찾아서 상대 아이디 등록하기
        couple.setMemberBId(userId);

        coupleRepository.save(couple);

        //결과 반환
        return true;
    }
}
