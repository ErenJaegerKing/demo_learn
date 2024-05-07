package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.example.domain.LoginUser;
import org.example.domain.User;
import org.example.mapper.MenuMapper;
import org.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @Author: erenjaeger
 * @Date: 2024/5/4 14:27
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    //UserDetails是Security官方提供的接口
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserName,username);
        User user = userMapper.selectOne(queryWrapper);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或者密码错误");
        }
        //--------------------------------查询用户权限信息---------------------------------

        //下面那行就是我们的权限集合，等下还要在LoginUser类做权限集合的转换
        List<String> list = menuMapper.selectPermsByUserId(user.getId());

        //------------------------------------------------------------------------------

        //把查询到的user结果，封装成UserDetails类型，然后返回。
        //但是由于UserDetails是个接口，所以我们先需要在domino目录新建LoginUser类，作为UserDetails的实现类，再写下面那行
        return new LoginUser(user,list); //这里传了第二个参数，表示的是权限信息
    }
}
