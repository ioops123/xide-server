package com.xide.http.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xide.session.dto.Session;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SessionDTO {
    public String uid;
    public String token;
    public String key;

    public SessionDTO() {

    }

    public SessionDTO(String uid, String token, String key) {
        this.uid = uid;
        this.token = token;
        this.key = key;
    }

    public SessionDTO(Session session) {
        this.uid = session.getUserId();
        this.token = session.getAccessToken();
        this.key = session.getSecret();
    }
}
