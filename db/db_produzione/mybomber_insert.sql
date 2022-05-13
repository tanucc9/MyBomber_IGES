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
-- Dumping data for table `evento`
--

LOCK TABLES `evento` WRITE;
/*!40000 ALTER TABLE `evento` DISABLE KEYS */;
INSERT INTO `evento` VALUES ('11 fiati in campo','','EA Sport','2029-09-19',19,'vincenzo@gmail.com','comi@libero.it','attivo',0,1),('Bibite Cup','Una partita frizzante','Goal Master','2025-05-05',10,'antonio@gmail.com','cinzia@libero.it','richiesta',0,0),('Bravi tutti','Per i più audaci!','Champion Sport','2022-01-27',17,'maddalena@gmail.com','benedetta@hotmail.it','attivo',0,2),('C.E. la facciamo','Partita tranquilla per tutti','Domingo della suerte','2022-03-19',19,'domenico@gmail.com','pasquale@gmail.com','richiesta',0,0),('Calcium','I migliori vincono sempre','Champion Sport','2023-06-17',18,'maddalena@gmail.com','benedetta@hotmail.it','attivo',0,1),('Champions Ligue','Perchè noi può','EA Sport','2022-02-12',15,'vincenzo@gmail.com','flavio@libero.it','richiesta',0,0),('Classico della suerte','Il leggendario evento italiano','Domingo della suerte','2022-02-10',18,'domenico@gmail.com','vincenzo@libero.it','attivo',4,2),('Coppa del nonno','La rivincita della serie A','Champion Sport','2027-07-13',17,'maddalena@gmail.com','pasquale@gmail.com','richiesta',0,0),('Evento del secolo','','Champion Sport','2023-07-13',19,'maddalena@gmail.com','claudio@hotmail.it','attivo',0,1),('Mondiali','','Tanucc Game','2022-02-02',2,'gaetano@gmail.com','benedetta@hotmail.it','attivo',0,1),('Partita 2.0','','Tanucc Game','2026-06-06',6,'gaetano@gmail.com','flavio@libero.it','richiesta',0,0),('Partita del cuore','Memorial','Champion Sport','2022-01-13',20,'maddalena@gmail.com','claudio@hotmail.it','completato',9,10),('Partitella Bella','Partita tra amici','Champion Sport','2022-09-18',22,'maddalena@gmail.com','claudio@hotmail.it','attivo',0,1),('Stoppi la palla al volo','','Goal Master','2022-04-22',19,'antonio@gmail.com','vittorio@gmai.com','attivo',0,2);
/*!40000 ALTER TABLE `evento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `gestore`
--

LOCK TABLES `gestore` WRITE;
/*!40000 ALTER TABLE `gestore` DISABLE KEYS */;
INSERT INTO `gestore` VALUES ('antonio@gmail.com','Antonio','Palladino','1f0ca711df81520887afe0dca099652a249e7eda60348be7327d432b02298652','082849456','Goal Master'),('domenico@gmail.com','Domenico','D\'Antuono','2a43da7cc03407a5c5a2458acee91c7c1e0ced9c9e83ed3df31b0fb73f82bcec','082849345','Domingo della suerte'),('gaetano@gmail.com','Gaetano','Mauro','13457ef444cbc288d72c37317e1d5f798706dbfb2b5703feecf11764f1d79009','082846123','Tanucc Game'),('maddalena@gmail.com','Maddalena','Viglione','553d97e7800ec7772b2a9f2d46623af73eb55b267b8167ea6c9a1293eb17145e','082849567','Champion Sport'),('vincenzo@gmail.com','Vincenzo','Bonavita','5c2fade36fcd91477228895d5627510ce65848d46fd4906cd80b5a19d199dce0','082849234','EA Sport');
/*!40000 ALTER TABLE `gestore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `giocatore`
--

LOCK TABLES `giocatore` WRITE;
/*!40000 ALTER TABLE `giocatore` DISABLE KEYS */;
INSERT INTO `giocatore` VALUES ('alex9','aless@gmail.com','Alessandra','De Donato','5c85bb36f3869809fb738a3ba6f990aedbfeca3df2dc1a997fa49c50d0eed8e6','8901234567','2004-07-10','Italia','Roma','Formello','80021',3),('benny10','benedetta@hotmail.it','Benedetta','Vittore','bc97a896374e5cff2f703d4e06f4ffd721aecfee2a045d88e90854635313a69e','1234567890','2000-09-10','Italia','Salerno','Campagna','84022',5),('cinzia22','cinzia@libero.it','Cinzia','Soligno','01be30770ef15c5d6d8020d4b3d8859e9c1ec0a64eb4f5f9535b7d54f3474dfd','6789012345','1987-09-22','Italia','Napoli','Lettere','80050',4),('cs7','claudio@hotmail.it','Claudio','Soligno','a0a23131baba5403daeed1bfe1b4eea6e7588f1d0835ee7af8464f26d7d55b82','0123456789','1996-10-30','Italia','Salerno','Campagna','84022',5),('cosinusoide','comi@libero.it','Cosimina','Facente','ec9791bfc2b7336e2d98cedf493c4d98d9422f627484664b82eaf2d1d0e62e7e','2345678901','2000-10-19','Italia','Salerno','Eboli','84025',5),('flaviuccio','flavio@libero.it','Flavio','Soligno','496ac691756603b8ef265e92b998edabbd228865785eb672f086a62bd44af041','3456789012','1977-04-22','Italia','Salerno','Eboli','84025',2),('pasqui','pasquale@gmail.com','Pasquale','Dezzo','e35bf5db2af5a880fb3e3887e1574f30b1139a3547ec82f8352e03d3d5bcd32d','9012345678','1988-12-09','Italia','Roma','Formello','00060',3),('piero10','pierf@libero.it','Pierfrancesco','Spada','c8725a193adb9e3544dd4d7464414bb90935ed87064f46add849136879ecba5c','5678901234','1995-03-16','Italia','Salerno','Fisciano','84084',5),('vinz11','vincenzo@libero.it','Vincenzo','Saldi','66a8e7a7a318c7a880d566d74a171db7d6181d887109deac42918ecbf398ebb2','4567890123','1999-11-07','Italia','Salerno','Fisciano','84084',0),('vitty','vittorio@gmai.com','Vittorio','Amoroso','8b8d1cef8ace7e631ef85eb838166873b2b6c78ec3de2e3d591d3d72e4574314','7890123456','1989-02-02','Italia','Napoli','Lettere','80050',0);
/*!40000 ALTER TABLE `giocatore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `partecipazione`
--

LOCK TABLES `partecipazione` WRITE;
/*!40000 ALTER TABLE `partecipazione` DISABLE KEYS */;
INSERT INTO `partecipazione` VALUES ('comi@libero.it','11 fiati in campo'),('benedetta@hotmail.it','Bravi tutti'),('pasquale@gmail.com','Bravi tutti'),('benedetta@hotmail.it','Calcium'),('cinzia@libero.it','Classico della suerte'),('vincenzo@libero.it','Classico della suerte'),('claudio@hotmail.it','Evento del secolo'),('benedetta@hotmail.it','Mondiali'),('aless@gmail.com','Partita del cuore'),('benedetta@hotmail.it','Partita del cuore'),('cinzia@libero.it','Partita del cuore'),('claudio@hotmail.it','Partita del cuore'),('comi@libero.it','Partita del cuore'),('flavio@libero.it','Partita del cuore'),('pasquale@gmail.com','Partita del cuore'),('pierf@libero.it','Partita del cuore'),('vincenzo@libero.it','Partita del cuore'),('vittorio@gmai.com','Partita del cuore'),('claudio@hotmail.it','Partitella Bella'),('aless@gmail.com','Stoppi la palla al volo'),('vittorio@gmai.com','Stoppi la palla al volo');
/*!40000 ALTER TABLE `partecipazione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `recensione`
--

LOCK TABLES `recensione` WRITE;
/*!40000 ALTER TABLE `recensione` DISABLE KEYS */;
INSERT INTO `recensione` VALUES ('cinzia@libero.it','claudio@hotmail.it','Partita del cuore',5,'bravissimo!'),('cinzia@libero.it','comi@libero.it','Partita del cuore',5,'Ottima prestazione '),('cinzia@libero.it','flavio@libero.it','Partita del cuore',2,''),('claudio@hotmail.it','aless@gmail.com','Partita del cuore',3,''),('claudio@hotmail.it','benedetta@hotmail.it','Partita del cuore',5,'Una calciatrice nata!'),('claudio@hotmail.it','pasquale@gmail.com','Partita del cuore',3,''),('claudio@hotmail.it','pierf@libero.it','Partita del cuore',5,'Bravissimo!');
/*!40000 ALTER TABLE `recensione` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `struttura`
--

LOCK TABLES `struttura` WRITE;
/*!40000 ALTER TABLE `struttura` DISABLE KEYS */;
INSERT INTO `struttura` VALUES ('Champion Sport','Via Verdi 13','Italia','Campagna','84022','Salerno','082856749'),('Domingo della suerte','Via Italia 1','Italia','Lettere','80050','Napoli','082834549'),('EA Sport','Via Papa Leone 2','Italia','Lauro','83023','Napoli','082823449'),('Goal Master','Via Manzoni 6','Italia','Formello','00060','Roma','082845649'),('Tanucc Game','Via Togliatti 88','Italia','Fisciano','84084','Salerno','082812349');
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

-- Dump completed on 2022-05-13 15:10:57
