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
-- Table structure for table `dejia`
--

DROP TABLE IF EXISTS `dejia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dejia` (
  `mid` int(11) NOT NULL,
  `zhudui` varchar(100) DEFAULT NULL,
  `kedui` varchar(100) DEFAULT NULL,
  `a1` float DEFAULT NULL,
  `a2` float DEFAULT NULL,
  `a3` float DEFAULT NULL,
  `b1` float DEFAULT NULL,
  `b2` float DEFAULT NULL,
  `b3` float DEFAULT NULL,
  `c1` float DEFAULT NULL,
  `c2` float DEFAULT NULL,
  `c3` float DEFAULT NULL,
  `d1` float DEFAULT NULL,
  `d2` float DEFAULT NULL,
  `d3` float DEFAULT NULL,
  `aa1` float DEFAULT NULL,
  `aa2` float DEFAULT NULL,
  `aa3` float DEFAULT NULL,
  `bb1` float DEFAULT NULL,
  `bb2` float DEFAULT NULL,
  `bb3` float DEFAULT NULL,
  `cc1` float DEFAULT NULL,
  `cc2` float DEFAULT NULL,
  `cc3` float DEFAULT NULL,
  `dd1` float DEFAULT NULL,
  `dd2` float DEFAULT NULL,
  `dd3` float DEFAULT NULL,
  `score` varchar(45) DEFAULT NULL,
  `result` varchar(45) DEFAULT NULL,
  `zs` int(11) DEFAULT NULL,
  `ks` int(11) DEFAULT NULL,
  `weidechudashui` varchar(45) DEFAULT NULL,
  `weidechudaxiaopan` varchar(45) DEFAULT NULL,
  `weidechuxiaoshui` varchar(45) DEFAULT NULL,
  `weidezhongdashui` varchar(45) DEFAULT NULL,
  `weidezhongdaxiaopan` varchar(45) DEFAULT NULL,
  `weidezhongxiaoshui` varchar(45) DEFAULT NULL,
  `weidechuzhushui` varchar(45) DEFAULT NULL,
  `weidechupan` varchar(45) DEFAULT NULL,
  `weidechukeshui` varchar(45) DEFAULT NULL,
  `weidezhongzhushui` varchar(45) DEFAULT NULL,
  `weidezhongpan` varchar(45) DEFAULT NULL,
  `weidezhongkeshui` varchar(45) DEFAULT NULL,
  `zgt` varchar(1000) DEFAULT NULL,
  `kgt` varchar(1000) DEFAULT NULL,
  `zh` int(11) DEFAULT NULL,
  `kh` int(11) DEFAULT NULL,
  `bifa1` float DEFAULT NULL,
  `bifa2` float DEFAULT NULL,
  `bifa3` float DEFAULT NULL,
  `bifa11` float DEFAULT NULL,
  `bifa22` float DEFAULT NULL,
  `bifa33` float DEFAULT NULL,
  `m1` int(11) DEFAULT NULL,
  `m2` int(11) DEFAULT NULL,
  `m3` int(11) DEFAULT NULL,
  `wlczs` varchar(45) DEFAULT NULL,
  `wlcp` varchar(45) DEFAULT NULL,
  `wlcks` varchar(45) DEFAULT NULL,
  `wlzzs` varchar(45) DEFAULT NULL,
  `wlzp` varchar(45) DEFAULT NULL,
  `wlzks` varchar(45) DEFAULT NULL,
  `wlcd` varchar(45) DEFAULT NULL,
  `wlcq` varchar(45) DEFAULT NULL,
  `wlcx` varchar(45) DEFAULT NULL,
  `wlzd` varchar(45) DEFAULT NULL,
  `wlzq` varchar(45) DEFAULT NULL,
  `wlzx` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dejia`
--

LOCK TABLES `dejia` WRITE;
/*!40000 ALTER TABLE `dejia` DISABLE KEYS */;
INSERT INTO `dejia` VALUES (737551,'拜仁慕尼黑','霍芬海姆',1.2,6.5,13,1.3,5.8,9,1.18,8,15,1.18,7.5,14,1.29,6,8.5,1.3,6,9,1.3,6,10,1.25,6.25,10,'3:1','胜',3,1,'1.02','3.5','0.85','0.88↓','3.5','0.95↑','0.900','球半/两球','1.020','0.830↓','两球 升','1.080↑','86^79^23','59',1,0,1.23,8,14.5,1.29,5.8,9.8,-1103828,4516802,6091178,NULL,NULL,NULL,NULL,NULL,NULL,'1.05','3.5','0.70','0.73↓','3.5','1.00↑'),(737552,NULL,NULL,2.6,3.6,2.5,2.65,3.4,2.6,2.62,3.6,2.62,2.7,3.55,2.5,2.62,3.6,2.5,2.65,3.3,2.65,2.9,3.5,2.4,2.85,3.45,2.4,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,2.72,3.7,2.72,2.82,3.2,2.16,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `dejia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-30 22:29:11
