package com.ming.mingchat.dto.response;

public class MemberLoginResponse {
    private String email;
    private String nickname;

    public MemberLoginResponse(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }
}
