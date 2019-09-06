package com.cx.basecode.system.controller;

import com.cx.basecode.common.controller.BaseController;
import com.cx.basecode.common.exception.MyException;
import com.cx.basecode.system.entity.User;
import com.cx.basecode.system.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author: alex
 * @date: 2019/9/6
 */
@Slf4j
@RestController
public class LoginController extends BaseController {
    @Autowired
    private IUserService userService;

    @PostMapping("register")
    public MyException register(
            @NotBlank(message = "{requred}") String userName,
            @NotBlank(message = "{requered}") String passWord) {
//        User user = userService.findByName(userName);
//        if (user != null) {
//            return new MyException("该用户名已存在");
//        }

        return null;
    }

}
