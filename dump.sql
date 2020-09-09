CREATE DATABASE  IF NOT EXISTS `MIS` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `MIS`;
-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: MIS
-- ------------------------------------------------------
-- Server version	5.7.31-0ubuntu0.18.04.1

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
-- Table structure for table `ADDRESSES`
--

DROP TABLE IF EXISTS `ADDRESSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ADDRESSES` (
  `ZipCode` varchar(11) NOT NULL,
  `CodAddress` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `HouseNumber` int(11) NOT NULL,
  PRIMARY KEY (`ZipCode`,`CodAddress`),
  CONSTRAINT `FKComprise` FOREIGN KEY (`ZipCode`) REFERENCES `CITIES` (`ZipCode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ADDRESSES`
--

LOCK TABLES `ADDRESSES` WRITE;
/*!40000 ALTER TABLE `ADDRESSES` DISABLE KEYS */;
INSERT INTO `ADDRESSES` VALUES ('00010',4,'Via Portunese',5),('20010',2,'Via G.Rossini',15),('40010',8,'Via P.Rossi',40),('47814',3,'Via Pegaso',10),('55011',6,'Via G.Gialli',1),('56010',7,'Via Giovanni Pascoli',3),('60010',9,'Via Verdi',23),('60019',1,'Via Capanna',20),('84010',5,'Via Bianchi',21);
/*!40000 ALTER TABLE `ADDRESSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `CITIES`
--

DROP TABLE IF EXISTS `CITIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `CITIES` (
  `ZipCode` varchar(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `CodState` int(11) NOT NULL,
  PRIMARY KEY (`ZipCode`),
  KEY `FKInclude` (`CodState`),
  CONSTRAINT `FKInclude` FOREIGN KEY (`CodState`) REFERENCES `STATES` (`CodState`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `CITIES`
--

LOCK TABLES `CITIES` WRITE;
/*!40000 ALTER TABLE `CITIES` DISABLE KEYS */;
INSERT INTO `CITIES` VALUES ('00010','Roma',1),('10115','Berlin',2),('13000','Marseille',3),('20010','Milano',1),('28001','Madrid',5),('30072','Bordeaux',3),('40010','Bologna',1),('41001','Seville',5),('47814','Rimini',1),('55011','Torino',1),('56010','Pisa',1),('60010','Ancona',1),('60019','Senigallia',1),('69000','Lyon',3),('75000','Paris',3),('80331','Munich',2),('84010','Salerno',1),('BN 1','Brighton',4),('E1 6AN','London',4),('M1','Manchester',4);
/*!40000 ALTER TABLE `CITIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DIAGNOSES`
--

DROP TABLE IF EXISTS `DIAGNOSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `DIAGNOSES` (
  `SerialNO` varchar(50) NOT NULL,
  `Causes` varchar(50) NOT NULL,
  `Symptoms` varchar(50) NOT NULL,
  PRIMARY KEY (`SerialNO`,`Causes`,`Symptoms`),
  CONSTRAINT `FKReport` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DIAGNOSES`
--

LOCK TABLES `DIAGNOSES` WRITE;
/*!40000 ALTER TABLE `DIAGNOSES` DISABLE KEYS */;
/*!40000 ALTER TABLE `DIAGNOSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EMPLOYEES`
--

DROP TABLE IF EXISTS `EMPLOYEES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMPLOYEES` (
  `CF` varchar(16) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Surname` varchar(25) NOT NULL,
  `BirthDate` date NOT NULL,
  `IBAN` varchar(27) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `PhoneNumber` varchar(15) NOT NULL,
  `Type` varchar(15) NOT NULL,
  `CodFunction` int(11) NOT NULL,
  PRIMARY KEY (`CF`),
  KEY `FKRole` (`CodFunction`),
  CONSTRAINT `FKRole` FOREIGN KEY (`CodFunction`) REFERENCES `FUNCTIONS` (`CodFunction`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EMPLOYEES`
--

LOCK TABLES `EMPLOYEES` WRITE;
/*!40000 ALTER TABLE `EMPLOYEES` DISABLE KEYS */;
INSERT INTO `EMPLOYEES` VALUES ('FBRLCU99R19I608E','Luca','Fabri','1999-02-02','00062381711839273','lf@gmail.com','098361531','external',2);
/*!40000 ALTER TABLE `EMPLOYEES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `EXECUTIONS`
--

DROP TABLE IF EXISTS `EXECUTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EXECUTIONS` (
  `E_W_SerialNO` varchar(50) NOT NULL,
  `E_W_NumWorkOrder` int(11) NOT NULL,
  `SerialNO` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`E_W_SerialNO`,`E_W_NumWorkOrder`,`SerialNO`,`Description`),
  KEY `FKExe_WOR` (`SerialNO`,`Description`),
  CONSTRAINT `FKExe_WOR` FOREIGN KEY (`SerialNO`, `Description`) REFERENCES `WORK_DESCRIPTIONS` (`SerialNO`, `Description`) ON DELETE CASCADE,
  CONSTRAINT `FKExe_WOR_1` FOREIGN KEY (`E_W_SerialNO`, `E_W_NumWorkOrder`) REFERENCES `WORK_ORDERS` (`SerialNO`, `NumWorkOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `EXECUTIONS`
--

LOCK TABLES `EXECUTIONS` WRITE;
/*!40000 ALTER TABLE `EXECUTIONS` DISABLE KEYS */;
INSERT INTO `EXECUTIONS` VALUES ('GHQUWU7261K',1,'GHQUWU7261K','work description demo'),('GHQUWU7261K',3,'GHQUWU7261K','work description demo'),('P3FNU71983',3,'P3FNU71983','cambio pezzo x'),('P3FNU71983',3,'P3FNU71983','cambio pezzo y'),('P3FNU71983',3,'P3FNU71983','cambio pezzo z');
/*!40000 ALTER TABLE `EXECUTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FAMILIES`
--

DROP TABLE IF EXISTS `FAMILIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FAMILIES` (
  `CodFamily` int(11) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`CodFamily`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FAMILIES`
--

LOCK TABLES `FAMILIES` WRITE;
/*!40000 ALTER TABLE `FAMILIES` DISABLE KEYS */;
INSERT INTO `FAMILIES` VALUES (1,'Plastic product manufacturing'),(2,'Automotive manufacturing'),(3,'Refineries'),(4,'Food production'),(5,'Steel mills'),(6,'Apparel production');
/*!40000 ALTER TABLE `FAMILIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `FUNCTIONS`
--

DROP TABLE IF EXISTS `FUNCTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FUNCTIONS` (
  `CodFunction` int(11) NOT NULL,
  `Function` varchar(25) NOT NULL,
  PRIMARY KEY (`CodFunction`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `FUNCTIONS`
--

LOCK TABLES `FUNCTIONS` WRITE;
/*!40000 ALTER TABLE `FUNCTIONS` DISABLE KEYS */;
INSERT INTO `FUNCTIONS` VALUES (1,'Manager'),(2,'Technician'),(3,'Apprenctice'),(4,'Mechanic'),(5,'Electrician'),(6,'Supervisor'),(7,'Other');
/*!40000 ALTER TABLE `FUNCTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ITEMS`
--

DROP TABLE IF EXISTS `ITEMS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ITEMS` (
  `SerialNO` varchar(50) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Picture` varchar(100) NOT NULL,
  `File` varchar(100) NOT NULL,
  `Doc` varchar(200) NOT NULL,
  `Specifications` varchar(200) NOT NULL,
  `ADR` float NOT NULL,
  `Model` varchar(25) NOT NULL,
  `Maker` varchar(25) NOT NULL,
  `Dimension` varchar(10) NOT NULL,
  `Year` varchar(4) NOT NULL,
  `CodFamily` int(11) NOT NULL,
  `CodSubfamily` int(11) NOT NULL,
  `CodUnit` int(11) NOT NULL,
  `ZipCode` varchar(11) NOT NULL,
  `CodAddress` int(11) NOT NULL,
  PRIMARY KEY (`SerialNO`),
  KEY `FKPlaced` (`CodFamily`,`CodSubfamily`),
  KEY `FKOperate` (`CodUnit`),
  KEY `FKADD_ITE` (`ZipCode`,`CodAddress`),
  CONSTRAINT `FKADD_ITE` FOREIGN KEY (`ZipCode`, `CodAddress`) REFERENCES `ADDRESSES` (`ZipCode`, `CodAddress`),
  CONSTRAINT `FKOperate` FOREIGN KEY (`CodUnit`) REFERENCES `UNITS` (`CodUnit`),
  CONSTRAINT `FKPlaced` FOREIGN KEY (`CodFamily`, `CodSubfamily`) REFERENCES `SUBFAMILIES` (`CodFamily`, `CodSubfamily`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ITEMS`
--

LOCK TABLES `ITEMS` WRITE;
/*!40000 ALTER TABLE `ITEMS` DISABLE KEYS */;
INSERT INTO `ITEMS` VALUES ('GHQUWU7261K','injection mold 1','/home/luca/eclipse-workspace/DB/src/injection-mold.jpg','/home/luca/eclipse-workspace/DB/src/injection-mold.jpg','description demo','specifications demo',8,'demo model','demo maker','18x18','2015',1,1,2,'00010',4),('P3FNU71983','plastic granulator 1','/home/luca/eclipse-workspace/DB/src/plastic-granulator.jpg','/home/luca/eclipse-workspace/DB/src/plastic-granulator.jpg','descriptions','specifications',9,'demo','demo','20x20','2018',1,3,2,'20010',2);
/*!40000 ALTER TABLE `ITEMS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAINTENANCE_SCHEDULES`
--

DROP TABLE IF EXISTS `MAINTENANCE_SCHEDULES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAINTENANCE_SCHEDULES` (
  `SerialNO` varchar(50) NOT NULL,
  `NumMaintenanceSchedule` int(11) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Tasks` varchar(100) NOT NULL,
  `ByTime` varchar(50) DEFAULT NULL,
  `ByUsage` varchar(50) DEFAULT NULL,
  `ByPrediction` varchar(50) DEFAULT NULL,
  `ByCondition` varchar(50) DEFAULT NULL,
  `ByRunToFail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`SerialNO`,`NumMaintenanceSchedule`),
  CONSTRAINT `FKProgram` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAINTENANCE_SCHEDULES`
--

LOCK TABLES `MAINTENANCE_SCHEDULES` WRITE;
/*!40000 ALTER TABLE `MAINTENANCE_SCHEDULES` DISABLE KEYS */;
INSERT INTO `MAINTENANCE_SCHEDULES` VALUES ('GHQUWU7261K',1,'maintenance schedule description','tasks','every moth',NULL,NULL,NULL,NULL),('P3FNU71983',1,'monthly maintenance','smontaggio e rimontaggio di due pezzi usurati','every month',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `MAINTENANCE_SCHEDULES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `MAN_HOURS`
--

DROP TABLE IF EXISTS `MAN_HOURS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `MAN_HOURS` (
  `SerialNO` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `CF` varchar(16) NOT NULL,
  `Hours` int(11) NOT NULL,
  `CostPerHour` float NOT NULL,
  PRIMARY KEY (`SerialNO`,`Description`,`CF`),
  KEY `FKMAN_EMP` (`CF`),
  CONSTRAINT `FKMAN_EMP` FOREIGN KEY (`CF`) REFERENCES `EMPLOYEES` (`CF`) ON DELETE CASCADE,
  CONSTRAINT `FKMAN_WOR` FOREIGN KEY (`SerialNO`, `Description`) REFERENCES `WORK_DESCRIPTIONS` (`SerialNO`, `Description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `MAN_HOURS`
--

LOCK TABLES `MAN_HOURS` WRITE;
/*!40000 ALTER TABLE `MAN_HOURS` DISABLE KEYS */;
INSERT INTO `MAN_HOURS` VALUES ('GHQUWU7261K','work description demo','FBRLCU99R19I608E',3,15),('P3FNU71983','cambio pezzo x','FBRLCU99R19I608E',3,15),('P3FNU71983','cambio pezzo y','FBRLCU99R19I608E',2,12.5),('P3FNU71983','cambio pezzo z','FBRLCU99R19I608E',1,10);
/*!40000 ALTER TABLE `MAN_HOURS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PLANS`
--

DROP TABLE IF EXISTS `PLANS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PLANS` (
  `P_M_SerialNO` varchar(50) NOT NULL,
  `P_M_NumMaintenanceSchedule` int(11) NOT NULL,
  `SerialNO` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`P_M_SerialNO`,`P_M_NumMaintenanceSchedule`,`SerialNO`,`Description`),
  KEY `FKPla_WOR` (`SerialNO`,`Description`),
  CONSTRAINT `FKPla_MAI` FOREIGN KEY (`P_M_SerialNO`, `P_M_NumMaintenanceSchedule`) REFERENCES `MAINTENANCE_SCHEDULES` (`SerialNO`, `NumMaintenanceSchedule`) ON DELETE CASCADE,
  CONSTRAINT `FKPla_WOR` FOREIGN KEY (`SerialNO`, `Description`) REFERENCES `WORK_DESCRIPTIONS` (`SerialNO`, `Description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PLANS`
--

LOCK TABLES `PLANS` WRITE;
/*!40000 ALTER TABLE `PLANS` DISABLE KEYS */;
INSERT INTO `PLANS` VALUES ('GHQUWU7261K',1,'GHQUWU7261K','work description demo'),('P3FNU71983',1,'P3FNU71983','cambio pezzo x'),('P3FNU71983',1,'P3FNU71983','cambio pezzo y');
/*!40000 ALTER TABLE `PLANS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PROJECT_SCHEDULES`
--

DROP TABLE IF EXISTS `PROJECT_SCHEDULES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PROJECT_SCHEDULES` (
  `CodProject` int(11) NOT NULL,
  `Description` varchar(25) NOT NULL,
  `Tasks` varchar(100) NOT NULL,
  `ByTime` varchar(50) DEFAULT NULL,
  `ByUsage` varchar(50) DEFAULT NULL,
  `ByPrediction` varchar(50) DEFAULT NULL,
  `ByCondition` varchar(50) DEFAULT NULL,
  `ByRunToFail` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`CodProject`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PROJECT_SCHEDULES`
--

LOCK TABLES `PROJECT_SCHEDULES` WRITE;
/*!40000 ALTER TABLE `PROJECT_SCHEDULES` DISABLE KEYS */;
INSERT INTO `PROJECT_SCHEDULES` VALUES (12,'project 1','tasks','every year',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `PROJECT_SCHEDULES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `PURCHASES`
--

DROP TABLE IF EXISTS `PURCHASES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `PURCHASES` (
  `CodSupplier` int(11) NOT NULL,
  `BillNumber` int(11) NOT NULL,
  `PurchaseDate` date NOT NULL,
  PRIMARY KEY (`CodSupplier`,`BillNumber`),
  CONSTRAINT `FKContract` FOREIGN KEY (`CodSupplier`) REFERENCES `SUPPLIERS` (`CodSupplier`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `PURCHASES`
--

LOCK TABLES `PURCHASES` WRITE;
/*!40000 ALTER TABLE `PURCHASES` DISABLE KEYS */;
INSERT INTO `PURCHASES` VALUES (1,1,'2020-09-08'),(1,2,'2020-09-09'),(2,1,'2020-09-05'),(2,3,'2020-09-09');
/*!40000 ALTER TABLE `PURCHASES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SALES`
--

DROP TABLE IF EXISTS `SALES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SALES` (
  `SerialNO` varchar(50) NOT NULL,
  `NumWarranty` varchar(50) DEFAULT NULL,
  `WarrantyDate` date DEFAULT NULL,
  `WarrantyRecord` float DEFAULT NULL,
  `WarrantyNotes` varchar(50) DEFAULT NULL,
  `WarrantyFile` varchar(50) DEFAULT NULL,
  `ContractDateConclusion` date DEFAULT NULL,
  `ContractExpiration` date DEFAULT NULL,
  `CodSupplier` int(11) NOT NULL,
  PRIMARY KEY (`SerialNO`),
  KEY `FKSal_SUP` (`CodSupplier`),
  CONSTRAINT `FKSal_ITE_FK` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`) ON DELETE CASCADE,
  CONSTRAINT `FKSal_SUP` FOREIGN KEY (`CodSupplier`) REFERENCES `SUPPLIERS` (`CodSupplier`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SALES`
--

LOCK TABLES `SALES` WRITE;
/*!40000 ALTER TABLE `SALES` DISABLE KEYS */;
INSERT INTO `SALES` VALUES ('GHQUWU7261K','1','2020-10-10',120000,'notes demo','',NULL,NULL,1),('P3FNU71983',NULL,NULL,NULL,NULL,'',NULL,NULL,2);
/*!40000 ALTER TABLE `SALES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPARE_PARTS`
--

DROP TABLE IF EXISTS `SPARE_PARTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SPARE_PARTS` (
  `SerialNO` varchar(50) NOT NULL,
  `NumSparePart` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Specifications` varchar(200) NOT NULL,
  `CodUnit` int(11) NOT NULL,
  PRIMARY KEY (`SerialNO`,`NumSparePart`),
  KEY `FKMeasure` (`CodUnit`),
  CONSTRAINT `FKMeasure` FOREIGN KEY (`CodUnit`) REFERENCES `UNITS` (`CodUnit`) ON DELETE CASCADE,
  CONSTRAINT `FKNeed` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPARE_PARTS`
--

LOCK TABLES `SPARE_PARTS` WRITE;
/*!40000 ALTER TABLE `SPARE_PARTS` DISABLE KEYS */;
INSERT INTO `SPARE_PARTS` VALUES ('GHQUWU7261K',1,'ricambio 1','description 2 demo','specifications demo 1',2),('GHQUWU7261K',2,'ricambio 2','description 2 demo','specifications 2 demo',2),('P3FNU71983',1,'pezzo x','description 3 demo','specifications 4 demo',2),('P3FNU71983',2,'pezzo y','description 4 demo','specifications 4 demo',2),('P3FNU71983',3,'pezzo z','description 5 demo','specifications 5 demo',2);
/*!40000 ALTER TABLE `SPARE_PARTS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SPRING`
--

DROP TABLE IF EXISTS `SPRING`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SPRING` (
  `S_W_SerialNO` varchar(50) NOT NULL,
  `S_W_NumWorkOrder` int(11) NOT NULL,
  `Picture` varchar(50) NOT NULL,
  `SerialNO` varchar(50) NOT NULL,
  `Causes` varchar(50) NOT NULL,
  `Symptoms` varchar(50) NOT NULL,
  PRIMARY KEY (`S_W_SerialNO`,`S_W_NumWorkOrder`),
  KEY `FKSpr_DIA` (`SerialNO`,`Causes`,`Symptoms`),
  CONSTRAINT `FKSpr_DIA` FOREIGN KEY (`SerialNO`, `Causes`, `Symptoms`) REFERENCES `DIAGNOSES` (`SerialNO`, `Causes`, `Symptoms`),
  CONSTRAINT `FKSpr_WOR_FK` FOREIGN KEY (`S_W_SerialNO`, `S_W_NumWorkOrder`) REFERENCES `WORK_ORDERS` (`SerialNO`, `NumWorkOrder`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SPRING`
--

LOCK TABLES `SPRING` WRITE;
/*!40000 ALTER TABLE `SPRING` DISABLE KEYS */;
/*!40000 ALTER TABLE `SPRING` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STATES`
--

DROP TABLE IF EXISTS `STATES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STATES` (
  `CodState` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  PRIMARY KEY (`CodState`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STATES`
--

LOCK TABLES `STATES` WRITE;
/*!40000 ALTER TABLE `STATES` DISABLE KEYS */;
INSERT INTO `STATES` VALUES (1,'Italy'),(2,'Germany'),(3,'France'),(4,'UK'),(5,'Spain');
/*!40000 ALTER TABLE `STATES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `STOCKS`
--

DROP TABLE IF EXISTS `STOCKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `STOCKS` (
  `SerialNO` varchar(50) NOT NULL,
  `NumSparePart` int(11) NOT NULL,
  `CodSupplier` int(11) NOT NULL,
  `BillNumber` int(11) NOT NULL,
  `Amount` int(11) NOT NULL,
  `UnitCost` float NOT NULL,
  `Picture` varchar(50) NOT NULL,
  `Notes` varchar(100) NOT NULL,
  `CodWarehouse` int(11) NOT NULL,
  PRIMARY KEY (`CodSupplier`,`BillNumber`,`SerialNO`,`NumSparePart`),
  KEY `FKStore` (`CodWarehouse`),
  KEY `FKBuying` (`SerialNO`,`NumSparePart`),
  CONSTRAINT `FKAppend` FOREIGN KEY (`CodSupplier`, `BillNumber`) REFERENCES `PURCHASES` (`CodSupplier`, `BillNumber`) ON DELETE CASCADE,
  CONSTRAINT `FKBuying` FOREIGN KEY (`SerialNO`, `NumSparePart`) REFERENCES `SPARE_PARTS` (`SerialNO`, `NumSparePart`),
  CONSTRAINT `FKStore` FOREIGN KEY (`CodWarehouse`) REFERENCES `WAREHOUSES` (`CodWarehouse`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `STOCKS`
--

LOCK TABLES `STOCKS` WRITE;
/*!40000 ALTER TABLE `STOCKS` DISABLE KEYS */;
INSERT INTO `STOCKS` VALUES ('GHQUWU7261K',1,1,1,3,100.5,'','notes demo',1),('GHQUWU7261K',2,1,1,4,30.3,'','notes demo',2),('P3FNU71983',1,1,2,1,250,'','pezzo x stato : nuovo',2),('P3FNU71983',3,2,1,4,210,'','stato pezzo : nuovo',1),('P3FNU71983',2,2,3,3,400,'','stato pezzo : nuovo',2);
/*!40000 ALTER TABLE `STOCKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUBFAMILIES`
--

DROP TABLE IF EXISTS `SUBFAMILIES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUBFAMILIES` (
  `CodFamily` int(11) NOT NULL,
  `CodSubfamily` int(11) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`CodFamily`,`CodSubfamily`),
  CONSTRAINT `FKComprehend` FOREIGN KEY (`CodFamily`) REFERENCES `FAMILIES` (`CodFamily`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUBFAMILIES`
--

LOCK TABLES `SUBFAMILIES` WRITE;
/*!40000 ALTER TABLE `SUBFAMILIES` DISABLE KEYS */;
INSERT INTO `SUBFAMILIES` VALUES (1,1,'Injection molds'),(1,2,'Extrusion machines'),(1,3,'Plasic granulators'),(2,1,'Robotic welders'),(2,2,'Conveyor systems'),(3,1,'Furnaces'),(3,2,'Boilers'),(4,1,'Cookers'),(4,2,'Ovens'),(5,1,'Blast furnaces'),(5,2,'Casters'),(6,1,'Electronic cutter'),(6,2,'Pressing machines');
/*!40000 ALTER TABLE `SUBFAMILIES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SUPPLIERS`
--

DROP TABLE IF EXISTS `SUPPLIERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SUPPLIERS` (
  `CodSupplier` int(11) NOT NULL,
  `Name` varchar(25) NOT NULL,
  `IBAN` varchar(27) NOT NULL,
  `PIVA` varchar(11) NOT NULL,
  `Email` varchar(25) NOT NULL,
  `Telephone` varchar(15) NOT NULL,
  `Fax` varchar(15) NOT NULL,
  `PEC` varchar(40) NOT NULL,
  `ZipCode` varchar(11) NOT NULL,
  `CodAddress` int(11) NOT NULL,
  PRIMARY KEY (`CodSupplier`),
  KEY `FKBased` (`ZipCode`,`CodAddress`),
  CONSTRAINT `FKBased` FOREIGN KEY (`ZipCode`, `CodAddress`) REFERENCES `ADDRESSES` (`ZipCode`, `CodAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SUPPLIERS`
--

LOCK TABLES `SUPPLIERS` WRITE;
/*!40000 ALTER TABLE `SUPPLIERS` DISABLE KEYS */;
INSERT INTO `SUPPLIERS` VALUES (1,'ULIVIERI ricambi srl','IT4736291000000284211235612','0764352056C','uricambi@gmail.com','0528162628','0712333611','uricambi.pec.it','47814',3),(2,'DMG MORI','IT4999191000093745192235612','0711860012','dmgmori@gmail.com','0528162628','0445577812','dmgmori.it','55011',6);
/*!40000 ALTER TABLE `SUPPLIERS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UNITS`
--

DROP TABLE IF EXISTS `UNITS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UNITS` (
  `CodUnit` int(11) NOT NULL,
  `Description` varchar(10) NOT NULL,
  PRIMARY KEY (`CodUnit`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UNITS`
--

LOCK TABLES `UNITS` WRITE;
/*!40000 ALTER TABLE `UNITS` DISABLE KEYS */;
INSERT INTO `UNITS` VALUES (1,'KM'),(2,'KWH'),(3,'H'),(4,'L'),(5,'M3'),(6,'CY');
/*!40000 ALTER TABLE `UNITS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `URGENCY_LEVELS`
--

DROP TABLE IF EXISTS `URGENCY_LEVELS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `URGENCY_LEVELS` (
  `CodUrgency` int(11) NOT NULL,
  `Description` varchar(50) NOT NULL,
  PRIMARY KEY (`CodUrgency`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `URGENCY_LEVELS`
--

LOCK TABLES `URGENCY_LEVELS` WRITE;
/*!40000 ALTER TABLE `URGENCY_LEVELS` DISABLE KEYS */;
INSERT INTO `URGENCY_LEVELS` VALUES (1,'Normal'),(2,'Urgent'),(3,'Priority'),(4,'Critical');
/*!40000 ALTER TABLE `URGENCY_LEVELS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `USES`
--

DROP TABLE IF EXISTS `USES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USES` (
  `U_S_CodSupplier` int(11) NOT NULL,
  `U_S_BillNumber` int(11) NOT NULL,
  `U_S_SerialNO` varchar(50) NOT NULL,
  `U_S_NumSparePart` int(11) NOT NULL,
  `SerialNO` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Amount` int(11) NOT NULL,
  PRIMARY KEY (`SerialNO`,`Description`,`U_S_CodSupplier`,`U_S_BillNumber`,`U_S_SerialNO`,`U_S_NumSparePart`),
  KEY `FKUse_STO` (`U_S_CodSupplier`,`U_S_BillNumber`,`U_S_SerialNO`,`U_S_NumSparePart`),
  CONSTRAINT `FKUse_STO` FOREIGN KEY (`U_S_CodSupplier`, `U_S_BillNumber`, `U_S_SerialNO`, `U_S_NumSparePart`) REFERENCES `STOCKS` (`CodSupplier`, `BillNumber`, `SerialNO`, `NumSparePart`) ON DELETE CASCADE,
  CONSTRAINT `FKUse_WOR` FOREIGN KEY (`SerialNO`, `Description`) REFERENCES `WORK_DESCRIPTIONS` (`SerialNO`, `Description`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `USES`
--

LOCK TABLES `USES` WRITE;
/*!40000 ALTER TABLE `USES` DISABLE KEYS */;
INSERT INTO `USES` VALUES (1,1,'GHQUWU7261K',1,'GHQUWU7261K','work description demo',2),(1,2,'P3FNU71983',1,'P3FNU71983','cambio pezzo x',1),(2,3,'P3FNU71983',2,'P3FNU71983','cambio pezzo y',1),(2,1,'P3FNU71983',3,'P3FNU71983','cambio pezzo z',1);
/*!40000 ALTER TABLE `USES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WAREHOUSES`
--

DROP TABLE IF EXISTS `WAREHOUSES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WAREHOUSES` (
  `CodWarehouse` int(11) NOT NULL,
  `Telephone` varchar(15) NOT NULL,
  `ZipCode` varchar(11) NOT NULL,
  `CodAddress` int(11) NOT NULL,
  PRIMARY KEY (`CodWarehouse`),
  KEY `FKLocate` (`ZipCode`,`CodAddress`),
  CONSTRAINT `FKLocate` FOREIGN KEY (`ZipCode`, `CodAddress`) REFERENCES `ADDRESSES` (`ZipCode`, `CodAddress`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WAREHOUSES`
--

LOCK TABLES `WAREHOUSES` WRITE;
/*!40000 ALTER TABLE `WAREHOUSES` DISABLE KEYS */;
INSERT INTO `WAREHOUSES` VALUES (1,'0817251831','00010',4),(2,'0982152310','20010',2),(3,'0443296381','56010',7);
/*!40000 ALTER TABLE `WAREHOUSES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WORKS`
--

DROP TABLE IF EXISTS `WORKS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WORKS` (
  `CF` varchar(16) NOT NULL,
  `SerialNO` varchar(50) NOT NULL,
  PRIMARY KEY (`SerialNO`,`CF`),
  KEY `FKWor_EMP` (`CF`),
  CONSTRAINT `FKWor_EMP` FOREIGN KEY (`CF`) REFERENCES `EMPLOYEES` (`CF`),
  CONSTRAINT `FKWor_ITE` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORKS`
--

LOCK TABLES `WORKS` WRITE;
/*!40000 ALTER TABLE `WORKS` DISABLE KEYS */;
/*!40000 ALTER TABLE `WORKS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WORK_DESCRIPTIONS`
--

DROP TABLE IF EXISTS `WORK_DESCRIPTIONS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WORK_DESCRIPTIONS` (
  `SerialNO` varchar(50) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `Tasks` varchar(50) NOT NULL,
  `Notes` varchar(100) NOT NULL,
  PRIMARY KEY (`SerialNO`,`Description`),
  CONSTRAINT `FKRelation` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORK_DESCRIPTIONS`
--

LOCK TABLES `WORK_DESCRIPTIONS` WRITE;
/*!40000 ALTER TABLE `WORK_DESCRIPTIONS` DISABLE KEYS */;
INSERT INTO `WORK_DESCRIPTIONS` VALUES ('GHQUWU7261K','work description demo','tasks demo','notes demo'),('P3FNU71983','cambio pezzo x','smontaggio e rimontaggio','note'),('P3FNU71983','cambio pezzo y','smontaggio e rimontaggio','note'),('P3FNU71983','cambio pezzo z','smontaggio e rimontaggio','note');
/*!40000 ALTER TABLE `WORK_DESCRIPTIONS` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `WORK_ORDERS`
--

DROP TABLE IF EXISTS `WORK_ORDERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `WORK_ORDERS` (
  `SerialNO` varchar(50) NOT NULL,
  `NumWorkOrder` int(11) NOT NULL,
  `Description` varchar(50) NOT NULL,
  `PlannedTimeMaintenance` float NOT NULL,
  `PlannedStart` date NOT NULL,
  `PlannedEnd` date NOT NULL,
  `ActualStart` date DEFAULT NULL,
  `ActualEnd` date DEFAULT NULL,
  `Type` varchar(25) NOT NULL,
  `BreakdownPicture` varchar(25) DEFAULT NULL,
  `Corrective` varchar(50) DEFAULT NULL,
  `CodUrgency` int(11) NOT NULL,
  `CodProject` int(11) DEFAULT NULL,
  `Run_SerialNO` varchar(50) DEFAULT NULL,
  `Run_NumMaintenanceSchedule` int(11) DEFAULT NULL,
  PRIMARY KEY (`SerialNO`,`NumWorkOrder`),
  KEY `FKState` (`CodUrgency`),
  KEY `FKRunByProject` (`CodProject`),
  KEY `FKRunByMS_FK` (`Run_SerialNO`,`Run_NumMaintenanceSchedule`),
  CONSTRAINT `FKIssueWork` FOREIGN KEY (`SerialNO`) REFERENCES `ITEMS` (`SerialNO`) ON DELETE CASCADE,
  CONSTRAINT `FKRunByMS_FK` FOREIGN KEY (`Run_SerialNO`, `Run_NumMaintenanceSchedule`) REFERENCES `MAINTENANCE_SCHEDULES` (`SerialNO`, `NumMaintenanceSchedule`),
  CONSTRAINT `FKRunByProject` FOREIGN KEY (`CodProject`) REFERENCES `PROJECT_SCHEDULES` (`CodProject`),
  CONSTRAINT `FKState` FOREIGN KEY (`CodUrgency`) REFERENCES `URGENCY_LEVELS` (`CodUrgency`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `WORK_ORDERS`
--

LOCK TABLES `WORK_ORDERS` WRITE;
/*!40000 ALTER TABLE `WORK_ORDERS` DISABLE KEYS */;
INSERT INTO `WORK_ORDERS` VALUES ('GHQUWU7261K',1,'work order maintenance 1',3,'2020-09-08','2020-09-08',NULL,NULL,'Ordinary',NULL,NULL,2,NULL,'GHQUWU7261K',1),('GHQUWU7261K',2,'extraordinary work order',4,'2020-09-08','2020-09-08',NULL,NULL,'Extraordinary',NULL,'piece break',2,NULL,'GHQUWU7261K',NULL),('GHQUWU7261K',3,'project work order with work description',3,'2020-10-11','2020-10-11',NULL,NULL,'Ordinary',NULL,NULL,3,12,'GHQUWU7261K',NULL),('P3FNU71983',1,'project work order 1',4,'2020-10-10','2020-10-10',NULL,NULL,'Ordinary',NULL,NULL,1,12,'P3FNU71983',NULL),('P3FNU71983',2,'project work order 2',7,'2020-11-10','2020-11-10',NULL,NULL,'Ordinary',NULL,NULL,1,12,'P3FNU71983',NULL),('P3FNU71983',3,'work order ms 1',5,'2020-09-08','2020-09-08','2020-09-08','2020-09-08','Ordinary',NULL,NULL,1,NULL,'P3FNU71983',1);
/*!40000 ALTER TABLE `WORK_ORDERS` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-09-07  8:27:55
