package com.cx.basecode.system.service.impl;

import com.cx.basecode.common.entity.QueryRequest;
import com.cx.basecode.system.entity.User;
import com.cx.basecode.system.mapper.UserMapper;
import com.cx.basecode.system.service.IUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.beans.factory.annotation.Autowired;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import java.util.List;

/**
 *  Service实现
 *
 * @author alex
 * @date 2019-09-06 15:34:53
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public IPage<User> findUsers(QueryRequest request, User user) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        // TODO 设置查询条件
        Page<User> page = new Page<>(request.getPageNum(), request.getPageSize());
        return this.page(page, queryWrapper);
    }

    @Override
    public List<User> findUsers(User user) {
	    LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
		// TODO 设置查询条件
		return this.baseMapper.selectList(queryWrapper);
    }

    @Override
    public User findByName(String username) {
        return this.baseMapper.findByName(username);
    }

    @Override
    @Transactional
    public void createUser(User user) {
        this.save(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.saveOrUpdate(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        LambdaQueryWrapper<User> wapper = new LambdaQueryWrapper<>();
	    // TODO 设置删除条件
	    this.remove(wapper);
	}
}
