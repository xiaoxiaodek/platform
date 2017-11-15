CREATE DATABASE  IF NOT EXISTS `platform` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `platform`;
-- MySQL dump 10.13  Distrib 5.5.54, for debian-linux-gnu (x86_64)
--
-- Host: 127.0.0.1    Database: drip
-- ------------------------------------------------------
-- Server version	5.5.54-0ubuntu0.14.04.1-log

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
-- Table structure for table `client`
--
DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `comid` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `comaddr` varchar(200) NOT NULL,
  `comemail` varchar(50) NOT NULL,
  `comname` varchar(60) NOT NULL,
  `comcontact` varchar(30) NOT NULL,
  `createtime` datetime NOT NULL,
  `typeId` int(11) NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`comid`),
  UNIQUE KEY `comid` (`comid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contract`
--

DROP TABLE IF EXISTS `contract`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contract` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `camt` double NOT NULL,
  `cstarttime` datetime NOT NULL,
  `cendtime` datetime NOT NULL,
  `cname` varchar(50) NOT NULL,
  `appreason` varchar(15) NOT NULL,
  `auditstatid` int(11) NOT NULL,
  `suppid` int(11) NOT NULL,
  `signdate` datetime NOT NULL,
  `cstat` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  `appusrid` int(11) NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`cid`),
  UNIQUE KEY `cid` (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL,
  `pid` int(11) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  `createtime` datetime NOT NULL,
  `flocal` varchar(255) NOT NULL,
  `fname` varchar(255) NOT NULL,
  `fsummary` text NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`fid`),
  UNIQUE KEY `fid` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cfile`
--

DROP TABLE IF EXISTS `cfile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cfile` (
  `cfid` int(11) NOT NULL AUTO_INCREMENT,
  `cid` int(11) NOT NULL,
  `fid` int(11) NOT NULL,
  PRIMARY KEY (`cfid`),
  UNIQUE KEY `cfid` (`cfid`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `account` (
  `acctid` int(11) NOT NULL,
  `acctname` varchar(40) NOT NULL,
  `accttype` int(11) NOT NULL,
  `acctamt` decimal(10,0) NOT NULL,
  `createtime` datetime NOT NULL,
  `modtime` datetime NOT NULL,
  `maid` int(11) DEFAULT NULL,
  PRIMARY KEY (`acctid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exinterface`
--

DROP TABLE IF EXISTS `exinterface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `exinterface` (
  `exinfid` int(11) NOT NULL COMMENT '外部接口编号',
  `exinfname` varchar(40) NOT NULL,
  `exinftype` int(11) NOT NULL,
  `payprice` decimal(10,0) NOT NULL,
  `discount` decimal(10,0) NOT NULL,
  `exinterfacecol` datetime NOT NULL,
  `modtime` datetime NOT NULL,
  PRIMARY KEY (`exinfid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exinterface`
--

LOCK TABLES `exinterface` WRITE;
/*!40000 ALTER TABLE `exinterface` DISABLE KEYS */;
/*!40000 ALTER TABLE `exinterface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `interface`
--

DROP TABLE IF EXISTS `interface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `interface` (
  `idfid` int(11) NOT NULL,
  `infname` varchar(40) NOT NULL,
  `inftype` int(11) NOT NULL,
  `exinf` tinyint(4) NOT NULL,
  `basprice` decimal(10,0) NOT NULL,
  `dicount` decimal(10,0) NOT NULL,
  `createtime` datetime NOT NULL,
  `interfacecol` datetime NOT NULL,
  PRIMARY KEY (`idfid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `interface`
--

LOCK TABLES `interface` WRITE;
/*!40000 ALTER TABLE `interface` DISABLE KEYS */;
/*!40000 ALTER TABLE `interface` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `intfaceexinf`
--

DROP TABLE IF EXISTS `intfaceexinf`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `intfaceexinf` (
  `id` int(11) NOT NULL,
  `infid` int(11) NOT NULL COMMENT '接口编号',
  `exinfid` int(11) NOT NULL COMMENT '外部接口编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `intfaceexinf`
--

LOCK TABLES `intfaceexinf` WRITE;
/*!40000 ALTER TABLE `intfaceexinf` DISABLE KEYS */;
/*!40000 ALTER TABLE `intfaceexinf` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `masteracct`
--

DROP TABLE IF EXISTS `masteracct`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `masteracct` (
  `maid` int(11) NOT NULL,
  `maname` varchar(40) NOT NULL COMMENT '总账户名称',
  `maamt` decimal(10,0) NOT NULL COMMENT '总账户金额',
  `createtime` datetime NOT NULL,
  `comid` int(11) NOT NULL COMMENT '公司编号',
  PRIMARY KEY (`maid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `masteracct`
--

LOCK TABLES `masteracct` WRITE;
/*!40000 ALTER TABLE `masteracct` DISABLE KEYS */;
/*!40000 ALTER TABLE `masteracct` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pinterface`
--

DROP TABLE IF EXISTS `pinterface`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pinterface` (
  `id` int(11) NOT NULL,
  `pid` int(11) NOT NULL COMMENT '项目编号',
  `infid` int(11) NOT NULL COMMENT '接口编号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `createtime` datetime NOT NULL,
  `modtime` datetime NOT NULL,
  `uemail` varchar(30) NOT NULL,
  `uname` varchar(20) NOT NULL,
  `upwd` varchar(32) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `uid` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `pname` varchar(45) NOT NULL,
  `auditstatid` int(11) NOT NULL,
  `psid` int(11) NOT NULL,
  `createtime` datetime NOT NULL,
  `modtime` datetime NOT NULL,
  `suppid` int(11) NOT NULL,
  `aid` int(11) NOT NULL,
  `apptime` datetime NOT NULL,
  `appreason` text NOT NULL,
  `apptype` int(11) NOT NULL,
  `cid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  UNIQUE KEY `pid_UNIQUE` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `operate`
--

DROP TABLE IF EXISTS `operate`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operate` (
  `oid` int(11) NOT NULL AUTO_INCREMENT,
  `oname` varchar(45) NOT NULL,
  `otime` datetime NOT NULL,
  `oresult` varchar(45) NOT NULL,
  `dealerid` int(11) NOT NULL,
  `cid` int(11) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `iid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`oid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operate`
--

