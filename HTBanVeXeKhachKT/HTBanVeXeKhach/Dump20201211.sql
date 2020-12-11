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
  `NgayKhoiHanh` date DEFAULT NULL,
  `GioKhoiHanh` varchar(6) DEFAULT NULL,
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
INSERT INTO `lotrinh` VALUES ('1','Đăk Lăk','NhaTrang','47M1-1234','2020-12-31','8:00'),('2','Đăk Lăk','NhaTrang','36AB-1234','2020-12-09','8:00'),('3','Hà Nội','NhaTrang','36AB-1234','2020-12-09','8:00'),('5','Hà Nội','NhaTrang','36AB-1234','2020-06-12','8:00'),('6','Hà Nội','TP.HCM','47M1-1234','2000-06-12','8:00'),('7','Hà Nội','TP.HCM','47M1-1234','2000-06-12','18:00');
/*!40000 ALTER TABLE `lotrinh` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `nhanvien`
--

DROP TABLE IF EXISTS `nhanvien`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `nhanvien` (
  `MaNV` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `tenNV` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ngaySinh` date NOT NULL,
  `diaChi` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `chucVu` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sdt` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `matKhau` varchar(20) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`MaNV`)
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
-- Table structure for table `vexe`
--

DROP TABLE IF EXISTS `vexe`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vexe` (
  `MaVe` varchar(5) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BienSoXe` varchar(10) DEFAULT NULL,
  `MaNV` varchar(20) NOT NULL,
  `HoTenKH` varchar(45) DEFAULT NULL,
  `SDTKH` varchar(11) DEFAULT NULL,
  `MaGhe` varchar(4) DEFAULT NULL,
  `ThoiGianDat` datetime DEFAULT NULL,
  `ThanhToan` tinyint DEFAULT NULL,
  `NgayKhoiHanh` date DEFAULT NULL,
  `GioKhoiHanh` varchar(6) DEFAULT NULL,
  `GiaVe` double DEFAULT NULL,
  `MaLoTrinh` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MaVe`),
  KEY `lotrinh_idx` (`MaLoTrinh`),
  CONSTRAINT `lotrinh` FOREIGN KEY (`MaLoTrinh`) REFERENCES `lotrinh` (`MaLoTrinh`)
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
INSERT INTO `xe` VALUES ('','Xe 40 chỗ ngồi'),('12QQ-1234','Xe limo house'),('36AB-1234','Xe limohouse'),('47M1-1234','Xe giường nằm');
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

-- Dump completed on 2020-12-11 17:03:11
