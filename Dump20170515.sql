-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: demo
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `camera`
--

DROP TABLE IF EXISTS `camera`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `camera` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `place` varchar(45) DEFAULT NULL,
  `url` varchar(128) DEFAULT NULL,
  `time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `camera`
--

LOCK TABLES `camera` WRITE;
/*!40000 ALTER TABLE `camera` DISABLE KEYS */;
INSERT INTO `camera` VALUES (2,NULL,'摄像头','我想我也不知道这是在什么地方','place001/camera001',NULL),(3,NULL,'摄像头','我想我也不知道这是在什么地方','place001/camera001',NULL),(4,NULL,'测试摄像头','我想我也不知道这是在什么地方','place001/camera001',NULL),(5,'lafite-1','lafite摄像头',NULL,'blabla','2017-05-15');
/*!40000 ALTER TABLE `camera` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `daily`
--

DROP TABLE IF EXISTS `daily`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `daily` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `person_id` int(11) DEFAULT NULL,
  `person_name` varchar(45) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL,
  `content` varchar(1024) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `isRefer` char(1) DEFAULT NULL,
  `inquirer_id` int(11) DEFAULT NULL,
  `inquirer_name` varchar(45) DEFAULT NULL,
  `feedback` varchar(1024) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_daily_login_idx` (`person_id`),
  KEY `fk_daily_login_1_idx` (`inquirer_id`),
  CONSTRAINT `fk_daily_login` FOREIGN KEY (`person_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_daily_login_1` FOREIGN KEY (`inquirer_id`) REFERENCES `user` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `daily`
--

LOCK TABLES `daily` WRITE;
/*!40000 ALTER TABLE `daily` DISABLE KEYS */;
INSERT INTO `daily` VALUES (1,1,NULL,'test','test',NULL,NULL,NULL,NULL,NULL,NULL),(3,NULL,NULL,NULL,'This is a test!',NULL,NULL,NULL,NULL,NULL,NULL),(4,1,NULL,'test','test',NULL,NULL,NULL,NULL,NULL,NULL),(5,1,NULL,'test','test',NULL,NULL,NULL,NULL,NULL,NULL),(6,1,NULL,'test','test',NULL,NULL,NULL,NULL,NULL,NULL),(7,NULL,NULL,'this is a test!!!',NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `daily` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `birth` varchar(45) DEFAULT NULL,
  `sex` char(1) DEFAULT NULL,
  `qq` varchar(45) DEFAULT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `login_name` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  `type` char(1) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'LafiteHao','80-01-07','M','375199496','18554653013','lafite','lafite123',NULL),(2,'LafiteHao','80-01-07','M','375199496','18554653013',NULL,NULL,NULL),(3,'LafiteHao','80-01-07','M','375199496','18554653013',NULL,NULL,NULL),(4,'LafiteHao','80-01-07','M','375199496','18554653013',NULL,NULL,NULL),(5,'LafiteHao',NULL,NULL,'375199496','18554653013',NULL,NULL,NULL),(6,'LafiteHao',NULL,NULL,'375199496','18554653013',NULL,NULL,NULL),(7,'LafiteHao',NULL,NULL,'375199496','18554653013',NULL,NULL,NULL),(8,'LafiteHao',NULL,NULL,'375199496','18554653013',NULL,NULL,NULL),(9,'LafiteHao',NULL,NULL,'375199496','18554653013',NULL,NULL,NULL),(10,NULL,NULL,NULL,NULL,NULL,'yika','Yika123',NULL),(11,NULL,NULL,NULL,NULL,NULL,'yika','Yika123',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-15 16:20:44
