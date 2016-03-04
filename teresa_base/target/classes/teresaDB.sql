CREATE DATABASE  IF NOT EXISTS `teresaDB` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `teresaDB`;
-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: teresaDB
-- ------------------------------------------------------
-- Server version	5.5.47-0ubuntu0.14.04.1

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
-- Table structure for table `questions`
--

DROP TABLE IF EXISTS `questions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `questions` (
  `question_key` int(11) NOT NULL AUTO_INCREMENT,
  `question` varchar(255) DEFAULT NULL,
  `qAction` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`question_key`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `questions`
--

LOCK TABLES `questions` WRITE;
/*!40000 ALTER TABLE `questions` DISABLE KEYS */;
INSERT INTO `questions` VALUES (1,'HOW LATE IS IT','getTime'),(2,'WHAT TIME IS IT','getTime'),(3,'START STOPWATCH','startStopwatch'),(4,'STOP STOPWATCH','stopStopwatch'),(5,'STOPWATCH LAP','lapStopwatch'),(6,'WHAT DO I HAVE TO DO TODAY','getDayTasks'),(7,'LIST ALL BIRTHDAYS','getBirthdays'),(8,'GET NOTIFICATIONS','getNotifications'),(9,'GET MAILS','getMails'),(10,'GET MESSAGES','getMessages'),(11,'GET TASKS','getTasks'),(12,'HOW IS THE WEATHER','showWeather'),(13,'QUIT','quit'),(14,'CANCEL','cancel'),(15,'UPDATE','update'),(16,'STOP TRACK','stopTrack'),(17,'START TRACK','startTrack'),(18,'PAUSE','pauseTrack'),(19,'MUTE','muteTrack'),(20,'QUIETER','quiteterTrack'),(21,'LOUDER','louderTrack'),(22,'THANK YOU','goodbye');
/*!40000 ALTER TABLE `questions` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-02-27 22:27:23
