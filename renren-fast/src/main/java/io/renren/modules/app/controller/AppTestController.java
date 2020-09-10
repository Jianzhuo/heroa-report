/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package io.renren.modules.app.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.common.utils.R;
import io.renren.modules.app.annotation.Login;
import io.renren.modules.app.annotation.LoginUser;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.sys.entity.SysRoleEntity;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.entity.SysUserRoleEntity;
import io.renren.modules.sys.service.SysRoleService;
import io.renren.modules.sys.service.SysUserRoleService;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * APP测试接口
 *
 * @author Mark sunlightcs@gmail.com
 */
@RestController
@RequestMapping("/app")
@Api("APP测试接口")
public class AppTestController {

    @GetMapping("userInfo")
    @ApiOperation("获取用户信息")
    public R userInfo(@LoginUser UserEntity user){
        return R.ok().put("user", user);
    }

    @Login
    @GetMapping("userId")
    @ApiOperation("获取用户ID")
    public R userInfo(@RequestAttribute("userId") Integer userId){
        return R.ok().put("userId", userId);
    }

    @GetMapping("notToken")
    @ApiOperation("忽略Token验证测试")
    public R notToken(){
        return R.ok().put("msg", "无需token也能访问。。。");
    }

    @Autowired
    private SysRoleService sysRoleService;
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysUserService sysUserService;

    //    查出属于指定用户角色的所有用户
    @RequestMapping("/user/list/{userRole}")
    public R list(@PathVariable("userRole") String userRole){

        //获得属性id
        Map<String, Object> columnMap = new HashMap<String, Object>();
        columnMap.put("role_name", userRole);
        List<SysRoleEntity> roles = sysRoleService.getBaseMapper().selectByMap(columnMap);
        Long roleId = roles.get(0).getRoleId();

        //查出属于该属性id下的所有用户
        columnMap = new HashMap<String, Object>();
        columnMap.put("role_id", roleId);
        List<SysUserRoleEntity> roleUsers = sysUserRoleService.getBaseMapper().selectByMap(columnMap);
        List<Long> userIds = roleUsers.stream().map(i -> i.getUserId()).collect(Collectors.toList());

        //获取用户名list
        QueryWrapper<SysUserEntity> queryWrapper = new QueryWrapper<SysUserEntity>();
        queryWrapper.select("username");
        queryWrapper.in("user_id",userIds);
        List<SysUserEntity> userNames =  sysUserService.getBaseMapper().selectList(queryWrapper);
        return R.ok().put("data", userNames);

    }

}
