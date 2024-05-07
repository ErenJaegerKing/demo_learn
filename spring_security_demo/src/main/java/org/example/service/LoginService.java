package org.example.service;

import org.example.domain.ResponseResult;
import org.example.domain.User;
import org.springframework.stereotype.Service;

/**
 * @Author: erenjaeger
 * @Date: 2024/5/4 23:39
 */
@Service
public interface LoginService {

    ResponseResult login(User user);

    ResponseResult logout();

}
