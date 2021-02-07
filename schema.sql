-- MySQL dump 10.13  Distrib 8.0.19, for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: avaliacao
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pla_historic`
--

DROP TABLE IF EXISTS `pla_historic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pla_historic` (
  `pla_id` bigint NOT NULL,
  `tea_id` bigint NOT NULL,
  PRIMARY KEY (`pla_id`,`tea_id`),
  KEY `tea_his_fk` (`tea_id`),
  CONSTRAINT `pla_his_fk` FOREIGN KEY (`pla_id`) REFERENCES `pla_player` (`pla_id`),
  CONSTRAINT `tea_his_fk` FOREIGN KEY (`tea_id`) REFERENCES `tea_team` (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pla_historic`
--

LOCK TABLES `pla_historic` WRITE;
/*!40000 ALTER TABLE `pla_historic` DISABLE KEYS */;
INSERT INTO `pla_historic` VALUES (1,1),(2,1),(3,1),(4,2);
/*!40000 ALTER TABLE `pla_historic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pla_player`
--

DROP TABLE IF EXISTS `pla_player`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pla_player` (
  `pla_id` bigint NOT NULL AUTO_INCREMENT,
  `pla_position` varchar(15) NOT NULL,
  `pla_first_name` varchar(50) NOT NULL,
  `pla_last_name` varchar(50) NOT NULL,
  `pla_salary` float NOT NULL,
  `pla_born` date NOT NULL,
  `tea_id` bigint DEFAULT NULL,
  PRIMARY KEY (`pla_id`),
  KEY `pla_tea_fk` (`tea_id`),
  CONSTRAINT `pla_tea_fk` FOREIGN KEY (`tea_id`) REFERENCES `tea_team` (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pla_player`
--

LOCK TABLES `pla_player` WRITE;
/*!40000 ALTER TABLE `pla_player` DISABLE KEYS */;
INSERT INTO `pla_player` VALUES (1,'SHOOTING_GUARD','Tyler','Herro',3640200,'2000-02-20',1),(2,'SMALL_FORWARD','Jimmy','Butler',32740000,'1989-10-14',1),(3,'CENTER','Edrice','Adebayo',3454080,'1997-08-18',1),(4,'POINT_GUARD','Dejounte','Murray',2321740,'1996-08-19',2);
/*!40000 ALTER TABLE `pla_player` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ros_roster`
--

DROP TABLE IF EXISTS `ros_roster`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ros_roster` (
  `ros_id` bigint NOT NULL AUTO_INCREMENT,
  `ros_first_name` varchar(50) NOT NULL,
  `ros_last_name` varchar(50) NOT NULL,
  `ros_salary` float NOT NULL,
  `ros_born` date NOT NULL,
  PRIMARY KEY (`ros_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ros_roster`
--

LOCK TABLES `ros_roster` WRITE;
/*!40000 ALTER TABLE `ros_roster` DISABLE KEYS */;
INSERT INTO `ros_roster` VALUES (1,'Gregg','Popovich',8000000,'1949-02-28'),(2,'Tim','Duncan',1000000,'1976-05-25'),(3,'Tom','Thibodeau',0,'1958-02-17');
/*!40000 ALTER TABLE `ros_roster` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sta_staff`
--

DROP TABLE IF EXISTS `sta_staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sta_staff` (
  `sta_id` bigint NOT NULL,
  `sta_function` varchar(30) NOT NULL,
  `tea_id` bigint DEFAULT NULL,
  PRIMARY KEY (`sta_id`),
  KEY `sta_tea_fk` (`tea_id`),
  CONSTRAINT `sta_ros_fk` FOREIGN KEY (`sta_id`) REFERENCES `ros_roster` (`ros_id`),
  CONSTRAINT `sta_tea_fk` FOREIGN KEY (`tea_id`) REFERENCES `tea_team` (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sta_staff`
--

LOCK TABLES `sta_staff` WRITE;
/*!40000 ALTER TABLE `sta_staff` DISABLE KEYS */;
INSERT INTO `sta_staff` VALUES (1,'Head Coach',2),(2,'Assistant Coach',2),(3,'Head Coach',3);
/*!40000 ALTER TABLE `sta_staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tea_team`
--

DROP TABLE IF EXISTS `tea_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tea_team` (
  `tea_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_city` varchar(30) NOT NULL,
  `tea_name` varchar(30) NOT NULL,
  `tea_conference` varchar(4) NOT NULL,
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tea_team`
--

LOCK TABLES `tea_team` WRITE;
/*!40000 ALTER TABLE `tea_team` DISABLE KEYS */;
INSERT INTO `tea_team` VALUES (1,'Miami','Heat','EAST'),(2,'San Antonio','Spurs','WEST'),(3,'Phoenix','Suns','WEST');
/*!40000 ALTER TABLE `tea_team` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-05 21:31:21
