package com.gec.obwiki.service;

import com.gec.obwiki.resp.HomeStatisticResp;
import com.gec.obwiki.resp.TrendStatisticResp;
import java.util.List;

public interface IEbookSnapshotService {
    void genSnapshot();
    HomeStatisticResp getHomeStatistic();
    List<TrendStatisticResp> get30DayTrend();
} 