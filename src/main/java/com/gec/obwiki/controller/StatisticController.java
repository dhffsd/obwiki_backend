package com.gec.obwiki.controller;

import com.gec.obwiki.resp.CommonResp;
import com.gec.obwiki.resp.HomeStatisticResp;
import com.gec.obwiki.resp.TrendStatisticResp;
import com.gec.obwiki.service.IEbookSnapshotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/statistic")
public class StatisticController {
    @Autowired
    private IEbookSnapshotService ebookSnapshotService;

    @GetMapping("/home")
    public CommonResp<HomeStatisticResp> homeStatistic() {
        HomeStatisticResp resp = ebookSnapshotService.getHomeStatistic();
        CommonResp<HomeStatisticResp> result = new CommonResp<>();
        result.setContent(resp);
        return result;
    }

    @GetMapping("/trend/30days")
    public CommonResp<List<TrendStatisticResp>> trend30Days() {
        List<TrendStatisticResp> list = ebookSnapshotService.get30DayTrend();
        CommonResp<List<TrendStatisticResp>> result = new CommonResp<>();
        result.setContent(list);
        return result;
    }
} 