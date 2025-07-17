package com.gec.obwiki.controller;

import com.alibaba.fastjson.JSONObject;
import com.gec.obwiki.req.*;
import com.gec.obwiki.resp.*;
import com.gec.obwiki.service.IUserService;
import com.gec.obwiki.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @GetMapping("/list")
    public CommonResp<PageResp<UserQueryResp>> list(@Valid UserQueryReq req) {
        PageResp<UserQueryResp> pageResp = userService.list(req);
        return new CommonResp<>(true, "查询成功", pageResp);
    }

    @GetMapping("/find/{id}")
    public CommonResp<UserQueryResp> findById(@PathVariable Long id) {
        UserQueryResp user = userService.findById(id);
        return new CommonResp<>(true, "查询成功", user);
    }

    @PostMapping("/save")
    public CommonResp save(@Valid @RequestBody UserSaveReq req) {
        userService.save(req);
        return new CommonResp<>(true, "操作成功", null);
    }

    @PutMapping("/update")
    public CommonResp update(@Valid @RequestBody UserSaveReq req) {
        userService.update(req);
        return new CommonResp<>(true, "更新成功", null);
    }

    @DeleteMapping("/delete/{id}")
    public CommonResp delete(@PathVariable Long id) {
        userService.delete(id);
        return new CommonResp<>(true, "删除成功", null);
    }

    @PostMapping("/reset-password")
    public CommonResp resetPassword(@Valid @RequestBody UserResetPasswordReq req) {
        userService.resetPassword(req);
        return new CommonResp<>(true, "密码重置成功", null);
    }

    @PostMapping("/change-password")
    public CommonResp changePassword(@Valid @RequestBody UserChangePasswordReq req) {
        userService.changePassword(req);
        return new CommonResp<>(true, "密码修改成功", null);
    }

    @PostMapping("/update-avatar")
    public CommonResp updateAvatar(@Valid @RequestBody UserUpdateAvatarReq req) {
        userService.updateAvatar(req);
        return new CommonResp<>(true, "头像更新成功", null);
    }

    @PostMapping("/update-status")
    public CommonResp updateStatus(@Valid @RequestBody UserUpdateStatusReq req) {
        userService.updateStatus(req);
        return new CommonResp<>(true, "状态更新成功", null);
    }

    @PostMapping("/login")
    public CommonResp<UserLoginResp> login(@Valid @RequestBody UserLoginReq req) {
        UserLoginResp userLoginResp = userService.login(req);
        Long token = snowFlake.nextId();
        LOG.info("生成单点登录token：{}，并放入redis中", token);
        userLoginResp.setToken(token.toString());
        redisTemplate.opsForValue().set(token.toString(),
                JSONObject.toJSONString(userLoginResp), 3600 * 24, TimeUnit.SECONDS);
        return new CommonResp<>(true, "登录成功", userLoginResp);
    }

    @GetMapping("/logout/{token}")
    public CommonResp logout(@PathVariable String token) {
        redisTemplate.delete(token);
        LOG.info("从redis中删除token: {}", token);
        return new CommonResp<>(true, "退出成功", null);
    }

    @GetMapping("/info/{token}")
    public CommonResp<UserQueryResp> getUserInfo(@PathVariable String token) {
        String userJson = redisTemplate.opsForValue().get(token);
        if (userJson != null) {
            UserLoginResp userLoginResp = JSONObject.parseObject(userJson, UserLoginResp.class);
            UserQueryResp userInfo = userService.findById(userLoginResp.getId());
            return new CommonResp<>(true, "获取用户信息成功", userInfo);
        }
        return new CommonResp<>(false, "token已过期", null);
    }
} 