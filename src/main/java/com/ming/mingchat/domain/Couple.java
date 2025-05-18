package com.ming.mingchat.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "couples")
public class Couple {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couple_id")
    private int id;

    @Column(name = "member_a_id")
    private int memberAId;

    @Column(name = "member_b_id")
    private int memberBId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    //커플 활성화 여부
    @Column(name = "couple_status")
    private String status = "ACTIVE";

    public Couple() {

    }

    public Couple(int memberAId, int memberBId, Room room) {
        this.memberAId = memberAId;
        this.memberBId = memberBId;
        this.room = room;
    }

    public int getId() {
        return id;
    }

    public int getMemberAId() {
        return memberAId;
    }

    public int getMemberBId() {
        return memberBId;
    }

    public Room getRoom() {
        return room;
    }

    public void setMemberAId(int memberAId) {
        this.memberAId = memberAId;
    }

    public void setMemberBId(int memberBId) {
        this.memberBId = memberBId;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Couple{" +
                "id=" + id +
                ", memberAId=" + memberAId +
                ", memberBId=" + memberBId +
                ", roomId=" + (room != null ? room.getId() : null) +
                '}';
    }
}
