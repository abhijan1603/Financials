-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: localhost    Database: jamshedpur_restaurant
-- ------------------------------------------------------
-- Server version	8.0.42

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
-- Table structure for table `sale_invoice_items`
--

DROP TABLE IF EXISTS `sale_invoice_items`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sale_invoice_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `line_total` decimal(15,2) NOT NULL,
  `quantity` decimal(15,3) NOT NULL,
  `rate` decimal(15,2) NOT NULL,
  `unit` varchar(20) NOT NULL,
  `inventory_item_id` bigint DEFAULT NULL,
  `invoice_id` bigint NOT NULL,
  `dish_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5ikog7qt24itwdlysuooehca7` (`inventory_item_id`),
  KEY `FKk4dbnun3gup2534xdooqnwvpl` (`invoice_id`),
  CONSTRAINT `FK5ikog7qt24itwdlysuooehca7` FOREIGN KEY (`inventory_item_id`) REFERENCES `inventory_items` (`id`),
  CONSTRAINT `FKk4dbnun3gup2534xdooqnwvpl` FOREIGN KEY (`invoice_id`) REFERENCES `sale_invoices` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sale_invoice_items`
--

LOCK TABLES `sale_invoice_items` WRITE;
/*!40000 ALTER TABLE `sale_invoice_items` DISABLE KEYS */;
INSERT INTO `sale_invoice_items` VALUES (1,220.00,1.000,220.00,'PLATE',1,1,NULL),(2,150.00,1.000,150.00,'PLATE',NULL,2,NULL),(3,220.00,1.000,220.00,'PLATE',NULL,2,NULL),(4,120.00,1.000,120.00,'PLATE',NULL,3,NULL),(5,80.00,1.000,80.00,'PLATE',NULL,4,NULL),(6,120.00,1.000,120.00,'PLATE',NULL,4,NULL),(7,120.00,1.000,120.00,'PLATE',NULL,4,NULL),(8,40.00,1.000,40.00,'PLATE',NULL,4,NULL),(9,0.00,1.000,0.00,'PLATE',NULL,4,NULL),(10,360.00,2.000,180.00,'PLATE',NULL,5,NULL),(11,120.00,2.000,60.00,'PLATE',NULL,5,NULL),(12,100.00,1.000,100.00,'PLATE',NULL,5,NULL),(13,40.00,1.000,40.00,'PLATE',NULL,5,NULL),(14,80.00,1.000,80.00,'PLATE',NULL,6,'French Fries'),(15,160.00,1.000,160.00,'PLATE',NULL,6,'Cheese Balls (8pcs)'),(16,180.00,1.000,180.00,'PLATE',NULL,6,'Masala Cheese Balls'),(17,50.00,1.000,50.00,'PLATE',NULL,6,'Hot & Sour Soup (Veg)'),(18,60.00,1.000,60.00,'PLATE',NULL,7,'Potato Twister'),(19,180.00,1.000,180.00,'PLATE',NULL,7,'Masala Cheese Balls'),(20,180.00,1.000,180.00,'PLATE',NULL,8,'Masala Cheese Balls'),(21,160.00,1.000,160.00,'PLATE',NULL,8,'Cheese Balls (8pcs)'),(22,60.00,1.000,60.00,'PLATE',NULL,8,'Potato Twister'),(23,80.00,1.000,80.00,'PLATE',NULL,8,'French Fries'),(24,120.00,2.000,60.00,'PLATE',NULL,9,'Potato Twister'),(25,160.00,1.000,160.00,'PLATE',NULL,9,'Cheese Balls (8pcs)'),(26,360.00,6.000,60.00,'PLATE',NULL,10,'Potato Twister'),(27,100.00,1.000,100.00,'PLATE',NULL,11,'Chicken Pakoda'),(28,70.00,1.000,70.00,'PLATE',NULL,11,'Sweet Corn Soup (Chicken)'),(29,70.00,1.000,70.00,'PLATE',NULL,11,'Hot & Sour Soup (Chicken)'),(30,30.00,1.000,30.00,'PLATE',NULL,11,'Omlet'),(31,80.00,1.000,80.00,'PLATE',NULL,12,'Chilly Chicken (Half)'),(32,120.00,1.000,120.00,'PLATE',NULL,12,'Chilli Babycorn'),(33,30.00,1.000,30.00,'PLATE',NULL,12,'Omlet'),(34,80.00,1.000,80.00,'PLATE',NULL,12,'Chicken Roll'),(35,250.00,1.000,250.00,'PLATE',NULL,13,'Mutton Dum Biryani'),(36,160.00,1.000,160.00,'PLATE',NULL,13,'Chicken Dum Biryani'),(37,160.00,1.000,160.00,'PLATE',NULL,14,'Chicken Dum Biryani'),(38,120.00,1.000,120.00,'PLATE',NULL,14,'Veg Biryani'),(39,120.00,1.000,120.00,'PLATE',NULL,14,'Dal Makhani'),(40,50.00,1.000,50.00,'PLATE',NULL,14,'Sweet Corn Soup (Veg)'),(41,160.00,1.000,160.00,'PLATE',NULL,15,'Cheese Balls (8pcs)'),(42,250.00,1.000,250.00,'PLATE',NULL,15,'Mutton Dum Biryani'),(43,160.00,1.000,160.00,'PLATE',NULL,15,'Chowmein (Chicken)'),(44,120.00,2.000,60.00,'PLATE',NULL,16,'Potato Twister'),(45,320.00,2.000,160.00,'PLATE',NULL,16,'Cheese Balls (8pcs)');
/*!40000 ALTER TABLE `sale_invoice_items` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-29 12:38:44
