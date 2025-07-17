package com.gec.obwiki.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DocQueryResp {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long ebookId;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parent;
    private String name;
    private Integer sort;
    private Integer viewCount;
    private Integer voteCount;
} 