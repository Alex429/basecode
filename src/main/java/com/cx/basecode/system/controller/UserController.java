package com.cx.basecode.system.controller;

import com.cx.basecode.common.annotation.Log;
import com.cx.basecode.common.utils.MyUtil;
import com.cx.basecode.common.entity.MyConstant;
import com.cx.basecode.common.controller.BaseController;
import com.cx.basecode.common.entity.BaseResponse;
import com.cx.basecode.common.entity.QueryRequest;
import com.cx.basecode.common.exception.MyException;
import com.cx.basecode.system.entity.User;
import com.cx.basecode.system.service.IUserService;
import com.wuwenze.poi.ExcelKit;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * Controller
 *
 * @author alex
 * @date 2019-09-06 15:34:53
 */
@Slf4j
@Validated
@Controller
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @GetMapping(MyConstant.VIEW_PREFIX + "user")
    public String userIndex() {
        return MyUtil.view("user/user");
    }

    @GetMapping("user")
    @ResponseBody
    @RequiresPermissions("user:list")
    public BaseResponse getAllUsers(User user) {
        return new BaseResponse().success().data(userService.findUsers(user));
    }

    @GetMapping("user/list")
    @ResponseBody
    @RequiresPermissions("user:list")
    public BaseResponse userList(QueryRequest request, User user) {
        Map<String, Object> dataTable = getDataTable(this.userService.findUsers(request, user));
        return new BaseResponse().success().data(dataTable);
    }

    @Log("新增User")
    @PostMapping("user")
    @ResponseBody
    @RequiresPermissions("user:add")
    public BaseResponse addUser(@Valid User user) throws MyException {
        try {
            this.userService.createUser(user);
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "新增User失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    @Log("删除User")
    @GetMapping("user/delete")
    @ResponseBody
    @RequiresPermissions("user:delete")
    public BaseResponse deleteUser(User user) throws MyException {
        try {
            this.userService.deleteUser(user);
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "删除User失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    @Log("修改User")
    @PostMapping("user/update")
    @ResponseBody
    @RequiresPermissions("user:update")
    public BaseResponse updateUser(User user) throws MyException {
        try {
            this.userService.updateUser(user);
            return new BaseResponse().success();
        } catch (Exception e) {
            String message = "修改User失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }

    @PostMapping("user/excel")
    @ResponseBody
    @RequiresPermissions("user:export")
    public void export(QueryRequest queryRequest, User user, HttpServletResponse response) throws MyException {
        try {
            List<User> users = this.userService.findUsers(queryRequest, user).getRecords();
            ExcelKit.$Export(User.class, response).downXlsx(users, false);
        } catch (Exception e) {
            String message = "导出Excel失败";
            log.error(message, e);
            throw new MyException(message);
        }
    }
}
