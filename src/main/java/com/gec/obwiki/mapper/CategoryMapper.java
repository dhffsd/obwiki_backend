package com.gec.obwiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.obwiki.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
} 