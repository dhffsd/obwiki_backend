package com.gec.obwiki.entity;

import lombok.Data;
import java.util.Date;

@Data
public class EbookSnapshot {
    private Long id;
    private Long ebookId;
    private Date date;
    private Long viewCount;
    private Long likeCount;
    private Long viewIncrease;
    private Long likeIncrease;
    // 统计用
    private Long totalViewCount;
    private Long totalLikeCount;
} 