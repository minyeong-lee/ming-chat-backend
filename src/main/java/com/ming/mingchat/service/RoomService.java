package com.ming.mingchat.service;

import com.ming.mingchat.domain.ChatMessage;
import com.ming.mingchat.domain.Couple;
import com.ming.mingchat.domain.Room;
import com.ming.mingchat.repository.ChatRepository;
import com.ming.mingchat.repository.CoupleRepository;
import com.ming.mingchat.repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private final CoupleRepository coupleRepository;
    private RoomRepository roomRepository;
    private ChatRepository chatRepository;

    public RoomService(RoomRepository roomRepository, CoupleRepository coupleRepository, ChatRepository chatRepository) {
        this.roomRepository = roomRepository;
        this.coupleRepository = coupleRepository;
        this.chatRepository = chatRepository;
    }

    /* 방 생성 */
    @Transactional
    public Room createRoom(int creatorId) {
        Room room = new Room("방 이름없음");
        Room savedRoom = roomRepository.save(room);

        Couple couple = new Couple(creatorId, 0, savedRoom);
        couple.setStatus("ACTIVATED");
        coupleRepository.save(couple);

        // AI 인사 멘트 생성 및 저장
        ChatMessage welcomeMessage = new ChatMessage(savedRoom.getRoomCode(), -1,
    "안녕하세요, 저는 여러분의 대화를 도와드릴 AI 커플 상담사입니다.\n\n" +
            "이 공간은 갈등을 원만하게 해결하기 위한 목적의 대화방입니다.\n" +
            "두 분 모두 열린 마음으로 서로를 이해해보려는 노력을 해주셨으면 합니다.\n\n" +
            "이번 대화는 총 2개의 토픽으로 나뉘며, 각 토픽에 대해 약 20분간 자유롭게 대화하실 수 있습니다.\n" +
            "다만, 원활한 진행을 위해 해당 토픽과 관련 없는 주제는 잠시 미뤄주시면 좋겠습니다.\n\n" +
            "그럼 첫 번째 주제를 안내드릴게요."
        );
        System.out.println(">> AI 인사 멘트 저장 완료: " + welcomeMessage.getMessage());
        chatRepository.save(welcomeMessage);

        // 주제 안내 멘트 추가
        ChatMessage topicGuideMessage = new ChatMessage(savedRoom.getRoomCode(), -1,
                "[토픽 1: 상황에 대한 서로의 인식]\n\n" +
                        "최근 갈등이 생겼던 상황 중 하나를 선택해 이야기해주세요.\n" +
                        "서로가 그 상황을 어떻게 이해했는지 중심으로 말해주세요.\n\n" +
                        "※ 이 단계에서는 \"속상해\", \"서운했어\", \"화났어\" 같은 감정 표현은 잠시 미뤄주세요.\n" +
                        "지금은 그때 무슨 일이 있었고, 각자 어떻게 해석했는지를 이야기하는 단계입니다.\n\n" +
                        "예: \"그때 네가 늦었잖아.\" / \"나는 그게 고의는 아니었다고 생각했어.\"\n\n" +
                        "➡ 감정은 다음 단계에서 다룰게요. 지금은 상황 자체에 집중해주세요."
        );
        System.out.println(">> 주제 안내 멘트 저장 시도: " + topicGuideMessage.getMessage());
        chatRepository.save(topicGuideMessage);


        return savedRoom;
    }

    /* 기존 방 입장하기 */
    @Transactional
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
