-- DROP DATABASE IF EXISTS trtpo_system;

CREATE DATABASE trtpo_system DEFAULT CHARACTER SET 'utf8';

USE trtpo_system;

CREATE TABLE lecturers(
id_lecturer INTEGER AUTO_INCREMENT,
full_name VARCHAR(100) NOT NULL,
PRIMARY KEY(id_lecturer)
);


CREATE TABLE  groups(
id_group INTEGER AUTO_INCREMENT,
group_number VARCHAR(20) NOT NULL,
bsuir_api_group_id VARCHAR(20) NOT NULL,
amount_of_test INTEGER NOT NULL,
PRIMARY KEY(id_group)
);




CREATE TABLE groups_subgroups (
id_group_subgroup INTEGER AUTO_INCREMENT,
id_group INTEGER NOT NULL,
id_lecturer INTEGER NOT NULL,
PRIMARY KEY(id_group_subgroup)
);

CREATE TABLE subgroups (
id_subgroup int AUTO_INCREMENT,
id_group_subgroup INTEGER NOT NULL,
subgroup_number varchar(5) NOT NULL,
PRIMARY KEY(id_subgroup),
FOREIGN KEY(id_group_subgroup) REFERENCES groups_subgroups(id_group_subgroup)
);

CREATE TABLE students(
id_student int AUTO_INCREMENT,
full_name varchar(100) NOT NULL,
git_user_name varchar(30) NOT NULL,
git_repo_name varchar(30) NOT NULL,
email varchar(30) NULL,
id_group_subgroup int NOT NULL,
PRIMARY KEY(id_student),
FOREIGN KEY(id_group_subgroup) REFERENCES groups_subgroups(id_group_subgroup)
);


CREATE TABLE absentees(
id_absence INTEGER AUTO_INCREMENT,
id_class INTEGER NOT NULL,
id_student INTEGER NOT NULL,
PRIMARY KEY(id_absence)
);


CREATE TABLE classes(
id_class INTEGER AUTO_INCREMENT,
class_date DATETIME NOT NULL,
id_group_subgroup INTEGER NOT NULL,
PRIMARY KEY(id_class)
);


CREATE TABLE issued_labs(
id_issued_lab INTEGER AUTO_INCREMENT,
id_lab INTEGER NOT NULL,
id_group_subgroup INTEGER NOT NULL,
id_class_of_issue INTEGER NOT NULL,
coefficient DECIMAL NOT NULL,
id_class_deadline INTEGER NOT NULL,
last_check_date_time DATETIME NOT NULL,
PRIMARY KEY(id_issued_lab)
);


CREATE TABLE labs(
id_lab INTEGER AUTO_INCREMENT,
lab_number INTEGER NOT NULL,
key_word VARCHAR(20) NOT NULL,
PRIMARY KEY(id_lab)
);


CREATE TABLE labs_marks(
id_lab_mark INTEGER AUTO_INCREMENT,
id_student INTEGER NOT NULL,
id_lab INTEGER NOT NULL,
coefficient DECIMAL NOT NULL,
mark INTEGER NOT NULL,
PRIMARY KEY(id_lab_mark)
);


CREATE TABLE tests(
id_test INTEGER AUTO_INCREMENT,
test_number INTEGER NOT NULL,
test_date TIMESTAMP NOT NULL,
PRIMARY KEY(id_test)
);


CREATE TABLE tests_result(
id_test_result INTEGER AUTO_INCREMENT,
id_student INTEGER NOT NULL,
id_test INTEGER NOT NULL,
mark INTEGER NOT NULL,
PRIMARY KEY(id_test_result),
FOREIGN KEY(id_test) REFERENCES tests(id_test)
);


CREATE TABLE bonuses(
id_bonus INTEGER AUTO_INCREMENT,
id_student INTEGER NOT NULL,
bonus INTEGER NOT NULL,
PRIMARY KEY(id_bonus)
);
