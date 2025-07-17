-- 海洋生物知识库系统数据库初始化脚本
-- 创建数据库
CREATE DATABASE IF NOT EXISTS obwiki DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE obwiki;

-- 用户表
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `login_name` varchar(50) NOT NULL COMMENT '登录名',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_login_name` (`login_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

-- 分类表
CREATE TABLE `category` (
  `id` bigint NOT NULL COMMENT 'ID',
  `name` varchar(50) NOT NULL COMMENT '名称',
  `parent` bigint NOT NULL COMMENT '父分类ID',
  `sort` int NOT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='分类表';

-- 文档表
CREATE TABLE `doc` (
  `id` bigint NOT NULL COMMENT 'ID',
  `ebook_id` bigint NOT NULL COMMENT '电子书ID',
  `parent` bigint NOT NULL COMMENT '父文档ID',
  `name` varchar(200) NOT NULL COMMENT '文档名称',
  `sort` int NOT NULL COMMENT '排序',
  `view_count` int DEFAULT '0' COMMENT '浏览次数',
  `vote_count` int DEFAULT '0' COMMENT '点赞次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='文档表';

-- 电子书表
CREATE TABLE `ebook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '电子书ID',
  `title` varchar(200) NOT NULL COMMENT '电子书标题',
  `description` text COMMENT '电子书描述',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `author_id` bigint(20) DEFAULT NULL COMMENT '作者ID',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-草稿，1-发布',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子书表';

-- 电子书快照表
CREATE TABLE `ebook_snapshot` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '快照ID',
  `ebook_id` bigint(20) NOT NULL COMMENT '电子书ID',
  `title` varchar(200) NOT NULL COMMENT '电子书标题',
  `description` text COMMENT '电子书描述',
  `cover` varchar(255) DEFAULT NULL COMMENT '封面图片',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_ebook_id` (`ebook_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='电子书快照表';

-- 内容表
CREATE TABLE `content` (
  `id` bigint NOT NULL COMMENT 'ID',
  `content` longtext COMMENT '内容详情',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='内容表';

-- 插入测试数据
INSERT INTO `user` (`login_name`, `nickname`, `password`, `email`, `phone`) VALUES
('admin', '管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'admin@obwiki.com', '13800138000'),
('test', '测试用户', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'test@obwiki.com', '13800138001');

INSERT INTO `category` (`id`, `name`, `parent`, `sort`) VALUES
(1, '海洋鱼类', 0, 1),
(2, '海洋哺乳动物', 0, 2),
(3, '海洋无脊椎动物', 0, 3),
(4, '热带鱼', 1, 1),
(5, '冷水鱼', 1, 2),
(6, '鲸类', 2, 1),
(7, '海豚类', 2, 2),
(8, '甲壳类', 3, 1),
(9, '软体动物', 3, 2);

INSERT INTO `doc` (`id`, `ebook_id`, `parent`, `name`, `sort`, `view_count`, `vote_count`) VALUES
(1, 1, 0, '第一章 海洋鱼类概述', 1, 100, 50),
(2, 1, 1, '1.1 热带鱼介绍', 1, 80, 30),
(3, 1, 1, '1.2 冷水鱼介绍', 2, 60, 20),
(4, 2, 0, '第一章 海洋哺乳动物', 1, 120, 80),
(5, 2, 4, '1.1 鲸类介绍', 1, 90, 45);

INSERT INTO `ebook` (`title`, `description`, `author_id`, `category_id`, `view_count`, `like_count`) VALUES
('海洋生物图鉴', '一本详细介绍海洋生物的电子书', 1, 1, 200, 100),
('深海探索', '探索深海中的神秘生物', 1, 2, 150, 75);

INSERT INTO `content` (`id`, `content`) VALUES
(1, '海洋鱼类是海洋生态系统中的重要组成部分，它们种类繁多，形态各异。本章将详细介绍海洋鱼类的分类、特征和习性。'),
(2, '热带鱼主要分布在热带海域，它们色彩艳丽，体型较小，是观赏鱼的主要来源。'),
(3, '冷水鱼主要分布在寒带和温带海域，它们体型较大，肉质鲜美，是重要的经济鱼类。'),
(4, '海洋哺乳动物是海洋中最具智慧的生物，包括鲸类、海豚类等。它们具有高度的社会性和智慧。'),
(5, '鲸类是海洋中最大的哺乳动物，包括蓝鲸、抹香鲸等。它们具有复杂的社交行为和迁徙习性。'); 