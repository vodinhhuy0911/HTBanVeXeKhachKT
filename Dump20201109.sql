-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: quanlybenxe
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `bangkhachhang`
--

DROP TABLE IF EXISTS `bangkhachhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bangkhachhang` (
  `idKH` int NOT NULL AUTO_INCREMENT,
  `TenKH` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ngaySinh` time NOT NULL,
  `SDT` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `veId` int NOT NULL,
  `xeId` int NOT NULL,
  PRIMARY KEY (`idKH`),
  KEY `fk_kh_ve_idx` (`veId`),
  KEY `fk_kh_xe_idx` (`xeId`),
  CONSTRAINT `fk_kh_ve` FOREIGN KEY (`veId`) REFERENCES `bangvexe` (`idVe`),
  CONSTRAINT `fk_kh_xe` FOREIGN KEY (`xeId`) REFERENCES `bangxekhach` (`idXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bangkhachhang`
--

LOCK TABLES `bangkhachhang` WRITE;
/*!40000 ALTER TABLE `bangkhachhang` DISABLE KEYS */;
/*!40000 ALTER TABLE `bangkhachhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bangvexe`
--

DROP TABLE IF EXISTS `bangvexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bangvexe` (
  `idVe` int NOT NULL AUTO_INCREMENT,
  `bienSoXe` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `noiDi` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `noiDen` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gioDi` time NOT NULL,
  `ngayBatDau` time NOT NULL,
  `ghe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `giaVe` int NOT NULL,
  `xeId` int NOT NULL,
  `nhanVienId` int NOT NULL,
  `khachHangId` int NOT NULL,
  PRIMARY KEY (`idVe`),
  KEY `fk_ve_xe_idx` (`xeId`),
  KEY `fk_ve_nv_idx` (`nhanVienId`),
  KEY `fk_ve_kh_idx` (`khachHangId`),
  CONSTRAINT `fk_ve_kh` FOREIGN KEY (`khachHangId`) REFERENCES `bangkhachhang` (`idKH`),
  CONSTRAINT `fk_ve_nv` FOREIGN KEY (`nhanVienId`) REFERENCES `nhanvien` (`idNV`),
  CONSTRAINT `fk_ve_xe` FOREIGN KEY (`xeId`) REFERENCES `bangxekhach` (`idXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bangvexe`
--

LOCK TABLES `bangvexe` WRITE;
/*!40000 ALTER TABLE `bangvexe` DISABLE KEYS */;
/*!40000 ALTER TABLE `bangvexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bangxekhach`
--

DROP TABLE IF EXISTS `bangxekhach`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bangxekhach` (
  `idXe` int NOT NULL AUTO_INCREMENT,
  `bienSoXe` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tenXe` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `noiDi` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `noiDen` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `gioBatDau` time NOT NULL,
  `ngayDi` time NOT NULL,
  `soLuongGheTrong` int NOT NULL,
  PRIMARY KEY (`idXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bangxekhach`
--

LOCK TABLES `bangxekhach` WRITE;
/*!40000 ALTER TABLE `bangxekhach` DISABLE KEYS */;
/*!40000 ALTER TABLE `bangxekhach` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `idNV` int NOT NULL AUTO_INCREMENT,
  `tenTK` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `tenNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ngaySinh` time NOT NULL,
  `diaChi` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chucVu` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`idNV`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
/*!40000 ALTER TABLE `nhanvien` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taikhoan`
--

DROP TABLE IF EXISTS `taikhoan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `taikhoan` (
  `tenTK` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `matKhau` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`tenTK`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taikhoan`
--

LOCK TABLES `taikhoan` WRITE;
/*!40000 ALTER TABLE `taikhoan` DISABLE KEYS */;
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'quanlybenxe'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-11-09 13:49:24
