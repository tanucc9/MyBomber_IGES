SET NAMES utf8 ;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;

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

CREATE TABLE `logo_squadra` (
  `id_logo_squadra` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) NOT NULL,
  `url` varchar(255) NOT NULL,
  PRIMARY KEY (`id_logo_squadra`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `squadra` (
  `id_squadra` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(30) NOT NULL,
  `nome_abbreviato` varchar(3) NOT NULL,
  `citta` varchar(30) NOT NULL,
  `descrizione` varchar(300) NOT NULL,
  `logo` int(11) DEFAULT NULL,
  `capitano` varchar(100) NOT NULL,
  PRIMARY KEY (`id_squadra`),
  UNIQUE KEY `nome` (`nome`),
  KEY `capitano_idx` (`capitano`),
  KEY `logo_squadra_idx` (`logo`),
  CONSTRAINT `squadra_ibfk_1` FOREIGN KEY (`logo`) REFERENCES `logo_squadra` (`id_logo_squadra`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `squadra_ibfk_2` FOREIGN KEY (`capitano`) REFERENCES `giocatore` (`e_mail`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
  `id_squadra` int(11) DEFAULT NULL,
  PRIMARY KEY (`e_mail`),
  KEY `id_squadra_idx` (`id_squadra`),
  CONSTRAINT `giocatore_ibfk_1` FOREIGN KEY (`id_squadra`) REFERENCES `squadra` (`id_squadra`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `evento` (
  `code` varchar(30) NOT NULL,
  `nome` varchar(30) NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  `struttura` varchar(30) NOT NULL,
  `data_evento` date NOT NULL,
  `ora` int(11) NOT NULL,
  `e_mail_gestore` varchar(30) NOT NULL,
  `e_mail_utente` varchar(30) NOT NULL,
  `stato` varchar(30) NOT NULL,
  `valutazione` float NOT NULL,
  `numero_partecipanti` int(11) NOT NULL,
  `tipologia` varchar(45) NOT NULL,
  PRIMARY KEY (`code`),
  UNIQUE KEY `nome_UNIQUE` (`nome`),
  KEY `e_mail_gestore` (`e_mail_gestore`),
  KEY `e_mail_utente` (`e_mail_utente`),
  KEY `struttura` (`struttura`),
  CONSTRAINT `evento_ibfk_1` FOREIGN KEY (`e_mail_gestore`) REFERENCES `gestore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `evento_ibfk_2` FOREIGN KEY (`e_mail_utente`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `evento_ibfk_3` FOREIGN KEY (`struttura`) REFERENCES `struttura` (`nome`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `partecipazione` (
  `e_mail` varchar(30) NOT NULL,
  `code_evento` varchar(30) NOT NULL,
  PRIMARY KEY (`e_mail`,`code_evento`),
  KEY `partecipazione_ibfk_2_idx` (`code_evento`),
  CONSTRAINT `partecipazione_ibfk_1` FOREIGN KEY (`e_mail`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `partecipazione_ibfk_2` FOREIGN KEY (`code_evento`) REFERENCES `evento` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `partecipazione_squadra` (
  `id_squadra` int(11) NOT NULL,
  `id_evento` varchar(30) NOT NULL,
  PRIMARY KEY (`id_squadra`,`id_evento`),
  KEY `squadra_evento_idx` (`id_evento`),
  CONSTRAINT `partecipazione_squadra_ibfk_1` FOREIGN KEY (`id_squadra`) REFERENCES `squadra` (`id_squadra`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `partecipazione_squadra_ibfk_2` FOREIGN KEY (`id_evento`) REFERENCES `evento` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `recensione` (
  `e_mail_recensore` varchar(30) NOT NULL,
  `e_mail_recensito` varchar(30) NOT NULL,
  `code_evento` varchar(30) NOT NULL,
  `recensione` float NOT NULL,
  `descrizione` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`e_mail_recensore`,`e_mail_recensito`,`code_evento`),
  KEY `e_mail_recensito` (`e_mail_recensito`),
  KEY `recensione_ibfk_3_idx` (`code_evento`),
  CONSTRAINT `recensione_ibfk_1` FOREIGN KEY (`e_mail_recensore`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `recensione_ibfk_2` FOREIGN KEY (`e_mail_recensito`) REFERENCES `giocatore` (`e_mail`) ON UPDATE CASCADE,
  CONSTRAINT `recensione_ibfk_3` FOREIGN KEY (`code_evento`) REFERENCES `evento` (`code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
