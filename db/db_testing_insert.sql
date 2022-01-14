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
INSERT INTO `gestore` VALUES ('bario@bario.it','piero','rossi','bario','3223415443','playb'),('gino@gino.it','gino','pozzo','gino','3923415443','playk'),('olio@olio.it','olio','rossi','olio','3923415443','playo');
/*!40000 ALTER TABLE `gestore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `giocatore`
--

LOCK TABLES `giocatore` WRITE;
/*!40000 ALTER TABLE `giocatore` DISABLE KEYS */;
INSERT INTO `giocatore` VALUES ('gio','gio4@email.it','Giovanni','Falco','Gio','3334562167','2001-11-16','Italia','Caserta','Caserta','89976',0),('mario','mario@mario.it','Mario','Calabrese','mario','3452167543','2000-03-03','Italia','Avellino','Avellino','80076',0),('pino','pino@pino.it','Pino','Inglese','pino','3665423187','2000-09-09','Italia','Napoli','Napoli','80000',0),('simone','simone@simone.it','Simone','Graziano','simone','3324156789','1999-08-12','Italia','Milano','Milano','78654',0);
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

-- Dump completed on 2022-01-12 17:08:03
