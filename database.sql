-- MySQL dump 10.13  Distrib 8.0.35, for Win64 (x86_64)
--
-- Host: localhost    Database: phc_database
-- ------------------------------------------------------
-- Server version	8.0.35

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `alerts`
--

DROP TABLE IF EXISTS `alerts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `alerts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `sent_at` datetime(6) DEFAULT NULL,
  `recipient_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK419j42c8tkd9ja320j2ah62eo` (`recipient_id`),
  CONSTRAINT `FK419j42c8tkd9ja320j2ah62eo` FOREIGN KEY (`recipient_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alerts`
--

LOCK TABLES `alerts` WRITE;
/*!40000 ALTER TABLE `alerts` DISABLE KEYS */;
/*!40000 ALTER TABLE `alerts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `check_in_time` time(6) DEFAULT NULL,
  `check_out_time` time(6) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `doctor_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjp3ip61rr7v54al5cisdpvv0x` (`doctor_id`),
  CONSTRAINT `FKjp3ip61rr7v54al5cisdpvv0x` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_roles`
--

DROP TABLE IF EXISTS `doctor_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_roles` (
  `doctor_id` bigint NOT NULL,
  `role_id` bigint NOT NULL,
  PRIMARY KEY (`doctor_id`,`role_id`),
  KEY `FKj0is9oih9estxq6ja4hsybk74` (`role_id`),
  CONSTRAINT `FKj0is9oih9estxq6ja4hsybk74` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKpwiqxvlyq6syoei9vm13yom06` FOREIGN KEY (`doctor_id`) REFERENCES `doctors` (`doctor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_roles`
--

LOCK TABLES `doctor_roles` WRITE;
/*!40000 ALTER TABLE `doctor_roles` DISABLE KEYS */;
INSERT INTO `doctor_roles` VALUES (1,1),(2,1),(3,1),(4,1),(5,1);
/*!40000 ALTER TABLE `doctor_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `doctor_id` bigint NOT NULL,
  `facility_id` bigint DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `specialization` varchar(255) DEFAULT NULL,
  `shift_id` bigint DEFAULT NULL,
  PRIMARY KEY (`doctor_id`),
  UNIQUE KEY `UKcaifv0va46t2mu85cg5afmayf` (`email`),
  UNIQUE KEY `UKf37oee8utq0h8o3998g7g9jf7` (`shift_id`),
  KEY `FKm5r4md9thhnrsktdsrdqtlt4r` (`facility_id`),
  CONSTRAINT `FK387yvgqq927rxa69i83lpf3kr` FOREIGN KEY (`shift_id`) REFERENCES `shifts` (`id`),
  CONSTRAINT `FKm5r4md9thhnrsktdsrdqtlt4r` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,1,'arul@example.com','Dr. Arul Kumar','$2a$10$H7MgQyY9/LJuW2akSYWBweahlS9hCtCD7/a1HiZSWC2yfcfT9jAZG','General Medicine',NULL),(2,1,'senthil@example.com','Dr. Senthil Kumar','$2a$10$uao6eBtXfC4ikPeaWFJTRuxdl2zLgIYnI6ABoJUFdMGSENa9ZIROC','Pediatrics',NULL),(3,2,'priya@example.com','Dr. Priya Raj','$2a$10$eNVz6wn17Zgr9jMSW8g43eudyh0nXHtT14uA/IiZea82qsWdYSbIu','Gynecology',NULL),(4,2,'karthik@example.com','Dr. Karthik Mani','$2a$10$hVJa8lroMFU02rzyEd8RV.OaCXGEr4hTNyaBPztaxS3T/krJc13b6','Orthopedics',NULL),(5,3,'meena@example.com','Dr. Meena Mohan','$2a$10$vb3JGAWH7cKGNncX/3m0sORI2eAPYrTous8dPlRj/7VcSDBZyqLd6','Dermatology',NULL);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facilities`
--

DROP TABLE IF EXISTS `facilities`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facilities` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `region_id` bigint DEFAULT NULL,
  `block` varchar(255) DEFAULT NULL,
  `district` varchar(255) DEFAULT NULL,
  `faciliy_type` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKg45t6dksqno1c0w5qhl8isg66` (`region_id`),
  CONSTRAINT `FKoj3xl0m7oisdpelyn34908xep` FOREIGN KEY (`region_id`) REFERENCES `regions` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facilities`
--

LOCK TABLES `facilities` WRITE;
/*!40000 ALTER TABLE `facilities` DISABLE KEYS */;
INSERT INTO `facilities` VALUES (1,1,'MADURAI EAST','MADURAI','Addl. PHC','RAJAKOOR'),(2,2,'Madurai Corp.','MADURAI','Urban PHC','Karisalkulam'),(3,3,'Madurai Corp.','MADURAI','Urban PHC','Munichalai'),(4,4,'Madurai West','MADURAI','Upgraded Block PHC','Samayanallur'),(5,5,'Melur','MADURAI','Upgraded Block PHC','Vellalore'),(6,6,'Thiruparankundram','MADURAI','Urban PHC','Thiruparankundram'),(7,7,'Madurai Corp.','MADURAI','Urban PHC','Kodikulam'),(8,8,'Thirumangalam','MADURAI','Upgraded Block PHC','Checkanurani'),(9,9,'Madurai Corp.','MADURAI','Urban PHC','K.Pudur'),(10,10,'Thirumangalam','MADURAI','Addl. PHC','T.Pudupatti'),(11,11,NULL,NULL,'Unknown','PSNACET');
/*!40000 ALTER TABLE `facilities` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `regions`
--

DROP TABLE IF EXISTS `regions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `regions` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `boundary` polygon NOT NULL /*!80003 SRID 4326 */,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `regions`
--

LOCK TABLES `regions` WRITE;
/*!40000 ALTER TABLE `regions` DISABLE KEYS */;
INSERT INTO `regions` VALUES (1,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0sΩm¶B\‹#@êLáNœéS@Wµ§£\‹#@v®¶$\ÎéS@´\Èz¢\Î\⁄#@\\ìnK\‰éS@\”k≥±\€#@\ÈFXTƒéS@sΩm¶B\‹#@êLáNœéS@'),(2,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0\Ôè\˜™ï\È#@ò°\ÒDÜS@∂-\ lê\È#@\—\Èy7ÜS@\‹,^,\È#@a\ƒ>ÜS@Ω\»¸\Z\È#@\‚Èï≤ÜS@\Ôè\˜™ï\È#@ò°\ÒDÜS@'),(3,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0T\≈T˙	\◊#@\\›zMàS@\œ˘.•\÷#@¶\–yç]àS@ú\‡õ¶\œ\÷#@≤J\Èô^àS@á¡¸2\◊#@˛ö¨QàS@T\≈T˙	\◊#@\\›zMàS@'),(4,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0&\√\Ò|\Ù#@xò\ˆ\Õ˝ÇS@b\ÿaL˙\Û#@â\ÏÉ,ÉS@\Û\∆Ia\ﬁ\Û#@è\√`˛\nÉS@)YNB\È\Û#@1“ã\⁄˝ÇS@&\√\Ò|\Ù#@xò\ˆ\Õ˝ÇS@'),(5,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0Cp\\\∆M\ı%@¢¥7¯\¬AS@^/M\‡\Ù%@Ø|ñ\Á¡AS@•¯¯Ñ\Ï\Ù%@\Œ\0d\ÀAS@Q\˜H\ı%@\‘>\Õ\…AS@Cp\\\∆M\ı%@¢¥7¯\¬AS@'),(6,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0_ïï\≈#@d \œ.ﬂÑS@ªH°,|\≈#@\◊2é\ÁÑS@D6ê.6\≈#@2\Ã	\⁄\‰ÑS@°\÷4\Ô8\≈#@≠4)›ÑS@_ïï\≈#@d \œ.ﬂÑS@'),(7,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0çF>Øx\Í#@N∑\ÏˇâS@\‹d:t\Í#@&\√\Ò|äS@Iº<ù+\Í#@Wï}WäS@Z\Û\„/-\Í#@`<ÉÜ˛âS@çF>Øx\Í#@N∑\ÏˇâS@'),(8,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0ZK\0˛\·#@M\Ÿ\È~S@™\’WW\‚#@IÖ±Ö ~S@X\«\ÒC•\·#@Z\÷˝c!~S@ﬁí∞´\·#@ä:s	~S@ZK\0˛\·#@M\Ÿ\È~S@'),(9,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0•JîΩ•\‰#@\'à∫@âS@\∆O\„\ﬁ\‰#@qU\ŸwEâS@l?\„\√\‰#@t\\ç\ÏJâS@í\ÏjÜ\‰#@\–#ÑGâS@•JîΩ•\‰#@\'à∫@âS@'),(10,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0\\\Â	Ñç#@îlu9%{S@>\Ì\\◊dç#@•°F!{S@v28J^ç#@iãk|&{S@\Ëyítç#@^f\ÿ({S@\\\Â	Ñç#@îlu9%{S@'),(11,_binary '\Ê\0\0\0\0\0\0\0\0\0\0\0\ O™}:\÷$@d˘ÉÅyS@iR\n∫Ω\‘$@ú\Zh>\ÁyS@6ëô\‘$@\ÊñVC\‚yS@B\Ì∑v¢\‘$@wÜ©-uyS@\ O™}:\÷$@d˘ÉÅyS@');
/*!40000 ALTER TABLE `regions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `role_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `UKofx66keruapi6vyqpv6f2or37` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (5,'ADMIN'),(3,'AO'),(4,'BMO'),(2,'DHO'),(1,'DOCTOR');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_doctors`
--

DROP TABLE IF EXISTS `roles_doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_doctors` (
  `role_role_id` bigint NOT NULL,
  `doctors_doctor_id` bigint NOT NULL,
  PRIMARY KEY (`role_role_id`,`doctors_doctor_id`),
  KEY `FK7dsw9798far9lhbdj4l93r1ru` (`doctors_doctor_id`),
  CONSTRAINT `FK7dsw9798far9lhbdj4l93r1ru` FOREIGN KEY (`doctors_doctor_id`) REFERENCES `doctors` (`doctor_id`),
  CONSTRAINT `FKt1215qkreuqpqht3gnnofic9q` FOREIGN KEY (`role_role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_doctors`
--

LOCK TABLES `roles_doctors` WRITE;
/*!40000 ALTER TABLE `roles_doctors` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles_users`
--

DROP TABLE IF EXISTS `roles_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles_users` (
  `role_role_id` bigint NOT NULL,
  `users_user_id` bigint NOT NULL,
  PRIMARY KEY (`role_role_id`,`users_user_id`),
  KEY `FKi2nrp9i4isa7un5d4jkuy0c30` (`users_user_id`),
  CONSTRAINT `FKi2nrp9i4isa7un5d4jkuy0c30` FOREIGN KEY (`users_user_id`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKsh5t497i4tny4nst9f4fgjww7` FOREIGN KEY (`role_role_id`) REFERENCES `roles` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles_users`
--

LOCK TABLES `roles_users` WRITE;
/*!40000 ALTER TABLE `roles_users` DISABLE KEYS */;
/*!40000 ALTER TABLE `roles_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shifts`
--

DROP TABLE IF EXISTS `shifts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shifts` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `end_time` time(6) DEFAULT NULL,
  `start_time` time(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `approved_by` bigint DEFAULT NULL,
  `assigned_by` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfu30fy0eohstq2onrserye1gu` (`approved_by`),
  KEY `FKnrm7f5exsw6aclun5a5russto` (`assigned_by`),
  CONSTRAINT `FKfu30fy0eohstq2onrserye1gu` FOREIGN KEY (`approved_by`) REFERENCES `users` (`user_id`),
  CONSTRAINT `FKnrm7f5exsw6aclun5a5russto` FOREIGN KEY (`assigned_by`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shifts`
--

LOCK TABLES `shifts` WRITE;
/*!40000 ALTER TABLE `shifts` DISABLE KEYS */;
/*!40000 ALTER TABLE `shifts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_roles`
--

DROP TABLE IF EXISTS `user_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_roles` (
  `role_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`role_id`,`user_id`),
  KEY `FKhfh9dx7w3ubf1co1vdev94g3f` (`user_id`),
  CONSTRAINT `FKh8ciramu9cc9q3qcqiv4ue8a6` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`),
  CONSTRAINT `FKhfh9dx7w3ubf1co1vdev94g3f` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_roles`
--

LOCK TABLES `user_roles` WRITE;
/*!40000 ALTER TABLE `user_roles` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `facility_id` bigint DEFAULT NULL,
  `user_id` bigint NOT NULL,
  `email` varchar(255) NOT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  KEY `FKpl5qpsn7qmnvp22slbem3lvu6` (`facility_id`),
  CONSTRAINT `FKpl5qpsn7qmnvp22slbem3lvu6` FOREIGN KEY (`facility_id`) REFERENCES `facilities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-13 15:38:23
