package com.xide.http.controllers;

import com.xide.http.dto.login.AccountAndSessionDTO;
import com.xide.http.dto.login.AccountInfoAndSession;
import com.xide.http.services.UserService;
import com.xide.jpa.repository.BooksRepository;
import com.xide.pages.JsonResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/token")
public class AccessTokenController {

    @Autowired
    private UserService userService;

    /**
     * 三方登录
     * @param type 三方登录类型 weixin-微信，qq-QQ
     * @param openid 三方平台唯一识别id
     * @param nickname 三方平台昵称
     * @return
     */
    @RequestMapping(value = "/thirdParty", method = RequestMethod.GET)
    public JsonResponseEntity<AccountAndSessionDTO> thirdPartySignin(@RequestParam String type,
                                                                     @RequestParam String openid,
                                                                     @RequestParam String nickname) {
        AccountInfoAndSession result = userService.login(openid);
        AccountAndSessionDTO dto = new AccountAndSessionDTO(result);
        JsonResponseEntity<AccountAndSessionDTO> response = new JsonResponseEntity<>();
        response.setData(dto);
        response.setMsg("登录成功");
        return response;
    }
}