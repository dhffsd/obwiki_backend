package com.gec.obwiki.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.obwiki.entity.User;
import com.gec.obwiki.exception.BusinessException;
import com.gec.obwiki.exception.BusinessExceptionCode;
import com.gec.obwiki.mapper.UserMapper;
import com.gec.obwiki.req.*;
import com.gec.obwiki.resp.PageResp;
import com.gec.obwiki.resp.UserLoginResp;
import com.gec.obwiki.resp.UserQueryResp;
import com.gec.obwiki.service.IUserService;
import com.gec.obwiki.util.CopyUtil;
import com.gec.obwiki.util.SnowFlake;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private SnowFlake snowFlake;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public PageResp<UserQueryResp> list(UserQueryReq req) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        if (StringUtils.isNotBlank(req.getName())) {
            queryWrapper.like("name", req.getName());
        }
        if (StringUtils.isNotBlank(req.getLoginName())) {
            queryWrapper.like("login_name", req.getLoginName());
        }
        if (StringUtils.isNotBlank(req.getNickname())) {
            queryWrapper.like("nickname", req.getNickname());
        }
        if (StringUtils.isNotBlank(req.getEmail())) {
            queryWrapper.like("email", req.getEmail());
        }
        if (StringUtils.isNotBlank(req.getPhone())) {
            queryWrapper.like("phone", req.getPhone());
        }
        if (req.getStatus() != null) {
            queryWrapper.eq("status", req.getStatus());
        }

        Page<User> page = new Page<>(req.getPage(), req.getSize());
        page = baseMapper.selectPage(page, queryWrapper);
        List<UserQueryResp> resps = CopyUtil.copyList(page.getRecords(), UserQueryResp.class);
        PageResp<UserQueryResp> pageResp = new PageResp<>();
        pageResp.setList(resps);
        pageResp.setTotal(page.getTotal());
        return pageResp;
    }

    @Override
    public UserQueryResp findById(Long id) {
        User user = baseMapper.selectById(id);
        if (user == null) {
            throw new BusinessException(BusinessExceptionCode.USER_NOT_EXIST);
        }
        return CopyUtil.copy(user, UserQueryResp.class);
    }

    @Override
    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (ObjectUtils.isEmpty(req.getId())) {
            // 检查登录名是否已存在
            User existUser = baseMapper.selectOne(new QueryWrapper<User>().eq("login_name", req.getLoginName()));
            if (existUser != null) {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
            // 新增
            long id = snowFlake.nextId();
            user.setId(id);
            user.setPassword(passwordEncoder.encode(req.getPassword()));
            user.setStatus(1); // 默认启用状态
            baseMapper.insert(user);
        } else {
            // 更新
            if (req.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(req.getPassword()));
            }
            baseMapper.updateById(user);
        }
    }

    @Override
    public void update(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);
        if (req.getPassword() != null) {
            user.setPassword(passwordEncoder.encode(req.getPassword()));
        }
        baseMapper.updateById(user);
    }

    @Override
    public void delete(Long id) {
        baseMapper.deleteById(id);
    }

    @Override
    public void resetPassword(UserResetPasswordReq req) {
        User user = new User();
        user.setId(req.getId());
        user.setPassword(passwordEncoder.encode(req.getPassword()));
        baseMapper.updateById(user);
    }

    @Override
    public void changePassword(UserChangePasswordReq req) {
        User user = baseMapper.selectById(req.getId());
        if (user == null) {
            throw new BusinessException(BusinessExceptionCode.USER_NOT_EXIST);
        }
        
        // 验证原密码
        if (!passwordEncoder.matches(req.getOldPassword(), user.getPassword())) {
            throw new BusinessException(BusinessExceptionCode.OLD_PASSWORD_ERROR);
        }
        
        // 更新新密码
        User updateUser = new User();
        updateUser.setId(req.getId());
        updateUser.setPassword(passwordEncoder.encode(req.getNewPassword()));
        baseMapper.updateById(updateUser);
    }

    @Override
    public void updateAvatar(UserUpdateAvatarReq req) {
        User user = new User();
        user.setId(req.getId());
        user.setAvatar(req.getAvatar());
        baseMapper.updateById(user);
    }

    @Override
    public void updateStatus(UserUpdateStatusReq req) {
        User user = new User();
        user.setId(req.getId());
        user.setStatus(req.getStatus());
        baseMapper.updateById(user);
    }

    @Override
    public UserLoginResp login(UserLoginReq req) {
        User user = baseMapper.selectOne(new QueryWrapper<User>().eq("login_name", req.getLoginName()));
        LOG.info("查到的用户: {}", user);
        if (user != null) {
            LOG.info("数据库密码: {}", user.getPassword());
            LOG.info("前端密码: {}", req.getPassword());
            LOG.info("BCrypt 校验: {}", passwordEncoder.matches(req.getPassword(), user.getPassword()));
        }
        if (user == null || !passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new BusinessException(BusinessExceptionCode.LOGIN_USER_ERROR);
        }
        return CopyUtil.copy(user, UserLoginResp.class);
    }
} 