package com.gec.obwiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.obwiki.entity.Doc;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface DocMapper extends BaseMapper<Doc> {
    @Update("UPDATE doc SET view_count = view_count + 1 WHERE id = #{id}")
    void increaseViewCount(Long id);

    @Update("UPDATE doc SET vote_count = vote_count + 1 WHERE id = #{id}")
    void increaseVoteCount(Long id);

    void updateEbookInfo();
} 