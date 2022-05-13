-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: mybomber_testing
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
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES ('Evento 333','Prova descrizione','playk','2022-01-16',22,'gino@gino.it','simone@simone.it','richiesta',0,0),('evento2','grande evento','playk','2022-01-03',2,'gino@gino.it','simone@simone.it','completato',0,10),('evento3','sdfghgfds','playk','2022-01-15',1,'gino@gino.it','simone@simone.it','attivo',0,3);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gestore`
--

LOCK TABLES `gestore` WRITE;
/*!40000 ALTER TABLE `gestore` DISABLE KEYS */;
INSERT INTO `gestore` VALUES ('bario@bario.it','piero','rossi','04aa681ddff90f70cbb3ac3c46c2af38e34d19ef30857b8e57efeb4f034c2c22','3223415443','playb'),('gino@gino.it','gino','pozzo','1bdc6c65b0b55f11f4a9c541fc9467677a9936e0b962276ee1878531af03e8b6','3923415443','playk'),('olio@olio.it','olio','rossi','2470e4dd27844b7593522872b0b0690ba5527d74da10f60d054ca03ffebcec29','3923415443','playo');
/*!40000 ALTER TABLE `gestore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `giocatore`
--

LOCK TABLES `giocatore` WRITE;
/*!40000 ALTER TABLE `giocatore` DISABLE KEYS */;
INSERT INTO `giocatore` VALUES ('gio','gio4@email.it','Giovanni','Falco','ecf28e55fdeac49edf0ccb7246955f2d2f13551519c15370a52387c57a0c6a9e','3334562167','2001-11-16','Italia','Caserta','Caserta','89976',0),('mario','mario@mario.it','Mario','Calabrese','59195c6c541c8307f1da2d1e768d6f2280c984df217ad5f4c64c3542b04111a4','3452167543','2000-03-03','Italia','Avellino','Avellino','80076',0),('pino','pino@pino.it','Pino','Inglese','a55c93846cec6f8780e3f00b112d6b897e8e74b02b52ddce0280b067a3a294cf','3665423187','2000-09-09','Italia','Napoli','Napoli','80000',0),('simone','simone@simone.it','Simone','Graziano','19a0098e641d4bee278bb5d470d06679ffc5fdc818c3a1c52bfb7f8cde3752d3','3324156789','1999-08-12','Italia','Milano','Milano','78654',0);
/*!40000 ALTER TABLE `giocatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `partecipazione`
--

LOCK TABLES `partecipazione` WRITE;
/*!40000 ALTER TABLE `partecipazione` DISABLE KEYS */;
INSERT INTO `partecipazione` VALUES ('pino@pino.it','Evento 333'),('simone@simone.it','Evento 333'),('pino@pino.it','evento3'),('simone@simone.it','evento3');
/*!40000 ALTER TABLE `partecipazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES ('pino@pino.it','mario@mario.it','evento3',2,'scarso'),('pino@pino.it','simone@simone.it','evento3',2,'scarso');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `struttura`
--

LOCK TABLES `struttura` WRITE;
/*!40000 ALTER TABLE `struttura` DISABLE KEYS */;
INSERT INTO `struttura` VALUES ('playb','via piero 21','italia','napoli','80098','napoli','1233211221'),('playk','via andrea 21','italia','napoli','80098','napoli','3122122143'),('playo','via giacomo 21','italia','napoli','80098','napoli','3122122112');
/*!40000 ALTER TABLE `struttura` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-05-13 15:37:54
