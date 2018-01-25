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

INSERT INTO groups(group_number, bsuir_api_group_id, amount_of_test)
VALUES ('550501', '550501', 0);
INSERT INTO groups(group_number, bsuir_api_group_id, amount_of_test)
VALUES ('550502', '550502', 0);
INSERT INTO groups(group_number, bsuir_api_group_id, amount_of_test)
VALUES ('550503', '550503', 0);
INSERT INTO groups(group_number, bsuir_api_group_id, amount_of_test)
VALUES ('550504', '550504', 0);

INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550501'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Яночкин Алексей Леонидович'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550501'), '1');

INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550502'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Яночкин Алексей Леонидович'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550502'),
	'1');

INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550502'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Яночкин Алексей Леонидович'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550502'),
	'2');


INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550503'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Искра Наталья Александровна'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550503'),
	'1');

INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550503'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Искра Наталья Александровна'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550503'),
	'2');

INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550504'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Искра Наталья Александровна'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550504'),
	'1');

INSERT INTO groups_subgroups(id_group, id_lecturer)
VALUES ((SELECT id_group FROM groups WHERE group_number='550504'),
	(SELECT id_lecturer FROM lecturers WHERE full_name='Искра Наталья Александровна'));
INSERT INTO subgroups (id_group_subgroup, subgroup_number)
VALUES ((SELECT MAX(id_group_subgroup)
	FROM groups_subgroups AS gr_sub
	INNER JOIN groups AS gr ON gr.id_group=gr_sub.id_group
	WHERE group_number='550504'),
	'2');