package com.gec.obwiki.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gec.obwiki.entity.User;
import com.gec.obwiki.req.*;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.resp.UserLoginResp;
import com.gec.obwiki.resp.UserQueryResp;

public interface IUserService extends IService<User> {
    PageResp<UserQueryResp> list(UserQueryReq req);
    UserQueryResp findById(Long id);
    void save(UserSaveReq req);
    void update(UserSaveReq req);
    void delete(Long id);
    void resetPassword(UserResetPasswordReq req);
    void changePassword(UserChangePasswordReq req);
    void updateAvatar(UserUpdateAvatarReq req);
    void updateStatus(UserUpdateStatusReq req);
    UserLoginResp login(UserLoginReq req);
} 