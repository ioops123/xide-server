package com.xide.http.dto.login;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountAndSessionDTO {
    @JsonProperty("info")
    public AccountDTO account;
    @JsonUnwrapped(prefix = "")
    public SessionDTO session;
    @JsonProperty("bind_id")
    public String bindId;
    @JsonProperty("first_login")
    public Boolean firstLogin;

    public AccountAndSessionDTO() {

    }

    public AccountAndSessionDTO(AccountInfoAndSession accountInfoAndSession) {
        if (accountInfoAndSession.account != null) {
            this.account = new AccountDTO(accountInfoAndSession.account);
        }
        if (accountInfoAndSession.session != null) {
            this.session = new SessionDTO(accountInfoAndSession.session);
        }
        this.firstLogin = accountInfoAndSession.firstLogin;
    }
}
