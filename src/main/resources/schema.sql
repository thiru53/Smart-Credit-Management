
--
-- Table structure for table credit_limit
--

DROP TABLE IF EXISTS credit_limit;

CREATE TABLE credit_limit (
  id bigint NOT NULL AUTO_INCREMENT,
  borrower_id varchar(255) DEFAULT NULL,
  credit_limit double DEFAULT NULL,
  remaining_amount double DEFAULT NULL,
  used_amount double DEFAULT NULL,
  PRIMARY KEY (id)
);


--
-- Table structure for table loan
--

DROP TABLE IF EXISTS loan;

CREATE TABLE loan (
  id bigint NOT NULL AUTO_INCREMENT,
  borrower_id varchar(255) DEFAULT NULL,
  loan_amount double DEFAULT NULL,
  loan_date date DEFAULT NULL,
  paid_date date DEFAULT NULL,
  payment_status varchar(255) DEFAULT NULL,
  repayment_date date DEFAULT NULL,
  PRIMARY KEY (id)
);


--
-- Table structure for table payment_transaction
--

DROP TABLE IF EXISTS payment_transaction;

CREATE TABLE payment_transaction (
  id bigint NOT NULL AUTO_INCREMENT,
  loan_amount double DEFAULT NULL,
  loan_id bigint DEFAULT NULL,
  paid_on_date date DEFAULT NULL,
  payment_status varchar(255) DEFAULT NULL,
  penalty_amount double DEFAULT NULL,
  repayment_date date DEFAULT NULL,
  total_amount double DEFAULT NULL,
  PRIMARY KEY (id)
);

