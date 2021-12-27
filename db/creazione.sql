CREATE SCHEMA IF NOT EXISTS mybomber;

USE mybomber ;
  
CREATE TABLE IF NOT EXISTS mybomber.giocatore (
  username VARCHAR(30) NOT NULL,
  e_mail VARCHAR(30) NOT NULL,
  nome VARCHAR(30) NOT NULL,
  cognome VARCHAR(30) NOT NULL,
  password_giocatore VARCHAR(30) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  data_nascita DATE NOT NULL,
  nazione_residenza VARCHAR(30) NOT NULL,
  provincia_residenza VARCHAR(30) NOT NULL,
  citta_residenza VARCHAR(30) NOT NULL,
  cap_residenza VARCHAR(10) NOT NULL,
  valutazione FLOAT NOT NULL,
  
  PRIMARY KEY (e_mail));
  
CREATE TABLE IF NOT EXISTS mybomber.struttura (
  nome VARCHAR(30) NOT NULL,
  indirizzo VARCHAR(30) NOT NULL,
  nazione VARCHAR(30) NOT NULL,
  citta VARCHAR(30) NOT NULL,
  cap VARCHAR(10) NOT NULL,
  provincia VARCHAR(30) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  PRIMARY KEY (nome));
  
CREATE TABLE IF NOT EXISTS mybomber.gestore (
  e_mail VARCHAR(30) NOT NULL,
  nome VARCHAR(30) NOT NULL,
  cognome VARCHAR(30) NOT NULL,
  password_gestore VARCHAR(30) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  struttura VARCHAR(30) NOT NULL,
  PRIMARY KEY (e_mail),
  
FOREIGN KEY(struttura)
  REFERENCES mydb.Struttura (nome)
  ON DELETE NO ACTION
  ON UPDATE CASCADE);

CREATE TABLE IF NOT EXISTS mybomber.evento (
  nome VARCHAR(30) NOT NULL,
  descrizione VARCHAR(100) NOT NULL,
  struttura VARCHAR(30) NOT NULL,
  data_evento Date NOT NULL,
  ora Time NOT NULL,
  e_mail_gestore VARCHAR(30) NOT NULL,
  e_mail_utente VARCHAR(30) NOT NULL,
  PRIMARY KEY (nome),
  
    FOREIGN KEY (e_mail_gestore)
    REFERENCES mydb.Gestore (e_mail)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    
    FOREIGN KEY (e_mail_utente)
    REFERENCES mydb.giocatore (e_mail)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
 
CREATE TABLE IF NOT EXISTS mybomber.partecipazione (
  e_mail VARCHAR(30) NOT NULL,
  nome_evento VARCHAR(30) NOT NULL,
  PRIMARY KEY (e_mail,nome_evento),
  
  FOREIGN KEY (e_mail)
  REFERENCES mydb.giocatore (e_mail)
  ON DELETE NO ACTION
  ON UPDATE CASCADE,
    
  FOREIGN KEY (nome_evento)
  REFERENCES mydb.evento (nome)
  ON DELETE NO ACTION
  ON UPDATE CASCADE);
  
  CREATE TABLE IF NOT EXISTS mybomber.recensione (
  e_mail_recensore VARCHAR(30) NOT NULL,
  e_mail_recensito VARCHAR(30) NOT NULL,
  nome_evento VARCHAR(30) NOT NULL,
  recensione FLOAT NOT NULL,
  PRIMARY KEY (e_mail_recensore,e_mail_recensito,nome_evento),
  
    FOREIGN KEY (e_mail_recensore)
    REFERENCES mydb.giocatore (e_mail)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    
    FOREIGN KEY (e_mail_recensito)
    REFERENCES mydb.giocatore (e_mail)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
    
    FOREIGN KEY (nome_evento)
    REFERENCES mydb.evento (nome)
    ON DELETE NO ACTION
    ON UPDATE CASCADE);
