-- MySQL dump 10.13  Distrib 5.7.27, for Linux (x86_64)
--
-- Host: localhost    Database: szkola_programowania
-- ------------------------------------------------------
-- Server version	5.7.27-0ubuntu0.18.04.1

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
-- Table structure for table `exercise`
--

DROP TABLE IF EXISTS `exercise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exercise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exercise`
--

LOCK TABLES `exercise` WRITE;
/*!40000 ALTER TABLE `exercise` DISABLE KEYS */;
INSERT INTO `exercise` VALUES (5,'Zadanie na logike','Opis zadania na logike'),(6,'Roznice','Znajdz roznice na obrazkach'),(7,'Rebus','Opis rebusu'),(9,'Rysowanka','Narysuj obrazek'),(10,'Kodowanie','Zakoduj program'),(14,'Kolorowanka','Pokoloruj obrazek!');
/*!40000 ALTER TABLE `exercise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solution`
--

DROP TABLE IF EXISTS `solution`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `solution` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created` datetime DEFAULT CURRENT_TIMESTAMP,
  `updated` datetime DEFAULT NULL,
  `description` text COLLATE utf8mb4_unicode_ci,
  `exercise_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `exercise_id` (`exercise_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `solution_ibfk_1` FOREIGN KEY (`exercise_id`) REFERENCES `exercise` (`id`) ON DELETE CASCADE,
  CONSTRAINT `solution_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solution`
--

LOCK TABLES `solution` WRITE;
/*!40000 ALTER TABLE `solution` DISABLE KEYS */;
INSERT INTO `solution` VALUES (4,'2019-09-05 11:17:07',NULL,'Rozwiazanie zadania logicznego przez stefanie',5,6),(6,'2019-09-05 11:18:25',NULL,'Program zakodowany przez stefanie',10,6),(7,'2019-09-05 11:19:11',NULL,'rysunek kajetana',9,7),(11,'2019-09-05 11:25:22',NULL,'rebus rozwiazany przez franka',7,9),(13,'2019-09-05 11:25:47',NULL,'roznice odnalezione przez franka',6,9),(15,'2019-09-05 11:26:44',NULL,'zadanie logiczne rozwiazne przez ewcie',5,14),(16,'2019-09-05 11:27:35',NULL,'Program zakodowany przez bartoszka',10,13),(26,'2019-10-12 18:38:38',NULL,'Jakies rozwiazanie',7,9),(27,'2019-10-27 08:46:32',NULL,'Rozwiazanie Marcysi',5,19);
/*!40000 ALTER TABLE `solution` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_group`
--

DROP TABLE IF EXISTS `user_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_group`
--

LOCK TABLES `user_group` WRITE;
/*!40000 ALTER TABLE `user_group` DISABLE KEYS */;
INSERT INTO `user_group` VALUES (6,'Wikinkowie'),(10,'Majowie'),(11,'Astekowie'),(15,'Meksykanie'),(17,'Najemnicy'),(18,'Marcepany'),(19,'Koziolki');
/*!40000 ALTER TABLE `user_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `group_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `email_2` (`email`),
  KEY `group_id` (`group_id`),
  CONSTRAINT `users_ibfk_1` FOREIGN KEY (`group_id`) REFERENCES `user_group` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (6,'dfsd','dfsf','$2a$10$ds3tvnmBjeIFBtgqqoHBaOLvd7TyG3k3N.iJp58dLiGPTu08ewe7K',15),(7,'kajetan','kajetan@yahoo.com','$2a$10$ueJg9hLYFJCJmsWT65KArOvSCvZUH99hfhxSaYtV1pJ3w0Y276tZa',NULL),(9,'franek','franek@wp.pl','$2a$10$70FPo0kTilWa3VIV291bYOumCCM89dLOYKYjeCw7gU4X2xw0n.8Oa',18),(10,'laura','laura@onet.pl','$2a$10$ETHi.QHXN9EPNlqD2.M6.OUWMQNPln3biG76POMHQZokHtQ5hpt0W',15),(11,'romek','romek@o2.pl','$2a$10$5wiSeV7A8icCm53oHX4uruoLu3LLLnZm.fYFy0M4ZRZu.2PMsERtW',18),(12,'kacperek','kacperek@gmail.com','$2a$10$DndjxulmVikXLQt3sKAdJ.lKcl1qJC65950LprPVsoUHDc0j8vd4y',10),(13,'bartoszek','bartoszek@wp.pl','$2a$10$YErNBDvO3I3LbgjTdHfFGeWXQ2bXGj9U9b1c14/zvfMkhPYpHbCxy',17),(14,'ewcia','ewcia@yahoo@com','$2a$10$K9NH2z7P76Blso1qLeAncO2KD3bu/due5T.uzwRElA.qztXJjX16m',11),(16,'michalek','michalek@gmail.com','$2a$10$00kclTBhw916gJ/V5lwZAOjg15Tzy1QS9I2lgnc7bk8eBzgXAkBIW',NULL),(17,'jolasia','jolasia@onet.pl','$2a$10$j9HXbjBM6ATFgDgvcwyW3eZtfrKl7HnFygZOA6Tt5z8qfljqkZpOG',NULL),(19,'marcysia','marcysia@onet.pl','$2a$10$Tmp77D0gw4G8nQAbiIpq5Oxl71K/bxzdxgpOJp8z8sBKj90H5eh6y',6);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-10-27  9:25:39
