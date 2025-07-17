package com.gec.obwiki.resp;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class HomeStatisticResp {
    private Long totalViewCount;
    private Long totalLikeCount;
    private Long todayViewCount;
    private Long todayLikeCount;
    private Long todayViewEstimated;
    private BigDecimal todayViewIncreasePercent;
} 