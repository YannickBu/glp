-- MySQL dump 10.13  Distrib 5.5.46, for debian-linux-gnu (x86_64)
--
-- Host: 172.28.1.165    Database: l1nk_prod
-- ------------------------------------------------------
-- Server version	5.5.46-0ubuntu0.14.04.2

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
-- Table structure for table `ARTICLE`
--

DROP TABLE IF EXISTS `ARTICLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ARTICLE` (
  `IDARTICLE` int(11) NOT NULL,
  `CONTENU` varchar(255) DEFAULT NULL,
  `DATEPUBLICATION` datetime DEFAULT NULL,
  `UTILISATEUR_IDUTILISATEUR` int(11) DEFAULT NULL,
  `GROUPE_IDGROUPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDARTICLE`),
  KEY `FK_ARTICLE_UTILISATEUR_IDUTILISATEUR` (`UTILISATEUR_IDUTILISATEUR`),
  KEY `FK_ARTICLE_GROUPE_IDGROUPE` (`GROUPE_IDGROUPE`),
  CONSTRAINT `FK_ARTICLE_GROUPE_IDGROUPE` FOREIGN KEY (`GROUPE_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`),
  CONSTRAINT `FK_ARTICLE_UTILISATEUR_IDUTILISATEUR` FOREIGN KEY (`UTILISATEUR_IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ARTICLE`
--

LOCK TABLES `ARTICLE` WRITE;
/*!40000 ALTER TABLE `ARTICLE` DISABLE KEYS */;
INSERT INTO `ARTICLE` VALUES (154,'J\'ai eu mon diplome ! :)','2016-01-27 17:09:22',9,0),(201,'Moi j\'ai une attitude sport ! ','2016-01-27 17:15:02',6,0);
/*!40000 ALTER TABLE `ARTICLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `COMPETENCE`
--

DROP TABLE IF EXISTS `COMPETENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMPETENCE` (
  `IDCOMPETENCE` int(11) NOT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `NOTE` int(11) DEFAULT NULL,
  `PROFIL_IDPROFIL` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDCOMPETENCE`),
  KEY `FK_COMPETENCE_PROFIL_IDPROFIL` (`PROFIL_IDPROFIL`),
  CONSTRAINT `FK_COMPETENCE_PROFIL_IDPROFIL` FOREIGN KEY (`PROFIL_IDPROFIL`) REFERENCES `PROFIL` (`IDPROFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `COMPETENCE`
--

LOCK TABLES `COMPETENCE` WRITE;
/*!40000 ALTER TABLE `COMPETENCE` DISABLE KEYS */;
INSERT INTO `COMPETENCE` VALUES (101,'JAVA',5,7),(151,'C',1,7);
/*!40000 ALTER TABLE `COMPETENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DIPLOME`
--

DROP TABLE IF EXISTS `DIPLOME`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DIPLOME` (
  `IDDIPLOME` int(11) NOT NULL,
  `ANNEFIN` int(11) DEFAULT NULL,
  `ANNEEDEBUT` int(11) DEFAULT NULL,
  `LIBELLE` varchar(255) DEFAULT NULL,
  `PROFIL_IDPROFIL` int(11) DEFAULT NULL,
  `LIEU` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDDIPLOME`),
  KEY `FK_DIPLOME_PROFIL_IDPROFIL` (`PROFIL_IDPROFIL`),
  CONSTRAINT `FK_DIPLOME_PROFIL_IDPROFIL` FOREIGN KEY (`PROFIL_IDPROFIL`) REFERENCES `PROFIL` (`IDPROFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIPLOME`
--

LOCK TABLES `DIPLOME` WRITE;
/*!40000 ALTER TABLE `DIPLOME` DISABLE KEYS */;
INSERT INTO `DIPLOME` VALUES (152,2016,2015,'Master MIAGE',7,'');
/*!40000 ALTER TABLE `DIPLOME` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EXPERIENCE`
--

DROP TABLE IF EXISTS `EXPERIENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EXPERIENCE` (
  `IDEXPERIENCE` int(11) NOT NULL,
  `ANNEFIN` int(11) DEFAULT NULL,
  `ANNEEDEBUT` int(11) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `ENTREPRISE` varchar(255) DEFAULT NULL,
  `LIEU` varchar(255) DEFAULT NULL,
  `POSTE` varchar(255) DEFAULT NULL,
  `PROFIL_IDPROFIL` int(11) DEFAULT NULL,
  `PAYS` varchar(255) DEFAULT NULL,
  `REGION` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDEXPERIENCE`),
  KEY `FK_EXPERIENCE_PROFIL_IDPROFIL` (`PROFIL_IDPROFIL`),
  CONSTRAINT `FK_EXPERIENCE_PROFIL_IDPROFIL` FOREIGN KEY (`PROFIL_IDPROFIL`) REFERENCES `PROFIL` (`IDPROFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXPERIENCE`
--

LOCK TABLES `EXPERIENCE` WRITE;
/*!40000 ALTER TABLE `EXPERIENCE` DISABLE KEYS */;
INSERT INTO `EXPERIENCE` VALUES (153,2016,2015,'Stage de M2','CGI','Lille','Stagiaire',7,NULL,NULL);
/*!40000 ALTER TABLE `EXPERIENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUPE`
--

DROP TABLE IF EXISTS `GROUPE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUPE` (
  `IDGROUPE` int(11) NOT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NOMGROUPE` varchar(255) DEFAULT NULL,
  `UTILISATEURRESPONSABLE_IDUTILISATEUR` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDGROUPE`),
  UNIQUE KEY `NOMGROUPE` (`NOMGROUPE`),
  KEY `FK_GROUPE_UTILISATEURRESPONSABLE_IDUTILISATEUR` (`UTILISATEURRESPONSABLE_IDUTILISATEUR`),
  CONSTRAINT `FK_GROUPE_UTILISATEURRESPONSABLE_IDUTILISATEUR` FOREIGN KEY (`UTILISATEURRESPONSABLE_IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUPE`
--

LOCK TABLES `GROUPE` WRITE;
/*!40000 ALTER TABLE `GROUPE` DISABLE KEYS */;
INSERT INTO `GROUPE` VALUES (0,'Groupe des Sciences, Technologies, Santé','Sciences, Technologies, Santé',NULL),(1,'Groupe des Droit, Economie, Gestion','Droit, Economie, Gestion',NULL),(2,'Groupe des Sciences Humaines et Sociales','Sciences Humaines et Sociales',NULL);
/*!40000 ALTER TABLE `GROUPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUPE_ARTICLE`
--

DROP TABLE IF EXISTS `GROUPE_ARTICLE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUPE_ARTICLE` (
  `groupes_IDGROUPE` int(11) NOT NULL,
  `articles_IDARTICLE` int(11) NOT NULL,
  PRIMARY KEY (`groupes_IDGROUPE`,`articles_IDARTICLE`),
  KEY `FK_GROUPE_ARTICLE_articles_IDARTICLE` (`articles_IDARTICLE`),
  CONSTRAINT `FK_GROUPE_ARTICLE_groupes_IDGROUPE` FOREIGN KEY (`groupes_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`),
  CONSTRAINT `FK_GROUPE_ARTICLE_articles_IDARTICLE` FOREIGN KEY (`articles_IDARTICLE`) REFERENCES `ARTICLE` (`IDARTICLE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUPE_ARTICLE`
--

LOCK TABLES `GROUPE_ARTICLE` WRITE;
/*!40000 ALTER TABLE `GROUPE_ARTICLE` DISABLE KEYS */;
/*!40000 ALTER TABLE `GROUPE_ARTICLE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUPE_UTILISATEUR`
--

DROP TABLE IF EXISTS `GROUPE_UTILISATEUR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUPE_UTILISATEUR` (
  `utilisateurs_IDUTILISATEUR` int(11) NOT NULL,
  `groupes_IDGROUPE` int(11) NOT NULL,
  PRIMARY KEY (`utilisateurs_IDUTILISATEUR`,`groupes_IDGROUPE`),
  KEY `FK_GROUPE_UTILISATEUR_groupes_IDGROUPE` (`groupes_IDGROUPE`),
  CONSTRAINT `FK_GROUPE_UTILISATEUR_utilisateurs_IDUTILISATEUR` FOREIGN KEY (`utilisateurs_IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`),
  CONSTRAINT `FK_GROUPE_UTILISATEUR_groupes_IDGROUPE` FOREIGN KEY (`groupes_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUPE_UTILISATEUR`
--

LOCK TABLES `GROUPE_UTILISATEUR` WRITE;
/*!40000 ALTER TABLE `GROUPE_UTILISATEUR` DISABLE KEYS */;
/*!40000 ALTER TABLE `GROUPE_UTILISATEUR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROFIL`
--

DROP TABLE IF EXISTS `PROFIL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROFIL` (
  `IDPROFIL` int(11) NOT NULL,
  `CENTREINTERET` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPROFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROFIL`
--

LOCK TABLES `PROFIL` WRITE;
/*!40000 ALTER TABLE `PROFIL` DISABLE KEYS */;
INSERT INTO `PROFIL` VALUES (4,NULL,NULL),(7,'Shopping','05454564564'),(10,NULL,NULL),(52,NULL,NULL),(56,NULL,NULL);
/*!40000 ALTER TABLE `PROFIL` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SEQUENCE`
--

DROP TABLE IF EXISTS `SEQUENCE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SEQUENCE` (
  `SEQ_NAME` varchar(50) NOT NULL,
  `SEQ_COUNT` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`SEQ_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SEQUENCE`
--

LOCK TABLES `SEQUENCE` WRITE;
/*!40000 ALTER TABLE `SEQUENCE` DISABLE KEYS */;
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN',250);
/*!40000 ALTER TABLE `SEQUENCE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UTILISATEUR`
--

DROP TABLE IF EXISTS `UTILISATEUR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UTILISATEUR` (
  `IDUTILISATEUR` int(11) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `STATUT` int(11) DEFAULT NULL,
  `GROUPEPRINCIPAL_IDGROUPE` int(11) DEFAULT NULL,
  `PROFIL_IDPROFIL` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDUTILISATEUR`),
  UNIQUE KEY `EMAIL` (`EMAIL`),
  KEY `FK_UTILISATEUR_PROFIL_IDPROFIL` (`PROFIL_IDPROFIL`),
  KEY `FK_UTILISATEUR_GROUPEPRINCIPAL_IDGROUPE` (`GROUPEPRINCIPAL_IDGROUPE`),
  CONSTRAINT `FK_UTILISATEUR_GROUPEPRINCIPAL_IDGROUPE` FOREIGN KEY (`GROUPEPRINCIPAL_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`),
  CONSTRAINT `FK_UTILISATEUR_PROFIL_IDPROFIL` FOREIGN KEY (`PROFIL_IDPROFIL`) REFERENCES `PROFIL` (`IDPROFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UTILISATEUR`
--

LOCK TABLES `UTILISATEUR` WRITE;
/*!40000 ALTER TABLE `UTILISATEUR` DISABLE KEYS */;
INSERT INTO `UTILISATEUR` VALUES (6,'rodolphe.declerck@gmail.com','Declerck','pwd','Rodolphe',0,0,4),(9,'manon.barrois1@gmail.com','Barrois','pwd','Manon',0,0,7),(12,'mima.diagne@gmail.com','Diagne','pwd','Mame',0,0,10),(54,'yannick.buchart@gmail.com','Buchart','3catyrfh4v','Yannick',0,0,52),(58,'pl.hequet@gmail.com','Pierre-Louis','eb84x07s4w','Hequet',0,0,56);
/*!40000 ALTER TABLE `UTILISATEUR` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UTILISATEURENATTENTE`
--

DROP TABLE IF EXISTS `UTILISATEURENATTENTE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UTILISATEURENATTENTE` (
  `IDUTILISATEURENATTENTE` int(11) NOT NULL,
  `ANNEEDIPLOME` int(11) DEFAULT NULL,
  `DATENAISSANCE` datetime DEFAULT NULL,
  `DIPLOME` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `NOM` varchar(255) DEFAULT NULL,
  `PRENOM` varchar(255) DEFAULT NULL,
  `GROUPEPRINCIPAL_IDGROUPE` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDUTILISATEURENATTENTE`),
  KEY `FK_UTILISATEURENATTENTE_GROUPEPRINCIPAL_IDGROUPE` (`GROUPEPRINCIPAL_IDGROUPE`),
  CONSTRAINT `FK_UTILISATEURENATTENTE_GROUPEPRINCIPAL_IDGROUPE` FOREIGN KEY (`GROUPEPRINCIPAL_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UTILISATEURENATTENTE`
--

LOCK TABLES `UTILISATEURENATTENTE` WRITE;
/*!40000 ALTER TABLE `UTILISATEURENATTENTE` DISABLE KEYS */;
/*!40000 ALTER TABLE `UTILISATEURENATTENTE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UTILISATEURGROUPES`
--

DROP TABLE IF EXISTS `UTILISATEURGROUPES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UTILISATEURGROUPES` (
  `IDUTILISATEURGROUPES` int(11) NOT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `GROUPE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDUTILISATEURGROUPES`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UTILISATEURGROUPES`
--

LOCK TABLES `UTILISATEURGROUPES` WRITE;
/*!40000 ALTER TABLE `UTILISATEURGROUPES` DISABLE KEYS */;
INSERT INTO `UTILISATEURGROUPES` VALUES (5,'rodolphe.declerck@gmail.com','diplome'),(8,'manon.barrois1@gmail.com','diplome'),(11,'mima.diagne@gmail.com','diplome'),(53,'yannick.buchart@gmail.com','diplome'),(57,'pl.hequet@gmail.com','diplome');
/*!40000 ALTER TABLE `UTILISATEURGROUPES` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-01-27 17:25:26
