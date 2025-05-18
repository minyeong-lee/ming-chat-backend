package com.ming.mingchat.dto.response;

public class MemberLoginResponse {
    private int id;
    private String email;
    private String nickname;

    public MemberLoginResponse(int id, String email, String nickname) {
        this.id = id;
        this.email = email;
        this.nickname = nickname;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
