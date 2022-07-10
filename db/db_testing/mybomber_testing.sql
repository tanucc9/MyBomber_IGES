CREATE DATABASE  IF NOT EXISTS `mybomber_testing` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mybomber_testing`;
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
  `tipologia` varchar(45) NOT NULL,
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
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES ('Evento 333','Prova descrizione','playk','2022-01-16',22,'gino@gino.it','simone@simone.it','richiesta',0,0,'libero'),('evento squadra','descrizione.','playk','2022-01-15',22,'gino@gino.it','gio4@email.it','completato',0,2,'squadra'),('evento squadra 2','descrizione.','playk','2022-01-15',22,'gino@gino.it','gio4@email.it','completato',0,1,'squadra'),('evento2','grande evento','playk','2022-01-03',2,'gino@gino.it','simone@simone.it','completato',0,10,'libero'),('evento3','sdfghgfds','playk','2022-01-15',1,'gino@gino.it','simone@simone.it','attivo',0,3,'libero');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `gestore`
--

LOCK TABLES `gestore` WRITE;
/*!40000 ALTER TABLE `gestore` DISABLE KEYS */;
INSERT INTO `gestore` VALUES ('bario@bario.it','piero','rossi','04aa681ddff90f70cbb3ac3c46c2af38e34d19ef30857b8e57efeb4f034c2c22','3223415443','playb'),('gino@gino.it','gino','pozzo','1bdc6c65b0b55f11f4a9c541fc9467677a9936e0b962276ee1878531af03e8b6','3923415443','playk'),('olio@olio.it','olio','rossi','2470e4dd27844b7593522872b0b0690ba5527d74da10f60d054ca03ffebcec29','3923415443','playo');
/*!40000 ALTER TABLE `gestore` ENABLE KEYS */;
UNLOCK TABLES;

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
  `id_squadra` int DEFAULT NULL,
  PRIMARY KEY (`e_mail`),
  KEY `id_squadra_idx` (`id_squadra`),
  CONSTRAINT `id_squadra` FOREIGN KEY (`id_squadra`) REFERENCES `squadra` (`id_squadra`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giocatore`
--

LOCK TABLES `giocatore` WRITE;
/*!40000 ALTER TABLE `giocatore` DISABLE KEYS */;
INSERT INTO `giocatore` VALUES ('gio','gio4@email.it','Giovanni','Falco','ecf28e55fdeac49edf0ccb7246955f2d2f13551519c15370a52387c57a0c6a9e','3334562167','2001-11-16','Italia','Caserta','Caserta','89976',0,2),('mario','mario@mario.it','Mario','Calabrese','59195c6c541c8307f1da2d1e768d6f2280c984df217ad5f4c64c3542b04111a4','3452167543','2000-03-03','Italia','Avellino','Avellino','80076',0,3),('pino','pino@pino.it','Pino','Inglese','a55c93846cec6f8780e3f00b112d6b897e8e74b02b52ddce0280b067a3a294cf','3665423187','2000-09-09','Italia','Napoli','Napoli','80000',0,NULL),('simone','simone@simone.it','Simone','Graziano','19a0098e641d4bee278bb5d470d06679ffc5fdc818c3a1c52bfb7f8cde3752d3','3324156789','1999-08-12','Italia','Milano','Milano','78654',0,NULL);
/*!40000 ALTER TABLE `giocatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logo_squadra`
--

DROP TABLE IF EXISTS `logo_squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logo_squadra` (
  `id_logo_squadra` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id_logo_squadra`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logo_squadra`
--

LOCK TABLES `logo_squadra` WRITE;
/*!40000 ALTER TABLE `logo_squadra` DISABLE KEYS */;
INSERT INTO `logo_squadra` VALUES (2,'logo_club1','img/logo_squadra/logo_club1.jpg'),(3,'logo_club2','img/logo_squadra/logo_club2.jpg');
/*!40000 ALTER TABLE `logo_squadra` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `partecipazione`
--

LOCK TABLES `partecipazione` WRITE;
/*!40000 ALTER TABLE `partecipazione` DISABLE KEYS */;
INSERT INTO `partecipazione` VALUES ('pino@pino.it','Evento 333'),('simone@simone.it','Evento 333'),('pino@pino.it','evento3'),('simone@simone.it','evento3');
/*!40000 ALTER TABLE `partecipazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partecipazione_squadra`
--

DROP TABLE IF EXISTS `partecipazione_squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partecipazione_squadra` (
  `id_squadra` int NOT NULL,
  `id_evento` varchar(30) NOT NULL,
  PRIMARY KEY (`id_squadra`,`id_evento`),
  KEY `evento_idx` (`id_evento`),
  CONSTRAINT `evento` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `squadra_partecipante` FOREIGN KEY (`id_squadra`) REFERENCES `squadra` (`id_squadra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partecipazione_squadra`
--

LOCK TABLES `partecipazione_squadra` WRITE;
/*!40000 ALTER TABLE `partecipazione_squadra` DISABLE KEYS */;
INSERT INTO `partecipazione_squadra` VALUES (2,'evento squadra'),(3,'evento squadra'),(2,'evento squadra 2');
/*!40000 ALTER TABLE `partecipazione_squadra` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES ('pino@pino.it','mario@mario.it','evento3',2,'scarso'),('pino@pino.it','simone@simone.it','evento3',2,'scarso');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `squadra`
--

DROP TABLE IF EXISTS `squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `squadra` (
  `id_squadra` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `nome_abbreviato` varchar(3) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `descrizione` varchar(300) NOT NULL,
  `logo` int DEFAULT NULL,
  `capitano` varchar(100) NOT NULL,
  PRIMARY KEY (`id_squadra`),
  UNIQUE KEY `nome` (`nome`),
  KEY `capitano_idx` (`capitano`),
  KEY `logo_squadra_idx` (`logo`),
  CONSTRAINT `capitano` FOREIGN KEY (`capitano`) REFERENCES `giocatore` (`e_mail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `logo_squadra` FOREIGN KEY (`logo`) REFERENCES `logo_squadra` (`id_logo_squadra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=148 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squadra`
--

LOCK TABLES `squadra` WRITE;
/*!40000 ALTER TABLE `squadra` DISABLE KEYS */;
INSERT INTO `squadra` VALUES (2,'tigers','tig','Salerno','Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.',2,'gio4@email.it'),(3,'Lyons','lyo','Napoli','Lorem ipsum lorem ipsum lorem ipsum lorem ipsum.',3,'mario@mario.it');
/*!40000 ALTER TABLE `squadra` ENABLE KEYS */;
UNLOCK TABLES;

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

-- Dump completed on 2022-07-10  9:54:27
CREATE DATABASE  IF NOT EXISTS `mybomber` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `mybomber`;
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
  `tipologia` varchar(45) NOT NULL,
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
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES ('11 fiati in campo','','EA Sport','2029-09-19',19,'vincenzo@gmail.com','comi@libero.it','attivo',0,1,'libero'),('asdfgh','asdfghbvcxs','Champion Sport','2022-07-31',15,'maddalena@gmail.com','cinzia@libero.it','richiesta',0,0,'squadra'),('asdfgsss','dfgfdxcvgfdfg','stadioooo','2022-07-24',16,'gestore@gestore.it','cinzia@libero.it','completato',0,1,'squadra'),('Bibite Cup','Una partita frizzante','Goal Master','2025-05-05',10,'antonio@gmail.com','cinzia@libero.it','richiesta',0,0,'libero'),('Bravi tutti','Per i più audaci!','Champion Sport','2022-01-27',17,'maddalena@gmail.com','benedetta@hotmail.it','attivo',0,2,'libero'),('C.E. la facciamo','Partita tranquilla per tutti','Domingo della suerte','2022-03-19',19,'domenico@gmail.com','pasquale@gmail.com','richiesta',0,0,'libero'),('Calcium','I migliori vincono sempre','Champion Sport','2023-06-17',18,'maddalena@gmail.com','benedetta@hotmail.it','attivo',0,1,'libero'),('Champions Ligue','Perchè noi può','EA Sport','2022-02-12',15,'vincenzo@gmail.com','flavio@libero.it','richiesta',0,0,'libero'),('Classico della suerte','Il leggendario evento italiano','Domingo della suerte','2022-02-10',18,'domenico@gmail.com','vincenzo@libero.it','attivo',4,2,'libero'),('Coppa del nonno','La rivincita della serie A','Champion Sport','2027-07-13',17,'maddalena@gmail.com','pasquale@gmail.com','richiesta',0,0,'libero'),('Evento del secolo','','Champion Sport','2023-07-13',19,'maddalena@gmail.com','claudio@hotmail.it','attivo',0,1,'libero'),('Mondiali','','Tanucc Game','2022-02-02',2,'gaetano@gmail.com','benedetta@hotmail.it','attivo',0,1,'libero'),('Partita 2.0','','Tanucc Game','2026-06-06',6,'gaetano@gmail.com','flavio@libero.it','richiesta',0,0,'libero'),('Partita del cuore','Memorial','Champion Sport','2022-01-13',20,'maddalena@gmail.com','claudio@hotmail.it','completato',9,10,'libero'),('Partitella Bella','Partita tra amici','Champion Sport','2022-09-18',22,'maddalena@gmail.com','claudio@hotmail.it','attivo',0,1,'libero'),('poiuy','kljhgfxd','stadioooo','2022-07-30',22,'gestore@gestore.it','cinzia@libero.it','completato',0,2,'squadra'),('qqqqqq','qqqqqqqq','stadioooo','2022-07-29',15,'gestore@gestore.it','cinzia@libero.it','attivo',0,1,'squadra'),('Stoppi la palla al volo','','Goal Master','2022-04-22',19,'antonio@gmail.com','vittorio@gmai.com','attivo',0,2,'libero');
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `gestore`
--

LOCK TABLES `gestore` WRITE;
/*!40000 ALTER TABLE `gestore` DISABLE KEYS */;
INSERT INTO `gestore` VALUES ('antonio@gmail.com','Antonio','Palladino','1f0ca711df81520887afe0dca099652a249e7eda60348be7327d432b02298652','082849456','Goal Master'),('domenico@gmail.com','Domenico','D\'Antuono','2a43da7cc03407a5c5a2458acee91c7c1e0ced9c9e83ed3df31b0fb73f82bcec','082849345','Domingo della suerte'),('gaetano@gmail.com','Gaetano','Mauro','13457ef444cbc288d72c37317e1d5f798706dbfb2b5703feecf11764f1d79009','082846123','Tanucc Game'),('gestore@gestore.it','gestoe','gesto','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','123456','stadioooo'),('gino@gino.it','gino','rossi','0043ee70d76e977c18c08943ce49f280e789061b1f1a50798af41acaeea8e78b','5433453443','playk'),('maddalena@gmail.com','Maddalena','Viglione','553d97e7800ec7772b2a9f2d46623af73eb55b267b8167ea6c9a1293eb17145e','082849567','Champion Sport'),('vincenzo@gmail.com','Vincenzo','Bonavita','5c2fade36fcd91477228895d5627510ce65848d46fd4906cd80b5a19d199dce0','082849234','EA Sport');
/*!40000 ALTER TABLE `gestore` ENABLE KEYS */;
UNLOCK TABLES;

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
  `id_squadra` int DEFAULT NULL,
  PRIMARY KEY (`e_mail`),
  KEY `id_squadra_idx` (`id_squadra`),
  CONSTRAINT `id_squadra` FOREIGN KEY (`id_squadra`) REFERENCES `squadra` (`id_squadra`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `giocatore`
--

LOCK TABLES `giocatore` WRITE;
/*!40000 ALTER TABLE `giocatore` DISABLE KEYS */;
INSERT INTO `giocatore` VALUES ('aaaaa','aaa@aaa.it','prova','prova','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','12345432','2022-03-08','Napoles','Napoles','Napole','12345',0,NULL),('alex9','aless@gmail.com','Alessandra','De Donato','5c85bb36f3869809fb738a3ba6f990aedbfeca3df2dc1a997fa49c50d0eed8e6','8901234567','2004-07-10','Italia','Roma','Formello','80021',3,NULL),('benny10','benedetta@hotmail.it','Benedetta','Vittore','bc97a896374e5cff2f703d4e06f4ffd721aecfee2a045d88e90854635313a69e','1234567890','2000-09-10','Italia','Salerno','Campagna','84022',5,NULL),('cinzia22','cinzia@libero.it','Cinzia','Soligno','01be30770ef15c5d6d8020d4b3d8859e9c1ec0a64eb4f5f9535b7d54f3474dfd','6789012345','1987-09-22','Italia','Napoli','Lettere','80050',4,13),('cs7','claudio@hotmail.it','Claudio','Soligno','a0a23131baba5403daeed1bfe1b4eea6e7588f1d0835ee7af8464f26d7d55b82','0123456789','1996-10-30','Italia','Salerno','Campagna','84022',5,NULL),('cosinusoide','comi@libero.it','Cosimina','Facente','ec9791bfc2b7336e2d98cedf493c4d98d9422f627484664b82eaf2d1d0e62e7e','2345678901','2000-10-19','Italia','Salerno','Eboli','84025',5,NULL),('cr1','cr1@gmail.com','Cr','uno','b80e1708501e7931e51dcc67aabff84675821c55a054e6978095e2497d926f69','12345678','1998-05-27','italoa','napoli','napoli','12345',0,NULL),('flaviuccio','flavio@libero.it','Flavio','Soligno','496ac691756603b8ef265e92b998edabbd228865785eb672f086a62bd44af041','3456789012','1977-04-22','Italia','Salerno','Eboli','84025',2,NULL),('iooo','io@io.it','io','io','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','12345432','2021-02-17','ital','npa','sss','12345',0,NULL),('io2','io@io2.it','qwer','ASD','03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4','123456','2022-01-05','it','it','it','12345',0,15),('pasqui','pasquale@gmail.com','Pasquale','Dezzo','e35bf5db2af5a880fb3e3887e1574f30b1139a3547ec82f8352e03d3d5bcd32d','9012345678','1988-12-09','Italia','Roma','Formello','00060',3,NULL),('piero10','pierf@libero.it','Pierfrancesco','Spada','c8725a193adb9e3544dd4d7464414bb90935ed87064f46add849136879ecba5c','5678901234','1995-03-16','Italia','Salerno','Fisciano','84084',5,NULL),('pino','pino@pino.it','pino','pino','a55c93846cec6f8780e3f00b112d6b897e8e74b02b52ddce0280b067a3a294cf','5433453443','2000-06-06','italia','napoli','napoli','80050',0,NULL),('vinz11','vincenzo@libero.it','Vincenzo','Saldi','66a8e7a7a318c7a880d566d74a171db7d6181d887109deac42918ecbf398ebb2','4567890123','1999-11-07','Italia','Salerno','Fisciano','84084',0,NULL),('vitty','vittorio@gmai.com','Vittorio','Amoroso','8b8d1cef8ace7e631ef85eb838166873b2b6c78ec3de2e3d591d3d72e4574314','7890123456','1989-02-02','Italia','Napoli','Lettere','80050',0,NULL);
/*!40000 ALTER TABLE `giocatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `logo_squadra`
--

DROP TABLE IF EXISTS `logo_squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logo_squadra` (
  `id_logo_squadra` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id_logo_squadra`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `logo_squadra`
--

LOCK TABLES `logo_squadra` WRITE;
/*!40000 ALTER TABLE `logo_squadra` DISABLE KEYS */;
INSERT INTO `logo_squadra` VALUES (2,'logo_club1','img/logo_squadra/logo_club1.jpg'),(3,'logo_club2','img/logo_squadra/logo_club2.jpg'),(4,'logo_club3','img/logo_squadra/logo_club3.png'),(5,'logo_club4','img/logo_squadra/logo_club4.jpg'),(6,'logo_club5','img/logo_squadra/logo_club5.jpg'),(7,'logo_club6','img/logo_squadra/logo_club6.jpg'),(8,'no_image','img/logo_squadra/no_image.png');
/*!40000 ALTER TABLE `logo_squadra` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `partecipazione`
--

LOCK TABLES `partecipazione` WRITE;
/*!40000 ALTER TABLE `partecipazione` DISABLE KEYS */;
INSERT INTO `partecipazione` VALUES ('comi@libero.it','11 fiati in campo'),('cinzia@libero.it','asdfgsss'),('benedetta@hotmail.it','Bravi tutti'),('pasquale@gmail.com','Bravi tutti'),('benedetta@hotmail.it','Calcium'),('cinzia@libero.it','Classico della suerte'),('vincenzo@libero.it','Classico della suerte'),('claudio@hotmail.it','Evento del secolo'),('benedetta@hotmail.it','Mondiali'),('aless@gmail.com','Partita del cuore'),('benedetta@hotmail.it','Partita del cuore'),('cinzia@libero.it','Partita del cuore'),('claudio@hotmail.it','Partita del cuore'),('comi@libero.it','Partita del cuore'),('flavio@libero.it','Partita del cuore'),('pasquale@gmail.com','Partita del cuore'),('pierf@libero.it','Partita del cuore'),('vincenzo@libero.it','Partita del cuore'),('vittorio@gmai.com','Partita del cuore'),('claudio@hotmail.it','Partitella Bella'),('aless@gmail.com','Stoppi la palla al volo'),('vittorio@gmai.com','Stoppi la palla al volo');
/*!40000 ALTER TABLE `partecipazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `partecipazione_squadra`
--

DROP TABLE IF EXISTS `partecipazione_squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `partecipazione_squadra` (
  `id_squadra` int NOT NULL,
  `id_evento` varchar(30) NOT NULL,
  PRIMARY KEY (`id_squadra`,`id_evento`),
  KEY `evento_idx` (`id_evento`),
  CONSTRAINT `evento` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`nome`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `squadra_partecipante` FOREIGN KEY (`id_squadra`) REFERENCES `squadra` (`id_squadra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `partecipazione_squadra`
--

LOCK TABLES `partecipazione_squadra` WRITE;
/*!40000 ALTER TABLE `partecipazione_squadra` DISABLE KEYS */;
INSERT INTO `partecipazione_squadra` VALUES (13,'poiuy'),(15,'poiuy'),(13,'qqqqqq');
/*!40000 ALTER TABLE `partecipazione_squadra` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES ('cinzia@libero.it','claudio@hotmail.it','Partita del cuore',5,'bravissimo!'),('cinzia@libero.it','comi@libero.it','Partita del cuore',5,'Ottima prestazione '),('cinzia@libero.it','flavio@libero.it','Partita del cuore',2,''),('claudio@hotmail.it','aless@gmail.com','Partita del cuore',3,''),('claudio@hotmail.it','benedetta@hotmail.it','Partita del cuore',5,'Una calciatrice nata!'),('claudio@hotmail.it','pasquale@gmail.com','Partita del cuore',3,''),('claudio@hotmail.it','pierf@libero.it','Partita del cuore',5,'Bravissimo!');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `squadra`
--

DROP TABLE IF EXISTS `squadra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `squadra` (
  `id_squadra` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `nome_abbreviato` varchar(3) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `descrizione` varchar(300) NOT NULL,
  `logo` int DEFAULT NULL,
  `capitano` varchar(100) NOT NULL,
  PRIMARY KEY (`id_squadra`),
  UNIQUE KEY `nome` (`nome`),
  KEY `capitano_idx` (`capitano`),
  KEY `logo_squadra_idx` (`logo`),
  CONSTRAINT `capitano` FOREIGN KEY (`capitano`) REFERENCES `giocatore` (`e_mail`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `logo_squadra` FOREIGN KEY (`logo`) REFERENCES `logo_squadra` (`id_logo_squadra`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `squadra`
--

LOCK TABLES `squadra` WRITE;
/*!40000 ALTER TABLE `squadra` DISABLE KEYS */;
INSERT INTO `squadra` VALUES (13,'tigers','tig','Salerno','kjdhsakl jfhdskjf hdskljhgklsjdhg klsjdhgklj sddakjhfdlk jahfkj ',4,'cinzia@libero.it'),(15,'The best','bes','Roma','asdfghjmhgfc dcsffdbgd lsakdjaslkda',7,'io@io2.it');
/*!40000 ALTER TABLE `squadra` ENABLE KEYS */;
UNLOCK TABLES;

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

--
-- Dumping data for table `struttura`
--

LOCK TABLES `struttura` WRITE;
/*!40000 ALTER TABLE `struttura` DISABLE KEYS */;
INSERT INTO `struttura` VALUES ('Champion Sport','Via Verdi 13','Italia','Campagna','84022','Salerno','082856749'),('Domingo della suerte','Via Italia 1','Italia','Lettere','80050','Napoli','082834549'),('EA Sport','Via Papa Leone 2','Italia','Lauro','83023','Napoli','082823449'),('Goal Master','Via Manzoni 6','Italia','Formello','00060','Roma','082845649'),('playk','via gino, 22','italia','napoli','80050','napoli','3213453443'),('stadioooo','via ss','italy','Roma','12345','Roma','1234567'),('Tanucc Game','Via Togliatti 88','Italia','Fisciano','84084','Salerno','082812349');
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

-- Dump completed on 2022-07-10  9:54:28
