DROP DATABASE IF EXISTS mydatabase;
CREATE DATABASE mydatabase;
USE mydatabase;

CREATE TABLE `MACHINE` (
    `machine_id` BIGINT NOT NULL AUTO_INCREMENT,
    `machine_name` VARCHAR(60) NULL,
    CONSTRAINT `PK_MACHINE` PRIMARY KEY (`machine_id`)
);

CREATE TABLE `PRODUCT` (
    `product_id` BIGINT NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(60) NULL,
    CONSTRAINT `PK_PRODUCT` PRIMARY KEY (`product_id`)
);

CREATE TABLE `WORKER` (
    `worker_id` BIGINT NOT NULL AUTO_INCREMENT,
    `worker_name` VARCHAR(60) NULL,
    CONSTRAINT `PK_WORKER` PRIMARY KEY (`worker_id`)
);

CREATE TABLE `WORKSHEET` (
    `worksheet_id` BIGINT NOT NULL AUTO_INCREMENT,
    `amount` INT NULL,
    `create_datetime` DATETIME NULL,
    `machine_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `worker_id` BIGINT NOT NULL,
    CONSTRAINT `PK_WORKSHEET` PRIMARY KEY (`worksheet_id`)
);

CREATE TABLE `RESULT` (
    `worksheet_id` BIGINT NOT NULL,
    `pass_qty` INT NULL,
    `fail_qty` INT NULL,
    `complete_datetime` DATETIME NULL,
    CONSTRAINT `PK_RESULT` PRIMARY KEY (`worksheet_id`),
    CONSTRAINT `FK_WORKSHEET_TO_RESULT_1` FOREIGN KEY (`worksheet_id`)
        REFERENCES `WORKSHEET` (`worksheet_id`)
);
