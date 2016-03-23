CREATE DATABASE IF NOT EXISTS `cargo_traffic`
  CHARACTER SET 'utf8mb4'
  COLLATE 'utf8mb4_general_ci';

CREATE USER 'cargo_admin'@'%' IDENTIFIED BY '7654321';
GRANT ALL PRIVILEGES ON cargo_traffic . * TO 'cargo_admin'@'%';

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`company` (
  `id`      INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `name`    VARCHAR(250)         NOT NULL,
  `transportation_cost_per_km` DOUBLE(10,2) UNSIGNED NOT NULL DEFAULT '0.00',
  `date`    DATETIME,
  `locked` BIT(1)                        DEFAULT FALSE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`address` (
  `id`      INTEGER(11)  UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `country` VARCHAR(250),
  `city`    VARCHAR(250),
  `street`  VARCHAR(250),
  `house`   VARCHAR(250),
  `flat`   VARCHAR(250),
  `deleted` BIT(1)                         DEFAULT FALSE,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`user` (
  `id`         INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `username`   VARCHAR(250)         NOT NULL,
  `password`   VARCHAR(250)         NOT NULL,
  `name`       VARCHAR(250)         ,
  `surname`    VARCHAR(250)         NOT NULL,
  `patronymic` VARCHAR(250)         ,
  `email`      VARCHAR(250)         NOT NULL,
  `birthday`   DATE                 ,
  `company_id` INTEGER(11) UNSIGNED,
  `address_id` INTEGER(11) UNSIGNED ,
  `deleted`    BIT(1)                        DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX (`company_id` ASC),
  INDEX (`address_id` ASC),
  FOREIGN KEY (`company_id`)
  REFERENCES `cargo_traffic`.`company` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`address_id`)
  REFERENCES `cargo_traffic`.`address` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`role` (
  `id`   INTEGER(11) UNSIGNED NOT NULL AUTO_INCREMENT UNIQUE,
  `name` VARCHAR(250) NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`user_role` (
  `user_id` INTEGER(11) UNSIGNED NOT NULL,
  `role_id` INTEGER(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`),
  INDEX (`role_id`),
  FOREIGN KEY (`user_id`)
  REFERENCES `cargo_traffic`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`role_id`)
  REFERENCES `cargo_traffic`.`role` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`packing_list` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `issue_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dispatcher` INT(11) UNSIGNED NOT NULL,
  `departure_warehouse` INT(11) UNSIGNED NOT NULL,
  `destination_warehouse` INT(11) UNSIGNED NOT NULL,
  `status` ENUM('CREATED', 'CHECKED','TRANSPORTATION_STARTED', 'DELIVERED', 'REJECTED'),
  PRIMARY KEY (`id`),
  INDEX (`departure_warehouse` ASC),
  INDEX (`destination_warehouse` ASC),
  INDEX (`dispatcher` ASC),
  FOREIGN KEY (`dispatcher`)
  REFERENCES `cargo_traffic`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`departure_warehouse`)
  REFERENCES `cargo_traffic`.`warehouse` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`destination_warehouse`)
  REFERENCES `cargo_traffic`.`warehouse` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`vehicle_type` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`vehicle_fuel` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `cost` DECIMAL(10,2) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`vehicle` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `vehicle_producer` VARCHAR(250) NOT NULL DEFAULT '',
  `vehicle_model` VARCHAR(250) NOT NULL DEFAULT '',
  `license_plate` VARCHAR(250) NOT NULL,
  `products_constraint` DOUBLE(10,2) UNSIGNED NOT NULL,
  `fuel_consumption` DOUBLE(10,2) UNSIGNED NOT NULL,
  `company_id` INT(11) UNSIGNED NOT NULL,
  `vehicle_type_id` INT(11) UNSIGNED NOT NULL,
  `vehicle_fuel_id` INT(11) UNSIGNED NOT NULL,
  `deleted` BIT(1) DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX (`company_id` ASC),
  INDEX (`vehicle_type_id` ASC),
  INDEX (`vehicle_fuel_id` ASC),
  FOREIGN KEY (`company_id`)
  REFERENCES `cargo_traffic`.`company` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`vehicle_type_id`)
  REFERENCES `cargo_traffic`.`vehicle_type` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`vehicle_fuel_id`)
  REFERENCES `cargo_traffic`.`vehicle_fuel` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`waybill` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `departure_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `arrival_date` TIMESTAMP NULL DEFAULT NULL,
  `status` ENUM('TRANSPORTATION_STARTED', 'TRANSPORTATION_COMPLETED', 'REJECTED') NOT NULL,
  `packing_list_id` INT(11) UNSIGNED NOT NULL,
  `manager_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`packing_list_id` ASC),
  INDEX (`manager_id` ASC),
  FOREIGN KEY (`packing_list_id`)
  REFERENCES `cargo_traffic`.`packing_list` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`manager_id`)
  REFERENCES `cargo_traffic`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`waybill_vehicle_driver` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `waybill_id` INT(11) UNSIGNED NOT NULL,
  `vehicle_id` INT(11) UNSIGNED NOT NULL,
  `driver_id` INT(11) UNSIGNED NOT NULL,
  `status` ENUM('TRANSPORTATION_STARTED', 'TRANSPORTATION_COMPLETED'),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `waybill_vehicle_driver_UNIQUE` (`waybill_id` ASC, `vehicle_id` ASC, `driver_id` ASC),
  INDEX `wvd_vehicle_fk_idx` (`vehicle_id` ASC),
  INDEX `wvd_driver_fk_idx` (`driver_id` ASC),
  CONSTRAINT `wvd_waybill_fk`
  FOREIGN KEY (`waybill_id`)
  REFERENCES `cargo_traffic`.`waybill` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `wvd_vehicle_fk`
  FOREIGN KEY (`vehicle_id`)
  REFERENCES `cargo_traffic`.`vehicle` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `wvd_driver_fk`
  FOREIGN KEY (`driver_id`)
  REFERENCES `cargo_traffic`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `cargo_traffic`.`measure_unit` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `cargo_traffic`.`product` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `price` DECIMAL(10,2) NULL ,
  `measure_unit_id` INT(11) UNSIGNED NULL,
  `storage_type` ENUM ('REFRIGERATOR', 'TANK', 'BOXCAR'),
  `deleted` BIT(1) NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  INDEX `measure_unit_id_idx` (`measure_unit_id` ASC),
  FOREIGN KEY (`measure_unit_id`)
  REFERENCES `cargo_traffic`.`measure_unit` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE `cargo_traffic`.`product_in_packing_list` (
  `product_id` INT(11) UNSIGNED NOT NULL ,
  `packing_list_id` INT(11) UNSIGNED NOT NULL ,
  `count` INT(11) NULL ,
  `status` ENUM('ACCEPTED','VERIFICATION_COMPLETED','DELIVERED','LOST'),
  PRIMARY KEY (`product_id`, `packing_list_id`) ,
  INDEX `packing_list_idx` (`packing_list_id` ASC) ,
  INDEX `product_idx` (`product_id` ASC) ,
  FOREIGN KEY (`packing_list_id`)
  REFERENCES `cargo_traffic`.`packing_list` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  FOREIGN KEY (`product_id`)
  REFERENCES `cargo_traffic`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

INSERT INTO `measure_unit` (name) VALUES
  ('KILOGRAM'), ('LITER'), ('SQUARE_METER'), ('PIECES');

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`waypoint` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `lat` FLOAT NOT NULL ,
  `lng` FLOAT NOT NULL ,
  `status` ENUM('CREATED', 'CHECKED', 'UNCHECKED'),
  `waybill_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) ,
  INDEX `waybill_idx` (`waybill_id` ASC),
  FOREIGN KEY (`waybill_id`)
  REFERENCES `cargo_traffic`.`waybill` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`product_in_wvd` (
  `product_id` INT(11) UNSIGNED NOT NULL,
  `quantity` INT(11) UNSIGNED NULL DEFAULT 0,
  `wvd_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`, `wvd_id`),
  INDEX `prod_in_wvd_wvd_fk_idx` (`wvd_id` ASC),
  CONSTRAINT `prod_in_wvd_product_fk`
  FOREIGN KEY (`product_id`)
  REFERENCES `cargo_traffic`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `prod_in_wvd_wvd_fk`
  FOREIGN KEY (`wvd_id`)
  REFERENCES `cargo_traffic`.`waybill_vehicle_driver` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;

CREATE TABLE IF NOT EXISTS `cargo_traffic`.`lost_product` (
  `product_id` INT(11) UNSIGNED NOT NULL,
  `quantity` INT(11) UNSIGNED NULL DEFAULT 0,
  `driver_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`product_id`, `driver_id`),
  UNIQUE INDEX `lost_product_idx` (`driver_id`, `product_id` ASC),
  CONSTRAINT `lost_product_product_fk`
  FOREIGN KEY (`product_id`)
  REFERENCES `cargo_traffic`.`product` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT,
  CONSTRAINT `lost_product_driver_fk`
  FOREIGN KEY (`driver_id`)
  REFERENCES `cargo_traffic`.`user` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `cargo_traffic`.`financial_highlights` (
  `id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `delivered_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `transportation_income` DECIMAL(50,4) UNSIGNED NOT NULL,
  `vehicle_fuel_loss` DECIMAL(50,4) UNSIGNED NOT NULL,
  `products_loss` DECIMAL(50,4) UNSIGNED DEFAULT '0.00',
  `profit` DECIMAL(50,4) DEFAULT '0.00',
  `waybill_vehicle_driver_id` INT(11) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX (`waybill_vehicle_driver_id` ASC),
  FOREIGN KEY (`waybill_vehicle_driver_id`)
  REFERENCES `cargo_traffic`.`waybill_vehicle_driver` (`id`)
    ON DELETE RESTRICT
    ON UPDATE RESTRICT)
  ENGINE = InnoDB
  DEFAULT CHARACTER SET = utf8mb4;