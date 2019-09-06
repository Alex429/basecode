package com.cx.basecode.system.controller;

import com.cx.basecode.common.entity.BaseResponse;
import com.cx.basecode.system.entity.User;
import com.cx.basecode.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: alex
 * @date: 2019/9/6
 */
@Slf4j
@RestController
public class LoginController {
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public BaseResponse register() {
        User u = userService.findByName("alex");
        log.info(u.toString());
        return null;
    }
}
