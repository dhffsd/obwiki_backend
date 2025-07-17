package com.gec.obwiki.req;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class DocSaveReq {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @NotNull(message = "【电子书】不能为空")
    private Long ebookId;
    @NotNull(message = "【父文档】不能为空")
    private Long parent;
    @NotNull(message = "【名称】不能为空")
    private String name;
    @NotNull(message = "【顺序】不能为空")
    private Integer sort;
    private Integer viewCount;
    private Integer voteCount;
    @NotNull(message = "【内容】不能为空")
    private String content;
} 