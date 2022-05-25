/*
creating basic types of relationships
	- One-To-One
    - One-To-Many
    - Many-To-Many
    - Self-Referencing
*/

CREATE SCHEMA `testRelationsCreate`;

use testRelationsCreate;

-- 1. One-To-One

CREATE TABLE `people`(
	`person_id` INT PRIMARY KEY AUTO_INCREMENT,
    `first_name` VARCHAR(20),
    `salary` DECIMAL(10,2),
    `passport_id` INT UNIQUE
);

CREATE TABLE `passport` (
	`passport_id` INT PRIMARY KEY AUTO_INCREMENT,
    `passport_number` VARCHAR(20) UNIQUE
);

ALTER TABLE  `people`
ADD CONSTRAINT fk_people_passport
FOREIGN KEY (`passport_id`)
REFERENCES passport(`passport_id`);

ALTER TABLE `people` 
AUTO_INCREMENT = 101;

-- 2. one-to-many relationship

 CREATE TABLE `manifacturer` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30) UNIQUE,
    `established_on` DATE
 );
 
 CREATE TABLE `models`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30),
    `manufacrure_id` INT ,
    CONSTRAINT fk_manifacturer_models
    FOREIGN KEY (`manufacrure_id`)
    REFERENCES manifacturer(`id`)
 );
 
 ALTER TABLE `models`
 AUTO_INCREMENT = 101;
 
 -- 3. many-to-many relationship

CREATE TABLE `exams`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(30)
)AUTO_INCREMENT = 101;

CREATE TABLE `students`(
	`id` INT PRIMARY KEY AUTO_INCREMENT,
	`name` VARCHAR(30)
);

CREATE TABLE `stutens_exams` (
	sudent_id INT ,
    exam_id int,
    CONSTRAINT pk_students_exams
    PRIMARY KEY (`sudent_id`,`exam_id`),
    CONSTRAINT fk_students_exams_students
    FOREIGN KEY (sudent_id)
    REFERENCES `students`(`id`),
    CONSTRAINT fk_students_exams_exams
    FOREIGN KEY (exam_id)
    REFERENCES `exams`(`id`)
);

-- 4. Self-Referencing

CREATE TABLE `teachers` (
	`id` INT PRIMARY KEY AUTO_INCREMENT,
    `name` VARCHAR(30),
    `manager_id` INT,
    CONSTRAINT fk_techers_teachers
    FOREIGN KEY (`manager_id`)
    REFERENCES teachers(`id`)
);

