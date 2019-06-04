package com.xide.http.dto.login;

import com.xide.jpa.entity.Users;
import com.xide.session.dto.Session;

public class AccountInfoAndSession {
    public final Users account;
    public final Session session;
    public final String bindId;
    public final Boolean firstLogin;

    public AccountInfoAndSession(Users account, Session session, String bindId, Boolean firstLogin) {
        this.account = account;
        this.session = session;
        this.bindId = bindId;
        this.firstLogin = firstLogin;
    }
}
