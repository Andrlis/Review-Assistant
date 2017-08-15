DROP DATABASE IF EXISTS trtpo_sytem

CREATE DATABASE trtpo_sytem DEFAULT CHARACTER SET 'utf8'

USE trtpo_sytem;

CREATE TABLE lecturers(
id_lecturer int AUTO_INCREMENT,
full_name varchar(100) NOT NULL,
login varchar(20) NOT NULL,
password varchar(20) NOT NULL,
PRIMARY KEY(id_lecturer)
)


CREATE TABLE  groups(
id_group int AUTO_INCREMENT,
group_number varchar(10) NOT NULL,
bsuir_api_group_id varchar(10) NOT NULL,
PRIMARY KEY(id_group)
)



CREATE TABLE subgruops (
id_subgroup int AUTO_INCREMENT,
subgroup_number varchar(5) NOT NULL,
PRIMARY KEY(id_subgroup)
)


CREATE TABLE groups_subgroups (
id_group_subgroup int AUTO_INCREMENT,
id_subgroup int NOT NULL,
id_group int NOT NULL,
id_lecturer int NOT NULL,
PRIMARY KEY(id_group_subgroup)
)

CREATE TABLE students(
id_student int AUTO_INCREMENT,
full_name varchar(100) NOT NULL,
git_link varchar(30) NOT NULL,
email varchar(30) NULL,
id_group_subgroup int NOT NULL,
PRIMARY KEY(id_student),
FOREIGN KEY(id_group_subgroup) REFERENCES groups_subgroups(id_group_subgroup)
)


CREATE TABLE absentees(
id_absence int AUTO_INCREMENT,
id_class int NOT NULL,
id_student int NOT NULL,
PRIMARY KEY(id_absence)
)

CREATE TABLE classes(
id_class int AUTO_INCREMENT,
class_date date NOT NULL,
class_time time NOT NULL,
id_group_subgroup int NOT NULL,
PRIMARY KEY(id_class)
)

CREATE TABLE issued_labs(
id_issued_lab int AUTO_INCREMENT,
id_lab int NOT NULL,
id_group_subgroup int NOT NULL
id_class_of_issue int NOT NULL,
coefficient decimal NOT NULL,			-
id_class_deadline int NOT NULL,
last_check_date_time timestamp NOT NULL,
PRIMARY KEY(id_issued_lab)
)


CREATE TABLE labs(
id_lab int AUTO_INCREMENT,
lab_number int NOT NULL,
key_word varchar(20) NOT NULL,
PRIMARY KEY(id_lab)
)


CREATE TABLE labs_marks(
id_lab_mark int AUTO_INCREMENT,
id_student int NOT NULL,
id_issued_lab int NOT NULL,
coefficient decimal NOT NULL,
mark int NOT NULL,
PRIMARY KEY(id_lab_mark)
)


CREATE TABLE tests(
id_test int AUTO_INCREMENT,
test_number int NOT NULL,
test_date timestamp NOT NULL
PRIMARY KEY(id_test)
)

CREATE TABLE tests_result(
id_test_result int AUTO_INCREMENT,
id_student int NOT NULL,
id_test int NOT NULL,
mark int NOT NULL,
PRIMARY KEY(id_test_result),
FOREIGN KEY(id_test) REFERENCES tests(id_test)
)

CREATE TABLE bonuses(
id_bonus int AUTO_INCREMENT,
id_student int NOT NULL,
bonus int NOT NULL,
PRIMARY KEY(id_bonus)
)
