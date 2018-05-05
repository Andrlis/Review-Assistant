USE trtpo_system;

SET NAMES 'utf8';
SET CHARACTER SET 'utf8';
SET SESSION collation_connection = 'utf8_general_ci';

INSERT INTO lecturers (full_name)
VALUES ('Искра Наталья Александровна');
INSERT INTO lecturers (full_name)
VALUES ('Яночкин Алексей Леонидович');


INSERT INTO users (username, password, role)
VALUES ("Iskra", "Iskra", "USER");
INSERT INTO users (username, password, role)
VALUES ("Yanochkin", "Yanochkin", "USER");
INSERT INTO users (username, password, role)
VALUES ("Admin", "Admin", "ADMIN");


INSERT INTO groups (group_number)
VALUES ('550501');

INSERT INTO subgroups (id_group, subgroup_number, id_lecturer)
VALUES (
  (SELECT id_group
   FROM groups
   WHERE group_number = '550501'),
  '1',
  (SELECT id_lecturer
   FROM lecturers
   WHERE full_name = 'Искра Наталья Александровна')
);

INSERT INTO classes (class_date, id_subgroup)
VALUES (
  "2018-01-11 18:45:00",
  (SELECT MAX(id_subgroup)
   FROM subgroups WHERE id_group IN (
     SELECT id_group FROM groups WHERE group_number = '550501'))
);


INSERT INTO groups (group_number)
VALUES ('550502');

INSERT INTO subgroups (id_group, subgroup_number, id_lecturer)
VALUES (
  (SELECT id_group
   FROM groups
   WHERE group_number = '550502'),
  '1',
  (SELECT id_lecturer
   FROM lecturers
   WHERE full_name = 'Яночкин Алексей Леонидович')
);

INSERT INTO classes (class_date, id_subgroup)
VALUES (
  "2018-01-11 18:45:00",
  (SELECT MIN(id_subgroup)
   FROM subgroups WHERE id_group IN (
     SELECT id_group FROM groups WHERE group_number = '550502'))
);

INSERT INTO subgroups (id_group, subgroup_number, id_lecturer)
VALUES (
  (SELECT id_group
   FROM groups
   WHERE group_number = '550502'),
  '2',
  (SELECT id_lecturer
   FROM lecturers
   WHERE full_name = 'Яночкин Алексей Леонидович')
);

INSERT INTO classes (class_date, id_subgroup)
VALUES (
  "2018-01-11 18:45:00",
  (SELECT MAX(id_subgroup)
   FROM subgroups WHERE id_group IN (
     SELECT id_group FROM groups WHERE group_number = '550502'))
);
