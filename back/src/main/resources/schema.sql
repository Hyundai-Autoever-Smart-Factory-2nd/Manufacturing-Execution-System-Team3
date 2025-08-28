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
    `work_date` DATE NOT NULL,
    `create_datetime` DATETIME NULL,
    `machine_id` BIGINT NOT NULL,
    `product_id` BIGINT NOT NULL,
    `worker_id` BIGINT NOT NULL,
    CONSTRAINT `PK_WORKSHEET` PRIMARY KEY (`worksheet_id`)
);

CREATE TABLE `RESULT` (
    `worksheet_id` BIGINT NOT NULL,
    `is_passed` BOOL NULL,
    `complete_datetime` DATETIME NULL
);
