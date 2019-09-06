package com.cx.basecode.system.service;

import com.cx.basecode.system.entity.User;

import com.cx.basecode.common.entity.QueryRequest;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *  Service接口
 *
 * @author alex
 * @date 2019-09-06 15:34:53
 */
public interface IUserService extends IService<User> {
    /**
     * 查询（分页）
     *
     * @param request QueryRequest
     * @param user user
     * @return IPage<User>
     */
    IPage<User> findUsers(QueryRequest request, User user);

    /**
     * 查询（所有）
     *
     * @param user user
     * @return List<User>
     */
    List<User> findUsers(User user);

    User findByName(String username);
    /**
     * 新增
     *
     * @param user user
     */
    void createUser(User user);

    /**
     * 修改
     *
     * @param user user
     */
    void updateUser(User user);

    /**
     * 删除
     *
     * @param user user
     */
    void deleteUser(User user);
}
