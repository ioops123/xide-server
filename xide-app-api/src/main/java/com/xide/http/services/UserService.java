package com.xide.http.services;

import com.xide.http.dto.login.AccountInfoAndSession;

public interface UserService {
    AccountInfoAndSession login(String openid);
}
