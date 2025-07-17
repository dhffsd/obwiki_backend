package com.gec.obwiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.obwiki.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
} 