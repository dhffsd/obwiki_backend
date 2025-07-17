package com.gec.obwiki.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gec.obwiki.entity.EbookSnapshot;
import com.gec.obwiki.resp.TrendStatisticResp;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface EbookSnapshotMapper extends BaseMapper<EbookSnapshot> {
    void genTodaySnapshot();
    void updateSnapshotViewCount();
    void updateSnapshotLikeCount();
    void updateSnapshotIncrease();
    EbookSnapshot getByDate(@Param("date") LocalDate date);
    List<TrendStatisticResp> get30DayTrend();
} 