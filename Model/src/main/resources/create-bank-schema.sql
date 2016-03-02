
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bank`;

-- -----------------------------------------------------
-- Schema bank
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bank` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `bank`;


-- -----------------------------------------------------
-- Table `bank`.`bankAccount`
-- -----------------------------------------------------

DROP TABLE IF EXISTS `bank`.`bank_account`;

CREATE TABLE `bank`.`bank_account` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `user_name` VARCHAR (45) NOT NULL,
  `password` VARCHAR (45) NOT NULL,
  `account_balance` DECIMAL(9,2) DEFAULT 0,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bank`.`transaction`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bank`.`transaction` ;

CREATE TABLE IF NOT EXISTS `bank`.`transaction` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `bank_account_id` INT(11) NOT NULL,
  `amount` DECIMAL(9,2) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bank_account_idx` (`bank_account_id` ASC),
  CONSTRAINT `fk_bank_account`
  FOREIGN KEY (`bank_account_id`)
  REFERENCES `bank`.`bank_account` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB;