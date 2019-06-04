package com.xide.http.services.impl;

import com.xide.http.dto.login.AccountInfoAndSession;
import com.xide.http.services.UserService;
import com.xide.jpa.entity.Users;
import com.xide.jpa.repository.UsersRepository;
import com.xide.session.SessionUtil;
import com.xide.session.dto.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Transactional
@Service("userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRepository usersRepository;

    @Resource(name = "appXideSessionUtil")
    private SessionUtil sessionUtil;

    @Override
    @Transactional
    public AccountInfoAndSession login(String openid) {
        Users user = usersRepository.findByOpenid(openid);
        if (user == null) {
            return new AccountInfoAndSession(null, null, user.getId(), true);
        }
        return createSessionByThirdPartyAccount(user, false);
    }

    private AccountInfoAndSession createSessionByThirdPartyAccount(Users user, Boolean firstLogin) {
        Session session = sessionUtil.createSession(user.getId());
        return new AccountInfoAndSession(user, session, user.getId(), firstLogin);
    }
}
