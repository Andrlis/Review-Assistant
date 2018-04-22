SET NAMES 'utf8';
SET CHARACTER SET 'utf8';
SET SESSION collation_connection = 'utf8_general_ci';

USE trtpo_system;

INSERT INTO lecturers(full_name)
VALUES ('Искра Наталья Александровна');
INSERT INTO lecturers(full_name)
VALUES ('Яночкин Алексей Леонидович');

INSERT INTO users(username, password, id_lecturer)
VALUES ("Iskra", "Iskra",
	(SELECT id_lecturer FROM lecturers WHERE full_name='Искра Наталья Александровна'));
INSERT INTO users(username, password, id_lecturer)
VALUES ("Yanochkin", "Yanochkin",
	(SELECT id_lecturer FROM lecturers WHERE full_name='Яночкин Алексей Леонидович'));

INSERT INTO groups(group_number)
VALUES ('550501');

INSERT INTO subgroups (id_group, subgroup_number)
VALUES ((SELECT MAX(id_group), '1');
INSERT INTO classes(id_subgroup, class_date)
VALUES ((SELECT MAX(id_group_subgroup), "2018-01-11 18:45:00");
