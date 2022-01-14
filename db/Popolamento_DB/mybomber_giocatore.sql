-- MySQL dump 10.13  Distrib 8.0.27, for Win64 (x86_64)
--
-- Host: localhost    Database: mybomber
-- ------------------------------------------------------
-- Server version	8.0.27

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
-- Dumping data for table `giocatore`
--

LOCK TABLES `giocatore` WRITE;
/*!40000 ALTER TABLE `giocatore` DISABLE KEYS */;
INSERT INTO `giocatore` VALUES ('alex9','aless@gmail.com','Alessandra','De Donato','ale','8901234567','2004-07-10','Italia','Roma','Formello','80021',0),('benny10','benedetta@hotmail.it','Benedetta','Vittore','Benedetta','1234567890','2000-09-10','Italia','Salerno','Campagna','84022',0),('cinzia22','cinzia@libero.it','Cinzia','Soligno','Cinzia','6789012345','1987-09-22','Italia','Napoli','Lettere','80050',4),('cs7','claudio@hotmail.it','Claudio','Soligno','Claudio','0123456789','1996-10-30','Italia','Salerno','Campagna','84022',0),('cosinusoide','comi@libero.it','Cosimina','Facente','Comi','2345678901','2000-10-19','Italia','Salerno','Eboli','84025',0),('flaviuccio','flavio@libero.it','Flavio','Soligno','Flavio','3456789012','1977-04-22','Italia','Salerno','Eboli','84025',0),('pasqui','pasquale@gmail.com','Pasquale','Dezzo','papapa','9012345678','1988-12-09','Italia','Roma','Formello','00060',0),('piero10','pierf@libero.it','Pierfrancesco','Spada','Pietro','5678901234','1995-03-16','Italia','Salerno','Fisciano','84084',0),('vinz11','vincenzo@libero.it','Vincenzo','Saldi','Vinz','4567890123','1999-11-07','Italia','Salerno','Fisciano','84084',0),('vitty','vittorio@gmai.com','Vittorio','Amoroso','Vitto','7890123456','1989-02-02','Italia','Napoli','Lettere','80050',0);
/*!40000 ALTER TABLE `giocatore` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-14 20:07:19
