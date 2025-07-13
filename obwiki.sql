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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `name` varchar(100) NOT NULL COMMENT '分类名称',
  `description` text COMMENT '分类描述',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父分类ID',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';

-- 文档表
CREATE TABLE `doc` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '文档ID',
  `title` varchar(200) NOT NULL COMMENT '文档标题',
  `content` longtext COMMENT '文档内容',
  `category_id` bigint(20) DEFAULT NULL COMMENT '分类ID',
  `author_id` bigint(20) DEFAULT NULL COMMENT '作者ID',
  `view_count` int(11) DEFAULT '0' COMMENT '浏览次数',
  `like_count` int(11) DEFAULT '0' COMMENT '点赞次数',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-草稿，1-发布',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_category_id` (`category_id`),
  KEY `idx_author_id` (`author_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文档表';

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
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '内容ID',
  `title` varchar(200) NOT NULL COMMENT '内容标题',
  `content` longtext COMMENT '内容详情',
  `type` varchar(50) DEFAULT NULL COMMENT '内容类型',
  `related_id` bigint(20) DEFAULT NULL COMMENT '关联ID',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态：0-禁用，1-启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_type` (`type`),
  KEY `idx_related_id` (`related_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='内容表';

-- 插入测试数据
INSERT INTO `user` (`login_name`, `nickname`, `password`, `email`, `phone`) VALUES
('admin', '管理员', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'admin@obwiki.com', '13800138000'),
('test', '测试用户', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDi', 'test@obwiki.com', '13800138001');

INSERT INTO `category` (`name`, `description`, `parent_id`, `sort`) VALUES
('海洋鱼类', '各种海洋鱼类的介绍', 0, 1),
('海洋哺乳动物', '鲸鱼、海豚等海洋哺乳动物', 0, 2),
('海洋无脊椎动物', '珊瑚、海星等无脊椎动物', 0, 3),
('鲨鱼', '各种鲨鱼品种', 1, 1),
('热带鱼', '热带海域的鱼类', 1, 2);

INSERT INTO `doc` (`title`, `content`, `category_id`, `author_id`, `view_count`, `like_count`) VALUES
('大白鲨介绍', '大白鲨是海洋中最著名的掠食者之一...', 4, 1, 100, 50),
('海豚的智慧', '海豚是海洋中最聪明的动物之一...', 2, 1, 80, 30);

INSERT INTO `ebook` (`title`, `description`, `author_id`, `category_id`, `view_count`, `like_count`) VALUES
('海洋生物图鉴', '一本详细介绍海洋生物的电子书', 1, 1, 200, 100),
('深海探索', '探索深海中的神秘生物', 1, 2, 150, 75); 