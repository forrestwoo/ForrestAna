-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: footballmatches
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
-- Table structure for table `bundesliga`
--

DROP TABLE IF EXISTS `bundesliga`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bundesliga` (
  `fid` int(11) NOT NULL,
  `stime` varchar(45) DEFAULT NULL,
  `hname` varchar(45) DEFAULT NULL,
  `gname` varchar(45) DEFAULT NULL,
  `hscore` int(11) DEFAULT NULL,
  `gscore` int(11) DEFAULT NULL,
  `hhalfscore` int(11) DEFAULT NULL,
  `ghalfscore` int(11) DEFAULT NULL,
  `hstanding` int(11) DEFAULT NULL,
  `gstanding` int(11) DEFAULT NULL,
  `round` int(11) DEFAULT NULL,
  `hid` int(11) DEFAULT NULL,
  `gid` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `win` float DEFAULT NULL,
  `draw` float DEFAULT NULL,
  `lost` float DEFAULT NULL,
  `pan` varchar(45) DEFAULT NULL,
  `handline` varchar(45) DEFAULT NULL,
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bundesliga`
--

LOCK TABLES `bundesliga` WRITE;
/*!40000 ALTER TABLE `bundesliga` DISABLE KEYS */;
INSERT INTO `bundesliga` VALUES (737551,'2018-08-25 02:30','拜仁慕尼黑','霍芬海姆',3,1,1,0,0,0,1,664,1869,5,1.2,7.27,12.93,'输半','两球/两球半',NULL),(737554,'2018-08-25 21:30','杜塞尔多夫','奥格斯堡',1,2,1,0,15,2,1,781,1923,5,2.56,3.24,2.82,'输','平手',NULL),(737555,'2018-08-25 21:30','弗赖堡','法兰克福',0,2,0,1,12,13,1,804,791,5,2.45,3.2,3,'输','平手/半球',NULL),(737556,'2018-08-25 21:30','柏林赫塔','纽伦堡',1,0,1,0,16,6,1,659,1138,5,1.81,3.52,4.63,'赢','半球',NULL),(737558,'2018-08-25 21:30','云达不莱梅','汉诺威96',1,1,0,0,3,11,1,1334,862,5,1.78,3.85,4.3,'输','半球/一球',NULL),(737559,'2018-08-25 21:30','沃尔夫斯堡','沙尔克04',2,1,1,0,4,17,1,1269,478,5,3.06,3.16,2.43,'赢','受平手/半球',NULL),(737552,'2018-08-26 00:30','门兴格拉德巴赫','勒沃库森',2,0,0,0,9,11,1,1089,986,5,2.66,3.56,2.52,'赢','平手',NULL),(737557,'2018-08-26 21:30','美因茨','斯图加特',1,0,0,0,12,11,1,1088,1195,5,2.77,3.16,2.64,'赢','平手',NULL),(737553,'2018-08-26 23:59','多特蒙德','莱比锡红牛',4,1,3,1,9,10,1,786,970,5,1.71,3.97,4.57,'赢','半球/一球',NULL);
/*!40000 ALTER TABLE `bundesliga` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-30 22:29:12
