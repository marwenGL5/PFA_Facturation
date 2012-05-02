-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.62-0ubuntu0.10.04.1


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema FacturationPFA
--

CREATE DATABASE IF NOT EXISTS FacturationPFA;
USE FacturationPFA;

--
-- Temporary table structure for view `FacturationPFA`.`v_user_role`
--
DROP TABLE IF EXISTS `FacturationPFA`.`v_user_role`;
DROP VIEW IF EXISTS `FacturationPFA`.`v_user_role`;
CREATE TABLE `FacturationPFA`.`v_user_role` (
  `username` varchar(64),
  `passwd` varchar(64),
  `group_name` varchar(20)
);

--
-- Definition of table `FacturationPFA`.`Client`
--

DROP TABLE IF EXISTS `FacturationPFA`.`Client`;
CREATE TABLE  `FacturationPFA`.`Client` (
  `idClient` int(11) NOT NULL AUTO_INCREMENT,
  `nomClient` varchar(50) NOT NULL,
  `prenomClient` varchar(50) NOT NULL,
  `ageClient` int(11) NOT NULL,
  `adresseClient` varchar(50) NOT NULL,
  `villeClient` varchar(50) NOT NULL,
  `codePClient` int(11) NOT NULL,
  `emailClient` varchar(60) NOT NULL,
  PRIMARY KEY (`idClient`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `FacturationPFA`.`Client`
--

/*!40000 ALTER TABLE `Client` DISABLE KEYS */;
LOCK TABLES `Client` WRITE;
INSERT INTO `FacturationPFA`.`Client` VALUES  (1,'marwen','ben yaflah',23,'11,rue Elfarabi','mornag',2090,'marwen.by@gmail.com'),
 (2,'nader','hajlaoui',23,'cité militaire','bardo',2000,'hajlaoui.nader@gmail.com'),
 (4,'mohamed','dhrif',23,'rue Elfol','la sokra',2090,'dhrif.hbib@gmail.com');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Client` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`Facture`
--

DROP TABLE IF EXISTS `FacturationPFA`.`Facture`;
CREATE TABLE  `FacturationPFA`.`Facture` (
  `numFacture` int(11) NOT NULL AUTO_INCREMENT,
  `dateFacture` date NOT NULL,
  `quantite` int(11) NOT NULL,
  `refArticle` varchar(60) NOT NULL,
  `prixTTC` double NOT NULL,
  `pourcentageReduction` double NOT NULL,
  `montantTTC` double NOT NULL,
  `prixHorsTaxe` double NOT NULL,
  `pourcentageTVA` double NOT NULL,
  `montantTVA` double NOT NULL,
  `netAPayer` double NOT NULL,
  `ModePaiement` varchar(30) NOT NULL,
  `Total` double NOT NULL,
  `idClient` int(11) NOT NULL,
  `idFournisseur` int(11) NOT NULL,
  PRIMARY KEY (`numFacture`),
  KEY `fk_constraint_Client` (`idClient`),
  CONSTRAINT `fk_constraint_Client` FOREIGN KEY (`idClient`) REFERENCES `Client` (`idClient`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `FacturationPFA`.`Facture`
--

/*!40000 ALTER TABLE `Facture` DISABLE KEYS */;
LOCK TABLES `Facture` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Facture` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`Fournisseur`
--

DROP TABLE IF EXISTS `FacturationPFA`.`Fournisseur`;
CREATE TABLE  `FacturationPFA`.`Fournisseur` (
  `idFournisseur` int(11) NOT NULL AUTO_INCREMENT,
  `nomFournisseur` varchar(64) NOT NULL,
  `adresseFournisseur` varchar(64) NOT NULL,
  `villeFournisseur` varchar(64) NOT NULL,
  `codePFournisseur` int(11) NOT NULL,
  `descriptionFournisseur` varchar(200) DEFAULT NULL,
  `telFournisseur` bigint(20) NOT NULL,
  `nomEnseigne` varchar(50) DEFAULT NULL,
  `capitale` int(11) DEFAULT NULL,
  `numSiret` bigint(20) NOT NULL,
  PRIMARY KEY (`idFournisseur`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`Fournisseur`
--

/*!40000 ALTER TABLE `Fournisseur` DISABLE KEYS */;
LOCK TABLES `Fournisseur` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Fournisseur` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`Groups`
--

DROP TABLE IF EXISTS `FacturationPFA`.`Groups`;
CREATE TABLE  `FacturationPFA`.`Groups` (
  `group_id` int(11) NOT NULL,
  `group_name` varchar(20) NOT NULL,
  `group_desc` varchar(100) NOT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`Groups`
--

/*!40000 ALTER TABLE `Groups` DISABLE KEYS */;
LOCK TABLES `Groups` WRITE;
INSERT INTO `FacturationPFA`.`Groups` VALUES  (1,'USER',''),
 (2,'ADMIN','Administration users');
UNLOCK TABLES;
/*!40000 ALTER TABLE `Groups` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`PasswdOublie`
--

DROP TABLE IF EXISTS `FacturationPFA`.`PasswdOublie`;
CREATE TABLE  `FacturationPFA`.`PasswdOublie` (
  `idClient` int(11) NOT NULL,
  `passwdGenere` varchar(20) NOT NULL,
  `dateGenere` date NOT NULL,
  `timeGenere` time NOT NULL,
  PRIMARY KEY (`idClient`),
  CONSTRAINT `Client_constraint` FOREIGN KEY (`idClient`) REFERENCES `Client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`PasswdOublie`
--

/*!40000 ALTER TABLE `PasswdOublie` DISABLE KEYS */;
LOCK TABLES `PasswdOublie` WRITE;
INSERT INTO `FacturationPFA`.`PasswdOublie` VALUES  (1,'EVVtHc','2012-04-30','20:18:59');
UNLOCK TABLES;
/*!40000 ALTER TABLE `PasswdOublie` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`SEQUENCE`
--

DROP TABLE IF EXISTS `FacturationPFA`.`SEQUENCE`;
CREATE TABLE  `FacturationPFA`.`SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`SEQUENCE`
--

/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
LOCK TABLES `SEQUENCE` WRITE;
INSERT INTO `FacturationPFA`.`SEQUENCE` VALUES  ('SEQ_GEN','0');
UNLOCK TABLES;
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`Users`
--

DROP TABLE IF EXISTS `FacturationPFA`.`Users`;
CREATE TABLE  `FacturationPFA`.`Users` (
  `username` varchar(64) NOT NULL,
  `passwd` varchar(64) NOT NULL,
  `idClient` int(11) NOT NULL,
  PRIMARY KEY (`idClient`) USING BTREE,
  CONSTRAINT `new_fk_constraint` FOREIGN KEY (`idClient`) REFERENCES `Client` (`idClient`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`Users`
--

/*!40000 ALTER TABLE `Users` DISABLE KEYS */;
LOCK TABLES `Users` WRITE;
INSERT INTO `FacturationPFA`.`Users` VALUES  ('maroo','sqd',1),
 ('nader','nader',2),
 ('haboub','dhrif',4);
UNLOCK TABLES;
/*!40000 ALTER TABLE `Users` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`personne`
--

DROP TABLE IF EXISTS `FacturationPFA`.`personne`;
CREATE TABLE  `FacturationPFA`.`personne` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `DATANAISSANCE` date DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `VERSION` int(11) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`personne`
--

/*!40000 ALTER TABLE `personne` DISABLE KEYS */;
LOCK TABLES `personne` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `personne` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`test`
--

DROP TABLE IF EXISTS `FacturationPFA`.`test`;
CREATE TABLE  `FacturationPFA`.`test` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`test`
--

/*!40000 ALTER TABLE `test` DISABLE KEYS */;
LOCK TABLES `test` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `test` ENABLE KEYS */;


--
-- Definition of table `FacturationPFA`.`user_groups`
--

DROP TABLE IF EXISTS `FacturationPFA`.`user_groups`;
CREATE TABLE  `FacturationPFA`.`user_groups` (
  `idClient` int(10) NOT NULL,
  `group_id` int(10) NOT NULL,
  PRIMARY KEY (`idClient`,`group_id`),
  KEY `fk_users_has_groups_groups1` (`group_id`),
  KEY `fk_users_has_groups_users` (`idClient`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FacturationPFA`.`user_groups`
--

/*!40000 ALTER TABLE `user_groups` DISABLE KEYS */;
LOCK TABLES `user_groups` WRITE;
INSERT INTO `FacturationPFA`.`user_groups` VALUES  (1,1),
 (2,1),
 (2,2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `user_groups` ENABLE KEYS */;


--
-- Definition of view `FacturationPFA`.`v_user_role`
--

DROP TABLE IF EXISTS `FacturationPFA`.`v_user_role`;
DROP VIEW IF EXISTS `FacturationPFA`.`v_user_role`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `FacturationPFA`.`v_user_role` AS select `u`.`username` AS `username`,`u`.`passwd` AS `passwd`,`g`.`group_name` AS `group_name` from ((`FacturationPFA`.`user_groups` `ug` join `FacturationPFA`.`Users` `u` on((`u`.`idClient` = `ug`.`idClient`))) join `FacturationPFA`.`Groups` `g` on((`g`.`group_id` = `ug`.`group_id`)));



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
