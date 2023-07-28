--
-- Dumping data for table `credit_limit`
--
INSERT INTO `credit_limit` VALUES (1,'25',5000,1000,4000),(2,'26',5000,1000,4000);

--
-- Dumping data for table `loan`
--
INSERT INTO `loan` VALUES (1,'25',1000,'2023-07-13','2023-07-13','Paid','2023-08-13'),(2,'25',4000,'2023-07-13',NULL,'Not Paid','2023-08-13'),(3,'26',4000,'2023-07-13',NULL,'Not Paid','2023-08-13');

--
-- Dumping data for table `payment_transaction`
--
INSERT INTO `payment_transaction` VALUES (1,1000,1,'2023-07-13','Paid',0,'2023-08-13',1000);
