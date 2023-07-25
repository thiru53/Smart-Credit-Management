
--
-- Table structure for table `credit_limit`
--

DROP TABLE IF EXISTS `credit_limit`;

CREATE TABLE `credit_limit` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrower_id` varchar(255) DEFAULT NULL,
  `credit_limit` double DEFAULT NULL,
  `remaining_amount` double DEFAULT NULL,
  `used_amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `credit_limit`
--

INSERT INTO `credit_limit` VALUES (1,'25',5000,1000,4000),(2,'26',5000,1000,4000);

--
-- Table structure for table `loan`
--

DROP TABLE IF EXISTS `loan`;

CREATE TABLE `loan` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `borrower_id` varchar(255) DEFAULT NULL,
  `loan_amount` double DEFAULT NULL,
  `loan_date` date DEFAULT NULL,
  `paid_date` date DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `repayment_date` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `loan`
--


INSERT INTO `loan` VALUES (1,'25',1000,'2023-07-13','2023-07-13','Paid','2023-08-13'),(2,'25',4000,'2023-07-13',NULL,'Not Paid','2023-08-13'),(3,'26',4000,'2023-07-13',NULL,'Not Paid','2023-08-13');

--
-- Table structure for table `payment_transaction`
--

DROP TABLE IF EXISTS `payment_transaction`;

CREATE TABLE `payment_transaction` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `loan_amount` double DEFAULT NULL,
  `loan_id` bigint DEFAULT NULL,
  `paid_on_date` date DEFAULT NULL,
  `payment_status` varchar(255) DEFAULT NULL,
  `penalty_amount` double DEFAULT NULL,
  `repayment_date` date DEFAULT NULL,
  `total_amount` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment_transaction`
--


INSERT INTO `payment_transaction` VALUES (1,1000,1,'2023-07-13','Paid',0,'2023-08-13',1000);
