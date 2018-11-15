-- MySQL dump 10.13  Distrib 5.6.39, for Win64 (x86_64)
--
-- Host: localhost    Database: steam
-- ------------------------------------------------------
-- Server version	5.6.39


USE steam;
--
-- Table structure for table `admin`
--


/*管理员*/
DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `id`       int(11) NOT NULL AUTO_INCREMENT
  COMMENT '主键，自增',
  `phone`    varchar(20)      DEFAULT NULL
  COMMENT '电话号码',
  `password` varchar(50)      DEFAULT NULL
  COMMENT '密码',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--
/*用户表*/
DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id`       int(11) NOT NULL AUTO_INCREMENT
  COMMENT '自增主键',
  `phone`    varchar(20)      DEFAULT NULL
  COMMENT '手机号码',
  `name`     varchar(20)      DEFAULT NULL
  COMMENT '姓名',
  `password` varchar(20)      DEFAULT NULL
  COMMENT '密码',
  `email`    varchar(30)      DEFAULT NULL
  COMMENT '邮箱',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8;

--
-- Table structure for table `pictures`
--
/*相册表*/
DROP TABLE IF EXISTS `pictures`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pictures` (
  `id`           varchar(100) NOT NULL
  COMMENT '主键',
  `picname`      varchar(50)  DEFAULT NULL
  COMMENT '相册名',
  `authorname`   varchar(30)  DEFAULT NULL
  COMMENT '作者名',
  `tag`          varchar(100) DEFAULT NULL
  COMMENT '标签',
  `realdir`      varchar(100) DEFAULT NULL
  COMMENT '实际物理路径',
  `virtualdir`   varchar(100) DEFAULT NULL
  COMMENT '虚拟路径',
  `indexpic`     varchar(100) DEFAULT NULL
  COMMENT '封面虚拟路径',
  `indexrealdir` varchar(100) DEFAULT NULL
  COMMENT '封面物理路径',
  `counts`       int          default null
  comment '相片个数',
  `flag`         int          default null
  comment '逻辑删除标志',
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;


/*里番表*/
DROP TABLE IF EXISTS `acg`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acg` (
  id             varchar(100) PRIMARY KEY,
  acgctid        varchar(100) COMMENT '合集id',
  acgname        VARCHAR(100) COMMENT '里番名',
  releasetime    DATE COMMENT '发行日期',
  magnet         VARCHAR(100) COMMENT '磁力链接',
  `indexpic`     varchar(100) DEFAULT NULL
  COMMENT '封面虚拟路径',
  `indexrealdir` varchar(100) DEFAULT NULL
  COMMENT '封面物理路径',
  publisher      VARCHAR(50) COMMENT '发行商'

)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*里番合集表*/
DROP TABLE IF EXISTS `acg_content`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acg_content` (
  acgctid     varchar(100) COMMENT '合集id',
  acgctname   VARCHAR(100) COMMENT '里番合集名',
  releasetime DATE COMMENT '合集收集年月',
  magnet      VARCHAR(100) COMMENT '磁力链接'
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

/*收藏表*/
DROP TABLE IF EXISTS `collection`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `collection` (
  `phone` varchar(20)  NOT NULL
  COMMENT '手机号码',
  `id`    varchar(100) NOT NULL
  COMMENT '相册id',
  primary key (`phone`, `id`)

)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

