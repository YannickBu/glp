-- MySQL dump 10.13  Distrib 5.5.47, for debian-linux-gnu (x86_64)
--
-- Host: 172.28.1.165    Database: l1nk_rodolphe
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
  `GROUPE_IDGROUPE` int(11) DEFAULT NULL,
  `UTILISATEUR_IDUTILISATEUR` int(11) DEFAULT NULL,
  PRIMARY KEY (`IDARTICLE`),
  KEY `FK_ARTICLE_GROUPE_IDGROUPE` (`GROUPE_IDGROUPE`),
  KEY `FK_ARTICLE_UTILISATEUR_IDUTILISATEUR` (`UTILISATEUR_IDUTILISATEUR`),
  CONSTRAINT `FK_ARTICLE_UTILISATEUR_IDUTILISATEUR` FOREIGN KEY (`UTILISATEUR_IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`),
  CONSTRAINT `FK_ARTICLE_GROUPE_IDGROUPE` FOREIGN KEY (`GROUPE_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ARTICLE`
--

LOCK TABLES `ARTICLE` WRITE;
/*!40000 ALTER TABLE `ARTICLE` DISABLE KEYS */;
INSERT INTO `ARTICLE` VALUES (101,'Moi j\'ai une attitude sport ! ','2016-02-03 14:09:35',0,54),(102,'J\'ai eu mon diplome ! :)','2016-02-03 14:10:51',0,4),(111,'Salut tout le monde !','2016-02-03 14:16:34',1,110),(116,'Je reviendrais !','2016-02-03 14:18:59',1,115),(121,'Vivement les vacs ! ;)','2016-02-03 14:25:14',2,120),(122,'J\'adore ce site ! <3','2016-02-03 14:27:17',2,106);
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
INSERT INTO `COMPETENCE` VALUES (123,'Java',5,2);
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
  `LIEU` varchar(255) DEFAULT NULL,
  `PROFIL_IDPROFIL` int(11) DEFAULT NULL,
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
INSERT INTO `DIPLOME` VALUES (124,2013,2010,'DUT informatique de Gestion',NULL,2);
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
  `PAYS` varchar(255) DEFAULT NULL,
  `POSTE` varchar(255) DEFAULT NULL,
  `REGION` varchar(255) DEFAULT NULL,
  `PROFIL_IDPROFIL` int(11) DEFAULT NULL,
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
INSERT INTO `EXPERIENCE` VALUES (125,2015,2015,'Automatisation de tests','CGI','Lille','France','Stagiaire','Nord-Pas de Calais-Picardie',2);
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
  `ISGROUPEOFFICIEL` tinyint(1) DEFAULT '0',
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
INSERT INTO `GROUPE` VALUES (0,'Groupe des Sciences, Technologies, Santé','Sciences, Technologies, Santé',NULL,0),(1,'Groupe des Droit, Economie, Gestion','Droit, Economie, Gestion',NULL,0),(2,'Groupe des Sciences Humaines et Sociales','Sciences Humaines et Sociales',NULL,0),(205,'aaaab','aaaab',54,0),(206,'aaaabc','aaaabc',54,0),(207,'aaaabcd','aaaabcd',54,0),(251,'Groupe institutionnel de géométrie','Géométrie',54,1),(301,'Groupe officiel de l\'Algébrique','Algébrique',54,1),(351,'Groupe des pandas','Bambou',54,1),(401,'English Groupe','Anglais',4,1),(451,'por favor','Espanol',54,1),(453,'Arbeit','Allemand',4,1),(501,'Ni ao !','Chinois',4,1),(502,'Guerre !','Vietnamien',54,1),(551,'Yamété','Japonais',4,1),(601,'Sacrebleu','Français',4,1),(651,'E = mc2','Physique',4,1),(701,'DSK','Economie',4,1),(751,'H20','Chimie',4,1),(801,'SoS :x','Sociologie',4,1),(851,'Psykowak','Psychologie',4,1),(901,'Litté quoi ?','Littérature',4,1),(951,'Apéro','Cacahuète',4,1);
/*!40000 ALTER TABLE `GROUPE` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GROUPEANIME_ANIMATEUR`
--

DROP TABLE IF EXISTS `GROUPEANIME_ANIMATEUR`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GROUPEANIME_ANIMATEUR` (
  `utilisateurs_IDUTILISATEUR` int(11) NOT NULL,
  `groupes_IDGROUPE` int(11) NOT NULL,
  PRIMARY KEY (`utilisateurs_IDUTILISATEUR`,`groupes_IDGROUPE`),
  KEY `FK_GROUPEANIME_ANIMATEUR_groupes_IDGROUPE` (`groupes_IDGROUPE`),
  CONSTRAINT `FK_GROUPEANIME_ANIMATEUR_groupes_IDGROUPE` FOREIGN KEY (`groupes_IDGROUPE`) REFERENCES `GROUPE` (`IDGROUPE`),
  CONSTRAINT `GROUPEANIME_ANIMATEUR_utilisateurs_IDUTILISATEUR` FOREIGN KEY (`utilisateurs_IDUTILISATEUR`) REFERENCES `UTILISATEUR` (`IDUTILISATEUR`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GROUPEANIME_ANIMATEUR`
--

LOCK TABLES `GROUPEANIME_ANIMATEUR` WRITE;
/*!40000 ALTER TABLE `GROUPEANIME_ANIMATEUR` DISABLE KEYS */;
INSERT INTO `GROUPEANIME_ANIMATEUR` VALUES (54,205),(54,206),(54,207),(54,251),(54,301),(54,351),(54,401),(4,451),(54,453),(4,501),(54,502),(4,551),(54,601),(54,651),(4,701),(4,751),(4,801),(54,801),(4,851),(54,901),(54,951);
/*!40000 ALTER TABLE `GROUPEANIME_ANIMATEUR` ENABLE KEYS */;
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
INSERT INTO `GROUPE_UTILISATEUR` VALUES (4,1);
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
  `ANNEEDIPLOME` int(11) DEFAULT NULL,
  `CENTREINTERET` varchar(255) DEFAULT NULL,
  `DIPLOMEPRINCIPAL` varchar(255) DEFAULT NULL,
  `MESATTENTES` varchar(255) DEFAULT NULL,
  `SITUATION` varchar(255) DEFAULT NULL,
  `TELEPHONE` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`IDPROFIL`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROFIL`
--

LOCK TABLES `PROFIL` WRITE;
/*!40000 ALTER TABLE `PROFIL` DISABLE KEYS */;
INSERT INTO `PROFIL` VALUES (2,2016,'Emeu','MASTER MIAGE','une pomme !','Stagiaire a CGI','0623686545'),(52,2016,NULL,'MASTER MIAGE',NULL,'Nouvel arrivant',NULL),(104,2000,'','Master Sociologie','Me faire des contacts','Chomeur',''),(108,2014,NULL,'Licence de droit',NULL,'Nouvel arrivant',NULL),(113,1990,'','Master de droit','','Annee sabbatique',''),(118,1992,'','Licence de psychologie','','Nouvel arrivant',''),(129,2016,NULL,'MASTER MIAGE',NULL,'Nouvel arrivant',NULL),(132,2016,NULL,'MASTER MIAGE',NULL,'Nouvel arrivant',NULL),(135,2016,NULL,'MASTER MIAGE',NULL,'Nouvel arrivant',NULL);
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
INSERT INTO `SEQUENCE` VALUES ('SEQ_GEN',150);
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
INSERT INTO `UTILISATEUR` VALUES (4,'rodolphe.declerck@gmail.com','Declerck','pwd','Rodolphe',0,0,2),(54,'pl.hequet@gmail.com','Hecquet','pwd','Pierre-Louis',0,0,52),(106,'jean-kevin.mouloude@gmail.com','Mouloude','pwd','Jean-Kevin',0,2,104),(110,'jean.jean@gmail.com','Jean','pwd','Jean',0,1,108),(115,'paul.bismute@gmail.com','Bismute','pwd','Paul',0,1,113),(120,'john.doe@gmail.com','Doe','pwd','John',0,2,118),(131,'manon.barrois1@gmail.com','Barrois','pwd','Manon',0,0,129),(134,'mima.diagne@gmail.com','Diagne','pwd','Mame',0,0,132),(137,'yannick.buchart@gmail.com','Buchart','pwd','Yannick',0,0,135);
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
INSERT INTO `UTILISATEURGROUPES` VALUES (3,'rodolphe.declerck@gmail.com','personnel'),(4,'rodolphe.declerck@gmail.com','administrateur'),(5,'rodolphe.declerck@gmail.com','moderateur'),(53,'pl.hequet@gmail.com','personnel'),(63,'test1@l1nk.com','personnel'),(105,'jean-kevin.mouloude@gmail.com','diplome'),(109,'jean.jean@gmail.com','diplome'),(114,'paul.bismute@gmail.com','diplome'),(119,'john.doe@gmail.com','diplome'),(130,'manon.barrois1@gmail.com','diplome'),(133,'mima.diagne@gmail.com','diplome'),(136,'yannick.buchart@gmail.com','diplome'),(452,'pl.hequet@gmail.com','moderateur'),(454,'rodolphe.declerck@gmail.com','moderateur');
/*!40000 ALTER TABLE `UTILISATEURGROUPES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PAYS`
--

DROP TABLE IF EXISTS `PAYS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PAYS` (
  `Nom` varchar(50) NOT NULL,
  `Code` varchar(4) NOT NULL DEFAULT '',
  PRIMARY KEY (`Code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PAYS`
--

LOCK TABLES `PAYS` WRITE;
/*!40000 ALTER TABLE `PAYS` DISABLE KEYS */;
INSERT INTO `PAYS` VALUES ('Austria','A'),('Afghanistan','AFG'),('Antigua and Barbuda','AG'),('Albania','AL'),('American Samoa','AMSA'),('Andorra','AND'),('Angola','ANG'),('Armenia','ARM'),('Aruba','ARU'),('Australia','AUS'),('Anguilla','AXA'),('Azerbaijan','AZ'),('Belgium','B'),('Bangladesh','BD'),('Barbados','BDS'),('Benin','BEN'),('Bermuda','BERM'),('Bulgaria','BG'),('Bhutan','BHT'),('Burundi','BI'),('Bosnia and Herzegovina','BIH'),('Bolivia','BOL'),('Brazil','BR'),('Bahrain','BRN'),('Brunei','BRU'),('Bahamas','BS'),('British Virgin Islands','BVIR'),('Belarus','BY'),('Belize','BZ'),('Cuba','C'),('Cameroon','CAM'),('Cayman Islands','CAYM'),('Canada','CDN'),('Ceuta','CEU'),('Switzerland','CH'),('Cote dIvoire','CI'),('Sri Lanka','CL'),('China','CN'),('Colombia','CO'),('Cocos Islands','COCO'),('Comoros','COM'),('Cook Islands','COOK'),('Costa Rica','CR'),('Curacao','CUR'),('Cape Verde','CV'),('Cyprus','CY'),('Czech Republic','CZ'),('Germany','D'),('Djibouti','DJI'),('Denmark','DK'),('Dominican Republic','DOM'),('Algeria','DZ'),('Spain','E'),('Kenya','EAK'),('Tanzania','EAT'),('Uganda','EAU'),('Ecuador','EC'),('Eritrea','ER'),('El Salvador','ES'),('Egypt','ET'),('Ethiopia','ETH'),('Estonia','EW'),('France','F'),('Falkland Islands','FALK'),('Faroe Islands','FARX'),('Fiji','FJI'),('Liechtenstein','FL'),('French Polynesia','FPOL'),('Micronesia','FSM'),('Gabon','G'),('Gaza Strip','GAZA'),('United Kingdom','GB'),('Guernsey','GBG'),('Jersey','GBJ'),('Isle of Man','GBM'),('Gibraltar','GBZ'),('Guatemala','GCA'),('Georgia','GE'),('Ghana','GH'),('Guinea-Bissau','GNB'),('Equatorial Guinea','GQ'),('Greece','GR'),('Greenland','GROX'),('Guadeloupe','GUAD'),('Guam','GUAM'),('Guyana','GUY'),('Hungary','H'),('Honduras','HCA'),('Saint Helena','HELX'),('Hong Kong','HONX'),('Croatia','HR'),('Italy','I'),('Israel','IL'),('India','IND'),('Iran','IR'),('Ireland','IRL'),('Iraq','IRQ'),('Iceland','IS'),('Japan','J'),('Jamaica','JA'),('Jordan','JOR'),('Cambodia','K'),('Kazakhstan','KAZ'),('Kyrgyzstan','KGZ'),('Kiribati','KIR'),('Saint Kitts and Nevis','KN'),('Kosovo','KOS'),('Kuwait','KWT'),('Luxembourg','L'),('Laos','LAO'),('Libya','LAR'),('Liberia','LB'),('Lesotho','LS'),('Lithuania','LT'),('Latvia','LV'),('Malta','M'),('Morocco','MA'),('Macao','MACX'),('Malaysia','MAL'),('Martinique','MART'),('Mayotte','MAYO'),('Monaco','MC'),('Moldova','MD'),('Melilla','MEL'),('Mexico','MEX'),('Marshall Islands','MH'),('Montenegro','MNE'),('Mongolia','MNG'),('Montserrat','MNTS'),('Mozambique','MOC'),('Mauritius','MS'),('Maldives','MV'),('Malawi','MW'),('Myanmar','MYA'),('Norway','N'),('Namibia','NAM'),('Nauru','NAU'),('New Caledonia','NCA'),('Nepal','NEP'),('Nicaragua','NIC'),('Niue','NIUE'),('Netherlands','NL'),('Sint Maarten','NLSM'),('Northern Mariana Islands','NMIS'),('North Korea','NOK'),('Norfolk Island','NORF'),('New Zealand','NZ'),('Oman','OM'),('Portugal','P'),('Panama','PA'),('Palau','PAL'),('Peru','PE'),('Pitcairn','PITC'),('Pakistan','PK'),('Poland','PL'),('Papua New Guinea','PNG'),('Puerto Rico','PR'),('Paraguay','PY'),('Qatar','Q'),('Russia','R'),('Argentina','RA'),('Botswana','RB'),('Taiwan','RC'),('Central African Republic','RCA'),('Congo','RCB'),('Chile','RCH'),('Reunion','REUN'),('Guinea','RG'),('Haiti','RH'),('Indonesia','RI'),('Mauritania','RIM'),('Lebanon','RL'),('Madagascar','RM'),('Mali','RMM'),('Niger','RN'),('Romania','RO'),('South Korea','ROK'),('Uruguay','ROU'),('Philippines','RP'),('South Africa','RSA'),('San Marino','RSM'),('Togo','RT'),('Rwanda','RWA'),('Sweden','S'),('Saudi Arabia','SA'),('Saint Barthelemy','SBAR'),('Swaziland','SD'),('Finland','SF'),('Singapore','SGP'),('Slovakia','SK'),('Solomon Islands','SLB'),('Slovenia','SLO'),('Saint Martin','SMAR'),('Suriname','SME'),('Senegal','SN'),('Somalia','SP'),('Saint Pierre and Miquelon','SPMI'),('Serbia','SRB'),('South Sudan','SSD'),('Sao Tome and Principe','STP'),('Sudan','SUD'),('Svalbard','SVAX'),('Seychelles','SY'),('Syria','SYR'),('Tajikistan','TAD'),('Chad','TCH'),('Thailand','THA'),('Timor-Leste','TL'),('Turkmenistan','TM'),('Tunisia','TN'),('Tonga','TO'),('Tokelau','TOK'),('Turkey','TR'),('Trinidad and Tobago','TT'),('Turks and Caicos Islands','TUCA'),('Tuvalu','TUV'),('Ukraine','UA'),('United Arab Emirates','UAE'),('United States','USA'),('Uzbekistan','UZB'),('Holy See','V'),('Virgin Islands','VIRG'),('Vietnam','VN'),('Vanuatu','VU'),('Wallis and Futuna','WAFU'),('Gambia','WAG'),('Sierra Leone','WAL'),('Nigeria','WAN'),('Dominica','WD'),('West Bank','WEST'),('Grenada','WG'),('Saint Lucia','WL'),('Samoa','WS'),('Western Sahara','WSA'),('Saint Vincent and the Grenadines','WV'),('Christmas Island','XMAS'),('Yemen','YE'),('Venezuela','YV'),('Zambia','Z'),('Zaire','ZRE'),('Zimbabwe','ZW');
/*!40000 ALTER TABLE `PAYS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `REGION`
--

DROP TABLE IF EXISTS `REGION`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `REGION` (
  `Nom` varchar(50) NOT NULL DEFAULT '',
  `Pays` varchar(4) NOT NULL DEFAULT '',
  PRIMARY KEY (`Nom`,`Pays`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `REGION`
--

LOCK TABLES `REGION` WRITE;
/*!40000 ALTER TABLE `REGION` DISABLE KEYS */;
INSERT INTO `REGION` VALUES ('??rnak','TR'),('?anl?urfa','TR'),('?l?skie','PL'),('?ódzkie','PL'),('?stanbul','TR'),('?wi?tokrzyskie','PL'),('?zmir','TR'),('A?r?','TR'),('Aargau','CH'),('Abia','WAN'),('Abruzzo','I'),('Abuja','WAN'),('Aceh','RI'),('Acre','BR'),('Ad?yaman','TR'),('Adamaoua','CAM'),('Adamawa','WAN'),('Adana','TR'),('Addis Ababa','ETH'),('Adrar','RIM'),('Adygeya','R'),('Affar','ETH'),('Afghanistan','AFG'),('Afyonkarahisar','TR'),('Agadez','RN'),('Aghion Oros','GR'),('Agnéby','CI'),('Aguascalientes','MEX'),('Aichi','J'),('Akershus','N'),('Akhal','TM'),('Akita','J'),('Akmola','KAZ'),('Aksaray','TR'),('Aktobe','KAZ'),('Akwa Ibom','WAN'),('Al Hasakah','SYR'),('Al Ladhiqiyah','SYR'),('Al Qunaytirah','SYR'),('al-Anbar','IRQ'),('Al-Baha','SA'),('Al-Hudud ash Shamaliyah','SA'),('Al-Jouf','SA'),('Al-Madinah Al-Monawarah','SA'),('al-Muthanna','IRQ'),('al-Najaf','IRQ'),('al-Qadisiya','IRQ'),('Al-Qaseem','SA'),('Al-Riyadh','SA'),('Alabama','USA'),('Alagoas','BR'),('Alajuela','CR'),('Aland','SF'),('Alaska','USA'),('Alba','RO'),('Albania','AL'),('Alberta','CDN'),('Alborz','IR'),('Algarve','P'),('Algeria','DZ'),('Almaty','KAZ'),('Almaty City','KAZ'),('Alsace','F'),('Altay','R'),('Altayskiy','R'),('Amapá','BR'),('Amasya','TR'),('Amazonas','BR'),('Amazonas','CO'),('Amazonas','PE'),('Amazonas','YV'),('American Samoa','AMSA'),('Amhara','ETH'),('Amur','R'),('Anambra','WAN'),('Anatolikis Makedonias kai Thrakis','GR'),('Ancash','PE'),('Andalucía','E'),('Andaman and Nicobar Islands','IND'),('Andhra Pradesh','IND'),('Andijon','UZB'),('Andorra','AND'),('Anguilla','AXA'),('Anhui','CN'),('Ankara','TR'),('Antalya','TR'),('Antigua and Barbuda','AG'),('Antioquia','CO'),('Antofagasta','RCH'),('Antwerpen','B'),('Anzoátegui','YV'),('Aomori','J'),('Appenzell Ausserrhoden','CH'),('Appenzell Innerrhoden','CH'),('Apure','YV'),('Apurímac','PE'),('Aquitaine','F'),('Ar Raqqah','SYR'),('Arad','RO'),('Aragón','E'),('Aragua','YV'),('Arauca','CO'),('Araucanía','RCH'),('Ardahan','TR'),('Ardebil','IR'),('Arequipa','PE'),('Arge?','RO'),('Arica y Parinacota','RCH'),('Arizona','USA'),('Arkansas','USA'),('Arkhangel´skaya','R'),('Armenia','ARM'),('Artemisa','C'),('Artvin','TR'),('Aruba','ARU'),('Arunachal Pradesh','IND'),('Arusha','EAT'),('As Sulaymaniyah','IRQ'),('As Suwayda\'','SYR'),('Aseer','SA'),('Ash Sharqiyah','SA'),('Ashanti','GH'),('Ashgabat','TM'),('Assaba','RIM'),('Assam','IND'),('Astana','KAZ'),('Astrakhanskaya','R'),('Asturias','E'),('Atacama','RCH'),('Atlántico','CO'),('Atlántida','HCA'),('Attikis','GR'),('Atyrau','KAZ'),('Aust-Agder','N'),('Australia Capital Territory','AUS'),('Auvergne','F'),('Aveiro','P'),('Ayacucho','PE'),('Ayd?n','TR'),('Ayeyarwady','MYA'),('Aysén','RCH'),('Azad Jammu and Kashmir','PK'),('Azerbaijan','AZ'),('Azores','P'),('Babylon','IRQ'),('Bac?u','RO'),('Bács-Kiskun','H'),('Baden-Württemberg','D'),('Bafing','CI'),('Baghdad','IRQ'),('Bago','MYA'),('Bahamas','BS'),('Bahia','BR'),('Bahrain','BRN'),('Baikonur','KAZ'),('Baja California','MEX'),('Baja California Sur','MEX'),('Bal?kesir','TR'),('Bali','RI'),('Balkan','TM'),('Balochistan','PK'),('Bamako','RMM'),('Bandundu','ZRE'),('Banskobystrický','SK'),('Banten','RI'),('Baranya','H'),('Barbados','BDS'),('Barinas','YV'),('Barisal','BD'),('Bart?n','TR'),('Bas-Congo','ZRE'),('Bas-Sassandra','CI'),('Basel-Landschaft','CH'),('Basel-Stadt','CH'),('Bashkortostan','R'),('Basilicata','I'),('Basrah','IRQ'),('Basse Normandie','F'),('Batman','TR'),('Bauchi','WAN'),('Bayburt','TR'),('Bayelsa','WAN'),('Bayern','D'),('Beijing','CN'),('Beja','P'),('Békés','H'),('Belgorodskaya','R'),('Belize','BZ'),('Bengkulu','RI'),('Bengo','ANG'),('Benguela','ANG'),('Beni','BOL'),('Benin','BEN'),('Benishangul-Gumuz','ETH'),('Benue','WAN'),('Berlin','D'),('Bermuda','BERM'),('Bern','CH'),('Bhutan','BHT'),('Bicol','RP'),('Bié','ANG'),('Bihar','IND'),('Bihor','RO'),('Bilecik','TR'),('Bingöl','TR'),('Bío-Bío','RCH'),('Bistri?a-N?s?ud','RO'),('Bitlis','TR'),('Blekinge','S'),('Bocas del Toro','PA'),('Bolívar','CO'),('Bolívar','YV'),('Bolu','TR'),('Boreioy Aigaioy','GR'),('Borno','WAN'),('Borsod-Abaúj-Zemplén','H'),('Boto?ani','RO'),('Botswana','RB'),('Bouenza','RCB'),('Bourgogne','F'),('Boyacá','CO'),('Br?ila','RO'),('Br?ko','BIH'),('Bra?ov','RO'),('Brabant Wallon','B'),('Braga','P'),('Braganca','P'),('Brakna','RIM'),('Brandenburg','D'),('Bratislavský','SK'),('Brazzaville','RCB'),('Bremen','D'),('Brest','BY'),('Bretagne','F'),('British Columbia','CDN'),('British Virgin Islands','BVIR'),('Brong Ahafo','GH'),('Brunei','BRU'),('Bruxelles','B'),('Bryanskaya','R'),('Bucure?ti','RO'),('Budapest','H'),('Buenos Aires','RA'),('Bulgaria','BG'),('Burdur','TR'),('Burgenland','A'),('Burkina Faso','BF'),('Bursa','TR'),('Burundi','BI'),('Buryat','R'),('Bushehr','IR'),('Buskerud','N'),('Buxoro','UZB'),('Buz?u','RO'),('C?l?ra?i','RO'),('Cabinda','ANG'),('Cabo Delgado','MOC'),('Cagayan Valley','RP'),('Cajamarca','PE'),('Calabarzon','RP'),('Calabria','I'),('Caldas','CO'),('California','USA'),('Callao','PE'),('Camagüey','C'),('Cambodia','K'),('Campania','I'),('Campeche','MEX'),('Çanakkale','TR'),('Canarias','E'),('Çank?r?','TR'),('Cantabria','E'),('Cape Verde','CV'),('Caquetá','CO'),('Cara?-Severin','RO'),('Carabobo','YV'),('Caraga','RP'),('Cartago','CR'),('Casanare','CO'),('Castelo Branco','P'),('Castilla y León','E'),('Castilla-La Mancha','E'),('Cataluña','E'),('Catamarca','RA'),('Cauca','CO'),('Cayman Islands','CAYM'),('Ceará','BR'),('Central','GH'),('Central','Z'),('Central African Republic','RCA'),('Central Luzon','RP'),('Central Visayas','RP'),('Centre','CAM'),('Centre','F'),('Cesar','CO'),('Ceuta','CEU'),('Chaco','RA'),('Chad','TCH'),('Chaharmahal and Bakhtiyari','IR'),('Champagne-Ardenne','F'),('Chandigarh','IND'),('Chaouia Ouardigha','MA'),('Chechenskaya','R'),('Chelyabinsk','R'),('Cherkas´ka','UA'),('Chernihivs´ka','UA'),('Chernivets´ka','UA'),('Chhattisgarh','IND'),('Chiapas','MEX'),('Chiba','J'),('Chihuahua','MEX'),('Chin','MYA'),('Chiriquí','PA'),('Chittagong','BD'),('Chocó','CO'),('Choluteca','HCA'),('Chongqing','CN'),('Christmas Island','XMAS'),('Chubut','RA'),('Chukotka','R'),('Chuquisaca','BOL'),('Chuvash','R'),('Cidade de Maputo','MOC'),('Ciego de Ávila','C'),('Cienfuegos','C'),('Cluj','RO'),('Coahuila','MEX'),('Cochabamba','BOL'),('Coclé','PA'),('Cocos Islands','COCO'),('Coimbra','P'),('Cojedes','YV'),('Colima','MEX'),('Colón','HCA'),('Colón','PA'),('Colorado','USA'),('Comayagua','HCA'),('Comoros','COM'),('Connecticut','USA'),('Constan?a','RO'),('Continental','GQ'),('Cook Islands','COOK'),('Copán','HCA'),('Copperbelt','Z'),('Coquimbo','RCH'),('Cordillera','RP'),('Córdoba','CO'),('Córdoba','RA'),('Corrientes','RA'),('Corse','F'),('Cortés','HCA'),('Çorum','TR'),('Covasna','RO'),('Croatia','HR'),('Cross River','WAN'),('Csongrád','H'),('Cuando Cubango','ANG'),('Cuanza Norte','ANG'),('Cuanza Sul','ANG'),('Cundinamarca','CO'),('Cunene','ANG'),('Curacao','CUR'),('Cusco','PE'),('Cuvette','RCB'),('Cuvette-Ouest','RCB'),('Cyprus','CY'),('Dadra and Nagar Haveli','IND'),('Dagestan','R'),('Dakar','SN'),('Dakhlet Nouadhibou','RIM'),('Dalarna','S'),('Daman and Diu','IND'),('Dâmbovi?a','RO'),('Dar es Salaam','EAT'),('Darién','PA'),('Dar`a','SYR'),('Dashhowuz','TM'),('Davao','RP'),('Dayr az Zawr','SYR'),('Delaware','USA'),('Delhi','IND'),('Delta','WAN'),('Delta Amacuro','YV'),('Denguélé','CI'),('Denizli','TR'),('Dhaka','BD'),('DI Yogyakarta','RI'),('Diala','IRQ'),('Diffa','RN'),('Dimashq','SYR'),('Diourbel','SN'),('Dire Dawa','ETH'),('District of Columbia','USA'),('Distrito Federal','BR'),('Distrito Federal','MEX'),('Distrito Federal','RA'),('Distrito Federal','YV'),('Diyarbak?r','TR'),('Djibouti','DJI'),('DKI Jakarta','RI'),('Dnipropetrovs´ka','UA'),('Dodoma','EAT'),('Dolj','RO'),('Dolno?l?skie','PL'),('Dominica','WD'),('Dominican Republic','DOM'),('Donets´ka','UA'),('Dosso','RN'),('Doukala Abda','MA'),('Drenthe','NL'),('Duhouk','IRQ'),('Durango','MEX'),('Dushanbe','TAD'),('Dytikis Elladas','GR'),('Dytikis Makedonias','GR'),('Düzce','TR'),('East Azarbayejan','IR'),('East Midlands','GB'),('East of England','GB'),('East-Kazakhstan','KAZ'),('Eastern','GH'),('Eastern','Z'),('Eastern Cape','RSA'),('Eastern Visayas','RP'),('Ebonyi','WAN'),('Ecuador','EC'),('Edirne','TR'),('Edo','WAN'),('Egypt','ET'),('Ehime','J'),('Ekiti','WAN'),('El Paraíso','HCA'),('El Salvador','ES'),('Elaz??','TR'),('Emberá','PA'),('Emilia-Romagna','I'),('Entre Ríos','RA'),('Enugu','WAN'),('Equateur','ZRE'),('Erbil','IRQ'),('Eritrea','ER'),('Erzincan','TR'),('Erzurum','TR'),('Esfahan','IR'),('Eski?ehir','TR'),('Espírito Santo','BR'),('Est','CAM'),('Estonia','EW'),('Evora','P'),('Extremadura','E'),('Extrême-Nord','CAM'),('Falcón','YV'),('Falkland Islands','FALK'),('Farg?ona','UZB'),('Faroe Islands','FARX'),('Fars','IR'),('FATA','PK'),('Fatick','SN'),('FCT Islamabad','PK'),('Federacija Bosne i Hercegovine','BIH'),('Fejér','H'),('Fes Boulemane','MA'),('Fiji','FJI'),('Finnmark','N'),('Flevoland','NL'),('Florida','USA'),('Formosa','RA'),('Franche-Comté','F'),('Francisco Morazán','HCA'),('Free State','RSA'),('French Guiana','FGU'),('French Polynesia','FPOL'),('Fribourg','CH'),('Friesland','NL'),('Friuli-Venezia Giulia','I'),('Fromager','CI'),('Fujian','CN'),('Fukui','J'),('Fukuoka','J'),('Fukushima','J'),('Gabon','G'),('Gala?i','RO'),('Galicia','E'),('Gambella','ETH'),('Gambia','WAG'),('Gansu','CN'),('Gao','RMM'),('Gauteng','RSA'),('Gaza','MOC'),('Gaza Strip','GAZA'),('Gaziantep','TR'),('Geita','EAT'),('Gelderland','NL'),('Genève','CH'),('Georgia','GE'),('Georgia','USA'),('Gharb Chrarda Beni Hssen','MA'),('Gibraltar','GBZ'),('Gifu','J'),('Gilgit-Baltistan','PK'),('Gillan','IR'),('Giresun','TR'),('Giurgiu','RO'),('Glarus','CH'),('Goa','IND'),('Goiás','BR'),('Golestan','IR'),('Gombe','WAN'),('Gomel','BY'),('Gorgol','RIM'),('Gorj','RO'),('Gorno-Badakhshan','TAD'),('Gorontalo','RI'),('Gotland','S'),('Gracias a Dios','HCA'),('Grand Casablanca','MA'),('Granma','C'),('Graubünden','CH'),('Greater Accra','GH'),('Greenland','GROX'),('Grenada','WG'),('Grodno','BY'),('Groningen','NL'),('Guadeloupe','GUAD'),('Guainía','CO'),('Guam','GUAM'),('Guanacaste','CR'),('Guanajuato','MEX'),('Guangdong','CN'),('Guangxi','CN'),('Guantánamo','C'),('Guarda','P'),('Guárico','YV'),('Guatemala','GCA'),('Guaviare','CO'),('Guelmim Es Semara','MA'),('Guernsey','GBG'),('Guerrero','MEX'),('Guidimagha','RIM'),('Guinea','RG'),('Guinea-Bissau','GNB'),('Guizhou','CN'),('Gujarat','IND'),('Gumma','J'),('Guyana','GUY'),('Gy?r-Moson-Sopron','H'),('Gümü?hane','TR'),('Gävleborg','S'),('Ha\'il','SA'),('HaDarom','IL'),('Haeme','SF'),('Hainan','CN'),('Hainaut','B'),('Haiti','RH'),('Hajdú-Bihar','H'),('Hakkari','TR'),('Halab','SYR'),('Halland','S'),('Hamah','SYR'),('Hamburg','D'),('Hamedan','IR'),('HaMerkaz','IL'),('Harari','ETH'),('Harghita','RO'),('Haryana','IND'),('Hatay','TR'),('Haut-Sassandra','CI'),('Haute-Normandie','F'),('Hawaii','USA'),('HaZafon','IL'),('Hebei','CN'),('Hedmark','N'),('Hefa','IL'),('Heilongjiang','CN'),('Henan','CN'),('Heredia','CR'),('Herrera','PA'),('Hessen','D'),('Heves','H'),('Hidalgo','MEX'),('Himachal Pradesh','IND'),('Hims','SYR'),('Hiroshima','J'),('Hodh Chargui','RIM'),('Hodh El Gharbi','RIM'),('Hokkaido','J'),('Holguín','C'),('Holy See','V'),('Hong Kong','HONX'),('Hordaland','N'),('Hormozgan','IR'),('Hovedstaden','DK'),('Huambo','ANG'),('Huancavelica','PE'),('Huánuco','PE'),('Hubei','CN'),('Huíla','ANG'),('Huila','CO'),('Hunan','CN'),('Hunedoara','RO'),('Hyogo','J'),('I?d?r','TR'),('Ia?i','RO'),('Ialomi?a','RO'),('Ibaraki','J'),('Ica','PE'),('Iceland','IS'),('Idaho','USA'),('Idlib','SYR'),('Ilam','IR'),('Île-de-France','F'),('Ilfov','RO'),('Illes Balears','E'),('Illinois','USA'),('Ilocos','RP'),('Imo','WAN'),('Inchiri','RIM'),('Indiana','USA'),('Ingushskaya','R'),('Inhambane','MOC'),('Insular','GQ'),('Intibucá','HCA'),('Ionion Nison','GR'),('Iowa','USA'),('Ipeiroy','GR'),('Ireland','IRL'),('Iringa','EAT'),('Irkutsk','R'),('Ishikawa','J'),('Isla de la Juventud','C'),('Islas de la Bahía','HCA'),('Isle of Man','GBM'),('Isparta','TR'),('Ivano-Frankivs´ka','UA'),('Ivanovskaya','R'),('Iwate','J'),('Jalisco','MEX'),('Jamaica','JA'),('Jambi','RI'),('Jammu and Kashmir','IND'),('Jász-Nagykun-Szolnok','H'),('Jawa Barat','RI'),('Jawa Tengah','RI'),('Jawa Timur','RI'),('Jazan','SA'),('Jersey','GBJ'),('Jewish ao.','R'),('Jharkhand','IND'),('Jiangsu','CN'),('Jiangxi','CN'),('Jigawa','WAN'),('Jiho?eský','CZ'),('Jihomoravský','CZ'),('Jilin','CN'),('Jizzax','UZB'),('Johor','MAL'),('Jordan','JOR'),('Jujuy','RA'),('Junín','PE'),('Jura','CH'),('Jämtland','S'),('Jönköping','S'),('K?r?ehir','TR'),('K?r?kkale','TR'),('K?rklareli','TR'),('Kabardino-Balkarskaya','R'),('Kachin','MYA'),('Kaduna','WAN'),('Kaffrine','SN'),('Kagawa','J'),('Kagera','EAT'),('Kagoshima','J'),('Kahramanmara?','TR'),('Kalimantan Barat','RI'),('Kalimantan Selatan','RI'),('Kalimantan Tengah','RI'),('Kalimantan Timur','RI'),('Kaliningradskaya','R'),('Kalmar','S'),('Kalmykiya','R'),('Kaluzhskaya','R'),('Kamchatka','R'),('Kanagawa','J'),('Kano','WAN'),('Kansas','USA'),('Kaolack','SN'),('Karabük','TR'),('Karachayevo-Cherkesskaya','R'),('Karaganda','KAZ'),('Karaman','TR'),('Karelia','R'),('Karlovarský','CZ'),('Karnataka','IND'),('Kars','TR'),('Kasai-Occidental','ZRE'),('Kasai-Oriental','ZRE'),('Kaskazini Pemba','EAT'),('Kaskazini Unguja','EAT'),('Kastamonu','TR'),('Katanga','ZRE'),('Katavi','EAT'),('Katsina','WAN'),('Kayah','MYA'),('Kayes','RMM'),('Kayin','MYA'),('Kayseri','TR'),('Kebbi','WAN'),('Kedah','MAL'),('Kédougou','SN'),('Kelantan','MAL'),('Kemerov','R'),('Kentrikis Makedonias','GR'),('Kentucky','USA'),('Kenya','EAK'),('Kepulauan Bangka Belitung','RI'),('Kepulauan Riau','RI'),('Kerala','IND'),('Kerbela','IRQ'),('Kerman','IR'),('Kermanshah','IR'),('Khabarov','R'),('Khakasiya','R'),('Khanty Mansi ao','R'),('Kharkivs´ka','UA'),('Khatlon','TAD'),('Khersons´ka','UA'),('Khmel´nyts´ka','UA'),('Khorasan-e-Razavi','IR'),('Khulna','BD'),('Khuzestan','IR'),('Khyber Pakhtunkhwa','PK'),('Kidal','RMM'),('Kigoma','EAT'),('Kilimanjaro','EAT'),('Kilis','TR'),('Kinshasa','ZRE'),('Kiribati','KIR'),('Kirkuk','IRQ'),('Kirov','R'),('Kirovohrads´ka','UA'),('Kocaeli','TR'),('Kochi','J'),('Kogi','WAN'),('Kohgiluyeh and Boyerahmad','IR'),('Kolda','SN'),('Komárom-Esztergom','H'),('Komi','R'),('Konya','TR'),('Kosovo','KOS'),('Kostanai','KAZ'),('Kostromskaya','R'),('Kouilou','RCB'),('Koulikoro','RMM'),('Košický','SK'),('Královéhradecký','CZ'),('Krasnodarskiy','R'),('Krasnoyarsk','R'),('Kritis','GR'),('Kronoberg','S'),('Krym','UA'),('Kuala Lumpur','MAL'),('Kujawsko-Pomorskie','PL'),('Kumamoto','J'),('Kuna Yala','PA'),('Kuopio','SF'),('Kurdestan','IR'),('Kurgan','R'),('Kurskaya','R'),('Kusini Pemba','EAT'),('Kusini Unguja','EAT'),('Kuwait','KWT'),('Kwara','WAN'),('KwaZulu-Natal','RSA'),('Kyïv','UA'),('Kyïvs´ka','UA'),('Kymi','SF'),('Kyoto','J'),('Kyrgyzstan','KGZ'),('Kütahya','TR'),('Kyzylorda','KAZ'),('Kärnten','A'),('l1nk_mame.REGIONs of Republican Subordination','TAD'),('La Guajira','CO'),('La Habana','C'),('La Libertad','PE'),('La Pampa','RA'),('La Paz','BOL'),('La Paz','HCA'),('La Rioja','E'),('La Rioja','RA'),('Labuan','MAL'),('Lacs','CI'),('Lagos','RCH'),('Lagos','WAN'),('Lagunes','CI'),('Lakshadweep','IND'),('Lambayeque','PE'),('Lampung','RI'),('Languedoc-Roussillon','F'),('Laos','LAO'),('Lappia','SF'),('Lara','YV'),('Las Tunas','C'),('Latvia','LV'),('Lazio','I'),('Lebanon','RL'),('Lebap','TM'),('Leiria','P'),('Lékoumou','RCB'),('Lempira','HCA'),('Leningradskaya','R'),('Lesotho','LS'),('Liaoning','CN'),('Liberecký','CZ'),('Liberia','LB'),('Libertador General Bernardo O\'Higgins','RCH'),('Libya','LAR'),('Liechtenstein','FL'),('Liège','B'),('Liguria','I'),('Likouala','RCB'),('Lima','PE'),('Lima City','PE'),('Limburg','B'),('Limburg','NL'),('Limón','CR'),('Limousin','F'),('Limpopo','RSA'),('Lindi','EAT'),('Lipetskaya','R'),('Lisboa','P'),('Lithuania','LT'),('Littoral','CAM'),('Lombardia','I'),('London','GB'),('Lorestan','IR'),('Loreto','PE'),('Lorraine','F'),('Los Santos','PA'),('Louga','SN'),('Louisiana','USA'),('Luanda','ANG'),('Luapula','Z'),('Lubelskie','PL'),('Lubuskie','PL'),('Luhans´ka','UA'),('Lunda-Norte','ANG'),('Lunda-Sul','ANG'),('Lusaka','Z'),('Luxembourg','B'),('Luxembourg','L'),('Luzern','CH'),('L´vivs´ka','UA'),('Ma?opolskie','PL'),('Macao','MACX'),('Macedonia','MK'),('Madagascar','RM'),('Madeira','P'),('Madhya Pradesh','IND'),('Madre de Dios','PE'),('Madrid','E'),('Magadan','R'),('Magallanes','RCH'),('Magdalena','CO'),('Magwe','MYA'),('Maharashtra','IND'),('Maine','USA'),('Makkah Al-Mokarramah','SA'),('Malanje','ANG'),('Malatya','TR'),('Malawi','MW'),('Maldives','MV'),('Malta','M'),('Maluku','RI'),('Maluku Utara','RI'),('Mandalay','MYA'),('Mangistau','KAZ'),('Manica','MOC'),('Maniema','ZRE'),('Manipur','IND'),('Manisa','TR'),('Manitoba','CDN'),('Manyara','EAT'),('Maputo','MOC'),('Mara','EAT'),('Maradi','RN'),('Marahoué','CI'),('Maramure?','RO'),('Maranhão','BR'),('Marche','I'),('Mardin','TR'),('Mariy-El','R'),('Markazi','IR'),('Marrakech Tensift Al Haouz','MA'),('Marshall Islands','MH'),('Martinique','MART'),('Mary','TM'),('Maryland','USA'),('Massachusetts','USA'),('Matam','SN'),('Matanzas','C'),('Mato Grosso','BR'),('Mato Grosso do Sul','BR'),('Maule','RCH'),('Mauritius','MS'),('Mayabeque','C'),('Mayotte','MAYO'),('Maysan','IRQ'),('Mazandaran','IR'),('Mazowieckie','PL'),('Mbeya','EAT'),('Mecklenburg-Vorpommern','D'),('Meghalaya','IND'),('Mehedin?i','RO'),('Meknes Tafilalet','MA'),('Melaka','MAL'),('Melilla','MEL'),('Mendoza','RA'),('Mérida','YV'),('Mersin','TR'),('Meta','CO'),('Metro Manila','RP'),('México, Estado de','MEX'),('Michigan','USA'),('Michoacán','MEX'),('Micronesia','FSM'),('Midi-Pyrénées','F'),('Midtjylland','DK'),('Mie','J'),('Mikkeli','SF'),('Mimaropa','RP'),('Minas Gerais','BR'),('Minnesota','USA'),('Minsk','BY'),('Minsk City','BY'),('Miranda','YV'),('Misiones','RA'),('Mississippi','USA'),('Missouri','USA'),('Miyagi','J'),('Miyazaki','J'),('Mizoram','IND'),('Mjini Magharibi Unguja','EAT'),('Mogilev','BY'),('Moldova','MD'),('Molise','I'),('Mon','MYA'),('Monaco','MC'),('Monagas','YV'),('Mongolia','MNG'),('Montagnes','CI'),('Montana','USA'),('Montenegro','MNE'),('Montserrat','MNTS'),('Mopti','RMM'),('Moquegua','PE'),('Moravskoslezský','CZ'),('Mordoviya','R'),('Morelos','MEX'),('Morogoro','EAT'),('Moscow','R'),('Moskovskaya','R'),('Moxico','ANG'),('Moyen-Cavally','CI'),('Moyen-Comoé','CI'),('Mpumalanga','RSA'),('Mtwara','EAT'),('Mu?','TR'),('Mu?la','TR'),('Murcia','E'),('Mure?','RO'),('Murmanskaya','R'),('Muslim Mindanao','RP'),('Mwanza','EAT'),('Mykolaïvs´ka','UA'),('Møre og Romsdal','N'),('N\'zi-Comoé','CI'),('Nagaland','IND'),('Nagano','J'),('Nagasaki','J'),('Najran','SA'),('Namangan','UZB'),('Namibe','ANG'),('Namibia','NAM'),('Nampula','MOC'),('Namur','B'),('Nara','J'),('Nariño','CO'),('Nasarawa','WAN'),('Nauru','NAU'),('Navarra','E'),('Navoiy','UZB'),('Nay Pyi Taw','MYA'),('Nayarit','MEX'),('Neam?','RO'),('Nebraska','USA'),('Negeri Sembilan','MAL'),('Nei Mongol','CN'),('Nepal','NEP'),('Neuchâtel','CH'),('Neuquén','RA'),('Nev?ehir','TR'),('Nevada','USA'),('New Brunswick','CDN'),('New Caledonia','NCA'),('New Hampshire','USA'),('New Jersey','USA'),('New Mexico','USA'),('New South Wales','AUS'),('New York','USA'),('New Zealand','NZ'),('Newfoundland and Labrador','CDN'),('Ngöbe Buglé','PA'),('Ni?de','TR'),('Niamey','RN'),('Niari','RCB'),('Niassa','MOC'),('Nicaragua','NIC'),('Nidwalden','CH'),('Niedersachsen','D'),('Niederösterreich','A'),('Niger','WAN'),('Niigata','J'),('Nineveh','IRQ'),('Ningxia','CN'),('Nitriansky','SK'),('Niue','NIUE'),('Nizhnii Novgorod','R'),('Njombe','EAT'),('Nógrád','H'),('Noord-Brabant','NL'),('Noord-Holland','NL'),('Nord','CAM'),('Nord-Kivu','ZRE'),('Nord-Ouest','CAM'),('Nord-Pas-de-Calais','F'),('Nord-Trøndelag','N'),('Nordjylland','DK'),('Nordland','N'),('Nordrhein-Westfalen','D'),('Norfolk Island','NORF'),('Norrbotten','S'),('Norte de Santander','CO'),('North Carolina','USA'),('North Dakota','USA'),('North East','GB'),('North Khorasan','IR'),('North Korea','NOK'),('North Ossetia-Alania','R'),('North West','GB'),('North West','RSA'),('North-Kazakhstan','KAZ'),('North-Western','Z'),('Northern','GH'),('Northern','Z'),('Northern Cape','RSA'),('Northern Ireland','GB'),('Northern Mariana Islands','NMIS'),('Northern Mindanao','RP'),('Northern Territory','AUS'),('Northwest Territories','CDN'),('Notioy Aigaioy','GR'),('Nouakchott','RIM'),('Nova Scotia','CDN'),('Novgorodskaya','R'),('Novosibirsk','R'),('Nueva Esparta','YV'),('Nuevo Léon','MEX'),('Nunavut','CDN'),('Nusa Tenggara Barat','RI'),('Nusa Tenggara Timur','RI'),('Oaxaca','MEX'),('Oberösterreich','A'),('Obwalden','CH'),('Ocotepeque','HCA'),('Odes´ka','UA'),('Odisha','IND'),('Ogun','WAN'),('Ohio','USA'),('Oita','J'),('Okayama','J'),('Okinawa','J'),('Oklahoma','USA'),('Olancho','HCA'),('Olomoucký','CZ'),('Olt','RO'),('Oman','OM'),('Omsk','R'),('Ondo','WAN'),('Ontario','CDN'),('Oost-Vlaanderen','B'),('Opolskie','PL'),('Oppland','N'),('Ordu','TR'),('Oregon','USA'),('Orenburg','R'),('Oriental','MA'),('Orientale','ZRE'),('Orlovskaya','R'),('Oromia','ETH'),('Oruro','BOL'),('Osaka','J'),('Oslo','N'),('Osmaniye','TR'),('Osun','WAN'),('Ouest','CAM'),('Oulu','SF'),('Overijssel','NL'),('Oyo','WAN'),('Pahang','MAL'),('País Vasco','E'),('Palau','PAL'),('Panamá','PA'),('Pando','BOL'),('Papua','RI'),('Papua Barat','RI'),('Papua New Guinea','PNG'),('Pará','BR'),('Paraguay','PY'),('Paraíba','BR'),('Paraná','BR'),('Pardubický','CZ'),('Pasco','PE'),('Pavlodar','KAZ'),('Pays de la Loire','F'),('Peloponnisos','GR'),('Pennsylvania','USA'),('Penza','R'),('Perak','MAL'),('Perlis','MAL'),('Perm','R'),('Pernambuco','BR'),('Pest','H'),('Piauí','BR'),('Picardie','F'),('Piemonte','I'),('Pinar del Río','C'),('Pitcairn','PITC'),('Piura','PE'),('Plateau','WAN'),('Plateaux','RCB'),('Plze?ský','CZ'),('Podkarpackie','PL'),('Podlaskie','PL'),('Pohjols-Karjala','SF'),('Pointe-Noire','RCB'),('Poitou Charentes','F'),('Poltavs´ka','UA'),('Pomorskie','PL'),('Pool','RCB'),('Portalegre','P'),('Porto','P'),('Portuguesa','YV'),('Potosí','BOL'),('Praha','CZ'),('Prahova','RO'),('Prešovský','SK'),('Primorskiy','R'),('Prince Edward Island','CDN'),('Provence-Alpes-Côte d\'Azur','F'),('Pskovskaya','R'),('Puducherry','IND'),('Puebla','MEX'),('Puerto Rico','PR'),('Puglia','I'),('Pulau Pinang','MAL'),('Punjab','IND'),('Punjab','PK'),('Puno','PE'),('Puntarenas','CR'),('Putrajaya','MAL'),('Putumayo','CO'),('Pwani','EAT'),('Qasqadaryo','UZB'),('Qatar','Q'),('Qazvin','IR'),('Qinghai','CN'),('Qom','IR'),('Qoraqalpog?iston','UZB'),('Quebec','CDN'),('Queensland','AUS'),('Querétaro','MEX'),('Quindío','CO'),('Quintana Roo','MEX'),('Rabat Sale Zemmour Zaer','MA'),('Rajasthan','IND'),('Rajshahi','BD'),('Rakhine','MYA'),('Rangpur','BD'),('Republika Srpska','BIH'),('Reunion','REUN'),('Rheinland-Pfalz','D'),('Rhode Island','USA'),('Rhône-Alpes','F'),('Riau','RI'),('Rif Dimashq','SYR'),('Rio de Janeiro','BR'),('Rio Grande do Norte','BR'),('Rio Grande do Sul','BR'),('Río Negro','RA'),('Ríos','RCH'),('Risaralda','CO'),('Rivers','WAN'),('Rivnens´ka','UA'),('Rize','TR'),('Rogaland','N'),('Rondônia','BR'),('Roraima','BR'),('Rostovskaya','R'),('Rukwa','EAT'),('Ruvuma','EAT'),('Rwanda','RWA'),('Ryazanskaya','R'),('S?laj','RO'),('Saarland','D'),('Sabah','MAL'),('Sachsen','D'),('Sachsen-Anhalt','D'),('Saga','J'),('Sagaing','MYA'),('Saint Barthelemy','SBAR'),('Saint Helena','HELX'),('Saint Kitts and Nevis','KN'),('Saint Lucia','WL'),('Saint Martin','SMAR'),('Saint Pierre and Miquelon','SPMI'),('Saint Vincent and the Grenadines','WV'),('Saint-Louis','SN'),('Saitama','J'),('Sakarya','TR'),('Sakha','R'),('Sakhalin','R'),('Salah al-Deen','IRQ'),('Salta','RA'),('Salzburg','A'),('Samara','R'),('Samarqand','UZB'),('Samoa','WS'),('Samsun','TR'),('San Andrés, Providencia y Santa Catalina','CO'),('San José','CR'),('San Juan','RA'),('San Luis','RA'),('San Luis Potosí','MEX'),('San Marino','RSM'),('San Martín','PE'),('Sancti Spíritus','C'),('Sangha','RCB'),('Sankt Gallen','CH'),('Sankt-Peterburg','R'),('Santa Bárbara','HCA'),('Santa Catarina','BR'),('Santa Cruz','BOL'),('Santa Cruz','RA'),('Santa Fe','RA'),('Santa Fe de Bogotá','CO'),('Santander','CO'),('Santarem','P'),('Santiago','RCH'),('Santiago de Cuba','C'),('Santiago de Estero','RA'),('São Paulo','BR'),('Sao Tome and Principe','STP'),('Saratov','R'),('Sarawak','MAL'),('Sardegna','I'),('Saskatchewan','CDN'),('Satu-Mare','RO'),('Savanes','CI'),('Schaffhausen','CH'),('Schleswig-Holstein','D'),('Schwyz','CH'),('Scotland','GB'),('Sédhiou','SN'),('Ségou','RMM'),('Selangor','MAL'),('Semnan','IR'),('Serbia','SRB'),('Sergipe','BR'),('Setubal','P'),('Sevastopol´','UA'),('Seychelles','SY'),('Shaanxi','CN'),('Shan','MYA'),('Shandong','CN'),('Shanghai','CN'),('Shanxi','CN'),('Shiga','J'),('Shimane','J'),('Shinyanga','EAT'),('Shizuoka','J'),('Sibiu','RO'),('Sichuan','CN'),('Sicilia','I'),('Sierra Leone','WAL'),('Siirt','TR'),('Sikasso','RMM'),('Sikkim','IND'),('Simiyu','EAT'),('Sinaloa','MEX'),('Sindh','PK'),('Singapore','SGP'),('Singida','EAT'),('Sinop','TR'),('Sint Maarten','NLSM'),('Sirdaryo','UZB'),('Sistan and Baluchestan','IR'),('Sivas','TR'),('Sjælland','DK'),('Skåne','S'),('Slovenia','SLO'),('Smolenskaya','R'),('Soccsksargen','RP'),('Sofala','MOC'),('Sogn og Fjordane','N'),('Sokoto','WAN'),('Solomon Islands','SLB'),('Solothurn','CH'),('Somali','ETH'),('Somalia','SP'),('Somogy','H'),('Sonora','MEX'),('Souss Massa Daraa','MA'),('South Australia','AUS'),('South Carolina','USA'),('South Dakota','USA'),('South East','GB'),('South Khorasan','IR'),('South Korea','ROK'),('South Sudan','SSD'),('South West','GB'),('South-Kazakhstan','KAZ'),('Southern','Z'),('Sri Lanka','CL'),('St?edo?eský','CZ'),('Stavropol´skiy','R'),('Steiermark','A'),('Stereas Elladas','GR'),('Stockholm','S'),('Suceava','RO'),('Sucre','CO'),('Sucre','YV'),('Sud','CAM'),('Sud-Bandama','CI'),('Sud-Comoé','CI'),('Sud-Kivu','ZRE'),('Sud-Ouest','CAM'),('Sudan','SUD'),('Sughd','TAD'),('Sulawesi Barat','RI'),('Sulawesi Selatan','RI'),('Sulawesi Tengah','RI'),('Sulawesi Tenggara','RI'),('Sulawesi Utara','RI'),('Sumatera Barat','RI'),('Sumatera Selatan','RI'),('Sumatera Utara','RI'),('Sums´ka','UA'),('Suomi','SF'),('Suriname','SME'),('Surxondaryo','UZB'),('Svalbard','SVAX'),('Sverdlov','R'),('Swaziland','SD'),('Syddanmark','DK'),('Sylhet','BD'),('Szabolcs-Szatmár-Bereg','H'),('Södermanland','S'),('Sør-Trøndelag','N'),('Tabasco','MEX'),('Tabora','EAT'),('Tabouk','SA'),('Táchira','YV'),('Tacna','PE'),('Tadla Azilal','MA'),('Tagant','RIM'),('Tahoua','RN'),('Taiwan','RC'),('Tamaulipas','MEX'),('Tambacounda','SN'),('Tambovskaya','R'),('Tamil Nadu','IND'),('Tanga','EAT'),('Tanger Tetouan','MA'),('Tanintharyi','MYA'),('Taraba','WAN'),('Tarapacá','RCH'),('Tarija','BOL'),('Tartus','SYR'),('Tasmania','AUS'),('Tatarstan','R'),('Taza Al Hoceima Taounate','MA'),('Tehran','IR'),('Tekirda?','TR'),('Tel Aviv','IL'),('Telangana','IND'),('Telemark','N'),('Teleorman','RO'),('Tennessee','USA'),('Terengganu','MAL'),('Ternopil´s´ka','UA'),('Tete','MOC'),('Texas','USA'),('Thailand','THA'),('Thessalias','GR'),('Thi Qar','IRQ'),('Thiès','SN'),('Thurgau','CH'),('Thüringen','D'),('Tianjin','CN'),('Ticino','CH'),('Tierra del Fuego','RA'),('Tigray','ETH'),('Tillabéri','RN'),('Timi?','RO'),('Timor-Leste','TL'),('Tiris Zemmour','RIM'),('Tirol','A'),('Tlaxcala','MEX'),('Tocantins','BR'),('Tochigi','J'),('Togo','RT'),('Tokat','TR'),('Tokelau','TOK'),('Tokushima','J'),('Tokyo','J'),('Tolima','CO'),('Tolna','H'),('Tombouctou','RMM'),('Tomsk','R'),('Tonga','TO'),('Toscana','I'),('Toshkent','UZB'),('Toshkent City','UZB'),('Tottori','J'),('Toyama','J'),('Trabzon','TR'),('Trarza','RIM'),('Tren?iansky','SK'),('Trentino-Alto Adige','I'),('Trinidad and Tobago','TT'),('Tripura','IND'),('Trnavský','SK'),('Troms','N'),('Trujillo','YV'),('Tucumán','RA'),('Tulcea','RO'),('Tul´skaya','R'),('Tumbes','PE'),('Tunceli','TR'),('Tunisia','TN'),('Turks and Caicos Islands','TUCA'),('Turku-Pori','SF'),('Tuvalu','TUV'),('Tverskaya','R'),('Tyumen','R'),('Tyva','R'),('U?ak','TR'),('Ucayali','PE'),('Udmurt','R'),('Uganda','EAU'),('Uíge','ANG'),('Ul´yanovsk','R'),('Umbria','I'),('United Arab Emirates','UAE'),('Upper East','GH'),('Upper West','GH'),('Uppsala','S'),('Uri','CH'),('Uruguay','ROU'),('Ústecký','CZ'),('Utah','USA'),('Utrecht','NL'),('Uttar Pradesh','IND'),('Uttarakhand','IND'),('Uusimaa','SF'),('Vaasa','SF'),('Valais','CH'),('Vâlcea','RO'),('Valenciana','E'),('Valle','HCA'),('Valle d\'Aosta','I'),('Valle de Cauca','CO'),('Vallée du Bandama','CI'),('Valparaíso','RCH'),('Van','TR'),('Vanuatu','VU'),('Vargas','YV'),('Vas','H'),('Vaslui','RO'),('Vaud','CH'),('Vaupés','CO'),('Veneto','I'),('Veracruz','MEX'),('Veraguas','PA'),('Vermont','USA'),('Vest-Agder','N'),('Vestfold','N'),('Veszprém','H'),('Viana do Castelo','P'),('Vichada','CO'),('Victoria','AUS'),('Vietnam','VN'),('Vila Real','P'),('Villa Clara','C'),('Vinnyts´ka','UA'),('Virgin Islands','VIRG'),('Virginia','USA'),('Viseu','P'),('Vitebsk','BY'),('Vlaams-Brabant','B'),('Vladimirskaya','R'),('Volgogradskaya','R'),('Vologodskaya','R'),('Volta','GH'),('Volyns´ka','UA'),('Vorarlberg','A'),('Voronezhskaya','R'),('Vrancea','RO'),('Vyso?ina','CZ'),('Värmland','S'),('Västerbotten','S'),('Västernorrland','S'),('Västmanland','S'),('Västra Götaland','S'),('Wakayama','J'),('Wales','GB'),('Wallis and Futuna','WAFU'),('Warmi?sko-Mazurskie','PL'),('Washington','USA'),('Wasit','IRQ'),('West Azarbayejan','IR'),('West Bank','WEST'),('West Bengal','IND'),('West Kazakhstan','KAZ'),('West Midlands','GB'),('West Virginia','USA'),('West-Vlaanderen','B'),('Western','GH'),('Western','Z'),('Western Australia','AUS'),('Western Cape','RSA'),('Western Sahara','WSA'),('Western Visayas','RP'),('Wielkopolskie','PL'),('Wien','A'),('Wisconsin','USA'),('Worodougou','CI'),('Wyoming','USA'),('Xinjiang','CN'),('Xizang','CN'),('Xorazm','UZB'),('Yalova','TR'),('Yamagata','J'),('Yamaguchi','J'),('Yamalo Nenets ao','R'),('Yamanashi','J'),('Yangon','MYA'),('Yaracuy','YV'),('Yaroslavskaya','R'),('Yazd','IR'),('YeDebub Biheroch Bihereseboch na Hizboch','ETH'),('Yemen','YE'),('Yerushalayim','IL'),('Yobe','WAN'),('Yorkshire and the Humber','GB'),('Yoro','HCA'),('Yozgat','TR'),('Yucatán','MEX'),('Yukon','CDN'),('Yunnan','CN'),('Zabaykalskiy','R'),('Zacatecas','MEX'),('Zachodniopomorskie','PL'),('Zaire','ANG'),('Zakarpats´ka','UA'),('Zala','H'),('Zambézia','MOC'),('Zamboanga Peninsula','RP'),('Zamfara','WAN'),('Zanjan','IR'),('Zanzan','CI'),('Zaporiz´ka','UA'),('Zeeland','NL'),('Zhambyl','KAZ'),('Zhejiang','CN'),('Zhytomyrs´ka','UA'),('Ziguinchor','SN'),('Zimbabwe','ZW'),('Zinder','RN'),('Zlínský','CZ'),('Zonguldak','TR'),('Zug','CH'),('Zuid-Holland','NL'),('Zulia','YV'),('Zürich','CH'),('Örebro','S'),('Östergötland','S'),('Žilinský','SK'),('Østfold','N');
/*!40000 ALTER TABLE `REGION` ENABLE KEYS */;
UNLOCK TABLES;

