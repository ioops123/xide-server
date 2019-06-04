package com.xide.http.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.xide.jpa.entity.Users;
import com.xide.utils.DateFormatter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {
    public String id;
    public String nickname;
    public String avatar;
    public String gender;
    public String birthday;

    public AccountDTO() {

    }

    public AccountDTO(Users account) {
        this.id = account.getId();
        this.nickname = account.getNickname();
        this.avatar = account.getAvatar();
        this.gender = account.getGender();
        this.birthday = DateFormatter.dateFormat(account.getBirthday());
    }
}
