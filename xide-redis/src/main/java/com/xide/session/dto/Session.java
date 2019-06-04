package com.xide.session.dto;

import lombok.Data;

import java.util.Map;

@Data
public final class Session {

    private String accessToken;
    private String userId;
    private String secret;
    private Boolean isValid;
    private Object user;

    public Session() {
    }

    public Session(String accessToken, Map<String, String> objectMap) {
        this.accessToken = accessToken;
        this.userId = objectMap.get(Key.id.name());
        this.secret = objectMap.get(Key.secret.name());
        this.isValid = "1".equals(objectMap.get(Key.valid.name()));
    }

    public Boolean isGuest() {
        return userId == null;
    }

    public enum Key {
        id,
        secret,
        valid
    }
}