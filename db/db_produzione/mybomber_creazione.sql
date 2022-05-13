-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mybomber
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `evento`
--

DROP TABLE IF EXISTS `evento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `evento` (
  `nome` varchar(30) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `struttura` varchar(30) NOT NULL,
  `data_evento` date NOT NULL,
  `ora` int NOT NULL,
  `e_mail_gestore` varchar(30) NOT NULL,
  `e_mail_utente` varchar(30) NOT NULL,
  `stato` varchar(30) NOT NULL,
  `valutazione` float NOT NULL,
  `numero_partecipanti` int NOT NULL,
  PRIMARY KEY (`nome`),
  KEY `e_mail_gestore` (`e_mail_gestore`),
  KEY `e_mail_utente` (`e_mail_utente`),
  KEY `struttura` (`struttura`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`e_mail_gestore`) REFERENCES `gestore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`e_mail_utente`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `evento_ibfk_3` FOREIGN KEY (`struttura`) REFERENCES `struttura` (`nome`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gestore`
--

DROP TABLE IF EXISTS `gestore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gestore` (
  `e_mail` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `password_gestore` varchar(64) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `struttura` varchar(30) NOT NULL,
  PRIMARY KEY (`e_mail`),
  KEY `struttura` (`struttura`),
  CONSTRAINT `gestore_ibfk_1` FOREIGN KEY (`struttura`) REFERENCES `struttura` (`nome`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `giocatore`
--

DROP TABLE IF EXISTS `giocatore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `giocatore` (
  `username` varchar(30) NOT NULL,
  `e_mail` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `cognome` varchar(30) NOT NULL,
  `password_giocatore` varchar(64) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `data_nascita` date NOT NULL,
  `nazione_residenza` varchar(30) NOT NULL,
  `provincia_residenza` varchar(30) NOT NULL,
  `citta_residenza` varchar(30) NOT NULL,
  `cap_residenza` varchar(10) NOT NULL,
  `valutazione` float NOT NULL,
  PRIMARY KEY (`e_mail`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `partecipazione`
--

DROP TABLE IF EXISTS `partecipazione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partecipazione` (
  `e_mail` varchar(30) NOT NULL,
  `nome_evento` varchar(30) NOT NULL,
  PRIMARY KEY (`e_mail`,`nome_evento`),
  KEY `nome_evento` (`nome_evento`),
  CONSTRAINT `partecipazione_ibfk_1` FOREIGN KEY (`e_mail`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `partecipazione_ibfk_2` FOREIGN KEY (`nome_evento`) REFERENCES `evento` (`nome`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `recensione`
--

DROP TABLE IF EXISTS `recensione`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `recensione` (
  `e_mail_recensore` varchar(30) NOT NULL,
  `e_mail_recensito` varchar(30) NOT NULL,
  `nome_evento` varchar(30) NOT NULL,
  `recensione` float NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`e_mail_recensore`,`e_mail_recensito`,`nome_evento`),
  KEY `e_mail_recensito` (`e_mail_recensito`),
  KEY `nome_evento` (`nome_evento`),
  CONSTRAINT `recensione_ibfk_1` FOREIGN KEY (`e_mail_recensore`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `recensione_ibfk_2` FOREIGN KEY (`e_mail_recensito`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `recensione_ibfk_3` FOREIGN KEY (`nome_evento`) REFERENCES `evento` (`nome`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `struttura`
--

DROP TABLE IF EXISTS `struttura`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `struttura` (
  `nome` varchar(30) NOT NULL,
  `indirizzo` varchar(30) NOT NULL,
  `nazione` varchar(30) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `cap` varchar(10) NOT NULL,
  `provincia` varchar(30) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  PRIMARY KEY (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-13 11:34:11
