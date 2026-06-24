-- MySQL dump 10.13  Distrib 5.7.21, for Win64 (x86_64)
--
-- Host: localhost    Database: yinfashouhu
-- ------------------------------------------------------
-- Server version	5.7.21-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `yinfashouhu`
--

/*!40000 DROP DATABASE IF EXISTS `yinfashouhu`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `yinfashouhu` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `yinfashouhu`;

--
-- Table structure for table `t_accompany_record`
--

DROP TABLE IF EXISTS `t_accompany_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_accompany_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `accompany_type` varchar(30) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `duration_minutes` int(11) DEFAULT '0',
  `companion` varchar(80) NOT NULL,
  `content` varchar(1000) NOT NULL,
  `emotion_result` varchar(30) DEFAULT 'STABLE',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_accompany_elder_time` (`elder_id`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_accompany_record`
--

LOCK TABLES `t_accompany_record` WRITE;
/*!40000 ALTER TABLE `t_accompany_record` DISABLE KEYS */;
INSERT INTO `t_accompany_record` VALUES (1,6,'CHAT','2026-06-17 15:00:00','2026-06-17 15:25:00',25,'护理员张姐','陪老人聊家常，询问睡眠和饮食情况。老人表达想念家人。','STABLE','建议安排一次家属视频','2026-06-18 10:21:16','2026-06-18 10:21:16'),(2,7,'EXERCISE','2026-06-17 10:00:00','2026-06-17 10:30:00',30,'康复师王老师','陪同完成下肢力量训练，过程需要轻度搀扶。','HAPPY','训练结束后精神状态较好','2026-06-18 10:21:16','2026-06-18 10:21:16'),(3,2,'VIDEO','2026-06-17 19:20:00','2026-06-17 19:45:00',25,'家属陈燕','协助老人和女儿视频通话，沟通近期饮食和血糖情况。','HAPPY','通话后情绪明显改善','2026-06-18 10:21:16','2026-06-18 10:21:16');
/*!40000 ALTER TABLE `t_accompany_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_behavior_record`
--

DROP TABLE IF EXISTS `t_behavior_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_behavior_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `behavior_type` varchar(30) NOT NULL,
  `behavior_name` varchar(100) NOT NULL,
  `location` varchar(100) DEFAULT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime DEFAULT NULL,
  `duration_minutes` int(11) DEFAULT '0',
  `risk_level` varchar(20) DEFAULT 'LOW',
  `source_device` varchar(100) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_behavior_elder_type_name_time` (`elder_id`,`behavior_type`,`behavior_name`,`start_time`),
  KEY `idx_behavior_elder_date` (`elder_id`,`start_time`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_behavior_record`
--

LOCK TABLES `t_behavior_record` WRITE;
/*!40000 ALTER TABLE `t_behavior_record` DISABLE KEYS */;
INSERT INTO `t_behavior_record` VALUES (1,1,'ACTIVITY','晨间慢走','康复花园','2026-06-17 08:10:00','2026-06-17 08:35:00',25,'LOW','摄像头-花园01','步速稳定，精神状态正常','2026-06-17 19:39:04','2026-06-17 19:39:04'),(2,1,'MEDICATION','早餐后服降压药','护理站','2026-06-17 08:45:00','2026-06-17 08:50:00',5,'LOW','护理员录入','已核对药盒','2026-06-17 19:39:04','2026-06-17 19:39:04'),(3,2,'MEDICATION','餐前测血糖','餐厅','2026-06-17 07:20:00','2026-06-17 07:25:00',5,'LOW','护理员录入','空腹血糖记录正常','2026-06-17 19:39:04','2026-06-17 19:39:04'),(4,2,'SLEEP','午休','房间203','2026-06-17 13:05:00','2026-06-17 14:20:00',75,'LOW','床垫传感器-203','午休稳定','2026-06-17 19:39:04','2026-06-17 19:39:04'),(5,4,'ABNORMAL','夜间离床时间偏长','房间305','2026-06-17 02:18:00','2026-06-17 02:42:00',24,'HIGH','床垫传感器-305','护理员已巡查，无跌倒','2026-06-17 19:39:04','2026-06-17 19:39:04'),(6,7,'ACTIVITY','康复训练','康复室','2026-06-17 10:00:00','2026-06-17 10:30:00',30,'MEDIUM','护理员录入','需搀扶完成训练','2026-06-17 19:39:04','2026-06-17 19:39:04'),(7,4,'ACTIVITY','临时测试记录已转为巡查记录','一楼护理站','2026-06-17 19:42:00','2026-06-17 19:45:00',15,'LOW','护理员录入','巡查状态正常，记录已规范化','2026-06-17 19:42:21','2026-06-22 21:52:20'),(19,1,'ACTIVITY','晨间走廊慢行','二楼走廊','2026-06-22 07:20:00','2026-06-22 07:48:00',28,'LOW','走廊摄像头201','步速稳定，正确使用扶手','2026-06-22 21:34:09','2026-06-22 21:52:20'),(20,2,'MEDICATION','早餐后服药确认','203房间','2026-06-22 08:35:00','2026-06-22 08:40:00',5,'LOW','203房语音终端','语音终端已确认老人完成服药','2026-06-22 21:34:09','2026-06-22 21:52:20'),(21,3,'SLEEP','午间午休','203房间','2026-06-22 12:40:00','2026-06-22 14:05:00',85,'LOW','305房门口摄像头','午休时长处于正常范围','2026-06-22 21:34:09','2026-06-22 21:52:20'),(22,4,'ABNORMAL','夜间离床超时','305房间','2026-06-21 23:08:00','2026-06-21 23:22:00',14,'HIGH','305房门口摄像头','触发离床超时提醒，已通知护理站','2026-06-22 21:34:09','2026-06-22 21:52:20'),(23,5,'ACTIVITY','康复室训练','康复活动室','2026-06-21 10:00:00','2026-06-21 10:42:00',42,'LOW','康复室摄像头01','下肢力量训练完成情况良好','2026-06-22 21:34:09','2026-06-22 21:52:20'),(24,6,'ABNORMAL','餐后久坐','餐厅入口','2026-06-20 12:35:00','2026-06-20 13:08:00',33,'MEDIUM','餐厅摄像头001','长时间静坐后护理员已现场确认状态','2026-06-22 21:34:09','2026-06-22 21:52:20'),(25,7,'ACTIVITY','花园散步','室外花园步道','2026-06-20 16:10:00','2026-06-20 16:46:00',36,'LOW','花园摄像头01','户外散步后正常返回，步态平稳','2026-06-22 21:34:09','2026-06-22 21:52:20'),(26,8,'SLEEP','夜间睡眠','101房间','2026-06-19 21:30:00','2026-06-20 05:40:00',490,'LOW','101房语音终端','夜间醒来一次，未触发紧急呼叫','2026-06-22 21:34:09','2026-06-22 21:52:20'),(27,1,'ABNORMAL','卫生间停留超时','卫生间','2026-06-19 06:18:00','2026-06-19 06:42:00',24,'MEDIUM','护理区摄像头101','超过阈值后护理员已到场查看','2026-06-22 21:34:09','2026-06-22 21:52:20'),(28,3,'MEDICATION','午间血糖药提醒完成','203房间','2026-06-18 12:10:00','2026-06-18 12:16:00',6,'LOW','203房语音终端','终端播报后已完成用药确认','2026-06-22 21:34:09','2026-06-22 21:52:20');
/*!40000 ALTER TABLE `t_behavior_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_camera_device`
--

DROP TABLE IF EXISTS `t_camera_device`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_camera_device` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `device_name` varchar(100) NOT NULL,
  `device_location` varchar(100) NOT NULL,
  `device_ip` varchar(50) NOT NULL,
  `status` varchar(20) DEFAULT 'ONLINE',
  `last_online_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_camera_ip` (`device_ip`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_camera_device`
--

LOCK TABLES `t_camera_device` WRITE;
/*!40000 ALTER TABLE `t_camera_device` DISABLE KEYS */;
INSERT INTO `t_camera_device` VALUES (1,'大门监控','正门入口','192.168.10.101','ONLINE','2026-06-17 14:32:00','出入口主摄像头','2026-06-17 20:01:27','2026-06-17 20:01:27'),(2,'走廊监控','二楼东侧走廊','192.168.10.105','ONLINE','2026-06-17 14:30:00','夜间巡查重点','2026-06-17 20:01:27','2026-06-17 20:01:27'),(3,'活动室监控','三层活动室西南角','192.168.10.110','OFFLINE','2026-06-17 08:15:00','待检修','2026-06-17 20:01:27','2026-06-17 20:01:27'),(4,'花园监控','后花园凉亭旁','192.168.10.130','MAINTENANCE','2026-06-16 09:47:00','镜头清洁中','2026-06-17 20:01:27','2026-06-17 20:01:27'),(22,'一楼护士站全景摄像头','一楼护士站','10.10.10.101','ONLINE','2026-06-22 21:20:00','覆盖护士交接班区域，支持夜视和移动侦测','2026-06-22 21:34:33','2026-06-22 21:51:28'),(23,'二楼东侧走廊跌倒识别摄像头','二楼东侧走廊','10.10.10.201','ONLINE','2026-06-22 21:18:00','接入跌倒识别算法，用于夜间巡查监测','2026-06-22 21:34:33','2026-06-22 21:51:28'),(24,'餐厅入口行为分析摄像头','餐厅入口','10.10.10.31','ONLINE','2026-06-22 21:16:00','用于就餐时段人流分析和异常停留识别','2026-06-22 21:34:33','2026-06-22 21:51:28'),(25,'花园步道巡查摄像头','室外花园步道','10.10.10.41','OFFLINE','2026-06-22 08:05:00','室外网络离线，已生成维护工单','2026-06-22 21:34:33','2026-06-22 21:51:28'),(26,'一楼电梯厅摄像头','一楼电梯厅','10.10.10.51','ONLINE','2026-06-22 21:12:00','覆盖等候区，并联动边缘网关告警','2026-06-22 21:34:33','2026-06-22 21:51:28'),(27,'305房离床监测摄像头','305房门口','10.10.10.65','MAINTENANCE','2026-06-21 18:30:00','隐私遮挡区域校准中','2026-06-22 21:34:33','2026-06-22 21:51:28'),(28,'二楼楼梯间摄像头','二楼楼梯间','10.10.10.72','ONLINE','2026-06-22 21:10:00','监测楼梯转角和扶手风险区域','2026-06-22 21:34:33','2026-06-22 21:51:28'),(29,'康复室全景摄像头','康复活动室','10.10.10.88','ONLINE','2026-06-22 21:08:00','用于康复训练复盘和活动轨迹记录','2026-06-22 21:34:33','2026-06-22 21:51:28');
/*!40000 ALTER TABLE `t_camera_device` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_checkup_record`
--

DROP TABLE IF EXISTS `t_checkup_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_checkup_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `checkup_date` date NOT NULL,
  `checkup_type` varchar(20) NOT NULL,
  `hospital_name` varchar(100) NOT NULL,
  `result_summary` varchar(1000) NOT NULL,
  `risk_level` varchar(20) DEFAULT 'LOW',
  `follow_up_suggestion` varchar(1000) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'NORMAL',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_checkup_elder_date` (`elder_id`,`checkup_date`),
  KEY `idx_checkup_risk` (`risk_level`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_checkup_record`
--

LOCK TABLES `t_checkup_record` WRITE;
/*!40000 ALTER TABLE `t_checkup_record` DISABLE KEYS */;
INSERT INTO `t_checkup_record` VALUES (1,4,'2026-06-17','ROUTINE','市人民医院体检中心','血压、血糖、血氧整体平稳，轻度血脂偏高。','LOW','继续低盐低脂饮食，7天后复测血脂。','NORMAL','年度常规体检','2026-06-22 10:40:23','2026-06-22 10:52:10'),(2,7,'2026-06-16','SPECIAL','第一人民医院心内科','心电图提示偶发早搏，血压偏高。','MEDIUM','1周内携带报告复诊心内科，监测血压。','ABNORMAL','建议重点随访','2026-06-22 10:40:23','2026-06-22 10:52:10');
/*!40000 ALTER TABLE `t_checkup_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_daily_report`
--

DROP TABLE IF EXISTS `t_daily_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_daily_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `report_date` date NOT NULL,
  `total_records` int(11) DEFAULT '0',
  `activity_minutes` int(11) DEFAULT '0',
  `sleep_minutes` int(11) DEFAULT '0',
  `medication_count` int(11) DEFAULT '0',
  `abnormal_count` int(11) DEFAULT '0',
  `risk_level` varchar(20) DEFAULT 'LOW',
  `summary` varchar(1000) DEFAULT NULL,
  `suggestion` varchar(1000) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_daily_report_elder_date` (`elder_id`,`report_date`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_daily_report`
--

LOCK TABLES `t_daily_report` WRITE;
/*!40000 ALTER TABLE `t_daily_report` DISABLE KEYS */;
INSERT INTO `t_daily_report` VALUES (1,1,'2026-06-17',2,25,0,1,0,'LOW','周建国在2026-06-17共记录2条行为，活动25分钟，用药1次。主要行为：晨间慢走、早餐后服降压药。','整体行为稳定，继续关注血压和晨间活动耐受情况。',1,'2026-06-17 19:39:04','2026-06-17 19:39:04'),(2,4,'2026-06-17',1,0,0,0,1,'HIGH','赵桂英在2026-06-17出现夜间离床时间偏长，异常1次。','建议加强夜间巡查，保持床边照明并评估跌倒风险。',1,'2026-06-17 19:39:04','2026-06-17 19:39:04'),(4,7,'2026-06-17',1,30,0,0,0,'LOW','孙宝林在2026-06-17共记录1条行为，活动30分钟，睡眠0分钟，用药0次，异常0次。主要行为：康复训练。','整体行为稳定，继续保持对康复训练等行为的日常观察。',1,'2026-06-17 19:40:58','2026-06-17 19:40:58');
/*!40000 ALTER TABLE `t_daily_report` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_data_dictionary`
--

DROP TABLE IF EXISTS `t_data_dictionary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_data_dictionary` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type` varchar(50) NOT NULL,
  `dict_code` varchar(50) NOT NULL,
  `dict_name` varchar(100) NOT NULL,
  `dict_value` varchar(100) DEFAULT NULL,
  `sort_no` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_data_dictionary_type_code` (`dict_type`,`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_data_dictionary`
--

LOCK TABLES `t_data_dictionary` WRITE;
/*!40000 ALTER TABLE `t_data_dictionary` DISABLE KEYS */;
INSERT INTO `t_data_dictionary` VALUES (1,'gender','male','男','1',1,1,'性别字典','2026-06-17 16:55:56','2026-06-17 16:55:56'),(2,'gender','female','女','0',2,1,'性别字典','2026-06-17 16:55:56','2026-06-17 16:55:56'),(3,'health_status','normal','正常','normal',1,1,'老人健康状态','2026-06-17 16:55:56','2026-06-17 16:55:56');
/*!40000 ALTER TABLE `t_data_dictionary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_dialog_record`
--

DROP TABLE IF EXISTS `t_dialog_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_dialog_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `dialog_time` datetime NOT NULL,
  `speaker` varchar(50) NOT NULL,
  `dialog_content` varchar(1000) NOT NULL,
  `emotion` varchar(20) DEFAULT 'NORMAL',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_dialog_elder_time` (`elder_id`,`dialog_time`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_dialog_record`
--

LOCK TABLES `t_dialog_record` WRITE;
/*!40000 ALTER TABLE `t_dialog_record` DISABLE KEYS */;
INSERT INTO `t_dialog_record` VALUES (1,1,'2026-06-17 09:10:00','护理员','今天早餐吃得怎么样，药按时吃了吗？','NORMAL','日常回访','2026-06-17 20:01:27','2026-06-17 20:01:27'),(2,6,'2026-06-17 15:20:00','老人','今天有点想家，晚上能不能早点视频和家里人聊聊。','SAD','关注情绪变化','2026-06-17 20:01:27','2026-06-17 20:01:27');
/*!40000 ALTER TABLE `t_dialog_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_edge_gateway`
--

DROP TABLE IF EXISTS `t_edge_gateway`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_edge_gateway` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `gateway_code` varchar(50) NOT NULL,
  `gateway_name` varchar(100) NOT NULL,
  `install_location` varchar(100) NOT NULL,
  `gateway_ip` varchar(50) DEFAULT NULL,
  `mac_address` varchar(50) DEFAULT NULL,
  `access_protocol` varchar(20) DEFAULT 'MQTT',
  `managed_device_count` int(11) DEFAULT '0',
  `firmware_version` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'ONLINE',
  `last_heartbeat_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_edge_gateway_code` (`gateway_code`),
  KEY `idx_edge_gateway_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_edge_gateway`
--

LOCK TABLES `t_edge_gateway` WRITE;
/*!40000 ALTER TABLE `t_edge_gateway` DISABLE KEYS */;
INSERT INTO `t_edge_gateway` VALUES (14,'GW-F1-NURSE','一楼护理区边缘网关','一楼弱电柜A区','10.30.1.10','A8:3B:76:11:20:01','MQTT',26,'v3.5.2','ONLINE','2026-06-22 09:20:10','汇聚一楼摄像头、语音终端、门磁和定位标签','2026-06-22 21:39:41','2026-06-22 21:47:14'),(15,'GW-F2-EAST','二楼东区边缘网关','二楼东侧弱电箱','10.30.2.20','A8:3B:76:11:20:02','MQTT',21,'v3.5.2','ONLINE','2026-06-22 09:20:08','运行本地事件预处理与跌倒识别模型','2026-06-22 21:39:41','2026-06-22 21:47:14'),(16,'GW-F3-ROOM','三楼居室区边缘网关','三楼低压设备柜','10.30.3.30','A8:3B:76:11:20:03','HTTP',18,'v3.4.9','MAINTENANCE','2026-06-22 08:35:00','固件升级观察期，暂不接入新增设备','2026-06-22 21:39:41','2026-06-22 21:47:14'),(17,'GW-GARDEN','室外花园边缘网关','花园设备箱','10.30.4.40','A8:3B:76:11:20:04','MQTT',9,'v3.3.7','OFFLINE','2026-06-20 16:02:00','室外链路中断，待更换防水网线','2026-06-22 21:39:41','2026-06-22 21:47:14'),(18,'GW-MEDICAL','医务室健康设备网关','医务室设备柜','10.30.5.50','A8:3B:76:11:20:05','MQTT',14,'v3.5.1','ONLINE','2026-06-22 09:20:12','连接血压计、血糖仪、体温枪和告警推送通道','2026-06-22 21:39:41','2026-06-22 21:47:14'),(19,'GW-DINING','餐厅公共区边缘网关','餐厅后厨弱电间','10.30.6.60','A8:3B:76:11:20:06','TCP',16,'v3.5.0','ONLINE','2026-06-22 09:19:54','接入餐厅摄像头、广播终端和人流统计传感器','2026-06-22 21:39:41','2026-06-22 21:47:14');
/*!40000 ALTER TABLE `t_edge_gateway` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_elder`
--

DROP TABLE IF EXISTS `t_elder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_elder` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(16) NOT NULL,
  `birthday` date NOT NULL,
  `gender` int(11) DEFAULT NULL COMMENT '0:男 1:女',
  `health_status` varchar(100) DEFAULT NULL,
  `emergency_contact` varchar(16) DEFAULT NULL,
  `relation` varchar(10) DEFAULT NULL,
  `contact_number` varchar(14) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_elder`
--

LOCK TABLES `t_elder` WRITE;
/*!40000 ALTER TABLE `t_elder` DISABLE KEYS */;
INSERT INTO `t_elder` VALUES (1,'周建国','1942-03-12',0,'高血压；轻度膝关节退化；需低盐饮食','周明','儿子','13810234567','2026-06-17 19:39:04','2026-06-17 19:39:04'),(2,'李秀兰','1946-11-05',1,'2型糖尿病；视力下降；每日监测血糖','陈燕','女儿','13611887654','2026-06-17 19:39:04','2026-06-17 19:39:04'),(3,'王德全','1939-07-21',0,'冠心病术后；需按时服药；避免剧烈运动','王磊','儿子','13987654321','2026-06-17 19:39:04','2026-06-17 19:39:04'),(4,'赵桂英','1948-01-30',1,'骨质疏松；夜间起夜频繁；有跌倒风险','赵敏','女儿','13566778890','2026-06-17 19:39:04','2026-06-17 19:39:04'),(5,'陈福生','1944-09-18',0,'慢性支气管炎；冬季易咳喘；需关注室内空气','陈立','儿子','13755661234','2026-06-17 19:39:04','2026-06-17 19:39:04'),(6,'刘玉梅','1950-06-09',1,'阿尔茨海默病早期；记忆力减退；需防走失','刘佳','孙女','18812340987','2026-06-17 19:39:04','2026-06-17 19:39:04'),(7,'孙宝林','1941-12-26',0,'脑梗康复期；左侧肢体力量弱；需辅助行走','孙强','儿子','15010998876','2026-06-17 19:39:04','2026-06-17 19:39:04'),(8,'黄月娥','1947-04-14',1,'睡眠质量差；血压波动；需晚间巡查','黄婷婷','女儿','18600112233','2026-06-17 19:39:04','2026-06-17 19:39:04');
/*!40000 ALTER TABLE `t_elder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_fall_record`
--

DROP TABLE IF EXISTS `t_fall_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_fall_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `fall_time` datetime NOT NULL,
  `fall_location` varchar(100) DEFAULT NULL,
  `severity` varchar(20) DEFAULT 'LOW',
  `handle_result` varchar(200) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_fall_elder_time` (`elder_id`,`fall_time`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_fall_record`
--

LOCK TABLES `t_fall_record` WRITE;
/*!40000 ALTER TABLE `t_fall_record` DISABLE KEYS */;
INSERT INTO `t_fall_record` VALUES (1,4,'2026-06-17 02:35:00','房间305','HIGH','已通知护士并完成巡查','夜间疑似跌倒，未见外伤','2026-06-17 20:01:27','2026-06-17 20:01:27'),(2,7,'2026-06-16 18:20:00','餐厅通道','MEDIUM','协助坐下休息并复查血压','步态不稳','2026-06-17 20:01:27','2026-06-17 20:01:27'),(3,1,'2026-06-22 06:35:00','房间101','LOW','护士已到场扶起，未见明显外伤','夜间起身失衡','2026-06-22 21:28:25','2026-06-22 21:46:38'),(4,2,'2026-06-22 12:18:00','餐厅通道','MEDIUM','现场处置完成，已复查血压','步态不稳，建议增加陪同','2026-06-22 21:28:25','2026-06-22 21:46:38'),(5,3,'2026-06-21 19:42:00','二楼走廊','LOW','已协助返回房间休息','转身时重心不稳','2026-06-22 21:28:25','2026-06-22 21:46:38'),(6,4,'2026-06-21 23:10:00','卫生间','HIGH','已送医务室评估','地面湿滑导致滑倒','2026-06-22 21:28:25','2026-06-22 21:46:38'),(7,5,'2026-06-20 08:25:00','花园步道','LOW','已完成巡查，持续关注步态','运动后体力不支','2026-06-22 21:28:25','2026-06-22 21:46:38'),(8,6,'2026-06-20 16:05:00','活动室','MEDIUM','已通知家属并安排观察','无明显外伤，已记录观察','2026-06-22 21:28:25','2026-06-22 21:46:38'),(9,7,'2026-06-19 07:50:00','电梯口','LOW','护士已到场扶起，未见明显外伤','疑似被杂物绊倒','2026-06-22 21:28:25','2026-06-22 21:46:38'),(10,8,'2026-06-19 21:33:00','阳台连廊','HIGH','已送医务室评估','建议调整防滑措施','2026-06-22 21:28:25','2026-06-22 21:46:38'),(11,1,'2026-06-18 10:20:00','护理站门口','MEDIUM','现场处置完成，已复查血压','步态不稳，建议增加陪同','2026-06-22 21:28:25','2026-06-22 21:46:38'),(12,2,'2026-06-18 14:46:00','房间203','LOW','已协助返回房间休息','转身时重心不稳','2026-06-22 21:28:25','2026-06-22 21:46:38'),(13,3,'2026-06-17 06:15:00','楼梯间','HIGH','已通知家属并安排观察','夜间起身失衡','2026-06-22 21:28:25','2026-06-22 21:46:38'),(14,4,'2026-06-17 15:24:00','房间305','MEDIUM','已完成巡查，持续关注步态','无明显外伤，已记录观察','2026-06-22 21:28:25','2026-06-22 21:46:38'),(15,5,'2026-06-16 09:18:00','二楼走廊','LOW','护士已到场扶起，未见明显外伤','疑似被杂物绊倒','2026-06-22 21:28:25','2026-06-22 21:46:38'),(16,6,'2026-06-16 18:52:00','餐厅通道','MEDIUM','现场处置完成，已复查血压','步态不稳，建议增加陪同','2026-06-22 21:28:25','2026-06-22 21:46:38'),(17,7,'2026-06-15 11:08:00','花园步道','LOW','已协助返回房间休息','运动后体力不支','2026-06-22 21:28:25','2026-06-22 21:46:38'),(18,8,'2026-06-15 22:12:00','卫生间','HIGH','已送医务室评估','地面湿滑导致滑倒','2026-06-22 21:28:25','2026-06-22 21:46:38'),(19,1,'2026-06-14 06:40:00','电梯口','LOW','护士已到场扶起，未见明显外伤','转身时重心不稳','2026-06-22 21:28:25','2026-06-22 21:46:38'),(20,2,'2026-06-14 13:33:00','活动室','MEDIUM','已完成巡查，持续关注步态','无明显外伤，已记录观察','2026-06-22 21:28:25','2026-06-22 21:46:38'),(21,3,'2026-06-13 08:22:00','护理站门口','LOW','已协助返回房间休息','步态不稳，建议增加陪同','2026-06-22 21:28:25','2026-06-22 21:46:38'),(22,4,'2026-06-13 20:05:00','阳台连廊','HIGH','已通知家属并安排观察','建议调整防滑措施','2026-06-22 21:28:25','2026-06-22 21:46:38'),(23,5,'2026-06-12 07:36:00','房间101','LOW','护士已到场扶起，未见明显外伤','夜间起身失衡','2026-06-22 21:28:25','2026-06-22 21:46:38'),(24,6,'2026-06-11 17:28:00','楼梯间','MEDIUM','现场处置完成，已复查血压','疑似被杂物绊倒','2026-06-22 21:28:25','2026-06-22 21:46:38'),(25,7,'2026-06-10 10:12:00','花园步道','LOW','已完成巡查，持续关注步态','运动后体力不支','2026-06-22 21:28:25','2026-06-22 21:46:38'),(26,8,'2026-06-09 21:48:00','卫生间','HIGH','已送医务室评估','地面湿滑导致滑倒','2026-06-22 21:28:25','2026-06-22 21:46:38');
/*!40000 ALTER TABLE `t_fall_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_health_data`
--

DROP TABLE IF EXISTS `t_health_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_health_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `blood_glucose` decimal(5,2) NOT NULL,
  `heart_rate` int(11) NOT NULL,
  `spo2` int(11) NOT NULL,
  `temperature` decimal(4,1) NOT NULL,
  `weight` decimal(5,1) NOT NULL,
  `record_time` datetime NOT NULL,
  `blood_pressure` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=243 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_health_data`
--

LOCK TABLES `t_health_data` WRITE;
/*!40000 ALTER TABLE `t_health_data` DISABLE KEYS */;
INSERT INTO `t_health_data` VALUES (1,1,8.90,108,99,38.1,62.3,'2026-06-22 08:10:00','131/95'),(2,2,7.80,95,96,37.6,58.2,'2026-06-22 08:25:00','146/87'),(3,4,6.70,82,94,37.0,63.8,'2026-06-21 19:40:00','161/79'),(4,7,5.60,68,92,36.5,69.8,'2026-06-21 09:15:00','117/71'),(5,6,9.10,110,99,38.3,55.2,'2026-06-20 15:30:00','132/96'),(7,1,6.90,84,94,37.2,61.9,'2026-06-21 07:45:00','161/80'),(8,1,5.80,71,92,36.6,62.1,'2026-06-16 07:40:00','118/71'),(9,1,9.20,58,90,36.1,62.1,'2026-06-17 07:40:00','133/97'),(10,1,8.10,99,97,37.9,62.3,'2026-06-22 07:40:00','147/89'),(11,1,7.00,86,95,37.3,62.9,'2026-06-18 07:40:00','162/80'),(12,1,5.90,73,93,36.8,62.9,'2026-06-19 07:40:00','119/72'),(13,1,4.80,60,90,36.2,61.5,'2026-06-20 07:35:00','133/98'),(14,2,8.20,102,98,38.0,58.6,'2026-06-18 08:05:00','148/89'),(15,2,7.20,89,96,37.5,58.6,'2026-06-19 08:05:00','163/81'),(16,2,6.10,76,93,36.9,57.2,'2026-06-20 08:10:00','119/73'),(17,2,5.00,62,91,36.4,57.8,'2026-06-21 08:15:00','134/65'),(18,2,8.40,104,98,38.1,57.4,'2026-06-16 08:05:00','149/90'),(19,2,7.30,91,96,37.6,58.0,'2026-06-17 08:05:00','163/82'),(20,2,6.20,78,94,37.1,58.2,'2026-06-22 08:05:00','120/74'),(21,3,5.10,65,91,36.5,68.1,'2026-06-19 08:30:00','135/65'),(22,3,8.50,107,99,38.3,66.7,'2026-06-20 08:35:00','149/91'),(23,3,7.40,93,97,37.7,67.3,'2026-06-21 08:40:00','164/83'),(24,3,6.30,80,94,37.2,66.9,'2026-06-16 08:30:00','121/74'),(25,3,5.30,67,92,36.7,67.5,'2026-06-17 08:30:00','136/66'),(26,3,8.70,109,99,36.1,67.7,'2026-06-22 08:30:00','150/92'),(27,3,7.60,96,97,37.9,67.7,'2026-06-18 08:30:00','165/83'),(28,4,6.50,83,95,37.3,64.0,'2026-06-21 09:05:00','122/75'),(29,4,5.40,70,92,36.8,64.2,'2026-06-16 09:00:00','136/67'),(30,4,8.80,111,90,36.3,64.2,'2026-06-17 09:00:00','151/93'),(31,4,7.70,98,98,38.0,64.4,'2026-06-22 09:00:00','108/84'),(32,4,6.60,85,95,37.5,65.0,'2026-06-18 09:00:00','122/76'),(33,4,5.50,72,93,36.9,65.0,'2026-06-19 09:00:00','137/68'),(34,4,9.00,59,90,36.4,63.6,'2026-06-20 09:05:00','152/93'),(35,5,7.90,101,98,38.2,68.9,'2026-06-16 09:25:00','108/85'),(36,5,6.80,87,96,37.6,68.9,'2026-06-17 09:25:00','123/77'),(37,5,5.70,74,93,37.1,69.1,'2026-06-22 09:25:00','138/68'),(38,5,9.10,61,91,36.5,69.7,'2026-06-18 09:25:00','152/94'),(39,5,8.00,103,99,38.3,69.7,'2026-06-19 09:25:00','109/86'),(40,5,6.90,90,96,37.8,68.3,'2026-06-20 09:30:00','124/77'),(41,5,5.80,77,94,37.2,68.9,'2026-06-21 09:35:00','138/69'),(42,6,9.20,63,92,36.7,48.6,'2026-06-18 10:10:00','153/95'),(43,6,8.10,105,99,36.1,56.6,'2026-06-19 10:10:00','110/87'),(44,6,7.10,92,97,37.9,55.6,'2026-06-21 10:05:00','124/78'),(45,6,6.00,79,94,37.4,55.2,'2026-06-16 10:10:00','139/70'),(46,6,4.90,66,92,36.8,55.8,'2026-06-17 10:10:00','154/96'),(47,6,8.30,108,90,36.3,56.0,'2026-06-22 10:10:00','110/87'),(48,7,7.20,95,97,38.0,69.2,'2026-06-20 10:30:00','125/79'),(49,7,6.10,81,95,37.5,69.8,'2026-06-21 10:30:00','140/71'),(50,7,5.00,68,93,37.0,70.0,'2026-06-16 10:35:00','155/96'),(51,7,8.40,110,90,36.4,76.8,'2026-06-22 10:35:00','111/88'),(52,7,7.30,97,98,38.2,70.2,'2026-06-17 10:35:00','126/80'),(53,7,6.30,84,95,37.6,70.8,'2026-06-18 10:35:00','141/72'),(54,7,5.20,71,93,37.1,76.8,'2026-06-19 10:35:00','155/97'),(55,8,8.60,112,91,36.6,60.2,'2026-06-21 11:00:00','112/89'),(56,8,7.50,99,98,36.0,60.4,'2026-06-16 11:05:00','127/81'),(57,8,6.40,86,96,37.8,60.4,'2026-06-22 11:05:00','141/72'),(58,8,5.30,73,94,37.2,60.6,'2026-06-17 11:05:00','156/98'),(59,8,8.70,60,91,36.7,61.2,'2026-06-18 11:05:00','113/90'),(60,8,7.60,102,99,36.2,61.2,'2026-06-19 11:05:00','127/81'),(61,8,6.50,89,96,37.9,59.8,'2026-06-20 11:00:00','142/73'),(186,1,5.80,71,97,36.3,67.9,'2026-06-23 10:30:00','116/71'),(187,2,6.50,76,94,36.5,59.3,'2026-06-23 13:37:00','120/74'),(188,3,7.20,81,97,36.7,71.7,'2026-06-23 16:44:00','124/77'),(189,4,5.70,86,94,36.9,60.6,'2026-06-23 07:51:00','128/80'),(190,5,6.40,91,97,37.1,65.6,'2026-06-23 10:58:00','132/83'),(191,6,7.10,96,94,37.3,57.9,'2026-06-23 13:05:00','136/86'),(192,7,5.60,101,97,36.2,69.6,'2026-06-23 16:12:00','140/89'),(193,8,6.30,106,94,36.4,54.9,'2026-06-23 07:19:00','144/68'),(194,1,7.00,69,97,36.6,68.1,'2026-06-24 10:31:00','148/71'),(195,2,5.50,74,94,36.8,59.5,'2026-06-24 13:38:00','113/74'),(196,3,6.20,79,97,37.0,71.9,'2026-06-24 16:45:00','117/77'),(197,4,6.90,84,94,37.2,60.8,'2026-06-24 07:52:00','121/80'),(198,5,5.40,89,97,36.1,65.8,'2026-06-24 10:59:00','125/83'),(199,6,6.10,94,94,36.3,58.1,'2026-06-24 13:06:00','129/86'),(200,7,6.80,99,97,36.5,69.8,'2026-06-24 16:13:00','133/89'),(201,8,5.30,104,94,36.7,55.1,'2026-06-24 07:20:00','137/68'),(202,1,6.00,67,97,36.9,68.3,'2026-06-25 10:32:00','141/71'),(203,2,6.70,72,94,37.1,59.7,'2026-06-25 13:39:00','145/74'),(204,3,5.20,77,97,37.3,72.1,'2026-06-25 16:46:00','149/77'),(205,4,5.90,82,94,36.2,61.0,'2026-06-25 07:53:00','114/80'),(206,5,6.60,87,97,36.4,66.0,'2026-06-25 10:00:00','118/83'),(207,6,5.10,92,94,36.6,58.3,'2026-06-25 13:07:00','122/86'),(208,7,5.80,97,97,36.8,70.0,'2026-06-25 16:14:00','126/89'),(209,8,6.50,102,94,37.0,55.3,'2026-06-25 07:21:00','130/68'),(210,1,7.20,107,97,37.2,68.5,'2026-06-26 10:33:00','134/71'),(211,2,5.70,70,94,36.1,59.9,'2026-06-26 13:40:00','138/74'),(212,3,6.40,75,97,36.3,72.3,'2026-06-26 16:47:00','142/77'),(213,4,7.10,80,94,36.5,61.2,'2026-06-26 07:54:00','146/80'),(214,5,5.60,85,97,36.7,66.2,'2026-06-26 10:01:00','150/83'),(215,6,6.30,90,94,36.9,58.5,'2026-06-26 13:08:00','115/86'),(216,7,7.00,95,97,37.1,70.2,'2026-06-26 16:15:00','119/89'),(217,8,5.50,100,94,37.3,55.5,'2026-06-26 07:22:00','123/68'),(218,1,6.20,105,97,36.2,68.7,'2026-06-27 10:34:00','127/71'),(219,2,6.90,68,94,36.4,60.1,'2026-06-27 13:41:00','131/74'),(220,3,5.40,73,97,36.6,72.5,'2026-06-27 16:48:00','135/77'),(221,4,6.10,78,94,36.8,61.4,'2026-06-27 07:55:00','139/80'),(222,5,6.80,83,97,37.0,66.4,'2026-06-27 10:02:00','143/83'),(223,6,5.30,88,94,37.2,58.7,'2026-06-27 13:09:00','147/86'),(224,7,6.00,93,97,36.1,70.4,'2026-06-27 16:16:00','112/89'),(225,8,6.70,98,94,36.3,55.7,'2026-06-27 07:23:00','116/68'),(226,1,5.20,103,97,36.5,68.9,'2026-06-28 10:35:00','120/71'),(227,2,5.90,66,94,36.7,60.3,'2026-06-28 13:42:00','124/74'),(228,3,6.60,71,97,36.9,72.7,'2026-06-28 16:49:00','128/77'),(229,4,5.10,76,94,37.1,61.6,'2026-06-28 07:56:00','132/80'),(230,5,5.80,81,97,37.3,66.6,'2026-06-28 10:03:00','136/83'),(231,6,6.50,86,94,36.2,58.9,'2026-06-28 13:10:00','140/86'),(232,7,7.20,91,97,36.4,70.6,'2026-06-28 16:17:00','144/89'),(233,8,5.70,96,94,36.6,55.9,'2026-06-28 07:24:00','148/68'),(234,1,6.40,101,97,36.8,69.1,'2026-06-29 10:36:00','113/71'),(235,2,7.10,106,94,37.0,60.5,'2026-06-29 13:43:00','117/74'),(236,3,5.60,69,97,37.2,72.9,'2026-06-29 16:50:00','121/77'),(237,4,6.30,74,94,36.1,61.8,'2026-06-29 07:57:00','125/80'),(238,5,7.00,79,97,36.3,66.8,'2026-06-29 10:04:00','129/83'),(239,6,5.50,84,94,36.5,59.1,'2026-06-29 13:11:00','133/86'),(240,7,6.20,89,97,36.7,70.8,'2026-06-29 16:18:00','137/89'),(241,8,6.90,94,94,36.9,56.1,'2026-06-29 07:25:00','141/68'),(242,1,6.20,80,98,36.8,60.0,'2026-06-23 15:23:00','100/80');
/*!40000 ALTER TABLE `t_health_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_health_data_threshold`
--

DROP TABLE IF EXISTS `t_health_data_threshold`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_health_data_threshold` (
  `id` int(11) NOT NULL,
  `blood_pressure_high` double(8,2) DEFAULT NULL,
  `blood_pressure_low` double(8,2) DEFAULT NULL,
  `blood_glucose_high` double(8,2) DEFAULT NULL,
  `blood_glucose_low` double(8,2) DEFAULT NULL,
  `heart_rate_low` int(11) DEFAULT NULL,
  `heart_rate_high` int(11) DEFAULT NULL,
  `spo2` int(11) DEFAULT NULL,
  `temperature_high` double(8,2) DEFAULT NULL,
  `temperature_low` double(8,2) DEFAULT NULL,
  `weight` double(8,2) DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_health_data_threshold`
--

LOCK TABLES `t_health_data_threshold` WRITE;
/*!40000 ALTER TABLE `t_health_data_threshold` DISABLE KEYS */;
INSERT INTO `t_health_data_threshold` VALUES (1,140.00,90.00,7.00,3.90,60,100,95,37.50,35.50,2.00,'2026-06-22 20:51:15');
/*!40000 ALTER TABLE `t_health_data_threshold` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_operation_log`
--

DROP TABLE IF EXISTS `t_operation_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_operation_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `operator_name` varchar(50) NOT NULL,
  `module_name` varchar(50) NOT NULL,
  `operation_type` varchar(30) NOT NULL,
  `operation_content` varchar(500) NOT NULL,
  `ip_address` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT '1',
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=307 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_operation_log`
--

LOCK TABLES `t_operation_log` WRITE;
/*!40000 ALTER TABLE `t_operation_log` DISABLE KEYS */;
INSERT INTO `t_operation_log` VALUES (9,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 21:04:44'),(10,'zhangsan','健康数据阈值设置','修改','修改健康数据阈值','127.0.0.1',1,'2026-06-16 21:04:53'),(11,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 21:07:21'),(12,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 21:10:40'),(13,'zhangsan','user','update','update user: zhangsan','127.0.0.1',1,'2026-06-16 21:10:53'),(14,'zhangsan','user','add','add user: admin','127.0.0.1',1,'2026-06-16 21:12:11'),(15,'zhangsan','user','update','update user: admin','127.0.0.1',1,'2026-06-16 21:12:17'),(16,'admin','system login','login','login success','127.0.0.1',1,'2026-06-16 21:15:01'),(17,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 21:18:33'),(18,'admin','system login','login','login success','127.0.0.1',1,'2026-06-16 21:20:29'),(19,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-16 21:20:31'),(20,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-16 21:20:38'),(21,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-16 21:20:40'),(22,'test','system login','login','login failed','127.0.0.1',0,'2026-06-16 21:20:44'),(23,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 21:20:48'),(24,'zhangsan','system logout','logout','logout success','127.0.0.1',1,'2026-06-16 21:22:49'),(25,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 21:22:53'),(26,'zhangsan','system logout','logout','logout success','127.0.0.1',1,'2026-06-16 21:23:13'),(28,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-16 22:12:12'),(29,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-16 22:12:17'),(30,'zhangsan','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-16 22:16:00'),(31,'zhangsan','system login','login','login success','127.0.0.1',1,'2026-06-17 16:31:35'),(32,'zhangsan','system logout','logout','logout success','127.0.0.1',1,'2026-06-17 16:31:49'),(33,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 16:31:52'),(34,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 17:20:59'),(35,'unknown','elder','add','add elder: 测试老人172133','127.0.0.1',1,'2026-06-17 17:21:33'),(36,'unknown','elder','update','update elder: 大国','127.0.0.1',1,'2026-06-17 17:21:34'),(37,'admin','elder','add','add elder: 测试','127.0.0.1',1,'2026-06-17 17:25:33'),(38,'admin','elder','delete','delete elder id: 14','127.0.0.1',1,'2026-06-17 17:25:42'),(40,'admin','elder','update','update elder: 大国','127.0.0.1',1,'2026-06-17 17:25:59'),(41,'admin','elder','update','update elder: 大国','127.0.0.1',1,'2026-06-17 17:26:05'),(42,'admin','user','update','update user: test','127.0.0.1',1,'2026-06-17 17:29:26'),(43,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-17 17:29:50'),(44,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-17 17:30:00'),(45,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-17 17:30:03'),(46,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-17 17:30:05'),(47,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 18:14:03'),(48,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 18:14:17'),(49,'admin','user','update','update user: admin','127.0.0.1',1,'2026-06-17 18:14:40'),(50,'admin','role','add','add role: 3','127.0.0.1',1,'2026-06-17 18:15:15'),(51,'admin','role','delete','delete role: 3','127.0.0.1',1,'2026-06-17 18:15:19'),(52,'admin','role','update','update role: admin','127.0.0.1',1,'2026-06-17 18:15:31'),(53,'admin','user','update','update user: test','127.0.0.1',1,'2026-06-17 18:15:49'),(54,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-17 18:15:51'),(55,'test','system login','login','login failed','127.0.0.1',0,'2026-06-17 18:15:55'),(56,'test','system login','login','login failed','127.0.0.1',0,'2026-06-17 18:16:00'),(57,'test','system login','login','login success','127.0.0.1',1,'2026-06-17 18:16:08'),(58,'test','role','add','add role: 132','127.0.0.1',1,'2026-06-17 18:16:31'),(59,'test','role','delete','delete role: 132','127.0.0.1',1,'2026-06-17 18:16:36'),(60,'test','system login','login','login failed','127.0.0.1',0,'2026-06-17 18:47:38'),(61,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 18:47:38'),(62,'test','system login','login','login failed','127.0.0.1',0,'2026-06-17 18:52:38'),(63,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 18:52:44'),(64,'admin','user','update','update user: test','127.0.0.1',1,'2026-06-17 18:53:21'),(65,'admin','user','update','update user: test','127.0.0.1',1,'2026-06-17 18:53:24'),(66,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-17 19:00:24'),(67,'312','system login','login','login failed','127.0.0.1',0,'2026-06-17 19:00:27'),(68,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:00:33'),(69,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:04:53'),(70,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:05:32'),(71,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:10:58'),(72,'admin','behavior report','generate','generate daily report id: 2','127.0.0.1',1,'2026-06-17 19:10:58'),(73,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:14:20'),(74,'admin','behavior report','generate','generate daily report id: 2','127.0.0.1',1,'2026-06-17 19:14:20'),(75,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:20:49'),(76,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:25:35'),(77,'admin','behavior report','generate','generate daily report id: 2','127.0.0.1',1,'2026-06-17 19:26:25'),(78,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:29:01'),(79,'admin','behavior report','generate','generate daily report id: 2','127.0.0.1',1,'2026-06-17 19:29:01'),(80,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:30:49'),(81,'admin','behavior report','generate','generate daily report id: 3','127.0.0.1',1,'2026-06-17 19:31:10'),(82,'admin','behavior report','delete','delete daily report id: 1','127.0.0.1',1,'2026-06-17 19:31:18'),(83,'admin','behavior report','generate','generate daily report id: 4','127.0.0.1',1,'2026-06-17 19:31:27'),(84,'admin','elder','delete','delete elder id: 12','127.0.0.1',1,'2026-06-17 19:32:06'),(85,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:34:21'),(86,'test','system login','login','login failed','127.0.0.1',0,'2026-06-17 19:34:49'),(87,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:34:58'),(88,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 19:39:24'),(89,'admin','behavior report','generate','generate daily report id: 3','127.0.0.1',1,'2026-06-17 19:40:46'),(90,'admin','behavior report','delete','delete daily report id: 3','127.0.0.1',1,'2026-06-17 19:40:51'),(91,'admin','behavior report','generate','generate daily report id: 4','127.0.0.1',1,'2026-06-17 19:40:58'),(92,'admin','user','update','update user: test','127.0.0.1',1,'2026-06-17 19:41:25'),(93,'admin','user','update','update user: admin','127.0.0.1',1,'2026-06-17 19:41:32'),(94,'admin','user','update','update user: admin','127.0.0.1',1,'2026-06-17 19:41:35'),(95,'admin','behavior report','add','add behavior record: test','127.0.0.1',1,'2026-06-17 19:42:21'),(96,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:01:51'),(97,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:04:15'),(98,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:06:15'),(99,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:07:57'),(100,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:08:54'),(101,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:09:18'),(102,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-17 20:13:00'),(103,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:13:01'),(104,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:13:06'),(105,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-17 20:13:54'),(106,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:19:25'),(107,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:28:33'),(108,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:29:00'),(109,'admin','system login','login','login success','127.0.0.1',1,'2026-06-17 20:31:54'),(110,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:00:40'),(111,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:24:09'),(112,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:24:54'),(113,' admin','system login','login','login failed','127.0.0.1',0,'2026-06-18 10:34:46'),(114,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:34:54'),(115,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:41:46'),(116,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:42:11'),(117,'admin','reminder task','add','add reminder task: 测试提醒任务','127.0.0.1',1,'2026-06-18 10:42:11'),(118,'admin','accompany record','add','add accompany record: 测试护理员','127.0.0.1',1,'2026-06-18 10:42:11'),(119,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 10:48:53'),(120,'admin','reminder task','add','add reminder task: 餐后用药','127.0.0.1',1,'2026-06-18 10:49:23'),(121,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 11:05:32'),(122,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 14:33:25'),(123,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 14:40:19'),(124,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 14:40:19'),(125,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 14:41:58'),(126,'admin','voice terminal','add','add voice terminal: VT-1001','127.0.0.1',1,'2026-06-18 14:45:52'),(127,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 14:53:50'),(128,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 14:53:50'),(129,'admin','system login','login','login success','127.0.0.1',1,'2026-06-18 15:09:26'),(130,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 10:00:50'),(131,'admin','edge gateway','update','update edge gateway: GW-1002','127.0.0.1',1,'2026-06-22 10:02:47'),(132,'admin','edge gateway','update','update edge gateway: GW-1001','127.0.0.1',1,'2026-06-22 10:02:52'),(133,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 10:36:42'),(134,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 10:39:50'),(135,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 10:42:16'),(136,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 10:47:01'),(137,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 10:47:06'),(138,'admin','user','update','update user: zhangsan','127.0.0.1',1,'2026-06-22 10:54:18'),(139,'admin','user','update','update user: lisi','127.0.0.1',1,'2026-06-22 10:54:24'),(140,'admin','reminder task','update','update reminder task: 餐后用药','127.0.0.1',1,'2026-06-22 10:55:37'),(141,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 10:57:10'),(142,'admin','accompany record','add','add accompany record: 1','127.0.0.1',1,'2026-06-22 11:12:41'),(143,'admin','accompany record','delete','delete accompany record: 1','127.0.0.1',1,'2026-06-22 11:12:48'),(144,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 11:32:12'),(145,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 11:35:29'),(146,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 19:37:33'),(147,'admin','role','update','update role: admin','127.0.0.1',1,'2026-06-22 19:38:01'),(148,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 19:41:48'),(149,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 19:42:13'),(150,'admin','health data','add','add health data: elderId=1','127.0.0.1',1,'2026-06-22 19:42:13'),(151,'admin','health data','update','update health data: id=6','127.0.0.1',1,'2026-06-22 19:42:13'),(152,'admin','health data','delete','delete health data: id=6','127.0.0.1',1,'2026-06-22 19:42:13'),(153,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 19:43:08'),(154,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 19:48:37'),(155,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 19:48:41'),(156,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:14:04'),(157,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 20:15:47'),(158,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:15:53'),(159,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:17:50'),(160,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:20:16'),(161,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 20:20:23'),(162,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:20:30'),(163,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 20:23:42'),(164,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:24:12'),(165,'admin','health data','update','update health data: id=1','127.0.0.1',1,'2026-06-22 20:25:29'),(166,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 20:31:15'),(167,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:31:26'),(168,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:31:43'),(169,'admin','health data','add','add health data: elderId=1','127.0.0.1',1,'2026-06-22 20:31:43'),(170,'admin','health data','update','update health data: id=70','127.0.0.1',1,'2026-06-22 20:31:44'),(171,'admin','health data','delete','delete health data: id=70','127.0.0.1',1,'2026-06-22 20:31:44'),(172,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:35:26'),(173,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:42:11'),(174,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 20:43:14'),(175,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:43:18'),(176,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:43:35'),(177,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:43:46'),(178,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:43:57'),(179,'admin','health data','update','update health data: id=47','127.0.0.1',1,'2026-06-22 20:44:22'),(180,'admin','health data','update','update health data: id=47','127.0.0.1',1,'2026-06-22 20:44:26'),(181,'admin','health data','add','add health data: elderId=8','127.0.0.1',1,'2026-06-22 20:44:51'),(182,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:46:36'),(183,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:48:19'),(184,'admin','elder','add','add elder: 联调测试老人204819','127.0.0.1',1,'2026-06-22 20:48:19'),(185,'admin','elder','update','update elder: 联调测试老人204819-改','127.0.0.1',1,'2026-06-22 20:48:19'),(186,'admin','elder','delete','delete elder id: 9','127.0.0.1',1,'2026-06-22 20:48:19'),(187,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:48:20'),(188,'admin','health data','add','add health data: elderId=1','127.0.0.1',1,'2026-06-22 20:48:20'),(189,'admin','health data','update','update health data: id=72','127.0.0.1',1,'2026-06-22 20:48:20'),(190,'admin','health data','delete','delete health data: id=72','127.0.0.1',1,'2026-06-22 20:48:20'),(191,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:49:30'),(192,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 20:50:00'),(193,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:50:08'),(194,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:51:15'),(195,'admin','elder','add','add elder: 联调老人205115','127.0.0.1',1,'2026-06-22 20:51:15'),(196,'admin','elder','update','update elder: 联调老人205115-已改','127.0.0.1',1,'2026-06-22 20:51:15'),(197,'admin','elder','delete','delete elder id: 10','127.0.0.1',1,'2026-06-22 20:51:15'),(198,'admin','health threshold','update','update health threshold','127.0.0.1',1,'2026-06-22 20:51:15'),(199,'admin','health data','add','add health data: elderId=1','127.0.0.1',1,'2026-06-22 20:51:16'),(200,'admin','health data','update','update health data: id=73','127.0.0.1',1,'2026-06-22 20:51:16'),(201,'admin','health data','delete','delete health data: id=73','127.0.0.1',1,'2026-06-22 20:51:16'),(202,'admin','behavior report','generate','generate daily report id: 5','127.0.0.1',1,'2026-06-22 20:55:18'),(203,'admin','behavior report','delete','delete daily report id: 5','127.0.0.1',1,'2026-06-22 20:55:21'),(204,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:58:35'),(205,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:59:15'),(206,'admin','elder','add','add elder: ITDIAG-elder','127.0.0.1',1,'2026-06-22 20:59:16'),(207,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 20:59:40'),(208,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:01:47'),(209,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:05:04'),(232,'admin','behavior report','generate','generate daily report id: 6','127.0.0.1',1,'2026-06-22 21:05:07'),(233,'admin','behavior report','delete','delete daily report id: 6','127.0.0.1',1,'2026-06-22 21:05:07'),(243,'admin','user','delete','delete user id: 5','127.0.0.1',1,'2026-06-22 21:05:08'),(247,'admin','elder','delete','delete elder id: 12','127.0.0.1',1,'2026-06-22 21:05:08'),(248,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:10:54'),(249,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:11:21'),(250,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:16:04'),(251,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:16:52'),(252,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:20:14'),(253,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 21:20:54'),(254,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:20:58'),(255,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:22:09'),(256,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:24:29'),(257,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:25:02'),(258,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:28:47'),(259,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:30:43'),(260,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:32:20'),(261,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:32:38'),(262,'admin','edge gateway','delete','delete edge gateway: GW-1002','127.0.0.1',1,'2026-06-22 21:34:24'),(263,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:34:48'),(264,'admin','accompany record','delete','delete accompany record: Social Worker Wang Ying','127.0.0.1',1,'2026-06-22 21:34:53'),(265,'admin','accompany record','delete','delete accompany record: Therapist Liu Na','127.0.0.1',1,'2026-06-22 21:34:54'),(266,'admin','accompany record','delete','delete accompany record: Caregiver Zhang Min','127.0.0.1',1,'2026-06-22 21:34:54'),(267,'admin','accompany record','delete','delete accompany record: Caregiver Chen Jie','127.0.0.1',1,'2026-06-22 21:34:57'),(268,'admin','accompany record','delete','delete accompany record: Therapist Zhou Qiang','127.0.0.1',1,'2026-06-22 21:34:57'),(269,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:34:57'),(270,'admin','accompany record','delete','delete accompany record: Psychological Social Worker Zhao Lin','127.0.0.1',1,'2026-06-22 21:34:59'),(271,'admin','accompany record','delete','delete accompany record: Social Worker Wang Ying','127.0.0.1',1,'2026-06-22 21:35:00'),(272,'admin','accompany record','delete','delete accompany record: Therapist Liu Na','127.0.0.1',1,'2026-06-22 21:35:01'),(273,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:36:31'),(274,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:40:13'),(275,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 21:41:03'),(276,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:43:20'),(277,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 21:43:25'),(278,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:43:28'),(279,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 21:48:11'),(280,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:01:11'),(281,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:02:05'),(282,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 22:02:37'),(283,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:02:38'),(284,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 22:05:26'),(285,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:05:27'),(286,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:07:54'),(287,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:10:07'),(288,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:14:05'),(289,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:20:51'),(290,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:20:51'),(291,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:21:58'),(292,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-22 22:22:42'),(293,'admin','system login','login','login success','127.0.0.1',1,'2026-06-22 22:22:47'),(294,'admin','user','update','update user: test','127.0.0.1',1,'2026-06-22 22:23:43'),(295,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 15:03:33'),(296,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-23 15:16:55'),(297,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 15:16:57'),(298,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-23 15:23:35'),(299,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 15:23:36'),(300,'admin','health data','add','add health data: elderId=1','127.0.0.1',1,'2026-06-23 15:24:28'),(301,'unknown','system logout','logout','logout success','127.0.0.1',1,'2026-06-23 15:39:34'),(302,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 15:39:35'),(303,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 16:19:44'),(304,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 16:31:31'),(305,'admin','system logout','logout','logout success','127.0.0.1',1,'2026-06-23 16:31:37'),(306,'admin','system login','login','login success','127.0.0.1',1,'2026-06-23 16:37:20');
/*!40000 ALTER TABLE `t_operation_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_permission`
--

DROP TABLE IF EXISTS `t_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `perm_code` varchar(80) NOT NULL,
  `perm_name` varchar(100) NOT NULL,
  `perm_url` varchar(200) NOT NULL,
  `perm_type` varchar(20) DEFAULT 'PAGE',
  `parent_id` int(11) DEFAULT '0',
  `icon` varchar(80) DEFAULT NULL,
  `sort_no` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_permission_code` (`perm_code`),
  UNIQUE KEY `uk_permission_url` (`perm_url`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_permission`
--

LOCK TABLES `t_permission` WRITE;
/*!40000 ALTER TABLE `t_permission` DISABLE KEYS */;
INSERT INTO `t_permission` VALUES (1,'elder:list','老人信息','/elder/elderList','PAGE',0,'fas fa-user-friends',10,1,'老人信息列表','2026-06-17 18:10:38','2026-06-17 18:10:38'),(2,'elder:add','新增老人','/elder/addView','PAGE',0,'fas fa-user-plus',11,1,'新增老人页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(3,'health:data','健康数据','/healthData','PAGE',0,'fas fa-heartbeat',20,1,'健康数据页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(4,'threshold:view','健康阈值设置','/hdt/getOne','PAGE',0,'fas fa-sliders-h',30,1,'健康阈值页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(5,'user:list','用户管理','/user/userList','PAGE',0,'fas fa-users-cog',40,1,'用户管理列表','2026-06-17 18:10:38','2026-06-17 18:10:38'),(6,'role:list','角色权限控制','/role/roleList','PAGE',0,'fas fa-lock',41,1,'角色权限控制页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(7,'permission:list','权限资源管理','/permission/permissionList','PAGE',0,'fas fa-key',42,1,'权限资源管理页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(8,'operationLog:list','操作日志','/operationLog/operationLogList','PAGE',0,'fas fa-history',50,1,'操作日志页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(9,'dictionary:list','数据字典','/dictionary/dictionaryList','PAGE',0,'fas fa-book',60,1,'数据字典页面','2026-06-17 18:10:38','2026-06-17 18:10:38'),(10,'report:daily','日常报告生成','/report/dailyReportList','PAGE',0,'fas fa-file-signature',70,1,'日常行为报告生成页面','2026-06-17 19:03:26','2026-06-17 19:03:26'),(11,'report:behavior','行为记录管理','/report/behaviorRecordList','PAGE',0,'fas fa-database',71,1,'行为记录管理页面','2026-06-17 19:03:26','2026-06-17 19:03:26'),(12,'record:fall','跌倒记录','/record/fallRecordList','PAGE',0,'fas fa-person-falling',15,1,'跌倒记录列表','2026-06-17 20:01:27','2026-06-17 20:01:27'),(13,'record:dialog','对话记录','/record/dialogRecordList','PAGE',0,'fas fa-comments',16,1,'对话记录列表','2026-06-17 20:01:27','2026-06-17 20:01:27'),(14,'record:camera','摄像头管理','/record/cameraDeviceList','PAGE',0,'fas fa-video',17,1,'摄像头管理列表','2026-06-17 20:01:27','2026-06-17 20:01:27'),(15,'emotion:reminder','提醒任务','/record/reminderTaskList','PAGE',0,'fas fa-tasks',18,1,'提醒任务列表','2026-06-18 10:21:16','2026-06-18 10:21:16'),(16,'emotion:accompany','陪伴记录','/record/accompanyRecordList','PAGE',0,'fas fa-hand-holding-heart',19,1,'陪伴记录列表','2026-06-18 10:21:16','2026-06-18 10:21:16'),(17,'record:voiceTerminal','??????','/record/voiceTerminalList','PAGE',0,'fas fa-microphone-alt',18,1,'????????','2026-06-18 14:37:58','2026-06-18 14:37:58'),(18,'record:edgeGateway','??????','/record/edgeGatewayList','PAGE',0,'fas fa-network-wired',19,1,'????????','2026-06-18 14:51:33','2026-06-18 14:51:33'),(19,'report:checkup','????','/report/checkupRecordList','PAGE',0,'fas fa-file-medical',24,1,'????????','2026-06-22 10:36:08','2026-06-22 10:36:08'),(20,'elder:healthData','健康数据','/healthData/list','PAGE',0,'fas fa-heartbeat',14,1,'老人健康数据管理','2026-06-22 19:39:13','2026-06-22 19:39:13'),(22,'operationLog:legacy','操作日志旧路径兼容','/log/operationLogList','PAGE',0,'fas fa-history',51,1,'兼容旧版操作日志入口','2026-06-22 22:21:40','2026-06-22 22:21:40');
/*!40000 ALTER TABLE `t_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_reminder_task`
--

DROP TABLE IF EXISTS `t_reminder_task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_reminder_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `elder_id` int(11) NOT NULL,
  `task_title` varchar(100) NOT NULL,
  `task_type` varchar(30) NOT NULL,
  `remind_time` datetime NOT NULL,
  `repeat_rule` varchar(20) DEFAULT 'ONCE',
  `status` varchar(20) DEFAULT 'PENDING',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `idx_reminder_elder_time` (`elder_id`,`remind_time`)
) ENGINE=InnoDB AUTO_INCREMENT=95 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_reminder_task`
--

LOCK TABLES `t_reminder_task` WRITE;
/*!40000 ALTER TABLE `t_reminder_task` DISABLE KEYS */;
INSERT INTO `t_reminder_task` VALUES (1,1,'早餐后服降压药','MEDICATION','2026-06-18 08:30:00','DAILY','PENDING','服药后观察半小时','2026-06-18 10:21:16','2026-06-18 10:21:16'),(2,2,'餐前测血糖','MEDICATION','2026-06-18 07:20:00','DAILY','PENDING','空腹血糖记录到健康档案','2026-06-18 10:21:16','2026-06-18 10:21:16'),(3,4,'晚间巡房提醒','FOLLOW_UP','2026-06-18 21:00:00','DAILY','PENDING','重点关注夜间起夜和跌倒风险','2026-06-18 10:21:16','2026-06-18 10:21:16'),(5,6,'餐后用药','ACTIVITY','2026-06-18 10:49:00','DAILY','PENDING','重点关注','2026-06-18 10:49:23','2026-06-22 10:55:37'),(7,1,'晨间降压药','MEDICATION','2026-06-16 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(8,2,'早餐后维生素','MEDICATION','2026-06-16 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(9,3,'午间血糖药','MEDICATION','2026-06-16 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(10,4,'晚间降脂药','MEDICATION','2026-06-16 19:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(11,5,'睡前安神药','MEDICATION','2026-06-16 21:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(12,6,'晨间降压药','MEDICATION','2026-06-16 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(13,7,'早餐后维生素','MEDICATION','2026-06-16 08:45:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(14,8,'晚间钙片','MEDICATION','2026-06-16 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(15,1,'晨间降压药','MEDICATION','2026-06-17 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(16,2,'早餐后维生素','MEDICATION','2026-06-17 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(17,3,'午间血糖药','MEDICATION','2026-06-17 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(18,4,'晚间降脂药','MEDICATION','2026-06-17 19:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(19,5,'睡前安神药','MEDICATION','2026-06-17 21:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(20,6,'晨间降压药','MEDICATION','2026-06-17 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(21,7,'早餐后维生素','MEDICATION','2026-06-17 08:45:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(22,8,'晚间钙片','MEDICATION','2026-06-17 20:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(23,1,'晨间降压药','MEDICATION','2026-06-18 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(24,2,'早餐后维生素','MEDICATION','2026-06-18 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(25,3,'午间血糖药','MEDICATION','2026-06-18 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(26,4,'晚间降脂药','MEDICATION','2026-06-18 19:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(27,5,'睡前安神药','MEDICATION','2026-06-18 21:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(28,6,'晨间降压药','MEDICATION','2026-06-18 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(29,7,'早餐后维生素','MEDICATION','2026-06-18 08:45:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(30,8,'晚间钙片','MEDICATION','2026-06-18 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(31,1,'晨间降压药','MEDICATION','2026-06-19 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(32,2,'早餐后维生素','MEDICATION','2026-06-19 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(33,3,'午间血糖药','MEDICATION','2026-06-19 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(34,4,'晚间降脂药','MEDICATION','2026-06-19 19:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(35,5,'睡前安神药','MEDICATION','2026-06-19 21:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(36,6,'晨间降压药','MEDICATION','2026-06-19 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(37,7,'早餐后维生素','MEDICATION','2026-06-19 08:45:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(38,8,'晚间钙片','MEDICATION','2026-06-19 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(39,1,'晨间降压药','MEDICATION','2026-06-20 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(40,2,'早餐后维生素','MEDICATION','2026-06-20 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(41,3,'午间血糖药','MEDICATION','2026-06-20 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(42,4,'晚间降脂药','MEDICATION','2026-06-20 19:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(43,5,'睡前安神药','MEDICATION','2026-06-20 21:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(44,6,'晨间降压药','MEDICATION','2026-06-20 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(45,7,'早餐后维生素','MEDICATION','2026-06-20 08:45:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(46,8,'晚间钙片','MEDICATION','2026-06-20 20:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(47,1,'晨间降压药','MEDICATION','2026-06-21 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(48,2,'早餐后维生素','MEDICATION','2026-06-21 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(49,3,'午间血糖药','MEDICATION','2026-06-21 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(50,4,'晚间降脂药','MEDICATION','2026-06-21 19:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(51,5,'睡前安神药','MEDICATION','2026-06-21 21:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(52,6,'晨间降压药','MEDICATION','2026-06-21 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(53,7,'早餐后维生素','MEDICATION','2026-06-21 08:45:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(54,8,'晚间钙片','MEDICATION','2026-06-21 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(55,1,'晨间降压药','MEDICATION','2026-06-22 08:00:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(56,2,'早餐后维生素','MEDICATION','2026-06-22 08:30:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(57,3,'午间血糖药','MEDICATION','2026-06-22 12:10:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(58,4,'晚间降脂药','MEDICATION','2026-06-22 19:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(59,5,'睡前安神药','MEDICATION','2026-06-22 21:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(60,6,'晨间降压药','MEDICATION','2026-06-22 08:15:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(61,7,'早餐后维生素','MEDICATION','2026-06-22 08:45:00','DAILY','DONE',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(62,8,'晚间钙片','MEDICATION','2026-06-22 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(63,1,'明日晨间降压药','MEDICATION','2026-06-23 08:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(64,2,'明日早餐后维生素','MEDICATION','2026-06-23 08:30:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(65,3,'明日午间血糖药','MEDICATION','2026-06-23 12:10:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(66,4,'明日晚间降脂药','MEDICATION','2026-06-23 19:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(67,5,'明日睡前安神药','MEDICATION','2026-06-23 21:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(68,6,'明日晨间降压药','MEDICATION','2026-06-23 08:15:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(69,7,'明日早餐后维生素','MEDICATION','2026-06-23 08:45:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(70,8,'明日晚间钙片','MEDICATION','2026-06-23 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(71,1,'一周用药计划','MEDICATION','2026-06-24 08:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(72,2,'一周用药计划','MEDICATION','2026-06-24 08:30:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(73,3,'一周用药计划','MEDICATION','2026-06-24 12:10:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(74,4,'一周用药计划','MEDICATION','2026-06-24 19:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(75,5,'一周用药计划','MEDICATION','2026-06-25 21:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(76,6,'一周用药计划','MEDICATION','2026-06-25 08:15:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(77,7,'一周用药计划','MEDICATION','2026-06-25 08:45:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(78,8,'一周用药计划','MEDICATION','2026-06-25 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(79,1,'一周用药计划','MEDICATION','2026-06-26 08:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(80,2,'一周用药计划','MEDICATION','2026-06-26 08:30:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(81,3,'一周用药计划','MEDICATION','2026-06-26 12:10:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(82,4,'一周用药计划','MEDICATION','2026-06-26 19:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(83,5,'一周用药计划','MEDICATION','2026-06-27 21:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(84,6,'一周用药计划','MEDICATION','2026-06-27 08:15:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(85,7,'一周用药计划','MEDICATION','2026-06-27 08:45:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(86,8,'一周用药计划','MEDICATION','2026-06-27 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(87,1,'一周用药计划','MEDICATION','2026-06-28 08:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(88,2,'一周用药计划','MEDICATION','2026-06-28 08:30:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(89,3,'一周用药计划','MEDICATION','2026-06-28 12:10:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(90,4,'一周用药计划','MEDICATION','2026-06-28 19:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(91,5,'一周用药计划','MEDICATION','2026-06-29 21:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(92,6,'一周用药计划','MEDICATION','2026-06-29 08:15:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(93,7,'一周用药计划','MEDICATION','2026-06-29 08:45:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38'),(94,8,'一周用药计划','MEDICATION','2026-06-29 20:00:00','DAILY','PENDING',NULL,'2026-06-22 21:14:06','2026-06-22 21:46:38');
/*!40000 ALTER TABLE `t_reminder_task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role`
--

DROP TABLE IF EXISTS `t_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_code` varchar(50) NOT NULL,
  `role_name` varchar(100) NOT NULL,
  `sort_no` int(11) DEFAULT '0',
  `status` int(11) DEFAULT '1',
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_role_code` (`role_code`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role`
--

LOCK TABLES `t_role` WRITE;
/*!40000 ALTER TABLE `t_role` DISABLE KEYS */;
INSERT INTO `t_role` VALUES (1,'admin','系统管理员',1,1,'拥有全部权限','2026-06-17 18:10:38','2026-06-22 19:38:01'),(2,'nurse','护理员',2,1,'日常护理与老人信息维护','2026-06-17 18:10:38','2026-06-17 18:10:38');
/*!40000 ALTER TABLE `t_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_role_permission`
--

DROP TABLE IF EXISTS `t_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_role_permission` (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_role_permission`
--

LOCK TABLES `t_role_permission` WRITE;
/*!40000 ALTER TABLE `t_role_permission` DISABLE KEYS */;
INSERT INTO `t_role_permission` VALUES (1,1,'2026-06-22 19:38:01'),(1,2,'2026-06-22 19:38:01'),(1,3,'2026-06-22 19:38:01'),(1,4,'2026-06-22 19:38:01'),(1,5,'2026-06-22 19:38:01'),(1,6,'2026-06-22 19:38:01'),(1,7,'2026-06-22 19:38:01'),(1,8,'2026-06-22 19:38:01'),(1,9,'2026-06-22 19:38:01'),(1,10,'2026-06-22 19:38:01'),(1,11,'2026-06-22 19:38:01'),(1,12,'2026-06-22 19:38:01'),(1,13,'2026-06-22 19:38:01'),(1,14,'2026-06-22 19:38:01'),(1,15,'2026-06-22 19:38:01'),(1,16,'2026-06-22 19:38:01'),(1,17,'2026-06-22 19:38:01'),(1,18,'2026-06-22 19:38:01'),(1,19,'2026-06-22 19:38:01'),(1,20,'2026-06-22 19:39:13'),(1,22,'2026-06-22 22:21:40'),(2,1,'2026-06-17 18:10:38'),(2,2,'2026-06-17 18:10:38'),(2,3,'2026-06-17 18:10:38'),(2,20,'2026-06-22 19:40:18');
/*!40000 ALTER TABLE `t_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `login_name` varchar(32) NOT NULL COMMENT '登录名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `real_name` varchar(32) DEFAULT NULL COMMENT '真实姓名',
  `job` varchar(8) DEFAULT NULL COMMENT '岗位',
  `sex` int(11) DEFAULT NULL COMMENT '性别：0=男 1=女',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `email` varchar(32) DEFAULT NULL COMMENT '邮箱',
  `status` int(11) DEFAULT NULL COMMENT '状态：0=在用 1=停用',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_user_login_name` (`login_name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'zhangsan','123123','张三','管理员',0,'15535353535','test@qq.com',1,'2026-06-15 16:48:33','2026-06-22 10:54:18'),(2,'lisi','123123','李四','护理师',0,'15535353535','123456@qq.com',1,'2026-06-15 16:49:30','2026-06-22 10:54:24'),(3,'test','123456','演示账号','访客',1,'13800000000','demo@example.com',0,'2026-06-16 20:07:50','2026-06-22 22:23:43'),(4,'admin','123456','admin','管理员',1,'123','admin@qq.com',1,'2026-06-16 21:12:11','2026-06-17 19:41:35');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user_role`
--

DROP TABLE IF EXISTS `t_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user_role`
--

LOCK TABLES `t_user_role` WRITE;
/*!40000 ALTER TABLE `t_user_role` DISABLE KEYS */;
INSERT INTO `t_user_role` VALUES (3,1,'2026-06-22 22:23:43'),(4,1,'2026-06-17 19:41:35');
/*!40000 ALTER TABLE `t_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_voice_terminal`
--

DROP TABLE IF EXISTS `t_voice_terminal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_voice_terminal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `terminal_code` varchar(50) NOT NULL,
  `terminal_name` varchar(100) NOT NULL,
  `elder_id` int(11) DEFAULT NULL,
  `install_location` varchar(100) NOT NULL,
  `device_ip` varchar(50) DEFAULT NULL,
  `wake_word` varchar(50) DEFAULT NULL,
  `volume` int(11) DEFAULT '60',
  `firmware_version` varchar(50) DEFAULT NULL,
  `status` varchar(20) DEFAULT 'ONLINE',
  `last_online_time` datetime DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_voice_terminal_code` (`terminal_code`),
  KEY `idx_voice_elder` (`elder_id`),
  KEY `idx_voice_status` (`status`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_voice_terminal`
--

LOCK TABLES `t_voice_terminal` WRITE;
/*!40000 ALTER TABLE `t_voice_terminal` DISABLE KEYS */;
INSERT INTO `t_voice_terminal` VALUES (15,'VT-ROOM-101','101房床头语音终端',1,'101房床头柜左侧','10.20.1.101','小银小银',68,'v2.3.1','ONLINE','2026-06-22 09:18:00','支持服药提醒、紧急呼叫、家属留言播报','2026-06-22 21:39:41','2026-06-22 21:47:14'),(16,'VT-ROOM-203','203房客厅语音终端',2,'203房客厅电视柜','10.20.1.203','小银小银',62,'v2.3.1','ONLINE','2026-06-22 09:12:00','绑定日程提醒和语音问答服务','2026-06-22 21:39:41','2026-06-22 21:47:14'),(17,'VT-ROOM-305','305房床旁语音终端',4,'305房护理床右侧','10.20.1.305','小银小银',55,'v2.2.8','MAINTENANCE','2026-06-21 18:40:00','麦克风阵列灵敏度校准中','2026-06-22 21:39:41','2026-06-22 21:47:14'),(18,'VT-NURSE-01','一楼护士站广播终端',NULL,'一楼护士站服务台','10.20.1.10','护理助手',72,'v2.4.0','ONLINE','2026-06-22 09:20:00','用于区域广播、批量提醒和紧急通知','2026-06-22 21:39:41','2026-06-22 21:47:14'),(19,'VT-ACT-01','康复活动室交互终端',5,'康复活动室入口右侧','10.20.1.88','小银小银',64,'v2.3.5','ONLINE','2026-06-22 09:10:00','支持活动签到和康复训练语音引导','2026-06-22 21:39:41','2026-06-22 21:47:14'),(20,'VT-GDN-01','花园休息区语音终端',7,'中心花园凉亭','10.20.1.41','小银小银',58,'v2.1.9','OFFLINE','2026-06-20 16:05:00','室外无线中继待更换','2026-06-22 21:39:41','2026-06-22 21:47:14'),(21,'VT-ROOM-208','208房卫生间求助终端',6,'208房卫生间门口','10.20.1.208','小银小银',70,'v2.3.0','ONLINE','2026-06-22 09:15:00','重点覆盖夜间如厕呼叫场景','2026-06-22 21:39:41','2026-06-22 21:47:14'),(22,'VT-DINING-01','餐厅分区播报终端',NULL,'一楼餐厅取餐区','10.20.1.61','护理助手',75,'v2.4.0','ONLINE','2026-06-22 09:19:00','用于餐前提醒、活动通知和寻人广播','2026-06-22 21:39:41','2026-06-22 21:47:14');
/*!40000 ALTER TABLE `t_voice_terminal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'yinfashouhu'
--

--
-- Dumping routines for database 'yinfashouhu'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-06-23 16:43:54
