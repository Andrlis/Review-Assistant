USE trtpo_system;

TRUNCATE TABLE lecturers;
TRUNCATE TABLE absentees;
TRUNCATE TABLE classes;
TRUNCATE TABLE labs_marks;
TRUNCATE TABLE issued_labs;
TRUNCATE TABLE labs;
TRUNCATE TABLE tests_result;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE tests;
SET FOREIGN_KEY_CHECKS = 1;
TRUNCATE TABLE bonuses;
TRUNCATE TABLE users;
TRUNCATE TABLE students;
TRUNCATE TABLE subgroups;
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE groups_subgroups;
SET FOREIGN_KEY_CHECKS = 1;
TRUNCATE TABLE groups;

ALTER TABLE lecturers AUTO_INCREMENT=0;
ALTER TABLE groups AUTO_INCREMENT=0;
ALTER TABLE groups_subgroups AUTO_INCREMENT=0;
ALTER TABLE subgroups AUTO_INCREMENT=0;
ALTER TABLE students AUTO_INCREMENT=0;
ALTER TABLE classes AUTO_INCREMENT=0;
ALTER TABLE absentees AUTO_INCREMENT=0;
ALTER TABLE labs AUTO_INCREMENT=0;
ALTER TABLE issued_labs AUTO_INCREMENT=0;
ALTER TABLE labs_marks AUTO_INCREMENT=0;
ALTER TABLE tests AUTO_INCREMENT=0;
ALTER TABLE tests_result AUTO_INCREMENT=0;
ALTER TABLE bonuses AUTO_INCREMENT=0;
ALTER TABLE users AUTO_INCREMENT=0;

INSERT INTO users(username, password) VALUES ('admin', 'admin');

INSERT INTO lecturers(full_name) VALUES ('Искра Наталья Алексндровна');
INSERT INTO lecturers(full_name) VALUES ('Яночкин Алексей Леонидович');

INSERT INTO groups(group_number, bsuir_api_group_id, amount_of_test) VALUES ('550501', '21365', 3);
INSERT INTO groups(group_number, bsuir_api_group_id, amount_of_test) VALUES ('550502', '21366', 3);

INSERT INTO groups_subgroups(id_group, id_lecturer) VALUES (1, 1);
INSERT INTO groups_subgroups(id_group, id_lecturer) VALUES (1, 1);
INSERT INTO groups_subgroups(id_group, id_lecturer) VALUES (2, 2);

INSERT INTO subgroups(id_group_subgroup, subgroup_number) VALUES (1, '1');
INSERT INTO subgroups(id_group_subgroup, subgroup_number) VALUES (1, '2');
INSERT INTO subgroups(id_group_subgroup, subgroup_number) VALUES (2, '1');
INSERT INTO subgroups(id_group_subgroup, subgroup_number) VALUES (2, '1');

INSERT INTO students(full_name, git_user_name, git_repo_name, email, id_group_subgroup) VALUES ('Лисовский Андрей Геннадьевич', 'Andrlis', 'Review-Assistant', 'email@gmail.com', 1);
INSERT INTO students(full_name, git_user_name, git_repo_name, email, id_group_subgroup) VALUES ('Кессо Павел Иванович', 'KessoPavel', 'Review-Assistant', 'email@gmail.com', 2);
INSERT INTO students(full_name, git_user_name, git_repo_name, email, id_group_subgroup) VALUES ('Рунова Юлия Геннадьевна', 'agreenwalrus', 'Review-Assistant', 'email@gmail.com', 2);
INSERT INTO students(full_name, git_user_name, git_repo_name, email, id_group_subgroup) VALUES ('Иван Иванович Иванов', 'Ivanov', 'Ivanov-Repo', 'ivanov@gmail.com', 3);

INSERT INTO classes(class_date, id_group_subgroup) VALUES ('2017-12-10 09:56:00', 1);
INSERT INTO classes(class_date, id_group_subgroup) VALUES ('2017-12-10 10:56:00', 2);
INSERT INTO classes(class_date, id_group_subgroup) VALUES ('2017-12-10 11:56:00', 3);
INSERT INTO classes(class_date, id_group_subgroup) VALUES ('2017-12-11 10:56:00', 1);
INSERT INTO classes(class_date, id_group_subgroup) VALUES ('2017-12-11 11:56:00', 2);
INSERT INTO classes(class_date, id_group_subgroup) VALUES ('2017-12-11 12:56:00', 3);

INSERT INTO absentees(id_class, id_student) VALUES (1, 1);
INSERT INTO absentees(id_class, id_student) VALUES (3, 4);

INSERT INTO labs(lab_number, key_word) VALUES (1, 'Lab1');
INSERT INTO labs(lab_number, key_word) VALUES (2, 'Lab2');

INSERT INTO issued_labs(id_lab, id_group_subgroup, id_class_of_issue, coefficient, id_class_deadline, last_check_date_time) VALUES (1, 1, 1, 1.0, 4, '2017-12-10 14:56:00');
INSERT INTO issued_labs(id_lab, id_group_subgroup, id_class_of_issue, coefficient, id_class_deadline, last_check_date_time) VALUES (1, 2, 2, 1.0, 5, '2017-12-10 14:56:00');

INSERT INTO labs_marks(id_student, id_issued_lab, coefficient, mark) VALUES (1, 1, 1.0, 9);
INSERT INTO labs_marks(id_student, id_issued_lab, coefficient, mark) VALUES (2, 1, 1.0, 9);
INSERT INTO labs_marks(id_student, id_issued_lab, coefficient, mark) VALUES (3, 1, 1.0, 9);
INSERT INTO labs_marks(id_student, id_issued_lab, coefficient, mark) VALUES (4, 2, 1.0, 9);

INSERT INTO tests(test_number, test_date) VALUES (1, '2017-12-2 14:56:00');

INSERT INTO tests_result(id_student, id_test, mark) VALUES (1, 1, 8);
INSERT INTO tests_result(id_student, id_test, mark) VALUES (2, 1, 8);
INSERT INTO tests_result(id_student, id_test, mark) VALUES (3, 1, 8);
INSERT INTO tests_result(id_student, id_test, mark) VALUES (4, 1, 8);

INSERT INTO bonuses(id_student, bonus) VALUES (3, 10);