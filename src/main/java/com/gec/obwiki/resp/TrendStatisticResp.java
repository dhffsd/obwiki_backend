package com.gec.obwiki.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;

@Data
public class TrendStatisticResp {
    @JsonFormat(pattern = "MM-dd")
    private Date date;
    private Long viewCount;
    private Long likeCount;
} 