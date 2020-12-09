-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: quanlybenxe
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
  KEY `fk_kh_xe_idx` (`xeId`),
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
-- Table structure for table `lotrinh`
--

DROP TABLE IF EXISTS `lotrinh`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lotrinh` (
  `MaLoTrinh` varchar(10) NOT NULL,
  `TuyenDi` varchar(45) DEFAULT NULL,
  `TuyenDen` varchar(45) DEFAULT NULL,
  `MaXe` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MaLoTrinh`),
  KEY `loTrinh_Xe_idx` (`MaXe`),
  CONSTRAINT `loTrinh_Xe` FOREIGN KEY (`MaXe`) REFERENCES `xe` (`MaXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lotrinh`
--

LOCK TABLES `lotrinh` WRITE;
/*!40000 ALTER TABLE `lotrinh` DISABLE KEYS */;
INSERT INTO `lotrinh` VALUES ('1','Đăk Lăk','TP.HCM','36AB-1234'),('2','Đăk Lăk','TP.HCM','36AB-1234'),('3','TP.HCM','NhaTrang','36AB-1234');
/*!40000 ALTER TABLE `lotrinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `idNV` int NOT NULL AUTO_INCREMENT,
  `tenNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChi` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chucVu` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`idNV`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `nhanvien`
--

LOCK TABLES `nhanvien` WRITE;
/*!40000 ALTER TABLE `nhanvien` DISABLE KEYS */;
INSERT INTO `nhanvien` VALUES (1,'123','1111-11-01','123','312','123','132@132'),(2,'32131','0015-11-16','s','egfss','asdfaf','adaf'),(3,'123','1212-12-12','qwregf','qwdgf','qwerf','qw'),(4,'egf','1111-11-11','wef','qwregf','qwergf','wqer'),(5,'123','1212-12-12','qwregf','qwdgf','qwerf','qw'),(7,'31','0014-11-15','wegf','qewg','wqf','qwe'),(8,'dfsd','0014-11-15','wre','wdf','wdf','qewf');
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
INSERT INTO `taikhoan` VALUES ('qwe','1233'),('root','123');
/*!40000 ALTER TABLE `taikhoan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `MaVe` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BienSoXe` varchar(10) DEFAULT NULL,
  `MaNV` int DEFAULT NULL,
  `HoTenKH` varchar(45) DEFAULT NULL,
  `SDTKH` varchar(11) DEFAULT NULL,
  `MaGhe` varchar(4) DEFAULT NULL,
  `ThoiGianDat` datetime DEFAULT NULL,
  `ThanhToan` tinyint DEFAULT NULL,
  PRIMARY KEY (`MaVe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vexe`
--

LOCK TABLES `vexe` WRITE;
/*!40000 ALTER TABLE `vexe` DISABLE KEYS */;
/*!40000 ALTER TABLE `vexe` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `xe`
--

DROP TABLE IF EXISTS `xe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `xe` (
  `MaXe` varchar(10) NOT NULL,
  `LoaiXe` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`MaXe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `xe`
--

LOCK TABLES `xe` WRITE;
/*!40000 ALTER TABLE `xe` DISABLE KEYS */;
INSERT INTO `xe` VALUES ('36AB-1234','Xe limohouse'),('47M1-1234','Xe giường nằm');
/*!40000 ALTER TABLE `xe` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-12-09 13:05:30
