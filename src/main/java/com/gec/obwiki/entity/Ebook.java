package com.gec.obwiki.entity;

import lombok.Data;

@Data
public class Ebook {
    private Long id;
    private String name;
    private Long category1Id;
    private Long category2Id;
    private String description;
    private String cover;
    private Integer docCount;
    private Integer viewCount;
    private Integer voteCount;
} 