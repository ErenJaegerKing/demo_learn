package org.example.controller;

import org.example.domain.ResponseResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: erenjaeger
 * @Date: 2024/5/3 22:35
 */
@RestController
public class HelloController {


    //官方提供的4个权限校验的方法
    //@PreAuthorize("hasAuthority('system:dept:list')")
    //@PreAuthorize("hasAnyAuthority('1','2','system:dept:list')")
    //@PreAuthorize("hasRole('system:dept:list')")
    //@PreAuthorize("hasAnyRole('1','2','system:dept:list')")
    //自定义权限校验的方法，huanfHasAuthority
    @PreAuthorize("@eren.erenHasAuthority('system:dept:list')")
    @RequestMapping("/hello")
    public String hello(){
        return "hello world!";
    }


    //基于配置的权限控制
    @RequestMapping("/configAuth")
    public ResponseResult configAuth(){
        return new ResponseResult(200,"访问成功");
    }
}