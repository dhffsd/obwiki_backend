package com.gec.obwiki.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gec.obwiki.entity.EbookSnapshot;
import com.gec.obwiki.mapper.EbookSnapshotMapper;
import com.gec.obwiki.resp.HomeStatisticResp;
import com.gec.obwiki.resp.TrendStatisticResp;
import com.gec.obwiki.service.IEbookSnapshotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class EbookSnapshotServiceImpl extends ServiceImpl<EbookSnapshotMapper, EbookSnapshot> implements IEbookSnapshotService {

    @Override
    @Transactional
    public void genSnapshot() {
        baseMapper.genTodaySnapshot();
        baseMapper.updateSnapshotViewCount();
        baseMapper.updateSnapshotLikeCount();
        baseMapper.updateSnapshotIncrease();
    }

    @Override
    public HomeStatisticResp getHomeStatistic() {
        HomeStatisticResp resp = new HomeStatisticResp();
        EbookSnapshot todaySnapshot = baseMapper.getByDate(LocalDate.now());
        EbookSnapshot yesterdaySnapshot = baseMapper.getByDate(LocalDate.now().minusDays(1));
        if (todaySnapshot == null) todaySnapshot = new EbookSnapshot();
        if (yesterdaySnapshot == null) yesterdaySnapshot = new EbookSnapshot();
        resp.setTotalViewCount(Optional.ofNullable(todaySnapshot.getTotalViewCount()).orElse(0L));
        resp.setTotalLikeCount(Optional.ofNullable(todaySnapshot.getTotalLikeCount()).orElse(0L));
        resp.setTodayViewCount(Optional.ofNullable(todaySnapshot.getViewIncrease()).orElse(0L));
        resp.setTodayLikeCount(Optional.ofNullable(todaySnapshot.getLikeIncrease()).orElse(0L));
        int hour = LocalTime.now().getHour();
        BigDecimal rate = hour == 0 ? BigDecimal.ONE : BigDecimal.valueOf(hour).divide(BigDecimal.valueOf(24), 2, RoundingMode.HALF_UP);
        if (rate.compareTo(BigDecimal.ZERO) == 0) rate = BigDecimal.ONE;
        resp.setTodayViewEstimated(
            rate.compareTo(BigDecimal.ZERO) == 0 ? 0L :
            BigDecimal.valueOf(resp.getTodayViewCount()).divide(rate, 0, RoundingMode.HALF_UP).longValue()
        );
        if (Optional.ofNullable(yesterdaySnapshot.getLikeIncrease()).orElse(0L) > 0) {
            BigDecimal increase = BigDecimal.valueOf(resp.getTodayLikeCount() - yesterdaySnapshot.getLikeIncrease());
            BigDecimal percent = increase.divide(BigDecimal.valueOf(yesterdaySnapshot.getLikeIncrease()), 2, RoundingMode.HALF_UP);
            resp.setTodayViewIncreasePercent(percent);
        } else {
            resp.setTodayViewIncreasePercent(BigDecimal.ONE);
        }
        return resp;
    }

    @Override
    public List<TrendStatisticResp> get30DayTrend() {
        return baseMapper.get30DayTrend();
    }
} 